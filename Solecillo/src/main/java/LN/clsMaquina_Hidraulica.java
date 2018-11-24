package LN;

import java.io.Serializable;

/**
 * Clase creada para generar un objeto nuevo (clsMaquina_Hidraulica).
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Josune Ordoñez (Josune07)
 */
public class clsMaquina_Hidraulica extends clsMaquina implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nombre_pueblo;
	private String nombre_rio;
	
	/**
	 * Constructor con todos los parámetros
	 */
		public clsMaquina_Hidraulica(int id, String n, String color, double v, String f, String e, String np, String nr)
		{
			super(id, n, color, v, f, e);
			this.nombre_pueblo=np;
			this.nombre_rio=nr;
		}
		
		/**
		 * Constructor con todos los parámetros, excepto el id
		 */
				public clsMaquina_Hidraulica(String n, String color, double v, String f, String e, String np, String nr)
				{
					super(n, color, v, f, e);
					this.nombre_pueblo=np;
					this.nombre_rio=nr;
				}
				
				/**
				 * Constructor para gráficos
				 */
				public clsMaquina_Hidraulica(String nr, String f, int tot_m)
				{
					super(f, tot_m);
					this.nombre_rio=nr;
				}
		
				/**
				 * Constructor vacío
				 */
				public clsMaquina_Hidraulica()
				{
					super();
					this.nombre_pueblo=null;
					this.nombre_rio=null;
				}
		
		
		public String getNombre_pueblo() {
			return nombre_pueblo;
		}

		public void setNombre_pueblo(String nombre_pueblo) {
			this.nombre_pueblo = nombre_pueblo;
		}

		public String getNombre_rio() {
			return nombre_rio;
		}

		public void setNombre_rio(String nombre_rio) {
			this.nombre_rio = nombre_rio;
		}	
}
