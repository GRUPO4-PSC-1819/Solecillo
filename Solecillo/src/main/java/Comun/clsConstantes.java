package Comun;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import LP.clsAltaEolica;

/**
 * Clase que contendrá aquellos métodos y atributos que deban ser accesibles para cualquiera de las clases restantes.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Josune Ordoñez (Josune07)
 */
public class clsConstantes 
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
	/**
	 * Listado de constantes a aplicar en diferentes fases del programa.
	 */
	public static final String USUARIO = "USUARIO";
	public static final String MAQUINA = "MAQUINA";
	public static final String MAQUINA_E = "MAQUINA_E";
	public static final String MAQUINA_H = "MAQUINA_H";
	public static final String MAQUINA_M = "MAQUINA_M";
	public static final String MAQUINA_S = "MAQUINA_S";
	public static final String ADMIN = "ADMIN";
	public static final String PRINCIPAL = "PRINCIPAL";
	public static final String CLIENTE = "CLIENTE";
	public static final String VISUALIZAR = "VISUALIZAR";
	public static final String BORRAR = "BORRAR";
	public static final String MODIFICAR = "MODIFICAR";
	public static final String VENTA = "VENTA";
	public static final String ESTADO1 = "Perfecto";
	public static final String ESTADO2 = "Correcto";
	public static final String ESTADO3 = "Necesita mantenimiento";
	public static final String ESTADO4 = "Peligro inminente";	
}