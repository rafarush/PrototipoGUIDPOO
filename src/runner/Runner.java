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
	public static ArrayList<Asignatura> asignaturas;
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
		
		Fct fct = new Fct();
	
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

		//GRUPOS PARA REPORTES
		gruposReportes = new ArrayList<Grupo>();
		Grupo grupo1 = new Grupo("Grupo 1", 1);
		grupo1.insertarAGrupoEstudiante(estu1);
		grupo1.insertarAGrupoEstudiante(estu2);
		Grupo grupo2 = new Grupo("Grupo 2", 1);
		grupo2.insertarAGrupoEstudiante(estu3);
		gruposReportes.add(grupo1);
		gruposReportes.add(grupo2);
		
		DatosAuto.definirTablaGrupo(gruposReportes);
		
		//PLANES DOCENTES
		planesDocentes = new ArrayList<PlanificacionDocente>();
		//PlanificacionDocente plan1 = new PlanificacionDocente(profe1,asignatura1,grupo1);
		//PlanificacionDocente plan2 = new PlanificacionDocente(profe2,asignatura2,grupo2);
		//planesDocentes.add(plan1);
		//planesDocentes.add(plan2);
		
		DatosAuto.definirTablaPlanDocente(planesDocentes);
		
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
