package LP;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


import Analisisdedatos.ScatterPlotExample;
import Comun.clsConstantes;
import LN.clsCliente;
import LN.clsGestor;
import LN.clsUsuario;
import LN.clsVenta;
import Persistencia.clsBD;
import weka.core.Instances;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.util.ArrayList;


public class principalFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	principalFrame a;
	private ArrayList<clsCliente> clnts;
	private ArrayList<clsVenta> ventas;
	private clsGestor objGestor;
	
	static JFrame miVentana;
	static clsUsuario usuario;
	static Instances data;
	static Instances test;
	

	/**
	 * Create the frame.
	 */

	public principalFrame(clsUsuario usu) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		miVentana=this;
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1000, 700);
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
		tabbedPane.addTab("Maquinaria", new ImageIcon(principalFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")), panel_1, null);
		panel_1.setLayout(null);
	
		
		JButton btnM = new JButton("Ver Máquinas");
		btnM.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnM.setBounds(254, 60, 257, 60);
		panel_1.add(btnM);
		
		btnM.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				clsListaM frame = new clsListaM("Lista de máquinas", clsConstantes.VISUALIZAR,null);
				frame.pack();
				frame.setVisible(true);
			}	
		});
		
		JButton btnBM = new JButton("Borrar Máquinas");
		btnBM.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnBM.setBounds(254, 130, 257, 60);
		panel_1.add(btnBM);
		
		btnBM.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				clsListaM frame = new clsListaM("Lista de máquinas", clsConstantes.BORRAR,null);
				frame.pack();
				frame.setVisible(true);
			}	
		});
		
		JButton btnMM = new JButton("Modificar Máquinas");
		btnMM.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnMM.setBounds(254, 200, 257, 60);
		panel_1.add(btnMM);
		
		btnMM.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				clsListaM frame = new clsListaM("Lista de máquinas", clsConstantes.MODIFICAR,null);
				frame.pack();
				frame.setVisible(true);
			}	
		});
				
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(135, 206, 235));
		tabbedPane.addTab("Clientes", new ImageIcon(principalFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/DetailsView.gif")), panel_2, null);
		panel_2.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(135, 206, 235));
		tabbedPane.addTab("Ventas", new ImageIcon(principalFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/DetailsView.gif")), panel_5, null);
		panel_5.setLayout(null);
		
		DefaultTableModel modeloV= new DefaultTableModel();
		modeloV.addColumn("Id");
		modeloV.addColumn("Cliente");
		modeloV.addColumn("Producto");
		modeloV.addColumn("Cantidad");
		
		JTable tableV = new JTable(modeloV);
		tableV.setColumnSelectionAllowed(true);
		tableV.setCellSelectionEnabled(true);
		tableV.setBounds(41, 333, 645, 169);
		
		objGestor=new clsGestor();
		ventas=objGestor.ListaVentas();
		
		for(clsVenta venta : ventas)
		{
			modeloV.addRow(new Object[]{venta.getId(),venta.getNombreC(),venta.getIdm(),venta.getCantidad()});
		}
		
		JButton btnRefrescar = new JButton("Refrescar");
		btnRefrescar.setBounds(729, 407, 115, 29);
		btnRefrescar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ventas=objGestor.ListaVentas();
				if (modeloV.getRowCount() > 0) {
				    for (int i = modeloV.getRowCount() - 1; i > -1; i--) {
				        modeloV.removeRow(i);
				    }
				}
				for(clsVenta venta : ventas)
				{
					modeloV.addRow(new Object[]{venta.getId(),venta.getNombreC(),venta.getIdm(),venta.getCantidad()});
				}
			}});
		panel_5.add(btnRefrescar);
		
	
		tableV.setModel(modeloV);
		
		panel_5.add(tableV);
		
		
		/*JLabel lblNewLabel_2 = new JLabel("Clientes");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(326, 16, 95, 56);
		panel_2.add(lblNewLabel_2);*/
		
		DefaultTableModel modeloT= new DefaultTableModel();
		modeloT.addColumn("Nombre");
		modeloT.addColumn("Apellido 1");
		modeloT.addColumn("Apellido 2");
		modeloT.addColumn("DNI ");
		modeloT.addColumn("EMPRESA");
		
		JTable table = new JTable(modeloT);
		
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setBounds(41, 333, 645, 169);
	
		
		
		// Datos prueba
		objGestor=new clsGestor();
		
		clnts=objGestor.ListaClientes();
		int i;
		int max=clnts.size();
		System.out.println(max);
		
		for(clsCliente cliente : clnts)
		{
			modeloT.addRow(new Object[]{cliente.getNombre(),cliente.getApellido1(),cliente.getApellido2(),cliente.getdni(), cliente.getEmpresa()});
		}
	
		table.setModel(modeloT);
		
		panel_2.add(table);
		
		TableRowSorter filtro = new TableRowSorter(modeloT);
		
		JLabel lblNewLabel_3 = new JLabel("Gestion Comercial");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(323, 16, 257, 56);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_2.add(lblNewLabel_3);
		

		JButton btnVenta = new JButton("VENTA");
		btnVenta.setBounds(729, 407, 115, 29);
		btnVenta.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//0 por el ud
				System.out.println(table.getValueAt(table.getSelectedRow(), 0));
				clsListaM frame = new clsListaM("Lista de máquinas", clsConstantes.VENTA,(String) table.getValueAt(table.getSelectedRow(), 0));
				frame.pack();
				frame.setVisible(true);
				
			}
			
		});
		
		panel_2.add(btnVenta);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"NOMBRE", "APELLIDO", "DNI", "COMPRAS"}));
		comboBox.setBounds(173, 281, 259, 26);
		panel_2.add(comboBox);
		
		JTextField textField = new JTextField();
		textField.setBounds(493, 283, 210, 36);
		panel_2.add(textField);
		textField.setColumns(10);
	
		
		textField.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) 
            {
                String cadena = (textField.getText());
                textField.setText(cadena);
                
                System.out.println(textField.getText());
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
		lblBuscarPor.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblBuscarPor.setBounds(48, 283, 152, 20);
		panel_2.add(lblBuscarPor);
		
		
		
		JButton btnNewButton = new JButton("BUSCAR");
		btnNewButton.setBounds(729, 281, 115, 29);
		panel_2.add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(15, 243, 881, 10);
		panel_2.add(separator);
		
		Label label = new Label("Nombre");
		label.setFont(new Font("Tahoma", Font.PLAIN, 19));
		label.setBounds(15, 94, 100, 27);
		panel_2.add(label);
		
		Label label_1 = new Label("Apellido 1");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		label_1.setBounds(15, 137, 100, 27);
		panel_2.add(label_1);
		
		Label label_2 = new Label("Apellido 2");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 19));
		label_2.setBounds(15, 190, 100, 27);
		panel_2.add(label_2);
		
		TextField textField_1 = new TextField();
		textField_1.setBounds(130, 94, 231, 27);
		panel_2.add(textField_1);
		
		TextField textField_2 = new TextField();
		textField_2.setBounds(130, 137, 231, 27);
		panel_2.add(textField_2);
		
		TextField textField_3 = new TextField();
		textField_3.setBounds(130, 190, 231, 27);
		panel_2.add(textField_3);
		
		
		
	
		
		
		
		if(usu!=null)//lo que va a visualizar un usuario
		{
			JPanel panel_3 = new JPanel();
			panel_3.setBackground(new Color(135, 206, 235));
			tabbedPane.addTab("Modificar datos", new ImageIcon(principalFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Inform.gif")), panel_3, null);
			panel_3.setLayout(null);
			
			JLabel lblNewLabel_31 = new JLabel("Modificar datos");
			lblNewLabel_31.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_31.setBounds(326, 16, 195, 56);
			panel_3.add(lblNewLabel_31);
			
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
			
			JLabel lblNewLabel_31 = new JLabel("Registro usuarios");
			lblNewLabel_31.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_31.setBounds(326, 16, 195, 56);
			panel_3.add(lblNewLabel_31);
			
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
	private Object i(int size) {
		// TODO Auto-generated method stub
		return null;
}
	}
