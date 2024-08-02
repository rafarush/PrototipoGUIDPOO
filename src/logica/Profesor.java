package logica;

public class Profesor {

	private String cI;
	private String nombre;
	private String categoCientifica;
	private String categoDocente;
	private String centroLaboral;
	private String organismo;
	private String direcc;
	private float salario;
	
	public Profesor(String cI, String nombre, String categoCientifica,
			String categoDocente, String centroLaboral, String organismo,
			String direcc, float salario) {
		setCi(cI);
		setNombre(nombre);
		setCategoCientifica(categoCientifica);
		setCategoDocente(categoDocente);
		setCentroLaboral(centroLaboral);
		setOrganismo(organismo);
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

	public String getCategoCientifica() {
		return categoCientifica;
	}

	public void setCategoCientifica(String categoCientifica) {
		this.categoCientifica = categoCientifica;
	}

	public String getCategoDocente() {
		return categoDocente;
	}

	public void setCategoDocente(String categoDocente) {
		this.categoDocente = categoDocente;
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
