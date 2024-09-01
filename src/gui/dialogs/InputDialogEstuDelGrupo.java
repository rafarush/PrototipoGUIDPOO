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

import logica.DatosAuto;
import logica.Fct;
import logica.JTableNoEdit;
import logica.PersonalApoyo;
import logica.Enums.BotonSelec;

import javax.swing.JScrollPane;
import javax.swing.JTable;



public class InputDialogEstuDelGrupo extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel upperBarPanel = new JPanel();
	private static JTableNoEdit table;
	private JScrollPane scrollPane;
	private JLabel lblEncabezado;
	final JLabel delBotton;
	private final JLabel atrasBtn;
	private boolean agregar = false;


	public InputDialogEstuDelGrupo(final String nombreGrupo) {
		setUndecorated(true);
		setModal(true);
		setBounds(100, 100, 560, 429);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		
		final JLabel closeBotton = new JLabel("");
		closeBotton.setIcon(new ImageIcon(InputDialogEstuDelGrupo.class.getResource("/gui/utils/closeBotton.png")));
		closeBotton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				closeBotton.setIcon(new ImageIcon(InputDialogEstuDelGrupo.class.getResource("/gui/utils/closeBottonSelected1.png")));
				closeBotton.setToolTipText("Cerrar");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				closeBotton.setIcon(new ImageIcon(InputDialogEstuDelGrupo.class.getResource("/gui/utils/closeBotton.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "¿Seguro que desea cerrar la ventana?", "Confirmación", 
						JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
					dispose();
				}
			}
		});
		closeBotton.setBounds(523, 0, 27, 34);
		upperBarPanel.add(closeBotton);
		
		
		upperBarPanel.setBackground(new Color(255, 255, 255));
		upperBarPanel.setBounds(0, 0, 560, 34);
		upperBarPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(upperBarPanel);
		upperBarPanel.setLayout(null);
		
		
		JLabel nameFrame = new JLabel("Entrada de datos (Estudiante a Grupo)");
		nameFrame.setBounds(10, 0, 254, 34);
		upperBarPanel.add(nameFrame);
		{
		    JPanel mainPanel = new JPanel();
			mainPanel.setBackground(Color.WHITE);
			mainPanel.setBounds(0, 34, 560, 395);
			getContentPane().add(mainPanel);
			mainPanel.setLayout(null);
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(0, 204, 255));
			panel.setBounds(413, 0, 147, 395);
			mainPanel.add(panel);
			
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 42, 393, 263);
			mainPanel.add(scrollPane);
			
			lblEncabezado = new JLabel("Estudiantes del Grupo:");
			lblEncabezado.setForeground(new Color(51, 51, 51));
			lblEncabezado.setBounds(126, 11, 223, 20);
			mainPanel.add(lblEncabezado);
			
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
					if(!agregar){
						agregar = true;
						tableDraw(nombreGrupo);
					}else{
						if(table.getSelectedRow()!=-1){
							String estId = table.getValueAt(table.getSelectedRow(), 0).toString();
							Fct.getInstance().buscarGrupo(nombreGrupo).insertarAGrupoEstudiante(Fct.getInstance().buscarUnEstudiante(estId));
							JOptionPane.showMessageDialog(null, "               CONFIRMACIÓN:\nSe añadió a "
									+Fct.getInstance().buscarUnEstudiante(estId).getNombre()
									+" al "+Fct.getInstance().buscarGrupo(nombreGrupo).getNombreGrupo());
							agregar = false;
							tableDraw(nombreGrupo);
						}else{
							JOptionPane.showMessageDialog(null, "Debe seleccionar el estudiante que desea agregar");
						}	
					}
				}
			});
			inputBotton.setIcon(new ImageIcon(InputDialogEst.class.getResource("/gui/utils/addBottonJDialog.png")));
			inputBotton.setBounds(102, 345, 63, 21);
			mainPanel.add(inputBotton);
			
			delBotton = new JLabel("");
			delBotton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			delBotton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					delBotton.setIcon(new ImageIcon(InputDialogEst.class.getResource("/gui/utils/delBottonSelected1.png")));
					delBotton.setToolTipText("Eliminar");
				}
				@Override
				public void mouseExited(MouseEvent e) {
					delBotton.setIcon(new ImageIcon(InputDialogEst.class.getResource("/gui/utils/delBotton.png")));
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					if(table.getSelectedRow() == -1){
						JOptionPane.showMessageDialog(null, "Debe seleccionar la fila que desea eliminar");
					}else{
						JOptionPane.showMessageDialog(null, "Función de sacar un estudiante del grupo");
					}
				}
			});
			delBotton.setIcon(new ImageIcon(InputDialogEstuDelGrupo.class.getResource("/gui/utils/delBotton.png")));
			delBotton.setBounds(211, 345, 63, 21);
			mainPanel.add(delBotton);
			
			
			atrasBtn = new JLabel("");
			atrasBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			atrasBtn.setIcon(new ImageIcon(ControlDocenteFrame.class.getResource("/gui/utils/flechaAtras24Selected.png")));
			atrasBtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					atrasBtn.setIcon(new ImageIcon(ControlDocenteFrame.class.getResource("/gui/utils/flechaAtras24Selected.png")));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					atrasBtn.setIcon(new ImageIcon(ControlDocenteFrame.class.getResource("/gui/utils/flechaAtras24.png")));
				}
				@Override
				public void mouseClicked(MouseEvent e){
					if (!agregar){
						JOptionPane.showMessageDialog(null, "No hay ruta hacia atrás");
					}else{
						agregar = false;
						tableDraw(nombreGrupo);
						actualizarNombreEncabezado();
					}
				}
			});
			atrasBtn.setToolTipText("Retornar a los estudiantes del grupo");
			atrasBtn.setBounds(10, 26, 24, 14);
			mainPanel.add(atrasBtn);
			
			tableDraw(nombreGrupo);
		}
	}
	
	private void tableDraw(String nombreGrupo){
		if (!agregar){
			DatosAuto.definirTablaEstudiantesCorto(Fct.getInstance().buscarGrupo(nombreGrupo).getGrupoEstudiantes());
			table = new JTableNoEdit(Runner.modeloEstudiante);
			table.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if (e.getClickCount() == 2) {
						JOptionPane.showMessageDialog(null, "No se permite modificación");
					}
				}
			});
			table.getTableHeader().setReorderingAllowed(false);
			scrollPane.setViewportView(table);
			actualizarNombreEncabezado();
		}else{
			DatosAuto.definirTablaEstudiantesCorto(Fct.getInstance().buscarEstudiantesSinGrupo());
			table = new JTableNoEdit(Runner.modeloEstudiante);
			table.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if (e.getClickCount() == 2) 
						JOptionPane.showMessageDialog(null, "No se permite modificación");
				}
			});
			table.getTableHeader().setReorderingAllowed(false);
			scrollPane.setViewportView(table);
			actualizarNombreEncabezado();
		}
		
	}
	
	private void actualizarNombreEncabezado(){
		if(!agregar){
			lblEncabezado.setText("Estudiantes del Grupo:");
			delBotton.setEnabled(true);
		}else{
			lblEncabezado.setText("Seleccione y añada al Estudiante:");
			delBotton.setEnabled(false);
		}
	}
}
