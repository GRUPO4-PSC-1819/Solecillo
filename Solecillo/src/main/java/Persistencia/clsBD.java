package Persistencia;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import javax.swing.JOptionPane;
import LN.clsCliente;
import LN.clsMaquina_Eolica;
import LN.clsMaquina_Hidraulica;
import LN.clsMaquina_Mareomotriz;
import LN.clsMaquina_Solar;
import LN.clsUsuario;
import LN.clsVenta;
import LP.clsAltaEolica;
/**
 * Clase que se encargará de pasar la información de memoria a una Base de Datos, y al mismo tiempo, que extraerá
 * dicha información de esta BD a memoria.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Josune Ordoñez (Josune07)
 */
public class clsBD 
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
	private static Connection connection = null;
	private static Statement statement = null;

	/** Inicializa una BD SQLITE y devuelve una conexión con ella. Debe llamarse a este 
	 * método antes que ningún otro, y debe devolver no null para poder seguir trabajando con la BD.
	 * @param nombreBD	Nombre de fichero de la base de datos
	 * @return Conexión con la base de datos indicada. Si hay algún error, se devuelve null
	 */
	public static Connection initBD( String nombreBD ) 
	{
		logger.log( Level.INFO, "Iniciando la Base de Datos "+nombreBD);
		try 
		{
		    Class.forName("org.sqlite.JDBC");
		    connection = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
			statement = connection.createStatement();
			statement.setQueryTimeout(30);  // poner timeout 30 msg
		    return connection;
		} 
		catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog( null, "Error de conexión!! No se ha podido conectar con " + nombreBD , "ERROR", JOptionPane.ERROR_MESSAGE );
			return null;
		}
	}
	
	/** Cierra la conexión con la Base de Datos
	 */
	public static void close() {
		logger.log( Level.INFO, "Cerrando la Base de Datos");
		try 
		{
			statement.close();
			connection.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	/** Devuelve la conexión si ha sido establecida previamente (#initBD()).
	 * @return	Conexión con la BD, null si no se ha establecido correctamente.
	 */
	public static Connection getConnection() {
		return connection;
	}
	
	/** Devuelve una sentencia para trabajar con la BD,
	 * si la conexión si ha sido establecida previamente (#initBD()).
	 * @return	Sentencia de trabajo con la BD, null si no se ha establecido correctamente.
	 */
	public static Statement getStatement() {
		return statement;
	}

	/** Crea diferentes tablas en una base de datos, si no existía ya. <br>
	 * Debe haberse inicializado la conexión correctamente. <br>
	 * @param tipo_tabla Tipo de tabla: Usuario, Venta, Máquina o Cliente.
	 */
	public static void crearTablaBD(String tipo_tabla)
	{
		logger.log( Level.INFO, "Creando en Base de Datos la tabla "+tipo_tabla);
		if (statement==null) return;
		switch (tipo_tabla)
		{
		case "USUARIO": 
			try 
			{
				statement.executeUpdate("CREATE TABLE IF NOT EXISTS USUARIO (NICKNAME STRING NOT NULL PRIMARY KEY,"+
										"CONTRASENYA STRING NOT NULL, "+ 
										"NOMBRE STRING, APELLIDO1 STRING, APELLIDO2 STRING)");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			break;
		case "VENTA": 
			try 
			{
				statement.executeUpdate("CREATE TABLE IF NOT EXISTS VENTA (ID INTEGER PRIMARY KEY,"+
										"DNIC STRING NOT NULL, "+ 
										"IDM INT, CANTIDAD INT)");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			break;
		case "MAQUINA": 
			try 
			{
				statement.executeUpdate("CREATE TABLE IF NOT EXISTS MAQUINA (ID INTEGER PRIMARY KEY,"+
										"NOMBRE STRING, "+ 
										"COLOR STRING, "+
										"VALOR DOUBLE, "+
										"FABRICANTE STRING, "+
										"TIPO CHAR, "+
										"ESTADO STRING, "+
										"NOMBRE_PUEBLO STRING, "+
										"NOMBRE_CAMPO STRING, "+
										"ALTURA DOUBLE, "+
										"DIAMETRO DOUBLE, "+
										"NOMBRE_RIO STRING, "+
										"DISTANCIA_MILLAS DOUBLE)");
			} 
			catch (SQLException e2) 
			{
				e2.printStackTrace();
			}
			break;	
		case "CLIENTE": 
			try 
			{
				statement.executeUpdate("CREATE TABLE IF NOT EXISTS  CLIENTE (NOMBRE STRING,"+ "AP_1 STRING, "+"AP_2 STRING,"+
										"DNI STRING NOT NULL PRIMARY KEY,"+ 
										"EMPRESA STRING)");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			break;			
		}
	}
	
	/** Inserta un dato en las tablas previamente mencionadas. <br>
	 * Debe haberse inicializado la conexión correctamente. <br>
	 * @param obj Objeto a insertar.
	 */
	public static void insertarDatoTablaBD(Object obj)
	{
		if (statement==null)return;
		if (obj instanceof clsUsuario)
			try 
			{
				logger.log( Level.INFO, "Insertando usuario con nickname: "+((clsUsuario)obj).getNickname());
				statement.executeUpdate("INSERT INTO USUARIO VALUES ('"+((clsUsuario)obj).getNickname()+"','"
						+ ((clsUsuario)obj).getContraseña()+"','"
						+((clsUsuario)obj).getNombre()+"','"
						+ ((clsUsuario)obj).getApellido1()+"','"+((clsUsuario)obj).getApellido2()+"')");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		else if (obj instanceof clsMaquina_Eolica)
		{
			try 
			{
				logger.log( Level.INFO, "Insertando máquina eólica");
				statement.executeUpdate("INSERT INTO MAQUINA (NOMBRE, COLOR, VALOR, FABRICANTE, TIPO, ESTADO, NOMBRE_PUEBLO, "
						+"NOMBRE_CAMPO, ALTURA, DIAMETRO, NOMBRE_RIO, DISTANCIA_MILLAS) VALUES "
						+"('"+((clsMaquina_Eolica)obj).getNombre()+"','"
						+ ((clsMaquina_Eolica)obj).getColor()+"',"
						+((clsMaquina_Eolica)obj).getValor()+",'"
						+((clsMaquina_Eolica)obj).getFabricante()+"',"
						+"'E','"
						+((clsMaquina_Eolica)obj).getEstado()+"','"
						+((clsMaquina_Eolica)obj).getNombre_pueblo()+"','"
						+((clsMaquina_Eolica)obj).getNombre_campo()+"',"
						+((clsMaquina_Eolica)obj).getAltura()+","
						+((clsMaquina_Eolica)obj).getDiametro()+","
						+"'',"
						+"0.0)");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
		else if (obj instanceof clsMaquina_Hidraulica)
		{
			try 
			{
				logger.log( Level.INFO, "Insertando máquina hidráulica");
				statement.executeUpdate("INSERT INTO MAQUINA (NOMBRE, COLOR, VALOR, FABRICANTE, TIPO, ESTADO, NOMBRE_PUEBLO, "
						+"NOMBRE_CAMPO, ALTURA, DIAMETRO, NOMBRE_RIO, DISTANCIA_MILLAS) VALUES "
						+"('"+((clsMaquina_Hidraulica)obj).getNombre()+"','"
						+ ((clsMaquina_Hidraulica)obj).getColor()+"',"
						+((clsMaquina_Hidraulica)obj).getValor()+",'"
						+((clsMaquina_Hidraulica)obj).getFabricante()+"',"
						+"'H','"
						+((clsMaquina_Hidraulica)obj).getEstado()+"','"
						+((clsMaquina_Hidraulica)obj).getNombre_pueblo()+"',"
						+"'',"
						+"0.0,"
						+"0.0,'"
						+((clsMaquina_Hidraulica)obj).getNombre_rio()+"',"
						+"0.0)");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
		else if (obj instanceof clsMaquina_Mareomotriz)
		{
			try 
			{
				logger.log( Level.INFO, "Insertando máquina mareomotriz");
				statement.executeUpdate("INSERT INTO MAQUINA (NOMBRE, COLOR, VALOR, FABRICANTE, TIPO, ESTADO, NOMBRE_PUEBLO, "
						+"NOMBRE_CAMPO, ALTURA, DIAMETRO, NOMBRE_RIO, DISTANCIA_MILLAS) VALUES "
						+"('"+((clsMaquina_Mareomotriz)obj).getNombre()+"','"
						+ ((clsMaquina_Mareomotriz)obj).getColor()+"',"
						+((clsMaquina_Mareomotriz)obj).getValor()+",'"
						+((clsMaquina_Mareomotriz)obj).getFabricante()+"',"
						+"'M','"
						+((clsMaquina_Mareomotriz)obj).getEstado()+"','"
						+((clsMaquina_Mareomotriz)obj).getNombre_pueblo()+"',"
						+"'',"
						+"0.0,"
						+"0.0,"
						+"'','"
						+((clsMaquina_Mareomotriz)obj).getDistancia_millas_marinas_pueblo()+"')");
			}  
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
		else if (obj instanceof clsMaquina_Solar)
		{
			try 
			{
				logger.log( Level.INFO, "Insertando máquina solar");
				statement.executeUpdate("INSERT INTO MAQUINA (NOMBRE, COLOR, VALOR, FABRICANTE, TIPO, ESTADO, NOMBRE_PUEBLO, "
						+"NOMBRE_CAMPO, ALTURA, DIAMETRO, NOMBRE_RIO, DISTANCIA_MILLAS) VALUES "
						+"('"+((clsMaquina_Solar)obj).getNombre()+"','"
						+ ((clsMaquina_Solar)obj).getColor()+"',"
						+((clsMaquina_Solar)obj).getValor()+",'"
						+((clsMaquina_Solar)obj).getFabricante()+"',"
						+"'S','"
						+((clsMaquina_Solar)obj).getEstado()+"','"
						+((clsMaquina_Solar)obj).getNombre_pueblo()+"','"
						+((clsMaquina_Solar)obj).getNombre_campo()+"',"
						+"0.0,"
						+"0.0,"
						+"'',"
						+"0.0)");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
		else if (obj instanceof clsCliente)
		{
			try 
			{
				logger.log( Level.INFO, "Insertando cliente con DNI: "+((clsCliente)obj).getdni());
				statement.executeUpdate("INSERT INTO CLIENTE VALUES ('"+((clsCliente)obj).getNombre()+"','"
						+ ((clsCliente)obj).getApellido1()+"','"
						+((clsCliente)obj).getApellido2()+"','"
						+ ((clsCliente)obj).getdni()+"','"+((clsCliente)obj).getEmpresa()+"')");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
		else if (obj instanceof clsVenta)
		{
			try 
			{
				logger.log( Level.INFO, "Insertando venta");
				statement.executeUpdate("INSERT INTO VENTA (DNIC, IDM, CANTIDAD) VALUES ('"
						+ ((clsVenta)obj).getDniC()+"',"
						+((clsVenta)obj).getIdm()+","
						+ ((clsVenta)obj).getCantidad()+")");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
		
	}
	/**
	 * Lectura de los datos de una tabla determinada.
	 * @param tipo_tabla Tipo de tabla
	 * @return Representación abstracta de los datos de la tabla de la BD: ResultSet
	 */
	public static ResultSet obtenerDatosTablaBD (String tipo_tabla)
	{
		if (statement==null) return null;
		ResultSet rs = null;
		switch (tipo_tabla)
		{
		case "USUARIO": 
			try 
			{
				logger.log( Level.INFO, "Obteniendo todos los datos de la tabla "+tipo_tabla);
			    rs = statement.executeQuery("select * from USUARIO");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			break;
			
		case "MAQUINA_E": 
			try 
			{
				logger.log( Level.INFO, "Obteniendo todos los datos de máquinas eólicas");
			    rs = statement.executeQuery("select * from MAQUINA where tipo='E'");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			break;
		
		case "MAQUINA_H": 
			try 
			{
				logger.log( Level.INFO, "Obteniendo todos los datos de máquinas hidráulicas");
			    rs = statement.executeQuery("select * from MAQUINA where tipo='H'");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			break;
			
		case "MAQUINA_M": 
			try 
			{
				logger.log( Level.INFO, "Obteniendo todos los datos de máquinas mareomotrices");
			    rs = statement.executeQuery("select * from MAQUINA where tipo='M'");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			break;
			
		case "MAQUINA_S": 
			try 
			{
				logger.log( Level.INFO, "Obteniendo todos los datos de máquinas solares");
			    rs = statement.executeQuery("select * from MAQUINA where tipo='S'");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			break;
				
		case "CLIENTE":
			try 
			{
				logger.log( Level.INFO, "Obteniendo todos los datos de la tabla "+tipo_tabla);
			    rs = statement.executeQuery("SELECT * from CLIENTE");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			break;
		case "VENTA":
			try 
			{
				logger.log( Level.INFO, "Obteniendo todos los datos de la tabla "+tipo_tabla);
			    rs = statement.executeQuery("SELECT * from VENTA");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			break;
	}
		 return rs;
	}
	
	/**
	 * Lectura de los datos de una sola máquina.
	 * @param id El id de la máquina que se quiere buscar información.
	 * @return Representación abstracta de los datos de la tabla de la BD: ResultSet
	 */
	public static ResultSet ObtenerUnaMaquina(int id)
	{
		if (statement==null) return null;
		ResultSet rs = null;
			try 
			{
				logger.log( Level.INFO, "Obteniendo todos los datos de la máquina con ID: "+id);
			    rs = statement.executeQuery("select * from MAQUINA where id="+id);
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		return rs;
	}
	
	/**
	 * Query para saber quiénes son los mejores clientes.
	 * @return ResultSet con el dni del cliente junto con el total de las ventas que ha realizado.
	 */
	public static ResultSet clientesTOP()
	{
		if (statement==null) return null;
		ResultSet rs = null;
			try 
			{
				logger.log( Level.INFO, "Obteniendo datos de los clientes top");
			    rs = statement.executeQuery("SELECT dnic, sum(cantidad) as TOTAL_VENTAS FROM venta GROUP BY dnic ORDER BY sum(cantidad) DESC LIMIT 5");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		return rs;
	}
	
	/**
	 * Query para saber el valor medio de las máquinas según su tipo.
	 * @return ResultSet con el tipo de máquina junto con el valor medio de las máquinas de ese tipo.
	 */
	public static ResultSet valor_medio_maquinas()
	{
		if (statement==null) return null;
		ResultSet rs = null;
			try 
			{
				logger.log( Level.INFO, "Obteniendo datos del valor medio de las máquinas");
			    rs = statement.executeQuery("SELECT tipo, avg(valor) AS MEDIA_VALOR FROM maquina GROUP BY tipo");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		return rs;
	}
	
	/**
	 * Query para saber el nº de ventas por tipo de máquina.
	 * @return ResultSet con el el tipo de las máquinas junto con el nº de ventas total.
	 */
	public static ResultSet ventas_tipo_maquina()
	{
		if (statement==null) return null;
		ResultSet rs = null;
			try 
			{
				logger.log( Level.INFO, "Obteniendo datos del ventas por tipo de máquina");
			    rs = statement.executeQuery("SELECT m.tipo, sum(v.cantidad) AS TOTAL_VENTAS FROM venta v INNER JOIN maquina m ON v.idm=m.id GROUP BY m.tipo");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		return rs;
	}
	
	/**
	 * Query para saber el valor medio de las máquinas según su estado.
	 * @return ResultSet con el el estado de las máquinas junto con el valor medio de las máquinas de ese estado.
	 */
	public static ResultSet valor_estado_maquina()
	{
		if (statement==null) return null;
		ResultSet rs = null;
			try 
			{
				logger.log( Level.INFO, "Obteniendo datos del valor según el estado de las máquinas");
			    rs = statement.executeQuery("SELECT estado, avg(valor) AS VALOR_MEDIO FROM maquina GROUP BY estado");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		return rs;
	}
	
	/**
	 * Query para saber cuántas máquinas tiene cada fabricante en cada río.
	 * @return ResultSet con el nombre de los ríos, fabricantes y nº total de máquinas.
	 */
	public static ResultSet num_maquinas_rio_fabricante()
	{
		if (statement==null) return null;
		ResultSet rs = null;
			try 
			{
				logger.log( Level.INFO, "Obteniendo datos número de máquinas que tiene cada fabricante en cada río");
			    rs = statement.executeQuery("SELECT nombre_rio, fabricante, count(id) AS TOTAL_MAQUINAS FROM maquina WHERE nombre_rio IS NOT '' GROUP BY nombre_rio, fabricante ORDER BY fabricante, nombre_rio");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		return rs;
	}
	
	/**
	 * Query para saber cuántas la altura y diamtero medio de las máquinas eólicas en cada pueblo.
	 * @return ResultSet con el nombre de los pueblos, y la altura y diametro medios de sus máquinas eólicas.
	 */
	public static ResultSet eolicas_pueblo_media_altura_diametro()
	{
		if (statement==null) return null;
		ResultSet rs = null;
			try 
			{
				logger.log( Level.INFO, "Obteniendo datos de medidas media de máquinas eólicas");
			    rs = statement.executeQuery("SELECT nombre_pueblo, avg(altura) AS MEDIA_ALTURA, avg(diametro) AS MEDIA_DIAMETRO FROM maquina WHERE altura>0 GROUP BY nombre_pueblo");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		return rs;
	}
	
	/**
	 * Modifica un usuario, considerando sus atributos identificativos como base: <br>
	 * @param obj Usuario a modificar
	 */
	public static void modificarUsuario(Object obj)
	{
		if (statement==null) return;
		if (obj instanceof clsUsuario)
			try 
			{
				logger.log( Level.INFO, "Modificando en la Base de Datos al usuario con nickname: "+((clsUsuario)obj).getNickname());
				statement.executeUpdate("UPDATE USUARIO SET CONTRASENYA ='"+((clsUsuario)obj).getContraseña()+"',"
						+ "NOMBRE = '"+((clsUsuario)obj).getNombre()+"',"
						+ "APELLIDO1 = '"+((clsUsuario)obj).getApellido1()+"', APELLIDO2 = '"+((clsUsuario)obj).getApellido2()+"'"
						+ "WHERE NICKNAME = '"+((clsUsuario)obj).getNickname()+"'");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
	}
	
	/**
	 * Modifica un máquina eólica, considerando sus atributos identificativos como base: <br>
	 * @param todos los atributos de una máquina eólica
	 */
	public static void modificarEolica(int id, String n, String c, double v, String f, String e, String np, String nc, double a, double d)
	{
		if (statement==null) return;
			try 
			{
				logger.log( Level.INFO, "Modificando en la Base de Datos la máquina eólica con ID: "+id);
				statement.executeUpdate("UPDATE MAQUINA SET NOMBRE ='"+n+"',"
						+ "COLOR = '"+c+"',"
						+ "VALOR = "+v+"," 
						+ "FABRICANTE = '"+f+"',"
						+ "ESTADO = '"+e+"',"
						+ "NOMBRE_PUEBLO = '"+np+"',"
						+ "NOMBRE_CAMPO = '"+nc+"',"
						+ "ALTURA = "+a+","
						+ "DIAMETRO = "+d+" "
						+ "WHERE ID = "+id+"");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
	}
	
	/**
	 * Modifica un máquina hidráulica, considerando sus atributos identificativos como base: <br>
	 * @param todos los atributos de una máquina hidráulica
	 */
	public static void modificarHidraulica(int id, String n, String c, double v, String f, String e, String np, String nr)
	{
		if (statement==null) return;
			try 
			{
				logger.log( Level.INFO, "Modificando en la Base de Datos la máquina hidráulica con ID: "+id);
				statement.executeUpdate("UPDATE MAQUINA SET NOMBRE ='"+n+"',"
						+ "COLOR = '"+c+"',"
						+ "VALOR = "+v+"," 
						+ "FABRICANTE = '"+f+"',"
						+ "ESTADO = '"+e+"',"
						+ "NOMBRE_PUEBLO = '"+np+"',"
						+ "NOMBRE_RIO = '"+nr+"' "
						+ "WHERE ID = "+id+"");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
	}
	
	/**
	 * Modifica un máquina mareomotriz, considerando sus atributos identificativos como base: <br>
	 * @param todos los atributos de una máquina mareomotriz
	 */
	public static void modificarMareomotriz(int id, String n, String c, double v, String f, String e, String np, double d)
	{
		if (statement==null) return;
			try 
			{
				logger.log( Level.INFO, "Modificando en la Base de Datos la máquina mareomotriz con ID: "+id);
				statement.executeUpdate("UPDATE MAQUINA SET NOMBRE ='"+n+"',"
						+ "COLOR = '"+c+"',"
						+ "VALOR = "+v+"," 
						+ "FABRICANTE = '"+f+"',"
						+ "ESTADO = '"+e+"',"
						+ "NOMBRE_PUEBLO = '"+np+"',"
						+ "DISTANCIA_MILLAS = "+d+" "
						+ "WHERE ID = "+id+"");			
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
	}
	
	/**
	 * Modifica un máquina solar, considerando sus atributos identificativos como base: <br>
	 * @param todos los atributos de una máquina solar
	 */
	public static void modificarSolar(int id, String n, String c, double v, String f, String e, String np, String nc)
	{
		if (statement==null) return;
			try 
			{
				logger.log( Level.INFO, "Modificando en la Base de Datos la máquina solar con ID: "+id);
				statement.executeUpdate("UPDATE MAQUINA SET NOMBRE ='"+n+"',"
						+ "COLOR = '"+c+"',"
						+ "VALOR = "+v+"," 
						+ "FABRICANTE = '"+f+"',"
						+ "ESTADO = '"+e+"',"
						+ "NOMBRE_PUEBLO = '"+np+"',"
						+ "NOMBRE_CAMPO = '"+nc+"' "
						+ "WHERE ID = "+id+"");				
			} 				 
			catch (SQLException e1)
			{	
				e1.printStackTrace();
			}
	}
	
	/**
	 * Elimina todos los usuarios de la aplicación
	 */
	public static void BorrarUsuarios()
	{
		if (statement==null) return;
			try 
			{
				logger.log( Level.INFO, "Borrando todos los usuarios de la Base de Datos");
				statement.executeQuery("DELETE FROM USUARIO");
			} 
			catch (SQLException e1) 
			{
				//JOptionPane.showMessageDialog(null, "Se ha borrado la tabla de usuarios.", "Información", JOptionPane.INFORMATION_MESSAGE);
			}
	}
	
	/**
	 * Elimina todos los clientes de la aplicación
	 */
	public static void BorrarClientes()
	{
		if (statement==null) return;
		try 
		{
			logger.log( Level.INFO, "Borrando todos los clientes de la Base de Datos");
			statement.executeQuery("DELETE FROM CLIENTE");
		} 
		catch (SQLException e1) 
		{
			//JOptionPane.showMessageDialog(null, "Se ha borrado la tabla de clientes.", "Información", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Elimina todas las máquinas de la aplicación
	 */
	public static void BorrarMaquinas()
	{
		if (statement==null) return;
			try 
			{
				logger.log( Level.INFO, "Borrando todas las máquinas de la Base de Datos");
				statement.executeQuery("DELETE FROM MAQUINA");
			} 
			catch (SQLException e1) 
			{
				//JOptionPane.showMessageDialog(null, "Se ha borrado la máquina con ID: "+id, "Información", JOptionPane.INFORMATION_MESSAGE);
			}
	}
	
	/**
	 * Elimina todas las ventas de la aplicación
	 */
	public static void BorrarVentas()
	{
		if (statement==null) return;
			try 
			{
				logger.log( Level.INFO, "Borrando todas las ventas de la Base de Datos");
				statement.executeQuery("DELETE FROM VENTA");
			} 
			catch (SQLException e1) 
			{
				//JOptionPane.showMessageDialog(null, "Se ha borrado la máquina con ID: "+id, "Información", JOptionPane.INFORMATION_MESSAGE);
			}
	}
	
	/**
	 * Elimina una máquina de la aplicación
	 * @param id de la máquina a borrar.
	 */
	public static void BorrarMaquina(int id)
	{
		if (statement==null) return;
			try 
			{
				logger.log( Level.INFO, "Borrando de la Base de Datos la máquina con ID: "+id);
				statement.executeQuery("DELETE FROM MAQUINA WHERE ID="+id);
			} 
			catch (SQLException e1) 
			{
				JOptionPane.showMessageDialog(null, "Se ha borrado la máquina con ID: "+id, "Información", JOptionPane.INFORMATION_MESSAGE);
			}
	}
}