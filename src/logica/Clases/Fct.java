package logica.Clases;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

import logica.utils.ProcesoNoPermitidoException;

public final class Fct {
	//atributos
	
	private ArrayList<Persona> personas;
	private ArrayList<Grupo> grupos;
	private PlanEstudio planEstudio;
	private ArrayList<Periodo> periodos;
	
	private Usuario usuario;
	
	private static Fct instance;

	
	
	//constructor
	
	
	// TENGO QUE PONER EL CONSTRUCTOR DE LA FCT EN PRIVATE
	private Fct() {
		personas= new ArrayList<>();
		grupos = new ArrayList<>();
		planEstudio =  new PlanEstudio();
		periodos = new ArrayList<>();
		
		usuario = new Usuario("Fermin", "12345678");
		
		crearPeriodos();
	}
	
	
	// SINGLENTON
	public static Fct getInstance() {
		if (instance == null)
            instance = new Fct();
		
        return instance;
	}
	
	// ELIMINAR FCT
	public static void eliminarInstance(){
		instance=null;
	} 
	

	// ELIMINAR PERSONA
	public void eliminarPersona(String iD){
		boolean val= true;
		int i=0;
		while(i<personas.size() && val){
			if(personas.get(i).getID().equalsIgnoreCase(iD)){
				if(buscarPersona(iD) instanceof Estudiante){
					eliminarEstudiante(iD); 
				}
				
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
	public void modificarPersona(String iD, String nombre, String categoriaDocente, String categoriaCientifica, String centroLaboral, String organismo, String direccion){
		
		buscarUnProfesor(iD).modificarProfesor(iD, nombre, categoriaDocente, categoriaCientifica, centroLaboral, organismo, direccion);
		
		for(Periodo p : periodos){
			for(PlanificacionDocente pd : p.getPlanificacionesDocentes()){
				if(pd.getProfesor().getID().equalsIgnoreCase(iD)){
					pd.getProfesor().modificarProfesor(iD, nombre, categoriaDocente, categoriaCientifica, centroLaboral, organismo, direccion);
				}
			}
		}
		
	}
	
	// cambiar salario base del profesor
	public void cambiarSalarioBaseProfe(float salarioBase){
		for(Profesor p : buscarProfesores()){
			p.setSalario(salarioBase);
		}
	}
	
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
	
	// cambiar salario base del personal de apoyo
		public void cambiarSalarioBasePersonalA(float salarioBase){
			for(PersonalApoyo pA : buscarPersonalApoyo()){
				pA.setSalario(salarioBase);
			}
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
	public void modificarPersona(String iD, String nombre, int anoAcademico, String centroLaboral, String organismo, String direccion) {
		buscarUnEstudiante(iD).modificarEstudiante(iD, nombre, anoAcademico, centroLaboral, organismo, direccion);
		
		for(int i=0;i<periodos.size();i++){
			if(buscarUnEstudiante(iD).getAnnoAcademico()==1){
				if(i==0 || i==6){
					for(PlanificacionDocente pD : periodos.get(i).getPlanificacionesDocentes()){
						if(pD.getGrupo().buscarEstudiante(iD).getID().equalsIgnoreCase(iD)){
							pD.getGrupo().buscarEstudiante(iD).modificarEstudiante(iD, nombre, anoAcademico, centroLaboral, organismo, direccion);
						}
					}
				}
			}else{
				if(i==buscarUnEstudiante(iD).getAnnoAcademico()-1 || i==buscarUnEstudiante(iD).getAnnoAcademico()-2 || i==buscarUnEstudiante(iD).getAnnoAcademico()+6 || i==buscarUnEstudiante(iD).getAnnoAcademico()+5){
					for(PlanificacionDocente pD : periodos.get(i).getPlanificacionesDocentes()){
						if(pD.getGrupo().buscarEstudiante(iD).getID().equalsIgnoreCase(iD)){
							pD.getGrupo().buscarEstudiante(iD).modificarEstudiante(iD, nombre, anoAcademico, centroLaboral, organismo, direccion);
						}
					}
				}
			}
		}
	}
	
	// ELIMINAR ESTUDIANTE
	public boolean eliminarEstudiante(String iD) {
		ArrayList<Grupo> gP = new ArrayList<>();
		boolean val = false;
		for(Grupo g : grupos){
			if(g.getGrupoEstudiantes().contains(buscarUnEstudiante(iD))){
				g.getGrupoEstudiantes().remove(buscarUnEstudiante(iD));
				if(g.getGrupoEstudiantes().size()==0){
					gP.add(g);
				}
				val = true;
				
			}
			
		}
 
		for (Periodo p : periodos){
			ArrayList<PlanificacionDocente> pDB = new ArrayList<>();
			for(PlanificacionDocente pD : p.getPlanificacionesDocentes()){
				for(Grupo g : gP){
					if(pD.getGrupo().getNombreGrupo().equalsIgnoreCase(g.getNombreGrupo()))
						pDB.add(pD);
				}
			}
			p.getPlanificacionesDocentes().removeAll(pDB);
			pDB.clear();
		}
		
		grupos.removeAll(gP);
		return val;
	}
	
	
	// PARA ELIMINAR UN ESTUDIANTE DE UN GRUPO
	public boolean eliminarEstudianteDeGrupo( Estudiante estudiante, Grupo grupo){
		boolean val = false;
		if(!verificarGrupoPD(grupo))
			val = grupo.eliminarEstudiante(estudiante);
		
		return val;
	}
	
	
	
	// CREAR GRUPO
	public boolean crearGrupo(String nombreGrupo, int anno){
		boolean val = false;
		
		if(buscarGrupo(nombreGrupo) == null){
			val = true; 
			Grupo grupo = new Grupo(nombreGrupo, anno);
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
	
	
	
	
	public void setPersona(ArrayList<Persona> personas) {
		this.personas = personas;
	}
	
	public void setPlanEstudio(ArrayList<Grupo> grupos) {
		this.grupos = grupos;
	}
	
	
	
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
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	

	public ArrayList<Periodo> getPeriodos() {
		return periodos;
	}

	// Este set no sera necesario ya que seran siempre 6 años y los que cambiara dentro d estos es responsabilidad d ellos
	public void setPeriodos(ArrayList<Periodo> periodos) {
		this.periodos = periodos;
	}	
	


	//OTROS

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
	
	
	// PARA BUSCAR DADO UN PROFESOR SI ESTE ESTA EN ALGUNA PLANIFICACION DOCENTE
	public boolean verificarProfeEnPlanDoc(String iD){
		int i=0;
		boolean val = false;
		while(i<periodos.size() && !val){
			int e = 0;
			while(e<periodos.get(i).getPlanificacionesDocentes().size() && !val){
				if(periodos.get(i).getPlanificacionesDocentes().get(e).getProfesor().getID().equalsIgnoreCase(iD))
					val = true;
				e++;
			}
			i++;
		}
		
		return val;
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
	
	// PARA BUSCAR A LOS PROFESORES QUE NO ESTAN EN EL CONSEJO DE DIRECCION
	public ArrayList<Profesor> buscarProfesSinCargo(){
		ArrayList<Profesor> profesSinCargo = new ArrayList<>();
		for(Profesor p : buscarProfesores()){
			if(p.getCargoConsejoDireccion()==null)
				profesSinCargo.add(p);
		}
		
		return profesSinCargo;
	}
	
	// AGREGAR A CONSEJO DE DIRECCION
	public boolean agregarCargoConsejoDireccion(Profesor profesor, String cargo){
		boolean val = false;
		
		if(cargo.equalsIgnoreCase("Director") && buscarDirector()==null ){
			profesor.agregarCargoConsejoDireccion(cargo);
			val = true;
		}else if(cargo.equalsIgnoreCase("Subdirector Docente")&& buscarSubdirectorDocente()==null ){
			profesor.agregarCargoConsejoDireccion(cargo);
			val = true;
		}else if(cargo.equalsIgnoreCase("Subdirector de Investigaciones y Posgrado")&& buscarSubdirectorInvPos()==null ){
			profesor.agregarCargoConsejoDireccion(cargo);
			val = true;
		}else if(cargo.equalsIgnoreCase("Subdirector de Extensión Universitaria")&& buscarSubdirectorExtendUni()==null ){
			profesor.agregarCargoConsejoDireccion(cargo);
			val = true;
		}else if(cargo.equalsIgnoreCase("Jefe de Laboratorios")&& buscarJefeLaboratorios()==null ){
			profesor.agregarCargoConsejoDireccion(cargo);
			val = true;
		}
		
		return val;
	}
	
	// PARA ELIMINAR A UN PROFESOR DEL CONSEJO DE DIRECCION
	public boolean eliminarDelCD(Profesor profesor){
		boolean val = false;
		
		if(profesor.getCargoConsejoDireccion()!=null){
			profesor.eliminarCargo();
			val = true;
		}
		
		return val;
	}
	
	// para buscar los cargos que faltan
	public ArrayList<String> buscarCargosCDFaltantes(){
		ArrayList<String> cargos = new ArrayList<>();
		
		if(buscarDirector()==null)
			cargos.add("Director");
		if(buscarSubdirectorDocente() ==null)
			cargos.add("Subdirector Docente");
		if(buscarSubdirectorInvPos() ==null)
			cargos.add("Subdirector de Investigaciones y Posgrado");
		if(buscarSubdirectorExtendUni() ==null)
			cargos.add("Subdirector de Extensión Universitaria");
		if(buscarJefeLaboratorios() ==null)
			cargos.add("Jefe de Laboratorios");
		
		return cargos;
	}
	
	// BUSCAR DIRECTOR
	public Profesor buscarDirector() {
		Profesor profesor = null;
		boolean val = true;
		int i = 0;
		
		while(i<buscarConsejoDireccion().size() && val){
			if(buscarConsejoDireccion().get(i).getCargoConsejoDireccion().equalsIgnoreCase("Director")){
				profesor = buscarConsejoDireccion().get(i);
				val = false;
			}
			i++;
		}
		
		return profesor;
	}
	
	// BUSCAR SUBDIRECTOR DOCENTE
	public Profesor buscarSubdirectorDocente() {
		Profesor profesor = null;
		boolean val = true;
		int i = 0;
		
		while(i<buscarConsejoDireccion().size() && val){
			if(buscarConsejoDireccion().get(i).getCargoConsejoDireccion().equalsIgnoreCase("Subdirector Docente")){
				profesor = buscarConsejoDireccion().get(i);
				val = false;
			}
			i++;
		}
		
		return profesor;
	}
	
	// BUSCAR Subdirector de Investigaciones y Posgrado
	public Profesor buscarSubdirectorInvPos() {
		Profesor profesor = null;
		boolean val = true;
		int i = 0;
		
		while(i<buscarConsejoDireccion().size() && val){
			if(buscarConsejoDireccion().get(i).getCargoConsejoDireccion().equalsIgnoreCase("Subdirector de Investigaciones y Posgrado")){
				profesor = buscarConsejoDireccion().get(i);
				val = false;
			}
			i++;
		}
		
		return profesor;
	}
	
	// BUSCAR Subdirector de Extensión Universitaria
	public Profesor buscarSubdirectorExtendUni() {
		Profesor profesor = null;
		boolean val = true;
		int i = 0;
		
		while(i<buscarConsejoDireccion().size() && val){
			if(buscarConsejoDireccion().get(i).getCargoConsejoDireccion().equalsIgnoreCase("Subdirector de Extensión Universitaria")){
				profesor = buscarConsejoDireccion().get(i);
				val = false;
			}
			i++;
		}
		
		return profesor;
	}
	
	// BUSCAR Jefe de Laboratorios
	public Profesor buscarJefeLaboratorios() {
		Profesor profesor = null;
		boolean val = true;
		int i = 0;
		
		while(i<buscarConsejoDireccion().size() && val){
			if(buscarConsejoDireccion().get(i).getCargoConsejoDireccion().equalsIgnoreCase("Jefe de Laboratorios")){
				profesor = buscarConsejoDireccion().get(i);
				val = false;
			}
			i++;
		}
		
		return profesor;
	}
	
	
	// PARA BUSCAR EL GRUPO
	public Grupo buscarGrupo(String nombre){
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
	
	
	
	// PARA PASAR DE PERIODO
	public boolean pasarPeriodo() throws ProcesoNoPermitidoException{
		boolean val = true;
		
		if(buscarEstudiantesSinNotasPrimerSemestre().size()!=0){
			val=false;
			throw new ProcesoNoPermitidoException("Hay "+buscarEstudiantesSinNotasPrimerSemestre().size()+" estudiantes sin notas.");
		}
		
		return val;
	}


	//para empezar un nuevo semestre
	public boolean empezarPeriodo() throws ProcesoNoPermitidoException{
		boolean val = false;
		
		if(buscarEstudiantesSinGrupo().size()==0){
			if(verificarGruposEnSusPD()){
				if(planEstudio.verificarAsignaturasMinimas()){
					if(buscarEstudiantes().size()>0){
						val = true;
					}else{
						throw new ProcesoNoPermitidoException("No existen estudiantes para empezar.");
					}
				}else{
					throw new ProcesoNoPermitidoException("En algún período no se han creado asignaturas.");
				}
			}else{
				throw new ProcesoNoPermitidoException("Hay grupos que le faltan crear sus planificaciones docentes correspondientes.");
			}
		}else{
			throw new ProcesoNoPermitidoException("Hay "+buscarEstudiantesSinGrupo().size()+" estudiantes sin grupo.");
		}
		
		return val;
	}
	
	//para verificar que todos los grupos estan entre las planidicaciones docentes
	public boolean verificarGruposEnSusPD() {
		 boolean val = true;
		 boolean var = false;
		 boolean var2 = false;
		 
		 for(int i = 0;i<grupos.size() && val; i++){
			 if(grupos.get(i) instanceof GrupoEspecial){
				 var2=false;
				 
				 int anno = ((GrupoEspecial) grupos.get(i)).getAsignatura().getAnnoAcademico();
				 int sem = ((GrupoEspecial) grupos.get(i)).getAsignatura().getSemestre();
				 
				 int per = anno-1;
				 
				 if(sem==2)
					 per=anno+5;
				 
				 for(int j=0;j<periodos.get(per).getPlanificacionesDocentes().size() && !var2 ; j++){
					 if(periodos.get(per).getPlanificacionesDocentes().get(j).getAsignatura().equals(((GrupoEspecial) grupos.get(i)).getAsignatura())){
						 var2 = true;
					 }
				 }
				 if(!var2)
						val = false;
			 }else{
				 for(int e=0;e<periodos.size() && val;e++){
						if(e==grupos.get(i).getAnnoAcademico()-1 || e==grupos.get(i).getAnnoAcademico()+5){ 
							var = false;
							for(PlanificacionDocente pD : periodos.get(e).getPlanificacionesDocentes()){
								if((planEstudio.buscarAsignaturasPorPeriodo(grupos.get(i).getAnnoAcademico(), 1).contains(pD.getAsignatura()) || planEstudio.buscarAsignaturasPorPeriodo(grupos.get(i).getAnnoAcademico(), 2).contains(pD.getAsignatura())) && pD.getGrupo().equals(grupos.get(i))){
									var = true;
								}
							}
							if(!var)
								val = false;
						}
						
					} 
			 }
			}
		 
		 
		 return val;
	}
	
	// PARA PASAR DE ANNO
	public boolean pasarAnno() throws ProcesoNoPermitidoException{
		boolean val = true;
		
		if(buscarEstudiantesSinNotas().size()!=0){
			val=false;
			throw new ProcesoNoPermitidoException("Hay "+buscarEstudiantesSinNotas().size()+" estudiantes sin notas.");
		}
		else{
			
			for(Estudiante e : buscarEstudiantes()){
				
				if(!e.verificarArrastreEspecial())
					e.limpiarEstudiante();
				else if((e.verificarNotasSuspensas1erSemestre().size()<2 && e.verificarNotasSuspensas2doSemestre().size()<2 ) && e.getAnnoAcademico()!=7)
					e.setAnnoAcademico(e.getAnnoAcademico()+1);
			}
			
			for(Periodo p : periodos)
				p.getPlanificacionesDocentes().clear();
			
			grupos.clear();
			crearGruposArrastres();
		}
			
		
		
		return val;
	}
	
	
	
	// Para crear los grupos de arrastre
	public void crearGruposArrastres(){
		
		for(int i = 2;i<7;i++){
			if(buscarArrastresPorAnno(i).size()!=0){
				for(Asignatura a : buscarAsignaturasArrastresPorAnno(i)){
					GrupoEspecial grupo = new GrupoEspecial("Arrastres de "+(i-1)+" en " +a.getNombre() , i-1 , a);
					grupo.setGrupoEstudiantes(buscarArrastresPorAsignatura(a));
					grupos.add(grupo);
					
				}
			}
		}
		
		
	}
	
	
	// para encontrar los anos en los q 
	
	
	//Para buscar los arrastre de un anno especifico
	public ArrayList<Estudiante> buscarArrastresPorAnno(int anno) {
		
		ArrayList<Estudiante> arrastres = new ArrayList<>();
		
		for(Estudiante e : buscarEstudiantesConArrastre()){
			if(e.getAnnoAcademico() == anno)
				arrastres.add(e);
		}
		
		return arrastres;
	}
	
	
	
	
	// Para buscar los arrastre de una asignatura especifica
	public ArrayList<Estudiante> buscarArrastresPorAsignatura(Asignatura asignatura){
		ArrayList<Estudiante> arrastres = new ArrayList<>();
		
		for(Estudiante e : buscarEstudiantesConArrastre()){
			if(e.buscarAsignaturasArrastres().contains(asignatura))
				arrastres.add(e);
		}
		
		
		return arrastres;
	}
	
	
	//buscar grupos de arrastre
	public ArrayList<Grupo> buscarGruposArrastres(){
		ArrayList<Grupo> gruposArr = new ArrayList<>();
		
		for(Grupo g : grupos){
			
			if(g.getGrupoEstudiantes().size()>0){
				if(g.getAnnoAcademico()!=g.getGrupoEstudiantes().get(0).getAnnoAcademico())
					gruposArr.add(g);
			}
			
		}
		
		
		return gruposArr;
	}
	
	
	
	
	
	
	// poara buscar las asginaturas que estan arrastrando en un anno especifico
	public ArrayList<Asignatura> buscarAsignaturasArrastresPorAnno(int anno) {
		
		ArrayList<Asignatura> asignaturasArrastres = new ArrayList<>();
		
		for(Estudiante e : buscarArrastresPorAnno(anno)){
			if(e.buscarAsignaturasArrastres().size()==1)
				if(!asignaturasArrastres.contains(e.buscarAsignaturasArrastres().get(0)))
					asignaturasArrastres.add(e.buscarAsignaturasArrastres().get(0));
			
			if(e.buscarAsignaturasArrastres().size()==2){
				if(!asignaturasArrastres.contains(e.buscarAsignaturasArrastres().get(0)))
					asignaturasArrastres.add(e.buscarAsignaturasArrastres().get(0));
				
				if(!asignaturasArrastres.contains(e.buscarAsignaturasArrastres().get(1)))
					asignaturasArrastres.add(e.buscarAsignaturasArrastres().get(1));
			}	
			
		}
		
		return asignaturasArrastres;
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
	
	
	// 4- PARA BUSCAR en cada año cual es el grupo con menor cantidad de estudiantes
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
	
	
	// 5- para saber los estudiantes que tienen más de 4.5  de promedio en todas las asignaturas de su carrera.
	public ArrayList<Estudiante> buscarEstudiantesOros(){
		ArrayList<Estudiante> oros = new ArrayList<>();
		
		for(Estudiante i : buscarEstudiantes()){
			if(i.calcularPromedio()>=4.5)
				oros.add(i);
		}
		
		return oros;
	}
	
	// 6- para saber los estudiantes sin grupo
	public ArrayList<Estudiante> buscarEstudiantesSinGrupo(){
		ArrayList<Estudiante> sinGrupo = new ArrayList<>();
		
		for(Estudiante e : buscarEstudiantes()){
			int i = 0;
			boolean val = true;
			
			if(e.getAnnoAcademico()==7)
				val = false;
			
			if(!e.verificarArrastreEspecial() && !e.verificarNecesidadGrupo())
				val = false;
			
			while(i<grupos.size() && val){
				if(grupos.get(i).getGrupoEstudiantes().contains(e) && grupos.get(i).getAnnoAcademico()==e.getAnnoAcademico())
					val=false;
				i++;
			}

			if(val)
				sinGrupo.add(e);
			
		}
		
		return sinGrupo;
	}
	
	//para buscar estudiantes que le faltan notas en el primer semetre
	public ArrayList<Estudiante> buscarEstudiantesSinNotasPrimerSemestre(){
		ArrayList<Estudiante> sinNotas1erSem = new ArrayList<>();
		
		
		for(Estudiante i : buscarEstudiantes()){
			if(!i.verificarNotasPrimerSemestre())
				sinNotas1erSem.add(i);
		}
		
		return sinNotas1erSem;
	}
	
	
	
	// para buscar los estudiantes sin grupo de un año dado
	public ArrayList<Estudiante> buscarEstudiantesSinGrupoPorAnno(int anno){
		ArrayList<Estudiante> estudiantes = new ArrayList<>();
		
		for(Estudiante e : buscarEstudiantesSinGrupo()){
			if(e.getAnnoAcademico()==anno)
				estudiantes.add(e);
		}
		
		return estudiantes;
	}
	
	
	//**************************************** fin reportes ******************************************************************
	
	// PARA BUSCAR ESTUDIANTES SUSPENSOS EN ALGUNA ASIGNATURA 
	public ArrayList<Estudiante> buscarEstudiantesSuspensos(){
		ArrayList<Estudiante> suspensos = new ArrayList<>();
		
		for(Estudiante i : buscarEstudiantes()){
			if(i.verificarSuspenso())
				suspensos.add(i);
		}
		
		return suspensos;
	}
	
	// PARA ELIMINAR UNA ASIGNATURA
	public boolean eliminarAsignatura(Asignatura asignatura){
		boolean eliminada = false;
		boolean val = verificarAsignaturaPD(asignatura);
		
		if(!val){
			ArrayList<ControlDocente> borrar = new ArrayList<>();
			eliminada = planEstudio.eliminarAsignatura(asignatura);
			for(Estudiante e : buscarEstudiantes()){
				for(ControlDocente cD : e.getNotas()){
					if(cD.getAsignatura().equals(asignatura)  && cD.getNota1()==2 && cD.getNota2()==2){
						borrar.add(cD);
					}
						
				}
				e.getNotas().removeAll(borrar);
				borrar.clear();
			}
			ArrayList<Grupo> borrargrupo = new ArrayList<>();
			for(Grupo g : grupos){
				if(g instanceof GrupoEspecial){
					if(((GrupoEspecial) g).getAsignatura().equals(asignatura))
						borrargrupo.add(g);
				}
			}
			grupos.removeAll(borrargrupo);
			
			
		}
			
		
		
		return eliminada;
	}
	
	// MODIFICAR ASIGNATURA
	public boolean modificarAsignatura(String nombre, int annoAcademico, int semestre, float horasClases) {
		boolean modificada=false;
		boolean val = verificarAsignaturaPD(planEstudio.buscarAsignatura(nombre));
		
		if(!val){
			planEstudio.buscarAsignatura(nombre).modificarAsignatura(annoAcademico, semestre, horasClases);
			modificada = true;
		}
		
		return modificada;
	}
	
	
	// PARA VERIFICAR SI UNA ASIGNATURA ESTA EN ALGUNA PLANIFICACION DOCENTE
	public boolean verificarAsignaturaPD(Asignatura asignatura){
		
        boolean val = false;
		
		int periodo = asignatura.getAnnoAcademico()-1;
		if(asignatura.getSemestre()==2)
			periodo+=6;
		
		int e = 0;
		while(e<periodos.get(periodo).getPlanificacionesDocentes().size() && !val){
			if(periodos.get(periodo).getPlanificacionesDocentes().get(e).getAsignatura().equals(asignatura))
				val=true;
			e++;
		}
		
		return val;
	}
	
	
	
	
	
	//PARA ELIMINAR UN GRUPO
	/***
	 * 
	 * @param grupo
	 * @return un booleano si se elimino o no
	 */
	public boolean eliminarGrupo(Grupo grupo){
		boolean eliminada = false;
		
		if(!verificarGrupoPD(grupo))
			eliminada = grupos.remove(grupo);
		
		return eliminada;
	}
	
	
	// PARA VERIFICAR SI UN GRUPO ESTA EN ALGUNA PLANIFICACION DOCENTE
	public boolean verificarGrupoPD(Grupo grupo){
		boolean val = false;
		int i = 0;
		
		while(i<periodos.size() && !val){
			if(i==grupo.getAnnoAcademico()-1 || i==grupo.getAnnoAcademico()+5){
				int e = 0;
				while(i<periodos.get(i).getPlanificacionesDocentes().size() && !val){
					if(periodos.get(i).getPlanificacionesDocentes().get(e).getGrupo().equals(grupo))
						val=true;
					e++;
				}
			}
			i++;
		}
		
		return val;
	}
	

	
	// Para que entrando un profesor te devuelva las asignaturas que da
	public ArrayList<Asignatura> buscarAsignaturasPorProfeYSemestre(String id , int semestre){
		ArrayList<Asignatura> asignaturas = new ArrayList<>();
		
		for(Periodo p : periodos){
			for(PlanificacionDocente pD : p.getPlanificacionesDocentes()){
				if(pD.getProfesor().getID().equalsIgnoreCase(id) && pD.getAsignatura().getSemestre()==semestre && !asignaturas.contains(pD.getAsignatura()))
					asignaturas.add(pD.getAsignatura());
			}
		}
		
		return asignaturas;
	}
	
	// Para que entrando una asignatura te devuelva los grupos que la dan
	public ArrayList<Grupo> buscarGruposPorAsignatura(String nombreAsig){
		ArrayList<Grupo> grupos = new ArrayList<>();
		
		for(Periodo p : periodos){
			for(PlanificacionDocente pD : p.getPlanificacionesDocentes()){
				if(pD.getAsignatura().getNombre().equalsIgnoreCase(nombreAsig))
					grupos.add(pD.getGrupo());
			}
		}
		
		return grupos;
	}
	
	// para buscar los grupos por un anno entrante
	public ArrayList<Grupo> buscarGruposPorAnno(int anno){
		ArrayList<Grupo> gruposPorAnno = new ArrayList<>();
		for(Grupo g : grupos){
			if(g.getAnnoAcademico()==anno)
				gruposPorAnno.add(g);
		}
		
		return gruposPorAnno;
	}
	
	// para busacr los grupos por un anno especifico que no estne vacios
	public ArrayList<Grupo> buscarGruposPorAnnoNoVacio(int anno){
		ArrayList<Grupo> gruposPorAnnoNoVacios = new ArrayList<>();
		
		for(Grupo g : buscarGruposPorAnno(anno)){
			if(g.getGrupoEstudiantes().size()>0)
				gruposPorAnnoNoVacios.add(g);
		}
		
		return gruposPorAnnoNoVacios;
	}
	
	
	// para buscar lso grupos que no esten vacios
	public ArrayList<Grupo> buscarGruposNoVacio(){
		ArrayList<Grupo> gruposNoVacios = new ArrayList<>();
		
		for(Grupo g : grupos){
			if(g.getGrupoEstudiantes().size()>0)
				gruposNoVacios.add(g);
		}
		
		return gruposNoVacios;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}