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
import javax.swing.table.TableRowSorter;

import runner.Runner;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

import logica.ComboBoxTextInicial;
import logica.DatosAuto;
import logica.Estudiante;
import logica.Grupo;
import logica.JTableNoEdit;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class Reportes extends JDialog {

	private final JPanel upperBarPanel = new JPanel();
	private JComboBox reportesComboBox;
	private JScrollPane scrollPane;
	private static JTableNoEdit tablaReportes;
	DefaultTableCellRenderer centrarCelda = new DefaultTableCellRenderer();


	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		try {
			Reportes dialog = new Reportes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	/**
	 * Create the dialog.
	 */
	public Reportes() {
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
				if(JOptionPane.showConfirmDialog(null, "�Seguro que desea cerrar la entrada de datos?\n Su progreso no se guardar�", "Confirmaci�n", 
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
		
		
		JLabel nameFrame = new JLabel("Reportes");
		nameFrame.setBounds(10, 0, 254, 34);
		upperBarPanel.add(nameFrame);
		{
			 JPanel mainPanel = new JPanel();
				mainPanel.setBackground(Color.WHITE);
				mainPanel.setBounds(0, 34, 617, 381);
				getContentPane().add(mainPanel);
				mainPanel.setLayout(null);
				
				JPanel panel = new JPanel();
				panel.setBackground(new Color(0, 204, 255));
				panel.setBounds(485, 0, 132, 381);
				mainPanel.add(panel);
				
				
				JLabel lblReportes = new JLabel("Lista de Reportes:");
				lblReportes.setHorizontalAlignment(SwingConstants.CENTER);
				lblReportes.setForeground(new Color(51, 51, 51));
				lblReportes.setBounds(146, 11, 166, 20);
				mainPanel.add(lblReportes);
				
				scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 98, 465, 272);
				mainPanel.add(scrollPane);
				
				reportesComboBox = new JComboBox();
				reportesComboBox.setToolTipText("Desplegar lista de reportes");
				reportesComboBox.setModel(new DefaultComboBoxModel(new String[] {null,"Estudiantes sin nota", 
						"Estudiantes sin grupos", "Estudiantes graduados", "Estudiantes suspensos en 1 o 2 asignaturas", 
						"Estudiantes con m\u00E1s de 4.5 de \u00EDndice acad\u00E9mico", 
						"Grupo con menor cantidad de estudiantes(Dado el a\u00F1o)"}));
				reportesComboBox.setRenderer(new ComboBoxTextInicial("Seleccione un reporte"));
				reportesComboBox.setSelectedIndex(0);
				reportesComboBox.addActionListener(new ActionListener() {			
					@Override
					public void actionPerformed(ActionEvent e) {
						//Dibuja las tablas con datos predeterminados
						centrarCelda.setHorizontalAlignment(JLabel.CENTER);
						tableDraw();
					}
				});
				reportesComboBox.setBounds(77, 42, 326, 20);
				mainPanel.add(reportesComboBox);		
				
		}	
		
	}

	private void tableDraw(){
		if (reportesComboBox.getSelectedIndex()>=1 && reportesComboBox.getSelectedIndex()<=5){
			tablaReportes = new JTableNoEdit(Runner.modeloEstudianteReporte);
			tablaReportes.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if (e.getClickCount() == 2) 
						JOptionPane.showMessageDialog(null, "Para editar un campo use el bot�n modificar");
				}
			});
			tablaReportes.getColumnModel().getColumn(2).setCellRenderer(centrarCelda);
			tablaReportes.getTableHeader().setReorderingAllowed(false);
			scrollPane.setViewportView(tablaReportes);
		}else if(reportesComboBox.getSelectedIndex()==6){
			tablaReportes = new JTableNoEdit(Runner.modeloGrupoReporte);
			tablaReportes.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if (e.getClickCount() == 2) 
						DatosAuto.definirTablaReportesEstu(Runner.gruposReportes.get(tablaReportes.getSelectedRow()).getEstudiantes());
						tablaReportes = new JTableNoEdit(Runner.modeloEstudianteReporte);
						tablaReportes.addMouseListener(new java.awt.event.MouseAdapter() {
							public void mouseClicked(java.awt.event.MouseEvent e) {
								if (e.getClickCount() == 2) 
									JOptionPane.showMessageDialog(null, "Para editar un campo use el bot�n modificar");
							}
						});
						tablaReportes.getColumnModel().getColumn(2).setCellRenderer(centrarCelda);
						tablaReportes.getTableHeader().setReorderingAllowed(false);
						scrollPane.setViewportView(tablaReportes);
						}
					});
				tablaReportes.getColumnModel().getColumn(1).setCellRenderer(centrarCelda);
				tablaReportes.getTableHeader().setReorderingAllowed(false);
				scrollPane.setViewportView(tablaReportes);
		}else{
			JOptionPane.showMessageDialog(null, "Seleccione un reporte en el ComboBox");
		}
	}
	
}
