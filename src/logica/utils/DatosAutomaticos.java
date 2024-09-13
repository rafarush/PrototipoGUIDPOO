package logica.utils;

import logica.Clases.Fct;

public  class DatosAutomaticos {

	
	public static void datosAutomaticos(){
		
		Fct.getInstance().crearPersona("84012345678", "Juan Carlos Pérez Gómez", "Doctor", "Titular", "Cujae", "MINED", "Calle 1 entre 2 y 4");
		Fct.getInstance().crearPersona("83023456789", "José Antonio Rodríguez Sánchez", "Máster", "Instructor", "CineSoft", "PCC", "Calle 2 entre 1 y 3");
		Fct.getInstance().crearPersona("82034567890", "Luis Miguel Hernández Ruiz", "Ninguna", "Asistente", "ETECSA", "MINED", "Calle 3 entre 2 y 4");
		Fct.getInstance().crearPersona("80056789012", "María Fernanda López Martínez", "Doctor", "Titular", "CIME", "FMC", "Calle 4 entre 5 y 7");
		
		//estudiantes
		Fct.getInstance().crearPersona("03012345678", "Juan Carlos Pérez Gómez", 1, "CIME", "MINED", "Calle 30 entre 13 y 15");
		//Fct.getInstance().crearPersona("04023456789", "José Antonio Rodríguez Sánchez", 1, "ETECSA", "PCC", "Calle 21 entre 34 y 324");
		//Fct.getInstance().crearPersona("05034567890", "Luis Miguel Hernández Ruiz", 1, "CIME", "MINED", "Calle 25 entre 12 y 14");
		//Fct.getInstance().crearPersona("04045678901", "María Fernanda López Martínez", 1, "ETECSA", "FMC", "Calle 7 entre 12 y 14");
		
		//grupos
		Fct.getInstance().crearGrupo("Grupo 1.1", 1);
		Fct.getInstance().buscarGrupo("Grupo 1.1").insertarAGrupoEstudiante(Fct.getInstance().buscarUnEstudiante("03012345678"));
		//Fct.getInstance().buscarGrupo("Grupo 1.1").insertarAGrupoEstudiante(Fct.getInstance().buscarUnEstudiante("04023456789"));
		//Fct.getInstance().buscarGrupo("Grupo 1.1").insertarAGrupoEstudiante(Fct.getInstance().buscarUnEstudiante("05034567890"));
		//Fct.getInstance().buscarGrupo("Grupo 1.1").insertarAGrupoEstudiante(Fct.getInstance().buscarUnEstudiante("04045678901"));
		
		
		//plan docente
		Fct.getInstance().crearPersona("0311568003", "Juan Peña Pina", "Secretaria", "Calle 20 entre 16 y 18");
		Fct.getInstance().crearPersona("0311568004", "Miguel Reina Monte", "Biblioteca", "Calle 19 entre 18 y 20");
		
		
		// Asignatura
		try{
			Fct.getInstance().getPlanEstudio().crearAsignatura("Calculo I",1 ,1 ,50 );
			//Fct.getInstance().getPlanEstudio().crearAsignatura("IP",1 ,1 ,50 );
			Fct.getInstance().getPeriodos().get(0).crearPlanificacionDocente(Fct.getInstance().buscarUnProfesor("84012345678"), Fct.getInstance().getPlanEstudio().buscarAsignatura("Calculo I"), Fct.getInstance().buscarGrupo("Grupo 1.1"));
			//Fct.getInstance().getPeriodos().get(0).crearPlanificacionDocente(Fct.getInstance().buscarUnProfesor("84012345678"), Fct.getInstance().getPlanEstudio().buscarAsignatura("IP"), Fct.getInstance().buscarGrupo("Grupo 1.1"));
			
			Fct.getInstance().getPlanEstudio().crearAsignatura("Calculo II",1 ,2 ,46 );
			Fct.getInstance().getPeriodos().get(6).crearPlanificacionDocente(Fct.getInstance().buscarUnProfesor("84012345678"), Fct.getInstance().getPlanEstudio().buscarAsignatura("Calculo II"), Fct.getInstance().buscarGrupo("Grupo 1.1"));
			
		}catch(ProcesoNoPermitidoException e){
			System.out.println("CAPTURADA");
		}
		
		Fct.getInstance().getPlanEstudio().crearAsignatura("Calculo III",2 ,1 ,52 );
		Fct.getInstance().getPlanEstudio().crearAsignatura("RA",2 ,2 ,60 );
		Fct.getInstance().getPlanEstudio().crearAsignatura("Red PC",3 ,1 ,26 );
		Fct.getInstance().getPlanEstudio().crearAsignatura("WEB",3 ,2 , 38);
		Fct.getInstance().getPlanEstudio().crearAsignatura("WEB II",4 ,1 ,48 );
		Fct.getInstance().getPlanEstudio().crearAsignatura("WEB III",4 ,2 ,50 );
		Fct.getInstance().getPlanEstudio().crearAsignatura("Inteligencia Artificial",5 ,1 ,20 );
		Fct.getInstance().getPlanEstudio().crearAsignatura("Inteligencia Artificial II",5 ,2 ,30 );
		Fct.getInstance().getPlanEstudio().crearAsignatura("Metodología de la invstigación	",6 ,1 ,38 );
		Fct.getInstance().getPlanEstudio().crearAsignatura("Seguridad Nascional",6 ,2 ,40 );
		
		
		
	} 
	
}
