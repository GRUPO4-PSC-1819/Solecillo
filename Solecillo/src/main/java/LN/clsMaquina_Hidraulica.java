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
 * Clase creada para generar un objeto nuevo (clsMaquina_Hidraulica).
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Josune Ordoñez (Josune07)
 */
public class clsMaquina_Hidraulica extends clsMaquina implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nombre_pueblo;
	private String nombre_rio;
	
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
		public clsMaquina_Hidraulica(int id, String n, String color, double v, String f, String e, String np, String nr)
		{
			super(id, n, color, v, f, e);
			this.nombre_pueblo=np;
			this.nombre_rio=nr;
			//logger.log( Level.INFO, "Constructor máquina hidráulica con todos los parámetros");
		}
		
		/**
		 * Constructor con todos los parámetros, excepto el id
		 */
				public clsMaquina_Hidraulica(String n, String color, double v, String f, String e, String np, String nr)
				{
					super(n, color, v, f, e);
					this.nombre_pueblo=np;
					this.nombre_rio=nr;
					//logger.log( Level.INFO, "Constructor máquina hidráulica con todos los parámetros excepto el id");
				}
				
				/**
				 * Constructor para gráficos
				 */
				public clsMaquina_Hidraulica(String nr, String f, int tot_m)
				{
					super(f, tot_m);
					this.nombre_rio=nr;
					//logger.log( Level.INFO, "Constructor máquina hidráulica con  nombre río, fabricante y total de máquinas");
				}
		
				/**
				 * Constructor vacío
				 */
				public clsMaquina_Hidraulica()
				{
					super();
					this.nombre_pueblo=null;
					this.nombre_rio=null;
					//logger.log( Level.INFO, "Constructor máquina hidráulica vacío");
				}
		
		
		public String getNombre_pueblo() {
			return nombre_pueblo;
		}

		public void setNombre_pueblo(String nombre_pueblo) {
			this.nombre_pueblo = nombre_pueblo;
		}

		public String getNombre_rio() {
			return nombre_rio;
		}

		public void setNombre_rio(String nombre_rio) {
			this.nombre_rio = nombre_rio;
		}	
}
