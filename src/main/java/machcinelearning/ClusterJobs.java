/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package machcinelearning;

import User.EmploiJob;
import static User.HandleDB.fetchJobs;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.JFrame;
import weka.classifiers.evaluation.ThresholdCurve;
import weka.clusterers.AbstractClusterer;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.Clusterer;
import weka.clusterers.EM;
import weka.clusterers.SimpleKMeans;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils.DataSource;

/**
 *
 * @author ryota
 */
public class ClusterJobs {
    
//   public static void maina(String[] args) throws Exception {
//    // load data
//    Instances train = DataSource.read(Utils.getOption('t', args));
//    // some data formats store the class attribute information as well
//    if (train.classIndex() != -1)
//      throw new IllegalArgumentException("Data cannot have class attribute!");
//
//    // instantiate clusterer
//    String[] options = Utils.splitOptions(Utils.getOption('W', args));
//    String classname = options[0];
//    options[0] = "";
//    Clusterer clusterer = AbstractClusterer.forName(classname, options);
//
//    // evaluate clusterer
//    clusterer.buildClusterer(train);
//    ClusterEvaluation eval = new ClusterEvaluation();
//    eval.setClusterer(clusterer);
//    eval.evaluateClusterer(train);
//
//    // setup visualization
//    // taken from: ClustererPanel.startClusterer()
//   
//    
//    Evaluation test = 
//    
//  }
    
    public static HashMap<Integer,ArrayList<EmploiJob>> getClusters(int n) throws FileNotFoundException, IOException, Exception{
        
                HashMap<Integer,ArrayList<EmploiJob>> jobsType = new HashMap<Integer,ArrayList<EmploiJob>>();
                Instances data = new Instances(new BufferedReader(new FileReader("ClusterData.arff")));

                SimpleKMeans model1 = new SimpleKMeans();
		EM model = new EM();
                
                model.setNumClusters(n);
   
		model.buildClusterer(data);
		System.out.println(model);
                

                
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
    
    
    public static void evaluateCluster() throws FileNotFoundException, IOException, Exception{
        //load data
        //we had a very good value of 71%
        //this is why : 
        Instances data = new Instances(new BufferedReader(new FileReader("ClusterData.arff")));
        EM cl = new EM();
        cl.buildClusterer(data);
        ClusterEvaluation eval = new ClusterEvaluation();
        eval.setClusterer(cl);
        eval.evaluateClusterer(new Instances(data));
        System.out.println(eval.clusterResultsToString());
        

        
        
        
        
    }
    
    
    public static void main(String args[]) throws Exception{
//	   HashMap<Integer,ArrayList<EmploiJob>> jobsType = getClusters(4);
//           for (Map.Entry<Integer, ArrayList<EmploiJob>> entry : jobsType.entrySet()) {
//    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//               System.out.println("**************");
//}

                evaluateCluster();
       }

}
