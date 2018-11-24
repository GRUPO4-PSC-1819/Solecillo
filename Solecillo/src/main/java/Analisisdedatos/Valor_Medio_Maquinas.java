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
import LN.clsMaquina; 

/**
 * Clase para crear un gráfico de barras  en el que se analizan el valor medio de las máquinas según el tipo de máquina.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Josune Ordoñez (Josune07)
 */
public class Valor_Medio_Maquinas extends JFrame {
   
	private static final long serialVersionUID = 1L;

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
      final DefaultCategoryDataset dataset=new DefaultCategoryDataset( );  
      for(int i=0;i<lm.size();i++)
      {
    	  dataset.setValue(lm.get(i).getValor(), lm.get(i).getTipo(), "");
      }
      return dataset; 
   }
}