package src.test.java.LP;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;

import Comun.clsConstantes;
import LP.loginFrame;
import Persistencia.clsBD;
import junit.framework.TestCase;

public class LoginFrame extends TestCase {

	@Before public void setUp() {
		Connection conec=clsBD.initBD("Solecillo.bd");
		clsBD.crearTablaBD(clsConstantes.USUARIO);
		clsBD.crearTablaBD(clsConstantes.CLIENTE);
		clsBD.crearTablaBD(clsConstantes.MAQUINA);
		clsBD.crearTablaBD(clsConstantes.VENTA);
	}
	
	@Test public void testventanainicio() {
		loginFrame p=new loginFrame();
		p.passwordField.setText("Admin");
		p.chckbxAdministrador.setSelected(true);
		p.textField.setText("Admin");
		p.btnAceptar_1.doClick();
	}
	
}
