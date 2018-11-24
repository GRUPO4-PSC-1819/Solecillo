package Mockito;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import LN.IGestor;
import LN.clsCliente;
import LN.clsGestor;
import LN.clsMaquina;


public class MockTest {

	clsGestor ges;
	ArrayList<clsMaquina> listado;
	clsCliente mockedcliente;
	@Before
	public void setup(){
		
		listado=new ArrayList<clsMaquina>();
		listado.add(new clsMaquina("a","a",3,"a","a"));
		listado.add(new clsMaquina("b","b",2,"b","b"));
		IGestor gestor = Mockito.mock(IGestor.class);
		Mockito.when(gestor.valor_medio_maquinas2(listado)).thenReturn((double) 4);
		ges= new clsGestor();
		ges.setCal(gestor);
		mockedcliente= Mockito.mock(clsCliente.class);
		Mockito.when(mockedcliente.getApellido1()).thenReturn("Hola");
		
		
	}
	
	
	@Test
	public void test() {
		Assert.assertEquals((double)4, ges.media(listado),0.1);
		Assert.assertEquals("Hola", mockedcliente.getApellido1());
	}

}
