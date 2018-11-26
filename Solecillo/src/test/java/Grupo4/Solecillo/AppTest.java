package Grupo4.Solecillo;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.ArrayList;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.omg.CORBA.portable.InputStream;
import Analisisdedatos.ScatterPlotExample;
import Comun.clsConstantes;
import LN.clsCliente;
import LN.clsGestor;
import LN.clsMaquina_Eolica;
import LN.clsMaquina_Hidraulica;
import LN.clsMaquina_Mareomotriz;
import LN.clsMaquina_Solar;
import LN.clsUsuario;
import LP.clsAltaEolica;
import LP.clsAltaHidraulica;
import LP.clsAltaMareomotriz;
import LP.clsAltaSolar;
import LP.clsAltaUsuario;
import LP.clsListaM;
import LP.loginFrame;
import LP.principalFrame;
import Persistencia.clsBD;
import java_cup.Main;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /*
	@Test public void testLN()
    {
    	
    	Connection conec=clsBD.initBD("Data/Solecillo.bd");
		clsBD.crearTablaBD(clsConstantes.USUARIO);
		clsBD.crearTablaBD(clsConstantes.CLIENTE);
		clsBD.crearTablaBD(clsConstantes.MAQUINA);
		clsBD.crearTablaBD(clsConstantes.VENTA);
		
		loginFrame p=new loginFrame();
		
		principalFrame window = new principalFrame(null);
		
		//window.btnRefrescar.doClick();
		
		
		//VENTAS
		clsListaM frame = new clsListaM("Lista de máquinas", clsConstantes.VISUALIZAR,null);
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
    }
    */
    @Test public void testAlta()
    {
		clsAltaUsuario u=new clsAltaUsuario();
		u.txtNombre.setText("n1");
		u.txtApe1.setText("ap11");
		u.txtApe2.setText("ap21");
		u.txtNickname.setText("nick1");
		u.txtContrasenya1.setText("c1");
		u.txtContrasenya2.setText("c1");
		//u.btnAceptar.doClick();

		clsAltaEolica e=new clsAltaEolica();
		e.txtNombre.setText("n1");
		e.txtColor.setText("c1");
		e.txtValor.setText("12.98");
		e.txtFabricante.setText("f1");
		e.txtNombrePueblo.setText("np1");
		e.txtNombreCampo.setText("nc1");
		e.txtAltura.setText("124.62");
		e.txtDiametro.setText("39.04");
	   // e.btnAceptar.doClick();

		clsAltaHidraulica h=new clsAltaHidraulica();
		h.txtNombre.setText("n2");
		h.txtColor.setText("c2");
		h.txtValor.setText("27.73");
		h.txtFabricante.setText("f2");
		h.txtNombrePueblo.setText("np2");
		h.txtNombreRio.setText("nr2");
	   // h.btnAceptar.doClick();	    

		clsAltaMareomotriz m=new clsAltaMareomotriz();
		m.txtNombre.setText("n3");
		m.txtColor.setText("c3");
		m.txtValor.setText("43.4");
		m.txtFabricante.setText("f3");
		m.txtNombrePueblo.setText("np3");
		m.txtDistancia.setText("9.72");
	    //m.btnAceptar.doClick();	    
	    
		clsAltaSolar s=new clsAltaSolar();
		s.txtNombre.setText("n4");
		s.txtColor.setText("c4");
		s.txtValor.setText("0.622");
		s.txtFabricante.setText("f4");
		s.txtNombrePueblo.setText("np4");
		s.txtNombreCampo.setText("nc4");
	    //s.btnAceptar.doClick();
    }
}
