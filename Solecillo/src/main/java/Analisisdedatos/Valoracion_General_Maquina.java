package Analisisdedatos;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 

public class Valoracion_General_Maquina extends JFrame {

	private static final long serialVersionUID = 1L;

public Valoracion_General_Maquina( String applicationTitle , String chartTitle ) {
      super( applicationTitle );     
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "Tipos de máquina",            
         "Valoración",            
         createDataset(),          
         PlotOrientation.VERTICAL,           
         true, true, false);
         
      ChartPanel chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 800 , 500 ) );        
      setContentPane( chartPanel ); 
   }
   
   private CategoryDataset createDataset( ) {
      final String eol = "Eólicas";        
      final String hid = "Hidráulicas";  
      final String mar = "Mareomotrices";   
      final String sol = "Solares";        
      final String ener = "Energía";        
      final String seg = "Seguridad";        
      final String opin = "Opinión";                
      final DefaultCategoryDataset dataset=new DefaultCategoryDataset( );  

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

      return dataset; 
   }
}