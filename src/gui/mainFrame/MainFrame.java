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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import runner.Runner;

import java.awt.event.ActionEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import logica.DatosAuto;
import logica.Enums.BotonSelec;
import logica.JTableNoEdit;

import javax.swing.ListSelectionModel;


public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private static JTableNoEdit table;
	private TableRowSorter<DefaultTableModel> tableSorter;
	private JTextField filterTextField;
	private boolean maximized = false;
	private Point initialClick;
	private BotonSelec btnSeleccionado = BotonSelec.PROFESOR;
	DefaultTableCellRenderer centrarCelda = new DefaultTableCellRenderer();
	private static Object[] valoresDeFila = new Object[50];
	
	
	
	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("Gestor de la FCT");
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 914, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
	    final JLayeredPane mainPane = new JLayeredPane();
		mainPane.setBounds(0, 0, 914, 545);
		contentPane.add(mainPane);
		
		final JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPane.setLayer(mainPanel, 1);
		mainPanel.setBounds(0, 0, 914, 545);
		mainPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		//Dibuja el ScrollPane
		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(240, 154, 664, 380);
		mainPanel.add(scrollPane);
		
		//Define los modelos y dibuja las tablas con datos predeterminados
		centrarCelda.setHorizontalAlignment(JLabel.CENTER);
		tableDraw();
		
		
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
							Runner.inputProfe = new InputDialogProfe();
							Runner.inputProfe.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							Runner.inputProfe.setVisible(true);
						} catch (Exception exc) {
							exc.printStackTrace();
						}
						break;
					case ESTUDIANTE:
						try {
							Runner.inputEst = new InputDialogEst();
							Runner.inputEst.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							Runner.inputEst.setVisible(true);
						} catch (Exception exc) {
							exc.printStackTrace();
						}
						break;
					case PERSOAUX:
						try {
							Runner.inputPerAux = new InputDialogPerAux();
							Runner.inputPerAux.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							Runner.inputPerAux.setVisible(true);
						} catch (Exception exc) {
							exc.printStackTrace();
						}
						break;	
					case PLANESTUDIO:
						try {
							Runner.inputAsignatura = new InputDialogAsignaturaPE();
							Runner.inputAsignatura.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							Runner.inputAsignatura.setVisible(true);
						} catch (Exception exc) {
							exc.printStackTrace();
						}
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
			public void keyTyped(KeyEvent e) {
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
		
		final JLabel delBotton = new JLabel("");
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
						((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
						((DefaultTableModel) table.getModel()).fireTableDataChanged();
					}
				}
			}
		});
		delBotton.setBounds(651, 122, 56, 21);
		mainPanel.add(delBotton);
		
		final JLabel modifyBotton = new JLabel("");
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
					//Define que JDialog se activa 
					switch(btnSeleccionado){
						case PROFESOR:
							try {
								//actualizar campos
								actualizarValoresDeFila(table.getSelectedRow());
								Runner.inputProfe = new InputDialogProfe(valoresDeFila);
								Runner.inputProfe.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								Runner.inputProfe.setVisible(true);
							} catch (Exception exc) {
								exc.printStackTrace();
							}
							break;
						case ESTUDIANTE:
							try {
								//actualizar campos
								actualizarValoresDeFila(table.getSelectedRow());
								Runner.inputEst = new InputDialogEst(valoresDeFila);
								Runner.inputEst.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								Runner.inputEst.setVisible(true);
							} catch (Exception exc) {
								exc.printStackTrace();
							}
							break;
						case PERSOAUX:
							try {
								//actualizar campos
								actualizarValoresDeFila(table.getSelectedRow());
								Runner.inputPerAux = new InputDialogPerAux(valoresDeFila);
								Runner.inputPerAux.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								Runner.inputPerAux.setVisible(true);
							} catch (Exception exc) {
								exc.printStackTrace();
							}
							break;	
						case PLANESTUDIO:
							try {
								//actualizar campos
								actualizarValoresDeFila(table.getSelectedRow());
								Runner.inputAsignatura = new InputDialogAsignaturaPE(valoresDeFila);
								Runner.inputAsignatura.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								Runner.inputAsignatura.setVisible(true);
							} catch (Exception exc) {
								exc.printStackTrace();
							}
							break;
					}
				}
			}
		});
		modifyBotton.setBounds(847, 122, 57, 21);
		mainPanel.add(modifyBotton);
		
		
		final JLabel lblnextPeriod = new JLabel("");
		lblnextPeriod.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblnextPeriod.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/nextPeriodBottonSelected.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblnextPeriod.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/nextPeriodBotton.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Implementar");
			}
		});
		lblnextPeriod.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/nextPeriodBotton.png")));
		lblnextPeriod.setToolTipText("Pasar al siguiente periodo");
		lblnextPeriod.setBounds(729, 122, 101, 21);
		mainPanel.add(lblnextPeriod);
		
					
		final JPanel menuBarPanel = new JPanel();
		mainPane.setLayer(menuBarPanel, 2);
		menuBarPanel.setBackground(new Color(0, 204, 255));
		menuBarPanel.setBounds(0, 33, 218, 512);
		mainPane.add(menuBarPanel);
		menuBarPanel.setLayout(null);
		
		final JLabel profeBarBotton = new JLabel("");
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
		
		
		final JLabel persoAuxBarBotton = new JLabel("");
		persoAuxBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PersoAuxBarBotton.png")));
		persoAuxBarBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!(btnSeleccionado == BotonSelec.PERSOAUX))
					persoAuxBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PersoAuxBarBottonSelected.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!(btnSeleccionado == BotonSelec.PERSOAUX))
					persoAuxBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PersoAuxBarBotton.png")));
			}
		});
		persoAuxBarBotton.setToolTipText("Mostrar el personal auxiliar");
		persoAuxBarBotton.setBounds(7, 149, 198, 34);
		menuBarPanel.add(persoAuxBarBotton);
		
		final JLabel planEstBarBotton = new JLabel("");
		planEstBarBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!(btnSeleccionado == BotonSelec.PLANESTUDIO))
					planEstBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PlanDeEstudioBarBottonSelected.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!(btnSeleccionado == BotonSelec.PLANESTUDIO))
					planEstBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PlanDeEstudioBarBotton.png")));
			}
		});
		planEstBarBotton.setToolTipText("Mostrar los datos del Plan de Estudio ");
		planEstBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PlanDeEstudioBarBotton.png")));
		planEstBarBotton.setBounds(5, 194, 203, 34);
		menuBarPanel.add(planEstBarBotton);
		
		//Actualizar iconos
		estuBarBotton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(!(btnSeleccionado == BotonSelec.ESTUDIANTE)){
					estuBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/EstudiantesBarBotton1Selected.png")));
					persoAuxBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PersoAuxBarBotton.png")));
					profeBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/ProfesBarBotton1.png")));
					planEstBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PlanDeEstudioBarBotton.png")));
					btnSeleccionado = BotonSelec.ESTUDIANTE;
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
				if(!(btnSeleccionado == BotonSelec.PERSOAUX)){
					persoAuxBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PersoAuxBarBottonSelected.png")));
					estuBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/EstudiantesBarBotton.png")));
					profeBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/ProfesBarBotton1.png")));
					planEstBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PlanDeEstudioBarBotton.png")));
					btnSeleccionado = BotonSelec.PERSOAUX;
					tableDraw();
				}	
			}
		});
		planEstBarBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!(btnSeleccionado == BotonSelec.PLANESTUDIO)){
					planEstBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PlanDeEstudioBarBottonSelected.png")));
					persoAuxBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/PersoAuxBarBotton.png")));
					estuBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/EstudiantesBarBotton.png")));
					profeBarBotton.setIcon(new ImageIcon(MainFrame.class.getResource("/gui/utils/ProfesBarBotton1.png")));
					btnSeleccionado = BotonSelec.PLANESTUDIO;
					tableDraw();
				}	
			}
		});
		
		final JLabel menuBarBotton = new JLabel("");
		menuBarBotton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBarBotton.setBounds(new Rectangle(10, 11, 46, 32));
		menuBarBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!maximized){
					if(menuBarPanel.getWidth()==218){
						menuBarPanel.setBounds(0, 33, 63, 545);
						scrollPane.setBounds(79, 154, 825, 380);
						lblFiltro.setBounds(36, 122, 115, 20);
						filterTextField.setBounds(115, 122, 115, 20);
						mainTitle.setBounds(78, 37, 804, 44);
						profeBarBotton.setVisible(false);
						estuBarBotton.setVisible(false);
						persoAuxBarBotton.setVisible(false);
						planEstBarBotton.setVisible(false);
					}else{
						menuBarPanel.setBounds(0, 33, 218, 545);
						scrollPane.setBounds(240, 154, 664, 380);
						lblFiltro.setBounds(230, 122, 50, 21);
						filterTextField.setBounds(277, 123, 115, 20);
						mainTitle.setBounds(233, 37, 649, 44);
						profeBarBotton.setVisible(true);
						estuBarBotton.setVisible(true);
						persoAuxBarBotton.setVisible(true);
						planEstBarBotton.setVisible(true);
					}
				}else{
					if(menuBarPanel.getWidth()==218){
						menuBarPanel.setBounds(0, 33, 63, 729);
						scrollPane.setBounds(79, 154, 1270, 560);
						delBotton.setBounds(700, 122, 56, 21);
						addBotton.setBounds(579, 122, 101, 21);
						lblFiltro.setBounds(64, 122, 61, 21);
						filterTextField.setBounds(117, 122, 115, 20);
						mainTitle.setBounds(270, 37, 804, 44);
						profeBarBotton.setVisible(false);
						estuBarBotton.setVisible(false);
						persoAuxBarBotton.setVisible(false);
						planEstBarBotton.setVisible(false);
					}else{
						menuBarPanel.setBounds(0, 33, 218, 729);
						scrollPane.setBounds(240, 154, 1110, 560);
						delBotton.setBounds(900, 122, 56, 21);
						addBotton.setBounds(779, 122, 101, 21);
						lblFiltro.setBounds(225, 122, 61, 21);
						filterTextField.setBounds(275, 123, 141, 20);
						mainTitle.setBounds(470, 37, 642, 44);	
						profeBarBotton.setVisible(true);
						estuBarBotton.setVisible(true);
						persoAuxBarBotton.setVisible(true);
						planEstBarBotton.setVisible(true);
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
						lblnextPeriod.setBounds(1100, 122, 101, 21);
						lblFiltro.setBounds(63, 122, 61, 21);
						filterTextField.setBounds(115, 122, 115, 20);
						mainTitle.setBounds(270, 37, 804, 44);
					}else{
						menuBarPanel.setBounds(0, 33, 218, 729);
					}				
					modifyBotton.setBounds(1290, 122, 63, 21);
					delBotton.setBounds(900, 122, 56, 21);
					addBotton.setBounds(779, 122, 101, 21);
					lblnextPeriod.setBounds(1100, 122, 101, 21);
					mainTitle.setBounds(470, 37, 642, 44);	
					maximized = true;
				}else{
					mainPane.setBounds(0, 0, 914, 545);
					mainPanel.setBounds(0, 0, 914, 545);
					contentPane.setBounds(100, 100, 914, 545);
					setBounds(100, 100, 914, 545);
					scrollPane.setBounds(240, 154, 664, 380);
					closeFrameBotton.setBounds(877, 0, 27, 34);
					maxFrameBotton.setBounds(846, 0, 27, 34);
					miniFrameBotton.setBounds(820, 0, 27, 34);
					if(menuBarPanel.getWidth()==63){
						menuBarPanel.setBounds(0, 33, 63, 512);
						scrollPane.setBounds(79, 154, 825, 380);
						lblFiltro.setBounds(65, 122, 61, 21);
						filterTextField.setBounds(117, 122, 115, 20);
						mainTitle.setBounds(78, 37, 804, 44);
					}else{
						menuBarPanel.setBounds(0, 33, 218, 512);
					}				
					modifyBotton.setBounds(841, 122, 63, 21);
					delBotton.setBounds(651, 122, 56, 21);
					addBotton.setBounds(530, 122, 101, 21);
					lblnextPeriod.setBounds(729, 122, 101, 21);
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
		
	}
	
	private void tableDraw(){
		switch(btnSeleccionado){
			case PROFESOR:
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
				table = new JTableNoEdit(Runner.modeloEstudiante);
				table.getColumnModel().getColumn(2).setCellRenderer(centrarCelda);
				table.getColumnModel().getColumn(3).setCellRenderer(centrarCelda);
				tableSorter = new TableRowSorter<>(Runner.modeloEstudiante);
				table.setRowSorter(tableSorter);
				table.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent e) {
						if (e.getClickCount() == 2) 
							JOptionPane.showMessageDialog(null, "Para editar un campo use el botón modificar");
					}
				});
				break;
			case PERSOAUX:
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
			case PLANESTUDIO:
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
		}
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);
	}
	
	private void actualizarValoresDeFila(int filaSelec){	
		for(int i = 0; i < table.getColumnCount();i++){
			valoresDeFila[i]= table.getValueAt(filaSelec, i);
		}
	}
	public static JTableNoEdit getTable(){
		return table;
	}
}
