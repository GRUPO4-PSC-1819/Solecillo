package LN;

public class Usuario {
	
	private int id;
	private String nombre_usuario;
	//private Estadisticas estadisticas;
	
	public Usuario (int id, String nombre_usuario)
	{
		this.id = id;
		this.nombre_usuario = nombre_usuario;
		//estadisticas = new Estadisticas(id);
	}

	public Usuario()
	{
		
	}
	
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	

}
