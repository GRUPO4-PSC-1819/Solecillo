package LN;

import java.io.Serializable;

/**
 * Clase creada para generar un objeto nuevo (clsMaquina_Mareomotriz).
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Josune Ordoñez (Josune07)
 */
public class clsMaquina_Mareomotriz extends clsMaquina implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nombre_pueblo;
	private double distancia_millas_marinas_pueblo;
	
	/**
	 * Constructor con todos los parámetros
	 */
		public clsMaquina_Mareomotriz(int id, String n, String color, double v, String f, String e, String np, double mm)
		{
			super(id, n, color, v, f, e);
			this.nombre_pueblo=np;
			this.distancia_millas_marinas_pueblo=mm;
		}
		
		/**
		 * Constructor con todos los parámetros, excepto el id
		 */
				public clsMaquina_Mareomotriz(String n, String color, double v, String f, String e, String np, double mm)
				{
					super(n, color, v, f, e);
					this.nombre_pueblo=np;
					this.distancia_millas_marinas_pueblo=mm;
				}
		
				/**
				 * Constructor vacío
				 */
		public clsMaquina_Mareomotriz()
		{
			super();
			this.nombre_pueblo=null;
			this.distancia_millas_marinas_pueblo=0.0;
		}
		
		
		public String getNombre_pueblo() {
			return nombre_pueblo;
		}

		public void setNombre_pueblo(String nombre_pueblo) {
			this.nombre_pueblo = nombre_pueblo;
		}

		public double getDistancia_millas_marinas_pueblo() {
			return distancia_millas_marinas_pueblo;
		}

		public void setDistancia_millas_marinas_pueblo(double distancia_millas_marinas_pueblo) {
			this.distancia_millas_marinas_pueblo = distancia_millas_marinas_pueblo;
		}
}
