package pruebas;

import static org.junit.Assert.*;

import java.util.ArrayList;

import logica.Clases.ControlDocente;
import logica.Clases.Fct;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class pruebaEstudiante {

	@Before
	public void setUp() throws Exception {
		Fct.getInstance().crearPersona("03010345678", "Juan Carlos Pérez Gómez", 1, "CIME", "MINED", "Calle 30 entre 13 y 15");
		
		ArrayList<ControlDocente> notas = new ArrayList<>();
		
		
		Fct.getInstance().getPlanEstudio().crearAsignatura("Calculo I",1 ,1 ,50 );
		Fct.getInstance().getPlanEstudio().crearAsignatura("IP",1 ,1 ,50 );
		Fct.getInstance().getPlanEstudio().crearAsignatura("Calculo II",1 ,2 ,46 );
		
		ControlDocente n1 = new ControlDocente(Fct.getInstance().buscarUnEstudiante("03010345678"), Fct.getInstance().getPlanEstudio().buscarAsignatura("Calculo I"));
		ControlDocente n2 = new ControlDocente(Fct.getInstance().buscarUnEstudiante("03010345678"), Fct.getInstance().getPlanEstudio().buscarAsignatura("IP"));
		ControlDocente n3 = new ControlDocente(Fct.getInstance().buscarUnEstudiante("03010345678"), Fct.getInstance().getPlanEstudio().buscarAsignatura("Calculo II"));
		
		n1.setNota1(5);
		n1.setNota2(5);
		
		n2.setNota1(5);
		n2.setNota2(5);
		
		n3.setNota1(5);
		n3.setNota2(5);
		
		notas.add(n1);
		notas.add(n2);
		notas.add(n3);
		
		
		Fct.getInstance().buscarUnEstudiante("03010345678").setNotas(notas);
	}

	@After
	public void tearDown() throws Exception {
		Fct.eliminarInstance();
	}

	@Test
	public void testCalcularPromedio1() {

		assertEquals("5.0", String.valueOf(Fct.getInstance().buscarUnEstudiante("03010345678").calcularPromedio()));
		
	}
	
	
	@Test
	public void testCalcularPromedio2() {

		assertNotEquals("4.0", String.valueOf(Fct.getInstance().buscarUnEstudiante("03010345678").calcularPromedio()));
		
	}
	
	
	@Test
	public void testVerificarNotas1() {
		assertTrue(Fct.getInstance().buscarUnEstudiante("03010345678").verificarNotas());
	}
	
	
	@Test
	public void testVerificarNotas2() {
		
		Fct.getInstance().buscarUnEstudiante("03010345678").getNotas().get(0).setNota1(0);
		
		assertFalse(Fct.getInstance().buscarUnEstudiante("03010345678").verificarNotas());
	}
	
	
	@Test
	public void testVerificarSuspenso1() {
		Fct.getInstance().buscarUnEstudiante("03010345678").getNotas().get(0).setNota1(2);
		Fct.getInstance().buscarUnEstudiante("03010345678").getNotas().get(1).setNota1(2);
		
		Fct.getInstance().buscarUnEstudiante("03010345678").getNotas().get(0).setNota2(2);
		Fct.getInstance().buscarUnEstudiante("03010345678").getNotas().get(1).setNota2(2);
		
		assertTrue(Fct.getInstance().buscarUnEstudiante("03010345678").verificarSuspenso());
	}
	
	
	@Test
	public void testVerificarSuspenso2() {
		assertFalse(Fct.getInstance().buscarUnEstudiante("03010345678").verificarSuspenso());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void test() {
	}

}
