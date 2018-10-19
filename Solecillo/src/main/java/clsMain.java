import java.awt.EventQueue;
import java.sql.Connection;

import Comun.clsConstantes;
import LP.loginFrame;
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