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
				}else if(notas.get(i).getNota1()<3 && notas.get(i).getNota2()==0 && notas.get(i).getAsignatura().getSemestre()==1)
					val=false;
				
				i++;
			}
			
			return val;
		}
		
		// para verificar el CASO ESPECIAL de los arrastres q es qu esten en 2do arrastrando algo d primero y aprueben 2do y no 1ro
		public boolean  verificarArrastreEspecial() {
			boolean val = true;
			
			for(int i = 0; i<verificarNotasSuspensas().size() && val ; i++){
				if((verificarNotasSuspensas().get(i).getNota1()<3 && verificarNotasSuspensas().get(i).getNota2()<3) && verificarNotasSuspensas().get(i).getAsignatura().getAnnoAcademico()!=annoAcademico)
					val=false; 
			}
			
			return val;
		}
		
		
		//para verificar si le hace falta un grupo
		public boolean verificarNecesidadGrupo(){
			boolean val = true;
			
			for(ControlDocente cD : verificarNotasSuspensas()){
				if(cD.getAsignatura().getAnnoAcademico()==annoAcademico && cD.getNota1()!=0 && cD.getNota2()!=0)
					val = false;
					
					
					
			}
			
			return val;
		}
		
		
		// DEVUELVE   LAs ASIGNATURAS SUSPENSAS
		public ArrayList<ControlDocente> verificarNotasSuspensas(){
			ArrayList<ControlDocente> suspensas = new ArrayList<>();
			
			for(ControlDocente i : notas){
				if(i.getNota1()<3 && i.getNota2()<3)
					suspensas.add(i);
			}
			
			return suspensas;
		}
		
		
		
		//DEVUELVE   LAs ASIGNATURAS SUSPENSAS
		public ArrayList<ControlDocente> verificarNotasSuspensas1erSemestre(){
			ArrayList<ControlDocente> suspensas1 = new ArrayList<>();
			
			for(ControlDocente i : notas){
				if(i.getNota1()<3 && i.getNota2()<3 && i.getAsignatura().getSemestre()==1)
					suspensas1.add(i);
			}
			
			return suspensas1;
		}
		
		
		
		//DEVUELVE   LAs ASIGNATURAS SUSPENSAS
		public ArrayList<ControlDocente> verificarNotasSuspensas2doSemestre(){
			ArrayList<ControlDocente> suspensas2 = new ArrayList<>();
			
			for(ControlDocente i : notas){
				if(i.getNota1()<3 && i.getNota2()<3 && i.getAsignatura().getSemestre()==2)
					suspensas2.add(i);
			}
			
			return suspensas2;
		}
		
		
		
		//para busacr las asignaturas q esta arrastrando
		public ArrayList<Asignatura> buscarAsignaturasArrastres(){
			ArrayList<Asignatura> asignaturas = new ArrayList<>();
			
			if(verificarNotasSuspensas1erSemestre().size()==1){
				asignaturas.add(verificarNotasSuspensas1erSemestre().get(0).getAsignatura());
			}
			if(verificarNotasSuspensas2doSemestre().size()==1){
				asignaturas.add(verificarNotasSuspensas2doSemestre().get(0).getAsignatura());
			}
			
			
			return asignaturas;
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




