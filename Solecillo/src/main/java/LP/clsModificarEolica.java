package LP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import LN.clsGestor;
import LN.clsMaquina_Eolica;

/**
 * Clase que generará una ventana que hereda de clsAltaEolica para modificar los datos de una máquina eólica en la base de datos.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Josune Ordoñez (Josune07)
 */
public class clsModificarEolica extends clsAltaEolica
{
	private static final long serialVersionUID = 1L;
	
	
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
	 * Constructor de la ventana que aprovecha la interfaz gráfica de clsAltaEolica.
	 * @param maq La máquina de la cual se van a modificar los datos.
	 * @param ventanita La ventana clsListaM de la que proviene la máquina, y que se utilizará para el refresco de los datos modificados.
	 */
	public clsModificarEolica(clsMaquina_Eolica maq, JFrame miVentana)
	{
		setTitle("Modificar máquina eólica");
		txtNombre.setText(maq.getNombre());
		txtColor.setText(maq.getColor());
		txtValor.setText(Double.toString(maq.getValor()));
		txtFabricante.setText(maq.getFabricante());
		txtEstado.setSelectedItem(maq.getEstado());
		txtNombrePueblo.setText(maq.getNombre_pueblo());
		txtNombreCampo.setText(maq.getNombre_campo());
		txtAltura.setText(Double.toString(maq.getAltura()));
		txtDiametro.setText(Double.toString(maq.getDiametro()));
		
		modifmaq=true;
				
		/*Escuchadores*/
		btnAceptar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				clsGestor objGestor=new clsGestor();
				if(txtNombre.getText().length()>0&&txtColor.getText().length()>0&&txtValor.getText().length()>0&&txtFabricante.getText().length()>0&&txtNombrePueblo.getText().length()>0&&txtNombreCampo.getText().length()>0&&txtAltura.getText().length()>0&&txtDiametro.getText().length()>0)
				{		
						try {
							logger.log( Level.INFO, "Modificando eólica de nombre: "+txtNombre.getText());
							objGestor.ModificarMaquinaEolica(maq.getId(), txtNombre.getText(), txtColor.getText(), Double.parseDouble(txtValor.getText()), txtFabricante.getText(), String.valueOf(txtEstado.getSelectedItem()), txtNombrePueblo.getText(), txtNombreCampo.getText(), Double.parseDouble(txtAltura.getText()), Double.parseDouble(txtDiametro.getText()));
							JOptionPane.showMessageDialog(null, "Se ha modificado la máquina eólica correctamente.");
							dispose();
							clsListaM.listaEolica = objGestor.ListaEolica();
							clsTablaE te1=new clsTablaE(clsListaM.listaEolica);
							te1.setOpaque(true); //content panes must be opaque
							clsListaM.miVentana.getContentPane().add(te1, BorderLayout.NORTH);
					        pack();
					        clsListaM.miVentana.setVisible(true);
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Los campos 'Valor', 'Altura' y 'Diametro' deben ser un número", "Error", JOptionPane.ERROR_MESSAGE);
						}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Introduzca todos los datos.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		this.btnCancelar.addActionListener(new ActionListener() 
			{
			public void actionPerformed(ActionEvent arg0) 
			{
				dispose();
			}
		});
	}
}