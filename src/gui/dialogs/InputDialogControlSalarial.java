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

import logica.PersonalApoyo;
import logica.Profesor;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;



public class InputDialogControlSalarial extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel upperBarPanel = new JPanel();
	private final JSpinner spinnerSalarioBase;


	/**
	 * Crea el frame para agregar
	 */
	public InputDialogControlSalarial() {
		setUndecorated(true);
		setModal(true);
		setBounds(100, 100, 431, 202);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		
		final JLabel closeBotton = new JLabel("");
		closeBotton.setIcon(new ImageIcon(InputDialogControlSalarial.class.getResource("/gui/utils/closeBotton.png")));
		closeBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				closeBotton.setIcon(new ImageIcon(InputDialogControlSalarial.class.getResource("/gui/utils/closeBottonSelected1.png")));
				closeBotton.setToolTipText("Cerrar");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				closeBotton.setIcon(new ImageIcon(InputDialogControlSalarial.class.getResource("/gui/utils/closeBotton.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "�Seguro que desea cerrar la entrada de datos?\n Su progreso no se guardar�", "Confirmaci�n", 
						JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
					dispose();
				}
			}
		});
		closeBotton.setBounds(393, 0, 27, 34);
		upperBarPanel.add(closeBotton);
		
		
		upperBarPanel.setBackground(new Color(255, 255, 255));
		upperBarPanel.setBounds(0, 0, 430, 34);
		upperBarPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(upperBarPanel);
		upperBarPanel.setLayout(null);
		
		
		JLabel nameFrame = new JLabel("Entrada de datos (Salario Base)");
		nameFrame.setBounds(10, 0, 254, 34);
		upperBarPanel.add(nameFrame);
		{
		    JPanel mainPanel = new JPanel();
			mainPanel.setBackground(Color.WHITE);
			mainPanel.setBounds(0, 34, 430, 168);
			getContentPane().add(mainPanel);
			mainPanel.setLayout(null);
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(0, 204, 255));
			panel.setBounds(281, 0, 150, 192);
			mainPanel.add(panel);
			
			JLabel lblSalarioBase = new JLabel("Salario Base:");
			lblSalarioBase.setForeground(new Color(51, 51, 51));
			lblSalarioBase.setBounds(10, 29, 166, 20);
			mainPanel.add(lblSalarioBase);
			
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
					JOptionPane.showMessageDialog(null, "Implementar");
					dispose();
				}
			});
			inputBotton.setIcon(new ImageIcon(InputDialogEst.class.getResource("/gui/utils/addBottonJDialog.png")));
			inputBotton.setBounds(33, 122, 63, 21);
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
					if(JOptionPane.showConfirmDialog(null, "�Seguro que desea cancelar la entrada de datos?\n Su progreso no se guardar�", "Confirmaci�n", 
							JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
						dispose();
					}
				}
			});
			cancelBotton.setIcon(new ImageIcon(InputDialogControlSalarial.class.getResource("/gui/utils/cancelBotton.png")));
			cancelBotton.setBounds(136, 122, 63, 21);
			mainPanel.add(cancelBotton);
			
			spinnerSalarioBase = new JSpinner();
			spinnerSalarioBase.setModel(new SpinnerNumberModel(100, 1, 20000, 50));
			spinnerSalarioBase.setBounds(10, 64, 82, 20);
			JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spinnerSalarioBase.getEditor();
			editor.getTextField().setEditable(false);
			mainPanel.add(spinnerSalarioBase);
			
			llenarValores();
		}
	}
	
	private void llenarValores(){
		spinnerSalarioBase.setValue(150);
	}
}