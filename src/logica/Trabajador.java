package logica;

public abstract class Trabajador extends Persona{

	protected float salario;
	
	public Trabajador(String nombre, String iD, String Direccion) {
		super(nombre, iD, Direccion);
		this.salario=100;
		// TODO Auto-generated constructor stub
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario){
		if(salario>0)
			this.salario = salario;
		else
			throw new IllegalArgumentException("El salario no puede ser menor que cero.");
		
	}
	
	abstract float calcularSalario();

	
}
