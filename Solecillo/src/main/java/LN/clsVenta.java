package LN;

import java.io.Serializable;


/**
 * Clase creada para generar un objeto nuevo (clsVenta). Implementa la interfaz Serializable. <br>
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Josune Ordoñez (Josune07)
 */
public class clsVenta implements Serializable{

	private static final long serialVersionUID = 1L;
	private int idm;
	private String dniC;
	private int cantidad;
	private int id;
	
	/**
	 * Constructor con todos los parámetros
	 */
	public clsVenta(int id, int idm, String dniC, int cantidad) {
		this.id=id;
		this.idm = idm;
		this.dniC = dniC;
		this.cantidad = cantidad;
	}
	
	/**
	 * Constructor con todos los parámetros, excepto el id
	 */
	public clsVenta(int idm, String dniC, int cantidad) {
		this.idm = idm;
		this.dniC = dniC;
		this.cantidad = cantidad;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		clsVenta other = (clsVenta) obj;
		if (id != other.id)
			return false;
		return true;
	}
	public int getIdm() {
		return idm;
	}
	public void setIdm(int idm) {
		this.idm = idm;
	}
	public String getDniC() {
		return dniC;
	}
	public void setDniC(String dniC) {
		this.dniC = dniC;
	}
	public int getId() {
		return id;
	}
	public  void setId(int id) {
		this.id = id;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
