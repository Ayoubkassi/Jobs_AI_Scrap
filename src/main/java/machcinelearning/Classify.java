package machcinelearning;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Random;

import javax.swing.JFrame;

import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;

import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.SMO;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Utils;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

public class Classify{
	
	//the function to put in the gui prediction 
public static String predict(double[] valleurs) {
	try {
		RandomForest tree = (RandomForest) weka.core.SerializationHelper.read("/home/ryota/Desktop/CRUD/Java_Desktop/classify_model");
		DataSource source = new DataSource("/home/ryota/Desktop/CRUD/Java_Desktop/classify/classificationdata.arff");
		Instances data = source.getDataSet();
		data.setClassIndex(data.numAttributes()-1);
		Instance job = new DenseInstance(1.0, valleurs);
		//Assosiate your instance with Instance object in this case dataRaw
		job.setDataset(data); 

		double label = tree.classifyInstance(job);
		String prediction =data.classAttribute().value((int) label);
		return prediction;
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	
		
	}

	public static void main(String[] args) throws Exception {

		/*
		 * Load the data
		 */
		DataSource source = new DataSource("/home/ryota/Desktop/CRUD/Java_Desktop/classify/classificationdata.arff");
		Instances data = source.getDataSet();
		System.out.println(data.numInstances() + " instances loaded.");
		// System.out.println(data.toString());

		// remove animal attribute
		String[] opts = new String[] { "-R", "1" };
		Remove remove = new Remove();
		remove.setOptions(opts);
		remove.setInputFormat(data);
		data = Filter.useFilter(data, remove);
		
		NumericToNominal convert=new NumericToNominal();
        convert.setInputFormat(data);

		Instances newData=Filter.useFilter(data, convert);

		/*
		 * Feature selection
		 */
		AttributeSelection attSelect = new AttributeSelection();
		InfoGainAttributeEval eval = new InfoGainAttributeEval();
		Ranker search = new Ranker();
		attSelect.setEvaluator(eval);
		attSelect.setSearch(search);
		attSelect.SelectAttributes(data);
		int[] indices = attSelect.selectedAttributes();
		System.out.println("Selected attributes: "+Utils.arrayToString(indices));

		/*
		 * Build a decision tree
		 */
		String[] options = new String[1];
		options[0] = "-O";
		RandomForest tree = new RandomForest();
		tree.setOptions(options);
		tree.buildClassifier(data);
		System.out.println(tree);

		/*
		 * Classify new instance.
		 */
		double[] vals = new double[data.numAttributes()];
		//1,
		vals[0]=1;
		vals[1]=0;
		vals[2]=1;
		vals[3]=1;
		vals[4]=0;
		vals[5]=0;
		vals[6]=1;
		vals[7]=1;
		vals[8]=0;
		vals[9]=0;
		vals[10]=0;
		vals[11]=0;
		vals[2]=0;
		vals[2]=0;
		vals[2]=0;
		vals[2]=0;
		vals[2]=1;
		vals[2]=0;
		vals[2]=1;
		vals[2]=0;
		vals[2]=0;
		vals[2]=1;
		vals[2]=1;
		vals[2]=1;
		vals[2]=1;
		vals[2]=0;
		vals[2]=0;
		vals[2]=0;
		vals[2]=0;
		vals[2]=0;
		vals[2]=0;
		vals[2]=1;
		vals[2]=1;
		vals[2]=0;
		vals[2]=0;
		vals[2]=1;
		vals[2]=1;
		vals[2]=0;
		vals[2]=1;
		vals[2]=0;
		vals[2]=0;
		vals[2]=0;
		vals[2]=0;
		vals[2]=0;
		
		

		System.out.println(predict(vals));

	
	

		/*
		 * Evaluation
		 */

		Classifier cl = new RandomForest();
		Evaluation eval_roc = new Evaluation(data);
		eval_roc.crossValidateModel(cl, data, 10, new Random(1), new Object[] {});
		System.out.println(eval_roc.toSummaryString());
		// Confusion matrix
		double[][] confusionMatrix = eval_roc.confusionMatrix();
		System.out.println(eval_roc.toMatrixString());

		
		//start serialisation----------
		 try
	     {
	         weka.core.SerializationHelper.write("classify_model", tree);
	     } 
	     catch (IOException ioe) 
	     {
	         ioe.printStackTrace();
	     }
	
	}
	
}
