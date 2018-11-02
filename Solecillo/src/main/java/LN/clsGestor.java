package LN;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import Comun.clsConstantes;
import LN.clsUsuario;
import Persistencia.clsBD;

/**
 * Clase creada para generar un objeto nuevo (clsGestor), que valdrá para establecer un vínculo entre la aplicación y sus dos métodos de persistencia: <br>
 * 1) Base de Datos (para usuarios e historiales de partidas). <br>
 * 2) Ficheros serializados (para guardar el estado de la partida).
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Josune Ordoñez (Josune07)
 */

public class clsGestor implements Serializable 
{
	private static final long serialVersionUID = 1L;

	/**
	 * Método que recoge la lista de usuarios registrados en la Base de Datos.
	 * @return Lista de usuarios registrados
	 */
	public ArrayList<clsUsuario> ListaUsuarios()	
	{	
		ArrayList<clsUsuario> lista = new ArrayList <clsUsuario>();
		clsBD.crearTablaBD(clsConstantes.USUARIO);
		ResultSet rs = clsBD.obtenerDatosTablaBD(clsConstantes.USUARIO);
		if (rs != null)
		{
			try 
			{
				while (rs.next())
				{
					lista.add(new clsUsuario(
							rs.getString("NOMBRE"),
							rs.getString("APELLIDO1"),
							rs.getString("APELLIDO2"),
							rs.getString("NICKNAME"),
							rs.getString("CONTRASENYA")));
				}
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return lista;	
	}
	
	
	public ArrayList<clsMaquina_Eolica> ListaEolica()	
	{	
		ArrayList<clsMaquina_Eolica> lista = new ArrayList <clsMaquina_Eolica>();
		clsBD.crearTablaBD(clsConstantes.MAQUINA);
		ResultSet rs = clsBD.obtenerDatosTablaBD(clsConstantes.MAQUINA_E);
		if (rs != null)
		{
			try 
			{
				while (rs.next())
				{
					lista.add(new clsMaquina_Eolica(
							rs.getInt("ID"),
							rs.getString("NOMBRE"),
							rs.getString("COLOR"),
							rs.getDouble("VALOR"),
							rs.getString("FABRICANTE"),
							rs.getString("NOMBRE_PUEBLO"),
							rs.getString("NOMBRE_CAMPO"),
							rs.getInt("ALTURA"),
							rs.getInt("DIAMETRO")));
				}
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			
		}
				
		return lista;	
	}
	
	
	public ArrayList<clsMaquina_Hidraulica> ListaHidraulica()	
	{	
		ArrayList<clsMaquina_Hidraulica> lista = new ArrayList <clsMaquina_Hidraulica>();
		clsBD.crearTablaBD(clsConstantes.MAQUINA);
		ResultSet rs = clsBD.obtenerDatosTablaBD(clsConstantes.MAQUINA_H);
		if (rs != null)
		{
			try 
			{
				while (rs.next())
				{
					lista.add(new clsMaquina_Hidraulica(
							rs.getInt("ID"),
							rs.getString("NOMBRE"),
							rs.getString("COLOR"),
							rs.getDouble("VALOR"),
							rs.getString("FABRICANTE"),
							rs.getString("NOMBRE_PUEBLO"),
							rs.getString("NOMBRE_RIO")));
				}
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return lista;	
	}
	
	
	public ArrayList<clsMaquina_Mareomotriz> ListaMareomotriz()	
	{	
		ArrayList<clsMaquina_Mareomotriz> lista = new ArrayList <clsMaquina_Mareomotriz>();
		clsBD.crearTablaBD(clsConstantes.MAQUINA);
		ResultSet rs = clsBD.obtenerDatosTablaBD(clsConstantes.MAQUINA_M);
		if (rs != null)
		{
			try 
			{
				while (rs.next())
				{
					lista.add(new clsMaquina_Mareomotriz(
							rs.getInt("ID"),
							rs.getString("NOMBRE"),
							rs.getString("COLOR"),
							rs.getDouble("VALOR"),
							rs.getString("FABRICANTE"),
							rs.getString("NOMBRE_PUEBLO"),
							rs.getDouble("DISTANCIA_MILLAS")));
				}
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return lista;	
	}
	
	public ArrayList<clsMaquina_Solar> ListaSolar()	
	{	
		ArrayList<clsMaquina_Solar> lista = new ArrayList <clsMaquina_Solar>();
		clsBD.crearTablaBD(clsConstantes.MAQUINA);
		ResultSet rs = clsBD.obtenerDatosTablaBD(clsConstantes.MAQUINA_S);
		if (rs != null)
		{
			try 
			{
				while (rs.next())
				{
					lista.add(new clsMaquina_Solar(
							rs.getInt("ID"),
							rs.getString("NOMBRE"),
							rs.getString("COLOR"),
							rs.getDouble("VALOR"),
							rs.getString("FABRICANTE"),
							rs.getString("NOMBRE_PUEBLO"),
							rs.getString("NOMBRE_CAMPO")));
				}
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return lista;	
	}
	
	/**
	 * Envía una serie de atributos para crear un nuevo usuario. En caso de que hubiera alguna repetición de nickname, saltará una excepción que impida
	 * que se lleve a cabo dicho registro.
	 * @param n Nombre del usuario
	 * @param ap1 Primer apellido del usuario
	 * @param ap2 Segundo apellido del usuario
	 * @param nick Nickname del usuario
	 * @param cont Contraseña del usuario
	 * @throws clsUsuarioRepetido Excepción indicando que ha sucedido una repetición de nickname
	 */
	public void CrearUsuario(String n, String ap1, String ap2, String nick, String cont) throws clsUsuarioRepetido
	{
		clsUsuario nuevo=new clsUsuario(n, ap1, ap2, nick, cont);
		ArrayList<clsUsuario> listausuarios=new ArrayList<clsUsuario>();
		listausuarios=ListaUsuarios();
		if(listausuarios.size()!=0)
		{
			HashSet<clsUsuario> set=new HashSet<clsUsuario>();
			set.addAll(listausuarios);
	
			if(set.add(nuevo)==false)
			{
				throw new clsUsuarioRepetido();
			}
		}
		clsBD.insertarDatoTablaBD(nuevo);
	}
	
	public void CrearMaquinaEolica(String n, String col, double val, String fab, String np, String nc, double a, double d)
	{
		clsMaquina_Eolica nuevo=new clsMaquina_Eolica(n, col, val, fab, np, nc, a, d);
		clsBD.insertarDatoTablaBD(nuevo);
	}
	
	public void CrearMaquinaHidraulica(String n, String col, double val, String fab, String np, String nr)
	{
		clsMaquina_Hidraulica nuevo=new clsMaquina_Hidraulica(n, col, val, fab, np, nr);
		clsBD.insertarDatoTablaBD(nuevo);
	}
	
	public void CrearMaquinaMareomotriz(String n, String col, double val, String fab, String np, double distancia)
	{
		clsMaquina_Mareomotriz nuevo=new clsMaquina_Mareomotriz(n, col, val, fab, np, distancia);
		clsBD.insertarDatoTablaBD(nuevo);
	}
	
	public void CrearMaquinaSolar(String n, String col, double val, String fab, String np, String nc)
	{
		clsMaquina_Solar nuevo=new clsMaquina_Solar(n, col, val, fab, np, nc);
		clsBD.insertarDatoTablaBD(nuevo);
	}
	
	/**
	 * Método para modificar un usuario existente en la Base de Datos.
	 * @param n Nombre del usuario
	 * @param ap1 Primer apellido del usuario
	 * @param ap2 Segundo apellido del usuario
	 * @param nick Nickname del usuario
	 * @param cont Contraseña del usuario
	 * @param fechaalta Fecha de alta del usuario
	 * @return Usuario modificado para actualizar la ventana clsEleccion
	 */
	public clsUsuario ModificarUsuario (String n, String ap1, String ap2, String nick, String cont, Date fechaalta)
	{
		clsUsuario modificado=new clsUsuario(n, ap1, ap2, nick, cont, fechaalta);
		clsBD.modificarDatoTablaBD(modificado);
		return modificado;
	}
}