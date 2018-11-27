package Analisisdedatos;

import java.io.FileOutputStream;
import java.io.IOException;
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

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import LN.clsGestor;
import LN.clsMaquina_Eolica;
import LP.clsAltaEolica;

/**
 * Clase para crear un gráfico de barras en las que se analizan las máquinas eólicas. Se hace referencia al pueblo y a la altura y diamtero medios. 
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Josune Ordoñez (Josune07)
 */


public class Pueblo_Altura_Diametro extends JFrame {
   
	private static final long serialVersionUID = 1L;

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
public Pueblo_Altura_Diametro( String applicationTitle , String chartTitle ) {
      super( applicationTitle );  
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "Media / Pueblo",            
         "Metros",            
         createDataset(),          
         PlotOrientation.VERTICAL,           
         true, true, false);
         
      ChartPanel chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 800 , 500 ) );        
       setContentPane( chartPanel ); 
   }
   
   private CategoryDataset createDataset( ) {
	  
	  clsGestor ges=new clsGestor(); 
	  ArrayList<clsMaquina_Eolica> lm=new ArrayList<clsMaquina_Eolica>(); 
	  lm=ges.eolicas_pueblo_media_altura_diametro();
      final DefaultCategoryDataset dataset=new DefaultCategoryDataset( );
      logger.log( Level.INFO, "Introduciendo datos en el gráfico de Pueblo_Altura_Diametro");
      for(int i=0;i<lm.size();i++)
      {
    	  dataset.setValue(lm.get(i).getAltura(), "Media Altura", lm.get(i).getNombre_pueblo());
    	  dataset.setValue(lm.get(i).getDiametro(), "Media Diametro", lm.get(i).getNombre_pueblo());
      }
	  return dataset; 
   }
}