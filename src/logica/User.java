package logica;

public class User {

	private String name;
	private String password;
	
	//Contructor
	public User(String name, String password) {
		setName(name);
		setPassword(password);
	}
	
	//Getters and Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		if(password.length()<8){
			throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
		}else{
			this.password = password;			
		}
	}
	
	
}
