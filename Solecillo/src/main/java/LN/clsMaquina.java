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
 * Clase creada para generar un objeto nuevo (clsMaquina).
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Josune Ordoñez (Josune07)
 */
public class clsMaquina implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	
	protected int id;
	protected String nombre;
	protected String color;
	protected double valor;
	protected String fabricante;
	protected String estado;
	private String tipo;
	private int tot_v;
	private int tot_maquinas;

	
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
	public clsMaquina(int id, String n, String color, double v, String f, String e)
	{
		this.id=id;
		this.nombre=n;
		this.color=color;
		this.valor=v;
		this.fabricante=f;
		this.estado=e;
		logger.log( Level.INFO, "Constructor máquina con todos los parámetros");
	}
	/**
	 * Constructor con todos los parámetros, excepto el id
	 */
			public clsMaquina(String n, String color, double v, String f, String e)
			{
				this.nombre=n;
				this.color=color;
				this.valor=v;
				this.fabricante=f;
				this.estado=e;
				logger.log( Level.INFO, "Constructor máquina con todos los parámetros excepto el id");
			}
			
			/**
			 * Constructor para gráficos
			 */
			public clsMaquina(String t, double v)
			{
				this.tipo=t;
				this.estado=t;
				this.valor=v;
				logger.log( Level.INFO, "Constructor máquina con tipo/estado y valor");
			}
			
			/**
			 * Constructor para gráficos
			 */
			public clsMaquina(String t, int tot_v)
			{
				this.tipo=t;
				this.fabricante=t;
				this.tot_v=tot_v;
				this.tot_maquinas=tot_v;
				logger.log( Level.INFO, "Constructor máquina con tipo/fabricante y total_ventas/total_maquinas");
			}
			
			/**
			 * Constructor vacío
			 */
	public clsMaquina()
	{
		this.id=0;
		this.nombre=null;
		this.color=null;
		this.valor=0.0;
		this.fabricante=null;
		this.estado=null;
		logger.log( Level.INFO, "Constructor máquina vacío");
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
		String e = "ID: "+this.getId()+" - Nombre: "+this.getNombre()+" - Color: "+this.getColor()+
				" - Valor: "+this.getValor()+" - Fabricante: "+this.getFabricante()+" - Estado: "+this.getEstado();
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
	public String getEstado() {
		return estado;
	}
 	public void setEstado(String estado) {
		this.estado = estado;
	}
 	public String getTipo() {
		return tipo;
	}
 	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
 	public int getTot_ventas() {
		return tot_v;
	}
 	public void setTot_ventas(int tot_v) {
		this.tot_v = tot_v;
	}
 	public int getTot_maquinas() {
		return tot_maquinas;
	}
 	public void setTot_maquinas(int tot_maquinas) {
		this.tot_maquinas = tot_maquinas;
	}

}