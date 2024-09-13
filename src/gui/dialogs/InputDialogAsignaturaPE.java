package gui.dialogs;


import gui.utils.JTextFieldLimitado;
import gui.utils.Validaciones;
import logica.Clases.*;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;





public class InputDialogAsignaturaPE extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel upperBarPanel = new JPanel();
	private JTextFieldLimitado nombreTextField;
	private JComboBox annoComboBox;
	private JComboBox semestreComboBox;
	private JSpinner spinnerHorasLectivas;
	private int limite = 25;
	private Border bordeRojo = BorderFactory.createLineBorder(Color.RED,1);
	private Border bordeNegro = BorderFactory.createLineBorder(Color.GRAY,1);

	/**
	 * Crear Input para agregar
	 */
	public InputDialogAsignaturaPE() {
		setUndecorated(true);
		setModal(true);
		setBounds(100, 100, 538, 360);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		
		final JLabel closeBotton = new JLabel("");
		closeBotton.setIcon(new ImageIcon(InputDialogAsignaturaPE.class.getResource("/gui/utils/closeBotton.png")));
		closeBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				closeBotton.setIcon(new ImageIcon(InputDialogAsignaturaPE.class.getResource("/gui/utils/closeBottonSelected1.png")));
				closeBotton.setToolTipText("Cerrar");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				closeBotton.setIcon(new ImageIcon(InputDialogAsignaturaPE.class.getResource("/gui/utils/closeBotton.png")));
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
		
		
		JLabel nameFrame = new JLabel("Entrada de datos (Asignaturas)");
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
			
			nombreTextField = new JTextFieldLimitado();
			nombreTextField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if(!nombreTextField.getText().isEmpty()){
						if (Validaciones.todoLetra(Validaciones.getCadenaSinEspacios(nombreTextField.getText()))){
							nombreTextField.setBorder(bordeNegro);
						}else{
							nombreTextField.setBorder(bordeRojo);
						}
					}else{
						nombreTextField.setBorder(bordeNegro);
					}
				}
				@Override
				public void keyTyped(KeyEvent e){
					JTextField text = (JTextField) e.getSource();
					if(text.getText().length()== limite)
						e.consume();
				}
			});
			nombreTextField.setToolTipText("Nombre de la Asignatura");
			nombreTextField.setColumns(10);
			nombreTextField.setBorder(bordeNegro);
			nombreTextField.setBounds(10, 54, 223, 20);
			mainPanel.add(nombreTextField);
			
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setForeground(new Color(51, 51, 51));
			lblNombre.setBounds(10, 29, 166, 20);
			mainPanel.add(lblNombre);
			
			JLabel lblHorasLectivas = new JLabel("Horas Lectivas:");
			lblHorasLectivas.setBounds(10, 85, 166, 20);
			mainPanel.add(lblHorasLectivas);
			
			JLabel lblAreaTrab = new JLabel("A\u00F1o:");
			lblAreaTrab.setBounds(10, 202, 35, 20);
			mainPanel.add(lblAreaTrab);
			
			annoComboBox = new JComboBox();
			annoComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6"}));
			annoComboBox.setBounds(10, 233, 71, 20);
			mainPanel.add(annoComboBox);
			
			JLabel lblSemestre = new JLabel("Semestre:");
			lblSemestre.setBounds(128, 202, 71, 20);
			mainPanel.add(lblSemestre);
			
			semestreComboBox = new JComboBox();
			semestreComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2"}));
			semestreComboBox.setBounds(128, 233, 71, 20);
			mainPanel.add(semestreComboBox);
			
			spinnerHorasLectivas = new JSpinner();
			spinnerHorasLectivas.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), new Integer(200), new Integer(1)));
			JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spinnerHorasLectivas.getEditor();
			editor.getTextField().setEditable(false);
			spinnerHorasLectivas.setBounds(10, 116, 51, 20);
			mainPanel.add(spinnerHorasLectivas);
			
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
					if(nombreTextField.getText().trim().isEmpty()){	
						JOptionPane.showMessageDialog(null, "Debe ponerle un nombre a la Asignatura");
					}else{
						if(Validaciones.todoLetra(Validaciones.getCadenaSinEspacios(nombreTextField.getText()))){
							Fct.getInstance().getPlanEstudio().crearAsignatura(nombreTextField.getText().trim(), Integer.valueOf(annoComboBox.getSelectedItem().toString()),
									Integer.valueOf(semestreComboBox.getSelectedItem().toString()), Integer.valueOf(spinnerHorasLectivas.getValue().toString()));
							JOptionPane.showMessageDialog(null, "Se ha añadido una Asignatura al Plan de Estudio con éxito");
							dispose();
						}else{
							JOptionPane.showMessageDialog(null, "El nombre de la asignatura solo puede contener caracteres alfabéticos");
							nombreTextField.setBorder(bordeRojo);
						}
					}	
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
			cancelBotton.setIcon(new ImageIcon(InputDialogAsignaturaPE.class.getResource("/gui/utils/cancelBotton.png")));
			cancelBotton.setBounds(136, 269, 63, 21);
			mainPanel.add(cancelBotton);
			
		}
	}
	
	
	/**
	 * Crear Input para modificar
	 */
	public InputDialogAsignaturaPE(Object[] valoresDeLaFila) {
		setUndecorated(true);
		setModal(true);
		setBounds(100, 100, 538, 360);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		
		final JLabel closeBotton = new JLabel("");
		closeBotton.setIcon(new ImageIcon(InputDialogAsignaturaPE.class.getResource("/gui/utils/closeBotton.png")));
		closeBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				closeBotton.setIcon(new ImageIcon(InputDialogAsignaturaPE.class.getResource("/gui/utils/closeBottonSelected1.png")));
				closeBotton.setToolTipText("Cerrar");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				closeBotton.setIcon(new ImageIcon(InputDialogAsignaturaPE.class.getResource("/gui/utils/closeBotton.png")));
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
		
		
		JLabel nameFrame = new JLabel("Entrada de datos (Asignaturas)");
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
			
			nombreTextField = new JTextFieldLimitado();
			nombreTextField.setToolTipText("Nombre de la persona");
			nombreTextField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if(!nombreTextField.getText().isEmpty()){
						if (Validaciones.todoLetra(Validaciones.getCadenaSinEspacios(nombreTextField.getText()))){
							nombreTextField.setBorder(bordeNegro);
						}else{
							nombreTextField.setBorder(bordeRojo);
						}
					}else{
						nombreTextField.setBorder(bordeNegro);
					}
				}
				@Override
				public void keyTyped(KeyEvent e){
					JTextField text = (JTextField) e.getSource();
					if(text.getText().length()== limite)
						e.consume();
				}
			});
			nombreTextField.setColumns(10);
			nombreTextField.setBounds(10, 54, 223, 20);
			mainPanel.add(nombreTextField);
			
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setForeground(new Color(51, 51, 51));
			lblNombre.setBounds(10, 29, 166, 20);
			mainPanel.add(lblNombre);
			
			JLabel lblHorasLectivas = new JLabel("Horas Lectivas:");
			lblHorasLectivas.setBounds(10, 85, 166, 20);
			mainPanel.add(lblHorasLectivas);
			
			JLabel lblAreaTrab = new JLabel("A\u00F1o:");
			lblAreaTrab.setBounds(10, 202, 35, 20);
			mainPanel.add(lblAreaTrab);
			
			annoComboBox = new JComboBox();
			annoComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6"}));
			annoComboBox.setBounds(10, 233, 71, 20);
			mainPanel.add(annoComboBox);
			
			JLabel lblSemestre = new JLabel("Semestre:");
			lblSemestre.setBounds(128, 202, 71, 20);
			mainPanel.add(lblSemestre);
			
			semestreComboBox = new JComboBox();
			semestreComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2"}));
			semestreComboBox.setBounds(128, 233, 71, 20);
			mainPanel.add(semestreComboBox);
			
			spinnerHorasLectivas = new JSpinner();
			spinnerHorasLectivas.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spinnerHorasLectivas.getEditor();
			editor.getTextField().setEditable(false);
			spinnerHorasLectivas.setBounds(10, 116, 51, 20);
			mainPanel.add(spinnerHorasLectivas);
			
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
					if(nombreTextField.getText().trim().isEmpty()){
						JOptionPane.showMessageDialog(null, "Debe ponerle un nombre a la Asignatura");
					}else{
						if (Validaciones.todoLetra(Validaciones.getCadenaSinEspacios(nombreTextField.getText()))){
							Fct.getInstance().modificarAsignatura(nombreTextField.getText(), Integer.valueOf(annoComboBox.getSelectedItem().toString()), 
									Integer.valueOf(semestreComboBox.getSelectedItem().toString()), 
									Integer.valueOf(spinnerHorasLectivas.getValue().toString()));
							JOptionPane.showMessageDialog(null, "Se ha modificado la Asignatura del Plan de Estudio con éxito");
							dispose();
						} else{
							JOptionPane.showMessageDialog(null, "Solo se permiten letras en el nombre de la Asignatura");
						}
					}	
				}
			});
			inputBotton.setIcon(new ImageIcon(InputDialogEst.class.getResource("/gui/utils/addBottonJDialog.png")));
			inputBotton.setBounds(33, 269, 63, 21);
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
			cancelBotton.setIcon(new ImageIcon(InputDialogAsignaturaPE.class.getResource("/gui/utils/cancelBotton.png")));
			cancelBotton.setBounds(136, 269, 63, 21);
			mainPanel.add(cancelBotton);
			
			
			llenarTextFields(valoresDeLaFila);
		}
	}
	
	private void llenarTextFields(Object[] valoresDeLaFila){
		nombreTextField.setText(valoresDeLaFila[0].toString());//Escribe el nombre
		annoComboBox.setSelectedItem(valoresDeLaFila[1].toString());//Pone el año
		semestreComboBox.setSelectedItem(valoresDeLaFila[2].toString());//Pone el semestre
		spinnerHorasLectivas.setValue(valoresDeLaFila[3]);//Pone la cantidad de horas
	}
	
}
