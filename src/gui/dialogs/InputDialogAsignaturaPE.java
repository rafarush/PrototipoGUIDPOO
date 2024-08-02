package gui.dialogs;


import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import runner.Runner;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;



public class InputDialogAsignaturaPE extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel upperBarPanel = new JPanel();
	private JTextField nombreTextField;
	private JTextField ciTextField;
	private JTextField direccTextField;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			InputDialog dialog = new InputDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
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
					Runner.inputPerAux.dispose();
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
		
		
		JLabel nameFrame = new JLabel("Entrada de datos (Personal Auxiliar)");
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
			
			JLabel lblAreaTrab = new JLabel("\u00C1rea de trabajo:");
			lblAreaTrab.setBounds(10, 202, 166, 20);
			mainPanel.add(lblAreaTrab);
			
			final JComboBox areaTrabajoComboBox = new JComboBox();
			areaTrabajoComboBox.setModel(new DefaultComboBoxModel(new String[] {"Biblioteca", "Laboratorio", "Secretar\u00EDa"}));
			areaTrabajoComboBox.setBounds(10, 233, 223, 20);
			mainPanel.add(areaTrabajoComboBox);
			
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
						Runner.modeloPersonalAux.addRow(new Object[]{ciTextField.getText(), nombreTextField.getText(), (String)areaTrabajoComboBox.getSelectedItem(), direccTextField.getText()});
						JOptionPane.showMessageDialog(null, "Se ha añadido al miembro del personal auxiliar con éxito");
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
}
