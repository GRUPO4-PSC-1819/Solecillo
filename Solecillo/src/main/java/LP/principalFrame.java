package LP;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;


import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class principalFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					principalFrame frame = new principalFrame();
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
	public principalFrame() 
	{
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 938, 608);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 916, 552);
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
		
		JLabel lblMaquina = new JLabel("Maquina 1");
		lblMaquina.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMaquina.setBounds(28, 229, 110, 20);
		panel_1.add(lblMaquina);
		
		JLabel lblMaquina_1 = new JLabel("Maquina 2");
		lblMaquina_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMaquina_1.setBounds(28, 288, 110, 20);
		panel_1.add(lblMaquina_1);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(Color.RED);
		progressBar.setValue(80);
		progressBar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		progressBar.setStringPainted(true);
		progressBar.setBounds(164, 229, 408, 25);
		panel_1.add(progressBar);
		
		JProgressBar progressBar_1 = new JProgressBar();
		progressBar_1.setForeground(Color.BLUE);
		progressBar_1.setValue(50);
		progressBar_1.setStringPainted(true);
		progressBar_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		progressBar_1.setBounds(164, 288, 408, 25);
		panel_1.add(progressBar_1);
		
		JLabel lblNewLabel_1 = new JLabel("Maquina 3");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(28, 350, 121, 20);
		panel_1.add(lblNewLabel_1);
		
		JProgressBar progressBar_2 = new JProgressBar();
		progressBar_2.setStringPainted(true);
		progressBar_2.setForeground(new Color(50, 205, 50));
		progressBar_2.setValue(15);
		progressBar_2.setBounds(164, 350, 408, 25);
		panel_1.add(progressBar_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(135, 206, 235));
		tabbedPane.addTab("Clientes", new ImageIcon(principalFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/DetailsView.gif")), panel_2, null);
		panel_2.setLayout(null);
		
		DefaultTableModel modeloT= new DefaultTableModel();
		modeloT.addColumn("Nombre");
		modeloT.addColumn("Apellido 1");
		modeloT.addColumn("Apellido 2");
		modeloT.addColumn("DNI ");
		
		table = new JTable(modeloT);
		
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setBounds(41, 333, 645, 169);
	
		
		
		// Datos prueba
		
		
		modeloT.addRow(new Object[]{"Josune","Ordoñez","Arrillaga","72555252"});
		modeloT.addRow(new Object[]{"Nerea","Etxeberria","Garcia","123456789"});
		table.setModel(modeloT);
		
		panel_2.add(table);
		
		TableRowSorter filtro = new TableRowSorter(modeloT);
		
		JLabel lblNewLabel_2 = new JLabel("Gestion Comercial");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(323, 16, 257, 56);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_2.add(lblNewLabel_2);
		
		JButton btnVenta = new JButton("VENTA");
		btnVenta.setBounds(729, 407, 115, 29);
		panel_2.add(btnVenta);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"NOMBRE", "APELLIDO", "DNI", "COMPRAS"}));
		comboBox.setBounds(173, 281, 259, 26);
		panel_2.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(493, 283, 193, 26);
		panel_2.add(textField);
		textField.setColumns(10);
		
		
		textField.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) 
            {
                String cadena = (textField.getText());
                textField.setText(cadena);
             
                int columna=0;
               
                if(comboBox.getSelectedIndex()==0) {
					//Nombre  
					//filtro.setRowFilter(RowFilter.regexFilter(textField.getText(), 1));
                	
					}
				
				if(comboBox.getSelectedIndex()==1) {
					columna=1;
				}
				if(comboBox.getSelectedIndex()==2) {
					columna=2;}
				if(comboBox.getSelectedIndex()==3) { 
					columna=3;
					
				}
				filtro.setRowFilter(RowFilter.regexFilter(textField.getText(), columna));
				table.setRowSorter(filtro);
        }}

    );
		
		
		
	
		JLabel lblBuscarPor = new JLabel("Buscar por: ");
		lblBuscarPor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBuscarPor.setBounds(48, 283, 152, 20);
		panel_2.add(lblBuscarPor);
		
		
		
		JButton btnNewButton = new JButton("BUSCAR");
		btnNewButton.setBounds(729, 281, 115, 29);
		panel_2.add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(15, 243, 881, 10);
		panel_2.add(separator);
		
		Label label = new Label("Nombre");
		label.setBounds(15, 94, 82, 27);
		panel_2.add(label);
		
		Label label_1 = new Label("Apellido 1");
		label_1.setBounds(15, 137, 82, 27);
		panel_2.add(label_1);
		
		Label label_2 = new Label("Apellido 2");
		label_2.setBounds(15, 190, 82, 27);
		panel_2.add(label_2);
		
		TextField textField_1 = new TextField();
		textField_1.setBounds(121, 94, 231, 27);
		panel_2.add(textField_1);
		
		TextField textField_2 = new TextField();
		textField_2.setBounds(121, 137, 231, 27);
		panel_2.add(textField_2);
		
		TextField textField_3 = new TextField();
		textField_3.setBounds(121, 190, 231, 27);
		panel_2.add(textField_3);
		
		
		}
		
	}

