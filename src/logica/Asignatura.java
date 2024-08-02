package logica;

public class Asignatura {

	private String nombre;
	private int anno;
	private int semestre;
	private int horas;
	
	public Asignatura(String nombre, int anno, int semestre, int horas) {
		setNombre(nombre);
		setAnno(anno);
		setSemestre(semestre);
		setHoras(horas);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}
	
	
}
