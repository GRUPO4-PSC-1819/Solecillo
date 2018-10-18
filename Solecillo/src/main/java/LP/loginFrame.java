package LP;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Comun.clsConstantes;
import LN.clsGestor;
import LN.clsUsuario;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class loginFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	private JButton btnAceptar_1;
	private JFrame miVentana;
	
	ArrayList<clsUsuario> usus=new ArrayList<clsUsuario>();

	
	/**
	 * Create the frame.
	 */
	public loginFrame() {
		getContentPane().setLayout(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 914, 572);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JTextPane txtpnSolecillosl = new JTextPane();
		txtpnSolecillosl.setEditable(false);
		txtpnSolecillosl.setBackground(Color.ORANGE);
		txtpnSolecillosl.setFont(new Font("Arimo", Font.BOLD, 26));
		txtpnSolecillosl.setText("SOLECILLO.S.L");
		txtpnSolecillosl.setBounds(337, 16, 319, 123);
		
		
	
		contentPane.add(txtpnSolecillosl);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		passwordField.setBounds(354, 234, 229, 38);
		contentPane.add(passwordField);
		
		JLabel txtpnPassword = new JLabel();
		txtpnPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtpnPassword.setBackground(Color.ORANGE);
		txtpnPassword.setText("Contraseña");
		txtpnPassword.setBounds(205, 234, 108, 38);
		contentPane.add(txtpnPassword);
		
		JLabel txtpnUsuario = new JLabel();
		txtpnUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtpnUsuario.setText("Usuario");
		txtpnUsuario.setBackground(Color.ORANGE);
		txtpnUsuario.setBounds(205, 169, 88, 38);
		contentPane.add(txtpnUsuario);
		
		
		JCheckBox chckbxAdministrador = new JCheckBox("Administrador");
		chckbxAdministrador.setToolTipText("Seleccione si quiere acceder como administrador");
		chckbxAdministrador.setFont(new Font("Tahoma", Font.PLAIN, 18));
		chckbxAdministrador.setBackground(Color.ORANGE);
		chckbxAdministrador.setBounds(354, 322, 198, 60);
		contentPane.add(chckbxAdministrador);
		
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setBounds(354, 169, 229, 38);
		contentPane.add(textField);
		textField.setColumns(10);
		
		miVentana = this;		
		
		btnAceptar_1 = new JButton("ENTRAR");
		btnAceptar_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAceptar_1.setBounds(604, 194, 157, 60);
		contentPane.add(btnAceptar_1);
		btnAceptar_1.addActionListener(new ActionListener() 
		{
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
			
			clsGestor objGestor=new clsGestor();
			usus=objGestor.ListaUsuarios();
			boolean existe = false;
			for(clsUsuario aux:usus)
			{
				if((textField.getText().toUpperCase().equals(aux.getNickname().toUpperCase()))&&(passwordField.getText().equals(aux.getContraseña())))
				{
					existe = true;
					ProgressBar pb=new ProgressBar("Entrando al sistema como usuario...", clsConstantes.PRINCIPAL, aux);
					pb.setVisible(true);
					miVentana.dispose();
					break;
				}
				if(textField.getText().toUpperCase().equals(clsConstantes.ADMIN) && passwordField.getText().toUpperCase().equals(clsConstantes.ADMIN))
				{
					if(chckbxAdministrador.isSelected()==true)
					{
						existe=true;
						ProgressBar pb=new ProgressBar("Entrando al sistema como administrador...", clsConstantes.ADMIN, null);
						pb.setVisible(true);
						miVentana.dispose();
						break;
					}
					else
					{
						existe=true;
						JOptionPane.showMessageDialog(null, "Sus credenciales son correctas, pero debe marcar la casilla de administrador.", "Error de administrador", JOptionPane.ERROR_MESSAGE);
						break;
					}
				}
			}
			if(!existe)
			{
				JOptionPane.showMessageDialog(null, "¿Está dado de alta? Su nickname o contraseña son incorrectos.", "¡Error de Login!", JOptionPane.ERROR_MESSAGE);
			}
			
			}
		});
		
		
		
	}
}

