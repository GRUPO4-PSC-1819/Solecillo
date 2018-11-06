package LP;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import LN.clsVenta;
import Persistencia.clsBD;

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
	private JRadioButton rdbtnEolica;
	private JRadioButton rdbtnHidraulica;
	private JRadioButton rdbtnMareomotriz;
	private JRadioButton rdbtnSolar;
	private ButtonGroup btngrp;
	private JButton btnSalir;
	
	private JPanel paneltabla;
	private JPanel panelbotonera;

	private clsTablaE te;

	private clsTablaS ts;

	private clsTablaM tm;

	private clsTablaH th;

	private JTextField textField;
	
	private static final boolean ANYADIR_A_FIC_LOG = true;
	
	/*Logger de la clase*/
	private static Logger logger = Logger.getLogger( "Mariano" );
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
			FileOutputStream fLog = new FileOutputStream( "Mariano"+".log" , ANYADIR_A_FIC_LOG );
			Handler h = new StreamHandler( fLog, f );
			h.setLevel( Level.FINEST );
			logger.addHandler( h );
			}
		catch (SecurityException | IOException e) 
		{
			logger.log( Level.SEVERE, "No se ha podido crear fichero de log en clase "+ clsListaM.class.getName() );
		}
		logger.log( Level.INFO, "" );
		logger.log( Level.INFO, DateFormat.getDateTimeInstance( DateFormat.LONG, DateFormat.LONG ).format( new Date() ) );
	}
	/**
	 * Constructor del JFrame que incluye escuchadores varios.
	 * @param titulo Título de la ventana.
	 */	
	public clsListaM(String titulo, String funcion,String cliente)
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
			//MEJORARLOOOOOO
			btnSalir = new JButton("Venta");
			textField = new JTextField("Cantidad");
			panelbotonera.add(textField, BorderLayout.WEST);
			//paneltabla.add(textField);
			//textField.setColumns(10);
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
				logger.log( Level.INFO, "Obteniendo los jugadores ordenados por Elo");
				//Collections.sort(listaEolica, new clsOrdenarPorElo());
				
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
				logger.log( Level.INFO, "Obteniendo los jugadores ordenados por Elo");
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
				logger.log( Level.INFO, "Obteniendo los jugadores ordenados por Elo");
				//Collections.sort(listaEolica, new clsOrdenarPorElo());
				
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
				logger.log( Level.INFO, "Obteniendo los jugadores ordenados por Elo");
				//Collections.sort(listaEolica, new clsOrdenarPorElo());
				
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
						int a=te.getFila();
						if(a>-1)
						{
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
						int a=th.getFila();
						if(a>-1)
						{
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
						int a=tm.getFila();
						if(a>-1)
						{
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
						int a=ts.getFila();
						if(a>-1)
						{
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
						int a=te.getFila();
						if(a>-1)
						{
							System.out.println(listaEolica.get(a-1));
							clsBD.insertarDatoTablaBD(new clsVenta(listaEolica.get(a-1).getId(),cliente,(int) (listaEolica.get(a-1).getValor()*Double.parseDouble(textField.getText()))));
							JOptionPane.showMessageDialog(null, "Se ha registrado una nueva venta.");
						}
					}
					else if(rdbtnHidraulica.isSelected())
					{
						int a=th.getFila();
						if(a>-1)
						{
					        clsBD.insertarDatoTablaBD(new clsVenta(listaHidraulica.get(a-1).getId(),cliente,(int)(listaEolica.get(a-1).getValor()* Double.parseDouble(textField.getText()))));
						}
					}
					else if(rdbtnMareomotriz.isSelected())
					{
						int a=tm.getFila();
						if(a>-1)
						{
					        clsBD.insertarDatoTablaBD(new clsVenta(listaMareomotriz.get(a-1).getId(),cliente,(int)(listaEolica.get(a-1).getValor()* Double.parseDouble(textField.getText()))));
						}
					}
					else if(rdbtnSolar.isSelected())
					{
						int a=ts.getFila();
						if(a>-1)
						{
					        clsBD.insertarDatoTablaBD(new clsVenta(listaSolar.get(a-1).getId(),cliente,(int)(listaEolica.get(a-1).getValor()* Double.parseDouble(textField.getText()))));
						}
					}
				}
				
				else if(funcion.equals(clsConstantes.MODIFICAR))
				{
					if(rdbtnEolica.isSelected())
					{
						int a=te.getFila();
						if(a>-1)
						{
							clsMaquina_Eolica mod;
							mod=objGestor.ObtenerEolica(a);
							clsModificarEolica window = new clsModificarEolica(mod, miVentana);
							window.setVisible(true);
						}
					}
					else if(rdbtnHidraulica.isSelected())
					{
						int a=th.getFila();
						if(a>-1)
						{
							clsMaquina_Hidraulica mod;
							mod=objGestor.ObtenerHidraulica(a);
							clsModificarHidraulica window = new clsModificarHidraulica(mod, miVentana);
							window.setVisible(true);
						}					
					}
					else if(rdbtnMareomotriz.isSelected())
					{
						int a=tm.getFila();
						if(a>-1)
						{
							clsMaquina_Mareomotriz mod;
							mod=objGestor.ObtenerMareomotriz(a);
							clsModificarMareomotriz window = new clsModificarMareomotriz(mod, miVentana);
							window.setVisible(true);
						}											
					}
					else if(rdbtnSolar.isSelected())
					{
						int a=ts.getFila();
						if(a>-1)
						{
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