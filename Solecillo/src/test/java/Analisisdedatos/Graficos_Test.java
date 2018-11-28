package Analisisdedatos;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.jfree.ui.RefineryUtilities;
import org.junit.Before;
import org.junit.Test;

import Comun.clsConstantes;
import LN.clsClienteRepetido;
import LN.clsGestor;
import LN.clsUsuarioRepetido;
import Persistencia.clsBD;

public class Graficos_Test {

	@Before public void setUp() {
		Connection conec=clsBD.initBD("Solecillo1.bd");
		clsBD.crearTablaBD(clsConstantes.USUARIO);
		clsBD.crearTablaBD(clsConstantes.CLIENTE);
		clsBD.crearTablaBD(clsConstantes.MAQUINA);
		clsBD.crearTablaBD(clsConstantes.VENTA);
		
		 clsGestor ge=new clsGestor();
		try {
		ge.MigracionUsuarios();
	} catch (clsUsuarioRepetido e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 try {
		ge.MigracionClientes();
	} catch (clsClienteRepetido e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 ge.MigracionMaquinas();
	 ge.MigracionVentas();
	}

	@Test
	public void test() {
		Pueblo_Altura_Diametro chart = new Pueblo_Altura_Diametro("Medias eólicas / Pueblo", 
		         "¿Cuál es la altura y diametro medios?");
		      chart.pack( );        
		      RefineryUtilities.centerFrameOnScreen( chart );        
		      chart.setVisible( true );	
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Rio_Fabricante_Maquina chart1 = new Rio_Fabricante_Maquina("Fabricantes por río", 
		         "¿Cuántas máquinas son de cada fabricante?");
		      chart1.pack( );        
		      RefineryUtilities.centerFrameOnScreen( chart1 );        
		      chart1.setVisible( true );
		      
		      try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
		      
		      ScatterPlotExample example = new ScatterPlotExample("Scatter Chart Example | BORAJI.COM");
		      example.setSize(800, 400);
		      example.setLocationRelativeTo(null);
		      example.setVisible(true);
		     
		      try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		      
		      top_clientes demo = new top_clientes( "Ventas" );  
		      demo.setSize( 560 , 367 );    
		      RefineryUtilities.centerFrameOnScreen( demo );   
		      demo.pack();
		      demo.setVisible( true );
		      
		      try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		      
		      Valor_Estado demo4 = new Valor_Estado( "Ventas" );  
		      demo4.setSize( 560 , 367 );    
		      RefineryUtilities.centerFrameOnScreen( demo4 );   
		      demo4.pack();
		      demo4.setVisible( true );
     		    
     		     try {
     				Thread.sleep(1500);
     			} catch (InterruptedException e) {
     				e.printStackTrace();
     			}
     		      
     		     Valor_Medio_Maquinas chart3 = new Valor_Medio_Maquinas("Valor medio máquinas", 
        		         "¿Cuánto valen?");
        		      chart3.pack( );        
        		      RefineryUtilities.centerFrameOnScreen( chart3 );        
        		      chart3.setVisible( true ); 
        		      
        		      try {
        					Thread.sleep(1500);
        				} catch (InterruptedException e) {
        					e.printStackTrace();
        				}     
        		      
        		      Ventas_Maquina demo1 = new Ventas_Maquina( "Ventas" );  
				      demo1.setSize( 560 , 367 );    
				      RefineryUtilities.centerFrameOnScreen( demo1 );   
				      demo1.pack();
				      demo1.setVisible( true ); 
	}

}
