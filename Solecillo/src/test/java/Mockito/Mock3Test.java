package Mockito;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import LN.IGestor;
import LN.clsGestor;
import LN.clsMaquina;

public class Mock3Test {
	clsGestor ges;
	ArrayList<clsMaquina> listado;
	@Before
	public void setup(){
		
		
		listado=new ArrayList<clsMaquina>();
		listado.add(new clsMaquina("a","a",3,"a","a"));
		listado.add(new clsMaquina("b","b",2,"b","b"));
		IGestor gestor = Mockito.mock(IGestor.class);
		Mockito.when(gestor.valor_medio_maquinas2(listado)).thenReturn((double) 2.55);
		ges= new clsGestor();
		ges.setI(gestor);
	
	}
	
	
	@Test
	public void test() {
		Assert.assertEquals((double)2.5, ges.media(listado),0.1);
	}
	
}
