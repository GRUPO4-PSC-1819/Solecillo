package LP;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
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

import javax.swing.JLabel;
import javax.swing.JTextField;

import Comun.clsConstantes;

import javax.swing.JButton;

import LN.clsUsuarioRepetido;
import LN.clsGestor;

/**
 * Clase que generará una JFrame para introducir los datos que a su vez serán enviados a clsBinarios para la creación
 * de un usuario en la base de datos.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Josune Ordoñez (Josune07)
 */
public class clsAltaUsuario extends JFrame 
{
	private static final long serialVersionUID = 1L;

	private JPanel panelprincipal;
	private JPanel panelizda;
	private JPanel panelbotonera;
	private JPanel panelsuperior;	
	
	JTextField txtNombre;
	JTextField txtApe1;
	JTextField txtApe2;
	JTextField txtNickname;
	JPasswordField txtContrasenya1;
	JPasswordField txtContrasenya2;
	
	private JLabel lblNombre;
	private JLabel lblApe1;
	private JLabel lblApe2;
	private JLabel lblNickname;
	private JLabel lblContrasenya1;
	private JLabel lblContrasenya2;
	
	JButton btnAceptar;
	JButton btnCancelar;
	
	private static final boolean ANYADIR_A_FIC_LOG = true;
	
	/*Logger*/
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
			logger.log( Level.SEVERE, "No se ha podido crear fichero de log en clase "+ clsAltaUsuario.class.getName() );
		}
		logger.log( Level.INFO, "" );
		logger.log( Level.INFO, DateFormat.getDateTimeInstance( DateFormat.LONG, DateFormat.LONG ).format( new Date() ) );
	}
		
	/**
	 * Constructor del JFrame que genera la parte visual de la ventana, así como los escuchadores requeridos para mejorar la interacción de la ventana.
	 */
	public clsAltaUsuario() 
	{
		panelprincipal = new JPanel();
		panelizda=new JPanel();
		panelbotonera=new JPanel();
		panelsuperior=new JPanel();
		this.setBounds(200, 200, 800, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panelprincipal.setLayout(new BorderLayout());
		panelizda.setLayout(new BoxLayout(panelizda, BoxLayout.Y_AXIS));
		panelbotonera.setLayout(new FlowLayout());
		panelsuperior.setLayout(new FlowLayout());
		panelprincipal.add(panelizda, BorderLayout.CENTER);
		panelprincipal.add(panelbotonera, BorderLayout.SOUTH);
		panelprincipal.add(panelsuperior, BorderLayout.NORTH);
		setTitle("Alta de usuario");
		panelprincipal.setBackground(Color.ORANGE);
		panelizda.setBackground(Color.ORANGE);
		panelbotonera.setBackground(Color.ORANGE);
		panelsuperior.setBackground(Color.ORANGE);

		lblNombre = new JLabel("        Nombre:           ");
		lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(txtNombre);
		
		lblApe1 = new JLabel("        Apellido 1:        ");
		lblApe1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(lblApe1);
		
		txtApe1 = new JTextField();
		txtApe1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(txtApe1);
		
		lblApe2 = new JLabel("        Apellido 2:        ");
		lblApe2.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(lblApe2);
		
		txtApe2 = new JTextField();
		txtApe2.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(txtApe2);	
		
		lblNickname = new JLabel("         Nickname:         ");
		lblNickname.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(lblNickname);
		
		txtNickname = new JTextField();
		txtNickname.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(txtNickname);
			
		lblContrasenya1 = new JLabel("        Contraseña:        ");
		lblContrasenya1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(lblContrasenya1);
		
		txtContrasenya1 = new JPasswordField();
		txtContrasenya1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(txtContrasenya1);		
		
		lblContrasenya2 = new JLabel("        Contraseña:        ");
		lblContrasenya2.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(lblContrasenya2);
		
		txtContrasenya2 = new JPasswordField();
		txtContrasenya2.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(txtContrasenya2);
		
		btnAceptar = new JButton("Aceptar");
		panelbotonera.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		panelbotonera.add(btnCancelar);
		
		
		getContentPane().add(panelprincipal);
		pack();
		
		/*Escuchadores*/		
		btnAceptar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{		
				Registrar();
			}
		});	
		
		
				
		btnCancelar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				dispose();
			}
		});
	}
	
	
	/**
	 * Método que registra un nuevo usuario a la aplicación.
	 */
	@SuppressWarnings("deprecation")
	private void Registrar()
	{
		clsGestor objGestor=new clsGestor();
		if(txtNombre.getText().length()>0&&txtApe1.getText().length()>0&&txtApe2.getText().length()>0&&txtNickname.getText().length()>0&&txtContrasenya1.getText().length()>0&&txtContrasenya2.getText().length()>0)
		{		
			if(txtNickname.getText().toUpperCase().equals(clsConstantes.ADMIN))
			{
				JOptionPane.showMessageDialog(null, "Este nickname está restringido y no va a poder ser usado.", "Nickname restringido", JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				if(txtContrasenya1.getText().equals(txtContrasenya2.getText())==false)
				{
					JOptionPane.showMessageDialog(null, "Introduzca la misma contraseña", "¡Contraseñas diferentes!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					try
					{
						logger.log( Level.INFO, "Dando de alta al usuario "+txtNickname.getText());
						objGestor.CrearUsuario(txtNombre.getText(), txtApe1.getText(), txtApe2.getText(), txtNickname.getText().toUpperCase(), txtContrasenya1.getText());//, frmFechas.getFec());
						JOptionPane.showMessageDialog(null, "Se ha registrado un nuevo usuario correctamente");
						dispose();
					}
					catch(clsUsuarioRepetido p)
					{
						JOptionPane.showMessageDialog(null, p.getMessage(), "Nickname repetido", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Introduzca todos los datos.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}