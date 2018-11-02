package LP;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Analisisdedatos.ScatterPlotExample;
import LN.clsUsuario;
import weka.core.Instances;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class principalFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	principalFrame a;
	
	static JFrame miVentana;
	static clsUsuario usuario;
	static Instances data;
	static Instances test;
	

	/**
	 * Create the frame.
	 */
	public principalFrame(clsUsuario usu) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 818, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		miVentana=this;
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 796, 459);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 240, 245));
		tabbedPane.addTab("Home", new ImageIcon(principalFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/homeFolder.gif")), panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pagina de inicio");
		lblNewLabel.setBackground(new Color(255, 240, 245));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(298, 16, 190, 51);
		panel.add(lblNewLabel);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(176, 224, 230));
		tabbedPane.addTab("Inventario", new ImageIcon(principalFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")), panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Inventario");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(326, 16, 95, 56);
		panel_1.add(lblNewLabel_1);
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(135, 206, 235));
		tabbedPane.addTab("Clientes", new ImageIcon(principalFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/DetailsView.gif")), panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Clientes");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(326, 16, 95, 56);
		panel_2.add(lblNewLabel_2);
		
		if(usu!=null)//lo que va a visualizar un usuario
		{
			JPanel panel_3 = new JPanel();
			panel_3.setBackground(new Color(135, 206, 235));
			tabbedPane.addTab("Modificar datos", new ImageIcon(principalFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Inform.gif")), panel_3, null);
			panel_3.setLayout(null);
			
			JLabel lblNewLabel_3 = new JLabel("Modificar datos");
			lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_3.setBounds(326, 16, 195, 56);
			panel_3.add(lblNewLabel_3);
			
			JButton btnAceptar_2 = new JButton("Modificar mis datos");
			btnAceptar_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnAceptar_2.setBounds(254, 194, 257, 60);
			panel_3.add(btnAceptar_2);
			btnAceptar_2.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					try 
					{
						clsModificarUsuario window = new clsModificarUsuario(usu, (principalFrame) miVentana);
						window.setVisible(true);
					} 
					catch (Exception w) 
					{
						w.printStackTrace();
					}
				}
			});
		 }
		
		else //lo que va a visualizar el administrador
		{
			JPanel panel_3 = new JPanel();
			panel_3.setBackground(new Color(135, 206, 235));
			tabbedPane.addTab("Dar de alta", new ImageIcon(principalFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Inform.gif")), panel_3, null);
			panel_3.setLayout(null);
			
			JLabel lblNewLabel_3 = new JLabel("Registro usuarios");
			lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_3.setBounds(326, 16, 195, 56);
			panel_3.add(lblNewLabel_3);
			
			JButton btnAceptar_2 = new JButton("Crear nuevo usuario");
			btnAceptar_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnAceptar_2.setBounds(254, 70, 257, 60);
			panel_3.add(btnAceptar_2);
			btnAceptar_2.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					try 
					{
						clsAltaUsuario window = new clsAltaUsuario();
						window.setVisible(true);
					} 
					catch (Exception w) 
					{
						w.printStackTrace();
					}
				}
			});
			
			JButton btnAceptar_3 = new JButton("Crear máquina eólica");
			btnAceptar_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnAceptar_3.setBounds(254, 140, 257, 60);
			panel_3.add(btnAceptar_3);
			btnAceptar_3.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					try 
					{
						clsAltaEolica window = new clsAltaEolica();
						window.setVisible(true);
					} 
					catch (Exception w) 
					{
						w.printStackTrace();
					}
				}
			});
			
			JButton btnAceptar_4 = new JButton("Crear máquina hidráulica");
			btnAceptar_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnAceptar_4.setBounds(254, 210, 257, 60);
			panel_3.add(btnAceptar_4);
			btnAceptar_4.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					try 
					{
						clsAltaHidraulica window = new clsAltaHidraulica();
						window.setVisible(true);
					} 
					catch (Exception w) 
					{
						w.printStackTrace();
					}
				}
			});
			
			JButton btnAceptar_5 = new JButton("Crear máquina mareomotriz");
			btnAceptar_5.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnAceptar_5.setBounds(254, 280, 257, 60);
			panel_3.add(btnAceptar_5);
			btnAceptar_5.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					try 
					{
						clsAltaMareomotriz window = new clsAltaMareomotriz();
						window.setVisible(true);
					} 
					catch (Exception w) 
					{
						w.printStackTrace();
					}
				}
			});
			
			JButton btnAceptar_6 = new JButton("Crear máquina solar");
			btnAceptar_6.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnAceptar_6.setBounds(254, 350, 257, 60);
			panel_3.add(btnAceptar_6);
			btnAceptar_6.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					try 
					{
						clsAltaSolar window = new clsAltaSolar();
						window.setVisible(true);
					} 
					catch (Exception w) 
					{
						w.printStackTrace();
					}
				}
			});	
			
		
			JPanel panel_4 = new JPanel();
			panel_4.setBackground(new Color(135, 206, 235));
			tabbedPane.addTab("Análisis", new ImageIcon(principalFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Inform.gif")), panel_4, null);
			panel_4.setLayout(null);
			
			JLabel lblNewLabel_4 = new JLabel("Análisis de datos");
			lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_4.setBounds(326, 16, 195, 56);
			panel_4.add(lblNewLabel_4);
			
			JButton btnAceptar_7 = new JButton("Visualizar la pantalla de análisis de datos");
			btnAceptar_7.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnAceptar_7.setBounds(254, 194, 257, 60);
			panel_4.add(btnAceptar_7);
			btnAceptar_7.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					try 
					{
					  ScatterPlotExample example = new ScatterPlotExample("Scatter Chart Example | BORAJI.COM");
				      example.setSize(800, 400);
				      example.setLocationRelativeTo(null);
				      example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				      example.setVisible(true);

					} 
					catch (Exception w) 
					{
						w.printStackTrace();
					}
				}
			});
		}
	   }
	}