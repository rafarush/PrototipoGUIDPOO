package logica;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Grupo {
	private String nombre;
	private int anno;
	private ArrayList<Estudiante> estudiantes;
	
	public Grupo(String nombre, int anno) {
		setNombre(nombre);
		setAnno(anno);
		estudiantes = new ArrayList<Estudiante>();
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
	public ArrayList<Estudiante> getEstudiantes() {
		return estudiantes;
	}
	public void addEstudiante(Estudiante estu){
		estudiantes.add(estu);
	}
	
	
	
}
