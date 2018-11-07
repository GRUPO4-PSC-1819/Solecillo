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
 
public class PieChart_AWT extends JFrame {
   
   public PieChart_AWT( String title ) {
	  
      super( title ); 
      this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      setContentPane(createDemoPanel( ));
      	
   }
   
   private static PieDataset createDataset( ) {
      DefaultPieDataset dataset = new DefaultPieDataset( );
      clsGestor gestor=new clsGestor();
      ArrayList<clsVenta> Ventas = gestor.ListaVentas();
      int total=0;
      ArrayList<String> clientes=new ArrayList<String>();
      for(clsVenta venta: Ventas)
      {
    	  total=venta.getCantidad()+total;
    	  if(!clientes.contains(venta.getDniC()))
    	  {
    		  clientes.add(venta.getDniC());
    	  }
      }
      int hola[]= new int[clientes.size()];
      
      for(clsVenta venta: Ventas)
      {
    	  hola[clientes.indexOf(venta.getDniC())]+=venta.getCantidad();
      }
      System.out.println((double)hola[0]/total);
      for(int i=0;i<hola.length;i++)
      {
    	  dataset.setValue( clientes.get(i), new Double( (100*hola[i])/total) );  
      }
      return dataset;         
   }
   
   private static JFreeChart createChart( PieDataset dataset ) {
      JFreeChart chart = ChartFactory.createPieChart(      
         "Clientes",   // chart title 
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