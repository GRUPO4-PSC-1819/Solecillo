package LP;
 import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
 import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
 import javax.swing.JButton;
 import LN.clsGestor;
 /**
 * Clase que generará una JFrame para introducir los datos que a su vez serán enviados a clsBinarios para la creación
 * de un usuario en la base de datos.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Josune Ordoñez (Josune07)
 */
public class clsAltaSolar extends JFrame 
{
	private static final long serialVersionUID = 1L;
 	private JPanel panelprincipal;
	private JPanel panelizda;
	private JPanel panelbotonera;
	private JPanel panelsuperior;	
	
	JTextField txtNombre;
	JTextField txtColor;
	JTextField txtValor;
	JTextField txtFabricante;
	JTextField txtNombrePueblo;
	JTextField txtNombreCampo;
	
	
	private JLabel lblNombre;
	private JLabel lblColor;
	private JLabel lblValor;
	private JLabel lblFabricante;
	private JLabel lblNombrePueblo;
	private JLabel lblNombreCampo;
	
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
			logger.log( Level.SEVERE, "No se ha podido crear fichero de log en clase "+ clsAltaSolar.class.getName() );
		}
		logger.log( Level.INFO, "" );
		logger.log( Level.INFO, DateFormat.getDateTimeInstance( DateFormat.LONG, DateFormat.LONG ).format( new Date() ) );
	}
		
	/**
	 * Constructor del JFrame que genera la parte visual de la ventana, así como los escuchadores requeridos para mejorar la interacción de la ventana.
	 */
	public clsAltaSolar() 
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
		
		lblColor = new JLabel("        Color:        ");
		lblColor.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(lblColor);
		
		txtColor = new JTextField();
		txtColor.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(txtColor);
		
		lblValor = new JLabel("        Valor:        ");
		lblValor.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(lblValor);
		
		txtValor = new JTextField();
		txtValor.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(txtValor);	
		
		lblFabricante = new JLabel("         Fabricante:         ");
		lblFabricante.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(lblFabricante);
		
		txtFabricante = new JTextField();
		txtFabricante.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(txtFabricante);
			
		lblNombrePueblo = new JLabel("        Nombre del pueblo:        ");
		lblNombrePueblo.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(lblNombrePueblo);
		
		txtNombrePueblo = new JTextField();
		txtNombrePueblo.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(txtNombrePueblo);		
		
		lblNombreCampo = new JLabel("        Nombre del campo:        ");
		lblNombreCampo.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(lblNombreCampo);
		
		txtNombreCampo = new JTextField();
		txtNombreCampo.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(txtNombreCampo);
		
		
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
	private void Registrar()
	{
		clsGestor objGestor=new clsGestor();
		if(txtNombre.getText().length()>0&&txtColor.getText().length()>0&&txtValor.getText().length()>0&&txtFabricante.getText().length()>0&&txtNombrePueblo.getText().length()>0&&txtNombreCampo.getText().length()>0)
		{		
				logger.log( Level.INFO, "Dando de alta máquina mareomotriz "+txtNombre.getText());
				try {
					objGestor.CrearMaquinaSolar(txtNombre.getText(), txtColor.getText(), Double.parseDouble(txtValor.getText()), txtFabricante.getText().toUpperCase(), txtNombrePueblo.getText(), txtNombreCampo.getText());
					dispose();
					JOptionPane.showMessageDialog(null, "Se ha registrado una nueva máquina solar correctamente.");
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "El campo 'Valor' debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Introduzca todos los datos.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}