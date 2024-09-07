package logica;

import java.util.ArrayList;

public class Grupo {

	// atributos
	private String nombreGrupo;
	private int annoAcademico;
	private ArrayList<Estudiante> grupoEstudiantes;

	public Grupo(String nombreGrupo , int annoAcademico) {
		setNombreGrupo(nombreGrupo);
		setAnnoAcademico(annoAcademico);
		this.grupoEstudiantes = new ArrayList<>();
	}

	
	// gets y sets
	
	public int getAnnoAcademico() {
		return annoAcademico;
	}


	public void setAnnoAcademico(int annosAcademico) {
		this.annoAcademico = annosAcademico;
	}
	
	
	
	public String getNombreGrupo() {
		return nombreGrupo;
	}


	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}
	
	
	public ArrayList<Estudiante> getGrupoEstudiantes() {
		return grupoEstudiantes;
	}
	
	
	public void setGrupoEstudiantes(ArrayList<Estudiante> grupoEstudiantes) {
		this.grupoEstudiantes = grupoEstudiantes;
	}

//
	public void insertarAGrupoEstudiante(Estudiante estudiante) {
		
		grupoEstudiantes.add(estudiante);
	}
	
	
	//
	public Estudiante buscarEstudiante(String iD){
		Estudiante estudiante = null;
		boolean val = true;
		int i = 0;
		
		while(i<grupoEstudiantes.size() && val){
			if(grupoEstudiantes.get(i).getID().equalsIgnoreCase(iD)){
				val=false;
				estudiante= grupoEstudiantes.get(i);
			}
			
			i++;
		}
		
		return estudiante;
	}
	
	//para eliminar los estudiantes
	public boolean eliminarEstudiante(Estudiante estudiante){
		boolean val;
		val = grupoEstudiantes.remove(estudiante);
	
		return val;
	}
	
	
	
	
	
}
