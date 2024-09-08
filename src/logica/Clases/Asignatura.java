package logica.Clases;

public class Asignatura {
	// ATRIBUTOS
	private String nombre;
	private int annoAcademico;
	private int semestre;
	private float horasClases;
	
	
	// CONSTRUCTOR
	public Asignatura(String nombre, int annoAcademico, int semestre, float horasClases) {
		setNombre(nombre);
		setAnnoAcademico(annoAcademico);
		setSemestre(semestre);
		setHorasClases(horasClases);
	}	
	
	// GETS Y SETS
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getAnnoAcademico() {
		return annoAcademico;
	}

	public void setAnnoAcademico(int annoAcademico) {
		if(annoAcademico<7 && annoAcademico>0)
			this.annoAcademico = annoAcademico;
		else
			throw new IllegalArgumentException("Ese anno academico no exite");
	}
	

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}


	public float getHorasClases() {
		return horasClases;
	}

	public void setHorasClases(float horasClases) {
		if(horasClases>0)
			this.horasClases = horasClases;
		else
			throw new IllegalArgumentException("La cantidad de horas clases tiene que ser mayor que 0");
	}

	//
	public int devolverPeriodo(){
		int periodo;
		
		if(semestre==2)
			periodo= annoAcademico+5;
		else
			periodo= annoAcademico-1; 
		
		return periodo;
	}
	
	// PARA MODIFICAR LA ASIGNATURA
	public void modificarAsignatura( int annoAcademico, int semestre, float horasClases) {
		
		setAnnoAcademico(annoAcademico);
		
		setSemestre(semestre);
		
		setHorasClases(horasClases);
	}
	
	
	
	
	
	
	
	
	
	

}
