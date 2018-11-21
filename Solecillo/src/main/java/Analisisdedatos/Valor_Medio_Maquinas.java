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

public class Valor_Medio_Maquinas extends ApplicationFrame {
   
   public Valor_Medio_Maquinas( String applicationTitle , String chartTitle ) {
      super( applicationTitle );  
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "Tipos de máquina",            
         "Valor",            
         createDataset(),          
         PlotOrientation.VERTICAL,           
         true, true, false);
         
      ChartPanel chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 800 , 500 ) );        
       setContentPane( chartPanel ); 
   }
   
   private CategoryDataset createDataset( ) {
	  
	  clsGestor ges=new clsGestor(); 
	  ArrayList<clsMaquina> lm=new ArrayList<clsMaquina>(); 
	  lm=ges.valor_medio_maquinas();
      final String eol = "Eólicas";        
      final String hid = "Hidráulicas";  
      final String mar = "Mareomotrices";   
      final String sol = "Solares";        
      final DefaultCategoryDataset dataset=new DefaultCategoryDataset( );  
      
    
    	  dataset.setValue(lm.get(0).getValor(), lm.get(0).getTipo(), eol);
    	  dataset.setValue(lm.get(1).getValor(), lm.get(1).getTipo(), hid);
    	  dataset.setValue(lm.get(2).getValor(), lm.get(2).getTipo(), mar);
    	  dataset.setValue(lm.get(3).getValor(), lm.get(3).getTipo(), sol);
      
      return dataset; 
   }
}