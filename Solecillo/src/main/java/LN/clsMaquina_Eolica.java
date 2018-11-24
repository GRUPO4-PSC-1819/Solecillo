package LN;

import java.io.Serializable;

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
				}
	
	/**
	 * Constructor específico para gráficos
	 */
	public clsMaquina_Eolica(String np, double a, double d)
				{
					this.nombre_pueblo=np;
					this.altura=a;
					this.diametro=d;
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
