package LP;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import Comun.clsConstantes;
import LN.clsGestor;
import LN.clsMaquina_Eolica;
import LN.clsMaquina_Hidraulica;
import LN.clsMaquina_Mareomotriz;
import LN.clsMaquina_Solar;

/**
 * Clase que generará una JFrame para mostrar los datos de los usuarios registrados.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */
public class clsListaM extends JFrame
{
	private static final long serialVersionUID = 1L;

	static ArrayList <clsMaquina_Eolica> listaEolica;
	static ArrayList <clsMaquina_Hidraulica> listaHidraulica;
	static ArrayList <clsMaquina_Mareomotriz> listaMareomotriz;
	static ArrayList <clsMaquina_Solar> listaSolar;
	
	static JFrame miVentana;
	
	private JLabel lblInformacion;
	public JRadioButton rdbtnEolica;
	public  JRadioButton rdbtnHidraulica;
	public  JRadioButton rdbtnMareomotriz;
	public  JRadioButton rdbtnSolar;
	private ButtonGroup btngrp;
	public JButton btnSalir;
	
	private JPanel paneltabla;
	private JPanel panelbotonera;

	public clsTablaE te;

	public clsTablaS ts;

	public clsTablaM tm;

	public clsTablaH th;
	
	public int a;

	public JTextField textField;
	
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
	 * Constructor del JFrame que incluye escuchadores varios.
	 * @param titulo Título de la ventana.
	 */	
	public clsListaM(String titulo, String funcion, String cliente)
	{
		super(titulo);
		getContentPane().setLayout(new BorderLayout());
		paneltabla=new JPanel();
	    panelbotonera=new JPanel();
	    
	    getContentPane().setBackground(Color.white);
	    panelbotonera.setLayout(new FlowLayout());
	    getContentPane().add(paneltabla, BorderLayout.NORTH);
	    getContentPane().add(panelbotonera, BorderLayout.SOUTH);
	    panelbotonera.setBackground(Color.white);

		lblInformacion = new JLabel("Ordenar por:");
		Font labelFont = lblInformacion.getFont();
		lblInformacion.setFont(new Font(labelFont.getName(), Font.PLAIN, 24));
		panelbotonera.add(lblInformacion, BorderLayout.CENTER);
		
		rdbtnEolica = new JRadioButton("Eólicas");
		rdbtnEolica.setBounds(51, 400, 128, 23);
		rdbtnEolica.setBackground(Color.WHITE);
		panelbotonera.add(rdbtnEolica);
		
		rdbtnHidraulica = new JRadioButton("Hidrúalicas");
		rdbtnHidraulica.setBounds(262, 400, 109, 23);
		rdbtnHidraulica.setBackground(Color.WHITE);
		panelbotonera.add(rdbtnHidraulica);
		
		rdbtnMareomotriz = new JRadioButton("Mareomotrices");
		rdbtnMareomotriz.setBounds(426, 400, 300, 23);
		rdbtnMareomotriz.setBackground(Color.WHITE);
		panelbotonera.add(rdbtnMareomotriz);
		
		rdbtnSolar = new JRadioButton("Solares");
		rdbtnSolar.setBounds(426, 400, 109, 23);
		rdbtnSolar.setBackground(Color.WHITE);
		panelbotonera.add(rdbtnSolar);
		
		btngrp = new ButtonGroup();
		btngrp.add(rdbtnEolica);
		btngrp.add(rdbtnHidraulica);
		btngrp.add(rdbtnMareomotriz);
		btngrp.add(rdbtnSolar);
		
		miVentana=this;

		if(funcion.equals(clsConstantes.VISUALIZAR))
		{
			btnSalir = new JButton("Salir");
		}
		else if(funcion.equals(clsConstantes.BORRAR))
		{
			btnSalir = new JButton("Borrar");
		}
		else if(funcion.equals(clsConstantes.MODIFICAR))
		{
			btnSalir = new JButton("Modificar");
		}
		else if(funcion.equals(clsConstantes.VENTA))
		{
			btnSalir = new JButton("Venta");
			textField = new JTextField("Cantidad");
			textField.addFocusListener(new FocusAdapter() {
				  public void focusGained(FocusEvent fEvt) {
				    JTextField tField = (JTextField)fEvt.getSource();
				    tField.setText("");
				  }
				});
			panelbotonera.add(textField);
		}
		btnSalir.setBounds(612, 400, 89, 23);
		panelbotonera.add(btnSalir);
		
		this.setPreferredSize(new Dimension(750,500));

		clsGestor objGestor = new clsGestor();
		
		listaEolica = objGestor.ListaEolica();
		listaHidraulica = objGestor.ListaHidraulica();
		listaMareomotriz = objGestor.ListaMareomotriz();
		listaSolar = objGestor.ListaSolar();
		
		te=new clsTablaE(listaEolica);
		th=new clsTablaH(listaHidraulica);
		tm=new clsTablaM(listaMareomotriz);
		ts=new clsTablaS(listaSolar);
		
		
		/*Escuchadores*/
		rdbtnEolica.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				logger.log( Level.INFO, "Seleccionando máquinas EÓLICAS");
				te.setOpaque(true); //content panes must be opaque
				getContentPane().add(te, BorderLayout.NORTH);
		        pack();
		        setVisible(true);
			}
		});
		rdbtnHidraulica.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				logger.log( Level.INFO, "Seleccionando máquinas HIDRÁULICAS");
				//Collections.sort(listaEolica, new clsOrdenarPorElo());				
				th.setOpaque(true); //content panes must be opaque
				getContentPane().add(th, BorderLayout.NORTH);				
		        pack();
		        setVisible(true);
			}
		});
		rdbtnMareomotriz.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				logger.log( Level.INFO, "Seleccionando máquinas MAREOMOTRICES");
				tm.setOpaque(true); //content panes must be opaque
				getContentPane().add(tm, BorderLayout.NORTH);				
		        pack();
		        setVisible(true);
			}
		});
		rdbtnSolar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				logger.log( Level.INFO, "Seleccionando máquinas SOLARES");
				ts.setOpaque(true); //content panes must be opaque
				getContentPane().add(ts, BorderLayout.NORTH);				
		        pack();
		        setVisible(true);
			}
		});
		
		
		btnSalir.addActionListener(new ActionListener()
		{
			

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(funcion.equals(clsConstantes.VISUALIZAR))
				{
					dispose();
				}
				else if(funcion.equals(clsConstantes.BORRAR))
				{
					if(rdbtnEolica.isSelected())
					{
						a=te.getFila();
						if(a>-1)
						{
							logger.log( Level.INFO, "Seleccionando BORRAR EÓLICA");
							objGestor.BorrarMaquina(a);
							listaEolica = objGestor.ListaEolica();
							clsTablaE te1=new clsTablaE(listaEolica);
							te1.setOpaque(true); //content panes must be opaque
							getContentPane().add(te1, BorderLayout.NORTH);
					        pack();
					        setVisible(true);
						}
					}
					else if(rdbtnHidraulica.isSelected())
					{
						a=th.getFila();
						if(a>-1)
						{
							logger.log( Level.INFO, "Seleccionando BORRAR HIDRÁULICA");
							objGestor.BorrarMaquina(a);
							listaHidraulica = objGestor.ListaHidraulica();
							clsTablaH th1=new clsTablaH(listaHidraulica);
							th1.setOpaque(true); //content panes must be opaque
							getContentPane().add(th1, BorderLayout.NORTH);
					        pack();
					        setVisible(true);	
						}
					}
					else if(rdbtnMareomotriz.isSelected())
					{
						a=tm.getFila();
						if(a>-1)
						{
							logger.log( Level.INFO, "Seleccionando BORRAR MAREOMOTRIZ");
							objGestor.BorrarMaquina(a);
							listaMareomotriz = objGestor.ListaMareomotriz();
							clsTablaM tm1=new clsTablaM(listaMareomotriz);
							tm1.setOpaque(true); //content panes must be opaque
							getContentPane().add(tm1, BorderLayout.NORTH);
					        pack();
					        setVisible(true);
						}
					}
					else if(rdbtnSolar.isSelected())
					{
						a=ts.getFila();
						if(a>-1)
						{
							logger.log( Level.INFO, "Seleccionando BORRAR SOLAR");
							objGestor.BorrarMaquina(a);
							listaSolar = objGestor.ListaSolar();
							clsTablaS ts1=new clsTablaS(listaSolar);
							ts1.setOpaque(true); //content panes must be opaque
							getContentPane().add(ts1, BorderLayout.NORTH);
					        pack();
					        setVisible(true);
						}
					}
				}
				else if(funcion.equals(clsConstantes.VENTA)){
					if(rdbtnEolica.isSelected())
					{
						a=te.getFila();
						if(a>-1)
						{
							if(textField.getText().length()>0)
							{
								try
								{
									logger.log( Level.INFO, "Seleccionando VENDER EÓLICA");
									clsMaquina_Eolica m;
									m=objGestor.ObtenerEolica(a);
									objGestor.CrearVenta(m.getId(), cliente, Integer.parseInt(textField.getText()));
									dispose();
									JOptionPane.showMessageDialog(null, "Se ha registrado una nueva venta correctamente.");
								}
							 catch (NumberFormatException e) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "El campo de Cantidad debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
							 	}
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Elija una cantidad", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
					else if(rdbtnHidraulica.isSelected())
					{
						a=th.getFila();
						if(a>-1)
						{
							if(textField.getText().length()>0)
							{
								try
								{
									logger.log( Level.INFO, "Seleccionando VENDER HIDRÁULICA");
									clsMaquina_Hidraulica m;
									m=objGestor.ObtenerHidraulica(a);
									objGestor.CrearVenta(m.getId(), cliente, Integer.parseInt(textField.getText()));
									dispose();
									JOptionPane.showMessageDialog(null, "Se ha registrado una nueva venta correctamente.");
								}
							 catch (NumberFormatException e) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "El campo de Cantidad debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
							 	}
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Elija una cantidad", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
					else if(rdbtnMareomotriz.isSelected())
					{
						a=tm.getFila();
						if(a>-1)
						{
							if(textField.getText().length()>0)
							{
								try
								{
									logger.log( Level.INFO, "Seleccionando VENDER MAREOMOTRIZ");
									clsMaquina_Mareomotriz m;
									m=objGestor.ObtenerMareomotriz(a);
									objGestor.CrearVenta(m.getId(), cliente, Integer.parseInt(textField.getText()));
									dispose();
									JOptionPane.showMessageDialog(null, "Se ha registrado una nueva venta correctamente.");
								}
							 catch (NumberFormatException e) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "El campo de Cantidad debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
							 	}
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Elija una cantidad", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
					else if(rdbtnSolar.isSelected())
					{
						a=ts.getFila();
						if(a>-1)
						{
							if(textField.getText().length()>0)
							{
								try
								{
									logger.log( Level.INFO, "Seleccionando VENDER SOLAR");
									clsMaquina_Solar m;
									m=objGestor.ObtenerSolar(a);
									objGestor.CrearVenta(m.getId(), cliente, Integer.parseInt(textField.getText()));
									dispose();
									JOptionPane.showMessageDialog(null, "Se ha registrado una nueva venta correctamente.");
								}
							 catch (NumberFormatException e) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "El campo de Cantidad debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
							 	}
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Elija una cantidad", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
				
				else if(funcion.equals(clsConstantes.MODIFICAR))
				{
					if(rdbtnEolica.isSelected())
					{
						a=te.getFila();
						if(a>-1)
						{
							logger.log( Level.INFO, "Seleccionando MODIFICAR EÓLICA");
							clsMaquina_Eolica mod;
							mod=objGestor.ObtenerEolica(a);
							clsModificarEolica window = new clsModificarEolica(mod, miVentana);
							window.setVisible(true);
						}
					}
					else if(rdbtnHidraulica.isSelected())
					{
						a=th.getFila();
						if(a>-1)
						{
							logger.log( Level.INFO, "Seleccionando MODIFICAR HIDRÁULICA");
							clsMaquina_Hidraulica mod;
							mod=objGestor.ObtenerHidraulica(a);
							clsModificarHidraulica window = new clsModificarHidraulica(mod, miVentana);
							window.setVisible(true);
						}					
					}
					else if(rdbtnMareomotriz.isSelected())
					{
						a=tm.getFila();
						if(a>-1)
						{
							logger.log( Level.INFO, "Seleccionando MODIFICAR MAREOMOTRIZ");
							clsMaquina_Mareomotriz mod;
							mod=objGestor.ObtenerMareomotriz(a);
							clsModificarMareomotriz window = new clsModificarMareomotriz(mod, miVentana);
							window.setVisible(true);
						}											
					}
					else if(rdbtnSolar.isSelected())
					{
						a=ts.getFila();
						if(a>-1)
						{
							logger.log( Level.INFO, "Seleccionando MODIFICAR SOLAR");
							clsMaquina_Solar mod;
							mod=objGestor.ObtenerSolar(a);
							clsModificarSolar window = new clsModificarSolar(mod, miVentana);
							window.setVisible(true);
						}					
					}
				}
			}
		});
	}	
}	