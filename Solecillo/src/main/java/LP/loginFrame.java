package LP;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		getContentPane().setLayout(null);
		
		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setBounds(298, 167, 115, 29);
		getContentPane().add(btnAceptar);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		
		JTextPane txtpnSolecillosl = new JTextPane();
		txtpnSolecillosl.setBackground(Color.ORANGE);
		txtpnSolecillosl.setFont(new Font("Arimo", Font.BOLD, 22));
		txtpnSolecillosl.setText("SOLECILLO.S.L");
		txtpnSolecillosl.setBounds(120, 16, 184, 38);
	
		contentPane.add(txtpnSolecillosl);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(120, 202, 184, 26);
		contentPane.add(passwordField);
		
		JLabel etiqueta = new JLabel();
		etiqueta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		etiqueta.setBackground(Color.ORANGE);
		etiqueta.setText("Password");
		etiqueta.setBounds(15, 202, 80, 28);
		contentPane.add(etiqueta);
		
		JLabel etiqueta_2 = new JLabel();
		etiqueta_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		etiqueta_2.setText("Usuario");
		etiqueta_2.setBackground(Color.ORANGE);
		etiqueta_2.setBounds(15, 162, 65, 28);
		contentPane.add(etiqueta_2);
		
		textField = new JTextField();
		textField.setBounds(118, 160, 186, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		btnAceptar_1 = new JButton("ACEPTAR");
		btnAceptar_1.addActionListener(new ActionListener() 
		{
			

			
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnAceptar_1.setBounds(313, 181, 100, 26);
		contentPane.add(btnAceptar_1);
		
		
	}
}
