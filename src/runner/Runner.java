package runner;


import gui.dialogs.*;
import gui.mainFrame.MainFrame;

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
	public static InputDialogPerAux inputPerAux;
	public static InputDialogAsignaturaPE inputAsignatura;
	public static InputDialogNota inputNota;
	public static DefaultTableModel modeloProfesor;
	public static DefaultTableModel modeloEstudiante;
	public static DefaultTableModel modeloPersonalAux;
	public static DefaultTableModel modeloPlanDeEstudio;
	public static DefaultTableModel modeloGrupoReporte;
	public static DefaultTableModel modeloEstudianteReporte;
	public static DefaultTableModel modeloPlanDocente;
	public static ArrayList<Estudiante> estudiantesReporte;
	public static ArrayList<Grupo> gruposReportes;
	public static ArrayList<PlanificacionDocente> planesDocentes;
	
	Fct fct;
	
	/**
	* Launch the application.
	*/
	public static void main(String[] args) {
		
		/**********************************************Datos Prueba********************************************************/
		//Usuario
		usuario = new User("Fermin", "1234");
		
		fct = new Fct();
	
		// JORGITOOOOOOO ---->>>>    la creacion de los profesores por defecto
		fct.crearPersona("95868426587", "Luis P�rez Fern�ndez","Doctor","Instructor","CineSoft","InfoCuba", "Ave. 26 entre calles A y B");
		fct.crearPersona("05062348364", "Rafael Castro Reyes","Doctor","Titular","Cujae","MINES", "Calle 30 entre 34 y Ave. 56");
		fct.crearPersona("05022358174", "Jorge Castro P�rez","M�ster","Asistente","Cujae","MINES", "Calle 25 entre 21 y Ave. 26");
		fct.crearPersona("05062347564", "Manuel Castro Reyes","M�ster","Titular","Cujae","MINES", "Calle 30 entre 34 y Ave. 56");
		
		DatosAuto.definirTablaProfes(fct.buscarProfesores());
		

		// JORGITOOOOOOO ---->>>>    la creacion de los estudiantes por defecto
		fct.crearPersona("05032379581", "Rafael11111 Men�ndez Rodriguez", 1 ,"Sucursal Comercial #5","Etecsa", "Calle 30 entre 34 y Ave. 56");
		fct.crearPersona("08868513264", "Alejandro Gonz�lez Fern�ndez",1,"La Mariposa","TRD","Ave. 26 entre calles A y B");
		fct.crearPersona("04021324587", "Jorgito", 2, "Las Palamas", "CTC", "Tulipan y Boyeros");
		
		
		DatosAuto.definirTablaEstudiantes(fct.buscarEstudiantes());
					
		
		// JORGITOOOOOOO ---->>>>    la creacion del personal de apoyo por defecto
		fct.crearPersona("09062235147", "Federico Criado Dom�nguez","Laboratorio", "Calle 30 entre 34 y Ave. 56");
		fct.crearPersona("59868285496", "Maria Elena G�mez P�rez","Biblioteca", "Ave. 26 entre calles A y B");
		
							
		DatosAuto.definirTablaPersonalAux(fct.buscarPersonalApoyo());
		
		
		// JORGITOOOOOOO ---->>>>    la creacion de las asignaturas por defecto
		fct.getPlanEstudio().crearAsignatura("Matem�tica I", 1, 1, 50);
		fct.getPlanEstudio().crearAsignatura("Matem�tica II", 1, 2, 50);
		fct.getPlanEstudio().crearAsignatura("Introducci�n a la Programaci�n", 1, 1, 60);
		fct.getPlanEstudio().crearAsignatura("Dise�o y POO", 1, 2, 90);
		fct.getPlanEstudio().crearAsignatura("Estructuras de Datos", 2, 1, 40);
		fct.getPlanEstudio().crearAsignatura("Seguridad Nacional", 2, 1, 90);
		
		DatosAuto.definirTablaPlanDeEstudio(fct.getPlanEstudio().getAsignaturas());
		
		//ESTUDIANTE PARA REPORTES
		DatosAuto.definirTablaReportesEstu(fct.buscarEstudiantesOros());

		
		
		// GRUPOS
		fct.crearGrupo("Grupo", 1);
		
		
		// AGREGAR A GRUPOS
		fct.buscarGrupo("Grupo").insertarAGrupoEstudiante(fct.buscarUnEstudiante("05032379581"));
		fct.buscarGrupo("Grupo").insertarAGrupoEstudiante(fct.buscarUnEstudiante("08868513264"));
		fct.buscarGrupo("Grupo").insertarAGrupoEstudiante(fct.buscarUnEstudiante("04021324587"));
		
		
		
		
		
		// PRUEBAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
		System.out.println(fct.buscarGrupo("Grupo").getNombreGrupo());
		System.out.println(fct.buscarUnProfesor("95868426587").getNombre());
		System.out.println(fct.buscarUnProfesor("05062347564").getNombre());
		
		
		
		
		//PLANES DOCENTES
		
		fct.getPeriodos().get(0).crearPlanificacionDocente(fct.buscarUnProfesor("95868426587"),fct.getPlanEstudio().buscarAsignatura("Matem�tica I") , fct.buscarGrupo("Grupo"));
		fct.getPeriodos().get(0).crearPlanificacionDocente(fct.buscarUnProfesor("05062347564"),fct.getPlanEstudio().buscarAsignatura("Introducci�n a la Programaci�n") , fct.buscarGrupo("Grupo"));
		
		DatosAuto.definirTablaPlanDocente(fct.getPeriodos().get(0).getPlanificacionesDocentes());
		
		
		//GRUPOS PARA REPORTES
		System.out.println(fct.buscarGrupoConMenorCantidad().get(0).getAnnoAcademico());
		System.out.println(fct.buscarGrupoConMenorCantidad().get(0).getNombreGrupo());
		System.out.println(fct.buscarGrupoConMenorCantidad().size());
		
		DatosAuto.definirTablaGrupo(fct.buscarGrupoConMenorCantidad());
				
		System.out.println("LLEGAAAAAAA");
		
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
