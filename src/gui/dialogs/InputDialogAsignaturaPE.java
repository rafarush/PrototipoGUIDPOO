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
import java.util.concurrent.Semaphore;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;



public class InputDialogAsignaturaPE extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel upperBarPanel = new JPanel();
	private JTextField nombreTextField;
	private JComboBox annoComboBox;
	private JComboBox semestreComboBox;
	private JSpinner spinnerHorasLectivas;

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
				if(JOptionPane.showConfirmDialog(null, "�Seguro que desea cerrar la entrada de datos?\n Su progreso no se guardar�", "Confirmaci�n", 
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
			
			nombreTextField = new JTextField();
			nombreTextField.setToolTipText("Nombre de la persona");
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
					if(nombreTextField.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "Debe ponerle un nombre a la Asignatura");
					}else{
						Runner.modeloPlanDeEstudio.addRow(new Object[]{nombreTextField.getText(), annoComboBox.getSelectedItem().toString(), semestreComboBox.getSelectedItem().toString(), spinnerHorasLectivas.getValue().toString()});

						JOptionPane.showMessageDialog(null, "Se ha a�adido una Asignatura al Plan de Estudio con �xito");
						dispose();
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
					if(JOptionPane.showConfirmDialog(null, "�Seguro que desea cancelar la entrada de datos?\n Su progreso no se guardar�", "Confirmaci�n", 
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
				if(JOptionPane.showConfirmDialog(null, "�Seguro que desea cerrar la entrada de datos?\n Su progreso no se guardar�", "Confirmaci�n", 
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
			
			nombreTextField = new JTextField();
			nombreTextField.setToolTipText("Nombre de la persona");
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
					if(nombreTextField.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "Debe ponerle un nombre a la Asignatura");
					}else{
						Runner.modeloPlanDeEstudio.addRow(new Object[]{nombreTextField.getText(), annoComboBox.getSelectedItem().toString(), semestreComboBox.getSelectedItem().toString(), spinnerHorasLectivas.getValue().toString()});
						/**
						 * Elimina la Asignatura original (PROVISIONAL)
						 */
						((DefaultTableModel) MainFrame.getTable().getModel()).removeRow(MainFrame.getTable().getSelectedRow());
						((DefaultTableModel) MainFrame.getTable().getModel()).fireTableDataChanged();
						
						JOptionPane.showMessageDialog(null, "Se ha modificado la Asignatura del Plan de Estudio con �xito");
						dispose();
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
					if(JOptionPane.showConfirmDialog(null, "�Seguro que desea cancelar la entrada de datos?\n Su progreso no se guardar�", "Confirmaci�n", 
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
		annoComboBox.setSelectedItem(valoresDeLaFila[1].toString());//Pone el a�o
		semestreComboBox.setSelectedItem(valoresDeLaFila[2].toString());//Pone el semestre
		spinnerHorasLectivas.setValue((Integer.valueOf(valoresDeLaFila[3].toString())));//Pone la cantidad de horas
	}
	
}
