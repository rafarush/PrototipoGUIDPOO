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

import logica.Estudiante;



public class InputDialogEst extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel upperBarPanel = new JPanel();
	private JTextField nombreTextField;
	private JTextField ciTextField;
	private JTextField direccTextField;
	private JTextField centroLaboralTextField;
	private JTextField organismoTextField;
	private JComboBox annoComboBox;


	/**
	 * Crea frame para agregar
	 */
	public InputDialogEst() {
		setUndecorated(true);
		setModal(true);
		setBounds(100, 100, 646, 442);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		
		final JLabel closeBotton = new JLabel("");
		closeBotton.setIcon(new ImageIcon(InputDialogEst.class.getResource("/gui/utils/closeBotton.png")));
		closeBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				closeBotton.setIcon(new ImageIcon(InputDialogEst.class.getResource("/gui/utils/closeBottonSelected1.png")));
				closeBotton.setToolTipText("Cerrar");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				closeBotton.setIcon(new ImageIcon(InputDialogEst.class.getResource("/gui/utils/closeBotton.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "¿Seguro que desea cerrar la entrada de datos?\n Su progreso no se guardará", "Confirmación", 
						JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
					Runner.inputEst.dispose();
				}
			}
		});
		closeBotton.setBounds(609, 0, 27, 34);
		upperBarPanel.add(closeBotton);
		
		
		upperBarPanel.setBackground(new Color(255, 255, 255));
		upperBarPanel.setBounds(0, 0, 646, 34);
		upperBarPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(upperBarPanel);
		upperBarPanel.setLayout(null);
		
		
		JLabel nameFrame = new JLabel("Entrada de datos (Estudiante)");
		nameFrame.setBounds(10, 0, 233, 34);
		upperBarPanel.add(nameFrame);
		{
		    JPanel mainPanel = new JPanel();
			mainPanel.setBackground(Color.WHITE);
			mainPanel.setBounds(0, 34, 646, 408);
			getContentPane().add(mainPanel);
			mainPanel.setLayout(null);
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(0, 204, 255));
			panel.setBounds(453, 0, 193, 408);
			mainPanel.add(panel);
			
			nombreTextField = new JTextField();
			nombreTextField.setToolTipText("Nombre de la persona");
			nombreTextField.setColumns(10);
			nombreTextField.setBounds(10, 54, 223, 20);
			mainPanel.add(nombreTextField);
			
			JLabel lblNombre = new JLabel("Nombre y apellidos:");
			lblNombre.setForeground(new Color(51, 51, 51));
			lblNombre.setBounds(10, 29, 166, 20);
			mainPanel.add(lblNombre);
			
			ciTextField = new JTextField();
			ciTextField.setToolTipText("Carn\u00E9 de identidad de la persona");
			ciTextField.setColumns(10);
			ciTextField.setBounds(10, 110, 223, 20);
			mainPanel.add(ciTextField);
			
			JLabel lblCarneIden = new JLabel("Carn\u00E9 de Identidad:");
			lblCarneIden.setBounds(10, 85, 166, 20);
			mainPanel.add(lblCarneIden);
			
			direccTextField = new JTextField();
			direccTextField.setToolTipText("Direcci\u00F3n particular de la persona");
			direccTextField.setColumns(10);
			direccTextField.setBounds(10, 171, 223, 20);
			mainPanel.add(direccTextField);
			
			JLabel lblDirecc = new JLabel("Direcci\u00F3n Particular:");
			lblDirecc.setBounds(10, 141, 166, 20);
			mainPanel.add(lblDirecc);
			
			JLabel lblAnyo = new JLabel("A\u00F1o:");
			lblAnyo.setBounds(10, 319, 46, 14);
			mainPanel.add(lblAnyo);
			
			final JComboBox anyoComboBox = new JComboBox();
			anyoComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6"}));
			anyoComboBox.setBounds(10, 337, 80, 20);
			mainPanel.add(anyoComboBox);
			
			JLabel lblCentroLaboral = new JLabel("Centro Laboral:");
			lblCentroLaboral.setBounds(10, 202, 166, 20);
			mainPanel.add(lblCentroLaboral);
			
			organismoTextField = new JTextField();
			organismoTextField.setToolTipText("Direcci\u00F3n particular de la persona");
			organismoTextField.setColumns(10);
			organismoTextField.setBounds(10, 288, 223, 20);
			mainPanel.add(organismoTextField);
			
			JLabel lblOrganismo = new JLabel("Organismo al que pretence:");
			lblOrganismo.setBounds(10, 258, 166, 20);
			mainPanel.add(lblOrganismo);
			
			
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
					if(nombreTextField.getText().isEmpty() || ciTextField.getText().isEmpty() || direccTextField.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "Existen campos vacíos");
					}else{
						Runner.fct.crearPersona(ciTextField.getText(), nombreTextField.getText(),Integer.valueOf(anyoComboBox.getSelectedItem().toString()),
								centroLaboralTextField.getText(),organismoTextField.getText(), direccTextField.getText());
						
						JOptionPane.showMessageDialog(null, "Se ha añadido al estudiante con éxito");
						dispose();
					}		
				}
			});
			inputBotton.setIcon(new ImageIcon(InputDialogEst.class.getResource("/gui/utils/addBottonJDialog.png")));
			inputBotton.setBounds(97, 376, 63, 21);
			mainPanel.add(inputBotton);
			
			final JLabel cancelBotton = new JLabel("");
			cancelBotton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			cancelBotton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					cancelBotton.setIcon(new ImageIcon(InputDialogEst.class.getResource("/gui/utils/cancelBottonSelected.png")));
					cancelBotton.setToolTipText("Agregar datos");
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
			cancelBotton.setIcon(new ImageIcon(InputDialogEst.class.getResource("/gui/utils/cancelBotton.png")));
			cancelBotton.setBounds(192, 376, 63, 21);
			mainPanel.add(cancelBotton);
			
			centroLaboralTextField = new JTextField();
			centroLaboralTextField.setToolTipText("Carn\u00E9 de identidad de la persona");
			centroLaboralTextField.setColumns(10);
			centroLaboralTextField.setBounds(10, 227, 223, 20);
			mainPanel.add(centroLaboralTextField);
			
		}
	}
	
	
	/**
	 * Crea frame para agregar
	 */
	public InputDialogEst(final Estudiante estudiante) {
		setUndecorated(true);
		setModal(true);
		setBounds(100, 100, 646, 442);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		
		final JLabel closeBotton = new JLabel("");
		closeBotton.setIcon(new ImageIcon(InputDialogEst.class.getResource("/gui/utils/closeBotton.png")));
		closeBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				closeBotton.setIcon(new ImageIcon(InputDialogEst.class.getResource("/gui/utils/closeBottonSelected1.png")));
				closeBotton.setToolTipText("Cerrar");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				closeBotton.setIcon(new ImageIcon(InputDialogEst.class.getResource("/gui/utils/closeBotton.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "¿Seguro que desea cerrar la entrada de datos?\n Su progreso no se guardará", "Confirmación", 
						JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
					Runner.inputEst.dispose();
				}
			}
		});
		closeBotton.setBounds(609, 0, 27, 34);
		upperBarPanel.add(closeBotton);
		
		
		upperBarPanel.setBackground(new Color(255, 255, 255));
		upperBarPanel.setBounds(0, 0, 646, 34);
		upperBarPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(upperBarPanel);
		upperBarPanel.setLayout(null);
		
		
		JLabel nameFrame = new JLabel("Entrada de datos (Estudiante)");
		nameFrame.setBounds(10, 0, 233, 34);
		upperBarPanel.add(nameFrame);
		{
		    JPanel mainPanel = new JPanel();
			mainPanel.setBackground(Color.WHITE);
			mainPanel.setBounds(0, 34, 646, 408);
			getContentPane().add(mainPanel);
			mainPanel.setLayout(null);
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(0, 204, 255));
			panel.setBounds(453, 0, 193, 408);
			mainPanel.add(panel);
			
			nombreTextField = new JTextField();
			nombreTextField.setToolTipText("Nombre de la persona");
			nombreTextField.setColumns(10);
			nombreTextField.setBounds(10, 54, 223, 20);
			mainPanel.add(nombreTextField);
			
			JLabel lblNombre = new JLabel("Nombre y apellidos:");
			lblNombre.setForeground(new Color(51, 51, 51));
			lblNombre.setBounds(10, 29, 166, 20);
			mainPanel.add(lblNombre);
			
			ciTextField = new JTextField();
			ciTextField.setToolTipText("Carn\u00E9 de identidad de la persona (NO PUEDE MODIFICARSE)");
			ciTextField.setColumns(10);
			ciTextField.setBounds(10, 110, 223, 20);
			ciTextField.setEditable(false);
			mainPanel.add(ciTextField);
			
			JLabel lblCarneIden = new JLabel("Carn\u00E9 de Identidad:");
			lblCarneIden.setBounds(10, 85, 166, 20);
			mainPanel.add(lblCarneIden);
			
			direccTextField = new JTextField();
			direccTextField.setToolTipText("Direcci\u00F3n particular de la persona");
			direccTextField.setColumns(10);
			direccTextField.setBounds(10, 171, 223, 20);
			mainPanel.add(direccTextField);
			
			JLabel lblDirecc = new JLabel("Direcci\u00F3n Particular:");
			lblDirecc.setBounds(10, 141, 166, 20);
			mainPanel.add(lblDirecc);
			
			JLabel lblAnyo = new JLabel("A\u00F1o:");
			lblAnyo.setBounds(10, 319, 46, 14);
			mainPanel.add(lblAnyo);
			
			annoComboBox = new JComboBox();
			annoComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6"}));
			annoComboBox.setBounds(10, 337, 80, 20);
			mainPanel.add(annoComboBox);
			
			JLabel lblCentroLaboral = new JLabel("Centro Laboral:");
			lblCentroLaboral.setBounds(10, 202, 166, 20);
			mainPanel.add(lblCentroLaboral);
			
			organismoTextField = new JTextField();
			organismoTextField.setToolTipText("Direcci\u00F3n particular de la persona");
			organismoTextField.setColumns(10);
			organismoTextField.setBounds(10, 288, 223, 20);
			mainPanel.add(organismoTextField);
			
			JLabel lblOrganismo = new JLabel("Organismo al que pretence:");
			lblOrganismo.setBounds(10, 258, 166, 20);
			mainPanel.add(lblOrganismo);
			
			
			final JLabel inputBotton = new JLabel("");
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
					if(nombreTextField.getText().isEmpty() || ciTextField.getText().isEmpty() || direccTextField.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "Existen campos vacíos");
					}else{
						
						estudiante.modificarEstudiante(ciTextField.getText(), nombreTextField.getText(), Integer.valueOf(annoComboBox.getSelectedItem().toString()),
								centroLaboralTextField.getText(), organismoTextField.getText(), direccTextField.getText());
						
						/*Runner.modeloEstudiante.addRow(new Object[]{ciTextField.getText(), nombreTextField.getText(),(String)annoComboBox.getSelectedItem(),null,
						 * centroLaboralTextField.getText(),organismoTextField.getText(), direccTextField.getText()});
						 */
						/**
						 * Elimina al profe original (PROVISIONAL)
						 *//*
						((DefaultTableModel) MainFrame.getTable().getModel()).removeRow(MainFrame.getTable().getSelectedRow());
						((DefaultTableModel) MainFrame.getTable().getModel()).fireTableDataChanged();*/

						JOptionPane.showMessageDialog(null, "Se ha modificado al estudiante con éxito");
					
						dispose();
					}		
				}
			});
			inputBotton.setIcon(new ImageIcon(InputDialogEst.class.getResource("/gui/utils/addBottonJDialog.png")));
			inputBotton.setBounds(97, 376, 63, 21);
			mainPanel.add(inputBotton);
			
			final JLabel cancelBotton = new JLabel("");
			cancelBotton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					cancelBotton.setIcon(new ImageIcon(InputDialogEst.class.getResource("/gui/utils/cancelBottonSelected.png")));
					cancelBotton.setToolTipText("Agregar datos");
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
			cancelBotton.setIcon(new ImageIcon(InputDialogEst.class.getResource("/gui/utils/cancelBotton.png")));
			cancelBotton.setBounds(192, 376, 63, 21);
			mainPanel.add(cancelBotton);
			
			centroLaboralTextField = new JTextField();
			centroLaboralTextField.setToolTipText("Carn\u00E9 de identidad de la persona");
			centroLaboralTextField.setColumns(10);
			centroLaboralTextField.setBounds(10, 227, 223, 20);
			mainPanel.add(centroLaboralTextField);
			
			llenarTextFields(estudiante);
		}
	}
	
	private void llenarTextFields(Estudiante estudiante){
		ciTextField.setText(estudiante.getID());//Escribe carne
		nombreTextField.setText(estudiante.getNombre());//Escribe nombre
		annoComboBox.setSelectedItem(estudiante.getAnnoAcademico());//Selecciona año
		centroLaboralTextField.setText(estudiante.getCentroLaboral());//Escribe centro Laboral
		organismoTextField.setText(estudiante.getOrganismo());//Escribe organismo
		direccTextField.setText(estudiante.getDireccion());//Escribe direccion
	}
	
}
