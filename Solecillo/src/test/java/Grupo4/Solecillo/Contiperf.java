package Grupo4.Solecillo;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;

public class Contiperf {


	@Rule
	public ContiPerfRule i = new ContiPerfRule();
	
	@Test
	@PerfTest(invocations = 1000, threads = 20, duration=2000)   //PerfTest convierte un JUnit en una prueba Contiperf. A definir nÃºmero de iteraciones y los hilos que se disponen
	@Required(max = 250, average = 250, median=200) 
	public void testLN() throws InterruptedException
    {
		Thread.sleep(200);
    }
	@Test
	@PerfTest(invocations = 1000, threads = 20, duration=2000)   //PerfTest convierte un JUnit en una prueba Contiperf. A definir nÃºmero de iteraciones y los hilos que se disponen
	@Required(max = 250, average = 250, median=200) 
	public void testBD() throws InterruptedException
       {
		Thread.sleep(200);
       }
	@Test
	@PerfTest(invocations = 1000, threads = 20, duration=2000)   //PerfTest convierte un JUnit en una prueba Contiperf. A definir nÃºmero de iteraciones y los hilos que se disponen
	@Required(max = 250, average = 250, median=200) 
	public void testGraficos() throws InterruptedException
    {
		Thread.sleep(200);
    }
    
	@Test
	@PerfTest(invocations = 1000, threads = 20, duration=2000)   //PerfTest convierte un JUnit en una prueba Contiperf. A definir nÃºmero de iteraciones y los hilos que se disponen
	@Required(max = 250, average = 250, median=200) 
    public void testAlta() throws InterruptedException
    {
    	Thread.sleep(200);
    }
    
   /* public void testVenta()
    {
    	clsListaM frame1 = new clsListaM("Lista de máquinas", clsConstantes.VENTA, "123456789");
		
		frame1.rdbtnEolica.setSelected(true);
		frame1.textField.setText("10");
		frame1.te.setRowSelectionInterval(0, 0);
		frame1.btnSalir.doClick();
		
		frame1.rdbtnHidraulica.setSelected(true);
		frame1.textField.setText("18");
		frame1.th.setRowSelectionInterval(0, 0);
		frame1.btnSalir.doClick();
		
		frame1.rdbtnMareomotriz.setSelected(true);
		frame1.textField.setText("3");
		frame1.tm.setRowSelectionInterval(0, 0);
		frame1.btnSalir.doClick();
		
		frame1.rdbtnSolar.setSelected(true);
		frame1.textField.setText("5");
		frame1.ts.setRowSelectionInterval(0, 0);
		frame1.btnSalir.doClick();
    }*/



}
