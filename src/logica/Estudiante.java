package logica;

public class Estudiante {
	
	private String cI;
	private String nombre;
	private String anno;
	private String grupo;
	private String centroLaboral;
	private String organismo;
	private String direcc;
	
	public Estudiante(String cI, String nombre, String anno, String grupo,
			String centroLaboral, String organismo, String direcc) {
		
		setCi(cI);
		setNombre(nombre);
		setAnno(anno);
		setGrupo(grupo);
		setCentroLaboral(centroLaboral);
		setOrganismo(organismo);
		setDirecc(direcc);
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

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
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
	
	
	
}
