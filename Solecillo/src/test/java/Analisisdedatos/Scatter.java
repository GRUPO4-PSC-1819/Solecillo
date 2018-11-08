package Analisisdedatos;

import static org.junit.Assert.*;

import org.junit.Test;

public class Scatter {

	@Test
	public void test() {
		
		PieChart_AWT demo = new PieChart_AWT( "Ventas" );  
		ScatterPlotExample example = new ScatterPlotExample("Scatter Chart Example | BORAJI.COM");
		example.btnAceptar_1.doClick();
	}

}
