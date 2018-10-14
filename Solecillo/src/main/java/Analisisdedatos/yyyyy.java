package Analisisdedatos;

import java.util.ArrayList;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.Prediction;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class yyyyy {
	
	public static double sigmoidsemana(double x) {
	    return (1/( 1 + Math.pow(Math.E,(-0.1*(x-50)))));
	  }
	public static double sigmoidrep(double x) {
	    return (1/( 1 + Math.pow(Math.E,(-0.5*(x-3)))));
	  }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Attribute> atts = new ArrayList<Attribute>(3);
		ArrayList<String> classVal = new ArrayList<String>();
        classVal.add("No peligra");
        classVal.add("Peligra");
        atts.add(new Attribute("Semana"));
        atts.add(new Attribute("Reparaciones"));
        atts.add(new Attribute("@@class@@",classVal));
		 
		Instances data=new Instances("Train",atts,0);
		Instances test=new Instances("Test",atts,0);
		
		
		
		double[] instanceValue1 = new double[data.numAttributes()];
	    instanceValue1[0] = 244;
	    instanceValue1[1] = 122;
	    instanceValue1[2] = 0;
	    DenseInstance denseInstance1 = new DenseInstance(1, instanceValue1);
	 
	    data.add(denseInstance1);
	    
	    instanceValue1 = new double[data.numAttributes()];
	    
	    instanceValue1[0] = 300;
	    instanceValue1[1] = 10;
	    instanceValue1[2] = 1;
	   
	    DenseInstance denseInstance2 = new DenseInstance(1, instanceValue1);

	    data.add(denseInstance2);
	    
	    for (int i=0;i<50;i++){
	    	data.add(denseInstance2);
	    	data.add(denseInstance1);
	    }
	    System.out.println(data);
	    
	    data.setClassIndex(data.numAttributes() - 1);  
	    
	    test.add(denseInstance1);
	    test.add(denseInstance2);
	    test.add(denseInstance2);
	    
	    test.setClassIndex(data.numAttributes() - 1);
	    
	   System.out.println(test);
	   System.out.println(data);
	    
	    
	    Classifier cls = new J48();
	    try {
			cls.buildClassifier(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Evaluation eval = null;
		try {
			eval = new Evaluation(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			eval.evaluateModel(cls, test);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    for(Prediction a:eval.predictions())
	    {
	    	System.out.println("RRRRRRRRRRRRRRRRRRR");
	    	System.out.println(a.weight());
	    	System.out.println(a.actual());
	    	
	    }
	    //System.out.println(eval.predictions().get(0));
	    System.out.println(eval.toSummaryString("\nResults\n======\n", false));
	    
	}
}
