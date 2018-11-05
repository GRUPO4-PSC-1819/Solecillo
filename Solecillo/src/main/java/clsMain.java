import java.awt.EventQueue;
import java.sql.Connection;
import java.util.ArrayList;

import Comun.clsConstantes;
import LN.clsCliente;
import LN.clsGestor;
import LN.clsMaquina_Eolica;
import LN.clsUsuario;
import LN.clsVenta;
import LP.loginFrame;
import LP.principalFrame;
import Persistencia.clsBD;

/**
 * Inicio del programa, que llevará a cabo el hilo de ejecución main, del que colgará el resto.  
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */
public class clsMain 
{
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Connection conec=clsBD.initBD("Data/Solecillo.bd");
					clsBD.crearTablaBD(clsConstantes.USUARIO);
					clsBD.crearTablaBD(clsConstantes.CLIENTE);
					clsBD.crearTablaBD(clsConstantes.MAQUINA);
					clsBD.crearTablaBD(clsConstantes.VENTA);
					//clsBD.insertarDatoTablaBD(new clsMaquina_Eolica(1,"a","a",1,"a","a","a",1,1));
					//clsBD.insertarDatoTablaBD(new clsMaquina_Eolica(2,"b","b",2,"b","b","b",2,2));
					//clsBD.insertarDatoTablaBD(new clsMaquina_Eolica(3,"c","c",3,"c","c","c",3,3));
					//clsBD.insertarDatoTablaBD(new clsCliente("b","b","a","a","a"));
					//clsBD.insertarDatoTablaBD(new clsCliente("c","b","a","a","a"));
					clsUsuario u=new clsUsuario();
					principalFrame p=new principalFrame(u);
					//loginFrame p=new loginFrame();
					p.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
}
