package LN;

import java.io.Serializable;

public class clsMaquina_Eolica extends clsMaquina implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nombre_pueblo;
	private String nombre_campo;
	private double altura;
	private double diametro;


		//constructor para crear nueva máquina_hidraulica
		public clsMaquina_Eolica(int id, String n, String color, double v, String f, String np, String nc, double a, double d)
		{
			super(id, n, color, v, f);
			this.nombre_pueblo=np;
			this.nombre_campo=nc;
			this.altura=a;
			this.diametro=d;
		}
		
		//constructor sin id
				public clsMaquina_Eolica(String n, String color, double v, String f, String np, String nc, double a, double d)
				{
					super(n, color, v, f);
					this.nombre_pueblo=np;
					this.nombre_campo=nc;
					this.altura=a;
					this.diametro=d;
				}
		
		//constructor vacío para serializar
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
