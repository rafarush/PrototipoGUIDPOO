package runner;


import gui.dialogs.*;
import gui.utils.ModeloGrupoEdit;
import gui.utils.ModeloPlanificacionDocenteEdit;
import gui.utils.Validaciones;

import java.util.ArrayList;

import javax.swing.JDialog;
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
		
		Fct.getInstance().generarDatosAutomaticos();

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
