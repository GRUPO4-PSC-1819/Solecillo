package LP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import LN.clsGestor;
import LN.clsUsuario;

/**
 * Clase que generará una ventana que hereda de clsAltaUsuario para modificar los datos de un usuario en la base de datos.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Josune Ordoñez (Josune07)
 */
public class clsModificarUsuario extends clsAltaUsuario
{
	private static final long serialVersionUID = 1L;
	
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
	
	/**
	 * Constructor de la ventana que aprovecha la interfaz gráfica de clsAltaUsuario.
	 * @param usuario El usuario del cual se van a modificar los datos.
	 * @param ventanita La ventana pricipalFrame de la que proviene el usuario.
	 */
	public clsModificarUsuario(clsUsuario usuario, JFrame miVentana)
	{
		setTitle("Modificar usuario");
		txtNombre.setText(usuario.getNombre());
		txtApe1.setText(usuario.getApellido1());
		txtApe2.setText(usuario.getApellido2());
		txtNickname.setText(usuario.getNickname());
		txtNickname.setEditable(false);
		txtContrasenya1.setText(usuario.getContraseña());
		txtContrasenya2.setText(usuario.getContraseña());
		
		modifusu=true;
				
		/*Escuchadores*/
		btnAceptar.addActionListener(new ActionListener() 
		{
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) 
			{
				clsGestor objGestor=new clsGestor();
				if(txtNombre.getText().length()>0&&txtApe1.getText().length()>0&&txtApe2.getText().length()>0&&txtNickname.getText().length()>0&&txtContrasenya1.getText().length()>0&&txtContrasenya2.getText().length()>0)
				{		
					if(txtContrasenya1.getText().equals(txtContrasenya2.getText())==false)
					{
						JOptionPane.showMessageDialog(null, "Introduzca la misma contraseña", "¡Contraseñas diferentes!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						logger.log( Level.INFO, "Modificando usuario de nombre: "+txtNombre.getText());
						objGestor.ModificarUsuario(txtNombre.getText(), txtApe1.getText(), txtApe2.getText(), txtNickname.getText(), txtContrasenya1.getText());
						JOptionPane.showMessageDialog(null, "Ha modificado el usuario correctamente.");
						miVentana.dispose();
						dispose();
						loginFrame p=new loginFrame();
						p.setVisible(true);
					}
				}
			}
		});
		this.btnCancelar.addActionListener(new ActionListener() 
			{
			public void actionPerformed(ActionEvent arg0) 
			{
				dispose();
			}
		});
	}
}