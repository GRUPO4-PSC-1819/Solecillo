package LN;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import Comun.clsConstantes;
import Persistencia.clsBD;

public class Gestor_Test {

	//Activa Contiperf cuando se lanza JUnit
		@Rule
		public ContiPerfRule i = new ContiPerfRule();
		
	@Before public void setUp() {
		Connection conec=clsBD.initBD("TestGestor.bd");
		clsBD.crearTablaBD(clsConstantes.USUARIO);
		clsBD.crearTablaBD(clsConstantes.CLIENTE);
		clsBD.crearTablaBD(clsConstantes.MAQUINA);
		clsBD.crearTablaBD(clsConstantes.VENTA);
		
		clsMaquina_Eolica e=new clsMaquina_Eolica();
		clsBD.insertarDatoTablaBD(e);
		
		clsMaquina_Hidraulica h=new clsMaquina_Hidraulica();
		clsBD.insertarDatoTablaBD(h);
		
		clsMaquina_Mareomotriz m=new clsMaquina_Mareomotriz();
		clsBD.insertarDatoTablaBD(m);
		
		clsMaquina_Solar s=new clsMaquina_Solar();
		clsBD.insertarDatoTablaBD(s);
		
		clsCliente c=new clsCliente();
		clsBD.insertarDatoTablaBD(c);
		
		clsVenta v=new clsVenta();
		clsBD.insertarDatoTablaBD(v);
		
		clsUsuario u=new clsUsuario();
		clsBD.insertarDatoTablaBD(u);
	}
	
	@Test
	public void test() {
		 clsGestor ge=new clsGestor();
		 ge.ListaUsuarios();
		 ge.ListaClientes();
		 ge.ListaVentas();
		 ge.ListaEolica();
		 ge.ListaHidraulica();
		 ge.ListaMareomotriz();
		 ge.ListaSolar();
		 
		 
try {
	ge.CrearUsuario("n", "ap1", "ap2", "nick", "cont");
} catch (clsUsuarioRepetido e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

try {
	ge.CrearCliente("n", "ap1", "ap2", "dni", "empresa");
} catch (clsClienteRepetido e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

		ge.CrearMaquinaEolica("n", "col", 43.45, "fab", "e", "np", "nc", 43.4, 23.4);
		ge.CrearMaquinaHidraulica("n", "col", 43.3, "fab", "e", "np", "nr");
		ge.CrearMaquinaMareomotriz("n", "col", 33.3, "fab", "e", "np", 564.2);
		ge.CrearMaquinaSolar("n", "col", 43.3, "fab", "e", "np", "nc");
		
		ge.CrearVenta(233, "dniC", 4);
		
		
		ge.ModificarUsuario("n", "ap1", "ap2", "nick", "cont");
		
		ge.BorrarMaquina(23);
		
		ge.BorrarUsuarios();
		ge.BorrarClientes();
		ge.BorrarMaquinas();
		ge.BorrarVentas();
		 
		 ge.ObtenerEolica(1);
		 ge.ObtenerHidraulica(1);
		 ge.ObtenerMareomotriz(1);
		 ge.ObtenerSolar(1);
		 
		 
		 ge.ModificarMaquinaEolica(32, "n", "col", 45.3, "fab", "e", "np", "nc", 43.4, 34.2);		 
		 ge.ModificarMaquinaHidraulica(43, "n", "col", 43.4, "fab", "e", "np", "nr");
		 ge.ModificarMaquinaMareomotriz(41, "n", "col", 45.4, "fab", "e", "np", 434.34);
		 ge.ModificarMaquinaSolar(22, "n", "col", 59.3, "fab", "e", "np", "nc");
		 
		/* try {
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
		 ge.MigracionVentas();*/
	}

}
