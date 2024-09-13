package logica.Clases;

public class Usuario {

	private String nombre;
	private String password;
	
	//Contructor
	public Usuario(String nombre, String password) {
		setNombre(nombre);
		setPassword(password);
	}
	
	//Getters and Setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		if(password.length()==8){
			throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
		}else{
			this.password = password;			
		}
	}
	
	
}
