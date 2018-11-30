package Mockito;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.jfree.ui.RefineryUtilities;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import Analisisdedatos.top_clientes;
import LN.IGestor;
import LN.clsCliente;
import LN.clsGestor;

public class Mock2Test {

	clsGestor ges;
	@Before
	public void setup(){
		ArrayList<clsCliente> a=new ArrayList<clsCliente>();
		a.add(new clsCliente("a",50));
		a.add(new clsCliente("b",40));
		a.add(new clsCliente("c",30));
		a.add(new clsCliente("d",20));
		a.add(new clsCliente("f",10));
		IGestor gestor = Mockito.mock(IGestor.class);
		Mockito.when(gestor.top5()).thenReturn(a);
		ges=new clsGestor();
		ges.setI(gestor);
	}
	
	@Test
	public void test() {
		top_clientes demo = new top_clientes( "Ventas" ,ges.top5_clientes());  
	      demo.setSize( 560 , 367 );    
	      RefineryUtilities.centerFrameOnScreen( demo );   
	      demo.pack();
	      demo.setVisible( true );
	      try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
