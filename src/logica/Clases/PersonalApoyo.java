package logica.Clases;

public class PersonalApoyo extends Trabajador{
	
	//atributos
	private String labor;
	
	

	//el salario base del personal de apoyo es d $100
	public PersonalApoyo(String iD, String nombre, String labor, String direccion) {
		super(nombre, iD, direccion);
		// TODO Auto-generated constructor stub
		
		setLabor(labor);
	}



	
	
	//sets y gets
	public String getLabor() {
		return labor;
	}

	public void setLabor(String labor) {
		
		if(labor.equalsIgnoreCase("Secretaria"))
			this.labor = "Secretaria";
		else if(labor.equalsIgnoreCase("Biblioteca"))
			this.labor = "Biblioteca";
		else if(labor.equalsIgnoreCase("Laboratorio"))
			this.labor = "Laboratorio";
		else
			throw new IllegalArgumentException("No existe esa labor para el personal de apoyo.");
		
	}




	/*public float getSalario() {
		return salario;
	}*/

	public void setSalario(float salario) {
		
		if(salario>0)
			this.salario = salario;
		else
			throw new IllegalArgumentException("El salario tiene que ser mayor que cero.");
	}



	public float calcularSalario() {
		return salario;
	}
	
	
	//OTROS
	
	// Modificar el personal de apoyo
	public void modificarPersonalApoyo(String iD, String nombre, String labor, String direccion){
		
		setNombre(nombre);
		
		setDireccion(direccion);
		
		setSalario(salario);
		
		setLabor(labor);
		
	}
	
	
	
	
	
}
