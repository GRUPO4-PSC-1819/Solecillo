package src.test.java.LP;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

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

public class clsAltas_test {

	/*clsUsuario u1;
	clsMaquina_Eolica e1;
	clsMaquina_Hidraulica h1;
	clsMaquina_Mareomotriz m1;
	clsMaquina_Solar s1;*/
	
	ArrayList<clsUsuario> usus=new ArrayList<clsUsuario>();
	ArrayList<clsMaquina_Eolica> eolicas=new ArrayList<clsMaquina_Eolica>();
	ArrayList<clsMaquina_Hidraulica> hidraulicas=new ArrayList<clsMaquina_Hidraulica>();
	ArrayList<clsMaquina_Mareomotriz> mareomotrices=new ArrayList<clsMaquina_Mareomotriz>();
	ArrayList<clsMaquina_Solar> solares=new ArrayList<clsMaquina_Solar>();
	
	
	clsGestor ges;

	@Before public void setUp() {
		
		/*u1=new clsUsuario("n1", "ap11", "ap21", "nick1", "c1");
		e1=new clsMaquina_Eolica("n1", "c1", 12.98, "f1", "np1", "nc1", 124.62, 39.04);
		h1=new clsMaquina_Hidraulica("n2", "c2", 27.73, "f2", "np2", "nr2");
		m1=new clsMaquina_Mareomotriz("n3", "c3", 43.4, "f3", "np3", 9.72);
		s1=new clsMaquina_Solar("n4", "c4", 0.622, "f4", "np4", "nc4");*/
		
		ges=new clsGestor();
	}
	
	@Test public void testAltaUsuario() {
		
		usus=ges.ListaUsuarios();
		
		assertEquals(0, usus.size());//no hay todavía elementos guardados, tamaño del ArrayList será 0
		clsAltaUsuario u=new clsAltaUsuario();
		u.txtNombre.setText("n1");
		u.txtApe1.setText("ap11");
		u.txtApe2.setText("ap21");
		u.txtNickname.setText("nick1");
		u.txtContrasenya1.setText("c1");
		u.txtContrasenya2.setText("c1");
		u.btnAceptar.doClick();
		
		//usus=ges.ListaUsuarios();
		//assertEquals(1, usus.size());//hemos guardado un usuario, tamaño del ArrayList será 1
	}
	
@Test public void testAltaEolica() {
		
		eolicas=ges.ListaEolica();
		
		assertEquals(0, eolicas.size());//no hay todavía elementos guardados, tamaño del ArrayList será 0
		clsAltaEolica e=new clsAltaEolica();
		e.txtNombre.setText("n1");
		e.txtColor.setText("c1");
		e.txtValor.setText("12.98");
		e.txtFabricante.setText("f1");
		e.txtNombrePueblo.setText("np1");
		e.txtNombreCampo.setText("nc1");
		e.txtAltura.setText("124.62");
		e.txtDiametro.setText("39.04");
	    e.btnAceptar.doClick();
	}

@Test public void testAltaHidraulica() {
	
	hidraulicas=ges.ListaHidraulica();
	
	assertEquals(0, hidraulicas.size());//no hay todavía elementos guardados, tamaño del ArrayList será 0
	clsAltaHidraulica h=new clsAltaHidraulica();
	h.txtNombre.setText("n2");
	h.txtColor.setText("c2");
	h.txtValor.setText("27.73");
	h.txtFabricante.setText("f2");
	h.txtNombrePueblo.setText("np2");
	h.txtNombreRio.setText("nr2");
    h.btnAceptar.doClick();
}

@Test public void testAltaMareomotriz() {
	
	mareomotrices=ges.ListaMareomotriz();
	
	assertEquals(0, mareomotrices.size());//no hay todavía elementos guardados, tamaño del ArrayList será 0
	clsAltaMareomotriz m=new clsAltaMareomotriz();
	m.txtNombre.setText("n3");
	m.txtColor.setText("c3");
	m.txtValor.setText("43.4");
	m.txtFabricante.setText("f3");
	m.txtNombrePueblo.setText("np3");
	m.txtDistancia.setText("9.72");
    m.btnAceptar.doClick();
}

@Test public void testAltaSolar() {
	
	solares=ges.ListaSolar();
	
	assertEquals(0, solares.size());//no hay todavía elementos guardados, tamaño del ArrayList será 0
	clsAltaSolar s=new clsAltaSolar();
	s.txtNombre.setText("n4");
	s.txtColor.setText("c4");
	s.txtValor.setText("0.622");
	s.txtFabricante.setText("f4");
	s.txtNombrePueblo.setText("np4");
	s.txtNombreCampo.setText("nc4");
    s.btnAceptar.doClick();
}

}
