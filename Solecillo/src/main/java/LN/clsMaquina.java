package LN;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase creada para generar un objeto nuevo (clsPieza), de la que heredarán todas las piezas del ajedrez.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */

public class clsMaquina implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	protected int id;
	protected String nombre;
	protected String color;
	protected double valor;
	protected String fabricante;
	protected Date fecha_compra;
	protected Date fecha_fin;

	//constructor para crear nueva máquina
	public clsMaquina(int id, String n, String color, double v, String f, Date ff)
	{
		this.id=id;
		this.nombre=n;
		this.color=color;
		this.valor=v;
		this.fabricante=f;
		this.fecha_compra=new Date();
		this.fecha_fin=ff;
	}
	
	//constructor vacío para serializar
	public clsMaquina()
	{
		this.id=0;
		this.nombre=null;
		this.color=null;
		this.valor=0.0;
		this.fabricante=null;
		this.fecha_compra=null;
		this.fecha_fin=null;
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
		clsMaquina other = (clsMaquina) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	/**
	 * Reimplementación del método toString.
	 */
	public String toString()
	{
		SimpleDateFormat formato = new SimpleDateFormat ("dd/MM/yyyy");
		String e = "ID: "+this.getId()+" - Nombre: "+this.getNombre()+" - Color: "+this.getColor()+
				" - Valor: "+this.getValor()+" - Fabricante: "+this.getFabricante()+
				"- Fecha de registro: "+formato.format(this.getFecha_compra());
		return e;
	}
	
	/**
	 * Ordenación natural hecha mediante nombre de maquinas.
	 */
	public int compareTo(clsMaquina arg0) 
	{
		return this.getNombre().compareTo(arg0.getNombre());
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public Date getFecha_compra() {
		return fecha_compra;
	}

	public void setFecha_compra(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	
	public Date getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}



}