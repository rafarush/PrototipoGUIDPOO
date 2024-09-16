package logica.Clases;

public class GrupoEspecial extends Grupo{

	Asignatura asignatura ;
	
	
	
	
	
	protected GrupoEspecial(String nombreGrupo, int annoAcademico , Asignatura asignatura) {
		super(nombreGrupo, annoAcademico);
		// TODO Auto-generated constructor stub
		setAsignatura(asignatura);
	}





	public Asignatura getAsignatura() {
		return asignatura;
	}


	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}


	
}
