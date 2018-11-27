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
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import LN.clsCliente;
import LN.clsGestor;
import LP.clsAltaEolica;

/**
 * Clase para crear un gráfico circular en el que se analizan los 5 clientes que más máquinas han comprado. 
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Josune Ordoñez (Josune07)
 */
public class top_clientes extends JFrame {
   
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

public top_clientes( String title ) {
	  
      super( title ); 
      this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      setContentPane(createDemoPanel( ));
      	
   }

public top_clientes( String title ,ArrayList<clsCliente> a) {
	  
    super( title ); 
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setContentPane(createDemoPanel( a));
    	
 }
 
   
   private static PieDataset createDataset( ) {
      DefaultPieDataset dataset = new DefaultPieDataset( );
      clsGestor gestor=new clsGestor();
      ArrayList<clsCliente> top_c=new ArrayList<clsCliente>();
      top_c=gestor.top_5_clientes();
      logger.log( Level.INFO, "Introduciendo datos en el gráfico de top_clientes");
      for(clsCliente c:top_c)
      {
    	  dataset.setValue(c.getdni(), c.getTot_Ventas());
      }
      return dataset;         
   }
   private static PieDataset createDataset( ArrayList<clsCliente> a) {
	      DefaultPieDataset dataset = new DefaultPieDataset( );
	      ArrayList<clsCliente> top_c=a;
	      for(clsCliente c:top_c)
	      {
	    	  dataset.setValue(c.getdni(), c.getTot_Ventas());
	      }
	      return dataset;         
	   }
   private static JFreeChart createChart( PieDataset dataset ) {
      JFreeChart chart = ChartFactory.createPieChart(      
         "Ventas Clientes TOP",   // chart title 
         dataset,          // data    
         true,             // include legend   
         true, 
         false);

      return chart;
   }
   
   public static JPanel createDemoPanel( ArrayList<clsCliente> a) {
	      JFreeChart chart = createChart(createDataset( a) );
	      
	      return new ChartPanel( chart ); 
	   }
   public static JPanel createDemoPanel( ) {
      JFreeChart chart = createChart(createDataset( ) );
      
      return new ChartPanel( chart ); 
   }

}