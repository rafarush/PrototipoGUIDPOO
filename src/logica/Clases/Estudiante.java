package logica.Clases;

import java.util.ArrayList;

public class Estudiante extends Persona{
	
	//atributos
	private int annoAcademico;
	private String centroLaboral;
	private String organismo;
	private ArrayList<ControlDocente> notas;
	

	// ONSTRUCTOR
	public Estudiante(String iD, String nombre, int annoAcademico, String centroLaboral, String organismo, String Direccion) {
		super(nombre, iD, Direccion);
		// TODO Auto-generated constructor stub
		 setAnnoAcademico(annoAcademico);
		 setCentroLaboral(centroLaboral);
		 setOrganismo(organismo);
		
		 notas = new ArrayList<ControlDocente>();
	}



	//gets y sets

	public int getAnnoAcademico() {
		return annoAcademico;
	}

	public void setAnnoAcademico(int annoAcademico) {
		if(annoAcademico>0 && annoAcademico<8)
			this.annoAcademico = annoAcademico;
		else 
			throw new IllegalArgumentException("Solo existen annos entre 1 y 6");
		
	}


	public String getCentroLaboral() {
		return centroLaboral;
	}

	public void setCentroLaboral(String centroLaboral) {
		this.centroLaboral = centroLaboral;
	}




	public String getOrganismo() {
		return organismo;
	}

	public void setOrganismo(String organismo) {
		this.organismo = organismo;
	}



	public ArrayList<ControlDocente> getNotas() {
		return notas;
	}

	public void setNotas(ArrayList<ControlDocente> notas) {
		this.notas = notas;
	}
	
	
	// **********************************  OTROS   *********************************
	
	public void insertarControlDocente(ControlDocente controlDocente){
		
		notas.add(controlDocente);
		
	}
	
	
	// insertar notas
	public void insertarNotas( Asignatura asignatura, float nota1, float nota2){
		buscarControlDocente(asignatura).setNota1(nota1);
		buscarControlDocente(asignatura).setNota2(nota2);
	}
	
	
	// BUSCAR CONTROL DOCENTE
	public ControlDocente buscarControlDocente(Asignatura asignatura){
		boolean val = true;
		int i = 0;
		ControlDocente controlDocente = null;
		
		while(i<notas.size() && val){
			if(notas.get(i).getAsignatura().equals(asignatura)){
				controlDocente= notas.get(i);
				val=false;
			}
							
			i++;
		}
		if(val)
			i=0;
		
		return controlDocente;
	}
	
	
	// DEVUELVE TRUE O FALSE SEGUN TENGA O NO TODAS LAS NOTAS
		public boolean verificarNotas(){
			boolean val = true;
			int i = 0;
			
			while(i<notas.size() && val){
				if(notas.get(i).getNota1()==0){
					val= false;
				}else{
					if(notas.get(i).getNota1()<3 && notas.get(i).getNota2()==0)
						val=false;
				}
				
				i++;
			}
			
			return val;
		}
	
		
		//DEVUELVE TRUE O FALSE SEGUN TENGA O NO TODAS LAS NOTAS del primer semestre
		public boolean verificarNotasPrimerSemestre(){
			boolean val = true;
			int i = 0;
			
			while(i<notas.size() && val){
				if(notas.get(i).getNota1()==0 && notas.get(i).getAsignatura().getSemestre()==1){
					val= false;
				}else{
					if(notas.get(i).getNota1()<3 && notas.get(i).getNota2()==0 && notas.get(i).getAsignatura().getSemestre()==1)
						val=false;
				}
				
				i++;
			}
			
			return val;
		}
		
		
		
		
		// DEVUELVE UN ENTERO CON LA CANTIDAD DE ASIGNATURAS SUSPENSAS
		public ArrayList<ControlDocente> verificarNotasSuspensas(){
			ArrayList<ControlDocente> suspensas = new ArrayList<>();
			
			for(ControlDocente i : notas){
				if(i.getNota1()<3 && i.getNota2()<3)
					suspensas.add(i);
			}
			
			return suspensas;
		}
		
		// PARA CALCULAR EL PROMEDIO DE LAS NOTAS 
		public float calcularPromedio(){
			float promedio = 0;
			
			for(ControlDocente c : notas){
				float mayor;
				if(c.getNota1() < c.getNota2())
					mayor = c.getNota2();
				else
					mayor = c.getNota1();
				
				promedio+=mayor;
			}
			
			return promedio / notas.size();
		}
		
		// devuelve un booleano para saber si arrastra o no
		
		public boolean verificarArrastre(){
			boolean val = false;
			if(verificarNotasSuspensas().size()==2){
				if(verificarNotasSuspensas().get(0).getAsignatura().getSemestre()!=verificarNotasSuspensas().get(1).getAsignatura().getSemestre() && 
					verificarNotasSuspensas().get(0).getAsignatura().getAnnoAcademico()==annoAcademico-1 &&   verificarNotasSuspensas().get(1).getAsignatura().getAnnoAcademico()==annoAcademico-1  )
					val = true;
			}
			if(verificarNotasSuspensas().size()==1 && verificarNotasSuspensas().get(0).getAsignatura().getAnnoAcademico()==annoAcademico-1)
				val = true;
			
			return val;
		}
		
		
		// devuelve un booleano para saber si suspende o no
		
		public boolean verificarSuspenso(){
			boolean val = false;
			if(verificarNotasSuspensas().size()>2)
				val=true;
			else if(verificarNotasSuspensas().size()==2){
				if(verificarNotasSuspensas().get(0).getAsignatura().getSemestre()==verificarNotasSuspensas().get(1).getAsignatura().getSemestre())
					val = true;
				}
			else if(verificarNotasSuspensas().size()==1 && verificarNotasSuspensas().get(0).getAsignatura().getAnnoAcademico()<annoAcademico)
				val = true;
			
			return val;
		}
		
		// modificar Estudiantes
		public void modificarEstudiante(String iD, String nombre, int anoAcademico, String centroLaboral, String organismo, String direccion) {
			
			setNombre(nombre);
			
			setCentroLaboral(centroLaboral);
			
			setOrganismo(organismo);
			
			setDireccion(direccion);
		}
		
		
		//para buscar una nota especifica
		public ArrayList<String> buscarNotaEspecifica(Asignatura asignatura){
			ArrayList<String> notas = new ArrayList<>();
			
			notas.add(Float.toString(buscarControlDocente(asignatura).getNota1()));
			notas.add(Float.toString(buscarControlDocente(asignatura).getNota2()));
			
			return notas;
		} 
}




