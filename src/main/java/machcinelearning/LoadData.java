/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package machcinelearning;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
 import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.converters.ArffLoader;


/**
 *
 * @author ryota
 */


public class LoadData {
    
    
    public static void nativeBayes() throws IOException, Exception{
         ArffLoader loader = new ArffLoader();
        loader.setFile(new File("/home/ryota/Desktop/new1.arff"));
        Instances structure = loader.getStructure();
        structure.setClassIndex(structure.numAttributes() - 1);

        // train NaiveBayes
        NaiveBayesUpdateable nb = new NaiveBayesUpdateable();
        nb.buildClassifier(structure);
        Instance current;
        while ((current = loader.getNextInstance(structure)) != null)
          nb.updateClassifier(current);
        
        System.out.println(nb);
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException, Exception{
        
//        DataSource source =  new DataSource("/home/ryota/Desktop/data.arff");
//        Instances dataset = source.getDataSet();
//        //Instances dataset = new Instances(new BufferedReader (new FileReader("/home/ryota/Desktop/data.arff")));
//        
//        //System.out.println(dataset.toSummaryString());
//        
//        ArffSaver saver = new ArffSaver();
//        saver.setInstances(dataset);
//        saver.setFile(new File("/home/ryota/Desktop/new1.arff"));
//        saver.writeBatch();
//        
//        //classifing
//        
//        //first using j48 algorithm
//        
//        String[] options = new String[1];
//        options[0] = "-U";            // unpruned tree
//        J48 tree = new J48();         // new instance of tree
//        tree.setOptions(options);     // set the options
//        tree.buildClassifier(dataset);   // build classifier

            nativeBayes();
        
        
        
    }
    
    
    
    
}
