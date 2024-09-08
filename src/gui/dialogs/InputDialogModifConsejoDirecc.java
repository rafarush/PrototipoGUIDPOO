package gui.dialogs;


import gui.mainFrame.MainFrame;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import runner.Runner;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Cursor;
import java.util.ArrayList;

import logica.Fct;
import logica.PersonalApoyo;
import logica.Profesor;



public class InputDialogModifConsejoDirecc extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel upperBarPanel = new JPanel();
	private JTextField nombreTextField;
	private JTextField ciTextField;
	private JComboBox cargoComboBox;


	/**
	 * Crea el frame para agregar
	 */
	public InputDialogModifConsejoDirecc(final Profesor profe) {
		setUndecorated(true);
		setModal(true);
		setBounds(100, 100, 538, 360);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		
		final JLabel closeBotton = new JLabel("");
		closeBotton.setIcon(new ImageIcon(InputDialogModifConsejoDirecc.class.getResource("/gui/utils/closeBotton.png")));
		closeBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				closeBotton.setIcon(new ImageIcon(InputDialogModifConsejoDirecc.class.getResource("/gui/utils/closeBottonSelected1.png")));
				closeBotton.setToolTipText("Cerrar");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				closeBotton.setIcon(new ImageIcon(InputDialogModifConsejoDirecc.class.getResource("/gui/utils/closeBotton.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "¿Seguro que desea cerrar la entrada de datos?\n Su progreso no se guardará", "Confirmación", 
						JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
					dispose();
				}
			}
		});
		closeBotton.setBounds(500, 0, 27, 34);
		upperBarPanel.add(closeBotton);
		
		
		upperBarPanel.setBackground(new Color(255, 255, 255));
		upperBarPanel.setBounds(0, 0, 537, 34);
		upperBarPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(upperBarPanel);
		upperBarPanel.setLayout(null);
		
		
		JLabel nameFrame = new JLabel("Entrada de datos (Consejo de Direcci\u00F3n)");
		nameFrame.setBounds(10, 0, 254, 34);
		upperBarPanel.add(nameFrame);
		{
		    JPanel mainPanel = new JPanel();
			mainPanel.setBackground(Color.WHITE);
			mainPanel.setBounds(0, 34, 537, 326);
			getContentPane().add(mainPanel);
			mainPanel.setLayout(null);
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(0, 204, 255));
			panel.setBounds(387, 0, 150, 326);
			mainPanel.add(panel);
			
			nombreTextField = new JTextField();
			nombreTextField.setToolTipText("Nombre de la persona");
			nombreTextField.setColumns(10);
			nombreTextField.setBounds(10, 54, 269, 20);
			nombreTextField.setEditable(false);
			mainPanel.add(nombreTextField);
			
			JLabel lblNombre = new JLabel("Nombre y apellidos:");
			lblNombre.setForeground(new Color(51, 51, 51));
			lblNombre.setBounds(10, 29, 166, 20);
			mainPanel.add(lblNombre);
			
			ciTextField = new JTextField();
			ciTextField.setToolTipText("Carn\u00E9 de identidad de la persona");
			ciTextField.setColumns(10);
			ciTextField.setBounds(10, 110, 269, 20);
			ciTextField.setEditable(false);
			mainPanel.add(ciTextField);
			
			JLabel lblCarneIden = new JLabel("Carn\u00E9 de Identidad:");
			lblCarneIden.setBounds(10, 85, 166, 20);
			mainPanel.add(lblCarneIden);
			
			JLabel lblCargo = new JLabel("Cargo en el Consejo de Direcci\u00F3n:");
			lblCargo.setBounds(10, 141, 189, 20);
			mainPanel.add(lblCargo);
			
			cargoComboBox = new JComboBox();
			cargoComboBox.setToolTipText("Cargo del profesor en el Consejo de Direcci\u00F3n");
			//definirCargosDisponibles(Fct.getInstance().buscarCargosCDFaltantes());
			//cargoComboBox.setModel(new DefaultComboBoxModel(new String[] {"Director", "Subdirector Docente", "Subdirector de Investigaciones y Posgrado", "Subdirector de Extensi\u00F3n Universitaria", "Jefe de Laboratorios"}));
			cargoComboBox.setModel(new DefaultComboBoxModel(Fct.getInstance().buscarCargosCDFaltantes().toArray()));
			cargoComboBox.setBounds(10, 167, 269, 20);
			mainPanel.add(cargoComboBox);
			
			final JLabel inputBotton = new JLabel("");
			inputBotton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			inputBotton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					inputBotton.setIcon(new ImageIcon(InputDialogEst.class.getResource("/gui/utils/addBottonJDialogSelected.png")));
					inputBotton.setToolTipText("Agregar datos");
				}
				@Override
				public void mouseExited(MouseEvent e) {
					inputBotton.setIcon(new ImageIcon(InputDialogEst.class.getResource("/gui/utils/addBottonJDialog.png")));
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					profe.setCargoConsejoDireccion(cargoComboBox.getSelectedItem().toString());
					JOptionPane.showMessageDialog(null, "Se ha asignado el cargo en el Consejo de Dirección con éxito");
					dispose();
				}
			});
			inputBotton.setIcon(new ImageIcon(InputDialogEst.class.getResource("/gui/utils/addBottonJDialog.png")));
			inputBotton.setBounds(33, 269, 63, 21);
			mainPanel.add(inputBotton);
			
			final JLabel cancelBotton = new JLabel("");
			cancelBotton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			cancelBotton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					cancelBotton.setIcon(new ImageIcon(InputDialogEst.class.getResource("/gui/utils/cancelBottonSelected.png")));
					cancelBotton.setToolTipText("Cancelar entrada de datos");
				}
				@Override
				public void mouseExited(MouseEvent e) {
					cancelBotton.setIcon(new ImageIcon(InputDialogEst.class.getResource("/gui/utils/cancelBotton.png")));
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					if(JOptionPane.showConfirmDialog(null, "¿Seguro que desea cancelar la entrada de datos?\n Su progreso no se guardará", "Confirmación", 
							JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
						dispose();
					}
				}
			});
			cancelBotton.setIcon(new ImageIcon(InputDialogModifConsejoDirecc.class.getResource("/gui/utils/cancelBotton.png")));
			cancelBotton.setBounds(136, 269, 63, 21);
			mainPanel.add(cancelBotton);
			
			llenarTextFields(profe);
		}
	}
	
	
	private void llenarTextFields(Profesor profe){
		ciTextField.setText(profe.getID());//Escribe el carne
		nombreTextField.setText(profe.getNombre());//Escribe el nombre
		//cargoComboBox.setSelectedItem(profe.getCargoConsejoDireccion());//Selecciona el cargo
	}
}
