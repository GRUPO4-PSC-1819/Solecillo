package Analisisdedatos;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import LN.clsGestor;
import LN.clsMaquina;
import LN.clsVenta;
 
public class Valor_Estado extends JFrame {
   
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public Valor_Estado( String title ) {
	  
      super( title ); 
      this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      setContentPane(createDemoPanel( ));
      	
   }
   
   private static PieDataset createDataset( ) {
      DefaultPieDataset dataset = new DefaultPieDataset( );
      clsGestor gestor=new clsGestor();
      ArrayList<clsMaquina>lm=gestor.valor_estado_maquina();
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