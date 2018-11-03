package LN;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase creada para generar un objeto nuevo (clsUsuario). Implementa la interfaz Comparable con clsUsuario y Serializable. <br>
 * La ordenación natural se ha hecho mediante los nicknames. Se implementarán de manera externa otros tipos de ordenaciones.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Josune Ordoñez (Josune07)
 */

public class clsCliente implements Serializable, Comparable<clsCliente> 
{
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dni;
	private String empresa;

	/**
	 * Constructor con parámetros para tomar usuarios de la Base de Datos.
	 * @param n Nombre del usuario
	 * @param ap1 Primer apellido del usuario
	 * @param ap2 Segundo apellido del usuario
	 * @param nick Nickname del usuario
	 * @param cont Contraseña del usuario
	 * @param el Puntuación ELO del usuario
	 * @param fecha Fecha de alta del usuario
	 */
	public clsCliente(String n, String ap1, String ap2, String nick, String cont)
	{
		nombre=n;
		apellido1=ap1;
		apellido2=ap2;
		dni=nick;
		empresa=cont;
	}
	
	/**
	 * Constructor vacío para poder serializar.
	 */
	public clsCliente()
	{
		nombre=null;
		apellido1=null;
		apellido2=null;
		dni=null;
		empresa=null;
				
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
				+ ((dni == null) ? 0 : dni.hashCode());
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
		clsCliente other = (clsCliente) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	/**
	 * Reimplementación del método toString.
	 */
	public String toString()
	{
		SimpleDateFormat formato = new SimpleDateFormat ("dd/MM/yyyy");
		String e = "Nombre: "+this.getNombre()+" - Apellido 1: "+this.getApellido1()+" - Apellido 2: "+this.getApellido2()+
				" - DNI: "+this.getdni();
		return e;
	}
	
	/**
	 * Ordenación natural hecha mediante nickname de usuarios.
	 */
	public int compareTo(clsCliente arg0) 
	{
		return this.getdni().compareTo(arg0.getdni());
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
	public String getdni() 
	{
		return dni;
	}
	public void setdni(String nickname)
	{
		this.dni = nickname;
	}
	public String getEmpresa()
	{
		return empresa;
	}
	public void setEmpresa(String contraseña)
	{
		this.empresa= contraseña;
	}
}