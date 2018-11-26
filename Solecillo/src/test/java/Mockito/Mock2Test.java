package Mockito;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.jfree.ui.RefineryUtilities;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import Analisisdedatos.top_clientes;
import LN.clsCliente;
import LN.clsGestor;

public class Mock2Test {

	clsGestor ges;
	@Before
	public void setup(){
		 ges=Mockito.mock(clsGestor.class);
		ArrayList<clsCliente> a=new ArrayList<clsCliente>();
		a.add(new clsCliente("a",50));
		a.add(new clsCliente("b",40));
		a.add(new clsCliente("c",30));
		a.add(new clsCliente("d",20));
		a.add(new clsCliente("f",10));
		Mockito.when(ges.top_5_clientes()).thenReturn(a);
	}
	
	@Test
	public void test() {
		top_clientes demo = new top_clientes( "Ventas" ,ges.top_5_clientes());  
	      demo.setSize( 560 , 367 );    
	      RefineryUtilities.centerFrameOnScreen( demo );   
	      demo.pack();
	      demo.setVisible( true );
	      try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
