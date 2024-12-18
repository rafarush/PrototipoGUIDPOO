package logica.Clases;

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
	protected Profesor( String iD, String nombre, String categoriaCientifica, String categoriaDocente, String centroLaboral, String organismo, String Direccion) {
		super(nombre, iD, Direccion);
		// TODO Auto-generated constructor stub
		setCategoriaCientifica(categoriaCientifica);
		
		
		setCategoriaDocente(categoriaDocente);
		
		
		setCentroLaboral(centroLaboral);
		
		setOrganismo(organismo);
		
		this.cargoConsejoDireccion = null;
		
		this.salario = 100;
		
		notasAlumnos = new ArrayList<ControlDocente>();
		
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
		
		if(categoriaCientifica.equalsIgnoreCase("M�ster"))
			this.categoriaCientifica = "M�ster";
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
		else if(cargoConsejoDireccion.equalsIgnoreCase("Subdirector de Extensi�n Universitaria"))
			this.cargoConsejoDireccion = "Subdirector de Extensi�n Universitaria";
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
		float salarioTotal = salario;
		
		if(categoriaDocente.equalsIgnoreCase("Asistente"))
			salarioTotal+=200;
		else if(categoriaDocente.equalsIgnoreCase("Auxiliar"))
			salarioTotal+=300;
		else if(categoriaDocente.equalsIgnoreCase("Titular"))
			salarioTotal+=540;
		
		if(categoriaCientifica.equalsIgnoreCase("M�ster"))
			salarioTotal+=380;
		else if(categoriaCientifica.equalsIgnoreCase("Doctor"))
			salarioTotal+=+550;
		
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
	public ControlDocente crearControlDocente(Estudiante estudiante, Asignatura asignatura){
		
		ControlDocente controlDocente = new ControlDocente(estudiante, asignatura);
		notasAlumnos.add(controlDocente);		
		return controlDocente;
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
