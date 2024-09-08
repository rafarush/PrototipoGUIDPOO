package logica.Clases;

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
		
		public boolean crearPlanificacionDocente(Profesor profesor, Asignatura asignatura, Grupo grupo){
			boolean val = false;
			if(!verificarPlanificacionDocente(grupo, asignatura)){
				PlanificacionDocente planificacionDocente =  new PlanificacionDocente(profesor, asignatura, grupo);
				planificacionesDocentes.add(planificacionDocente);
				profesor.crearControlDocente(grupo, asignatura);
				val = true;
			}
			
			return val;
		}

		// PARA VERIFICAR SI UNA PLANFICACION DOCENTE YA ESTA CREADA
		public boolean verificarPlanificacionDocente(Grupo grupo, Asignatura asignatura) {
			boolean val = false;
			int i = 0;
			while(i<planificacionesDocentes.size() && !val){
				if(planificacionesDocentes.get(i).getAsignatura().equals(asignatura) && planificacionesDocentes.get(i).getGrupo().getNombreGrupo().equalsIgnoreCase(grupo.getNombreGrupo()))
					val = true;
				i++;
			}
			
			return val;
		}
}











