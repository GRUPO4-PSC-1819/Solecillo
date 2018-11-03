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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
	
	private JLabel lblInformacion;
	private JRadioButton rdbtnEolica;
	private JRadioButton rdbtnHidraulica;
	private JRadioButton rdbtnMareomotriz;
	private JRadioButton rdbtnSolar;
	private ButtonGroup btngrp;
	private JButton btnSalir;
	
	private JPanel paneltabla;
	private JPanel panelbotonera;
	
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
	public clsListaM(String titulo)
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
		rdbtnMareomotriz.setBounds(426, 400, 109, 23);
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

		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(612, 400, 89, 23);
		panelbotonera.add(btnSalir);
		
		this.setPreferredSize(new Dimension(750,500));

		clsGestor objGestor = new clsGestor();
		listaEolica = objGestor.ListaEolica();
		listaHidraulica = objGestor.ListaHidraulica();
		listaMareomotriz = objGestor.ListaMareomotriz();
		listaSolar = objGestor.ListaSolar();
		
		/*Escuchadores*/
		rdbtnEolica.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				logger.log( Level.INFO, "Obteniendo los jugadores ordenados por Elo");
				//Collections.sort(listaEolica, new clsOrdenarPorElo());
				
				clsTablaE t=new clsTablaE(listaEolica);
				t.setOpaque(true); //content panes must be opaque
				getContentPane().add(t, BorderLayout.NORTH);
				
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
				
				clsTablaH t=new clsTablaH(listaHidraulica);
				t.setOpaque(true); //content panes must be opaque
				getContentPane().add(t, BorderLayout.NORTH);
				
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
				
				clsTablaM t=new clsTablaM(listaMareomotriz);
				t.setOpaque(true); //content panes must be opaque
				getContentPane().add(t, BorderLayout.NORTH);
				
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
				
				clsTablaS t=new clsTablaS(listaSolar);
				t.setOpaque(true); //content panes must be opaque
				getContentPane().add(t, BorderLayout.NORTH);
				
		        pack();
		        setVisible(true);
			}
		});
		
		
		btnSalir.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				dispose();	
			}
		});
	}	
}	