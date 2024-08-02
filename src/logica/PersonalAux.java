package logica;

public class PersonalAux {

	private String cI;
	private String nombre;
	private String areaDeTrabajo;
	private String direcc;
	private float salario;
	
	public PersonalAux(String cI, String nombre, String areaDeTrabajo,
			String direcc, float salario) {
		
		setCi(cI);
		setNombre(nombre);
		setAreaDeTrabajo(areaDeTrabajo);
		setDirecc(direcc);
		setSalario(salario);
	}

	public String getCi() {
		return cI;
	}

	public void setCi(String cI) {
		this.cI = cI;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAreaDeTrabajo() {
		return areaDeTrabajo;
	}

	public void setAreaDeTrabajo(String areaDeTrabajo) {
		this.areaDeTrabajo = areaDeTrabajo;
	}

	public String getDirecc() {
		return direcc;
	}

	public void setDirecc(String direcc) {
		this.direcc = direcc;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}
	
	
	
	
}
