package LN;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import Comun.clsConstantes;
import LN.clsUsuario;
import LP.clsAltaEolica;
import Persistencia.clsBD;

/**
 * Clase creada para generar un objeto nuevo (clsGestor), que valdrá para establecer un vínculo entre la aplicación y los métodos de persistencia: <br>
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Josune Ordoñez (Josune07)
 */

public class clsGestor implements Serializable
{
	
private static final boolean ANYADIR_A_FIC_LOG = true;
	
	/*Logger*/
	private static Logger logger = Logger.getLogger( "Solecillo" );
	static 
	{
		try 
		{
			logger.setLevel( Level.FINEST );
			Formatter f = new SimpleFormatter() 
			{
				@Override
				public synchronized String format(LogRecord record) 
				{
					if (record.getLevel().intValue()<Level.CONFIG.intValue())
						return "\t\t(" + record.getLevel() + ") " + record.getMessage() + "\n";
					if (record.getLevel().intValue()<Level.WARNING.intValue())
						return "\t(" + record.getLevel() + ") " + record.getMessage() + "\n";
					return "(" + record.getLevel() + ") " + record.getMessage() + "\n";
				}
			};
			FileOutputStream fLog = new FileOutputStream( "Solecillo"+".log" , ANYADIR_A_FIC_LOG );
			Handler h = new StreamHandler( fLog, f );
			h.setLevel( Level.FINEST );
			logger.addHandler( h );
		} 
		catch (SecurityException | IOException e) 
		{
			logger.log( Level.SEVERE, "No se ha podido crear fichero de log en clase "+ clsAltaEolica.class.getName() );
		}
		logger.log( Level.INFO, "" );
		logger.log( Level.INFO, DateFormat.getDateTimeInstance( DateFormat.LONG, DateFormat.LONG ).format( new Date() ) );
	}
	private static final long serialVersionUID = 1L;
	IGestor ges;
	
	public void setI(IGestor cal) {
		this.ges = cal;
	}
	public double media(ArrayList<clsMaquina> lista){
		return ges.valor_medio_maquinas2(lista);
	}
	
	/**
	 * Método que recoge la lista de usuarios registrados en la Base de Datos.
	 * @return Lista de usuarios registrados
	 */
	public ArrayList<clsUsuario> ListaUsuarios()	
	{	
		logger.log( Level.INFO, "Llamando al método de listado de usuarios");
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
	
	/**
	 * Método que recoge la lista de máquinas eólicas registradas en la Base de Datos.
	 * @return Lista de máquinas eólicas
	 */
	public ArrayList<clsMaquina_Eolica> ListaEolica()	
	{	
		logger.log( Level.INFO, "Llamando al método de listado de máquinas eólicas");
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
	
	/**
	 * Método que para conseguir una máquina eólica de la Base de Datos.
	 * @param id e la máquina de la que se quiere lograr información.
	 * @return La máquina eólica
	 */
	public clsMaquina_Eolica ObtenerEolica(int id)	
	{	
		logger.log( Level.INFO, "Llamando al método de obtener una máquina eólica");
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
	
	/**
	 * Método que recoge la lista de máquinas hidráulicas registradas en la Base de Datos.
	 * @return Lista de máquinas hidráulicas
	 */
	public ArrayList<clsMaquina_Hidraulica> ListaHidraulica()	
	{	
		logger.log( Level.INFO, "Llamando al método de listado de máquinas hidráulicas");
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
	
	/**
	 * Método que para conseguir una máquina hidráulica de la Base de Datos.
	 * @param id e la máquina de la que se quiere lograr información.
	 * @return La máquina hidráulica
	 */
	public clsMaquina_Hidraulica ObtenerHidraulica(int id)	
	{	
		logger.log( Level.INFO, "Llamando al método de obtener una máquina hidráulica");
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
	public ArrayList<clsCliente> top5_clientes() {
		// TODO Auto-generated method stub
		return ges.top5();
	}
	/**
	 * Método que recoge la lista de máquinas mareomotrices registradas en la Base de Datos.
	 * @return Lista de máquinas mareomotrices
	 */
	public ArrayList<clsMaquina_Mareomotriz> ListaMareomotriz()	
	{	
		logger.log( Level.INFO, "Llamando al método de listado de máquinas mareomotrices");
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
	
	/**
	 * Método que para conseguir una máquina mareomotriz de la Base de Datos.
	 * @param id e la máquina de la que se quiere lograr información.
	 * @return La máquina mareomotriz
	 */
	public clsMaquina_Mareomotriz ObtenerMareomotriz(int id)	
	{	
		logger.log( Level.INFO, "Llamando al método de obtener una máquina mareomotriz");
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
	
	/**
	 * Método que recoge la lista de máquinas solares registradas en la Base de Datos.
	 * @return Lista de máquinas solares
	 */
	public ArrayList<clsMaquina_Solar> ListaSolar()	
	{	
		logger.log( Level.INFO, "Llamando al método de listado de máquinas solares");
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
	
	/**
	 * Método que para conseguir una máquina solar de la Base de Datos.
	 * @param id e la máquina de la que se quiere lograr información.
	 * @return La máquina solar
	 */
	public clsMaquina_Solar ObtenerSolar(int id)	
	{	
		logger.log( Level.INFO, "Llamando al método de obtener una máquina solar");
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
	
	/**
	 * Método que recoge la lista de clientes registrados en la Base de Datos.
	 * @return Lista de clientes
	 */
	public ArrayList<clsCliente> ListaClientes()	
	{	
		logger.log( Level.INFO, "Llamando al método de listado de clientes");
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
	
	/**
	 * Método que recoge la lista de ventas registradas en la Base de Datos.
	 * @return Lista de ventas
	 */
	public ArrayList<clsVenta> ListaVentas()	
	{	
		logger.log( Level.INFO, "Llamando al método de listado de ventas");
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
	 * @param todos los atributos de un usuario
	 * @throws clsUsuarioRepetido Excepción indicando que ha sucedido una repetición de nickname
	 */
	public void CrearUsuario(String n, String ap1, String ap2, String nick, String cont) throws clsUsuarioRepetido
	{
		logger.log( Level.INFO, "Llamando al método de crear nuevo usuario");
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
	
	/**
	 * Envía una serie de atributos para crear un nuevo cliente. En caso de que hubiera alguna repetición de DNI, saltará una excepción que impida
	 * que se lleve a cabo dicho registro.
	 * @param todos los atributos de un cliente
	 * @throws clsClienteRepetido Excepción indicando que ha sucedido una repetición de DNI
	 */
	public void CrearCliente(String n, String ap1, String ap2, String dni, String empresa) throws clsClienteRepetido
	{
		logger.log( Level.INFO, "Llamando al método de crear nuevo cliente");
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
	
	/**
	 * Envía una serie de atributos para crear un nueva máquina eólica.
	 * @param todos los atributos de una máquina eólica.
	 */
	public void CrearMaquinaEolica(String n, String col, double val, String fab, String e, String np, String nc, double a, double d)
	{
		logger.log( Level.INFO, "Llamando al método de crear nueva máquina eólica");
		clsMaquina_Eolica nuevo=new clsMaquina_Eolica(n, col, val, fab, e, np, nc, a, d);
		clsBD.insertarDatoTablaBD(nuevo);
	}
	
	/**
	 * Envía una serie de atributos para crear un nueva máquina hidráulica.
	 * @param todos los atributos de una máquina hidráulica.
	 */
	public void CrearMaquinaHidraulica(String n, String col, double val, String fab, String e, String np, String nr)
	{
		logger.log( Level.INFO, "Llamando al método de crear nueva máquina hidráulica");
		clsMaquina_Hidraulica nuevo=new clsMaquina_Hidraulica(n, col, val, fab, e, np, nr);
		clsBD.insertarDatoTablaBD(nuevo);
	}
	
	/**
	 * Envía una serie de atributos para crear un nueva máquina mareomotriz.
	 * @param todos los atributos de una máquina mareomotriz.
	 */
	public void CrearMaquinaMareomotriz(String n, String col, double val, String fab, String e, String np, double distancia)
	{
		logger.log( Level.INFO, "Llamando al método de crear nueva máquina mareomotriz");
		clsMaquina_Mareomotriz nuevo=new clsMaquina_Mareomotriz(n, col, val, fab, e, np, distancia);
		clsBD.insertarDatoTablaBD(nuevo);
	}
	
	/**
	 * Envía una serie de atributos para crear un nueva máquina solar.
	 * @param todos los atributos de una máquina solar.
	 */
	public void CrearMaquinaSolar(String n, String col, double val, String fab, String e, String np, String nc)
	{
		logger.log( Level.INFO, "Llamando al método de crear nueva máquina solar");
		clsMaquina_Solar nuevo=new clsMaquina_Solar(n, col, val, fab, e, np, nc);
		clsBD.insertarDatoTablaBD(nuevo);
	}
	
	/**
	 * Envía una serie de atributos para crear un nueva venta.
	 * @param todos los atributos de una venta.
	 */
	public void CrearVenta(int idm, String dniC, int can)
	{
		logger.log( Level.INFO, "Llamando al método de crear nueva venta");
		clsVenta nuevo=new clsVenta(idm, dniC, can);
		clsBD.insertarDatoTablaBD(nuevo);
	}
	
	/**
	 * Método para modificar un usuario existente en la Base de Datos.
	 * @param todos los atributos de un usuario
	 * @return Usuario modificado para actualizar la ventana clsEleccion
	 */
	public clsUsuario ModificarUsuario (String n, String ap1, String ap2, String nick, String cont)
	{
		logger.log( Level.INFO, "Llamando al método de modificar usuario");
		clsUsuario modificado=new clsUsuario(n, ap1, ap2, nick, cont);
		clsBD.modificarUsuario(modificado);
		return modificado;
	}

	
	/**
	 * Método para borrar una máquina.
	 * @param id de la máquina a borrar
	 */
	public void BorrarMaquina(int id)
	{
		logger.log( Level.INFO, "Llamando al método de borrar una máquina");
		clsBD.BorrarMaquina(id);
	}
	
	/**
	 * Método para borrar todos los usuarios.
	 */
	public void BorrarUsuarios()
	{
		logger.log( Level.INFO, "Llamando al método de borrar usuarios");
		clsBD.BorrarUsuarios();
	}
	
	/**
	 * Método para borrar todos los clientes.
	 */
	public void BorrarClientes()
	{
		logger.log( Level.INFO, "Llamando al método de borrar clientes");
		clsBD.BorrarClientes();
	}
	
	/**
	 * Método para borrar todas las máquinas.
	 */
	public void BorrarMaquinas()
	{
		logger.log( Level.INFO, "Llamando al método de borrar máquinas");
		clsBD.BorrarMaquinas();
	}
	
	/**
	 * Método para borrar todas las ventas.
	 */
	public void BorrarVentas()
	{
		logger.log( Level.INFO, "Llamando al método de borrar ventas");
		clsBD.BorrarVentas();
	}
	
	/**
	 * Método para modificar una máquina eólica.
	 * @param todos los atributos de una máquina eólica
	 */
	public void ModificarMaquinaEolica(int id, String n, String col, double val, String fab, String e, String np, String nc, double a, double d)
	{
		logger.log( Level.INFO, "Llamando al método de modificar una máquina eólica");
		clsBD.modificarEolica(id, n, col, val, fab, e, np, nc, a, d);
	}
	
	/**
	 * Método para modificar una máquina hidráulica.
	 * @param todos los atributos de una máquina hidráulica
	 */
	public void ModificarMaquinaHidraulica(int id, String n, String col, double val, String fab, String e, String np, String nr)
	{
		logger.log( Level.INFO, "Llamando al método de modificar una máquina hidráulica");
		clsBD.modificarHidraulica(id, n, col, val, fab, e, np, nr);
	}
	
	/**
	 * Método para modificar una máquina mareomotriz.
	 * @param todos los atributos de una máquina mareomotriz
	 */
	public void ModificarMaquinaMareomotriz(int id, String n, String col, double val, String fab, String e, String np, double d)
	{
		logger.log( Level.INFO, "Llamando al método de modificar una máquina mareomotriz");
		clsBD.modificarMareomotriz(id, n, col, val, fab, e, np, d);
	}
	
	/**
	 * Método para modificar una máquina solar.
	 * @param todos los atributos de una máquina solar
	 */
	public void ModificarMaquinaSolar(int id, String n, String col, double val, String fab, String e, String np, String nc)
	{
		logger.log( Level.INFO, "Llamando al método de modificar una máquina solar");
		clsBD.modificarSolar(id, n, col, val, fab, e, np, nc);
	}
	
	/**
	 * Método para modificar una migrar usuarios.
	 */
	public void MigracionUsuarios() throws clsUsuarioRepetido
	{
		logger.log( Level.INFO, "Llamando al método de migrar usuarios");
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
	
	/**
	 * Método para modificar una migrar clientes.
	 */
	public void MigracionClientes() throws clsClienteRepetido
	{
		logger.log( Level.INFO, "Llamando al método de migrar clientes");
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
	
	/**
	 * Método para modificar una migrar máquinas.
	 */
	public void MigracionMaquinas()
	{
		logger.log( Level.INFO, "Llamando al método de migrar máquinas");
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
		double min_a=100;
		double max_a=150;
		double min_d=25;
		double max_d=80;
		double min_dm=1;
		double max_dm=15;
		logger.log( Level.INFO, "Empezando migración de máquinas eólicas");
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
		logger.log( Level.INFO, "Empezando migración de máquinas hidráulicas");
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
		logger.log( Level.INFO, "Empezando migración de máquinas mareomotrices");
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
		logger.log( Level.INFO, "Empezando migración de máquinas solares");
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
	
	/**
	 * Método para modificar una migrar ventas.
	 */
	public void MigracionVentas()
	{
		logger.log( Level.INFO, "Llamando al método de migrar ventas");
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
	
	/**
	 * Método para saber los 5 clientes que más han comprado
	 * @return lista de clientes
	 */
	public ArrayList<clsCliente> top_5_clientes()
	{
		logger.log( Level.INFO, "Llamando al método de lograr mejores clientes");
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
	
	/**
	 * Método para saber el valor medio de las máquinas según su tipo
	 * @return lista de máquinas
	 */
	public ArrayList<clsMaquina> valor_medio_maquinas()
	{
		logger.log( Level.INFO, "Llamando al método de valor medio de las máquinas");
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
	
	public double valor_medio_maquinas3(ArrayList<clsMaquina> lista2)
	{
		ArrayList<clsMaquina> lista = lista2;
		double m=0;
		int i;
		for (i=0;i<lista2.size();i++)
		{
			m=m+lista2.get(i).getValor();
		}
		
		double t=m/i;
		return t;
	}
	

	/**
	 * Método para saber el nº de ventas por tipo de máquina
	 * @return lista de máquinas
	 */

	public ArrayList<clsMaquina> ventas_tipo_maquina()
	{
		logger.log( Level.INFO, "Llamando al método de ventas por tipo de máquina");
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
	
	/**
	 * Método para saber el valor medio de las máquinas por estado
	 * @return lista de máquinas
	 */
	public ArrayList<clsMaquina> valor_estado_maquina()
	{
		logger.log( Level.INFO, "Llamando al método de valor según estado de las máquinas");
		ArrayList<clsMaquina> lista = new ArrayList <clsMaquina>();
		ResultSet rs = clsBD.valor_estado_maquina();
		if (rs != null)
		{
			try 
			{
				while (rs.next())
				{
					lista.add(new clsMaquina(
							rs.getString("ESTADO"),
							rs.getDouble("VALOR_MEDIO")));
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
	 * Método para saber el nº de máquinas que tiene cada fabricante en cada río.
	 * @return lista de máquinas hidráulicas
	 */
	public ArrayList<clsMaquina_Hidraulica> num_maquinas_rio_fabricante()
	{
		logger.log( Level.INFO, "Llamando al método de lograr número de máquinas por río-fabricante");
		ArrayList<clsMaquina_Hidraulica> lista = new ArrayList <clsMaquina_Hidraulica>();
		ResultSet rs = clsBD.num_maquinas_rio_fabricante();
		if (rs != null)
		{
			try 
			{
				while (rs.next())
				{
					lista.add(new clsMaquina_Hidraulica(
							rs.getString("NOMBRE_RIO"),
							rs.getString("FABRICANTE"),
							rs.getInt("TOTAL_MAQUINAS")));
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
	 * Método para saber el las medidad de las máquinas eólicas por pueblo
	 * @return lista de máquinas eólicas
	 */
	public ArrayList<clsMaquina_Eolica> eolicas_pueblo_media_altura_diametro()
	{
		logger.log( Level.INFO, "Llamando al método de lograr medidas medias de las eólicas");
		ArrayList<clsMaquina_Eolica> lista = new ArrayList <clsMaquina_Eolica>();
		ResultSet rs = clsBD.eolicas_pueblo_media_altura_diametro();
		if (rs != null)
		{
			try 
			{
				while (rs.next())
				{
					lista.add(new clsMaquina_Eolica(
							rs.getString("NOMBRE_PUEBLO"),
							rs.getDouble("MEDIA_ALTURA"),
							rs.getDouble("MEDIA_DIAMETRO")));
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
