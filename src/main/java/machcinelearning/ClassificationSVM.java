/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package machcinelearning;

import weka.classifiers.functions.SMO;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

/**
 *
 * @author pattern
 */
public class ClassificationSVM {
    public static void main(String[] args) throws Exception {
        ConverterUtils.DataSource source = new ConverterUtils.DataSource("/home/ryota/Desktop/new1.arff");
        Instances dataset = source.getDataSet();

        // specifiy the class attribute
        dataset.setClassIndex(dataset.numAttributes() - 1);

        // create and build the classifier
        SMO svm = new SMO();

        svm.buildClassifier(dataset);

        // print result

        System.out.println(svm.getCapabilities().toString());

    }
}
