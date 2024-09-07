package logica;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import runner.Runner;
import gui.utils.ModeloPlanificacionDocenteEdit;


public class DatosAuto {
	
	/**
	 * Carga los datos desde el main
	 */
	
	/*****************************Define modelo de la tabla Profesores y agrega las filas***************************************/
	
	/**
	 * Convertir instancia de Profesor a un array de Objeto
	 * @return obj
	 */
	public static Object[] convertirAObjetoProfe(Profesor profe){
		Object[] profeObj = new Object[]{profe.getID(), profe.getNombre(), profe.getCategoriaCientifica(), profe.getCategoriaDocente(), 
				profe.getCentroLaboral(), profe.getOrganismo(), profe.getDireccion(), profe.calcularSalario()};
		return profeObj;
	}
	
	/**
	 * Imprime cada fila con los profesores
	 * @param profesores
	 */
	public static void agregarFilasProfes(ArrayList<Profesor> profesores){
		if(profesores.isEmpty()){
			JOptionPane.showMessageDialog(null, "No hay registro de profesores. Por favor, añada.");
		}else{
			for(Profesor p : profesores){
				Runner.modeloProfesor.addRow(convertirAObjetoProfe(p));
			}
		}
	}
	
	/**
	 * Define el modelo de columnas de la tabla profesor
	 * @param profesores
	 */
	public static void definirTablaProfes(ArrayList<Profesor> profesores){
	    Runner.modeloProfesor = new DefaultTableModel();
		Runner.modeloProfesor.addColumn("CI");
		Runner.modeloProfesor.addColumn("Nombre");
		Runner.modeloProfesor.addColumn("Categoría Científica");
		Runner.modeloProfesor.addColumn("Categoría Docente");
		Runner.modeloProfesor.addColumn("Centro Laboral");
		Runner.modeloProfesor.addColumn("Organismo");
		Runner.modeloProfesor.addColumn("Dirección");
		Runner.modeloProfesor.addColumn("Salario");
		agregarFilasProfes(profesores);
	}
	
	
/***************Define modelo de la tabla Profesores Consejo Direcc y agrega las filas************************/
	
	/**
	 * Convertir instancia de Profesor a un array de Objeto
	 * @return obj
	 */
	public static Object[] convertirAObjetoProfeConsejo(Profesor profe){
		Object[] profeObj = new Object[]{profe.getID(), profe.getNombre(), profe.getCategoriaCientifica(), profe.getCategoriaDocente(), 
				profe.getCargoConsejoDireccion()};
		return profeObj;
	}
	
	/**
	 * Imprime cada fila con los profesores
	 * @param profesores
	 */
	public static void agregarFilasProfesConsejo(ArrayList<Profesor> profesores){
		if(profesores.isEmpty()){
			JOptionPane.showMessageDialog(null, "No hay registro de profesores. Por favor, añada.");
		}else{
			for(Profesor p : profesores){
				Runner.modeloProfesorConsejo.addRow(convertirAObjetoProfeConsejo(p));
			}
		}
	}
	
	/**
	 * Define el modelo de columnas de la tabla profesor
	 * @param profesores
	 */
	public static void definirTablaProfesConsejo(ArrayList<Profesor> profesores){
	    Runner.modeloProfesorConsejo = new DefaultTableModel();
		Runner.modeloProfesorConsejo.addColumn("CI");
		Runner.modeloProfesorConsejo.addColumn("Nombre");
		Runner.modeloProfesorConsejo.addColumn("Categoría Científica");
		Runner.modeloProfesorConsejo.addColumn("Categoría Docente");
		Runner.modeloProfesorConsejo.addColumn("Cargo");
		agregarFilasProfesConsejo(profesores);
	}
	
	
	
	/***********************Define modelo de la tabla Estudiantes y agrega las filas**************************/
	
	/**
	 * Define el modelo de columnas de la tabla Estudiantes
	 * @param estudiantes
	 */
	public static void definirTablaEstudiantes(ArrayList<Estudiante> estudiantes){
		Runner.modeloEstudiante = new DefaultTableModel();
		Runner.modeloEstudiante.addColumn("CI");
		Runner.modeloEstudiante.addColumn("Nombre");
		Runner.modeloEstudiante.addColumn("Año");
		Runner.modeloEstudiante.addColumn("Centro Laboral");
		Runner.modeloEstudiante.addColumn("Organismo");
		Runner.modeloEstudiante.addColumn("Dirección");
		agregarFilasEstudiantes(estudiantes);
	}
	
	
	/**
	 * Imprime cada fila con los estudiantes
	 * @param estudiantes
	 */
	public static void agregarFilasEstudiantes(ArrayList<Estudiante> estudiantes){
		if(estudiantes.isEmpty()){
			JOptionPane.showMessageDialog(null, "No hay registro de estudiantes. Por favor, añada.");
		}else{
			for(Estudiante e : estudiantes){
				Runner.modeloEstudiante.addRow(convertirAObjetoEstudiante(e));
			}
		}
	}
	
	/**
	 * Convertir instancia de Estudiante a un array de Objeto
	 * @return obj
	 */
	public static Object[] convertirAObjetoEstudiante(Estudiante estu){
		Object[] estuObj = new Object[]{estu.getID(), estu.getNombre(), estu.getAnnoAcademico(), 
				estu.getCentroLaboral(), estu.getOrganismo(), estu.getDireccion()};	
		return estuObj;
	}
	
/***********************Define modelo de la tabla EstudianteNotas y agrega las filas**************************/
	
	/**
	 * Define el modelo de columnas de la tabla EstudiantesNotas
	 * @param estudiantes, asignatura
	 */
	public static void definirTablaEstudiantesNotas(ArrayList<Estudiante> estudiantes, Asignatura asignatura){
		Runner.modeloEstudianteCorto = new DefaultTableModel();
		Runner.modeloEstudianteCorto.addColumn("CI");
		Runner.modeloEstudianteCorto.addColumn("Nombre");
		Runner.modeloEstudianteCorto.addColumn("Año");
		Runner.modeloEstudianteCorto.addColumn("Nota 1");
		Runner.modeloEstudianteCorto.addColumn("Nota 2");
		agregarFilasEstudiantesNotas(estudiantes, asignatura);
	}
	
	
	/**
	 * Imprime cada fila con los estudianteCorto
	 * @param estudiantes
	 */
	public static void agregarFilasEstudiantesNotas(ArrayList<Estudiante> estudiantes, Asignatura asignatura){
		if(estudiantes.isEmpty()){
			JOptionPane.showMessageDialog(null, "No hay registro de estudiantes. Por favor, añada.");
		}else{
			for(Estudiante e : estudiantes){
				Runner.modeloEstudianteCorto.addRow(convertirAObjetoEstudianteNotas(e, asignatura));
			}
		}
	}
	
	/**
	 * Convertir instancia de EstudianteCorto a un array de Objeto
	 * @return obj
	 */
	public static Object[] convertirAObjetoEstudianteNotas(Estudiante estu, Asignatura asignatura){
		Object[] estuObj = new Object[]{estu.getID(), estu.getNombre(), estu.getAnnoAcademico(),
				estu.buscarNotaEspecifica(asignatura).get(0),estu.buscarNotaEspecifica(asignatura).get(1)};	
		if(estu.buscarNotaEspecifica(asignatura).get(0).equalsIgnoreCase("0.0"))
			estuObj[3] = "Sin nota";
		if(estu.buscarNotaEspecifica(asignatura).get(0).equalsIgnoreCase("0.0"))
			estuObj[4] = "Sin nota";
		
		return estuObj;
	}
		
	
/***********************Define modelo de la tabla EstudianteCorto y agrega las filas**************************/
	
	/**
	 * Define el modelo de columnas de la tabla EstudiantesCorto
	 * @param estudiantes
	 */
	public static void definirTablaEstudiantesCorto(ArrayList<Estudiante> estudiantes){
		Runner.modeloEstudianteCorto = new DefaultTableModel();
		Runner.modeloEstudianteCorto.addColumn("CI");
		Runner.modeloEstudianteCorto.addColumn("Nombre");
		Runner.modeloEstudianteCorto.addColumn("Año");
		agregarFilasEstudiantesCorto(estudiantes);
	}
	
	
	/**
	 * Imprime cada fila con los estudianteCorto
	 * @param estudiantes
	 */
	public static void agregarFilasEstudiantesCorto(ArrayList<Estudiante> estudiantes){
		if(estudiantes.isEmpty()){
			JOptionPane.showMessageDialog(null, "No hay registro de estudiantes. Por favor, añada.");
		}else{
			for(Estudiante e : estudiantes){
				Runner.modeloEstudianteCorto.addRow(convertirAObjetoEstudianteCorto(e));
			}
		}
	}
	
	/**
	 * Convertir instancia de EstudianteCorto a un array de Objeto
	 * @return obj
	 */
	public static Object[] convertirAObjetoEstudianteCorto(Estudiante estu){
		Object[] estuObj = new Object[]{estu.getID(), estu.getNombre(), estu.getAnnoAcademico()};	
		
		return estuObj;
	}
	
	
/*****************************Define modelo de la tabla PersonalAux y agrega las filas**************************************/
	
	/**
	 * Define el modelo de columnas de la tabla PersonalAux
	 * @param personalAux
	 */
	public static void definirTablaPersonalAux(ArrayList<PersonalApoyo> personalAux){
		Runner.modeloPersonalAux = new DefaultTableModel();
		Runner.modeloPersonalAux.addColumn("CI");
		Runner.modeloPersonalAux.addColumn("Nombre");
		Runner.modeloPersonalAux.addColumn("Área de Trabajo");
		Runner.modeloPersonalAux.addColumn("Dirección");
		Runner.modeloPersonalAux.addColumn("Salario");
		agregarFilasPersonalAux(personalAux);
	}
	
	
	/**
	 * Imprime cada fila con el PersonalAux
	 * @param personalAux
	 */
	public static void agregarFilasPersonalAux(ArrayList<PersonalApoyo> personalAux){
		if(personalAux.isEmpty()){
			JOptionPane.showMessageDialog(null, "No hay registro de personal auxiliar. Por favor, añada.");
		}else{
			for(PersonalApoyo pe : personalAux){
				Runner.modeloPersonalAux.addRow(convertirAObjetoPersonalAux(pe));
			}
		}
	}
	
	/**
	 * Convertir instancia de PersonalAux a un array de Objeto
	 * @return obj
	 */
	public static Object[] convertirAObjetoPersonalAux(PersonalApoyo persoAux){
		Object[] persoAuxObj = new Object[]{persoAux.getID(), persoAux.getNombre(), persoAux.getLabor(), 
				persoAux.getDireccion(), persoAux.calcularSalario()};	
		
		return persoAuxObj;
	}
	
	
/*****************************Define modelo de la tabla PlanDeEstudio y agrega las filas**************************************/
	
	/**
	 * Define el modelo de columnas de la tabla PlanDeEstudio
	 * @param asignaturas
	 */
	public static void definirTablaPlanDeEstudio(ArrayList<Asignatura> asignaturas){
		Runner.modeloPlanDeEstudio = new DefaultTableModel();
		Runner.modeloPlanDeEstudio.addColumn("Asignatura");
		Runner.modeloPlanDeEstudio.addColumn("Año Académico");
		Runner.modeloPlanDeEstudio.addColumn("Semestre");
		Runner.modeloPlanDeEstudio.addColumn("Horas Lectivas");;
		agregarFilasPlanDeEstudio(asignaturas);
	}
	
	
	/**
	 * Imprime cada fila con la Asignatura
	 * @param asignaturas
	 */
	public static void agregarFilasPlanDeEstudio(ArrayList<Asignatura> asignaturas){
		if(asignaturas.isEmpty()){
			JOptionPane.showMessageDialog(null, "No hay registro de Plan de Estudio. Por favor, añada.");
		}else{
			for(Asignatura a : asignaturas){
				Runner.modeloPlanDeEstudio.addRow(convertirAObjetoAsignatura(a));
			}
		}
	}
	
	/**
	 * Convertir instancia de Asignatura a un array de Objeto
	 * @return obj
	 */
	public static Object[] convertirAObjetoAsignatura(Asignatura asignatura){
		Object[] asignaturaObj = new Object[]{asignatura.getNombre(), asignatura.getAnnoAcademico(), asignatura.getSemestre(), 
				asignatura.getHorasClases()};	
	
		return asignaturaObj;
	}
	
	
/*********************Define modelo de la tabla de Reportes para Estudiantes y agrega las filas*************************/
	
	/**
	 * Define el modelo de columnas de la tabla Reportes para Estudiantes
	 * @param estudiantes
	 */
	public static void definirTablaReportesEstu(ArrayList<Estudiante> estudiantesReporte){
		Runner.modeloEstudianteReporte = new DefaultTableModel();
		Runner.modeloEstudianteReporte.addColumn("Carné de Identidad");
		Runner.modeloEstudianteReporte.addColumn("Nombre");
		Runner.modeloEstudianteReporte.addColumn("Año");
		agregarFilasReportesEstu(estudiantesReporte);
	}
	
	
	/**
	 * Imprime cada fila con el estudiante
	 * @param estudiantes
	 */
	public static void agregarFilasReportesEstu(ArrayList<Estudiante> estudiantesReporte){
		if(estudiantesReporte.isEmpty()){
			JOptionPane.showMessageDialog(null, "No hay registro de Estudiantes para este Reporte");
		}else{
			for(Estudiante e : estudiantesReporte){
				Runner.modeloEstudianteReporte.addRow(convertirAObjetoEstudiante(e));
			}
		}
	}
	
	/**
	 * Convertir instancia de Estudiante a un array de Objeto
	 * @return obj
	 */
	public static Object[] convertirAObjetoAsignatura(Estudiante estu){
		Object[] estudianteObj = new Object[]{estu.getID(), estu.getNombre(), estu.getAnnoAcademico()};	
		return estudianteObj;
	}
	
	
/*****************************Define modelo de la tabla Grupo y agrega las filas**************************************/
	
	/**
	 * Define el modelo de columnas de la tabla Grupos
	 * @param grupos
	 */
	public static void definirTablaGrupo(ArrayList<Grupo> grupos){
		Runner.modeloGrupoReporte = new DefaultTableModel();
		Runner.modeloGrupoReporte.addColumn("Nombre");
		Runner.modeloGrupoReporte.addColumn("Año");
		Runner.modeloGrupoReporte.addColumn("Matrícula");
		agregarFilasGrupo(grupos);
	}
	
	
	/**
	 * Imprime cada fila con el Grupo
	 * @param grupo
	 */
	public static void agregarFilasGrupo(ArrayList<Grupo> grupos){
		if(grupos.isEmpty()){
			JOptionPane.showMessageDialog(null, "No hay registro de grupos. Por favor, añada.");
		}else{
			for(Grupo g : grupos){
				Runner.modeloGrupoReporte.addRow(convertirAObjetoGrupo(g));
			}
		}
	}
	
	/**
	 * Convertir instancia de Grupo a un array de Objeto
	 * @return obj
	 */
	public static Object[] convertirAObjetoGrupo(Grupo grupo){
		Object[] grupoObj = new Object[]{grupo.getNombreGrupo(), grupo.getAnnoAcademico(), grupo.getGrupoEstudiantes().size()};		
		return grupoObj;
	}
	
	
/*****************************Define modelo de la tabla Plan Docente y agrega las filas**************************************/
	
	/**
	 * Define el modelo de columnas de la tabla Plan Docente
	 * @param planesDocentes
	 */
	public static void definirTablaPlanDocente(ArrayList<PlanificacionDocente> planesDocentes){
		Runner.modeloPlanDocente = new DefaultTableModel();
		Runner.modeloPlanDocente.addColumn("Profesor");
		Runner.modeloPlanDocente.addColumn("Asignatura");
		Runner.modeloPlanDocente.addColumn("Grupo");
		agregarFilasPlanesDocentes(planesDocentes);
	}
	
	
	/**
	 * Imprime cada fila con el planDocente
	 * @param planesDocentes
	 */
	public static void agregarFilasPlanesDocentes(ArrayList<PlanificacionDocente> planesDocentes){
		
		if(planesDocentes.isEmpty()){
			JOptionPane.showMessageDialog(null, "No hay registro de planificaciones docentes. Por favor, añada.");
		}else{
			for(PlanificacionDocente p : planesDocentes){
				Runner.modeloPlanDocente.addRow(convertirAObjetoPlanDocente(p));
			}
		}
	}
	
	/**
	 * Convertir instancia de PlanDocente a un array de Objeto
	 * @return obj
	 */
	public static Object[] convertirAObjetoPlanDocente(PlanificacionDocente planDocente){
		Object[] planDocenteObj = new Object[]{planDocente.getProfesor().getNombre(),planDocente.getAsignatura().getNombre(), 
				planDocente.getGrupo().getNombreGrupo()};		
		return planDocenteObj;
	}
	
	//Para usar tabla con objetos
	public static void llenarTablaPlanificacionDocente(ArrayList<PlanificacionDocente> planesDocentes){
		Runner.modeloPlanDocente1 = new ModeloPlanificacionDocenteEdit(planesDocentes);
	}
	
	
	/****************************************Define los modelos de tabla***********************************************/
	/*
	public static void inicializar(){
		
		//Defino modeloProfesor de la tabla
		Runner.modeloProfesor = new DefaultTableModel();
		Runner.modeloProfesor.addColumn("CI");
		Runner.modeloProfesor.addColumn("Nombre");
		Runner.modeloProfesor.addColumn("Categoría Científica");
		Runner.modeloProfesor.addColumn("Categoría Docente");
		Runner.modeloProfesor.addColumn("Centro Laboral");
		Runner.modeloProfesor.addColumn("Organismo");
		Runner.modeloProfesor.addColumn("Dirección");
		Runner.modeloProfesor.addColumn("Salario");
		//Runner.modeloProfesor.addRow(convertirAObjetoProfe(Runner.profe1));
		//Runner.modeloProfesor.addRow(new Object[]{Runner.profe1.getCi(),Runner.profe1.getNombre(),Runner.profe1.getCategoCientifica(),Runner.profe1.getCategoDocente(),Runner.profe1.getCentroLaboral(),Runner.profe1.getOrganismo(),Runner.profe1.getDirecc(),Runner.profe1.getSalario()});
		//Runner.modeloProfesor.addRow(new Object[]{"05062347564", "Manuel Castro Reyes","Máster","Profesor Titular","Cujae","MINES", "Calle 30 entre 34 y Ave. 56",null});
    
		
		//Defino modeloEstudiante de la tabla
		Runner.modeloEstudiante = new DefaultTableModel();
		Runner.modeloEstudiante.addColumn("CI");
		Runner.modeloEstudiante.addColumn("Nombre");
		Runner.modeloEstudiante.addColumn("Año");
		Runner.modeloEstudiante.addColumn("Grupo");
		Runner.modeloEstudiante.addColumn("Centro Laboral");
		Runner.modeloEstudiante.addColumn("Organismo");
		Runner.modeloEstudiante.addColumn("Dirección");
		Runner.modeloEstudiante.addRow(new Object[]{"05032379581", "Rafael Menéndez Rodriguez","1",null,"Sucursal Comercial #5","Etecsa", "Calle 30 entre 34 y Ave. 56"});
		Runner.modeloEstudiante.addRow(new Object[]{"08868513264", "Alejandro González Fernández","1",null,"La Mariposa","TRD","Ave. 26 entre calles A y B"});
		
		//Defino modeloPersonalAux de la tabla
		Runner.modeloPersonalAux = new DefaultTableModel();
		Runner.modeloPersonalAux.addColumn("CI");
		Runner.modeloPersonalAux.addColumn("Nombre");
		Runner.modeloPersonalAux.addColumn("Área de Trabajo");
		Runner.modeloPersonalAux.addColumn("Dirección");
		Runner.modeloPersonalAux.addColumn("Salario");
		Runner.modeloPersonalAux.addRow(new Object[]{"09062235147", "Federico Criado Domínguez","Laboratorio", "Calle 30 entre 34 y Ave. 56",null});
		Runner.modeloPersonalAux.addRow(new Object[]{"59868285496", "Maria Elena Gómez Pérez","Biblioteca", "Ave. 26 entre calles A y B",null});
		
		//Defino modeloPlanDeEstudio de la tabla
		Runner.modeloPlanDeEstudio = new DefaultTableModel();
		Runner.modeloPlanDeEstudio.addColumn("Asignatura");
		Runner.modeloPlanDeEstudio.addColumn("Año Académico");
		Runner.modeloPlanDeEstudio.addColumn("Semestre");
		Runner.modeloPlanDeEstudio.addColumn("Horas Lectivas");
		Runner.modeloPlanDeEstudio.addRow(new Object[]{"Matemática I", "1","1", "50"});
		Runner.modeloPlanDeEstudio.addRow(new Object[]{"Matemática II", "1","2", "50"});
		Runner.modeloPlanDeEstudio.addRow(new Object[]{"Introducción a la Programación", "1","1", "60"});
		Runner.modeloPlanDeEstudio.addRow(new Object[]{"Diseño y POO", "1","2", "90"});
		Runner.modeloPlanDeEstudio.addRow(new Object[]{"Estructuras de Datos", "2","1", "40"});
		Runner.modeloPlanDeEstudio.addRow(new Object[]{"Seguridad Nacional", "2","1", "90"});
	}	*/
	/****************************************************************************************************/

}
