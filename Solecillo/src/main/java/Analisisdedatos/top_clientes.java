package Analisisdedatos;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import LN.clsCliente;
import LN.clsGestor;

/**
 * Clase para crear un gráfico circular en el que se analizan los 5 clientes que más máquinas han comprado. 
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Josune Ordoñez (Josune07)
 */
public class top_clientes extends JFrame {
   
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