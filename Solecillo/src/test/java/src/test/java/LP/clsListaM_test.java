package src.test.java.LP;

import org.junit.Before;
import org.junit.Test;
import Comun.clsConstantes;
import LN.clsCliente;
import LP.clsListaM;

public class clsListaM_test {
	
	clsCliente c1;
	clsCliente c2;
	clsCliente c3;
	clsCliente c4;

	@Before public void setUp() {
		
		c1=new clsCliente("n1", "ap11", "ap21", "dni1", "e1");
		c2=new clsCliente("n2", "ap12", "ap22", "dni2", "e2");
		c3=new clsCliente("n3", "ap13", "ap23", "dni3", "e3");
		c4=new clsCliente("n4", "ap14", "ap24", "dni4", "e4");
	}
	
	@Test public void testventanainicio() {
		
		clsListaM frame1 = new clsListaM("Lista de máquinas", clsConstantes.VISUALIZAR, c1.getdni());
		
		clsListaM frame2 = new clsListaM("Lista de máquinas", clsConstantes.VENTA, c2.getdni());
		frame2.rdbtnEolica.setSelected(true);
		frame2.textField.setText("10");
		frame2.a=0;
		frame2.btnSalir.doClick();
		
		clsListaM frame3 = new clsListaM("Lista de máquinas", clsConstantes.BORRAR, c3.getdni());
		frame3.rdbtnMareomotriz.setSelected(true);
		frame3.a=4;
		frame3.btnSalir.doClick();
		
		clsListaM frame4 = new clsListaM("Lista de máquinas", clsConstantes.MODIFICAR, c4.getdni());
		frame4.rdbtnSolar.setSelected(true);
		frame4.a=2;
		frame4.btnSalir.doClick();
	}

}
