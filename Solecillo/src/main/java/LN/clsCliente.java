package LN;

import java.io.Serializable;

/**
 * Clase creada para generar un objeto nuevo (clsCliente). Implementa la interfaz Comparable con clsCliente y Serializable. <br>
 * La ordenación natural se ha hecho mediante los dni.
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
	private int tot_ventas;
	
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
	 * Constructor con parámetros para crear un nuevo cliente.
	 * @param n Nombre del cliente
	 * @param ap1 Primer apellido del cliente
	 * @param ap2 Segundo apellido del cliente
	 * @param dni DNI del cliente
	 * @param emp Empresa del cliente
	 */
	public clsCliente(String n, String ap1, String ap2, String dni, String emp)
	{
		nombre=n;
		apellido1=ap1;
		apellido2=ap2;
		this.dni=dni;
		setEmpresa(emp);
	}
	
	/**
	 * Constructor dos parámetros para crear un nuevo cliente.
	 * @param dni DNI del cliente
	 * @param tot_ventas Total de Ventas hechas a ese cliente
	 */
	public clsCliente(String dni, int tot_ventas)
	{
		this.dni=dni;
		this.tot_ventas=tot_ventas;
	}
	
	/**
	 * Constructor vacío.
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
	 * Reimplementación del método toString.
	 */
	public String toString()
	{
		String e = "Nombre: "+this.getNombre()+" - Apellido 1: "+this.getApellido1()+" - Apellido 2: "+this.getApellido2()+
				" - DNI: "+this.getdni();
		return e;
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
	public String getdni() 
	{
		return dni;
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
	
	public int getTot_Ventas() {
		return tot_ventas;
	}

	public void setTot_Ventas(int tot_ventas) {
		this.tot_ventas = tot_ventas;
	}
}