package LN;

import java.io.Serializable;

public class clsMaquina_Solar extends clsMaquina implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nombre_pueblo;
	private String nombre_campo;	
	
	//constructor para crear nueva máquina_hidraulica
		public clsMaquina_Solar(int id, String n, String color, double v, String f, String e, String np, String nc)
		{
			super(id, n, color, v, f, e);
			this.nombre_pueblo=np;
			this.nombre_campo=nc;
		}
		
		//constructor sin id
				public clsMaquina_Solar(String n, String color, double v, String f, String e, String np, String nc)
				{
					super(n, color, v, f, e);
					this.nombre_pueblo=np;
					this.nombre_campo=nc;
				}
		
		//constructor vacío para serializar
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
