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

import java.awt.Cursor;


import logica.Clases.*;
import gui.utils.*;
import javax.swing.JScrollPane;



public class InputDialogEstuDelGrupo extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel upperBarPanel = new JPanel();
	private static JTableNoEdit table;
	private JScrollPane scrollPane;
	private JLabel lblEncabezado;
	final JLabel delBotton;
	private final JLabel atrasBtn;
	private boolean agregar = false;
	
	
    /**
     * Crea el JDialog para editar el grupo
     * @param grupo
     */
	public InputDialogEstuDelGrupo(final Grupo grupo) {
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
				if(JOptionPane.showConfirmDialog(null, "�Seguro que desea cerrar la ventana?", "Confirmaci�n", 
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
						tableDraw(grupo);
					}else{
						if(table.getSelectedRow()!=-1){
							String estId = table.getValueAt(table.getSelectedRow(), 0).toString();
							Fct.getInstance().buscarGrupo(grupo.getNombreGrupo()).insertarAGrupoEstudiante(Fct.getInstance().buscarUnEstudiante(estId));
							JOptionPane.showMessageDialog(null, "               CONFIRMACI�N:\nSe a�adi� a "
									+Fct.getInstance().buscarUnEstudiante(estId).getNombre()
									+" al "+Fct.getInstance().buscarGrupo(grupo.getNombreGrupo()).getNombreGrupo());
							agregar = false;
							tableDraw(grupo);
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
						if(!Fct.getInstance().verificarGrupoPD(grupo)){
							String estudianteID = table.getValueAt(table.getSelectedRow(), 0).toString();
							Estudiante estudiante = (Estudiante)Fct.getInstance().buscarPersona(estudianteID);
							if(Fct.getInstance().eliminarEstudianteDeGrupo(estudiante, grupo)){
								JOptionPane.showMessageDialog(null, "Se ha sacado del grupo al estudiante satisfactoriamente");
								tableDraw(grupo);
							}else{
								JOptionPane.showMessageDialog(null, "No se pudo eliminar al estudiante debido a un error");
							}
							
						}else{
							JOptionPane.showMessageDialog(null, "No puede eliminar un estudiante de un grupo que tenga Planificaci�n Docente");
						}
					}
				}
			});
			delBotton.setIcon(new ImageIcon(InputDialogEstuDelGrupo.class.getResource("/gui/utils/delBotton.png")));
			delBotton.setBounds(211, 345, 63, 21);
			mainPanel.add(delBotton);
			
			
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
					if (!agregar){
						JOptionPane.showMessageDialog(null, "No hay ruta hacia atr�s");
					}else{
						agregar = false;
						tableDraw(grupo);
						actualizarNombreEncabezado();
					}
				}
			});
			atrasBtn.setToolTipText("Retornar a los estudiantes del grupo");
			atrasBtn.setBounds(10, 26, 24, 14);
			mainPanel.add(atrasBtn);
			
			tableDraw(grupo);
		}
	}
	
	private void tableDraw(Grupo grupo){
		if (!agregar){
			DefinidorDeModelo.definirTablaEstudiantesCorto(Fct.getInstance().buscarGrupo(grupo.getNombreGrupo()).getGrupoEstudiantes());
			table = new JTableNoEdit(Runner.modeloEstudianteCorto);
			table.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if (e.getClickCount() == 2) {
						JOptionPane.showMessageDialog(null, "No se permite modificaci�n");
					}
				}
			});
			table.getTableHeader().setReorderingAllowed(false);
			scrollPane.setViewportView(table);
			actualizarNombreEncabezado();
		}else{
			DefinidorDeModelo.definirTablaEstudiantesCorto(Fct.getInstance().buscarEstudiantesSinGrupoPorAnno(grupo.getAnnoAcademico()));
			table = new JTableNoEdit(Runner.modeloEstudianteCorto);
			table.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if (e.getClickCount() == 2) 
						JOptionPane.showMessageDialog(null, "No se permite modificaci�n");
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
			lblEncabezado.setText("Seleccione y a�ada al Estudiante:");
			delBotton.setEnabled(false);
		}
	}
}
