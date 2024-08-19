package logica;

import java.util.ArrayList;

public class Periodo {
	
	// ATRIBUTOS
	private ArrayList<PlanificacionDocente> planificacionesDocentes;

	public Periodo() {
		planificacionesDocentes = new ArrayList<>();
	}
	
	// gets y sets
	
		public ArrayList<PlanificacionDocente> getPlanificacionesDocentes() {
			return planificacionesDocentes;
		}

		
		// NO CREO QUE HAGA FALTA EL SET YA Q HABRA UN CREAR A PLANIFICACION DOCENTE 
		public void setPlanificacionesDocentes(
				ArrayList<PlanificacionDocente> planificacionesDocentes) {
			this.planificacionesDocentes = planificacionesDocentes;
		}
		
		public void crearPlanificacionDocente(Profesor profesor, Asignatura asignatura, Grupo grupo){
			PlanificacionDocente planificacionDocente =  new PlanificacionDocente(profesor, asignatura, grupo);
			planificacionesDocentes.add(planificacionDocente);
			profesor.crearControlDocente(grupo, asignatura);
		}

}
