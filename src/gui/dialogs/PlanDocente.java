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

public class PlanDocente extends JDialog {

	private final JPanel upperBarPanel = new JPanel();
	private JComboBox asignaturasComboBox;
	private JScrollPane scrollPane;
	private static JTableNoEdit tabla;
	private DefaultTableCellRenderer centrarCelda = new DefaultTableCellRenderer();
	private final JPanel mainPanel;
	private final JLabel atrasBtn;
	private JLabel lblTabla;
	private BotonSelec btnSeleccionado = BotonSelec.PLANDOCENTE;
	private static String asignaturaSelec;
	private static String profeSelec;
	private static String grupoSelec;
	private static int filaSelec;
	final JComboBox semestreComboBox;
	final JComboBox annoComboBox;


	/**
	 * Crea el JDialog
	 */
	public PlanDocente() {
		setUndecorated(true);
		setModal(true);
		setBounds(100, 100, 617, 415);
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
		
		
		JLabel nameFrame = new JLabel("Planificaci\u00F3n Docente");
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
				
				final JLabel auxBtn = new JLabel("Crear Planificaci\u00F3n Docente");
				auxBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				auxBtn.setBorder(new LineBorder(new Color(5, 5, 5)));
				auxBtn.setBackground(Color.LIGHT_GRAY);
				auxBtn.addMouseListener(new MouseAdapter() {
					@Override
				public void mouseClicked(MouseEvent e) {
					switch (btnSeleccionado) {
					case PLANDOCENTE:
						btnSeleccionado = BotonSelec.PROFESOR;
						auxBtn.setText("Asignar Profesor");
						actualizarLblNombre();
						tableDraw();
						break;
					case PROFESOR:
						btnSeleccionado = BotonSelec.PLANESTUDIO;
						auxBtn.setText("Asignar Asignatura");
						profeSelec = tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
						actualizarLblNombre();
						tableDraw();
						break;
					case PLANESTUDIO:
						btnSeleccionado = BotonSelec.GRUPO;
						auxBtn.setText("Asignar Grupo");
						asignaturaSelec = tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
						actualizarLblNombre();
						tableDraw();
						break;
					case GRUPO:
						//Llamar a crear planificacion docente
						grupoSelec = tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
						Runner.fct.getPeriodos().get(0).crearPlanificacionDocente(Runner.fct.buscarUnProfesor(profeSelec), 
								Runner.fct.getPlanEstudio().buscarAsignatura(asignaturaSelec),
								Runner.fct.buscarGrupo(grupoSelec));
						JOptionPane.showMessageDialog(null, "Planificación realizada con éxito");
						btnSeleccionado = BotonSelec.PLANDOCENTE;
						auxBtn.setText("Crear Planificaci\u00F3n Docente");
						actualizarLblNombre();
						tableDraw();
						break;

					default:
						break;
					}
						
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					auxBtn.setBorder(new LineBorder(new Color(0, 0, 0)));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					auxBtn.setBorder(new LineBorder(new Color(5, 5, 5)));
				}
			});
				auxBtn.setForeground(Color.BLACK);
				auxBtn.setHorizontalAlignment(SwingConstants.CENTER);
				auxBtn.setBounds(309, 72, 166, 20);
				mainPanel.add(auxBtn);
				
				scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 98, 465, 272);
				mainPanel.add(scrollPane);
				
				atrasBtn = new JLabel("");
				atrasBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				atrasBtn.setIcon(new ImageIcon(PlanDocente.class.getResource("/gui/utils/flechaAtras24Selected.png")));
				atrasBtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						atrasBtn.setIcon(new ImageIcon(PlanDocente.class.getResource("/gui/utils/flechaAtras24Selected.png")));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						atrasBtn.setIcon(new ImageIcon(PlanDocente.class.getResource("/gui/utils/flechaAtras24.png")));
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
						tabla.getTableHeader().setReorderingAllowed(false);
						scrollPane.setViewportView(tabla);
						}
				});
				atrasBtn.setToolTipText("Retornar a los grupos");
				atrasBtn.setBounds(10, 83, 24, 14);
				mainPanel.add(atrasBtn);
				
				annoComboBox = new JComboBox();
				annoComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6"}));
				annoComboBox.setBounds(69, 72, 37, 20);
				mainPanel.add(annoComboBox);
				
				JLabel lblAnno = new JLabel("A\u00F1o:");
				lblAnno.setBounds(69, 54, 46, 14);
				mainPanel.add(lblAnno);
				
				semestreComboBox = new JComboBox();
				semestreComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2"}));
				semestreComboBox.setBounds(146, 72, 37, 20);
				mainPanel.add(semestreComboBox);
				
				JLabel lblSemestre = new JLabel("Semestre:");
				lblSemestre.setBounds(146, 54, 63, 14);
				mainPanel.add(lblSemestre);
				
				
				tableDraw();
		}	
		
	}
	
	
	private void tableDraw(){
		switch(btnSeleccionado){
			case PROFESOR:
				tabla = new JTableNoEdit(Runner.modeloProfesor);
				//tabla.getColumnModel().getColumn(7).setCellRenderer(centrarCelda);
				tabla.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent e) {
						if (e.getClickCount() == 2) 
							JOptionPane.showConfirmDialog(null, "No se permite modificar");/*
							btnSeleccionado = BotonSelec.PLANESTUDIO;
							profeSelec = tablaNotas.getValueAt(tablaNotas.getSelectedRow(), 0).toString();
							tableDraw();
							actualizarLblNombre();
							*/
					}
				});
				break;
			case GRUPO:
				tabla = new JTableNoEdit(Runner.modeloGrupoReporte);/*
				tablaNotas.getColumnModel().getColumn(2).setCellRenderer(centrarCelda);
				tablaNotas.getColumnModel().getColumn(3).setCellRenderer(centrarCelda);
				tabla.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent e) {
						if (e.getClickCount() == 2) 
							JOptionPane.showMessageDialog(null, "No se permite modificar");
					}
				});*/
				break;
			case PLANESTUDIO:
				tabla = new JTableNoEdit(Runner.modeloPlanDeEstudio);/*
				tabla.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent e) {
						if (e.getClickCount() == 2) 
							JOptionPane.showConfirmDialog(null, "No se permite modificar");
							btnSeleccionado = BotonSelec.GRUPO;
							asignaturaSelec = tablaNotas.getValueAt(tablaNotas.getSelectedRow(), 0).toString();
							tableDraw();
							actualizarLblNombre();
					}
				});*/
				break;
			case PLANDOCENTE:
				if(Integer.valueOf(semestreComboBox.getSelectedItem().toString())==1){
					DatosAuto.llenarTablaPlanificacionDocente(Runner.fct.getPeriodos().get(annoComboBox.getSelectedIndex()).getPlanificacionesDocentes());
				}else{
					DatosAuto.llenarTablaPlanificacionDocente(Runner.fct.getPeriodos().get(annoComboBox.getSelectedIndex()+6).getPlanificacionesDocentes());
				}
				tabla = new JTableNoEdit(Runner.modeloPlanDocente);
				tabla.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent e) {
						if (e.getClickCount() == 2) 
							JOptionPane.showConfirmDialog(null, "No se permite modificar");
					}
				});
				break;
		}
		tabla.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tabla);
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
		case GRUPO:
			lblTabla.setText("Seleccione un Grupo");
			break;
		case PLANDOCENTE:
			lblTabla.setText("Planes Docentes");
			break;
		}
	}
}
