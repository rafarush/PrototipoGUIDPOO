package logica;

import java.util.ArrayList;

public class Fct {
	//atributos
	
	private ArrayList<Persona> personas;
	private ArrayList<Grupo> grupos;
	private PlanEstudio planEstudio;
	private ArrayList<Periodo> periodos;

	
	
	//constructor
	public Fct() {
		personas= new ArrayList<>();
		grupos = new ArrayList<>();
		planEstudio =  new PlanEstudio();
		periodos = new ArrayList<>();
		
		crearPeriodos();
	}
	
	

	// ELIMINAR PERSONA
	public void eliminarPersona(String iD){
		boolean val= true;
		int i=0;
		while(i<personas.size() && val){
			if(personas.get(i).getID().equalsIgnoreCase(iD)){
				
				
				
				// lo que se va a hacer siempre aunque sea profe, estu o per.apoyo
				personas.remove(i);
				val=false;
			}
			i++;
		}
		
	}
	
	
	//**************************************  PROFESOR ************************************************************
	
	//crear , modificar y eliminar 
	
	// crear profesor
	public boolean crearPersona(String iD, String nombre, String categoriaDocente, String categoriaCientifica, String centroLaboral, String organismo, String direccion ){
		
		boolean val = false;
		if(buscarPersona(iD)==null){
			Profesor profesor1= new Profesor(iD, nombre, categoriaDocente, categoriaCientifica, centroLaboral, organismo, direccion);
			personas.add(profesor1);
			val = true;
		}
		else
			throw new IllegalArgumentException("Esa persona ya esta en el sistema.");
	
		return val;
	}
	
	// MODIFICAR PROFESOR
	public void modificarPersona(String iD, String nombre, String categoriaDocente, String categoriaCientifica, String centroLaboral, String organismo, String direccion, float salario ){
		
		buscarUnProfesor(iD).modificarProfesor(iD, nombre, categoriaDocente, categoriaCientifica, centroLaboral, organismo, direccion, salario);
		
		for(Periodo p : periodos){
			for(PlanificacionDocente pd : p.getPlanificacionesDocentes()){
				if(pd.getProfesor().getID().equalsIgnoreCase(iD)){
					pd.getProfesor().modificarProfesor(iD, nombre, categoriaDocente, categoriaCientifica, centroLaboral, organismo, direccion, salario);
				}
			}
		}
		
	}
	
	// ELIMINAR PROFESOR
	// no creo q haga falta
	/*public void eliminarProfesor(Profesor profesor){
		boolean val= true;
		int i=0;
		while(i<personas.size() && val){
			
			i++;
		}
		
	}*/
	
	//**************************************  PERSONAL DE APOYO ************************************************************
	
	// crear personalApoyo
	public boolean crearPersona( String iD, String nombre, String labor, String direccion){
		
		boolean val = false;
		if(buscarPersona(iD)==null){
			PersonalApoyo personajeApoyo1 = new PersonalApoyo(iD, nombre, labor, direccion);
			personas.add(personajeApoyo1);
			val = true;
		}
		else
			throw new IllegalArgumentException("Esa persona ya esta en el sistema.");
		
		return val;
	}
	
	// modificar Personal de apoyo
	public void modificarPersona(String iD, String nombre, String labor, String direccion) {
		buscarUnPersonalApoyo(iD).modificarPersonalApoyo(iD, nombre, labor, direccion);
	}
	
	
	//**************************************  ESTUDIANTE   ************************************************************
	
	// crear Estudiante
	public boolean crearPersona( String iD, String nombre, int anoAcademico, String centroLaboral, String organismo, String direccion){
		
		boolean val = false;
		if(buscarPersona(iD)==null){
			Estudiante estudiante = new Estudiante( iD,nombre, anoAcademico, centroLaboral, organismo, direccion);
			personas.add(estudiante);
			val = true;
		}
		else
			throw new IllegalArgumentException("Esa persona ya esta en el sistema.");
		
		return val;
	}
	
	// MODIFICAR ESTUDIANTE
	
	
	
	
	
	
	
	
	
	
	// CREAR GRUPO
	public boolean crearGrupo(String nombreGrupo, int anno){
		boolean val = false;
		Grupo grupo = new Grupo(nombreGrupo, anno);
		if(buscarGrupo(nombreGrupo) == null){
			val = true; 
			grupos.add(grupo);
		}
		
		return val;
	}
	
	
	// CREAR ANNOS ACADEMICOS
	public void crearPeriodos(){
		for(int i=0;i<12;i++){
			Periodo periodo = new Periodo();
			periodos.add(periodo);
		}
	}
	
	//sets y gets
	
	
	public PlanEstudio getPlanEstudio() {
		return planEstudio;
	}

	// NO ME VA A HACER FALTA ASI QUE NO SE SI LO PONGO O NO
	public void setPlanEstudio(PlanEstudio planEstudio) {
		this.planEstudio = planEstudio;
	}
	
	
	// get de PERSONAS
	public ArrayList<Persona> getPersonas() {
		return personas;
	}
	
	
	// get de GRUPOS
	public ArrayList<Grupo> getGrupos() {
		return grupos;
	}
	

	public ArrayList<Periodo> getPeriodos() {
		return periodos;
	}

	// Este set no sera necesario ya que seran siempre 6 años y los que cambiara dentro d estos es responsabilidad d ellos
	public void setPeriodos(ArrayList<Periodo> periodos) {
		this.periodos = periodos;
	}	
	


	//OTROS

	
	//   TENGO que implementar que tengan todos el mismo año ---->>>> YA IMPLEMENTADO

	//   TENGO que implementar que el estudiante no puede entrar si esta en otro grupo 
	public void insertarAGrupo(Estudiante estudiante, String grupo){
		int i = 0;
		boolean val = true;
		while(i<grupos.size() && val){
			if(grupos.get(i).getNombreGrupo().equalsIgnoreCase(grupo)){
				if(grupos.get(i).getGrupoEstudiantes().size()==0){
					grupos.get(i).insertarAGrupoEstudiante(estudiante);
				}else if(grupos.get(i).getGrupoEstudiantes().get(0).getAnnoAcademico() == estudiante.getAnnoAcademico()){
					grupos.get(i).insertarAGrupoEstudiante(estudiante);
				}else{
					throw new IllegalArgumentException("Son de distintos annos academicos");
				}
				val=false;
				}
			i++;
		}
		if(val)
			throw new IllegalArgumentException("No existe el grupo");
	}




	// PARA BUSCAR PROFESOR
	public ArrayList<Profesor> buscarProfesores(){
		
		ArrayList<Profesor> listaProfesores = new ArrayList<>();
		for(Persona i : personas){
			if (i instanceof Profesor)
				listaProfesores.add((Profesor)i);
		}
		
		return listaProfesores;
	}
	
	// PARA BUSCAR PERSONAL DE APOYO
	public ArrayList<PersonalApoyo> buscarPersonalApoyo(){
		
		ArrayList<PersonalApoyo> listaApoyo = new ArrayList<>();
		for(Persona i : personas){
			if (i instanceof PersonalApoyo)
				listaApoyo.add((PersonalApoyo)i);
		}
	
		return listaApoyo;
	}
	
	// PARA BUSCAR ESTUDIANTE
	public ArrayList<Estudiante> buscarEstudiantes(){
			ArrayList<Estudiante> estudiantes = new ArrayList<>(); 
			for(Persona i : personas){
				if (i instanceof Estudiante)
					estudiantes.add((Estudiante)i);
			}
		
		return estudiantes;
	}
	
	
	//     PARA BUSCAR A TRAVES D UN ID A UNA PERSONA
	//****************************************************************************************
	public Persona buscarPersona(String iD){
		Persona persona = null;
		
		boolean val = true;
		int i = 0;
		while(i<personas.size() && val){
			if(personas.get(i).getID().equalsIgnoreCase(iD)){
				val = false;
				persona=personas.get(i);
			}
			i++;
		}
		
		return persona;
	}
	//****************************************************************************************
	
	
	
	
	// PARA BUSCAR A LOS DEL CONSEJO DE DIRECCION
	public ArrayList<Profesor> buscarConsejoDireccion(){
		ArrayList<Profesor> consejoDireccion = new ArrayList<>();
		
		for(Profesor i : buscarProfesores()){
			if(i.getCargoConsejoDireccion()!=null)
				consejoDireccion.add(i);
		}
		
		return consejoDireccion;
	}
	
	
	// AGREGAR A CONSEJO DE DIRECCION
	public void agregarCargoConsejoDireccion(Persona persona, String cargo){
		((Profesor)persona).setCargoConsejoDireccion(cargo);
	}
	
	
	// PARA BUSCAR EL GRUPO
	public Grupo buscarGrupo(String nombre){
		//System.out.println("grupo");
		Grupo grupo = null;
		int i = 0;
		boolean val = true;
		while(i<grupos.size() && val){
			if(grupos.get(i).getNombreGrupo().equalsIgnoreCase(nombre)){
				grupo=grupos.get(i);
			}
			i++;
		}
		
		return grupo;
	}
	
	
	// PARA BUSCAR UN PROFESOR ESPECIFICO
	public Profesor buscarUnProfesor(String iD){
		
		Profesor profesor = null;
		int i = 0;
		boolean val = true;
		
		while(i<buscarProfesores().size() && val){
			if(buscarProfesores().get(i).getID().equalsIgnoreCase(iD)){
				val=false;
				profesor = buscarProfesores().get(i);
			}
			i++;
		}
		
		return profesor;
	}
	
	// PARA BUSCAR UN ESTUDIANTE ESPECIFICO
	public Estudiante buscarUnEstudiante(String iD) {
		
		Estudiante estudiante = null;
		int i = 0;
		boolean val = true;
		
		while(i<buscarEstudiantes().size() && val){
			if(buscarEstudiantes().get(i).getID().equalsIgnoreCase(iD)){
				val=false;
				estudiante = buscarEstudiantes().get(i);
			}
			i++;
		}
		
		return estudiante;
	}
	
	// PARa BUSCAR UN PERNAL DE APOYO ESPECIFICO
	public PersonalApoyo buscarUnPersonalApoyo(String iD) {

		PersonalApoyo personalApoyo = null;
		int i = 0;
		boolean val = true;
		
		while(i<buscarPersonalApoyo().size() && val){
			if(buscarPersonalApoyo().get(i).getID().equalsIgnoreCase(iD)){
				val=false;
				personalApoyo = buscarPersonalApoyo().get(i);
			}
			i++;
		}
		
		return personalApoyo;
	}
	
	
	// PARA VERIFICAR QUE TODOS LOS ESTUDIANTES TENGAN NOTAS
	/*public boolean verificarNotas(){
		boolean val = true;
		
		for(Grupo i : grupos){
			for(Estudiante e : i.getGrupoEstudiantes()){
				if(!e.verificarNotas())
					val=false;
			}
		}
		
		return val;
	}*/
	
	
	// PARA PASAR DE PERIODO
	public boolean pasarPeriodo(){
		boolean val = true;
		
		if(buscarEstudiantesSinNotas().size()!=0)
			val=false;
		
		return val;
	}
	
	
	// PARA PASAR DE ANNO
	public boolean pasarAnno(){
		boolean val = true;
		
		if(buscarEstudiantesSinNotas().size()!=0)
			val=false;
		else{
			for(Estudiante e : buscarEstudiantes()){
				if(e.verificarArrastre() || e.verificarNotasSuspensas().size()==0)
					e.setAnnoAcademico(e.getAnnoAcademico()+1);
			}
			grupos.clear();
			crearGruposArrastres();
		}
			
		
		
		return val;
	}
	
	
	
	// Para crear los grupos de arrastre
	public void crearGruposArrastres(){
		
		for(int i = 2;i<7;i++){
			if(buscarArrastresPorAnno(i).size()!=0){
				Grupo grupo = new Grupo("Arrastres de "+(i-1) , i-1);
				grupo.setGrupoEstudiantes(buscarArrastresPorAnno(i));
				grupos.add(grupo);
			}
		}
		
	}
	
	
	
	
	//Para buscar los arrastre de un anno especifico
	public ArrayList<Estudiante> buscarArrastresPorAnno(int anno) {
		
		ArrayList<Estudiante> arrastres = new ArrayList<>();
		
		for(Estudiante e : buscarEstudiantesConArrastre()){
			if(e.getAnnoAcademico() == anno)
				arrastres.add(e);
		}
		
		return arrastres;
	}
	
	
	
	
	
	
	//---------------------------------------   REPORTES    ---------------------------------------------------------------
	
	// 1- PARA BUSCAR TODOS LOS ESTUDIANTES QEU ESTAN GRADUADOS(ANNO ACADEMICO = 7)
	public ArrayList<Estudiante> buscarEstudiantesGraduados(){
		ArrayList<Estudiante> graduados = new ArrayList<>();
		
		for(Estudiante i : buscarEstudiantes()){
			if(i.getAnnoAcademico()==7)
				graduados.add(i);
		}
		
		return graduados;
	}
	
	// 2- PARA BUSCAR LOS ESTUDIANTES QUE LE FALTAN AL MENOS UNA NOTA
	public ArrayList<Estudiante> buscarEstudiantesSinNotas(){
		ArrayList<Estudiante> sinNotas = new ArrayList<>();
		
		for(Estudiante i : buscarEstudiantes()){
			if(!i.verificarNotas())
				sinNotas.add(i);
		}
		
		return sinNotas;
	}
	
	// 3- PARA BUSCAR LOS ESTUDIANTES QUE ESTAN ARRASTRANDO ALGUNA ASIGNATURA(VOY POR LOS ESTUDIANTES CON EL ANNO EN QEU ESTAN 
	// SI ESTAN EN ALGUN GRUPO DEL ANNO ANTERIOR ARRASTRA)
	public ArrayList<Estudiante> buscarEstudiantesConArrastre(){
		ArrayList<Estudiante> arrastres = new ArrayList<>();
		
		for(Estudiante e : buscarEstudiantes()){
			if(e.verificarArrastre())
				arrastres.add(e);
		}
		
		return arrastres;
	}
	
	
	// 4- PARA BUSCAR ESTUDIANTES SUSPENSOS EN ALGUNA ASIGNATURA                         
	public ArrayList<Estudiante> buscarEstudiantesSuspensos(){
		ArrayList<Estudiante> suspensos = new ArrayList<>();
		
		for(Estudiante i : buscarEstudiantes()){
			if(i.verificarSuspenso())
				suspensos.add(i);
		}
		
		return suspensos;
	}
	
	// 5- PARA BUSCAR en cada año cual es el grupo con menor cantidad de estudiantes
	public ArrayList<Grupo> buscarGrupoConMenorCantidad(){
		ArrayList<Grupo> gruposM = new ArrayList<>();
		
		for(int i=1;i<7;i++){
			int menor = Integer.MAX_VALUE;
			Grupo grupo = null;
			for(Grupo g : grupos){
				if(g.getAnnoAcademico()== i && g.getGrupoEstudiantes().size() < menor){
					grupo = g;
					menor = g.getGrupoEstudiantes().size();
				}
			}
			if(grupo!=null)
				gruposM.add(grupo);
		}
		
		return gruposM;
	}
	
	
	// 6- para saber los estudiantes que tienen más de 4.5  de promedio en todas las asignaturas de su carrera.
	public ArrayList<Estudiante> buscarEstudiantesOros(){
		ArrayList<Estudiante> oros = new ArrayList<>();
		
		for(Estudiante i : buscarEstudiantes()){
			if(i.calcularPromedio()>=4.5)
				oros.add(i);
		}
		
		return oros;
	}
	
	// 7- para saber los estudiantes sin grupo
	public ArrayList<Estudiante> buscarEstudiantesSinGrupo(){
		ArrayList<Estudiante> sinGrupo = new ArrayList<>();
		
		for(Estudiante e : buscarEstudiantes()){
			int i = 0;
			boolean val = true;
			
			if(!e.verificarArrastre()){
				while(i<grupos.size() && val){
					if(grupos.get(i).getGrupoEstudiantes().contains(e))
						val=false;
					i++;
				}
			}else{
				int cont = 0;
				while(i<grupos.size() && val){
					if(grupos.get(i).getGrupoEstudiantes().contains(e))
						cont++;
					if(cont==2)
						val=false;
				}
			}
			
			if(val)
				sinGrupo.add(e);
		}
		
		return sinGrupo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
