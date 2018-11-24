package LN;

import java.io.Serializable;

/**
 * Clase creada para generar un objeto nuevo (clsMaquina_Solar).
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Josune Ordoñez (Josune07)
 */
public class clsMaquina_Solar extends clsMaquina implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nombre_pueblo;
	private String nombre_campo;	
	
	/**
	 * Constructor con todos los parámetros
	 */
		public clsMaquina_Solar(int id, String n, String color, double v, String f, String e, String np, String nc)
		{
			super(id, n, color, v, f, e);
			this.nombre_pueblo=np;
			this.nombre_campo=nc;
		}
		
		/**
		 * Constructor con todos los parámetros, excepto el id
		 */
				public clsMaquina_Solar(String n, String color, double v, String f, String e, String np, String nc)
				{
					super(n, color, v, f, e);
					this.nombre_pueblo=np;
					this.nombre_campo=nc;
				}
		
				/**
				 * Constructor vacío
				 */
		public clsMaquina_Solar()
		{
			super();
			this.nombre_pueblo=null;
			this.nombre_campo=null;
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
}
