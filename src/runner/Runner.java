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
	public static Fct fct;
	
	
	/**
	* Launch the application.
	*/
	public static void main(String[] args) {
		
		
		/**********************************************Datos Prueba********************************************************/
		
		//Usuario
		usuario = new User("Fermin", "1234");
		
		fct = new Fct();
		
		fct.datosAutomaticos();
		
		// JORGITOOOOOOO ---->>>>    la creacion de los profesores por defecto
		/*fct.crearPersona("95868426587", "Luis Pérez Fernández","Doctor","Instructor","CineSoft","InfoCuba", "Ave. 26 entre calles A y B");
		fct.crearPersona("05062348364", "Rafael Castro Reyes","Doctor","Titular","Cujae","MINES", "Calle 30 entre 34 y Ave. 56");
		fct.crearPersona("05022358174", "Jorge Castro Pérez","Máster","Asistente","Cujae","MINES", "Calle 25 entre 21 y Ave. 26");
		*/
		fct.crearPersona("05064807564", "Manuel Cuadrado Aldiva","Máster","Titular","Cujae","MINES", "Calle 30 entre 34 y Ave. 56");
		Profesor profe = (Profesor) fct.buscarPersona("05064807564");
		profe.setCargoConsejoDireccion("Director");
	
		
		//System.out.println(((Profesor)fct.buscarPersona("95868426587")).calcularSalario());
		
		//DatosAuto.definirTablaProfes(fct.buscarProfesores());
		//DatosAuto.llenarFilasProfes(fct.buscarProfesores());
		

		// JORGITOOOOOOO ---->>>>    la creacion de los estudiantes por defecto
		/*fct.crearPersona("05032379581", "Rafael Menéndez Rodriguez", 1 ,"Sucursal Comercial #5","Etecsa", "Calle 30 entre 34 y Ave. 56");
		fct.crearPersona("08868513264", "Alejandro González Fernández",1,"La Mariposa","TRD","Ave. 26 entre calles A y B");
		fct.crearPersona("04021324587", "Jorgito", 2, "Las Palamas", "CTC", "Tulipan y Boyeros");
		fct.crearPersona("04021334457", "Rafa", 6, "Las Palamas", "CTC", "Tulipan y Boyeros");
		fct.crearPersona("90990834457", "TOYCHOLITO", 6, "Las Palamas", "CTC", "Tulipan y Boyeros");
		*/
		
		
		//DatosAuto.definirTablaEstudiantes(fct.buscarEstudiantes());
		
		
		// JORGITOOOOOOO ---->>>>    la creacion del personal de apoyo por defecto
		/*fct.crearPersona("09062235147", "Federico Criado Domínguez","Laboratorio", "Calle 30 entre 34 y Ave. 56");
		fct.crearPersona("59868285496", "Maria Elena Gómez Pérez","Biblioteca", "Ave. 26 entre calles A y B");
		*/
							
		//DatosAuto.definirTablaPersonalAux(fct.buscarPersonalApoyo());
		
		
		// JORGITOOOOOOO ---->>>>    la creacion de las asignaturas por defecto
		/*fct.getPlanEstudio().crearAsignatura("Matemática I", 1, 1, 50);
		fct.getPlanEstudio().crearAsignatura("Matemática II", 1, 2, 50);
		fct.getPlanEstudio().crearAsignatura("Introducción a la Programación", 1, 1, 60);
		fct.getPlanEstudio().crearAsignatura("Diseño y POO", 1, 2, 90);
		fct.getPlanEstudio().crearAsignatura("Estructuras de Datos", 2, 1, 40);
		fct.getPlanEstudio().crearAsignatura("Seguridad Nacional", 2, 1, 90);
		*/
		//DatosAuto.definirTablaPlanDeEstudio(fct.getPlanEstudio().getAsignaturas());
		
		//ESTUDIANTE PARA REPORTES
		//DatosAuto.definirTablaReportesEstu(fct.buscarEstudiantesOros());
		
		// GRUPOS
		/*fct.crearGrupo("13", 1);
		fct.crearGrupo("14", 1);
		fct.crearGrupo("64", 6);
		*/
		
		// AGREGAR A GRUPOS
		/*fct.buscarGrupo("13").insertarAGrupoEstudiante(fct.buscarUnEstudiante("05032379581"));
		fct.buscarGrupo("13").insertarAGrupoEstudiante(fct.buscarUnEstudiante("08868513264"));
		fct.buscarGrupo("13").insertarAGrupoEstudiante(fct.buscarUnEstudiante("04021324587"));
		fct.buscarGrupo("64").insertarAGrupoEstudiante(fct.buscarUnEstudiante("04021334457"));
		*/
		
		//PLANES DOCENTES
		
		/*fct.getPeriodos().get(0).crearPlanificacionDocente(fct.buscarUnProfesor("95868426587"),fct.getPlanEstudio().buscarAsignatura("Matemática I") , fct.buscarGrupo("13"));
		fct.getPeriodos().get(0).crearPlanificacionDocente(fct.buscarUnProfesor("05062347564"),fct.getPlanEstudio().buscarAsignatura("Introducción a la Programación") , fct.buscarGrupo("13"));
		*/
		
	    //DatosAuto.definirTablaPlanDocente(fct.getPeriodos().get(0).getPlanificacionesDocentes());
		//DatosAuto.llenarTablaPlanificacionDocente(fct.getPeriodos().get(0).getPlanificacionesDocentes());
	    
	    
	    fct.getPlanEstudio().crearAsignatura("Matemática III", 2, 1, 50);
	    fct.getPlanEstudio().crearAsignatura("IA", 2, 2, 50);
	    
	    fct.crearGrupo("Grupo 2.2", 2);
	    fct.crearGrupo("Grupo 2.1", 2);
	    
	    fct.getPeriodos().get(1).crearPlanificacionDocente(fct.buscarUnProfesor("95868426587"),fct.getPlanEstudio().buscarAsignatura("Matemática III") , fct.buscarGrupo("Grupo 2.1"));
	    fct.getPeriodos().get(7).crearPlanificacionDocente(fct.buscarUnProfesor("95868426587"),fct.getPlanEstudio().buscarAsignatura("IA") , fct.buscarGrupo("Grupo 2.2"));
	    
	    
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
