package LN;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase creada para generar un objeto nuevo (clsCliente). Implementa la interfaz Comparable con clsUsuario y Serializable. <br>
 * La ordenaciÃ³n natural se ha hecho mediante los nicknames. Se implementarÃ¡n de manera externa otros tipos de ordenaciones.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Josune OrdoÃ±ez (Josune07)
 */

public class clsCliente implements Serializable, Comparable<clsCliente> 
{
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dni;
	private String empresa;
	private Date fechadealta;
	
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
	 * Constructor con parÃ¡metros para crear un nuevo usuario.
	 * @param n Nombre del cliente
	 * @param ap1 Primer apellido del cliente
	 * @param ap2 Segundo apellido del cliente
	 * @param nick Nickname del cliente
	 * @param cont ContraseÃ±a del cliente
	 */
	public clsCliente(String n, String ap1, String ap2, String dni, String emp)
	{
		nombre=n;
		apellido1=ap1;
		apellido2=ap2;
		this.dni=dni;
		setEmpresa(emp);

		fechadealta=new Date();
	}
	
	/**
	 * Constructor con parÃ¡metros para tomar usuarios de la Base de Datos.
	 * @param n Nombre del usuario
	 * @param ap1 Primer apellido del usuario
	 * @param ap2 Segundo apellido del usuario
	 * @param nick Nickname del usuario
	 * @param cont ContraseÃ±a del usuario
	 * @param el PuntuaciÃ³n ELO del usuario
	 * @param fecha Fecha de alta del usuario
	 */
	
	/**
	 * Constructor vacÃ­o para poder serializar.
	 */
	public clsCliente()
	{
		nombre=null;
		apellido1=null;
		apellido2=null;
		dni=null;
		setEmpresa(null);

	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		return result;
	}
	


	/**
	 * ReimplementaciÃ³n del mÃ©todo toString.
	 */
	public String toString()
	{
		SimpleDateFormat formato = new SimpleDateFormat ("dd/MM/yyyy");
		String e = "Nombre: "+this.getNombre()+" - Apellido 1: "+this.getApellido1()+" - Apellido 2: "+this.getApellido2()+
				" - DNI: "+this.getdni()+" - Fecha de registro: "+formato.format(this.getFechadealta());
		return e;
	}
	
	/**
	 * OrdenaciÃ³n natural hecha mediante nickname de usuarios.
	 */
	

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
	public String getdni() 
	{
		return dni;
	}
	public void setApellido2(String apellido2) 
	{
		this.apellido2 = apellido2;
	}
	public Date getFechadealta()
	{
		return fechadealta;
	}
	public void setFechadealta(Date fechadealta)
	{
		this.fechadealta = fechadealta;
	}
	
	@Override
	public int compareTo(clsCliente arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

}
	
	
	

