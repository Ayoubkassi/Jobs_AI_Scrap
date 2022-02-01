/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package machcinelearning;

import java.util.Random;
import javax.swing.JFrame;
import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

/**
 *
 * @author pattern
 */
public class ClassificationJ48 {

	public static String predInstance(int[] tab) throws Exception {
		ConverterUtils.DataSource source = new ConverterUtils.DataSource("classificationdata.arff");
		Instances data = source.getDataSet();
		System.out.println(data.numInstances() + " instances loaded.");

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
		System.out.println("Selected attributes: " + Utils.arrayToString(indices));
                
		/*
		 * Build a decision tree
		 */
		String[] options = new String[1];
		options[0] = "-U";
		J48 tree = new J48();
		tree.setOptions(options);
		tree.buildClassifier(data);
		System.out.println(tree);
                
                Evaluation eval_roc = new Evaluation(data);
		eval_roc.crossValidateModel(tree, data, 10, new Random(1), new Object[] {});
		System.out.println(eval_roc.toSummaryString());
		// Confusion matrix
		double[][] confusionMatrix = eval_roc.confusionMatrix();
		System.out.println(eval_roc.toMatrixString());
		// Classify new instance.

		double[] vals = new double[data.numAttributes()];
		for (int i = 0; i < 45; i++) {
			vals[i] = tab[i];
		}
		Instance myJob = new DenseInstance(1.0, vals);
		// Assosiate your instance with Instance object in this case dataRaw
		myJob.setDataset(data);

		double label = tree.classifyInstance(myJob);
		return data.classAttribute().value((int) label);
	}

	public static void classify() throws Exception {
		ConverterUtils.DataSource source = new ConverterUtils.DataSource("classificationdata.arff");
		Instances data = source.getDataSet();
		System.out.println(data.numInstances() + " instances loaded.");

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
		System.out.println("Selected attributes: " + Utils.arrayToString(indices));

		/*
		 * Build a decision tree
		 */
		String[] options = new String[1];
		options[0] = "-U";
		J48 tree = new J48();
		tree.setOptions(options);
		tree.buildClassifier(data);
		System.out.println(tree);

		TreeVisualizer tv = new TreeVisualizer(null, tree.graph(),
				new PlaceNode2());
		JFrame frame = new javax.swing.JFrame("Tree Visualizer");
		frame.setSize(3200, 850);
		frame.getContentPane().add(tv);
		frame.setVisible(true);
		tv.fitToScreen();
	}

	public static void main(String[] args) throws Exception {
		// load the data
		// classify();

	}

}
