package Persistencia;

import static org.junit.Assert.*;

import org.junit.Test;

import LN.clsCliente;
import LN.clsMaquina_Eolica;
import LN.clsMaquina_Hidraulica;
import LN.clsMaquina_Mareomotriz;
import LN.clsMaquina_Solar;
import LN.clsUsuario;

public class BD_Test {

	@Test
	public void test() {
		clsBD.crearTablaBD("USUARIO");
		clsBD.crearTablaBD("MAQUINA");
		clsBD.crearTablaBD("CLIENTE");
		clsBD.crearTablaBD("VENTA");
		
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
		
		clsBD.obtenerDatosTablaBD("USUARIO");
		clsBD.obtenerDatosTablaBD("MAQUINA_E");
		clsBD.obtenerDatosTablaBD("MAQUINA_H");
		clsBD.obtenerDatosTablaBD("MAQUINA_M");
		clsBD.obtenerDatosTablaBD("MAQUINA_S");
		clsBD.obtenerDatosTablaBD("CLIENTE");
		clsBD.obtenerDatosTablaBD("VENTA");
		
		
		clsBD.ObtenerUnaMaquina(12);
		
		
		clsBD.clientesTOP();
		clsBD.valor_medio_maquinas();
		clsBD.ventas_tipo_maquina();
		clsBD.valor_estado_maquina();
		clsBD.num_maquinas_rio_fabricante();
		clsBD.eolicas_pueblo_media_altura_diametro();
		
		clsUsuario u=new clsUsuario();
		clsBD.modificarUsuario(u);
		
		
		clsBD.BorrarUsuarios();
		clsBD.BorrarClientes();
		clsBD.BorrarMaquinas();
		clsBD.BorrarVentas();
		clsBD.BorrarMaquina(24);
		
		
	}

}
