import java.awt.EventQueue;
import java.sql.Connection;

import Comun.clsConstantes;
import LN.clsCliente;
import LN.clsUsuario;
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
					
					clsBD.insertarDatoTablaBD(new clsCliente("a","a","a","a","a"));
					clsBD.insertarDatoTablaBD(new clsCliente("b","b","a","a","a"));
					clsBD.insertarDatoTablaBD(new clsCliente("c","b","a","a","a"));
					//clsUsuario u=new clsUsuario();
					//principalFrame p=new principalFrame(u);
					loginFrame p=new loginFrame();
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
