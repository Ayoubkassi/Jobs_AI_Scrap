/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package machcinelearning;

import model.EmploiJob;
import static database.HandleDB.fetchJobs;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.EM;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author pattern
 */
public class ClusterJobs {
    
    public static void buildModel(int n) throws FileNotFoundException, IOException, Exception{
               Instances data = new Instances(new BufferedReader(new FileReader("ClusterData.arff")));
                EM model = new EM();

                model.setNumClusters(n);

                 model.buildClusterer(data);
                 
                 weka.core.SerializationHelper.write("cluster_model.model",model);

    }

    public static HashMap<Integer, ArrayList<EmploiJob>> getClusters(int n)
            throws FileNotFoundException, IOException, Exception {

        HashMap<Integer, ArrayList<EmploiJob>> jobsType = new HashMap<Integer, ArrayList<EmploiJob>>();
        Instances data = new Instances(new BufferedReader(new FileReader("ClusterData.arff")));

        EM model = new EM();

        model.setNumClusters(n);

        model.buildClusterer(data);
        
        //build model
        //weka.core.SerializationHelper.write("cluster_model.model",model);
        
        System.out.println(model);

        ArrayList<EmploiJob> jobs = fetchJobs();

        for (int i = 0; i < n; i++) {
            ArrayList<EmploiJob> jobs1 = new ArrayList<EmploiJob>();
            int iter = 0;
            for (Instance in : data) {
                if (model.clusterInstance(in) == i) {
                    jobs1.add(jobs.get(iter));
                }
                iter++;
            }

            jobsType.put(i, jobs1);
        }

        return jobsType;

    }

    public static void evaluateCluster() throws FileNotFoundException, IOException, Exception {
        // load data
        // we had a very good value of 71%
        // this is why :
        Instances data = new Instances(new BufferedReader(new FileReader("ClusterData.arff")));
        EM cl = new EM();
        cl.buildClusterer(data);
        ClusterEvaluation eval = new ClusterEvaluation();
        eval.setClusterer(cl);
        eval.evaluateClusterer(new Instances(data));
        System.out.println(eval.clusterResultsToString());

    }

    public static void main(String args[]) throws Exception {
        
        buildModel(4);
    }

}
