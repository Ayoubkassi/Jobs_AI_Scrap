/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package machcinelearning;

import User.EmploiJob;
import static User.HandleDB.fetchJobs;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
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
    public static void main(String args[]) throws Exception{
		
		//load data
		Instances data = new Instances(new BufferedReader(new FileReader("ClusterData.arff")));
		
		// new instance of clusterer
                //or we can use Simple KMeans
                SimpleKMeans model1 = new SimpleKMeans();
		EM model = new EM();
                
                model.setNumClusters(2);
                
		// build the clusterer
                //model1.buildClus
		model.buildClusterer(data);
		//System.out.println(model);
		System.out.println(model);
                //let's cluster an instaance
                
                ArrayList<EmploiJob> jobs = fetchJobs();
                for(EmploiJob job : jobs){
                    char[] requirement = job.getRequirements().toCharArray();
                    int[] requirements = new int[90];
                    for (int i = 0; i < 45; i++) {
                         requirements[i] = Character.getNumericValue(requirement[i*2]);
                         System.out.print(requirements[i]+" ");
                    }
                    
                    System.out.println("");
                     //Instance myTest = new DenseInstance(1.0, requirements);
                     try{
                     //System.out.println(job.getTitle() + "  ----------------  "+ model.clusterInstance(myTest));
                     }catch(Exception ex){
                         ex.printStackTrace();
                     }
                }
                  
                

                
		//double logLikelihood = ClusterEvaluation.crossValidateModel(model, data, 10, new Random(1));
		//System.out.println(logLikelihood);


	}

}
