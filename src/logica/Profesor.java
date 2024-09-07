package logica;

import java.util.ArrayList;

public class Profesor extends Trabajador{


	//atributos
	private String categoriaDocente;
	private String categoriaCientifica;
	private String centroLaboral;
	private String organismo;
	private String cargoConsejoDireccion;
	private ArrayList<ControlDocente> notasAlumnos;
	

	// CONSTRUCOTR para los profesores sin cargo en el consejo de direccion
	public Profesor( String iD, String nombre, String categoriaCientifica, String categoriaDocente, String centroLaboral, String organismo, String Direccion) {
		super(nombre, iD, Direccion);
		// TODO Auto-generated constructor stub
		setCategoriaCientifica(categoriaCientifica);
		
		
		setCategoriaDocente(categoriaDocente);
		
		
		setCentroLaboral(centroLaboral);
		
		setOrganismo(organismo);
		
		this.cargoConsejoDireccion = null;
		
		notasAlumnos = new ArrayList<ControlDocente>();
		
	}

	// CONSTRUCOTR para los profesores <<CON>> cargo en el consejo de direccion
		public Profesor(String nombre, String iD, String Direccion, String categoriaDocente, String categoriaCientifica, String centroLaboral, String organismo, String cargoConsejoDireccion) {
			super(nombre, iD, Direccion);
			// TODO Auto-generated constructor stub
			setCategoriaDocente(categoriaDocente);
			
			setCategoriaCientifica(categoriaCientifica);
			
			setCentroLaboral(centroLaboral);
			
			setOrganismo(organismo);
			
			setCargoConsejoDireccion(cargoConsejoDireccion);
			
		}

	

	//sets y gets
	public String getCategoriaDocente() {
		return categoriaDocente;
	}

	public void setCategoriaDocente(String categoriaDocente) {
		
		if(categoriaDocente.equalsIgnoreCase("Instructor"))
			this.categoriaDocente = "Instructor";
		else if(categoriaDocente.equalsIgnoreCase("Asistente"))
			this.categoriaDocente = "Asistente";
		else if(categoriaDocente.equalsIgnoreCase("Auxiliar"))
			this.categoriaDocente = "Auxiliar";
		else if(categoriaDocente.equalsIgnoreCase("Titular"))
			this.categoriaDocente = "Titular";
		else
			throw new IllegalArgumentException("No existe esa categoria docente.");
		
	}

	
	public String getCategoriaCientifica() {
		return categoriaCientifica;
	}

	public void setCategoriaCientifica(String categoriaCientifica) {
		
		if(categoriaCientifica.equalsIgnoreCase("Máster"))
			this.categoriaCientifica = "Máster";
		else if(categoriaCientifica.equalsIgnoreCase("Doctor"))
			this.categoriaCientifica = "Doctor";
		else if(categoriaCientifica.equals("Ninguna"))
			this.categoriaCientifica = "Ninguna";
		else
			throw new IllegalArgumentException("No existe esa categoria cientifica.");
		
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
	
	
	
	public String getCargoConsejoDireccion() {
		return cargoConsejoDireccion;
	}

	public void setCargoConsejoDireccion(String cargoConsejoDireccion) {
		
		if(cargoConsejoDireccion==null)
			this.cargoConsejoDireccion=null;
	    else if(cargoConsejoDireccion.equalsIgnoreCase("Director"))
			this.cargoConsejoDireccion = "Director";
		else if(cargoConsejoDireccion.equalsIgnoreCase("Subdirector Docente"))
			this.cargoConsejoDireccion = "Subdirector Docente";
		else if(cargoConsejoDireccion.equalsIgnoreCase("Subdirector de Investigaciones y Posgrado"))
			this.cargoConsejoDireccion = "Subdirector de Investigaciones y Posgrado";
		else if(cargoConsejoDireccion.equalsIgnoreCase("Subdirector de Extensión Universitaria"))
			this.cargoConsejoDireccion = "Subdirector de Extensión Universitaria";
		else if(cargoConsejoDireccion.equals("Jefe de Laboratorios"))
			this.cargoConsejoDireccion = "Jefe de Laboratorios";
		
	}

	
	public ArrayList<ControlDocente> getNotasAlumnos() {
		return notasAlumnos;
	}

	// ESTE NO VA A SER NECESARIO
	public void setNotasAlumnos(ArrayList<ControlDocente> notasAlumnos) {
		this.notasAlumnos = notasAlumnos;
	}
	
	
	
	// OTROS
	
	//para calcular el salario en dependencia de sus categorias el base sera el del Instructor que sera de $100


	public float calcularSalario(){
		float salarioTotal = 0;
		
		if(categoriaDocente.equalsIgnoreCase("Asistente"))
			salarioTotal=salario+200;
		else if(categoriaDocente.equalsIgnoreCase("Auxiliar"))
			salarioTotal=salario+300;
		else if(categoriaDocente.equalsIgnoreCase("Titular"))
			salarioTotal=salario+540;
		
		if(categoriaCientifica.equalsIgnoreCase("Master"))
			salarioTotal=salario+380;
		else if(categoriaCientifica.equalsIgnoreCase("Doctor"))
			salarioTotal=salario+550;
		
		return salarioTotal;
	}
	
	// PARA CREAR EL CONTROL DOCENTE (LAS NOTAS DE LOS ESTUDUIANTES)
	public boolean darNota(Estudiante estudiante, Asignatura asignatura, float nota1, float nota2){
		boolean val = false;
		
		if(buscarControlDocente(estudiante, asignatura)!=null){
			val = true;
			buscarControlDocente(estudiante, asignatura).insertarNotas(nota1, nota2);
			estudiante.insertarNotas(asignatura, nota1, nota2);
		}
		
		return val;
	}
	
	
	// PARA CREAR EL CONTROL DOCENTE
	public void crearControlDocente(Grupo grupo, Asignatura asignatura){
		
		for(Estudiante i : grupo.getGrupoEstudiantes()){
			ControlDocente controlDocente = new ControlDocente(i, asignatura);
			notasAlumnos.add(controlDocente);
			i.insertarControlDocente(controlDocente);
		}
	}
	
	
	// PARA BUSACR EL CONTROL DOCENTE EN UN PROFESOR DADO UN ESTUDIANTE Y UNA ASIGNATURA
	public ControlDocente buscarControlDocente(Estudiante estudiante, Asignatura asignatura){
		boolean val = true;
		int i = 0;
		ControlDocente controlDocente = null;
		
		while(i<notasAlumnos.size() && val){
			if(notasAlumnos.get(i).getAsignatura().equals(asignatura) && notasAlumnos.get(i).getiDEstudiante().equalsIgnoreCase(estudiante.getID())){
				controlDocente=notasAlumnos.get(i);
				val=false;
			}			
			i++;
		}
		
		return controlDocente;
	}
	
	
	// para modificar los atributos del profesor
	public void modificarProfesor(String iD, String nombre, String categoriaDocente, String categoriaCientifica, String centroLaboral, String organismo, String direccion ){
		
		setNombre(nombre);
		
		setCategoriaCientifica(categoriaCientifica);
		
		setCategoriaDocente(categoriaDocente);
			
		setCentroLaboral(centroLaboral);
		
		setOrganismo(organismo);
		
		setDireccion(direccion);
		
	}
	
	// PARA DAR CARGO EN EL CONSEJO DE DIRECCON
	public void  agregarCargoConsejoDireccion( String cargo){
		setCargoConsejoDireccion(cargo);
	}
	
	
	// para eliminarlo del consejo de direccion
	public void eliminarCargo(){
		setCargoConsejoDireccion(null);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
