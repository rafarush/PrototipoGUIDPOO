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
	
	public static MainFrame frame;
	public static User usuario;
	public static LoginUser login;
	public static InputDialogProfe inputProfe;
	public static InputDialogEst inputEst;
	public static InputDialogGrupo inputGrupo;
	public static InputDialogPerAux inputPerAux;
	public static InputDialogAsignaturaPE inputAsignatura;
	public static InputDialogNota inputNota;
	public static InputDialogModifConsejoDirecc inputModConDir;
	public static InputDialogEstuDelGrupo inputDialogEstuDelGrupo;
	public static InputDialogProfeAConsejo inputProfeAConsejo;
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
		
		// JORGITOOOOOOO ---->>>>    la creacion de los profesores por defecto
		/*Fct.getInstance().crearPersona("95868426587", "Luis Pérez Fernández","Doctor","Instructor","CineSoft","InfoCuba", "Ave. 26 entre calles A y B");
		Fct.getInstance().crearPersona("05062348364", "Rafael Castro Reyes","Doctor","Titular","Cujae","MINES", "Calle 30 entre 34 y Ave. 56");
		Fct.getInstance().crearPersona("05022358174", "Jorge Castro Pérez","Máster","Asistente","Cujae","MINES", "Calle 25 entre 21 y Ave. 26");
		*/
		Fct.getInstance().crearPersona("05064807564", "Manuel Cuadrado Aldiva","Máster","Titular","Cujae","MINES", "Calle 30 entre 34 y Ave. 56");
		Profesor profe = (Profesor) Fct.getInstance().buscarPersona("05064807564");
		profe.setCargoConsejoDireccion("Director");
	
		
		//System.out.println(((Profesor)Fct.getInstance().buscarPersona("95868426587")).calcularSalario());
		
		//DatosAuto.definirTablaProfes(Fct.getInstance().buscarProfesores());
		//DatosAuto.llenarFilasProfes(Fct.getInstance().buscarProfesores());
		

		// JORGITOOOOOOO ---->>>>    la creacion de los estudiantes por defecto
		/*Fct.getInstance().crearPersona("05032379581", "Rafael Menéndez Rodriguez", 1 ,"Sucursal Comercial #5","Etecsa", "Calle 30 entre 34 y Ave. 56");
		Fct.getInstance().crearPersona("08868513264", "Alejandro González Fernández",1,"La Mariposa","TRD","Ave. 26 entre calles A y B");
		Fct.getInstance().crearPersona("04021324587", "Jorgito", 2, "Las Palamas", "CTC", "Tulipan y Boyeros");
		Fct.getInstance().crearPersona("04021334457", "Rafa", 6, "Las Palamas", "CTC", "Tulipan y Boyeros");
		Fct.getInstance().crearPersona("90990834457", "TOYCHOLITO", 6, "Las Palamas", "CTC", "Tulipan y Boyeros");
		*/
		
		
		//DatosAuto.definirTablaEstudiantes(Fct.getInstance().buscarEstudiantes());
		
		
		// JORGITOOOOOOO ---->>>>    la creacion del personal de apoyo por defecto
		/*Fct.getInstance().crearPersona("09062235147", "Federico Criado Domínguez","Laboratorio", "Calle 30 entre 34 y Ave. 56");
		Fct.getInstance().crearPersona("59868285496", "Maria Elena Gómez Pérez","Biblioteca", "Ave. 26 entre calles A y B");
		*/
							
		//DatosAuto.definirTablaPersonalAux(Fct.getInstance().buscarPersonalApoyo());
		
		
		// JORGITOOOOOOO ---->>>>    la creacion de las asignaturas por defecto
		/*Fct.getInstance().getPlanEstudio().crearAsignatura("Matemática I", 1, 1, 50);
		Fct.getInstance().getPlanEstudio().crearAsignatura("Matemática II", 1, 2, 50);
		Fct.getInstance().getPlanEstudio().crearAsignatura("Introducción a la Programación", 1, 1, 60);
		Fct.getInstance().getPlanEstudio().crearAsignatura("Diseño y POO", 1, 2, 90);
		Fct.getInstance().getPlanEstudio().crearAsignatura("Estructuras de Datos", 2, 1, 40);
		Fct.getInstance().getPlanEstudio().crearAsignatura("Seguridad Nacional", 2, 1, 90);
		*/
		//DatosAuto.definirTablaPlanDeEstudio(Fct.getInstance().getPlanEstudio().getAsignaturas());
		
		//ESTUDIANTE PARA REPORTES
		//DatosAuto.definirTablaReportesEstu(Fct.getInstance().buscarEstudiantesOros());
		
		// GRUPOS
		/*Fct.getInstance().crearGrupo("13", 1);
		Fct.getInstance().crearGrupo("14", 1);
		Fct.getInstance().crearGrupo("64", 6);
		*/
		
		// AGREGAR A GRUPOS
		/*Fct.getInstance().buscarGrupo("13").insertarAGrupoEstudiante(Fct.getInstance().buscarUnEstudiante("05032379581"));
		Fct.getInstance().buscarGrupo("13").insertarAGrupoEstudiante(Fct.getInstance().buscarUnEstudiante("08868513264"));
		Fct.getInstance().buscarGrupo("13").insertarAGrupoEstudiante(Fct.getInstance().buscarUnEstudiante("04021324587"));
		Fct.getInstance().buscarGrupo("64").insertarAGrupoEstudiante(Fct.getInstance().buscarUnEstudiante("04021334457"));
		*/
		
		//PLANES DOCENTES
		
		/*Fct.getInstance().getPeriodos().get(0).crearPlanificacionDocente(Fct.getInstance().buscarUnProfesor("95868426587"),Fct.getInstance().getPlanEstudio().buscarAsignatura("Matemática I") , Fct.getInstance().buscarGrupo("13"));
		Fct.getInstance().getPeriodos().get(0).crearPlanificacionDocente(Fct.getInstance().buscarUnProfesor("05062347564"),Fct.getInstance().getPlanEstudio().buscarAsignatura("Introducción a la Programación") , Fct.getInstance().buscarGrupo("13"));
		*/
		
	    //DatosAuto.definirTablaPlanDocente(Fct.getInstance().getPeriodos().get(0).getPlanificacionesDocentes());
		//DatosAuto.llenarTablaPlanificacionDocente(Fct.getInstance().getPeriodos().get(0).getPlanificacionesDocentes());
	    
	    
		Fct.getInstance().getPlanEstudio().crearAsignatura("Matemática III", 2, 1, 50);
		Fct.getInstance().getPlanEstudio().crearAsignatura("IA", 2, 2, 50);
	    
		Fct.getInstance().crearGrupo("Grupo 2.2", 2);
		Fct.getInstance().crearGrupo("Grupo 2.1", 2);
	    
		Fct.getInstance().getPeriodos().get(1).crearPlanificacionDocente(Fct.getInstance().buscarUnProfesor("95868426587"),Fct.getInstance().getPlanEstudio().buscarAsignatura("Matemática III") , Fct.getInstance().buscarGrupo("Grupo 2.1"));
		Fct.getInstance().getPeriodos().get(7).crearPlanificacionDocente(Fct.getInstance().buscarUnProfesor("95868426587"),Fct.getInstance().getPlanEstudio().buscarAsignatura("IA") , Fct.getInstance().buscarGrupo("Grupo 2.2"));
	    
	    
		try {
			login = new LoginUser();
			login.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			login.setVisible(true);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
		/******************************************************************************************************************/
		
		
	}

}
