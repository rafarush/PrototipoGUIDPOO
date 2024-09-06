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

import logica.DatosAuto;
import logica.Fct;
import logica.Profesor;



public class InputDialogProfe extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel upperBarPanel = new JPanel();
	private JTextField nombreTextField;
	private JTextField ciTextField;
	private JTextField direccTextField;
	private JTextField organismoTextField;
	private JTextField centroLabTextField;
	private JComboBox catCienComboBox;
	private JComboBox catDocComboBox;

	
	/**
	 * Crea el cuadro de dialogo para agregar.
	 */
	public InputDialogProfe() {
		setResizable(false);
		setUndecorated(true);
		setModal(true);
		setBounds(100, 100, 634, 426);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		final JLabel closeBotton = new JLabel("");
		closeBotton.setIcon(new ImageIcon(InputDialogProfe.class.getResource("/gui/utils/closeBotton.png")));
		closeBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				closeBotton.setIcon(new ImageIcon(InputDialogProfe.class.getResource("/gui/utils/closeBottonSelected1.png")));
				closeBotton.setToolTipText("Cerrar");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				closeBotton.setIcon(new ImageIcon(InputDialogProfe.class.getResource("/gui/utils/closeBotton.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "¿Seguro que desea cerrar la entrada de datos?\n Su progreso no se guardará", "Confirmación", 
						JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
					dispose();
				}
			}
		});
		closeBotton.setBounds(597, 0, 27, 34);
		upperBarPanel.add(closeBotton);
		
		
		upperBarPanel.setBackground(new Color(255, 255, 255));
		upperBarPanel.setBounds(0, 0, 634, 34);
		upperBarPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(upperBarPanel);
		upperBarPanel.setLayout(null);
		
		
		JLabel nameFrame = new JLabel("Entrada de datos (Profesores)");
		nameFrame.setBounds(10, 0, 205, 34);
		upperBarPanel.add(nameFrame);
		{
		    JPanel mainPanel = new JPanel();
			mainPanel.setBackground(Color.WHITE);
			mainPanel.setBounds(0, 34, 634, 392);
			getContentPane().add(mainPanel);
			mainPanel.setLayout(null);
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(0, 204, 255));
			panel.setBounds(451, 0, 183, 392);
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
				
			JLabel lblCatCientifica = new JLabel("Categor\u00EDa Cient\u00EDfica:");
			lblCatCientifica.setBounds(289, 202, 128, 20);
			mainPanel.add(lblCatCientifica);
			
			final JComboBox catCienComboBox = new JComboBox();
			catCienComboBox.setModel(new DefaultComboBoxModel(new String[] {"M\u00E1ster", "Doctor", "Ninguna"}));
			catCienComboBox.setBounds(289, 227, 128, 20);
			mainPanel.add(catCienComboBox);
			
			JLabel lblCatDocente = new JLabel("Categor\u00EDa Docente:");
			lblCatDocente.setBounds(289, 258, 128, 20);
			mainPanel.add(lblCatDocente);
			
			final JComboBox catDocComboBox = new JComboBox();
			catDocComboBox.setModel(new DefaultComboBoxModel(new String[] {"Instructor", "Asistente", "Auxiliar", "Titular"}));
			catDocComboBox.setBounds(289, 287, 128, 20);
			mainPanel.add(catDocComboBox);
			
			JLabel lblCentroLab = new JLabel("Centro Laboral:");
			lblCentroLab.setBounds(10, 202, 166, 20);
			mainPanel.add(lblCentroLab);
			
			organismoTextField = new JTextField();
			organismoTextField.setToolTipText("Direcci\u00F3n particular de la persona");
			organismoTextField.setColumns(10);
			organismoTextField.setBounds(10, 288, 223, 20);
			mainPanel.add(organismoTextField);
			
			JLabel lblOrganismo = new JLabel("Organismo al que pretence:");
			lblOrganismo.setBounds(10, 258, 166, 20);
			mainPanel.add(lblOrganismo);
			
			centroLabTextField = new JTextField();
			centroLabTextField.setToolTipText("Carn\u00E9 de identidad de la persona");
			centroLabTextField.setColumns(10);
			centroLabTextField.setBounds(10, 229, 223, 20);
			mainPanel.add(centroLabTextField);
					
			//Boton de ingresar datos y su funcionalidad 
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
						Fct.getInstance().crearPersona(ciTextField.getText(), nombreTextField.getText(), catCienComboBox.getSelectedItem().toString(), 
								catDocComboBox.getSelectedItem().toString(),centroLabTextField.getText(), organismoTextField.getText(),
								direccTextField.getText());
						//DatosAuto.definirTablaProfes(Fct.getInstance().buscarProfesores());
						/*Runner.modeloProfesor.addRow(new Object[]{ciTextField.getText(), nombreTextField.getText(), 
								(String)catCienComboBox.getSelectedItem(),(String)catDocComboBox.getSelectedItem(),
								centroLabTextField.getText(), organismoTextField.getText(), direccTextField.getText()});*/
						JOptionPane.showMessageDialog(null, "Se ha añadido al profesor con éxito");
						dispose();
					}
				}
			});
			inputBotton.setIcon(new ImageIcon(InputDialogEst.class.getResource("/gui/utils/addBottonJDialog.png")));
			inputBotton.setBounds(99, 360, 63, 21);
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
			cancelBotton.setIcon(new ImageIcon(InputDialogProfe.class.getResource("/gui/utils/cancelBotton.png")));
			cancelBotton.setBounds(202, 360, 63, 21);
			mainPanel.add(cancelBotton);
						
		}
	}

	/**
	 * Crea el cuadro de dialogo para editar.
	 */
	public InputDialogProfe(final Profesor profe) {
		setResizable(false);
		setUndecorated(true);
		setModal(true);
		setBounds(100, 100, 634, 426);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		final JLabel closeBotton = new JLabel("");
		closeBotton.setIcon(new ImageIcon(InputDialogProfe.class.getResource("/gui/utils/closeBotton.png")));
		closeBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				closeBotton.setIcon(new ImageIcon(InputDialogProfe.class.getResource("/gui/utils/closeBottonSelected1.png")));
				closeBotton.setToolTipText("Cerrar");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				closeBotton.setIcon(new ImageIcon(InputDialogProfe.class.getResource("/gui/utils/closeBotton.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "¿Seguro que desea cerrar la entrada de datos?\n Su progreso no se guardará", "Confirmación", 
						JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
					dispose();
				}
			}
		});
		closeBotton.setBounds(597, 0, 27, 34);
		upperBarPanel.add(closeBotton);
		
		
		upperBarPanel.setBackground(new Color(255, 255, 255));
		upperBarPanel.setBounds(0, 0, 634, 34);
		upperBarPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(upperBarPanel);
		upperBarPanel.setLayout(null);
		
		
		JLabel nameFrame = new JLabel("Entrada de datos (Profesores)");
		nameFrame.setBounds(10, 0, 205, 34);
		upperBarPanel.add(nameFrame);
		{
		    JPanel mainPanel = new JPanel();
			mainPanel.setBackground(Color.WHITE);
			mainPanel.setBounds(0, 34, 634, 392);
			getContentPane().add(mainPanel);
			mainPanel.setLayout(null);
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(0, 204, 255));
			panel.setBounds(451, 0, 183, 392);
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
				
			JLabel lblCatCientifica = new JLabel("Categor\u00EDa Cient\u00EDfica:");
			lblCatCientifica.setBounds(289, 202, 128, 20);
			mainPanel.add(lblCatCientifica);
			
			catCienComboBox = new JComboBox();
			catCienComboBox.setModel(new DefaultComboBoxModel(new String[] {"Máster", "Doctor", "Ninguna"}));
			catCienComboBox.setBounds(289, 227, 128, 20);
			mainPanel.add(catCienComboBox);
			
			JLabel lblCatDocente = new JLabel("Categor\u00EDa Docente:");
			lblCatDocente.setBounds(289, 258, 128, 20);
			mainPanel.add(lblCatDocente);
			
			catDocComboBox = new JComboBox();
			catDocComboBox.setModel(new DefaultComboBoxModel(new String[] {"Instructor", "Asistente", "Auxiliar", "Titular"}));
			catDocComboBox.setBounds(289, 287, 128, 20);
			mainPanel.add(catDocComboBox);
			
			JLabel lblCentroLab = new JLabel("Centro Laboral:");
			lblCentroLab.setBounds(10, 202, 166, 20);
			mainPanel.add(lblCentroLab);
			
			organismoTextField = new JTextField();
			organismoTextField.setToolTipText("Direcci\u00F3n particular de la persona");
			organismoTextField.setColumns(10);
			organismoTextField.setBounds(10, 288, 223, 20);
			mainPanel.add(organismoTextField);
			
			JLabel lblOrganismo = new JLabel("Organismo al que pretence:");
			lblOrganismo.setBounds(10, 258, 166, 20);
			mainPanel.add(lblOrganismo);
			
			centroLabTextField = new JTextField();
			centroLabTextField.setToolTipText("Carn\u00E9 de identidad de la persona");
			centroLabTextField.setColumns(10);
			centroLabTextField.setBounds(10, 229, 223, 20);
			mainPanel.add(centroLabTextField);
					
			//Boton de ingresar datos y su funcionalidad 
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
						profe.modificarProfesor(ciTextField.getText(), nombreTextField.getText(), catDocComboBox.getSelectedItem().toString(), 
								catCienComboBox.getSelectedItem().toString(),centroLabTextField.getText(), organismoTextField.getText(), direccTextField.getText());
						/*
						Runner.modeloProfesor.addRow(new Object[]{ciTextField.getText(), nombreTextField.getText(), 
								(String)catCienComboBox.getSelectedItem(),(String)catDocComboBox.getSelectedItem(),
								centroLabTextField.getText(), organismoTextField.getText(), direccTextField.getText()});*/
						/**
						 * Elimina al profe original (PROVISIONAL)
						 *//*
						((DefaultTableModel) MainFrame.getTable().getModel()).removeRow(MainFrame.getTable().getSelectedRow());
						((DefaultTableModel) MainFrame.getTable().getModel()).fireTableDataChanged();*/

						JOptionPane.showMessageDialog(null, "Se ha modificado al profesor con éxito");
						dispose();
					}
				}
			});
			inputBotton.setIcon(new ImageIcon(InputDialogEst.class.getResource("/gui/utils/addBottonJDialog.png")));
			inputBotton.setBounds(99, 360, 63, 21);
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
			cancelBotton.setIcon(new ImageIcon(InputDialogProfe.class.getResource("/gui/utils/cancelBotton.png")));
			cancelBotton.setBounds(202, 360, 63, 21);
			mainPanel.add(cancelBotton);
			
			llenarTextFields(profe);
		
			
			
		}
	}
	/**
	 * Funcion para llenar los campos con los datos del profesor seleccionado
	 * @param valoresDeLaFila
	 */
	private void llenarTextFields(Profesor profe){
		ciTextField.setText(profe.getID());//Escribe el carne
		nombreTextField.setText(profe.getNombre());//Escribe el nombre
		catCienComboBox.setSelectedItem(profe.getCategoriaCientifica());//Selecciona categoria cientifica
		catDocComboBox.setSelectedItem(profe.getCategoriaDocente());//Selecciona categoria docente
		centroLabTextField.setText(profe.getCentroLaboral());//Escribe el centro laboral
		organismoTextField.setText(profe.getOrganismo());//Escribe el organismo
		direccTextField.setText(profe.getDireccion());//Escribe la direccion
	}

	
		
	
}
