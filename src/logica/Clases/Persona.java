package logica.Clases;

public abstract class Persona {
	
	//atributos
	protected String nombre;
	protected String iD;
	protected String direccion;
	
	
	//constructor
	protected Persona( String nombre, String iD, String Direccion ){
		setNombre(nombre);
		
		setID(iD);
		
		setDireccion(Direccion);
	}

	
	
	
	//sets y gets
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getID() {
		return iD;
	}

	public void setID(String iD) {
		this.iD = iD;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
}
