package logica;

import java.util.ArrayList;

public class PlanEstudio {
	private ArrayList<Asignatura> asignaturas;

	// CONSTRUCTOR
	public PlanEstudio() {
		asignaturas = new ArrayList<>();
	}

	
	// gets y sets
	public ArrayList<Asignatura> getAsignaturas() {
		return asignaturas;
	}

	
	public void setAsignaturas(ArrayList<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}
	
	//crear asignatura
	public void crearAsignatura(String nombre, int annoAcademico, int semestre, float horasClases){
		Asignatura asignatura = new Asignatura(nombre, annoAcademico, semestre, horasClases);
		asignaturas.add(asignatura);
	}
	
	// busca la Asignatura
	public Asignatura buscarAsignatura(String nombreAsig){
		Asignatura asignatura= null;
		int i=0;
		
		while(i<asignaturas.size() && asignatura==null){
			if(asignaturas.get(i).getNombre().equalsIgnoreCase(nombreAsig))
				asignatura=asignaturas.get(i);
			i++;
		}
		
		return asignatura;
	}
	
	

}
