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
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;

/**
 *
 * @author ryota
 */


public class LoadData {
    
    public static void main(String[] args) throws FileNotFoundException, IOException, Exception{
        
        DataSource source =  new DataSource("/home/ryota/Desktop/data.arff");
        Instances dataset = source.getDataSet();
        //Instances dataset = new Instances(new BufferedReader (new FileReader("/home/ryota/Desktop/data.arff")));
        
        //System.out.println(dataset.toSummaryString());
        
        ArffSaver saver = new ArffSaver();
        saver.setInstances(dataset);
        saver.setFile(new File("/home/ryota/Desktop/new1.arff"));
        saver.writeBatch();
        
    }
    
}
