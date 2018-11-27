import java.awt.EventQueue;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import Comun.clsConstantes;
import LP.clsAltaEolica;
import LP.loginFrame;
import Persistencia.clsBD;


/**
 * Inicio del programa, que llevará a cabo el hilo de ejecución main, del que colgará el resto.  
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Josune Ordoñez (Josune07)
 */
public class clsMain 
{
	
private static final boolean ANYADIR_A_FIC_LOG = true;
	
	/*Logger*/
	private static Logger logger = Logger.getLogger( "Solecillo" );
	static 
	{
		try 
		{
			logger.setLevel( Level.FINEST );
			Formatter f = new SimpleFormatter() 
			{
				@Override
				public synchronized String format(LogRecord record) 
				{
					if (record.getLevel().intValue()<Level.CONFIG.intValue())
						return "\t\t(" + record.getLevel() + ") " + record.getMessage() + "\n";
					if (record.getLevel().intValue()<Level.WARNING.intValue())
						return "\t(" + record.getLevel() + ") " + record.getMessage() + "\n";
					return "(" + record.getLevel() + ") " + record.getMessage() + "\n";
				}
			};
			FileOutputStream fLog = new FileOutputStream( "Solecillo"+".log" , ANYADIR_A_FIC_LOG );
			Handler h = new StreamHandler( fLog, f );
			h.setLevel( Level.FINEST );
			logger.addHandler( h );
		} 
		catch (SecurityException | IOException e) 
		{
			logger.log( Level.SEVERE, "No se ha podido crear fichero de log en clase "+ clsAltaEolica.class.getName() );
		}
		logger.log( Level.INFO, "" );
		logger.log( Level.INFO, DateFormat.getDateTimeInstance( DateFormat.LONG, DateFormat.LONG ).format( new Date() ) );
	}
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Connection conec=clsBD.initBD("Solecillo.bd");
					clsBD.crearTablaBD(clsConstantes.USUARIO);
					clsBD.crearTablaBD(clsConstantes.CLIENTE);
					clsBD.crearTablaBD(clsConstantes.MAQUINA);
					clsBD.crearTablaBD(clsConstantes.VENTA);
					loginFrame p=new loginFrame();
					logger.log( Level.INFO, "Entrando en la aplicación desde el main");
					//p.textField.setText("admin");
					//p.passwordField.setText("admin");
					//p.chckbxAdministrador.setSelected(true);
					//p.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
}