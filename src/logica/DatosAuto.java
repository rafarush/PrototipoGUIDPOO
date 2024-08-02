package logica;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;









import runner.Runner;
import gui.*;
import gui.mainFrame.MainFrame;


/*
 * 
 * 
 * 
 * 
 * 
 * 
 * TERMINAR EL DISEÑO DE LA INICIALIZACION AUTOMÁTICA DE LOS DATOS CON LAS CLASES RESTANTES
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */

public class DatosAuto {
	
	
	/*****************************Define modelo de la tabla Profesores y agrega las filas***************************************/
	
	/**
	 * Convertir instancia de Profesor a un array de Objeto
	 * @return obj
	 */
	public static Object[] convertirAObjetoProfe(Profesor profe){
		Object[] profeObj = new Object[]{profe.getCi(), profe.getNombre(), profe.getCategoCientifica(), profe.getCategoDocente(), 
				profe.getCentroLaboral(), profe.getOrganismo(), profe.getDirecc(), profe.getSalario()};	
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
	
	/*****************************Define modelo de la tabla Estudiantes y agrega las filas**************************************/
	
	/**
	 * Define el modelo de columnas de la tabla Estudiantes
	 * @param profesores
	 */
	public static void definirTablaEstudiantes(ArrayList<Estudiante> estudiantes){
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
		agregarFilasEstudiantes(estudiantes);
	}
	
	
	/**
	 * Imprime cada fila con los estudiantes
	 * @param profesores
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
		Object[] estuObj = new Object[]{estu.getCi(), estu.getNombre(), estu.getAnno(), estu.getGrupo(), 
				estu.getCentroLaboral(), estu.getOrganismo(), estu.getDirecc()};	
		return estuObj;
	}
	
	
	/****************************************Define los modelos de tabla***********************************************/
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
	}	
	/****************************************************************************************************/

}
