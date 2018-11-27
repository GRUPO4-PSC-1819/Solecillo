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
 * Clase creada para generar un objeto nuevo (clsUsuarios). Implementa la interfaz Comparable con clsCliente y Serializable. <br>
 * La ordenación natural se ha hecho mediante los nickname.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Josune Ordoñez (Josune07)
 */

public class clsUsuario implements Serializable, Comparable<clsUsuario> 
{
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String nickname;
	private String contraseña;
	
	
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
	public clsUsuario(String n, String ap1, String ap2, String nick, String cont)
	{
		nombre=n;
		apellido1=ap1;
		apellido2=ap2;
		nickname=nick;
		contraseña=cont;
		logger.log( Level.INFO, "Constructor usuario con todos los parámetros");
	}
		
	/**
	 * Constructor vacío.
	 */
	public clsUsuario()
	{
		nombre=null;
		apellido1=null;
		apellido2=null;
		nickname=null;
		contraseña=null;
		logger.log( Level.INFO, "Constructor usuario vacío");
	}
	
	/**
	 * Implementación de hashCode() para evitar crear colisiones entre usuarios.
	 */
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nickname == null) ? 0 : nickname.hashCode());
		return result;
	}
	
	/**
	 * Implementación del método equals() para determinar el atributo distintivo (nickname) de un objeto clsUsuario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		clsUsuario other = (clsUsuario) obj;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		return true;
	}

	/**
	 * Reimplementación del método toString.
	 */
	public String toString()
	{
		String e = "Nombre: "+this.getNombre()+" - Apellido 1: "+this.getApellido1()+" - Apellido 2: "+this.getApellido2()+
				" - Nickname: "+this.getNickname();
		return e;
	}
	
	/**
	 * Ordenación natural hecha mediante nickname de usuarios.
	 */
	public int compareTo(clsUsuario arg0) 
	{
		return this.getNickname().compareTo(arg0.getNickname());
	}

	public String getNombre() 
	{
		return nombre;
	}
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	public String getApellido1()
	{
		return apellido1;
	}
	public void setApellido1(String apellido1) 
	{
		this.apellido1 = apellido1;
	}
	public String getApellido2() 
	{
		return apellido2;
	}
	public void setApellido2(String apellido2) 
	{
		this.apellido2 = apellido2;
	}
	public String getNickname() 
	{
		return nickname;
	}
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
	public String getContraseña()
	{
		return contraseña;
	}
	public void setContraseña(String contraseña)
	{
		this.contraseña = contraseña;
	}
}