package LP;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import Comun.clsConstantes;
import LN.clsUsuario;

/**
 * Clase que generará una JFrame que visualizará una barra de progreso cuando se inicie la aplicación.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Josune Ordoñez (Josune07)
 */
public class ProgressBar extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	MiRunnable miHilo = null; 
	JProgressBar progressBar;
	clsUsuario usuario;
	int v1;
	int v2;
	int v3;
	int v4;
	int v5;
	
	int e1;
	int e2;
	int e3;
	int e4;
	int e5;
	String ventana_a_abrir;
	
	boolean sigo = true;
	
	private static final boolean ANYADIR_A_FIC_LOG = true;
	
	/*Logger de la clase*/
	private static Logger logger = Logger.getLogger( "Mariano" );
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
			FileOutputStream fLog = new FileOutputStream( "Mariano"+".log" , ANYADIR_A_FIC_LOG );
			Handler h = new StreamHandler( fLog, f );
			h.setLevel( Level.FINEST );
			logger.addHandler( h );
		} 
		catch (SecurityException | IOException e) 
		{
			logger.log( Level.SEVERE, "No se ha podido crear fichero de log en clase "+ ProgressBar.class.getName() );
		}
		logger.log( Level.INFO, "" );
		logger.log( Level.INFO, DateFormat.getDateTimeInstance( DateFormat.LONG, DateFormat.LONG ).format( new Date() ) );
	}
	/**
	 * Constructor de la JFrame.
	 * @param titulo Titulo de la ventana.
	 * @param v Para saber si es un usuario o el adminstrador
	 * @param usu El usuario en cuestión.
	 */
	public ProgressBar(String titulo, String v, clsUsuario usu) {
		
		setBounds(450, 300, 600, 140);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
		
		setTitle(titulo);
		progressBar = new JProgressBar();
		progressBar.setBounds(50, 30, 300, 35);
		getContentPane().add(progressBar);
		
		Random rnd_v=new Random();
		v1=rnd_v.nextInt(100);
		v2=rnd_v.nextInt(100);
		v3=rnd_v.nextInt(100);
		v4=rnd_v.nextInt(100);
		v5=rnd_v.nextInt(100);

		e1=rnd_v.nextInt(400);
		e2=rnd_v.nextInt(400);
		e3=rnd_v.nextInt(400);
		e4=rnd_v.nextInt(400);
		e5=rnd_v.nextInt(400);

		progressBar.setBackground(Color.black);
		progressBar.setForeground(Color.blue);
		
		ventana_a_abrir=v;
		usuario=usu;
		
		miHilo = new MiRunnable();
		Thread nuevoHilo = new Thread(miHilo);
		nuevoHilo.start();
		
		addWindowListener( new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				logger.log(Level.INFO, "Volviendo al menu principal");
				nuevoHilo.stop();
				dispose();
				loginFrame frame = new loginFrame();
				frame.setVisible(true);
			}
		});	
	}
	/**
	 * Método que cierra la ProgressBar y carga la nueva ventana, dependiendo de cuál sea la que deba mostrar.
	 */
	public void cerrar()
	{
		this.dispose();
		if(ventana_a_abrir.equals(clsConstantes.PRINCIPAL))
		{
			logger.log( Level.INFO, "Cargando ventana de usuario");
			principalFrame window = new principalFrame(usuario);
			window.setVisible(true);
		}
		else if(ventana_a_abrir.equals(clsConstantes.ADMIN))
		{
			logger.log( Level.INFO, "Cargando ventana de administrador");
			principalFrame window = new principalFrame(usuario);
			window.setVisible(true);
		}
		
	}
	/**
	 * Clase interna que implementa la interfaz Runnable que repinta la ProgressBar.
	 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Josune Ordoñez (Josune07)
	 */
		class MiRunnable implements Runnable 
		{
			
			@Override
			public void run() 
			{
				while (sigo) 
				{
					for(int i=0;i<=100;i++)
					{
						progressBar.setStringPainted(true);
						progressBar.setString(i + "%");
						progressBar.setValue(i);				
						if(i==v1)
						{
							try 
							{
								Thread.sleep(e1);
							} 
							catch (InterruptedException e1) 
							{
									e1.printStackTrace();
							}
						}							
						if(i==v2)
						{
							try 
							{
								Thread.sleep(e2);
							} 
							catch (InterruptedException e1) 
							{
								e1.printStackTrace();
							}
						}						
						if(i==v3)
						{
							try 
							{
								Thread.sleep(e3);
							} 
							catch (InterruptedException e1) 
							{
								e1.printStackTrace();
							}
						}							
						if(i==v4)
						{
							try 
							{
								Thread.sleep(e4);
							} 
							catch (InterruptedException e1) 
							{
								e1.printStackTrace();
							}
						}							
						if(i==v5)
						{
							try 
							{
								Thread.sleep(e5);
							} 
							catch (InterruptedException e1) 
							{
								e1.printStackTrace();
							}
						}						
						try 
						{
							Thread.sleep(15);
						} 
						catch (InterruptedException e) 
						{
							e.printStackTrace();
						}
					}					
					sigo=false;
					cerrar();
			}
		}
	}
}