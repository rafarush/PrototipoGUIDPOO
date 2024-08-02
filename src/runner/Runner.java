package runner;


import gui.dialogs.*;
import gui.mainFrame.MainFrame;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import logica.DatosAuto;
import logica.Estudiante;
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
	public static ArrayList<Estudiante> estudiantes;
	//public static ArrayList<PersoAux> personalAux;
	//public static ArrayList<Asignaturas> Asignaturas;
	
	
	/**
	* Launch the application.
	*/
	public static void main(String[] args) {
		
		/********************Datos Prueba*****************************/
		
		//PROFESORES
		Profesor profe1 = new Profesor("95868426587", "Luis P�rez Fern�ndez","Doctor","Instructor","CineSoft","InfoCuba", "Ave. 26 entre calles A y B", 0.0f);
		Profesor profe2 = new Profesor("05062348364", "Rafael Castro Reyes","Doctor","Profesor Titular","Cujae","MINES", "Calle 30 entre 34 y Ave. 56", 0.0f);
		Profesor profe3 = new Profesor("05022358174", "Jorge Castro P�rez","M�ster","Asistente","Cujae","MINES", "Calle 25 entre 21 y Ave. 26", 0.0f);
		Profesor profe4 = new Profesor("05062347564", "Manuel Castro Reyes","M�ster","Profesor Titular","Cujae","MINES", "Calle 30 entre 34 y Ave. 56", 0.0f);
		
		profesores = new ArrayList<Profesor>();
		profesores.add(profe1);
		profesores.add(profe2);
		profesores.add(profe3);
		profesores.add(profe4);
	
		DatosAuto.definirTablaProfes(profesores);
		
		//ESTUDIANTES
		Estudiante estu1 = new Estudiante("05032379581", "Rafael Men�ndez Rodriguez","1",null,"Sucursal Comercial #5","Etecsa", "Calle 30 entre 34 y Ave. 56");
		Estudiante estu2 = new Estudiante("08868513264", "Alejandro Gonz�lez Fern�ndez","1",null,"La Mariposa","TRD","Ave. 26 entre calles A y B");
			
		estudiantes = new ArrayList<Estudiante>();
		estudiantes.add(estu1);
		estudiantes.add(estu2);
		
		DatosAuto.definirTablaEstudiantes(estudiantes);
		
		
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
		
		
	}

}
