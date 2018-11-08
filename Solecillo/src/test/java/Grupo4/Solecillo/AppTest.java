package Grupo4.Solecillo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;

import org.omg.CORBA.portable.InputStream;

import Analisisdedatos.PieChart_AWT;
import Analisisdedatos.ScatterPlotExample;
import Comun.clsConstantes;
import LN.clsGestor;
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
		
		System.out.println("Holi");
		clsAltaUsuario a=new clsAltaUsuario();
		
		clsAltaEolica a1=new clsAltaEolica();
		
		clsAltaHidraulica a2=new clsAltaHidraulica();
		
		clsAltaMareomotriz a3=new clsAltaMareomotriz();
		
		clsAltaSolar a4=new clsAltaSolar();
		
		 
		 clsGestor ge=new clsGestor();
		 ge.ListaClientes();
		 ge.ListaEolica();
		 ge.ListaMareomotriz();
		 ge.ListaSolar();
		 ge.ObtenerEolica(1);
		 ge.ObtenerHidraulica(1);
		 ge.ObtenerMareomotriz(1);
		 ge.ObtenerSolar(1);
		
		
    }
}
