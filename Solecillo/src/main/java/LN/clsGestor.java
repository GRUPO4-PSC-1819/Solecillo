package LN;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

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
							rs.getString("ESTADO"),
							rs.getString("NOMBRE_PUEBLO"),
							rs.getString("NOMBRE_CAMPO"),
							rs.getDouble("ALTURA"),
							rs.getDouble("DIAMETRO")));
				}
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			
		}
				
		return lista;	
	}
	
	public clsMaquina_Eolica ObtenerEolica(int id)	
	{	
		clsMaquina_Eolica m=null;
		clsBD.crearTablaBD(clsConstantes.MAQUINA);
		ResultSet rs = clsBD.ObtenerUnaMaquina(id);
		if (rs != null)
		{
			try 
			{
				while (rs.next())
				{
					m=new clsMaquina_Eolica(
							rs.getInt("ID"),
							rs.getString("NOMBRE"),
							rs.getString("COLOR"),
							rs.getDouble("VALOR"),
							rs.getString("FABRICANTE"),
							rs.getString("ESTADO"),
							rs.getString("NOMBRE_PUEBLO"),
							rs.getString("NOMBRE_CAMPO"),
							rs.getDouble("ALTURA"),
							rs.getDouble("DIAMETRO"));
				}
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			
		}
				
		return m;	
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
							rs.getString("ESTADO"),
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
	
	public clsMaquina_Hidraulica ObtenerHidraulica(int id)	
	{	
		clsMaquina_Hidraulica m=null;
		clsBD.crearTablaBD(clsConstantes.MAQUINA);
		ResultSet rs = clsBD.ObtenerUnaMaquina(id);
		if (rs != null)
		{
			try 
			{
				while (rs.next())
				{
					m=new clsMaquina_Hidraulica(
							rs.getInt("ID"),
							rs.getString("NOMBRE"),
							rs.getString("COLOR"),
							rs.getDouble("VALOR"),
							rs.getString("FABRICANTE"),
							rs.getString("ESTADO"),
							rs.getString("NOMBRE_PUEBLO"),
							rs.getString("NOMBRE_RIO"));
				}
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			
		}
				
		return m;	
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
							rs.getString("ESTADO"),
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
	
	public clsMaquina_Mareomotriz ObtenerMareomotriz(int id)	
	{	
		clsMaquina_Mareomotriz m=null;
		clsBD.crearTablaBD(clsConstantes.MAQUINA);
		ResultSet rs = clsBD.ObtenerUnaMaquina(id);
		if (rs != null)
		{
			try 
			{
				while (rs.next())
				{
					m=new clsMaquina_Mareomotriz(
							rs.getInt("ID"),
							rs.getString("NOMBRE"),
							rs.getString("COLOR"),
							rs.getDouble("VALOR"),
							rs.getString("FABRICANTE"),
							rs.getString("ESTADO"),
							rs.getString("NOMBRE_PUEBLO"),
							rs.getDouble("DISTANCIA_MILLAS"));
				}
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return m;	
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
							rs.getString("ESTADO"),
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
	
	public clsMaquina_Solar ObtenerSolar(int id)	
	{	
		clsMaquina_Solar m=null;
		clsBD.crearTablaBD(clsConstantes.MAQUINA);
		ResultSet rs = clsBD.ObtenerUnaMaquina(id);
		if (rs != null)
		{
			try 
			{
				while (rs.next())
				{
					m=new clsMaquina_Solar(
							rs.getInt("ID"),
							rs.getString("NOMBRE"),
							rs.getString("COLOR"),
							rs.getDouble("VALOR"),
							rs.getString("FABRICANTE"),
							rs.getString("ESTADO"),
							rs.getString("NOMBRE_PUEBLO"),
							rs.getString("NOMBRE_CAMPO"));
				}
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return m;	
	}
	public ArrayList<clsCliente> ListaClientes()	
	{	
		ArrayList<clsCliente> lista = new ArrayList <clsCliente>();
		ResultSet rs = clsBD.obtenerDatosTablaBD("CLIENTE");
		if (rs != null)
		{
			try 
			{
				while (rs.next())
				{
					lista.add(new clsCliente(
							rs.getString("NOMBRE"),
							rs.getString("AP_1"),
							rs.getString("AP_2"),
							rs.getString("DNI"),
							rs.getString("EMPRESA")));
				}
			} 
			catch (SQLException e)
			{
				e.printStackTrace(); 
			}
			
		}
				
		return lista;	
	}
	
	public ArrayList<clsVenta> ListaVentas()	
	{	
		ArrayList<clsVenta> lista = new ArrayList <clsVenta>();
		ResultSet rs = clsBD.obtenerDatosTablaBD(clsConstantes.VENTA);

		if (rs != null)
		{
			try 
			{
				while (rs.next())
				{lista.add(new clsVenta(
							rs.getInt("ID"),
							rs.getInt("IDM"),
							rs.getString("DNIC"),
							rs.getInt("CANTIDAD")));
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
	
	public void CrearCliente(String n, String ap1, String ap2, String dni, String empresa) throws clsClienteRepetido
	{
		clsCliente nuevo=new clsCliente(n, ap1, ap2, dni, empresa);
		ArrayList<clsCliente> listaClientes=new ArrayList<clsCliente>();
		listaClientes=ListaClientes();
		if(listaClientes.size()!=0)
		{
			HashSet<clsCliente> set=new HashSet<clsCliente>();
			set.addAll(listaClientes);
			if(set.add(nuevo)==false)
			{
				throw new clsClienteRepetido();
			}
		}
		clsBD.insertarDatoTablaBD(nuevo);
	}
	
	public void CrearMaquinaEolica(String n, String col, double val, String fab, String e, String np, String nc, double a, double d)
	{
		clsMaquina_Eolica nuevo=new clsMaquina_Eolica(n, col, val, fab, e, np, nc, a, d);
		clsBD.insertarDatoTablaBD(nuevo);
	}
	
	public void CrearMaquinaHidraulica(String n, String col, double val, String fab, String e, String np, String nr)
	{
		clsMaquina_Hidraulica nuevo=new clsMaquina_Hidraulica(n, col, val, fab, e, np, nr);
		clsBD.insertarDatoTablaBD(nuevo);
	}
	
	public void CrearMaquinaMareomotriz(String n, String col, double val, String fab, String e, String np, double distancia)
	{
		clsMaquina_Mareomotriz nuevo=new clsMaquina_Mareomotriz(n, col, val, fab, e, np, distancia);
		clsBD.insertarDatoTablaBD(nuevo);
	}
	
	public void CrearMaquinaSolar(String n, String col, double val, String fab, String e, String np, String nc)
	{
		clsMaquina_Solar nuevo=new clsMaquina_Solar(n, col, val, fab, e, np, nc);
		clsBD.insertarDatoTablaBD(nuevo);
	}
	
	public void CrearVenta(int idm, String dniC, int can)
	{
		clsVenta nuevo=new clsVenta(idm, dniC, can);
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
	public clsUsuario ModificarUsuario (String n, String ap1, String ap2, String nick, String cont)
	{
		clsUsuario modificado=new clsUsuario(n, ap1, ap2, nick, cont);
		clsBD.modificarDatoTablaBD(modificado);
		return modificado;
	}

	
	
	public void BorrarMaquina(int id)
	{
		clsBD.BorrarMaquina(id);
	}
	public void BorrarUsuarios()
	{
		clsBD.BorrarUsuarios();
	}
	public void BorrarClientes()
	{
		clsBD.BorrarClientes();
	}
	public void BorrarMaquinas()
	{
		clsBD.BorrarMaquinas();
	}
	public void BorrarVentas()
	{
		clsBD.BorrarVentas();
	}
	
	
	
	
	
	public void ModificarMaquinaEolica(int id, String n, String col, double val, String fab, String e, String np, String nc, double a, double d)
	{
		clsBD.modificarEolica(id, n, col, val, fab, e, np, nc, a, d);
	}
	
	public void ModificarMaquinaHidraulica(int id, String n, String col, double val, String fab, String e, String np, String nr)
	{
		clsBD.modificarHidraulica(id, n, col, val, fab, e, np, nr);
	}
	
	public void ModificarMaquinaMareomotriz(int id, String n, String col, double val, String fab, String e, String np, double d)
	{
		clsBD.modificarMareomotriz(id, n, col, val, fab, e, np, d);
	}
	
	public void ModificarMaquinaSolar(int id, String n, String col, double val, String fab, String e, String np, String nc)
	{
		clsBD.modificarSolar(id, n, col, val, fab, e, np, nc);
	}
	
	public void MigracionUsuarios() throws clsUsuarioRepetido
	{
		BorrarUsuarios();
		String nombre;
		String apellido1;
		String apellido2;
		String nickname;
		String contraseña;
		for(int i1=0;i1<100;i1++)
		{
			nombre="NumbreUsu_"+i1;
			apellido1="Apellido1_Usu"+i1;
			apellido2="Apellido2_Usu"+i1;
			nickname="Nickname_Usu"+i1;
			contraseña="Contraseña_Usu"+i1;
			CrearUsuario(nombre, apellido1, apellido2, nickname, contraseña);
		}
	}
	public void MigracionClientes() throws clsClienteRepetido
	{
		BorrarClientes();
		String nombre_c;
		String apellido1_c;
		String apellido2_c;
		String dni;
		String empresa;
		for(int i2=0;i2<100;i2++)
		{
			nombre_c="NumbreCli_"+i2;
			apellido1_c="Apellido1_Cli"+i2;
			apellido2_c="Apellido2_Cli"+i2;
			dni="123456789"+i2;
			empresa="Empresa_Cli"+i2;
			CrearCliente(nombre_c, apellido1_c, apellido2_c, dni, empresa);
		}
	}
	public void MigracionMaquinas()
	{
		BorrarMaquinas();
		String nombre_m;
		String color_m=null;
		double valor_m;
		String fabricante_m=null;
		String estado_m=null;
		String nombre_pueblo_m=null;
		String nombre_campo_m=null;
		String nombre_rio_m=null;
		double altura;
		double diametro;
		double distancia;
		double min_a=10;
		double max_a=100;
		double min_d=10;
		double max_d=70;
		double min_dm=1;
		double max_dm=15;
		//EÓLICAS
		for(int i3=0;i3<100;i3++)
		{
			nombre_m="NombreMáquinaEólica"+i3;
			
			if(i3%5==0)color_m="Verde";
			else if(i3%4==0)color_m="Blanco";
			else if(i3%3==0)color_m="Gris";
			else if(i3%2==0)color_m="Azul";
			else if(i3%1==0)color_m="Negro";
			
			valor_m=ThreadLocalRandom.current().nextDouble(1000000, 3000000);
			
			if(i3%3==0)fabricante_m="Fabricante_1";
			else if(i3%2==0)fabricante_m="Fabricante_2";
			else if(i3%1==0)fabricante_m="Fabricante_3";
			
			if(i3%4==0)estado_m=clsConstantes.ESTADO1;
			else if(i3%3==0)estado_m=clsConstantes.ESTADO2;
			else if(i3%2==0)estado_m=clsConstantes.ESTADO3;
			else if(i3%1==0)estado_m=clsConstantes.ESTADO4;
			
			if(i3%5==0)nombre_pueblo_m="Tafalla";
			else if(i3%4==0)nombre_pueblo_m="Tudela";
			else if(i3%3==0)nombre_pueblo_m="Legutiano";
			else if(i3%2==0)nombre_pueblo_m="Estella";
			else if(i3%1==0)nombre_pueblo_m="Salvatierra";
			
			if(i3%2==0)nombre_campo_m="Campo 1";
			else if(i3%1==0)nombre_campo_m="Campo2";
			
			altura=ThreadLocalRandom.current().nextDouble(min_a, max_a);
			
			diametro=ThreadLocalRandom.current().nextDouble(min_d, max_d);
			
			CrearMaquinaEolica(nombre_m, color_m, valor_m, fabricante_m, estado_m, nombre_pueblo_m, nombre_campo_m, altura, diametro);
		}
		//HIDRÁULICAS
		for(int i4=0;i4<100;i4++)
		{
			nombre_m="NombreMáquinaHidráulica"+i4;
			
			if(i4%5==0)color_m="Verde";
			else if(i4%4==0)color_m="Blanco";
			else if(i4%3==0)color_m="Gris";
			else if(i4%2==0)color_m="Azul";
			else if(i4%1==0)color_m="Negro";
			
			valor_m=ThreadLocalRandom.current().nextDouble(70000, 2000000);
			
			if(i4%3==0)fabricante_m="Fabricante_1";
			else if(i4%2==0)fabricante_m="Fabricante_2";
			else if(i4%1==0)fabricante_m="Fabricante_3";
			
			if(i4%4==0)estado_m=clsConstantes.ESTADO1;
			else if(i4%3==0)estado_m=clsConstantes.ESTADO2;
			else if(i4%2==0)estado_m=clsConstantes.ESTADO3;
			else if(i4%1==0)estado_m=clsConstantes.ESTADO4;
			
			if(i4%5==0)nombre_pueblo_m="Tolosa";
			else if(i4%4==0)nombre_pueblo_m="Hernani";
			else if(i4%3==0)nombre_pueblo_m="Deba";
			else if(i4%2==0)nombre_pueblo_m="Zumaia";
			else if(i4%1==0)nombre_pueblo_m="Estella";
			
			if(i4%5==0)nombre_rio_m="Oria";
			else if(i4%4==0)nombre_rio_m="Urumea";
			else if(i4%3==0)nombre_rio_m="Deba";
			else if(i4%2==0)nombre_rio_m="Urola";
			else if(i4%1==0)nombre_rio_m="Ega";

			CrearMaquinaHidraulica(nombre_m, color_m, valor_m, fabricante_m, estado_m, nombre_pueblo_m, nombre_rio_m);
		}
		//MAREOMOTRICES
		for(int i5=0;i5<100;i5++)
		{
			nombre_m="NombreMáquinaMareomotriz"+i5;
			
			if(i5%5==0)color_m="Verde";
			else if(i5%4==0)color_m="Blanco";
			else if(i5%3==0)color_m="Gris";
			else if(i5%2==0)color_m="Azul";
			else if(i5%1==0)color_m="Negro";
			
			valor_m=ThreadLocalRandom.current().nextDouble(500000, 2500000);
			
			if(i5%3==0)fabricante_m="Fabricante_1";
			else if(i5%2==0)fabricante_m="Fabricante_2";
			else if(i5%1==0)fabricante_m="Fabricante_3";
			
			if(i5%4==0)estado_m=clsConstantes.ESTADO1;
			else if(i5%3==0)estado_m=clsConstantes.ESTADO2;
			else if(i5%2==0)estado_m=clsConstantes.ESTADO3;
			else if(i5%1==0)estado_m=clsConstantes.ESTADO4;
			
			if(i5%5==0)nombre_pueblo_m="Zarautz";
			else if(i5%4==0)nombre_pueblo_m="Bakio";
			else if(i5%3==0)nombre_pueblo_m="Lekeitio";
			else if(i5%2==0)nombre_pueblo_m="Pasajes";
			else if(i5%1==0)nombre_pueblo_m="San Juan de Luz";
			
			distancia=ThreadLocalRandom.current().nextDouble(min_dm, max_dm);

			CrearMaquinaMareomotriz(nombre_m, color_m, valor_m, fabricante_m, estado_m, nombre_pueblo_m, distancia);
		}
		//SOLARES
		for(int i6=0;i6<100;i6++)
		{
			nombre_m="NombreMáquinaSolar"+i6;
			
			if(i6%5==0)color_m="Verde";
			else if(i6%4==0)color_m="Blanco";
			else if(i6%3==0)color_m="Gris";
			else if(i6%2==0)color_m="Azul";
			else if(i6%1==0)color_m="Negro";
			
			valor_m=ThreadLocalRandom.current().nextDouble(50000, 500000);
			
			if(i6%3==0)fabricante_m="Fabricante_1";
			else if(i6%2==0)fabricante_m="Fabricante_2";
			else if(i6%1==0)fabricante_m="Fabricante_3";
			
			if(i6%4==0)estado_m=clsConstantes.ESTADO1;
			else if(i6%3==0)estado_m=clsConstantes.ESTADO2;
			else if(i6%2==0)estado_m=clsConstantes.ESTADO3;
			else if(i6%1==0)estado_m=clsConstantes.ESTADO4;
			
			if(i6%5==0)nombre_pueblo_m="Puente la Reina";
			else if(i6%4==0)nombre_pueblo_m="Olite";
			else if(i6%3==0)nombre_pueblo_m="Artajona";
			else if(i6%2==0)nombre_pueblo_m="Lodosa";
			else if(i6%1==0)nombre_pueblo_m="Caparroso";
			
			if(i6%2==0)nombre_campo_m="Campo 1";
			else if(i6%1==0)nombre_campo_m="Campo2";
			
			CrearMaquinaSolar(nombre_m, color_m, valor_m, fabricante_m, estado_m, nombre_pueblo_m, nombre_campo_m);
		}
	}
	public void MigracionVentas()
	{
		BorrarVentas();
		int id_m;
		String dni_c;
		int cantidad;
		ArrayList<clsCliente>clientes=new ArrayList<clsCliente>();
		clientes=ListaClientes();
		int id_cliente;
		for(int i=0;i<1000;i++)
		{
			id_m=ThreadLocalRandom.current().nextInt(1, 400);
			id_cliente=ThreadLocalRandom.current().nextInt(0, 99);
			dni_c=clientes.get(id_cliente).getdni();
			cantidad=ThreadLocalRandom.current().nextInt(1, 5);
			
			CrearVenta(id_m, dni_c, cantidad);
		}
	}
	
	public ArrayList<clsCliente> top_10_clientes()
	{
		ArrayList<clsCliente> lista = new ArrayList <clsCliente>();
		ResultSet rs = clsBD.clientesTOP();
		if (rs != null)
		{
			try 
			{
				while (rs.next())
				{
					lista.add(new clsCliente(
							rs.getString("DNIC"),
							rs.getInt("TOTAL_VENTAS")));
				}
			} 
			catch (SQLException e)
			{
				e.printStackTrace(); 
			}
			
		}
		return lista;
	}
	
	public ArrayList<clsMaquina> valor_medio_maquinas()
	{
		ArrayList<clsMaquina> lista = new ArrayList <clsMaquina>();
		ResultSet rs = clsBD.valor_medio_maquinas();
		if (rs != null)
		{
			try 
			{
				while (rs.next())
				{
					lista.add(new clsMaquina(
							rs.getString("TIPO"),
							rs.getDouble("MEDIA_VALOR")));
				}
			} 
			catch (SQLException e)
			{
				e.printStackTrace(); 
			}
			
		}
		return lista;
	}
	
	public ArrayList<clsMaquina> ventas_tipo_maquina()
	{
		ArrayList<clsMaquina> lista = new ArrayList <clsMaquina>();
		ResultSet rs = clsBD.ventas_tipo_maquina();
		if (rs != null)
		{
			try 
			{
				while (rs.next())
				{
					lista.add(new clsMaquina(
							rs.getString("TIPO"),
							rs.getInt("TOTAL_VENTAS")));
				}
			} 
			catch (SQLException e)
			{
				e.printStackTrace(); 
			}
			
		}
		return lista;
	}

}