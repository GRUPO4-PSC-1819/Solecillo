package LN;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import Comun.clsConstantes;
import Persistencia.clsBD;

public class Gestor_Test {

	//Activa Contiperf cuando se lanza JUnit
		@Rule
		public ContiPerfRule i = new ContiPerfRule();
		
	@Before public void setUp() {
		Connection conec=clsBD.initBD("Data/Solecillo.bd");
		clsBD.crearTablaBD(clsConstantes.USUARIO);
		clsBD.crearTablaBD(clsConstantes.CLIENTE);
		clsBD.crearTablaBD(clsConstantes.MAQUINA);
		clsBD.crearTablaBD(clsConstantes.VENTA);
	}
	
	@Test
	public void test() {
		clsGestor ge=new clsGestor();
		 ge.ListaEolica();
		 ge.ListaMareomotriz();
		 ge.ListaSolar();
		 ge.ObtenerEolica(1);
		 ge.ObtenerHidraulica(1);
		 ge.ObtenerMareomotriz(1);
		 ge.ObtenerSolar(1);
	}

}
