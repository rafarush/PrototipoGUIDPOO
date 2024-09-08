package gui.dialogs;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import runner.Runner;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

import logica.Clases.*;
import gui.utils.*;
import gui.utils.Enums.BotonSelec;

import javax.swing.border.LineBorder;

import java.awt.Cursor;

public class InputJDialogControlDocente extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private final JPanel upperBarPanel = new JPanel();
	private JScrollPane scrollPane;
	private static JTableNoEdit tablaNotas;
	private DefaultTableCellRenderer centrarCelda = new DefaultTableCellRenderer();
	private final JPanel mainPanel;
	private final JLabel atrasBtn;
	private JLabel lblTabla;
	private BotonSelec btnSeleccionado = BotonSelec.PROFESOR;
	public static String asignaturaSelec;
	public static String profeSelec;
	public static String grupoSelec;
	public static String estuSelec;
	private static int filaSelec;


	/**
	 * Crea el JDialog
	 */
	public InputJDialogControlDocente() {
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
						filaSelec = tablaNotas.getSelectedRow();
						if(!(btnSeleccionado==BotonSelec.ESTUDIANTE) || filaSelec == -1){
							JOptionPane.showMessageDialog(null, "Para dar nota debe tener un estudiante seleccionado");
						}else{
							estuSelec = tablaNotas.getValueAt(filaSelec, 0).toString();
							try {
								InputDialogNota inputNota = new InputDialogNota();
								inputNota.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								inputNota.setVisible(true);
								tableDraw();
							} catch (Exception exc) {
								exc.printStackTrace();
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
				atrasBtn.setIcon(new ImageIcon(InputJDialogControlDocente.class.getResource("/gui/utils/flechaAtras24Selected.png")));
				atrasBtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						atrasBtn.setIcon(new ImageIcon(InputJDialogControlDocente.class.getResource("/gui/utils/flechaAtras24Selected.png")));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						atrasBtn.setIcon(new ImageIcon(InputJDialogControlDocente.class.getResource("/gui/utils/flechaAtras24.png")));
					}
					@Override
					public void mouseClicked(MouseEvent e){
						switch (btnSeleccionado) {
						case PROFESOR:
							JOptionPane.showMessageDialog(null, "No existe ruta hacia atrás");
							break;
						case PLAN_ESTUDIO:
							centrarCelda.setHorizontalAlignment(JLabel.CENTER);
							btnSeleccionado = BotonSelec.PROFESOR;
							tableDraw();
							actualizarLblNombre();
							break;
						case GRUPO:
							centrarCelda.setHorizontalAlignment(JLabel.CENTER);
							btnSeleccionado = BotonSelec.PLAN_ESTUDIO;
							tableDraw();
							actualizarLblNombre();
							break;
						case ESTUDIANTE:
							centrarCelda.setHorizontalAlignment(JLabel.CENTER);
							btnSeleccionado = BotonSelec.GRUPO;
							tableDraw();
							actualizarLblNombre();
							break;
						default:
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
				DatosAuto.definirTablaProfes(Fct.getInstance().buscarProfesores());
				tablaNotas = new JTableNoEdit(Runner.modeloProfesor);
				tablaNotas.getColumnModel().getColumn(7).setCellRenderer(centrarCelda);
				tablaNotas.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent e) {
						if (e.getClickCount() == 2) 
							btnSeleccionado = BotonSelec.PLAN_ESTUDIO;
							profeSelec = tablaNotas.getValueAt(tablaNotas.getSelectedRow(), 0).toString();
							tableDraw();
							actualizarLblNombre();
					}
				});
				break;
			case GRUPO:
				DatosAuto.definirTablaGrupo(Fct.getInstance().buscarGruposPorAsignatura(asignaturaSelec));
				tablaNotas = new JTableNoEdit(Runner.modeloGrupoReporte);
				tablaNotas.getColumnModel().getColumn(1).setCellRenderer(centrarCelda);
				tablaNotas.getColumnModel().getColumn(2).setCellRenderer(centrarCelda);
				tablaNotas.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent e) {
						if (e.getClickCount() == 2) {
							btnSeleccionado = BotonSelec.ESTUDIANTE;
							grupoSelec = tablaNotas.getValueAt(tablaNotas.getSelectedRow(), 0).toString();
							tableDraw();
							actualizarLblNombre();
						}
					}
				});
				break;
			case ESTUDIANTE:
				DatosAuto.definirTablaEstudiantesNotas(Fct.getInstance().buscarGrupo(grupoSelec).getGrupoEstudiantes(), Fct.getInstance().getPlanEstudio().buscarAsignatura(asignaturaSelec));
				tablaNotas = new JTableNoEdit(Runner.modeloEstudianteCorto);
				tablaNotas.getColumnModel().getColumn(2).setCellRenderer(centrarCelda);
				tablaNotas.getColumnModel().getColumn(3).setCellRenderer(centrarCelda);
				tablaNotas.getColumnModel().getColumn(4).setCellRenderer(centrarCelda);
				tablaNotas.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent e) {
						if (e.getClickCount() == 2) 
							JOptionPane.showMessageDialog(null, "Para asignar nota a un estudiante utilice el boton 'Dar Nota'");
					}
				});
				break;
			case PLAN_ESTUDIO:
				DatosAuto.definirTablaPlanDeEstudio(Fct.getInstance().buscarAsignaturasPorProfe(profeSelec));
				tablaNotas = new JTableNoEdit(Runner.modeloPlanDeEstudio);
				for(int i=1; i<=3;i++)
					tablaNotas.getColumnModel().getColumn(i).setCellRenderer(centrarCelda);
				tablaNotas.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent e) {
						if (e.getClickCount() == 2) 
							btnSeleccionado = BotonSelec.GRUPO;
							asignaturaSelec = tablaNotas.getValueAt(tablaNotas.getSelectedRow(), 0).toString();
							tableDraw();
							actualizarLblNombre();
					}
				});
				break;
			default:
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
		case PLAN_ESTUDIO:
			lblTabla.setText("Seleccione una Asignatura");
			break;
		case ESTUDIANTE:
			lblTabla.setText("Estudiantes del Grupo");
			break;
		case GRUPO:
			lblTabla.setText("Seleccione un Grupo");
			break;
		default:
			break;
		}
	}
	
	public static void mensajeConfirm(String nota1, String nota2){
		JOptionPane.showMessageDialog(null, "Confirmación :\nCI Estudiante: "+tablaNotas.getValueAt(filaSelec, 0)+
				"\nGrupo: "+grupoSelec+
				"\nNota1: "+nota1+"\nNota2: "+nota2+"\nAsignatura: "+asignaturaSelec+"\nCI Profesor: "+profeSelec);
	}
}
