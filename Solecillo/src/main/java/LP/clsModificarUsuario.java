package LP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import LN.clsGestor;
import LN.clsUsuario;
/**
 * Clase que generará una ventana que hereda de clsAltaUsuario para modificar los datos de un usuario existente en la base de datos.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */
public class clsModificarUsuario extends clsAltaUsuario
{
	private static final long serialVersionUID = 1L;
	
	clsUsuario modif;
	
	/**
	 * Constructor de la ventana que aprovecha la interfaz gráfica de clsAltaUsuario, incluyendo los datos de un usuario dado.
	 * @param usuario El usuario que está modificando sus datos.
	 * @param ventanita La ventana clsEleccion de la que proviene el usuario, que tendrá su atributo "usuario" modificado al cambiar los datos referentes al usuario.
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