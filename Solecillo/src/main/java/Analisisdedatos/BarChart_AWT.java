package Analisisdedatos;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

public class BarChart_AWT extends ApplicationFrame {
   
   public BarChart_AWT( String applicationTitle , String chartTitle ) {
      super( applicationTitle );        
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "Category",            
         "Score",            
         createDataset(),          
         PlotOrientation.VERTICAL,           
         true, true, false);
         
      ChartPanel chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 800 , 500 ) );        
      setContentPane( chartPanel ); 
   }
   
   private CategoryDataset createDataset( ) {
      final String eol = "Eolicas";        
      final String hid = "Hidraulicas";        
      final String sol = "Solares";        
      final String ener = "Energia";        
      final String seg = "Seguridad";        
      final String opin = "Opinion";                
      final DefaultCategoryDataset dataset = 
      new DefaultCategoryDataset( );  

      
      dataset.addValue( 1.0 , eol , ener);        
      dataset.addValue( 3.0 , eol , seg );        
      dataset.addValue( 5.0 , eol , opin);            

      dataset.addValue( 5.0 , hid , ener );        
      dataset.addValue( 6.0 , hid , seg );       
      dataset.addValue( 10.0 , hid , opin );        

      dataset.addValue( 4.0 , sol , ener );        
      dataset.addValue( 2.0 , sol , seg );        
      dataset.addValue( 3.0 , sol , opin );        
;               

      return dataset; 
   }
   
   public static void main( String[ ] args ) {
      BarChart_AWT chart = new BarChart_AWT("Estadísticas máquinas", 
         "Cual es la mejor?");
      chart.pack( );        
      RefineryUtilities.centerFrameOnScreen( chart );        
      chart.setVisible( true ); 
   }
}
