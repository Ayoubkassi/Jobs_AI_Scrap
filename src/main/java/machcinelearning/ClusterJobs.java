/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package machcinelearning;

import User.EmploiJob;
import static User.HandleDB.fetchJobs;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.EM;
import weka.clusterers.SimpleKMeans;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author ryota
 */
public class ClusterJobs {
    
    public static HashMap<Integer,ArrayList<EmploiJob>> getClusters(int n) throws FileNotFoundException, IOException, Exception{
        
                HashMap<Integer,ArrayList<EmploiJob>> jobsType = new HashMap<Integer,ArrayList<EmploiJob>>();
                Instances data = new Instances(new BufferedReader(new FileReader("ClusterData.arff")));

                SimpleKMeans model1 = new SimpleKMeans();
		EM model = new EM();
                
                model.setNumClusters(n);
   
		model.buildClusterer(data);
		System.out.println(model);
                
                
                System.out.println("searched values is : ");
                double logLikelihood = ClusterEvaluation.crossValidateModel(model, data, 10, new Random(-1));
		System.out.println(logLikelihood);
                
                ArrayList<EmploiJob> jobs = fetchJobs();

       
                for (int i = 0; i < n; i++) {
                    ArrayList<EmploiJob> jobs1 = new ArrayList<EmploiJob>();
                    int iter = 0;
                    for(Instance in : data){
                        if(model.clusterInstance(in) == i){
                            jobs1.add(jobs.get(iter));
                        }
                        iter++;
                    }
                    
                    jobsType.put(i, jobs1);
                }   
                
                
                
                
               return jobsType;

    }
    
    
    public static void main(String args[]) throws Exception{
	   HashMap<Integer,ArrayList<EmploiJob>> jobsType = getClusters(4);
           for (Map.Entry<Integer, ArrayList<EmploiJob>> entry : jobsType.entrySet()) {
    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
               System.out.println("**************");
}
       }

}
