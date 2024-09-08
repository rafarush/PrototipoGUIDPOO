package logica.Clases;

public class ControlDocente {

	// ATRIBUTO
	private String iDEstudiante;
	private Asignatura asignatura;
	private float nota1;
	private float nota2;
	
	// CONSTRUCTOR
	public ControlDocente(Estudiante estudiante, Asignatura asignatura){
		setiDEstudiante(estudiante.getID());
		setAsignatura(asignatura);
		nota1=0;
		nota2=0;
		//setNota1(nota1);
		//setNota2(nota2);
	}
	
	

	
	// SETS Y GETS
	public String getiDEstudiante() {
		return iDEstudiante;
	}

	public void setiDEstudiante(String iDEstudiante) {
		this.iDEstudiante = iDEstudiante;
	}

	
	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	
	public float getNota1() {
		return nota1;
	}

	public void setNota1(float nota1) {
		this.nota1 = nota1;
	}

	
	public float getNota2() {
		return nota2;
	}

	public void setNota2(float nota2) {
		this.nota2 = nota2;
	}
	
	
	
	//  OTROS
	
	// PARA DAR LAS NOTAS DE LOS ESTUDIANTES
	public void insertarNotas(float nota1, float nota2){
		setNota1(nota1);
		setNota2(nota2);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
