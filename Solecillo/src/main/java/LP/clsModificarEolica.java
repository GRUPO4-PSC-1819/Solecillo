package LP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import javax.swing.JFrame;
import java.awt.BorderLayout;

import javax.swing.JOptionPane;

import LN.clsGestor;
import LN.clsMaquina_Eolica;
import LN.clsUsuario;
/**
 * Clase que generará una ventana que hereda de clsAltaUsuario para modificar los datos de un usuario existente en la base de datos.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */
public class clsModificarEolica extends clsAltaEolica
{
	private static final long serialVersionUID = 1L;
	
	clsMaquina_Eolica modif;
	
	/**
	 * Constructor de la ventana que aprovecha la interfaz gráfica de clsAltaUsuario, incluyendo los datos de un usuario dado.
	 * @param usuario El usuario que está modificando sus datos.
	 * @param ventanita La ventana clsEleccion de la que proviene el usuario, que tendrá su atributo "usuario" modificado al cambiar los datos referentes al usuario.
	 */
	public clsModificarEolica(clsMaquina_Eolica maq, JFrame miVentana)
	{
		setTitle("Modificar máquina eólica");
		txtNombre.setText(maq.getNombre());
		txtColor.setText(maq.getColor());
		txtValor.setText(Double.toString(maq.getValor()));
		txtFabricante.setText(maq.getFabricante());
		txtNombrePueblo.setText(maq.getNombre_pueblo());
		txtNombreCampo.setText(maq.getNombre_campo());
		txtAltura.setText(Double.toString(maq.getAltura()));
		txtDiametro.setText(Double.toString(maq.getDiametro()));
		
		modifmaq=true;
				
		/*Escuchadores*/
		btnAceptar.addActionListener(new ActionListener() 
		{
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) 
			{
				clsGestor objGestor=new clsGestor();
				if(txtNombre.getText().length()>0&&txtColor.getText().length()>0&&txtValor.getText().length()>0&&txtFabricante.getText().length()>0&&txtNombrePueblo.getText().length()>0&&txtNombreCampo.getText().length()>0&&txtAltura.getText().length()>0&&txtDiametro.getText().length()>0)
				{		
						//logger.log( Level.INFO, "Dando de alta máquina eólica "+txtNombre.getText());
						try {
							objGestor.ModificarMaquinaEolica(maq.getId(), txtNombre.getText(), txtColor.getText(), Double.parseDouble(txtValor.getText()), txtFabricante.getText().toUpperCase(), txtNombrePueblo.getText(), txtNombreCampo.getText(), Double.parseDouble(txtAltura.getText()), Double.parseDouble(txtDiametro.getText()));
							JOptionPane.showMessageDialog(null, "Se ha modificado la máquina eólica correctamente.");
							dispose();
							clsListaM.listaEolica = objGestor.ListaEolica();
							clsTablaE te1=new clsTablaE(clsListaM.listaEolica);
							te1.setOpaque(true); //content panes must be opaque
							clsListaM.miVentana.getContentPane().add(te1, BorderLayout.NORTH);
					        pack();
					        clsListaM.miVentana.setVisible(true);
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
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