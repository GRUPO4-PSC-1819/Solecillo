package Analisisdedatos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.Prediction;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author imssbora
 */
public class ScatterPlotExample extends JFrame {
	static Instances data;
	static Instances test;
	public JButton btnAceptar_1;
	static LinkedList<DenseInstance> ltest;
	static XYSeries series1;
	static XYSeries series2;
	
	
  private static final long serialVersionUID = 6294689542092367723L;

  public ScatterPlotExample(String title) {
    super(title);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    ArrayList<Attribute> atts = new ArrayList<Attribute>(3);
	ArrayList<String> classVal = new ArrayList<String>();
    classVal.add("No peligra");
    classVal.add("Peligra");
    atts.add(new Attribute("Semana"));
    atts.add(new Attribute("Reparaciones"));
    atts.add(new Attribute("@@class@@",classVal));
    data=new Instances("Train",atts,0);
    test=new Instances("Train",atts,0);

    // Create dataset
    XYDataset dataset = createDataset();

    // Create chart
    JFreeChart chart = ChartFactory.createScatterPlot(
        "Máquinas con y sin problemas", 
        "Semanas", "Reparaciones", dataset);

    
    //Changes background color
    XYPlot plot = (XYPlot)chart.getPlot();
    plot.setBackgroundPaint(new Color(255,228,196));
    
   
    // Create Panel
    ChartPanel panel = new ChartPanel(chart);
    panel.setLayout(null);
    
    setContentPane(panel);
    
    btnAceptar_1 = new JButton("Predecir");
	btnAceptar_1.addActionListener(new ActionListener() 
	{
		
		public void actionPerformed(ActionEvent arg0) {
			ScatterPlotExample example2 = new ScatterPlotExample("Analisis de datos",1);
			example2.setSize(800, 400);
		      example2.setLocationRelativeTo(null);
		      example2.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		      example2.setVisible(true);
		}
	});
	btnAceptar_1.setBounds(600, 300, 100, 26);
	panel.add(btnAceptar_1);
	
  }

  public ScatterPlotExample(String string, int i) {
	super(string);
	
	XYDataset dataset2 = createTest();
	
    // Create chart
    JFreeChart chart2 = ChartFactory.createScatterPlot(
        "Máquinas con y sin problemas", 
        "Semanas", "Reparaciones", dataset2);

    
    //Changes background color
    XYPlot plot2 = (XYPlot)chart2.getPlot();
    plot2.setBackgroundPaint(new Color(255,228,196));
    
   
    // Create Panel
    ChartPanel panel2 = new ChartPanel(chart2);
    
    setContentPane(panel2);
	
}

private XYDataset createTest(){
	  XYSeriesCollection dataset2 = new XYSeriesCollection();
	  
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
		 int i=0;
		    for(Prediction a:eval.predictions())
		    {
		    	
		    	if(a.actual()==0)
		    	{
		    		series1.add(ltest.get(i).value(0),ltest.get(i).value(1));
		    	}
		    	else{
		    		series2.add(ltest.get(i).value(0),ltest.get(i).value(1));
		    	}
		    	i++;
		    	System.out.println(a.actual());
		    	System.out.println(i);
		    }
		    
		    dataset2.addSeries(series1);
		    dataset2.addSeries(series2);
	return dataset2;
	  
  }
  
  
  private XYDataset createDataset() {
    XYSeriesCollection dataset = new XYSeriesCollection();
    series1 = new XYSeries("No problemas");
    series2 = new XYSeries("Problemas");
    XYSeries series3 = new XYSeries("Nuevos");
    ltest=new LinkedList<DenseInstance>();
    

	
	double[] instanceValue = new double[data.numAttributes()];
	DenseInstance denseInstance;
    int auxx;
    int auxy;
    for (int i=0;i<70;i++)
    {
    	if(i<50){
    	instanceValue = new double[data.numAttributes()];
    	auxy = ThreadLocalRandom.current().nextInt(0, 8 + 1);
    	auxx=ThreadLocalRandom.current().nextInt(40, 60 + 1);
    	if(sigmoidsemana(auxx)*sigmoidrep(auxy)<0.4){
    		series1.add(auxx,auxy);
    		 instanceValue[0] = auxx;
    		 instanceValue[1] = auxy;
    		 instanceValue[2] =0;
    	}else
    	{
    		series2.add(auxx,auxy);
    		instanceValue[0] = auxx;
   		 	instanceValue[1] = auxy;
   		 	instanceValue[2] =1;
    	}
    	denseInstance = new DenseInstance(1, instanceValue);
    	data.add(denseInstance);
    	}
    	else
    	{
    		instanceValue = new double[data.numAttributes()];
        	auxy = ThreadLocalRandom.current().nextInt(0, 8 + 1);
        	auxx=ThreadLocalRandom.current().nextInt(40, 60 + 1);
        	if(sigmoidsemana(auxx)*sigmoidrep(auxy)<0.4){
        		series3.add(auxx,auxy);
        		 instanceValue[0] = auxx;
        		 instanceValue[1] = auxy;
        		 instanceValue[2] =0;
        	}else
        	{
        		series3.add(auxx,auxy);
        		instanceValue[0] = auxx;
       		 	instanceValue[1] = auxy;
       		 	instanceValue[2] =1;
        	}
        	
        	denseInstance = new DenseInstance(1, instanceValue);
        	ltest.add(denseInstance);
        	test.add(denseInstance);
    	}
    }  
    data.setClassIndex(data.numAttributes() - 1);
    test.setClassIndex(test.numAttributes() - 1);
    
    dataset.addSeries(series1);
    dataset.addSeries(series2);
    dataset.addSeries(series3);
    

    return dataset;
  }
	public static double sigmoidsemana(double x) {
	    return (1/( 1 + Math.pow(Math.E,(-0.1*(x-50)))));
	  }
	public static double sigmoidrep(double x) {
	    return (1/( 1 + Math.pow(Math.E,(-0.5*(x-3)))));
	  }
}