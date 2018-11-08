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
import LN.clsVenta;
 
public class PieChart_PROD extends JFrame {
   
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public PieChart_PROD( String title ) {
	  
      super( title ); 
      this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      setContentPane(createDemoPanel( ));
      	
   }
   
   private static PieDataset createDataset( ) {
      DefaultPieDataset dataset = new DefaultPieDataset( );
      clsGestor gestor=new clsGestor();
      ArrayList<clsVenta> Ventas = gestor.ListaVentas();
      int total=0;
      ArrayList<Integer> productos=new ArrayList<Integer>();
      for(clsVenta venta: Ventas)
      {
    	  total=venta.getCantidad()+total;
    	  if(!productos.contains(venta.getIdm()))
    	  {
    		  
    		  productos.add(venta.getIdm());
    	  }
      }
      int hola[]= new int[productos.size()];
      
      for(clsVenta venta: Ventas)
      {
    	  hola[productos.indexOf(venta.getIdm())]+=venta.getCantidad();
      }
      for(int i=0;i<hola.length;i++)
      {
    	  dataset.setValue( productos.get(i), new Double( (100*hola[i])/total) );  
      }
      return dataset;         
   }
   
   private static JFreeChart createChart( PieDataset dataset ) {
      JFreeChart chart = ChartFactory.createPieChart(      
         "Productos",   // chart title 
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