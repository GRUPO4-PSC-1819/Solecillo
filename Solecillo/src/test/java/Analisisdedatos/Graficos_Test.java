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
		Pueblo_Altura_Diametro p=new Pueblo_Altura_Diametro("", "");
	      p.setSize( 560 , 367 );    
	      RefineryUtilities.centerFrameOnScreen( p );   
	      p.pack();
		p.setVisible(true);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
