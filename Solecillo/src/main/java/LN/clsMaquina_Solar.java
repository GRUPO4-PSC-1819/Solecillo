package LN;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
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
 * Clase creada para generar un objeto nuevo (clsMaquina_Solar).
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Josune Ordoñez (Josune07)
 */
public class clsMaquina_Solar extends clsMaquina implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nombre_pueblo;
	private String nombre_campo;	
	
	
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
	 * Constructor con todos los parámetros
	 */
		public clsMaquina_Solar(int id, String n, String color, double v, String f, String e, String np, String nc)
		{
			super(id, n, color, v, f, e);
			this.nombre_pueblo=np;
			this.nombre_campo=nc;
			//logger.log( Level.INFO, "Constructor máquina solar con todos los parámetros");
		}
		
		/**
		 * Constructor con todos los parámetros, excepto el id
		 */
				public clsMaquina_Solar(String n, String color, double v, String f, String e, String np, String nc)
				{
					super(n, color, v, f, e);
					this.nombre_pueblo=np;
					this.nombre_campo=nc;
					//logger.log( Level.INFO, "Constructor máquina solar con todos los parámetros excepto el id");
				}
		
				/**
				 * Constructor vacío
				 */
		public clsMaquina_Solar()
		{
			super();
			this.nombre_pueblo=null;
			this.nombre_campo=null;
			//logger.log( Level.INFO, "Constructor máquina solar vacío");
		}
		
		
		public String getNombre_pueblo() {
			return nombre_pueblo;
		}

		public void setNombre_pueblo(String nombre_pueblo) {
			this.nombre_pueblo = nombre_pueblo;
		}

		public String getNombre_campo() {
			return nombre_campo;
		}

		public void setNombre_campo(String nombre_campo) {
			this.nombre_campo = nombre_campo;
		}	
}
