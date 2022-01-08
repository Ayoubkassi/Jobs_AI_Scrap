/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package machcinelearning;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 *
 * @author pattern
 */
public class ClassificationBayes {
    public static void main(String[] args) throws Exception {
        DataSource source = new DataSource("/home/ryota/Desktop/new1.arff");
        Instances dataset = source.getDataSet();

        // specifiy the class attribute
        dataset.setClassIndex(dataset.numAttributes() - 1);

        // create and build the classifier
        NaiveBayes nb = new NaiveBayes();

        nb.buildClassifier(dataset);

        // print result

        System.out.println(nb.getCapabilities().toString());

    }
}
