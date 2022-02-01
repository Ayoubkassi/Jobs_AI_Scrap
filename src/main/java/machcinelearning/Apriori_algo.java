/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package machcinelearning;

/**
 *
 * @author ryota
 */
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import weka.associations.AbstractAssociator;
import weka.associations.Apriori;
import weka.associations.AprioriItemSet;
import weka.associations.AssociationRules;
import weka.associations.Item;
import weka.associations.ItemSet;

public class Apriori_algo {

	public static ArrayList<String> recommande(ArrayList<ArrayList<ArrayList<String>>> liste_rules,
			ArrayList<String> skills) {
		ArrayList<String> recommandation = new ArrayList<String>();
		int max = 0;
		int max_item_recommanded = 0;

		for (int i = 0; i < liste_rules.get(0).size(); i++) {
			int max_items_found = 0;
			int recommanded_for_this = 0;
			for (int j = 0; j < skills.size(); j++) {

				if (liste_rules.get(0).get(i).contains(skills.get(j))) {
					max_items_found++;
				}

			}
			recommanded_for_this = liste_rules.get(2).get(i).size();
			if (max_items_found > max) {
				recommandation = liste_rules.get(2).get(i);
				max = max_items_found;

			} else if (max_items_found == max) {
				if (recommanded_for_this > max_item_recommanded) {
					recommandation = liste_rules.get(2).get(i);
				}
			}
		}

		return recommandation;
	}

	public static void main(String[] args) throws Exception {
		DataSource source = new DataSource("/home/ryota/Desktop/CRUD/Java_Desktop/association_data.arff");
		Instances data = source.getDataSet();
		System.out.println(data.numInstances() + " instances loaded.");

		// ----------

		// ---------------
		ArrayList<ArrayList<ArrayList<String>>> liste_rules = new ArrayList<ArrayList<ArrayList<String>>>();
		ArrayList<ArrayList<String>> rule = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> metrics = new ArrayList<ArrayList<String>>();
		ArrayList<String> cas_metric = new ArrayList<String>();
		ArrayList<String> cas = new ArrayList<String>();
		int iteration = 0;

		NumericToNominal convert = new NumericToNominal();
		convert.setInputFormat(data);

		Instances newData = Filter.useFilter(data, convert);

		String[] opts = new String[] { "-N", "1000", "-M", "0.07", "-C", "0.4" };

		Apriori model = new Apriori();
		model.setOptions(opts);
		model.buildAssociations(newData);
		ArrayList<Object>[] rules = model.getAllTheRules();
		// rules[0].get(10).atimes(0)== 0 -1 ------>
		// rules[1].get(10)--------rules[2].get(10) ==Double

		for (int i = 0; i < 2; i++) {

			for (int j = 0; j < rules[i].size(); j++) {
				Double metric_number = (Double) (rules[i + 2].get(j));
				String metric_string = metric_number.toString();
				cas_metric.add(metric_string);
				metrics.add(cas_metric);
				cas_metric = new ArrayList();

				AprioriItemSet permises = (AprioriItemSet) rules[i].get(j);
				for (int c = 0; c < 45; c++) {
					if (permises.itemAt(c) == 0) {
						cas.add(data.attribute(c).name());
					}
				}
				rule.add(cas);
				cas = new ArrayList();

			}
			// System.out.println(rule);System.out.println("\n");
			liste_rules.add(rule);
			liste_rules.add(metrics);
			rule = new ArrayList();
			metrics = new ArrayList();

		}

		// liste_rules.get(0|1|2|3).get(1-45)
		// item input |confidence |item_recom|lift

		// start serialisation----------
		try {
			FileOutputStream fos = new FileOutputStream("/home/ryota/Desktop/CRUD/Java_Desktop/listeRules");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(liste_rules);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		

	}

}
