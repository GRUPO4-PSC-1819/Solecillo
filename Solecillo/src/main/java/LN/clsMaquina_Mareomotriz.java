package LN;

import java.io.Serializable;
import java.util.Date;

public class clsMaquina_Mareomotriz extends clsMaquina implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nombre_pueblo;
	private double distancia_millas_marinas_pueblo;
	
		//constructor para crear nueva máquina_hidraulica
		public clsMaquina_Mareomotriz(int id, String n, String color, double v, String f, Date ff, String np, double mm)
		{
			super(id, n, color, v, f, ff);
			this.nombre_pueblo=np;
			this.distancia_millas_marinas_pueblo=mm;
		}
		
		//constructor vacío para serializar
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
