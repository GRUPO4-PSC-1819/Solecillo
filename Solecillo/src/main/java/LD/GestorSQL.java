package LD;
import java.sql.*;
import java.util.ArrayList;

import LN.Usuario;

public class GestorSQL 
{
	public Connection ConectarA (String ruta)
	{
		// Cargamos el driver
		try 
		{
			Class.forName("org.sqlite.JDBC");
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println("No se ha encontrado la libreria");
		}
		
		
		
		 Connection conec = null; // Declaramos la conexion
		 try 
		 {
			 conec = DriverManager.getConnection("jdbc:sqlite:"+ ruta );
			 System.out.println("Conexiï¿½n realizada correctamente - Ruta de base de datos: " + ruta);
		 }
		 
		 catch (SQLException e)
		 {
			 System.out.println("No se ha encontrado la ruta de la BD");
		 }
		 
		 return conec;
	
	}
	
public ArrayList<Usuario> MostrarUsuarios()  
	{
		Connection conn = ConectarA("data/Solecillo.bd"); //Nos conectamos a la BD
		Statement stmt = null;
	
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		
		String sentencia= "SELECT * FROM usuario";
		
		ResultSet rs = null;
		
		try {
			rs = stmt.executeQuery(sentencia);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String nombre = " ";
		//String id= " ";
		
		ArrayList<Usuario> datos_U_aux = new ArrayList<Usuario>();
		//int id_int;
		
		Usuario U1;
		
		try {
			
				while(rs.next())
				{
					U1 = new Usuario();
					//id =rs.getString(1);
					//System.out.println(id);
			
					//id_int=Integer.parseInt(id);
					//System.out.println(id_int);
					//U1.setId(id_int);
			
					nombre= rs.getString(1);
					System.out.println(nombre);
					U1.setNombre_usuario(nombre);
			
					datos_U_aux.add(U1);

				}
				
				
		}
		catch ( SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return datos_U_aux;
		
		
		
	}
		
	
public void guardarUsuario(Usuario U1)
	{
		Connection conn = ConectarA("data/Solecillo.db"); //Nos conectamos a la BD
		Statement stmt = null;
	
		try 
		{
			stmt = conn.createStatement();
		}
		catch (SQLException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String sentencia = "INSERT INTO USUARIO (ID_U, NOMBRE_U ) VALUES ( " +U1.getId()+ ", '"+U1.getNombre_usuario()+"'"+" );";
		
		
		try 
		{
			stmt.executeUpdate(sentencia);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	}



}
