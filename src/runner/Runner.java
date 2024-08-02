package runner;


import gui.dialogs.*;
import gui.mainFrame.MainFrame;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import logica.DatosAuto;
import logica.Profesor;


public class Runner {
	
	public static MainFrame frame;
	public static InputDialogProfe inputProfe;
	public static InputDialogEst inputEst;
	public static InputDialogPerAux inputPerAux;
	public static DefaultTableModel modeloProfesor;
	public static DefaultTableModel modeloEstudiante;
	public static DefaultTableModel modeloPersonalAux;
	public static DefaultTableModel modeloPlanDeEstudio;
	public static ArrayList<Profesor> profesores;
	
	
	/**
	* Launch the application.
	*/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainFrame();
					ImageIcon appIcono = new ImageIcon(MainFrame.class.getResource("/gui/utils/appicon.png"));
					frame.setIconImage(appIcono.getImage());
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		/*
		Profesor profe1 = new Profesor("95868426587", "Luis Pérez Fernández","Doctor","Instructor","CineSoft","InfoCuba", "Ave. 26 entre calles A y B", 0.0f);
		Profesor profe2 = new Profesor("05062348364", "Rafael Castro Reyes","Doctor","Profesor Titular","Cujae","MINES", "Calle 30 entre 34 y Ave. 56", 0.0f);
		Profesor profe3 = new Profesor("05022358174", "Jorge Castro Pérez","Máster","Asistente","Cujae","MINES", "Calle 25 entre 21 y Ave. 26", 0.0f);
		Profesor profe4 = new Profesor("05062347564", "Manuel Castro Reyes","Máster","Profesor Titular","Cujae","MINES", "Calle 30 entre 34 y Ave. 56", 0.0f);
		
		profesores = new ArrayList<Profesor>();
		profesores.add(profe1);
		profesores.add(profe2);
		profesores.add(profe3);
		profesores.add(profe4);
		
		DatosAuto.agregarFilasProfes(profesores);
		*/
	}

}
