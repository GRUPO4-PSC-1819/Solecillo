package LP;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import LD.GestorSQL;
import LN.Usuario;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JCheckBox;

import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class loginFrame extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	private JButton btnAceptar_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginFrame frame = new loginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public loginFrame() {
		
		//Conectar con la BBDD y recoger los usuarios registrados
		GestorSQL gestor = new GestorSQL();
		ArrayList<Usuario> usuarios = new ArrayList();
		usuarios=gestor.MostrarUsuarios();
		
		for(Usuario u : usuarios)
		{
			
			System.out.println(u.getNombre_usuario());
			
		}
		System.out.println(getName());
		
		getContentPane().setLayout(null);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(298, 167, 115, 29);
		getContentPane().add(btnAceptar);
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
		txtpnPassword.setText("Password");
		txtpnPassword.setBounds(205, 234, 88, 38);
		contentPane.add(txtpnPassword);
		
		JLabel txtpnUsuario = new JLabel();
		txtpnUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtpnUsuario.setText("Usuario");
		txtpnUsuario.setBackground(Color.ORANGE);
		txtpnUsuario.setBounds(205, 169, 88, 38);
		contentPane.add(txtpnUsuario);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setBounds(354, 169, 229, 38);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		btnAceptar_1 = new JButton("ACEPTAR");
		btnAceptar_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAceptar_1.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane ventanita= new JOptionPane();
				//Validación de datos
				ventanita.showInternalMessageDialog(contentPane,"El usuario y/o contraseña son incorrectos. Intentelo de nuevo ","Error", 0);
			}
		});
		btnAceptar_1.setBounds(354, 394, 157, 60);
		contentPane.add(btnAceptar_1);
		
		JCheckBox chckbxAdministrador = new JCheckBox("Administrador");
		chckbxAdministrador.setToolTipText("Seleccione si quiere acceder como administrador");
		
		chckbxAdministrador.setFont(new Font("Tahoma", Font.PLAIN, 18));
		chckbxAdministrador.setBackground(Color.ORANGE);
		chckbxAdministrador.setBounds(354, 322, 198, 60);
		contentPane.add(chckbxAdministrador);
		
		
	}
}

