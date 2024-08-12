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
	public static ArrayList<Profesor> profesores;
	public static ArrayList<Estudiante> estudiantes;
	public static ArrayList<PersonalApoyo> personalAux;
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
		
		//PROFESORES
		Profesor profe1 = new Profesor("95868426587", "Luis Pérez Fernández","Doctor","Instructor","CineSoft","InfoCuba", "Ave. 26 entre calles A y B");
		Profesor profe2 = new Profesor("05062348364", "Rafael Castro Reyes","Doctor","Titular","Cujae","MINES", "Calle 30 entre 34 y Ave. 56");
		Profesor profe3 = new Profesor("05022358174", "Jorge Castro Pérez","Máster","Asistente","Cujae","MINES", "Calle 25 entre 21 y Ave. 26");
		Profesor profe4 = new Profesor("05062347564", "Manuel Castro Reyes","Máster","Titular","Cujae","MINES", "Calle 30 entre 34 y Ave. 56");
		
	
		// JORGITOOOOOOO ---->>>>    la creacion de los profesores por defecto
		fct.crearPersona("95868426587", "Luis Pérez Fernández","Doctor","Instructor","CineSoft","InfoCuba", "Ave. 26 entre calles A y B");
		fct.crearPersona("05062348364", "Rafael Castro Reyes","Doctor","Titular","Cujae","MINES", "Calle 30 entre 34 y Ave. 56");
		fct.crearPersona("05022358174", "Jorge Castro Pérez","Máster","Asistente","Cujae","MINES", "Calle 25 entre 21 y Ave. 26");
		fct.crearPersona("05062347564", "Manuel Castro Reyes","Máster","Titular","Cujae","MINES", "Calle 30 entre 34 y Ave. 56");
		
		
		profesores = new ArrayList<Profesor>();
		profesores.add(profe1);
		profesores.add(profe2);
		profesores.add(profe3);
		profesores.add(profe4);
	
		DatosAuto.definirTablaProfes(profesores);
		
		//ESTUDIANTES
		Estudiante estu1 = new Estudiante("05032379581", "Rafael Menéndez Rodriguez",1,"Sucursal Comercial #5","Etecsa", "Calle 30 entre 34 y Ave. 56");
		Estudiante estu2 = new Estudiante("08868513264", "Alejandro González Fernández",1,"La Mariposa","TRD","Ave. 26 entre calles A y B");
		Estudiante estu3 = new Estudiante("04021324587", "Jorgito", 2, "Las Palamas", "CTC", "Tulipan y Boyeros");
			
		
		// JORGITOOOOOOO ---->>>>    la creacion de los estudiantes por defecto
		fct.crearPersona("05032379581", "Rafael Menéndez Rodriguez", 1 ,"Sucursal Comercial #5","Etecsa", "Calle 30 entre 34 y Ave. 56");
		fct.crearPersona("08868513264", "Alejandro González Fernández",1,"La Mariposa","TRD","Ave. 26 entre calles A y B");
		fct.crearPersona("04021324587", "Jorgito", 2, "Las Palamas", "CTC", "Tulipan y Boyeros");
		
		
		
		
		estudiantes = new ArrayList<Estudiante>();
		estudiantes.add(estu1);
		estudiantes.add(estu2);
		estudiantes.add(estu3);
		
		DatosAuto.definirTablaEstudiantes(estudiantes);
		
		
		//Personal Auxiliar
		PersonalApoyo persoAux1 = new PersonalApoyo("09062235147", "Federico Criado Domínguez","Laboratorio", "Calle 30 entre 34 y Ave. 56");
		PersonalApoyo persoAux2 = new PersonalApoyo("59868285496", "Maria Elena Gómez Pérez","Biblioteca", "Ave. 26 entre calles A y B");
					
		
		// JORGITOOOOOOO ---->>>>    la creacion del personal de apoyo por defecto
		fct.crearPersona("09062235147", "Federico Criado Domínguez","Laboratorio", "Calle 30 entre 34 y Ave. 56");
		fct.crearPersona("59868285496", "Maria Elena Gómez Pérez","Biblioteca", "Ave. 26 entre calles A y B");
		
		
		
		
		
		personalAux = new ArrayList<PersonalApoyo>();
		personalAux.add(persoAux1);
		personalAux.add(persoAux2);
							
		DatosAuto.definirTablaPersonalAux(personalAux);
					
		//Asignaturas
		Asignatura asignatura1 = new Asignatura("Matemática I", 1, 1, 50);
		Asignatura asignatura2 = new Asignatura("Matemática II", 1, 2, 50);
		Asignatura asignatura3 = new Asignatura("Introducción a la Programación", 1, 1, 60);
		Asignatura asignatura4 = new Asignatura("Diseño y POO", 1, 2, 90);
		Asignatura asignatura5 = new Asignatura("Estructuras de Datos", 2, 1, 40);
		Asignatura asignatura6 = new Asignatura("Seguridad Nacional", 2, 1, 90);
		
		
		
		// JORGITOOOOOOO ---->>>>    la creacion del personal de apoyo por defecto
		fct.getPlanEstudio().crearAsignatura("Matemática I", 1, 1, 50);
		fct.getPlanEstudio().crearAsignatura("Matemática II", 1, 2, 50);
		fct.getPlanEstudio().crearAsignatura("Introducción a la Programación", 1, 1, 60);
		fct.getPlanEstudio().crearAsignatura("Diseño y POO", 1, 2, 90);
		fct.getPlanEstudio().crearAsignatura("Estructuras de Datos", 2, 1, 40);
		fct.getPlanEstudio().crearAsignatura("Seguridad Nacional", 2, 1, 90);
		
		
					
		asignaturas = new ArrayList<Asignatura>();
		asignaturas.add(asignatura1);
		asignaturas.add(asignatura2);
		asignaturas.add(asignatura3);
		asignaturas.add(asignatura4);
		asignaturas.add(asignatura5);
		asignaturas.add(asignatura6);
							
		DatosAuto.definirTablaPlanDeEstudio(asignaturas);
		
		//ESTUDIANTE PARA REPORTES
		estudiantesReporte = new ArrayList<Estudiante>();
		estudiantesReporte.add(estu3);
		estudiantesReporte.add(estu1);
		
		DatosAuto.definirTablaReportesEstu(estudiantesReporte);

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
		PlanificacionDocente plan1 = new PlanificacionDocente(profe1,asignatura1,grupo1);
		PlanificacionDocente plan2 = new PlanificacionDocente(profe2,asignatura2,grupo2);
		planesDocentes.add(plan1);
		planesDocentes.add(plan2);
		
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
