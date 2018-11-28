package Analisisdedatos;

import static org.junit.Assert.*;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;

public class Contiperf_Graficos {

	@Rule
	public ContiPerfRule i = new ContiPerfRule();
	
	@Test
	@PerfTest(invocations = 1000, threads = 20, duration=2000)   //PerfTest convierte un JUnit en una prueba Contiperf. A definir nÃºmero de iteraciones y los hilos que se disponen
	@Required(max = 300, average = 300, median=500)  //Required define las requisitos de rendimiento, si no los cumple --> rojo
	public void test() throws Exception{
		//Para que le de tiempo a ejecutar todas las invocations antes de hacer el anÃ¡lisis con contiperf
		Thread.sleep(200);
	}

}
