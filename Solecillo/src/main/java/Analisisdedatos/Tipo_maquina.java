package Analisisdedatos;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

public class Tipo_maquina extends ApplicationFrame {
   
   public Tipo_maquina( String applicationTitle , String chartTitle ) {
      super( applicationTitle );        
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "Tipos de máquina",            
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
      final String mar = "Mareomotrices";   
      final String sol = "Solares";        
      final String ener = "Energia";        
      final String seg = "Seguridad";        
      final String opin = "Opinion";                
      final DefaultCategoryDataset dataset = 
      new DefaultCategoryDataset( );  

      
      dataset.addValue( 1.438 , eol , ener);        
      dataset.addValue( 3.48 , eol , seg );        
      dataset.addValue( 5.028 , eol , opin);            

      dataset.addValue( 5.843 , hid , ener );        
      dataset.addValue( 6.56 , hid , seg );       
      dataset.addValue( 7.43 , hid , opin );
      
      dataset.addValue( 6.7 , mar , ener );        
      dataset.addValue( 3.67 , mar , seg );       
      dataset.addValue( 8.2 , mar , opin );  

      dataset.addValue( 4.5 , sol , ener );        
      dataset.addValue( 2.87 , sol , seg );        
      dataset.addValue( 3.35 , sol , opin );        
;               

      return dataset; 
   }
   
   public static void main( String[ ] args ) {
      Tipo_maquina chart = new Tipo_maquina("Estadísticas máquinas", 
         "¿Cuál es la mejor?");
      chart.pack( );        
      RefineryUtilities.centerFrameOnScreen( chart );        
      chart.setVisible( true ); 
   }
}
