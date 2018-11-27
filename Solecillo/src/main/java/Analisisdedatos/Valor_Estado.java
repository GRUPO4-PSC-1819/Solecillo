package Analisisdedatos;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import LN.clsGestor;
import LN.clsMaquina;
import LP.clsAltaEolica;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

/**
 * Clase para crear un gráfico circular en el que se analizan el valor media de las máquinas según su estado.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Josune Ordoñez (Josune07)
 */
public class Valor_Estado extends JFrame {
   
	
private static final boolean ANYADIR_A_FIC_LOG = true;
	
	/*Logger*/
	private static Logger logger = Logger.getLogger( "Solecillo" );
	static 
	{
		try 
		{
			logger.setLevel( Level.FINEST );
			Formatter f = new SimpleFormatter() 
			{
				@Override
				public synchronized String format(LogRecord record) 
				{
					if (record.getLevel().intValue()<Level.CONFIG.intValue())
						return "\t\t(" + record.getLevel() + ") " + record.getMessage() + "\n";
					if (record.getLevel().intValue()<Level.WARNING.intValue())
						return "\t(" + record.getLevel() + ") " + record.getMessage() + "\n";
					return "(" + record.getLevel() + ") " + record.getMessage() + "\n";
				}
			};
			FileOutputStream fLog = new FileOutputStream( "Solecillo"+".log" , ANYADIR_A_FIC_LOG );
			Handler h = new StreamHandler( fLog, f );
			h.setLevel( Level.FINEST );
			logger.addHandler( h );
		} 
		catch (SecurityException | IOException e) 
		{
			logger.log( Level.SEVERE, "No se ha podido crear fichero de log en clase "+ clsAltaEolica.class.getName() );
		}
		logger.log( Level.INFO, "" );
		logger.log( Level.INFO, DateFormat.getDateTimeInstance( DateFormat.LONG, DateFormat.LONG ).format( new Date() ) );
	}
	private static final long serialVersionUID = 1L;

public Valor_Estado( String title ) {
	  
      super( title ); 
      this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      setContentPane(createDemoPanel( ));
      JButton btngrafico3 = new JButton("Valorar modelo");
		btngrafico3.setBounds(30, 150, 300, 100);
		btngrafico3.setFont(new Font("Arial", Font.PLAIN, 16));
		btngrafico3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {				
				Instances data = null;
				try {
					data = new Instances(new FileReader("iris.arff"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				data.setClassIndex(data.numAttributes() - 1);
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
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				 Evaluation eval = null;
				try {
					eval = new Evaluation(train);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
					try {
						eval.evaluateModel(forest, test);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					System.out.println("** Decision Tress Evaluation with Datasets **");
					System.out.println(eval.toSummaryString());
					PrintWriter writer = null;
					try {
						writer = new PrintWriter("Informe.txt", "UTF-8");
					} catch (FileNotFoundException | UnsupportedEncodingException e1) {
						e1.printStackTrace();
					}
					writer.println(eval.toSummaryString());
					
					writer.close();
			}});
		
      this.getContentPane().add(btngrafico3);
   }
   
   private static PieDataset createDataset( ) {
      DefaultPieDataset dataset = new DefaultPieDataset( );
      clsGestor gestor=new clsGestor();
      ArrayList<clsMaquina>lm=gestor.valor_estado_maquina();
      logger.log( Level.INFO, "Introduciendo datos en el gráfico de Valor_Estado");
      for(clsMaquina m:lm)
      {
    	  dataset.setValue(m.getEstado(), m.getValor());
      }
      return dataset;         
   }
   
   private static JFreeChart createChart( PieDataset dataset ) {
      JFreeChart chart = ChartFactory.createPieChart3D(      
         "Valor Medio",   // chart title 
         dataset,          // data    
         true,             // include legend   
         true, 
         false);

      return chart;
   }
   
   public static JPanel createDemoPanel( ) {
      JFreeChart chart = createChart(createDataset( ) );
      
      
      return new ChartPanel( chart ); 
   }

}