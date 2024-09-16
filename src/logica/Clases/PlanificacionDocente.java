package logica.Clases;

public class PlanificacionDocente {
	
	// ATRIBUTOS
	private Profesor profesor;
	private Asignatura asignatura;
	private Grupo grupo;
	
	
	// CONSTRUCTOR
	protected PlanificacionDocente(Profesor profesor, Asignatura asignatura,
			Grupo grupo) {
		setProfesor(profesor);
		setAsignatura(asignatura);
		setGrupo(grupo);
	}
	// gets y sets
		
	public Profesor getProfesor() {
		return profesor;
	}
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	
	
	
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	
	
	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}
	
	
	
	
	
	
	
	
	
	

}
