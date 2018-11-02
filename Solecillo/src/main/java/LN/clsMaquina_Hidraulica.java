package LN;

import java.io.Serializable;

public class clsMaquina_Hidraulica extends clsMaquina implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nombre_pueblo;
	private String nombre_rio;
	
	
	//constructor para crear nueva máquina_hidraulica
		public clsMaquina_Hidraulica(int id, String n, String color, double v, String f, String np, String nr)
		{
			super(id, n, color, v, f);
			this.nombre_pueblo=np;
			this.nombre_rio=nr;
		}
		
		//constructor sin id
				public clsMaquina_Hidraulica(String n, String color, double v, String f, String np, String nr)
				{
					super(n, color, v, f);
					this.nombre_pueblo=np;
					this.nombre_rio=nr;
				}
		
		//constructor vacío para serializar
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
