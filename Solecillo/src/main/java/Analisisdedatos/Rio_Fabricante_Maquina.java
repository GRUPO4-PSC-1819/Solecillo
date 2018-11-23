package Analisisdedatos;

import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities;

import LN.clsCliente;
import LN.clsGestor;
import LN.clsMaquina;
import LN.clsMaquina_Hidraulica; 

public class Rio_Fabricante_Maquina extends ApplicationFrame {
   
   public Rio_Fabricante_Maquina( String applicationTitle , String chartTitle ) {
      super( applicationTitle );  
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "Fabricantes / Río",            
         "Nº de máquinas",            
         createDataset(),          
         PlotOrientation.VERTICAL,           
         true, true, false);
         
      ChartPanel chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 800 , 500 ) );        
       setContentPane( chartPanel ); 
   }
   
   private CategoryDataset createDataset( ) {
	  
	  clsGestor ges=new clsGestor(); 
	  ArrayList<clsMaquina_Hidraulica> lm=new ArrayList<clsMaquina_Hidraulica>(); 
	  lm=ges.num_maquinas_rio_fabricante();
      final DefaultCategoryDataset dataset=new DefaultCategoryDataset( );  
	  for(clsMaquina_Hidraulica l:lm)
	  {
	  	dataset.setValue(l.getTot_maquinas(), l.getFabricante(), l.getNombre_rio());
	  }      
	  return dataset; 
   }
}