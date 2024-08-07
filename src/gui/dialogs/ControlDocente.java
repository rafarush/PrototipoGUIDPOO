package gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import runner.Runner;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

import logica.Asignatura;
import logica.ComboBoxTextInicial;
import logica.DatosAuto;
import logica.Estudiante;
import logica.Grupo;
import logica.JTableNoEdit;
import logica.Profesor;
import logica.Enums.BotonSelec;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import javax.swing.border.LineBorder;
import java.awt.Cursor;

public class ControlDocente extends JDialog {

	private final JPanel upperBarPanel = new JPanel();
	private JComboBox asignaturasComboBox;
	private JScrollPane scrollPane;
	private static JTableNoEdit tablaNotas;
	private DefaultTableCellRenderer centrarCelda = new DefaultTableCellRenderer();
	private final JPanel mainPanel;
	private final JLabel atrasBtn;
	private JLabel lblTabla;
	private BotonSelec btnSeleccionado = BotonSelec.PROFESOR;
	private String asignaturaSelec;
	private String profeSelec;


	/**
	 * Crea el JDialog
	 */
	public ControlDocente() {
		setUndecorated(true);
		setModal(true);
		setBounds(100, 100, 617, 415);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		//tablaNotas = new JTableNoEdit(defecto = new DefaultTableModel());
		
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
				if(JOptionPane.showConfirmDialog(null, "¿Seguro que desea cerrar la entrada de notas?", "Confirmación", 
						JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
					dispose();
				}
			}
		});
		closeBotton.setBounds(580, 0, 27, 34);
		upperBarPanel.add(closeBotton);
		
		
		upperBarPanel.setBackground(new Color(255, 255, 255));
		upperBarPanel.setBounds(0, 0, 617, 34);
		upperBarPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(upperBarPanel);
		upperBarPanel.setLayout(null);
		
		
		JLabel nameFrame = new JLabel("Otorgamiento de notas");
		nameFrame.setBounds(10, 0, 254, 34);
		upperBarPanel.add(nameFrame);
		{
			 mainPanel = new JPanel();
			 mainPanel.setToolTipText("");
				mainPanel.setBackground(Color.WHITE);
				mainPanel.setBounds(0, 34, 617, 381);
				getContentPane().add(mainPanel);
				mainPanel.setLayout(null);
				
				JPanel panel = new JPanel();
				panel.setBackground(new Color(0, 204, 255));
				panel.setBounds(485, 0, 132, 381);
				mainPanel.add(panel);
				
				
				lblTabla = new JLabel("");
				lblTabla.setHorizontalAlignment(SwingConstants.CENTER);
				lblTabla.setForeground(new Color(51, 51, 51));
				lblTabla.setBounds(146, 11, 166, 20);
				mainPanel.add(lblTabla);
				actualizarLblNombre();
				
				final JLabel darNotaBtn = new JLabel("Dar Nota");
				darNotaBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				darNotaBtn.setBorder(new LineBorder(new Color(5, 5, 5)));
				darNotaBtn.setBackground(Color.LIGHT_GRAY);
				darNotaBtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int selec = tablaNotas.getSelectedRow();
						if(!(btnSeleccionado==BotonSelec.ESTUDIANTE) || selec == -1){
							JOptionPane.showMessageDialog(null, "Para dar nota debe tener un estudiante seleccionado");
						}else{
							String nota = JOptionPane.showInputDialog("Dar Nota", 5);
							if(nota.isEmpty()){
								JOptionPane.showMessageDialog(null, "No introdujo ningún valor");
							}else{
								JOptionPane.showMessageDialog(null, "Confirmación :\nCI Estudiante: "+tablaNotas.getValueAt(selec, 0)+
															"\nNota: "+nota+"\nAsignatura: "+asignaturaSelec+"\nCI Profesor: "+profeSelec);
								//Mandar nota a logica...
							}
						}
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						darNotaBtn.setBorder(new LineBorder(new Color(0, 0, 0)));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						darNotaBtn.setBorder(new LineBorder(new Color(5, 5, 5)));
					}
				});
				darNotaBtn.setForeground(Color.BLACK);
				darNotaBtn.setHorizontalAlignment(SwingConstants.CENTER);
				darNotaBtn.setBounds(413, 72, 62, 20);
				mainPanel.add(darNotaBtn);
				
				scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 98, 465, 272);
				mainPanel.add(scrollPane);
				
				atrasBtn = new JLabel("");
				atrasBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				atrasBtn.setIcon(new ImageIcon(ControlDocente.class.getResource("/gui/utils/flechaAtras24Selected.png")));
				atrasBtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						atrasBtn.setIcon(new ImageIcon(ControlDocente.class.getResource("/gui/utils/flechaAtras24Selected.png")));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						atrasBtn.setIcon(new ImageIcon(ControlDocente.class.getResource("/gui/utils/flechaAtras24.png")));
					}
					@Override
					public void mouseClicked(MouseEvent e){
						switch (btnSeleccionado) {
						case PROFESOR:
							JOptionPane.showMessageDialog(null, "No existe ruta hacia atrás");
							break;
						case PLANESTUDIO:
							centrarCelda.setHorizontalAlignment(JLabel.CENTER);
							btnSeleccionado = BotonSelec.PROFESOR;
							tableDraw();
							actualizarLblNombre();
							break;
						case ESTUDIANTE:
							centrarCelda.setHorizontalAlignment(JLabel.CENTER);
							btnSeleccionado = BotonSelec.PLANESTUDIO;
							tableDraw();
							actualizarLblNombre();
							break;
						}
						tablaNotas.getTableHeader().setReorderingAllowed(false);
						scrollPane.setViewportView(tablaNotas);
						}
				});
				atrasBtn.setToolTipText("Retornar a los grupos");
				atrasBtn.setBounds(10, 83, 24, 14);
				mainPanel.add(atrasBtn);
				
				
				tableDraw();
		}	
		
	}
	
	
	private void tableDraw(){
		switch(btnSeleccionado){
			case PROFESOR:
				tablaNotas = new JTableNoEdit(Runner.modeloProfesor);
				tablaNotas.getColumnModel().getColumn(7).setCellRenderer(centrarCelda);
				tablaNotas.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent e) {
						if (e.getClickCount() == 2) 
							btnSeleccionado = BotonSelec.PLANESTUDIO;
							profeSelec = tablaNotas.getValueAt(tablaNotas.getSelectedRow(), 0).toString();
							tableDraw();
							actualizarLblNombre();
					}
				});
				break;
			case ESTUDIANTE:
				tablaNotas = new JTableNoEdit(Runner.modeloEstudiante);
				tablaNotas.getColumnModel().getColumn(2).setCellRenderer(centrarCelda);
				tablaNotas.getColumnModel().getColumn(3).setCellRenderer(centrarCelda);
				tablaNotas.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent e) {
						if (e.getClickCount() == 2) 
							JOptionPane.showMessageDialog(null, "Para asignar nota a un estudiante utilice el boton 'Dar Nota'");
					}
				});
				break;
			case PLANESTUDIO:
				tablaNotas = new JTableNoEdit(Runner.modeloPlanDeEstudio);
				for(int i=1; i<=3;i++)
					tablaNotas.getColumnModel().getColumn(i).setCellRenderer(centrarCelda);
				tablaNotas.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent e) {
						if (e.getClickCount() == 2) 
							btnSeleccionado = BotonSelec.ESTUDIANTE;
							asignaturaSelec = tablaNotas.getValueAt(tablaNotas.getSelectedRow(), 0).toString();
							tableDraw();
							actualizarLblNombre();
					}
				});
				break;
		}
		tablaNotas.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tablaNotas);
	}
	
	public void actualizarLblNombre(){
		switch (btnSeleccionado) {
		case PROFESOR:
			lblTabla.setText("Seleccione un Profesor");
			break;
		case PLANESTUDIO:
			lblTabla.setText("Seleccione una Asignatura");
			break;
		case ESTUDIANTE:
			lblTabla.setText("Seleccione un Estudiante");
			break;
		}
	}
}
