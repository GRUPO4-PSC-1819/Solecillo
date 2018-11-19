package Analisisdedatos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.Instance;
import weka.core.Instances;




public class Cargar_datos {

	 public static void main( String[ ] args ) {
		 //Load the iris dataset and set its class index
		 Instances data = null;
		try {
			data = new Instances(new FileReader("iris.arff"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 data.setClassIndex(data.numAttributes() - 1);
		 System.out.println(data);
		 System.out.println(data.numInstances());
		 
		 
		double Data[][] = new double[data.numInstances()][data.numAttributes() ];
		   for (int i = 0; i < data.numInstances(); i++) {
		       for (int j = 0; j < data.numAttributes(); j++) {
		           Data[i][j] = data.instance(i).value(j);
		        }
		    }
		    
		 /*System.out.println(data);
		 data.randomize(new java.util.Random(0));
		 System.out.println(data);
		 
		 
		 int percent=80;
		int trainSize = (int) Math.round(data.numInstances() * percent
				    / 100);
		int testSize = data.numInstances() - trainSize;
		Instances train = new Instances(data, 0, trainSize);
		Instances test = new Instances(data, trainSize, testSize);
		 
		 RandomForest forest=new RandomForest();
		 try {
			forest.buildClassifier(train);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 Evaluation eval = null;
		try {
			eval = new Evaluation(train);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			try {
				eval.evaluateModel(forest, test);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("** Decision Tress Evaluation with Datasets **");
			System.out.println(eval.toSummaryString());
			PrintWriter writer = null;
			try {
				writer = new PrintWriter("the-file-name.txt", "UTF-8");
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			writer.println(eval.toSummaryString());
			
			writer.close();
			System.out.print(" the expression for the input data as per alogorithm is ");
		*/

		    
	   }
}
