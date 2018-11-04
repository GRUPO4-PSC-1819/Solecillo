package LN;

import java.io.Serializable;

public class clsVenta implements Serializable{

	
	private int idm;
	private String nombreC;
	private static int nid=20;
	private int cantidad;
	private int id;
	
	
	public static int getNid() {
		return nid;
	}
	public static void setNid(int nid) {
		clsVenta.nid = nid;
	}
	public clsVenta()
	{
		
	}
	public clsVenta(int idm, String nombreC, int cantidad) {
		super();
		this.idm = idm;
		this.nombreC = nombreC;
		this.cantidad = cantidad;
		this.id=nid;
		nid++;
	}
	public clsVenta(int id,String nombreC,int idm, int cantidad) {
		super();
		this.idm = idm;
		this.nombreC = nombreC;
		this.cantidad = cantidad;
		this.id=id;
	}
	
	public int getIdm() {
		return idm;
	}
	public void setIdm(int idm) {
		this.idm = idm;
	}
	public String getNombreC() {
		return nombreC;
	}
	public void setNombreC(String nombreC) {
		this.nombreC = nombreC;
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
