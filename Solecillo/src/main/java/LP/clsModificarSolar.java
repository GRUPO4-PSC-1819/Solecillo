package LP;
 import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
 import javax.swing.JFrame;
import javax.swing.JOptionPane;
 import LN.clsGestor;
import LN.clsMaquina_Eolica;
import LN.clsMaquina_Mareomotriz;
import LN.clsMaquina_Solar;
/**
 * Clase que generará una ventana que hereda de clsAltaUsuario para modificar los datos de un usuario existente en la base de datos.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */
public class clsModificarSolar extends clsAltaSolar
{
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructor de la ventana que aprovecha la interfaz gráfica de clsAltaUsuario, incluyendo los datos de un usuario dado.
	 * @param usuario El usuario que está modificando sus datos.
	 * @param miVentana La ventana clsEleccion de la que proviene el usuario, que tendrá su atributo "usuario" modificado al cambiar los datos referentes al usuario.
	 */
	public clsModificarSolar(clsMaquina_Solar maq, JFrame miVentana)
	{
		
		setTitle("Modificar máquina solar");
		txtNombre.setText(maq.getNombre());
		txtColor.setText(maq.getColor());
		txtValor.setText(Double.toString(maq.getValor()));
		txtFabricante.setText(maq.getFabricante());
		txtNombrePueblo.setText(maq.getNombre_pueblo());
		txtNombreCampo.setText(maq.getNombre_campo());
		
		modifmaq=true;
				
		/*Escuchadores*/
		btnAceptar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				clsGestor objGestor=new clsGestor();
				if(txtNombre.getText().length()>0&&txtColor.getText().length()>0&&txtValor.getText().length()>0&&txtFabricante.getText().length()>0&&txtNombrePueblo.getText().length()>0&&txtNombreCampo.getText().length()>0)
				{		
						//logger.log( Level.INFO, "Dando de alta máquina mareomotriz "+txtNombre.getText());
						try {
							objGestor.ModificarMaquinaSolar(maq.getId(), txtNombre.getText(), txtColor.getText(), Double.parseDouble(txtValor.getText()), txtFabricante.getText().toUpperCase(), txtNombrePueblo.getText(), txtNombreCampo.getText());
							JOptionPane.showMessageDialog(null, "Se ha modificado la máquina solar correctamente.");
							dispose();
							clsListaM.listaSolar = objGestor.ListaSolar();
							clsTablaS ts1=new clsTablaS(clsListaM.listaSolar);
							ts1.setOpaque(true); //content panes must be opaque
							clsListaM.miVentana.getContentPane().add(ts1, BorderLayout.NORTH);
					        pack();
					        clsListaM.miVentana.setVisible(true);
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "El campo 'Valor' debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
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