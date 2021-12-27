/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package machcinelearning;

import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.SMO;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

/**
 *
 * @author ryota
 */
public class Prediction {
    //load training set
    
    public static void main(String[] args) throws Exception{
        //load training set
       ConverterUtils.DataSource source =  new ConverterUtils.DataSource("/home/ryota/Desktop/CRUD/Java_Desktop/dataset.arff");
       Instances trainingDataset = source.getDataSet();
       
       //specifiy the class attribute
       trainingDataset.setClassIndex(trainingDataset.numAttributes()-1);
       
       
       //get number of classes
       
       int nbClasses = trainingDataset.numClasses();
       
       //print class value of training set
       
        for (int i = 0; i < nbClasses; i++) {
            String classValue = trainingDataset.classAttribute().value(i);
            System.out.println("Class value "+i+" is "+ classValue);
            
            
        }
        
        //create and build the classifier
        
//        NaiveBayes nb = new NaiveBayes();
        SMO nb = new SMO();
        nb.buildClassifier(trainingDataset);

        
        
        //load testDataset
        ConverterUtils.DataSource source1 =  new ConverterUtils.DataSource("/home/ryota/Desktop/CRUD/Java_Desktop/dataset.arff");
        Instances testDataset = source1.getDataSet();
       
       //specifiy the class attribute
       testDataset.setClassIndex(trainingDataset.numAttributes()-1);
       
       //loop throw testset and make decisions
       
        System.out.println("*******************************************");
        System.out.println("Predicted class is : ");
        
        for (int i = 0; i < testDataset.numInstances()  ; i++) {
            double actualClass = testDataset.instance(i).classValue();
            String actual = testDataset.classAttribute().value((int) actualClass);
            
            Instance newInst = testDataset.instance(i);
            
            double predNB = nb.classifyInstance(newInst);
            
            String predString = testDataset.classAttribute().value((int) predNB);
            //System.out.println(actual+ "     --------------   "+predString);
            if(actual.equals(predString)){
                System.out.println(1);
            }
            else{
                System.out.println(0);
            }
            
            
            
        }
        
        
       
       
    }
        
        
}
