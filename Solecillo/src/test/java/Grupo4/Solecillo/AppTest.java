package Grupo4.Solecillo;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.ArrayList;

import org.omg.CORBA.portable.InputStream;

import Analisisdedatos.PieChart_AWT;
import Analisisdedatos.ScatterPlotExample;
import Comun.clsConstantes;
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
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    
    
    	
	
    public void testApp()
    {
    	ArrayList<clsUsuario> usus;
		ArrayList<clsMaquina_Eolica> eolicas;
    	ArrayList<clsMaquina_Hidraulica> hidraulicas;
    	ArrayList<clsMaquina_Mareomotriz> mareomotrices;
    	ArrayList<clsMaquina_Solar> solares;
    	clsGestor ges;
    	Connection conec=clsBD.initBD("Data/Solecillo.bd");
		clsBD.crearTablaBD(clsConstantes.USUARIO);
		clsBD.crearTablaBD(clsConstantes.CLIENTE);
		clsBD.crearTablaBD(clsConstantes.MAQUINA);
		clsBD.crearTablaBD(clsConstantes.VENTA);
		
		loginFrame p=new loginFrame();
		
		principalFrame window = new principalFrame(null);
		
		//window.btnRefrescar.doClick();
		
		clsListaM frame = new clsListaM("Lista de máquinas", clsConstantes.VISUALIZAR,null);
		clsListaM frame1 = new clsListaM("Lista de máquinas", clsConstantes.VENTA,null);
		
		frame1.rdbtnEolica.setSelected(true);
		frame1.textField.setText("10");
		frame1.te.setRowSelectionInterval(0, 0);
		frame1.btnSalir.doClick();
		
		ges=new clsGestor();
		
		usus=new ArrayList<clsUsuario>();
	 	usus=ges.ListaUsuarios();
		assertTrue(usus.size()>0);//no hay todavía elementos guardados, tamaño del ArrayList será 0
		clsAltaUsuario u=new clsAltaUsuario();
		u.txtNombre.setText("n1");
		u.txtApe1.setText("ap11");
		u.txtApe2.setText("ap21");
		u.txtNickname.setText("nick1");
		u.txtContrasenya1.setText("c1");
		u.txtContrasenya2.setText("c1");
		u.btnAceptar.doClick();
		
		eolicas=new ArrayList<clsMaquina_Eolica>();
		eolicas=ges.ListaEolica();
		assertTrue(eolicas.size()>0);//hay dos eólicas en bbdd, tamaño del ArrayList será 2
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
	    
	    
	    hidraulicas=new ArrayList<clsMaquina_Hidraulica>();
		hidraulicas=ges.ListaHidraulica();
		assertTrue(hidraulicas.size()>0);//hay dos hidráulicas en bbdd, tamaño del ArrayList será 2
		clsAltaHidraulica h=new clsAltaHidraulica();
		h.txtNombre.setText("n2");
		h.txtColor.setText("c2");
		h.txtValor.setText("27.73");
		h.txtFabricante.setText("f2");
		h.txtNombrePueblo.setText("np2");
		h.txtNombreRio.setText("nr2");
	    h.btnAceptar.doClick();
	    
	    
	    mareomotrices=new ArrayList<clsMaquina_Mareomotriz>();
		mareomotrices=ges.ListaMareomotriz();
		assertTrue(mareomotrices.size()>0);//hay dos mareomotrices en bbdd, tamaño del ArrayList será 2
		clsAltaMareomotriz m=new clsAltaMareomotriz();
		m.txtNombre.setText("n3");
		m.txtColor.setText("c3");
		m.txtValor.setText("43.4");
		m.txtFabricante.setText("f3");
		m.txtNombrePueblo.setText("np3");
		m.txtDistancia.setText("9.72");
	    m.btnAceptar.doClick();
	    
	    
	    solares=new ArrayList<clsMaquina_Solar>();
		solares=ges.ListaSolar();
		assertTrue(solares.size()>0);//hay dos solares en bbdd, tamaño del ArrayList será 2
		clsAltaSolar s=new clsAltaSolar();
		s.txtNombre.setText("n4");
		s.txtColor.setText("c4");
		s.txtValor.setText("0.622");
		s.txtFabricante.setText("f4");
		s.txtNombrePueblo.setText("np4");
		s.txtNombreCampo.setText("nc4");
	    s.btnAceptar.doClick();
		
		PieChart_AWT demo = new PieChart_AWT( "Ventas" );  
		ScatterPlotExample example = new ScatterPlotExample("Scatter Chart Example | BORAJI.COM");
		example.btnAceptar_1.doClick();

    }


}
