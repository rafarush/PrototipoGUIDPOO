package logica;

public class PlanificacionDocente {

	private Asignatura asignatura;
	private Grupo grupo;
	private Profesor profe;
	
	public PlanificacionDocente(Profesor profe, Asignatura asignatura, Grupo grupo) {
		this.asignatura = asignatura;
		this.grupo = grupo;
		this.profe = profe;
	}
	public Asignatura getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	public Profesor getProfe() {
		return profe;
	}
	public void setProfe(Profesor profe) {
		this.profe = profe;
	}
	
}
