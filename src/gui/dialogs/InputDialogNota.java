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

import java.awt.Cursor;

import javax.swing.SpinnerListModel;

import logica.Asignatura;
import logica.Estudiante;
import logica.Fct;
import logica.Grupo;
import logica.Profesor;



public class InputDialogNota extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel upperBarPanel = new JPanel();
	private JSpinner spinnerNota1;
	private String nota1;
	private String nota2;

	/**
	 * Crear Input para agregar
	 */
	public InputDialogNota() {
		setUndecorated(true);
		setModal(true);
		setBounds(100, 100, 359, 140);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		nota1 = new String();
		nota2 = new String();
		
		
		final JLabel closeBotton = new JLabel("");
		closeBotton.setIcon(new ImageIcon(InputDialogNota.class.getResource("/gui/utils/closeBotton.png")));
		closeBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				closeBotton.setIcon(new ImageIcon(InputDialogNota.class.getResource("/gui/utils/closeBottonSelected1.png")));
				closeBotton.setToolTipText("Cerrar");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				closeBotton.setIcon(new ImageIcon(InputDialogNota.class.getResource("/gui/utils/closeBotton.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "¿Seguro que desea cerrar la entrada de datos?\n Su progreso no se guardará", "Confirmación", 
						JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
					dispose();
				}
			}
		});
		closeBotton.setBounds(322, 0, 27, 34);
		upperBarPanel.add(closeBotton);
		
		
		upperBarPanel.setBackground(new Color(255, 255, 255));
		upperBarPanel.setBounds(0, 0, 359, 34);
		upperBarPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(upperBarPanel);
		upperBarPanel.setLayout(null);
		
		
		JLabel nameFrame = new JLabel("Entrada de datos (Nota)");
		nameFrame.setBounds(10, 0, 254, 34);
		upperBarPanel.add(nameFrame);
		{
		    JPanel mainPanel = new JPanel();
			mainPanel.setBackground(Color.WHITE);
			mainPanel.setBounds(0, 34, 359, 107);
			getContentPane().add(mainPanel);
			mainPanel.setLayout(null);
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(0, 204, 255));
			panel.setBounds(209, 0, 150, 107);
			mainPanel.add(panel);
			
			JLabel lblNota1 = new JLabel("Nota 1:");
			lblNota1.setBounds(10, 11, 51, 20);
			mainPanel.add(lblNota1);
			
			spinnerNota1 = new JSpinner();
			spinnerNota1.setModel(new SpinnerListModel(new String[] {"2", "3", "4", "5"}));
			JSpinner.DefaultEditor editor1 = (JSpinner.DefaultEditor) spinnerNota1.getEditor();
			editor1.getTextField().setEditable(false);
			spinnerNota1.setBounds(10, 31, 51, 20);
			mainPanel.add(spinnerNota1);
			
			JLabel lblNota2 = new JLabel("Nota 2:");
			lblNota2.setBounds(105, 11, 51, 20);
			mainPanel.add(lblNota2);
			
			final JSpinner spinnerNota2 = new JSpinner();
			spinnerNota2.setModel(new SpinnerListModel(new String[] {"2", "3", "4", "5"}));
			JSpinner.DefaultEditor editor2 = (JSpinner.DefaultEditor) spinnerNota2.getEditor();
			editor2.getTextField().setEditable(false);
			spinnerNota2.setBounds(105, 31, 51, 20);
			mainPanel.add(spinnerNota2);
			
			
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
					nota1 = spinnerNota1.getModel().getValue().toString();
					nota2 = spinnerNota2.getModel().getValue().toString();
					Profesor profe = (Profesor) Fct.getInstance().buscarPersona(ControlDocenteFrame.profeSelec);
					Asignatura asignatura = Fct.getInstance().getPlanEstudio().buscarAsignatura(ControlDocenteFrame.asignaturaSelec);
					Estudiante estudiante = Fct.getInstance().buscarUnEstudiante(ControlDocenteFrame.estuSelec);
					//Grupo grupo = Fct.getInstance().buscarGrupo(ControlDocenteFrame.grupoSelec);
					/*
					 * MANDAR NOTA A LOGICA
					 */
					profe.darNota(estudiante, asignatura, Integer.valueOf(nota1), Integer.valueOf(nota2));
					ControlDocenteFrame.mensajeConfirm(nota1, nota2);
					dispose();
				}
			});
			inputBotton.setIcon(new ImageIcon(InputDialogEst.class.getResource("/gui/utils/addBottonJDialog.png")));
			inputBotton.setBounds(10, 73, 63, 21);
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
					if(JOptionPane.showConfirmDialog(null, "¿Seguro que desea cancelar la entrada de nota?\nla nota no se guardará", "Confirmación", 
							JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
						dispose();
					}
				}
			});
			cancelBotton.setIcon(new ImageIcon(InputDialogNota.class.getResource("/gui/utils/cancelBotton.png")));
			cancelBotton.setBounds(113, 73, 63, 21);
			mainPanel.add(cancelBotton);
			
		}
	}
}
