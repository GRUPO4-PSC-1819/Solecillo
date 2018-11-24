package Analisisdedatos;

import java.util.ArrayList;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import LN.clsGestor;
import LN.clsMaquina_Eolica;

/**
 * Clase para crear un gráfico de barras en las que se analizan las máquinas eólicas. Se hace referencia al pueblo y a la altura y diamtero medios. 
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Josune Ordoñez (Josune07)
 */
public class Pueblo_Altura_Diametro extends JFrame {
   
	private static final long serialVersionUID = 1L;

public Pueblo_Altura_Diametro( String applicationTitle , String chartTitle ) {
      super( applicationTitle );  
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "Media / Pueblo",            
         "Metros",            
         createDataset(),          
         PlotOrientation.VERTICAL,           
         true, true, false);
         
      ChartPanel chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 800 , 500 ) );        
       setContentPane( chartPanel ); 
   }
   
   private CategoryDataset createDataset( ) {
	  
	  clsGestor ges=new clsGestor(); 
	  ArrayList<clsMaquina_Eolica> lm=new ArrayList<clsMaquina_Eolica>(); 
	  lm=ges.eolicas_pueblo_media_altura_diametro();
      final DefaultCategoryDataset dataset=new DefaultCategoryDataset( );  
      for(int i=0;i<lm.size();i++)
      {
    	  dataset.setValue(lm.get(i).getAltura(), "Media Altura", lm.get(i).getNombre_pueblo());
    	  dataset.setValue(lm.get(i).getDiametro(), "Media Diametro", lm.get(i).getNombre_pueblo());
      }
	  return dataset; 
   }
}