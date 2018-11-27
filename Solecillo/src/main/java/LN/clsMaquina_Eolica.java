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
 * Clase creada para generar un objeto nuevo (clsMaquina_Eolica).
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Josune Ordoñez (Josune07)
 */
public class clsMaquina_Eolica extends clsMaquina implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nombre_pueblo;
	private String nombre_campo;
	private double altura;
	private double diametro;


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
	public clsMaquina_Eolica(int id, String n, String color, double v, String f, String e, String np, String nc, double a, double d)
		{
			super(id, n, color, v, f, e);
			this.nombre_pueblo=np;
			this.nombre_campo=nc;
			this.altura=a;
			this.diametro=d;
		    logger.log( Level.INFO, "Constructor máquina eólica con todos los parámetros");
		}
		
	/**
	 * Constructor con todos los parámetros, excepto el id
	 */
	public clsMaquina_Eolica(String n, String color, double v, String f, String e, String np, String nc, double a, double d)
				{
					super(n, color, v, f, e);
					this.nombre_pueblo=np;
					this.nombre_campo=nc;
					this.altura=a;
					this.diametro=d;
					logger.log( Level.INFO, "Constructor máquina eólica con todos los parámetros menos id");
				}
	
	/**
	 * Constructor específico para gráficos
	 */
	public clsMaquina_Eolica(String np, double a, double d)
				{
					this.nombre_pueblo=np;
					this.altura=a;
					this.diametro=d;
					logger.log( Level.INFO, "Constructor máquina eólica con nombre pueblo, altura y diametro");
				}
		
	/**
	 * Constructor vacío
	 */
		public clsMaquina_Eolica()
		{
			super();
			this.nombre_pueblo=null;
			this.nombre_campo=null;
			this.altura=0.0;
			this.diametro=0.0;
			logger.log( Level.INFO, "Constructor máquina eólica vacío");
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
				
		public double getAltura() {
			return altura;
		}

		public void setAltura(double altura) {
			this.altura = altura;
		}

		public double getDiametro() {
			return diametro;
		}

		public void setDiametro(double diametro) {
			this.diametro = diametro;
		}
}
