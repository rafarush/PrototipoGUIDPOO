package gui.mainFrame;

import gui.dialogs.*;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import runner.Runner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import logica.*;
import logica.Enums.BotonSelec;

import java.awt.Dimension;


public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private static JTableNoEdit table;
	private TableRowSorter<DefaultTableModel> tableSorter;
	private JTextField filterTextField;
	final JLabel lblPeriodoActual;
	final JLabel nextPeriodBotton;
	final JPanel mainPanel;
	private boolean maximized = false;
	private Point initialClick;
	private BotonSelec btnSeleccionado = BotonSelec.PROFESOR;
	DefaultTableCellRenderer centrarCelda = new DefaultTableCellRenderer();
	private static Object[] valoresDeFila = new Object[50];
	private static int periodo = 0;
	
	
	
	
	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setSize(new Dimension(914, 618));
		setTitle("Gestor de la FCT");
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 914, 605);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		
	    final JLayeredPane mainPane = new JLayeredPane();
		mainPane.setBounds(0, 0, 914, 618);
		contentPane.add(mainPane);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPane.setLayer(mainPanel, 1);
		mainPanel.setBounds(0, 0, 914, 618);
		mainPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		//Dibuja el ScrollPane
		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(240, 154, 664, 437);
		mainPanel.add(scrollPane);
		
		
		JLabel frameName = new JLabel("Gestor de la Filial de Ciencias T\u00E9cnicas");
		frameName.setBounds(10, 11, 345, 14);
		mainPanel.add(frameName);
		
		final JLabel miniFrameBotton = new JLabel("");
		miniFrameBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/miniBotton.png")));
		miniFrameBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				miniFrameBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/miniBottonSelected.png")));
				miniFrameBotton.setToolTipText("Minimizar");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				miniFrameBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/miniBotton.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				setExtendedState(ICONIFIED);
			}
		});
		miniFrameBotton.setBounds(820, 0, 27, 34);
		mainPanel.add(miniFrameBotton);
		
		
		
		final JLabel closeFrameBotton = new JLabel("");
		closeFrameBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/closeBotton.png")));
		closeFrameBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				closeFrameBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/closeBottonSelected1.png")));
				closeFrameBotton.setToolTipText("Cerrar");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				closeFrameBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/closeBotton.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "¿Seguro que desea cerrar la ventana?", "Confirmación", 
						JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
					dispose();
				}
			}
		});
		closeFrameBotton.setBounds(877, 0, 27, 34);
		mainPanel.add(closeFrameBotton);
		
		final JLabel addBotton = new JLabel("");
		addBotton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/addBotton.png")));
		addBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				addBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/addBottonSelected1.png")));
				addBotton.setToolTipText("Agregar elemento la tabla");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				addBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/addBotton.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				//Define que JDialog se activa 
				switch(btnSeleccionado){
					case PROFESOR:
						try {
							InputDialogProfe inputProfe = new InputDialogProfe();
							inputProfe.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							inputProfe.setVisible(true);
						} catch (Exception exc) {
							exc.printStackTrace();
						}
						tableDraw();
						break;
					case ESTUDIANTE:
						try {
							InputDialogEst inputEst = new InputDialogEst();
							inputEst.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							inputEst.setVisible(true);
						} catch (Exception exc) {
							exc.printStackTrace();
						}
						tableDraw();
						break;
					case PERSO_AUX:
						try {
							InputDialogPerAux inputPerAux = new InputDialogPerAux();
							inputPerAux.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							inputPerAux.setVisible(true);
						} catch (Exception exc) {
							exc.printStackTrace();
						}
						tableDraw();
						break;	
					case PLAN_ESTUDIO:
						try {
							InputDialogAsignaturaPE inputAsignatura = new InputDialogAsignaturaPE();
							inputAsignatura.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							inputAsignatura.setVisible(true);
						} catch (Exception exc) {
							exc.printStackTrace();
						}
						tableDraw();
						break;
					case GRUPO:
						try {
							InputDialogGrupo inputGrupo = new InputDialogGrupo();
							inputGrupo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							inputGrupo.setVisible(true);
						} catch (Exception exc) {
							exc.printStackTrace();
						}
						tableDraw();
						break;
					case CONSEJO_DIRECC:
						if (Fct.getInstance().buscarCargosCDFaltantes().size()>0){
							try {
								InputDialogProfeAConsejo inputProfeAConsejo = new InputDialogProfeAConsejo();
								inputProfeAConsejo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								inputProfeAConsejo.setVisible(true);
							} catch (Exception exc) {
								exc.printStackTrace();
							}
							tableDraw();
						}else{
							JOptionPane.showMessageDialog(null, "Ya están ocupados todos los cargos en el Consejo de Dirección");
						}
						break;
					default:
						break;
				}		
			}
		});
		addBotton.setBounds(530, 122, 101, 21);
		mainPanel.add(addBotton);
		
		final JLabel mainTitle = new JLabel("Control del Proceso Docente del Curso por Encuentros (CPE)");
		mainTitle.setHorizontalAlignment(SwingConstants.CENTER);
		mainTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		mainTitle.setBounds(240, 37, 642, 44);
		mainPanel.add(mainTitle);
		
		filterTextField = new JTextField();
		filterTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar()=='\n'){
					String aux = filterTextField.getText();
					if(aux.trim().length()==0){
						tableSorter.setRowFilter(null);
						table.setRowSorter(tableSorter);
					}else{
						tableSorter.setRowFilter(RowFilter.regexFilter("(?i)"+aux));
						table.setRowSorter(tableSorter);
					}
				}		
			}
		});
		filterTextField.setToolTipText("Escriba criterio de filtrado");
		filterTextField.setBounds(277, 123, 115, 20);
		mainPanel.add(filterTextField);
		filterTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		filterTextField.setColumns(10);
		
		final JLabel lblFiltro = new JLabel("Filtro:");
		lblFiltro.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFiltro.setBounds(230, 122, 50, 21);
		mainPanel.add(lblFiltro);
		lblFiltro.setHorizontalAlignment(SwingConstants.CENTER);
		
		/*
		 * Boton de eliminar
		 */
		final JLabel delBotton = new JLabel("");
		delBotton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		delBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/delBotton.png")));
		delBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				delBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/delBottonSelected1.png")));
				delBotton.setToolTipText("Eliminar algún elemento");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				delBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/delBotton.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {	
				if(table.getSelectedRow() == -1){
					JOptionPane.showMessageDialog(null, "Debe seleccionar la fila que desea eliminar");
				}else{
					if(JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar esa fila?\n No se podrá recuperar", "Confirmación", 
					JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
						
						switch (btnSeleccionado) {
						case ESTUDIANTE:
							JOptionPane.showMessageDialog(null, "Falta verificar que no este en un grupo con plan docente");
							Estudiante estudiante = Fct.getInstance().buscarUnEstudiante(table.getValueAt(table.getSelectedRow(), 0).toString());
							Fct.getInstance().eliminarPersona(estudiante.getID());
							JOptionPane.showMessageDialog(null, "Se ha eliminado a el estudiante correctamente");
							tableDraw();
							break;
							
						case PROFESOR:
							String profe = table.getValueAt(table.getSelectedRow(), 0).toString();
							if(!Fct.getInstance().verificarProfeEnPlanDoc(profe)){
								Fct.getInstance().eliminarPersona(profe);
								JOptionPane.showMessageDialog(null, "Se ha eliminado a el profesor correctamente");
								tableDraw();
							}else{
								JOptionPane.showMessageDialog(null, "No se puede eliminar a el profesor, ya que forma parte de la Planificación Docente");
							}
							break;
							
						case PERSO_AUX:
							Fct.getInstance().eliminarPersona(table.getValueAt(table.getSelectedRow(), 0).toString());
							JOptionPane.showMessageDialog(null, "Se ha eliminado al miembro del personal auxiliar correctamente");
							tableDraw();
							break;
							
						case GRUPO:
							Grupo grupo = Fct.getInstance().buscarGrupo(table.getValueAt(table.getSelectedRow(), 0).toString());
							if (!Fct.getInstance().verificarGrupoPD(grupo)){
								Fct.getInstance().eliminarGrupo(grupo);
								JOptionPane.showMessageDialog(null, "Se ha eliminado el grupo correctamente");
								tableDraw();
							}else{
								JOptionPane.showMessageDialog(null, "No se puede eliminar el grupo, ya que forma parte de la Planificación Docente");
							}
							break;
							
						case PLAN_ESTUDIO:
							String nombreAsignatura = table.getValueAt(table.getSelectedRow(), 0).toString();
							Asignatura asignatura = Fct.getInstance().getPlanEstudio().buscarAsignatura(nombreAsignatura);
							
							if (!Fct.getInstance().verificarAsignaturaPD(asignatura)){
								Fct.getInstance().eliminarAsignatura(asignatura);
								JOptionPane.showMessageDialog(null, "Se ha eliminado la asignatura correctamente");
								tableDraw();
							}else{
								JOptionPane.showMessageDialog(null, "No se puede eliminar la asignatura, ya que forma parte de la Planificación Docente");
							}
							break;
						case CONSEJO_DIRECC:
							
							String profeID = table.getValueAt(table.getSelectedRow(), 0).toString();
							Profesor profesor = (Profesor) Fct.getInstance().buscarPersona(profeID);
							Fct.getInstance().eliminarDelCD(profesor);
							JOptionPane.showMessageDialog(null, "Ha sido eliminado el profesor del Consejo de Dirección");
							tableDraw();
							break;
						default:
							break;
						}
					}
				}
			}
		});
		delBotton.setBounds(651, 122, 56, 21);
		mainPanel.add(delBotton);
		
		final JLabel modifyBotton = new JLabel("");
		modifyBotton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		modifyBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/modifyBotton.png")));
		modifyBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				modifyBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/modifyBottonSelected.png")));
				modifyBotton.setToolTipText("Modificar elementos de algún campo");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				modifyBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/modifyBotton.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				table.getSelectedRow();
				if(table.getSelectedRow() == - 1){
					JOptionPane.showMessageDialog(null, "Debe seleccionar la fila que desea modificar");
				}else{
					int filaSelec = table.getSelectedRow();
					//Define que JDialog se activa 
					switch(btnSeleccionado){
						case PROFESOR:
							try {
								InputDialogProfe inputProfe = new InputDialogProfe(Fct.getInstance().buscarUnProfesor(table.getValueAt(filaSelec, 0).toString()));
								inputProfe.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								inputProfe.setVisible(true);
							} catch (Exception exc) {
								exc.printStackTrace();
							}
							tableDraw();
							break;
						case ESTUDIANTE:
							try {
								InputDialogEst inputEst = new InputDialogEst(Fct.getInstance().buscarUnEstudiante(table.getValueAt(filaSelec, 0).toString()));
								inputEst.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								inputEst.setVisible(true);
							} catch (Exception exc) {
								exc.printStackTrace();
							}
							tableDraw();
							break;
						case PERSO_AUX:
							try {
								InputDialogPerAux inputPerAux = new InputDialogPerAux(Fct.getInstance().buscarUnPersonalApoyo(table.getValueAt(filaSelec, 0).toString()));
								inputPerAux.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								inputPerAux.setVisible(true);
							} catch (Exception exc) {
								exc.printStackTrace();
							}
							tableDraw();
							break;	
						case PLAN_ESTUDIO:
							try {
								//actualizar campos
								actualizarValoresDeFila(table.getSelectedRow());
								InputDialogAsignaturaPE inputAsignatura = new InputDialogAsignaturaPE(valoresDeFila);
								inputAsignatura.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								inputAsignatura.setVisible(true);
							} catch (Exception exc) {
								exc.printStackTrace();
							}
							break;
						case GRUPO:
							try {
								Grupo grupo = Fct.getInstance().buscarGrupo(table.getValueAt(table.getSelectedRow(), 0).toString());
								InputDialogEstuDelGrupo inputDialogEstuDelGrupo = new InputDialogEstuDelGrupo(grupo);
								inputDialogEstuDelGrupo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								inputDialogEstuDelGrupo.setVisible(true);
							} catch (Exception exc) {
								exc.printStackTrace();
							}
							tableDraw();
							break;
						case CONSEJO_DIRECC:
							try {
								InputDialogModifConsejoDirecc inputModConDir = new InputDialogModifConsejoDirecc(Fct.getInstance().buscarUnProfesor(table.getValueAt(table.getSelectedRow(), 0).toString()));
								inputModConDir.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								inputModConDir.setVisible(true);
							} catch (Exception exc) {
								exc.printStackTrace();
							}
							tableDraw();
							break;
						default:
							break;
					}
				}
			}
		});
		modifyBotton.setBounds(847, 122, 57, 21);
		mainPanel.add(modifyBotton);
		
		
		nextPeriodBotton = new JLabel("");
		
		nextPeriodBotton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		nextPeriodBotton.addMouseListener(new MouseAdapter() {
			/*@Override
			public void mouseEntered(MouseEvent e) {
				nextPeriodBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/nextPeriodBottonSelected.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				nextPeriodBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/nextPeriodBotton.png")));
			}*/
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "¿Seguro que desea pasar a la siguiente fase?\n No se podrá recuperar", "Confirmación", 
						JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
					
					switch (periodo) {
					case 0:
						try {
							if (Fct.getInstance().empezarPeriodo()){
								JOptionPane.showMessageDialog(null, "Inicio de semestre 1");
								periodo = 1;
								actualizarLblPeriodoActual();
								//actualizarIcoBotonPeriodoActual();
							}
						} catch (ProcesoNoPermitidoException exc) {
							JOptionPane.showMessageDialog(null, "No se pudo empezar:\n"+ exc.getMessage());
						}
						
						break;
					case 1:
						try {
							if (Fct.getInstance().pasarPeriodo()){
								JOptionPane.showMessageDialog(null, "Inicio de semestre 2");
								periodo = 2;
								actualizarLblPeriodoActual();
								//actualizarIcoBotonPeriodoActual();
							}
						} catch (ProcesoNoPermitidoException exc) {
							JOptionPane.showMessageDialog(null, "No se pudo pasar de periodo:\n"+ exc.getMessage());
						}
						
						break;
					case 2:
						try {
							if (Fct.getInstance().pasarAnno()){

								JOptionPane.showMessageDialog(null, "Se pudo pasar de año (Inicio de preparación de semestre)");
								periodo = 0;
								actualizarLblPeriodoActual();
								//actualizarIcoBotonPeriodoActual();
							}
						} catch (ProcesoNoPermitidoException exc) {
							JOptionPane.showMessageDialog(null, "No se pudo pasar de año:\n"+ exc.getMessage());
						}
						
						break;
					default:
						break;
					}
				}
				
			}
		});
		nextPeriodBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/nextPeriodBotton.png")));
		nextPeriodBotton.setToolTipText("Avanzar entre los periodos");
		nextPeriodBotton.setBounds(729, 122, 101, 21);
		mainPanel.add(nextPeriodBotton);
		
					
		final JPanel menuBarPanel = new JPanel();
		mainPane.setLayer(menuBarPanel, 2);
		menuBarPanel.setBackground(new Color(0, 204, 255));
		menuBarPanel.setBounds(0, 33, 218, 618);
		mainPane.add(menuBarPanel);
		menuBarPanel.setLayout(null);
		
		final JLabel profeBarBotton = new JLabel("");
		profeBarBotton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		profeBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/ProfesBarBottonSelected1.png")));
		profeBarBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!(btnSeleccionado == BotonSelec.PROFESOR))
					profeBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/ProfesBarBottonSelected1.png")));
				profeBarBotton.setToolTipText("Mostrar los profesores");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!(btnSeleccionado == BotonSelec.PROFESOR))
					profeBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/ProfesBarBotton1.png")));
			}
		});
		profeBarBotton.setBounds(10, 59, 198, 34);
		menuBarPanel.add(profeBarBotton);
		
		
		
		final JLabel estuBarBotton = new JLabel("");
		estuBarBotton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		estuBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/EstudiantesBarBotton.png")));
		estuBarBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!(btnSeleccionado == BotonSelec.ESTUDIANTE)){
					estuBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/EstudiantesBarBotton1Selected.png")));
					estuBarBotton.setToolTipText("Mostrar los estudiantes");
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!(btnSeleccionado == BotonSelec.ESTUDIANTE))
					estuBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/EstudiantesBarBotton.png")));
			}
		});
		estuBarBotton.setBounds(12, 104, 198, 34);
		menuBarPanel.add(estuBarBotton);
		
		
		final JLabel grupoBarBotton = new JLabel("");
		grupoBarBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				grupoBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/GruposBarBottonSelected.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				grupoBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/GruposBarBotton.png")));
			}
		});
		grupoBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/GruposBarBotton.png")));
		grupoBarBotton.setBounds(10, 145, 197, 34);
		menuBarPanel.add(grupoBarBotton);
		

		final JLabel persoAuxBarBotton = new JLabel("");
		persoAuxBarBotton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		persoAuxBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PersoAuxBarBotton.png")));
		persoAuxBarBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!(btnSeleccionado == BotonSelec.PERSO_AUX))
					persoAuxBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PersoAuxBarBottonSelected.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!(btnSeleccionado == BotonSelec.PERSO_AUX))
					persoAuxBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PersoAuxBarBotton.png")));
			}
		});
		persoAuxBarBotton.setToolTipText("Mostrar el personal auxiliar");
		persoAuxBarBotton.setBounds(7, 184, 198, 34);
		menuBarPanel.add(persoAuxBarBotton);
		
		final JLabel planEstBarBotton = new JLabel("");
		planEstBarBotton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		planEstBarBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!(btnSeleccionado == BotonSelec.PLAN_ESTUDIO))
					planEstBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PlanDeEstudioBarBottonSelected.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!(btnSeleccionado == BotonSelec.PLAN_ESTUDIO))
					planEstBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PlanDeEstudioBarBotton.png")));
			}
		});
		planEstBarBotton.setToolTipText("Mostrar los datos del Plan de Estudio ");
		planEstBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PlanDeEstudioBarBotton.png")));
		planEstBarBotton.setBounds(5, 229, 203, 34);
		menuBarPanel.add(planEstBarBotton);
		
		final JLabel reportesIco = new JLabel("");
		reportesIco.setBounds(123, 411, 46, 39);
		menuBarPanel.add(reportesIco);
		reportesIco.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/marker-documents_filtered_via_10015_io_filtered_via_10015_ioCambiTam.png")));
		
		final JLabel reportesBarBotton = new JLabel("");
		reportesBarBotton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		reportesBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/ReportesBarBotton.png")));
		reportesBarBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					JDialogReportes dialog = new JDialogReportes();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception exc) {
					exc.printStackTrace();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				reportesBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/ReportesBarBottonSelected1.png")));
				reportesIco.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/marker-documents_filtered_via_10015_io_46x39_via_10015_io.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				reportesBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/ReportesBarBotton.png")));
				reportesIco.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/marker-documents_filtered_via_10015_io_filtered_via_10015_ioCambiTam.png")));
			}
		});
		reportesBarBotton.setBounds(10, 422, 103, 25);
		menuBarPanel.add(reportesBarBotton);
		
		final JLabel otorgarNotasIcon = new JLabel("");
		otorgarNotasIcon.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/icons8-create-96_filtered_via_10015_io_filtered_via_10015_io_46x39_via_10015_io.png")));
		otorgarNotasIcon.setBounds(167, 370, 46, 39);
		menuBarPanel.add(otorgarNotasIcon);
		
		final JLabel otorgarNotasBarBotton = new JLabel("");
		otorgarNotasBarBotton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		otorgarNotasBarBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				otorgarNotasBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/OtorgarNotaBarBottonSelected.png")));
				otorgarNotasIcon.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/icons8-create-96_filtered_via_10015_io_filtered_via_10015_io_filtered_via_10015_io_46x39_via_10015_io.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				otorgarNotasBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/OtorgarNotaBarBotton.png")));
				otorgarNotasIcon.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/icons8-create-96_filtered_via_10015_io_filtered_via_10015_io_46x39_via_10015_io.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					InputJDialogControlDocente dialog = new InputJDialogControlDocente();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception exc) {
					exc.printStackTrace();
				}
			}
		});
		otorgarNotasBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/OtorgarNotaBarBotton.png")));
		otorgarNotasBarBotton.setBounds(10, 374, 159, 29);
		menuBarPanel.add(otorgarNotasBarBotton);
		
		final JPanel separadorMenuBar = new JPanel();
		separadorMenuBar.setBounds(0, 316, 218, 1);
		menuBarPanel.add(separadorMenuBar);
		
		final JLabel planDocenteIcon = new JLabel("");
		planDocenteIcon.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/planificacionDocente_filtered.png")));
		planDocenteIcon.setBounds(162, 328, 46, 39);
		menuBarPanel.add(planDocenteIcon);
		
		final JLabel planificacionDocenteBarBotton = new JLabel("");
		planificacionDocenteBarBotton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		planificacionDocenteBarBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				planificacionDocenteBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PlanDocenteBarBottonSelected.png")));
				planDocenteIcon.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/planificacionDocente_filteredSelected.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				planificacionDocenteBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PlanDocenteBarBotton.png")));
				planDocenteIcon.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/planificacionDocente_filtered.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (Fct.getInstance().getPlanEstudio().getAsignaturas().size()>0 && Fct.getInstance().buscarGruposNoVacio().size()>0 && Fct.getInstance().buscarEstudiantes().size()>0){
					try {
						InputJDialogPlanifDocente dialog = new InputJDialogPlanifDocente();
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception exc) {
						exc.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(null, "Faltan datos para poder crear Planificaciones Docentes, por favor, añada");
				}
			}
		});
		planificacionDocenteBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PlanDocenteBarBotton.png")));
		planificacionDocenteBarBotton.setBounds(10, 330, 146, 29);
		menuBarPanel.add(planificacionDocenteBarBotton);
		
		
		//PopupMenu para CAMBIO DE CREDENCIALES Y CERRAR SESION
		final JPopupMenu popupMenu = new JPopupMenu();
		JMenuItemEdit cerrarSesionButtonItem = new JMenuItemEdit();
		JMenuItemEdit cambiarContraButtonItem = new JMenuItemEdit();
		JMenuItemEdit cambiarNombreButtonItem = new JMenuItemEdit();
		cerrarSesionButtonItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
        			JDialogLoginUser login = new JDialogLoginUser();
        			login.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        			login.setVisible(true);
        		} catch (Exception exc) {
        			exc.printStackTrace();
        		}
            }
        });
		cambiarContraButtonItem.setText("Cambiar contraseña");
		cambiarContraButtonItem.setIcon(null);
		cambiarContraButtonItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String contra = "";
            	contra = JOptionPane.showInputDialog("Escriba la nueva contraseña:");
            	if(contra.isEmpty()){
            		JOptionPane.showMessageDialog(null, "No se escribió nada");
            	}else{
            		Runner.usuario.setPassword(contra);
            		JOptionPane.showMessageDialog(null, "Se ha cambiado la contraseña satisfactoriamente");
            	} 	
            }
        });
		cambiarNombreButtonItem.setText("Cambiar nombre de usuario");
		cambiarNombreButtonItem.setIcon(null);
		
        popupMenu.add(cerrarSesionButtonItem);
        popupMenu.add(cambiarNombreButtonItem);
        popupMenu.add(cambiarContraButtonItem);
        
		
		final JLabel nombreUsuario = new JLabel("");
		nombreUsuario.setForeground(new Color(255, 255, 255));
		nombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nombreUsuario.setBounds(55, 534, 153, 15);
		nombreUsuario.setText(Runner.usuario.getName());
		menuBarPanel.add(nombreUsuario);
		
		final JLabel sesionIconBarBotton = new JLabel("");
		sesionIconBarBotton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sesionIconBarBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sesionIconBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/userPhoto-removebg-preview_filtered_via_10015_io_46x39_via_10015_ioSelected.png")));
				nombreUsuario.setForeground(new Color(0, 0, 0));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sesionIconBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/userPhoto-removebg-preview_filtered_via_10015_io_46x39_via_10015_io.png")));
				nombreUsuario.setForeground(new Color(255, 255, 255));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// if(e.isPopupTrigger()) {
	                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
	            // }
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// if(e.isPopupTrigger()) {
	                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
	             //}
			}
		});
		sesionIconBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/userPhoto-removebg-preview_filtered_via_10015_io_46x39_via_10015_io.png")));
		sesionIconBarBotton.setBounds(10, 523, 46, 39);
		menuBarPanel.add(sesionIconBarBotton);
		
		
		final JLabel consejoDireccBarBotton = new JLabel("");
		consejoDireccBarBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				consejoDireccBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/ConsejoDireccBarBottonSelected.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				consejoDireccBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/ConsejoDireccBarBotton.png")));
			}
		});
		consejoDireccBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/ConsejoDireccBarBotton.png")));
		consejoDireccBarBotton.setToolTipText("Mostrar los profesores que forman parte del Consejo de Direcci\u00F3n ");
		consejoDireccBarBotton.setBounds(10, 271, 203, 34);
		menuBarPanel.add(consejoDireccBarBotton);
		
		
		//Actualizar iconos y la tabla
		estuBarBotton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(!(btnSeleccionado == BotonSelec.ESTUDIANTE)){
					estuBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/EstudiantesBarBotton1Selected.png")));
					persoAuxBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PersoAuxBarBotton.png")));
					profeBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/ProfesBarBotton1.png")));
					planEstBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PlanDeEstudioBarBotton.png")));
					consejoDireccBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/ConsejoDireccBarBotton.png")));
					btnSeleccionado = BotonSelec.ESTUDIANTE;
					tableDraw();
				}		
			}
		});
		grupoBarBotton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(!(btnSeleccionado == BotonSelec.GRUPO)){
					grupoBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/GruposBarBottonSelected.png")));
					consejoDireccBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/ConsejoDireccBarBotton.png")));
					estuBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/EstudiantesBarBotton.png")));
					persoAuxBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PersoAuxBarBotton.png")));
					profeBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/ProfesBarBotton1.png")));
					planEstBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PlanDeEstudioBarBotton.png")));
					btnSeleccionado = BotonSelec.GRUPO;
					tableDraw();
				}		
			}
		});
		profeBarBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!(btnSeleccionado == BotonSelec.PROFESOR)){
					profeBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/ProfesBarBottonSelected1.png")));
					estuBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/EstudiantesBarBotton.png")));
					grupoBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/GruposBarBotton.png")));
					consejoDireccBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/ConsejoDireccBarBotton.png")));
					persoAuxBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PersoAuxBarBotton.png")));
					planEstBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PlanDeEstudioBarBotton.png")));
					btnSeleccionado = BotonSelec.PROFESOR;
					tableDraw();
				}		
			}
		});
		persoAuxBarBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!(btnSeleccionado == BotonSelec.PERSO_AUX)){
					persoAuxBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PersoAuxBarBottonSelected.png")));
					estuBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/EstudiantesBarBotton.png")));
					consejoDireccBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/ConsejoDireccBarBotton.png")));
					grupoBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/GruposBarBotton.png")));
					profeBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/ProfesBarBotton1.png")));
					planEstBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PlanDeEstudioBarBotton.png")));
					btnSeleccionado = BotonSelec.PERSO_AUX;
					tableDraw();
				}	
			}
		});
		planEstBarBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!(btnSeleccionado == BotonSelec.PLAN_ESTUDIO)){
					planEstBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PlanDeEstudioBarBottonSelected.png")));
					persoAuxBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PersoAuxBarBotton.png")));
					estuBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/EstudiantesBarBotton.png")));
					consejoDireccBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/ConsejoDireccBarBotton.png")));
					grupoBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/GruposBarBotton.png")));
					profeBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/ProfesBarBotton1.png")));
					btnSeleccionado = BotonSelec.PLAN_ESTUDIO;
					tableDraw();
				}	
			}
		});
		consejoDireccBarBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!(btnSeleccionado == BotonSelec.CONSEJO_DIRECC)){
					consejoDireccBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/ConsejoDireccBarBottonSelected.png")));
					planEstBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PlanDeEstudioBarBotton.png")));
					persoAuxBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PersoAuxBarBotton.png")));
					estuBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/EstudiantesBarBotton.png")));
					grupoBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/GruposBarBotton.png")));
					profeBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/ProfesBarBotton1.png")));
					btnSeleccionado = BotonSelec.CONSEJO_DIRECC;
					tableDraw();
				}	
			}
		});
		
		
		final JLabel controlSalarialBarIcon = new JLabel("");
		controlSalarialBarIcon.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/icoControlSalarial.png")));
		controlSalarialBarIcon.setToolTipText("Cambiar salario base de los tranajadores");
		controlSalarialBarIcon.setBounds(172, 451, 46, 39);
		menuBarPanel.add(controlSalarialBarIcon);
		
		final JLabel controlSalarialBarBotton = new JLabel("");
		controlSalarialBarBotton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		controlSalarialBarBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				controlSalarialBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/ControlSalarialSelected.png")));
				controlSalarialBarIcon.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/icoControlSalarialSelected.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				controlSalarialBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/ControlSalarial.png")));
				controlSalarialBarIcon.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/icoControlSalarial.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					InputDialogControlSalarial inputSalario = new InputDialogControlSalarial();
					inputSalario.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					inputSalario.setVisible(true);
				} catch (Exception exc) {
					exc.printStackTrace();
				}
				tableDraw();
			}
		});
		controlSalarialBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/ControlSalarial.png")));
		controlSalarialBarBotton.setToolTipText("Cambiar salario base de los tranajadores");
		controlSalarialBarBotton.setBounds(10, 465, 171, 25);
		menuBarPanel.add(controlSalarialBarBotton);
		
		
		final JLabel filtroBtn = new JLabel("");
		filtroBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				filtroBtn.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/filterBottonSelected.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				filtroBtn.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/filterBotton.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				String aux = filterTextField.getText();
				if(aux.trim().length()==0){
					tableSorter.setRowFilter(null);
					table.setRowSorter(tableSorter);
				}else{
					tableSorter.setRowFilter(RowFilter.regexFilter("(?i)"+aux));
					table.setRowSorter(tableSorter);
				}
			}
		});
		filtroBtn.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/filterBotton.png")));
		filtroBtn.setBounds(397, 122, 61, 21);
		mainPanel.add(filtroBtn);
		
		
		lblPeriodoActual = new JLabel("New label");
		lblPeriodoActual.setHorizontalAlignment(SwingConstants.CENTER);
		lblPeriodoActual.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPeriodoActual.setBounds(490, 80, 168, 14);
		mainPanel.add(lblPeriodoActual);
		
		
		final JLabel menuBarBotton = new JLabel("");
		menuBarBotton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBarBotton.setBounds(new Rectangle(10, 11, 46, 32));
		menuBarBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!maximized){
					if(menuBarPanel.getWidth()==218){
						menuBarPanel.setBounds(0, 33, 63, 618);
						scrollPane.setBounds(79, 154, 825, 437);
						lblFiltro.setBounds(36, 122, 115, 20);
						filterTextField.setBounds(115, 122, 115, 20);
						filtroBtn.setBounds(235, 122, 61, 21);
						mainTitle.setBounds(78, 37, 804, 44);
						profeBarBotton.setVisible(false);
						estuBarBotton.setVisible(false);
						grupoBarBotton.setVisible(false);
						persoAuxBarBotton.setVisible(false);
						planEstBarBotton.setVisible(false);
						reportesBarBotton.setVisible(false);
						otorgarNotasBarBotton.setVisible(false);
						controlSalarialBarBotton.setVisible(false);
						controlSalarialBarIcon.setVisible(false);
						consejoDireccBarBotton.setVisible(false);
						separadorMenuBar.setVisible(false);
						planificacionDocenteBarBotton.setVisible(false);
						nombreUsuario.setVisible(false);
						sesionIconBarBotton.setVisible(false);
					}else{
						menuBarPanel.setBounds(0, 33, 218, 618);
						scrollPane.setBounds(240, 154, 664, 437);
						lblFiltro.setBounds(230, 122, 50, 21);
						filterTextField.setBounds(277, 123, 115, 20);
						filtroBtn.setBounds(402, 122, 61, 21);
						mainTitle.setBounds(233, 37, 649, 44);
						profeBarBotton.setVisible(true);
						estuBarBotton.setVisible(true);
						grupoBarBotton.setVisible(true);
						persoAuxBarBotton.setVisible(true);
						planEstBarBotton.setVisible(true);
						reportesBarBotton.setVisible(true);
						otorgarNotasBarBotton.setVisible(true);
						controlSalarialBarBotton.setVisible(true);
						controlSalarialBarIcon.setVisible(true);
						consejoDireccBarBotton.setVisible(true);
						separadorMenuBar.setVisible(true);
						planificacionDocenteBarBotton.setVisible(true);
						nombreUsuario.setVisible(true);
						sesionIconBarBotton.setVisible(true);
					}
				}else{
					if(menuBarPanel.getWidth()==218){
						menuBarPanel.setBounds(0, 33, 63, 729);
						scrollPane.setBounds(79, 154, 1270, 560);
						delBotton.setBounds(700, 122, 56, 21);
						addBotton.setBounds(579, 122, 101, 21);
						lblFiltro.setBounds(64, 122, 61, 21);
						filterTextField.setBounds(117, 122, 115, 20);
						filtroBtn.setBounds(237, 122, 61, 21);
						mainTitle.setBounds(270, 37, 804, 44);
						profeBarBotton.setVisible(false);
						estuBarBotton.setVisible(false);
						grupoBarBotton.setVisible(false);
						persoAuxBarBotton.setVisible(false);
						planEstBarBotton.setVisible(false);
						reportesBarBotton.setVisible(false);
						otorgarNotasBarBotton.setVisible(false);
						controlSalarialBarBotton.setVisible(false);
						controlSalarialBarIcon.setVisible(false);
						consejoDireccBarBotton.setVisible(false);
						separadorMenuBar.setVisible(false);
						planificacionDocenteBarBotton.setVisible(false);
						nombreUsuario.setVisible(false);
						sesionIconBarBotton.setVisible(false);
					}else{
						menuBarPanel.setBounds(0, 33, 218, 729);
						scrollPane.setBounds(240, 154, 1110, 560);
						delBotton.setBounds(900, 122, 56, 21);
						addBotton.setBounds(779, 122, 101, 21);
						lblFiltro.setBounds(225, 122, 61, 21);
						filterTextField.setBounds(275, 123, 141, 20);
						filtroBtn.setBounds(421, 122, 61, 21);
						mainTitle.setBounds(470, 37, 642, 44);	
						profeBarBotton.setVisible(true);
						estuBarBotton.setVisible(true);
						grupoBarBotton.setVisible(true);
						persoAuxBarBotton.setVisible(true);
						planEstBarBotton.setVisible(true);
						reportesBarBotton.setVisible(true);
						otorgarNotasBarBotton.setVisible(true);
						controlSalarialBarBotton.setVisible(true);
						controlSalarialBarIcon.setVisible(true);
						consejoDireccBarBotton.setVisible(true);
						separadorMenuBar.setVisible(true);
						planificacionDocenteBarBotton.setVisible(true);
						nombreUsuario.setVisible(true);
						sesionIconBarBotton.setVisible(true);
					}
				}
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if(menuBarPanel.getWidth()==218){
					menuBarBotton.setToolTipText("Encoger menú");
				}else{
					menuBarBotton.setToolTipText("Desplegar menú");
				}
			}
		});
		menuBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/MenuBarBotton.png")));
		menuBarBotton.setBounds(10, 11, 46, 32);
		menuBarPanel.add(menuBarBotton);
		
		
		final JLabel maxFrameBotton = new JLabel("");
		maxFrameBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/maxBotton.png")));
		maxFrameBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				maxFrameBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/maxBottonSelected.png")));
				if(mainPanel.getWidth() == 914){
					maxFrameBotton.setToolTipText("Maximizar");
				}else{
					maxFrameBotton.setToolTipText("Restaurar");
				}
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				maxFrameBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/maxBotton.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(mainPanel.getWidth() == 914){			
					mainPanel.setBounds(0,0,1366,729);
					mainPane.setBounds(0,0,1366,729);
					contentPane.setBounds(0,0,1366,729);	
					setBounds(0,0,1366,729);
					scrollPane.setBounds(240, 154, 1110, 560);
					closeFrameBotton.setBounds(1329,0, 27, 34);
					maxFrameBotton.setBounds(1298,0, 27, 34);
					miniFrameBotton.setBounds(1272, 0, 27, 34);
					if(menuBarPanel.getWidth()==63){
						menuBarPanel.setBounds(0, 33, 63, 729);
						scrollPane.setBounds(79, 154, 1270, 560);
						delBotton.setBounds(700, 122, 56, 21);
						addBotton.setBounds(579, 122, 101, 21);
						nextPeriodBotton.setBounds(1100, 122, 101, 21);
						lblFiltro.setBounds(63, 122, 61, 21);
						filterTextField.setBounds(115, 122, 115, 20);
						mainTitle.setBounds(270, 37, 804, 44);
					}else{
						menuBarPanel.setBounds(0, 33, 218, 729);
					}		
					nombreUsuario.setBounds(55, 650, 153, 15);
					sesionIconBarBotton.setBounds(10, 638, 46, 39);
					modifyBotton.setBounds(1290, 122, 63, 21);
					delBotton.setBounds(900, 122, 56, 21);
					addBotton.setBounds(779, 122, 101, 21);
					nextPeriodBotton.setBounds(1100, 122, 101, 21);
					mainTitle.setBounds(470, 37, 642, 44);	
					maximized = true;
				}else{
					mainPane.setBounds(0, 0, 914, 618);
					mainPanel.setBounds(0, 0, 914, 618);
					contentPane.setBounds(100, 100, 914, 618);
					setBounds(100, 100, 914, 618);
					scrollPane.setBounds(240, 154, 664, 450);
					closeFrameBotton.setBounds(877, 0, 27, 34);
					maxFrameBotton.setBounds(846, 0, 27, 34);
					miniFrameBotton.setBounds(820, 0, 27, 34);
					if(menuBarPanel.getWidth()==63){
						menuBarPanel.setBounds(0, 33, 63, 618);
						scrollPane.setBounds(79, 154, 825, 380);
						lblFiltro.setBounds(65, 122, 61, 21);
						filterTextField.setBounds(117, 122, 115, 20);
						mainTitle.setBounds(78, 37, 804, 44);
					}else{
						menuBarPanel.setBounds(0, 33, 218, 618);
					}				
					nombreUsuario.setBounds(55, 546, 153, 15);
					sesionIconBarBotton.setBounds(10, 534, 46, 39);
					modifyBotton.setBounds(841, 122, 63, 21);
					delBotton.setBounds(651, 122, 56, 21);
					addBotton.setBounds(530, 122, 101, 21);
					nextPeriodBotton.setBounds(729, 122, 101, 21);
					mainTitle.setBounds(240, 37, 642, 44);
					maximized = false;
				}		
			}
		});
		maxFrameBotton.setBounds(846, 0, 27, 34);
		mainPanel.add(maxFrameBotton);
		
		
		final JLabel upperFrameBar = new JLabel("");
		upperFrameBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				initialClick = e.getPoint();
			}
		});
		upperFrameBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				//Posicion del frame
				int frameX = getLocation().x;
				int frameY = getLocation().y;
				//Cuantos pixeles se movio el raton
				int movedX = e.getX() - initialClick.x;
				int movedY = e.getY() - initialClick.y;
				//Posicion a la que hay que mover el frame
				int x = frameX + movedX;
				int y = frameY + movedY;
				//Mover el frame
				setLocation(x,y);
			}
		});	
		upperFrameBar.setBounds(0, 0, 914, 34);
		mainPanel.add(upperFrameBar);
		
		//Define los modelos y dibuja las tablas con datos predeterminados
		centrarCelda.setHorizontalAlignment(JLabel.CENTER);
		tableDraw();
		actualizarLblPeriodoActual();
		
	}
	
	public void tableDraw(){
		switch(btnSeleccionado){
			case PROFESOR:
				DatosAuto.definirTablaProfes(Fct.getInstance().buscarProfesores());
				table = new JTableNoEdit(Runner.modeloProfesor);
				table.getColumnModel().getColumn(7).setCellRenderer(centrarCelda);
				tableSorter = new TableRowSorter<>(Runner.modeloProfesor);
				table.setRowSorter(tableSorter);
				table.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent e) {
						if (e.getClickCount() == 2) 
							JOptionPane.showMessageDialog(null, "Para editar un campo use el botón modificar");
					}
				});
				break;
			case ESTUDIANTE:
				DatosAuto.definirTablaEstudiantes(Fct.getInstance().buscarEstudiantes());
				table = new JTableNoEdit(Runner.modeloEstudiante);
				table.getColumnModel().getColumn(2).setCellRenderer(centrarCelda);
				tableSorter = new TableRowSorter<>(Runner.modeloEstudiante);
				table.setRowSorter(tableSorter);
				table.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent e) {
						if (e.getClickCount() == 2) 
							JOptionPane.showMessageDialog(null, "Para editar un campo use el botón modificar");
					}
				});
				break;
			case PERSO_AUX:
				DatosAuto.definirTablaPersonalAux(Fct.getInstance().buscarPersonalApoyo());
				table = new JTableNoEdit(Runner.modeloPersonalAux);
				tableSorter = new TableRowSorter<>(Runner.modeloPersonalAux);
				table.setRowSorter(tableSorter);
				table.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent e) {
						if (e.getClickCount() == 2) 
							JOptionPane.showMessageDialog(null, "Para editar un campo use el botón modificar");
					}
				});
				break;	
			case PLAN_ESTUDIO:
				DatosAuto.definirTablaPlanDeEstudio(Fct.getInstance().getPlanEstudio().getAsignaturas());
				table = new JTableNoEdit(Runner.modeloPlanDeEstudio);
				for(int i=1; i<=3;i++)
					table.getColumnModel().getColumn(i).setCellRenderer(centrarCelda);
				tableSorter = new TableRowSorter<>(Runner.modeloPlanDeEstudio);
				table.setRowSorter(tableSorter);
				table.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent e) {
						if (e.getClickCount() == 2) 
							JOptionPane.showMessageDialog(null, "Para editar un campo use el botón modificar");
					}
				});
				break;
			case GRUPO:
				DatosAuto.definirTablaGrupo(Fct.getInstance().getGrupos());
				table = new JTableNoEdit(Runner.modeloGrupoReporte);
				table.getColumnModel().getColumn(1).setCellRenderer(centrarCelda);
				table.getColumnModel().getColumn(2).setCellRenderer(centrarCelda);
				tableSorter = new TableRowSorter<>(Runner.modeloGrupoReporte);
				table.setRowSorter(tableSorter);
				table.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent e) {
						if (e.getClickCount() == 2) {
							JOptionPane.showMessageDialog(null, "Para editar un campo use el botón modificar");
							
						}	
					}
				});
				break;
			case CONSEJO_DIRECC:
				DatosAuto.definirTablaProfesConsejo(Fct.getInstance().buscarConsejoDireccion());
				table = new JTableNoEdit(Runner.modeloProfesorConsejo);
				tableSorter = new TableRowSorter<>(Runner.modeloProfesorConsejo);
				table.setRowSorter(tableSorter);
				table.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent e) {
						if (e.getClickCount() == 2) {
							JOptionPane.showMessageDialog(null, "Para editar un campo use el botón modificar");
							
						}	
					}
				});
				break;
			default:
					JOptionPane.showMessageDialog(null, "Debe seleccionar una categoria");
				break;
		}
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);
	}
	
	private void actualizarValoresDeFila(int filaSelec){	
		for(int i = 0; i < table.getColumnCount();i++){
			valoresDeFila[i] = table.getValueAt(filaSelec, i);
		}
	}
	public static JTableNoEdit getTable(){
		return table;
	}
	
	private void actualizarLblPeriodoActual(){
		switch (periodo) {
		case 0:
			
			lblPeriodoActual.setText("(Fase Preparatoria)");
			nextPeriodBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/startPeriodsBotton.png")));
			
			break;
		case 1:
			
			lblPeriodoActual.setText("(Semestre 1)");
			nextPeriodBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/nextPeriodBotton.png")));
			
			break;
		case 2:
			
			lblPeriodoActual.setText("(Semestre 2)");
			nextPeriodBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/nextYearBotton.png")));
			
			break;
		default:
			break;
		}
	}
	
	/*
	private void actualizarIcoBotonPeriodoActual(){
		switch (periodo) {
		case 0:
			
			nextPeriodBotton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					nextPeriodBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/startPeriodsBottonSelected.png")));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					nextPeriodBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/startPeriodsBotton.png")));
				}
			});
			nextPeriodBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/startPeriodsBotton.png")));
			nextPeriodBotton.setToolTipText("Comenzar periodos");
			mainPanel.add(nextPeriodBotton);
			
			break;
		case 1:
			
			nextPeriodBotton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					nextPeriodBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/nextPeriodBottonSelected.png")));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					nextPeriodBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/nextPeriodBotton.png")));
				}
			});
			nextPeriodBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/nextPeriodBotton.png")));
			nextPeriodBotton.setToolTipText("Pasar al siguiente periodo");
			mainPanel.add(nextPeriodBotton);
			
			break;
		case 2:

			nextPeriodBotton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					nextPeriodBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/nextYearBottonSelected.png")));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					nextPeriodBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/nextYearBotton.png")));
				}
			});
			nextPeriodBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/nextYearBotton.png")));
			nextPeriodBotton.setToolTipText("Pasar al siguiente periodo");
			mainPanel.add(nextPeriodBotton);
			
			break;
		default:
			break;
		}
	}
	*/
}
