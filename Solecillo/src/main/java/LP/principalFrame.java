package LP;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import LN.clsUsuario;
import javax.swing.JTabbedPane;
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
		btnAceptar_2.setBounds(254, 194, 257, 60);
		panel_3.add(btnAceptar_2);
	}
	
}
