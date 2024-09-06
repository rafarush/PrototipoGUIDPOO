package runner;


import gui.dialogs.*;
import gui.mainFrame.MainFrame;
import gui.utils.ModeloGrupoEdit;
import gui.utils.ModeloPlanificacionDocenteEdit;
import gui.utils.ModeloProfesorEdit;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import logica.*;


public class Runner {
	
	public static User usuario;
	public static DefaultTableModel modeloProfesor;
	public static DefaultTableModel modeloProfesorConsejo;
	public static DefaultTableModel modeloEstudiante;
	public static DefaultTableModel modeloEstudianteCorto;
	public static DefaultTableModel modeloPersonalAux;
	public static DefaultTableModel modeloPlanDeEstudio;
	public static DefaultTableModel modeloGrupoReporte;
	public static ModeloGrupoEdit modeloGrupoReporte1;
	public static DefaultTableModel modeloEstudianteReporte;
	public static ModeloPlanificacionDocenteEdit modeloPlanDocente1;
	public static DefaultTableModel modeloPlanDocente;
	public static ArrayList<Estudiante> estudiantesReporte;
	public static ArrayList<Grupo> gruposReportes;
	public static ArrayList<PlanificacionDocente> planesDocentes;
	
	
	/**
	* Launch the application.
	*/
	public static void main(String[] args) {
		
		
		/**********************************************Datos Prueba********************************************************/
		
		//Usuario
	    usuario = new User("Fermin", "1234");
		
		Fct.getInstance().datosAutomaticos();
		

		Fct.getInstance().crearPersona("05064807564", "Manuel Cuadrado Aldiva","Máster","Titular","Cujae","MINES", "Calle 30 entre 34 y Ave. 56");
		Profesor profe = (Profesor) Fct.getInstance().buscarPersona("05064807564");
		profe.setCargoConsejoDireccion("Director");
	    
		Fct.getInstance().getPlanEstudio().crearAsignatura("Matemática III", 2, 1, 50);
		Fct.getInstance().getPlanEstudio().crearAsignatura("IA", 2, 2, 50);
	    
		Fct.getInstance().crearGrupo("Grupo 2.2", 2);
		Fct.getInstance().crearGrupo("Grupo 2.1", 2);
	    
		Fct.getInstance().getPeriodos().get(1).crearPlanificacionDocente(Fct.getInstance().buscarUnProfesor("95868426587"),Fct.getInstance().getPlanEstudio().buscarAsignatura("Matemática III") , Fct.getInstance().buscarGrupo("Grupo 2.1"));
		Fct.getInstance().getPeriodos().get(7).crearPlanificacionDocente(Fct.getInstance().buscarUnProfesor("95868426587"),Fct.getInstance().getPlanEstudio().buscarAsignatura("IA") , Fct.getInstance().buscarGrupo("Grupo 2.2"));
	    
	    
		try {
			JDialogLoginUser login = new JDialogLoginUser();
			login.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			login.setVisible(true);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
		/******************************************************************************************************************/
		
		
	}

}
