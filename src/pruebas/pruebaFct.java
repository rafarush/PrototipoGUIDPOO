package pruebas;

import static org.junit.Assert.*;
import logica.Clases.Fct;
import logica.Clases.Persona;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class pruebaFct {

	
	
	
	@Before
	public void setUp() throws Exception {
		Fct.getInstance().crearPersona("84012345678", "Juan Carlos Pérez Gómez", "Doctor", "Titular", "Cujae", "MINED", "Calle 1 entre 2 y 4");
	}

	@After
	public void tearDown() throws Exception {
		Fct.eliminarInstance();
	}

	
	
	@Test
	public void testBuscarPesonaCamino1() {
		assertNull("Devolvio un objeto", Fct.getInstance().buscarPersona("04111068006"));
	}
	
	@Test
	public void testBuscarPesonaCamino2() {
		Fct.getInstance().crearPersona("04111068006", "Pepe Reina Peña","Secretaria", "Calle 1 entre 16 y 18");
		assertEquals("04111068006", Fct.getInstance().buscarPersona("04111068006").getID());
	}
	
	@Test
	public void testBuscarPesonaCamino3() {
		Fct.getInstance().crearPersona("04111068006", "Pepe Reina Peña","Secretaria", "Calle 1 entre 16 y 18");
		Fct.getInstance().crearPersona("04111168006", "Luis Rico Hermoso","Biblioteca", "Calle 3 entre 16 y 18");
		
		assertEquals("04111168006", Fct.getInstance().buscarPersona("04111168006").getID());
	}
	
	@Test
	public void testBuscarPesonaCamino4() {
		Fct.getInstance().crearPersona("04111168006", "Luis Rico Hermoso","Biblioteca", "Calle 3 entre 16 y 18");
		Fct.getInstance().crearPersona("04111068006", "Pepe Reina Peña","Secretaria", "Calle 1 entre 16 y 18");
		
		assertNull("Devolvio un objeto", Fct.getInstance().buscarPersona("04111868006"));
	}
	
	@Test
	public void testEliminarDelCD1() {
		
		assertFalse(Fct.getInstance().eliminarDelCD(Fct.getInstance().buscarUnProfesor("84012345678")));
	}
	
	
	@Test
	public void testEliminarDelCD2() {
		Fct.getInstance().crearPersona("83023456789", "José Antonio Rodríguez Sánchez", "Máster", "Instructor", "CineSoft", "PCC", "Calle 2 entre 1 y 3");
		Fct.getInstance().agregarCargoConsejoDireccion(Fct.getInstance().buscarUnProfesor("83023456789"), "Director");
		
		assertTrue(Fct.getInstance().eliminarDelCD(Fct.getInstance().buscarUnProfesor("83023456789")));
	}
	
	
	
	
	@Test
	public void test() {
	}
	
	
}
