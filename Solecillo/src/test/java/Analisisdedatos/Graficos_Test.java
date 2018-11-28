package Analisisdedatos;

import static org.junit.Assert.*;

import org.junit.Test;

public class Graficos_Test {

	@Test
	public void test() {
		Pueblo_Altura_Diametro p=new Pueblo_Altura_Diametro("", "");
		p.setVisible(true);
		Rio_Fabricante_Maquina pq=new Rio_Fabricante_Maquina("", "");
		pq.setVisible(true);
		ScatterPlotExample example = new ScatterPlotExample("Scatter Chart Example | BORAJI.COM");
		example.setVisible(true);
		top_clientes jn=new top_clientes("");
		jn.setVisible(true);
		Valor_Estado vg=new Valor_Estado("");
		vg.setVisible(true);
		Valor_Medio_Maquinas t =new Valor_Medio_Maquinas("", "");
		t.setVisible(true);
		Ventas_Maquina fve=new Ventas_Maquina("");
		fve.setVisible(true);
	}

}
