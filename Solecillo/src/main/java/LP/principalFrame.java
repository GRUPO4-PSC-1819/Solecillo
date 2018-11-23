package LP;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.jfree.ui.RefineryUtilities;
import Analisisdedatos.PieChart_AWT;
import Analisisdedatos.PieChart_PROD;
import Analisisdedatos.Rio_Fabricante_Maquina;
import Analisisdedatos.ScatterPlotExample;
import Analisisdedatos.Valor_Estado;
import Analisisdedatos.Valor_Medio_Maquinas;
import Analisisdedatos.Ventas_Maquina;
import Analisisdedatos.top_clientes;
import Comun.clsConstantes;
import LN.clsCliente;
import LN.clsClienteRepetido;
import LN.clsGestor;
import LN.clsUsuario;
import LN.clsUsuarioRepetido;
import LN.clsVenta;
import weka.core.Instances;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;


public class principalFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	principalFrame a;
	private ArrayList<clsCliente> clnts;
	private ArrayList<clsVenta> ventas;
	private clsGestor objGestor;
	private TextField textNombre;
	private TextField textApe1;
	private TextField textApe2;
	private TextField textDNI;
	private TextField textEmpresa;
	public JButton btnRefrescar;
	
	static JFrame miVentana;
	static clsUsuario usuario;
	static Instances data;
	static Instances test;
	

private static final boolean ANYADIR_A_FIC_LOG = true;
	
	/*Logger*/
	private static Logger logger = Logger.getLogger( "Solecillo" );
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
			FileOutputStream fLog = new FileOutputStream( "Solecillo"+".log" , ANYADIR_A_FIC_LOG );
			Handler h = new StreamHandler( fLog, f );
			h.setLevel( Level.FINEST );
			logger.addHandler( h );
		} 
		catch (SecurityException | IOException e) 
		{
			logger.log( Level.SEVERE, "No se ha podido crear fichero de log en clase "+ clsAltaEolica.class.getName() );
		}
		logger.log( Level.INFO, "" );
		logger.log( Level.INFO, DateFormat.getDateTimeInstance( DateFormat.LONG, DateFormat.LONG ).format( new Date() ) );
	}
	/**
	 * Create the frame.
	 */

	public principalFrame(clsUsuario usu) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		tableV.setBounds(41, 150, 645, 169);
		
		objGestor=new clsGestor();
		ventas=objGestor.ListaVentas();
		
		for(clsVenta venta : ventas)
		{
			modeloV.addRow(new Object[]{venta.getId(),venta.getDniC(),venta.getIdm(),venta.getCantidad()});
		}
		
		btnRefrescar = new JButton("Refrescar");
		btnRefrescar.setBounds(729, 407, 115, 29);
		btnRefrescar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ventas=objGestor.ListaVentas();
				if (modeloV.getRowCount() > 0) {
				    for (int i = modeloV.getRowCount() - 1; i > -1; i--) {
				        modeloV.removeRow(i);
				    }
				}
				for(clsVenta venta : ventas)
				{
					modeloV.addRow(new Object[]{venta.getId(),venta.getDniC(),venta.getIdm(),venta.getCantidad()});
				}
			}});
		panel_5.add(btnRefrescar);
		
		JButton btngrafico = new JButton("Gráfico Ventas - Cliente ");
		btngrafico.setBounds(729, 307, 225, 29);
		btngrafico.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {				
		          ventas=objGestor.ListaVentas();
		          if(ventas.size()>0)
		          {
					  PieChart_AWT demo = new PieChart_AWT( "Ventas" );  
				      demo.setSize( 560 , 367 );    
				      RefineryUtilities.centerFrameOnScreen( demo );   
				      demo.pack();
				      demo.setVisible( true ); 
		          }
		          else
		          {
		      		JOptionPane.showMessageDialog(null, "No hay ventas todavía.", "Error", JOptionPane.ERROR_MESSAGE);
		          }
			}});
		panel_5.add(btngrafico);
		
		JButton btngrafico2 = new JButton("Gráfico Ventas - Producto ");
		btngrafico2.setBounds(729, 207, 225, 29);
		btngrafico2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {				
		          ventas=objGestor.ListaVentas();
		          if(ventas.size()>0)
		          {
					  PieChart_PROD demo = new PieChart_PROD( "Ventas" );  
				      demo.setSize( 560 , 367 );    
				      RefineryUtilities.centerFrameOnScreen( demo );   
				      demo.pack();
				      demo.setVisible( true ); 
		          }
		          else
		          {
		      		JOptionPane.showMessageDialog(null, "No hay ventas todavía.", "Error", JOptionPane.ERROR_MESSAGE);
		          }
			}});
		panel_5.add(btngrafico2);
		
		JButton btngrafico3 = new JButton("Gráfico Ventas Clientes TOP");
		btngrafico3.setBounds(729, 257, 225, 29);
		btngrafico3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {				
		          ventas=objGestor.ListaVentas();
		          if(ventas.size()>0)
		          {
					  top_clientes demo = new top_clientes( "Ventas" );  
				      demo.setSize( 560 , 367 );    
				      RefineryUtilities.centerFrameOnScreen( demo );   
				      demo.pack();
				      demo.setVisible( true ); 
		          }
		          else
		          {
		      		JOptionPane.showMessageDialog(null, "No hay ventas todavía.", "Error", JOptionPane.ERROR_MESSAGE);
		          }
			}});
		panel_5.add(btngrafico3);
		
		JButton btngrafico4 = new JButton("Gráfico Valor Medio Máquinas");
		btngrafico4.setBounds(729, 357, 225, 29);
		btngrafico4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
		        	  Valor_Medio_Maquinas chart = new Valor_Medio_Maquinas("Valor medio máquinas", 
		        		         "¿Cuánto valen?");
		        		      chart.pack( );        
		        		      RefineryUtilities.centerFrameOnScreen( chart );        
		        		      chart.setVisible( true ); 
			}});
		panel_5.add(btngrafico4);
		
		JButton btngrafico5 = new JButton("Gráfico Ventas - Tipo Máquina");
		btngrafico5.setBounds(729, 457, 225, 29);
		btngrafico5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
		          ventas=objGestor.ListaVentas();
		          if(ventas.size()>0)
		          {
					  Ventas_Maquina demo = new Ventas_Maquina( "Ventas" );  
				      demo.setSize( 560 , 367 );    
				      RefineryUtilities.centerFrameOnScreen( demo );   
				      demo.pack();
				      demo.setVisible( true ); 
		          }
		          else
		          {
		      		JOptionPane.showMessageDialog(null, "No hay ventas todavía.", "Error", JOptionPane.ERROR_MESSAGE);
		          }
			}});
		panel_5.add(btngrafico5);
		
		JButton btngrafico6 = new JButton("Gráfico Valor - Estado Máquina");
		btngrafico6.setBounds(729, 507, 225, 29);
		btngrafico6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
					  Valor_Estado demo = new Valor_Estado( "Ventas" );  
				      demo.setSize( 560 , 367 );    
				      RefineryUtilities.centerFrameOnScreen( demo );   
				      demo.pack();
				      demo.setVisible( true ); 
			}});
		panel_5.add(btngrafico6);
		
		JButton btngrafico7 = new JButton("Gráfico Río - Fabricante Máquina");
		btngrafico7.setBounds(729, 557, 225, 29);
		btngrafico7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 Rio_Fabricante_Maquina chart = new Rio_Fabricante_Maquina("Fabricantes por río", 
        		         "¿Cuántas máquinas son de cada fabricante?");
        		      chart.pack( );        
        		      RefineryUtilities.centerFrameOnScreen( chart );        
        		      chart.setVisible( true ); 
			}});
		panel_5.add(btngrafico7);
	
		tableV.setModel(modeloV);
        JScrollPane js1 = new JScrollPane(tableV);
        js1.setBounds(41, 150, 645, 169);
        js1.setVisible(true);
        panel_5.add(js1);

		DefaultTableModel modeloT= new DefaultTableModel();
		modeloT.addColumn("Nombre");
		modeloT.addColumn("Apellido 1");
		modeloT.addColumn("Apellido 2");
		modeloT.addColumn("DNI ");
		modeloT.addColumn("EMPRESA");
		
		JTable table = new JTable(modeloT);
		
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setBounds(41, 373, 645, 169);
	
		
		
		// Datos prueba
		objGestor=new clsGestor();
		
		clnts=objGestor.ListaClientes();
		//int max=clnts.size();
		
		//modeloT.addRow(new Object[]{"NOMBRE", "APELLIDO 1", "APELLIDO 2", "DNI", "EMPRESA"});
		for(clsCliente cliente : clnts)
		{
			modeloT.addRow(new Object[]{cliente.getNombre(), cliente.getApellido1(), cliente.getApellido2(), cliente.getdni(), cliente.getEmpresa()});
		}
		table.setModel(modeloT);
		
		
		table.setModel(modeloT);
        JScrollPane js = new JScrollPane(table);
        js.setBounds(41, 373, 645, 169);
        js.setVisible(true);
        panel_2.add(js);
		
		
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
				int i=-1;
				
				if(table.getSelectedRow()==-1)
				{
					JOptionPane.showMessageDialog(null, "Elija un cliente.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					clsListaM frame = new clsListaM("Lista de máquinas", clsConstantes.VENTA, (String) table.getValueAt(table.getSelectedRow(), 3));
					frame.pack();
					frame.setVisible(true);
				}
				
				
			}
			
		});
		
		panel_2.add(btnVenta);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"NOMBRE", "APELLIDO 1", "APELLIDO 2", "DNI", "COMPRAS"}));
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
				if(comboBox.getSelectedIndex()==4) { 
					columna=4;
				}
				filtro.setRowFilter(RowFilter.regexFilter(textField.getText(), columna));
				table.setRowSorter(filtro);
        }}

    );
		
		
		
	
		JLabel lblBuscarPor = new JLabel("Buscar por: ");
		lblBuscarPor.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblBuscarPor.setBounds(48, 283, 152, 20);
		panel_2.add(lblBuscarPor);
		
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
		
		Label label_3 = new Label("DNI");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 19));
		label_3.setBounds(400, 94, 100, 27);
		panel_2.add(label_3);
		
		Label label_4 = new Label("Empresa");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 19));
		label_4.setBounds(400, 137, 100, 27);
		panel_2.add(label_4);
		
		textNombre = new TextField();
		textNombre.setBounds(130, 94, 231, 27);
		panel_2.add(textNombre);
		
		textApe1 = new TextField();
		textApe1.setBounds(130, 137, 231, 27);
		panel_2.add(textApe1);
		
		textApe2 = new TextField();
		textApe2.setBounds(130, 190, 231, 27);
		panel_2.add(textApe2);
		
		textDNI = new TextField();
		textDNI.setBounds(510, 94, 231, 27);
		panel_2.add(textDNI);
		
		textEmpresa = new TextField();
		textEmpresa.setBounds(510, 137, 231, 27);
		panel_2.add(textEmpresa);
		
		JButton btnNewCliente = new JButton("Nuevo cliente");
		btnNewCliente.setBounds(400, 190, 115, 29);
		panel_2.add(btnNewCliente);
		btnNewCliente.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				if(textNombre.getText().length()>0&&textApe1.getText().length()>0&&textApe2.getText().length()>0&&textDNI.getText().length()>0&&textEmpresa.getText().length()>0)
				{
					try {
						objGestor.CrearCliente(textNombre.getText(),textApe1.getText(),textApe2.getText(),textDNI.getText(),textEmpresa.getText());
						logger.log( Level.INFO, "Dando de alta al cliente con DNI: "+textDNI.getText());
						ArrayList<clsCliente> clientes = objGestor.ListaClientes();
						if (modeloT.getRowCount() > 0) {
						    for (int i = modeloT.getRowCount() - 1; i > -1; i--) {
						        modeloT.removeRow(i);
						    }
						}
						for(clsCliente cliente : clientes)
						{
							modeloT.addRow(new Object[]{cliente.getNombre(),cliente.getApellido1(),cliente.getApellido2(),cliente.getdni(), cliente.getEmpresa()});
						}
						textNombre.setText("");
						textApe1.setText("");
						textApe2.setText("");
						textDNI.setText("");
						textEmpresa.setText("");
						JOptionPane.showMessageDialog(null, "Se ha registrado un nuevo cliente correctamente");
						
					} catch (clsClienteRepetido e1) 
					{
						JOptionPane.showMessageDialog(null, e1.getMessage(), "DNI repetido", JOptionPane.WARNING_MESSAGE);
					}
						
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Introduzca todos los datos.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		
	
		
		
		
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
						clsModificarUsuario window = new clsModificarUsuario(usu, miVentana);
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
			
			JPanel panel_41 = new JPanel();
			panel_41.setBackground(new Color(135, 206, 235));
			tabbedPane.addTab("Migración", new ImageIcon(principalFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Inform.gif")), panel_41, null);
			panel_41.setLayout(null);
			
			JButton btnAceptar_21 = new JButton("Migrar datos de usuarios");
			btnAceptar_21.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnAceptar_21.setBounds(254, 70, 257, 60);
			panel_41.add(btnAceptar_21);
			btnAceptar_21.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						objGestor.MigracionUsuarios();
					} catch (clsUsuarioRepetido e1) {
						e1.printStackTrace();
					}
				}
			});
			JButton btnAceptar_22 = new JButton("Migrar datos de clientes");
			btnAceptar_22.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnAceptar_22.setBounds(254, 140, 257, 60);
			panel_41.add(btnAceptar_22);
			btnAceptar_22.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) {
						try {
							objGestor.MigracionClientes();
						} catch (clsClienteRepetido e1) {
							e1.printStackTrace();
						}
				}
			});
			JButton btnAceptar_23 = new JButton("Migrar datos de máquinas");
			btnAceptar_23.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnAceptar_23.setBounds(254, 210, 257, 60);
			panel_41.add(btnAceptar_23);
			btnAceptar_23.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) {
						objGestor.MigracionMaquinas();
				}
			});
			JButton btnAceptar_24 = new JButton("Migrar datos de ventas");
			btnAceptar_24.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnAceptar_24.setBounds(254, 280, 257, 60);
			panel_41.add(btnAceptar_24);
			btnAceptar_24.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) {
						objGestor.MigracionVentas();
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
			btnAceptar_7.setBounds(254, 194, 367, 60);
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
				      example.setVisible(true);

					} 
					catch (Exception w) 
					{
						w.printStackTrace();
					}
				}
			});
		}
			addWindowListener( new WindowAdapter() 
			{
				@Override
				public void windowClosing(WindowEvent e) 
				{
					logger.log(Level.INFO, "Volviendo al menu principal");
					dispose();
					loginFrame frame = new loginFrame();
					frame.setVisible(true);
				}
			});	
		
	   }

}
	
