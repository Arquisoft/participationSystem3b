package test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.model.Citizen;

public class ModelTest {

	private Citizen c;
	@Before
	public void iniziar(){ 
		c=new Citizen("Juan",  "Torres Pardo", "juan@example.com", new Date(),"C/ Lo que sea",
				"Rumania", "90500084Y","adada", "12345");
	}
	
	@Test
	public void atributos(){
		//Comprobar que todos los atributos se han introducido correctamente.
		assertEquals(c.getNombre(), "Juan");
		assertEquals(c.getApellidos(), "Torres Pardo");
		assertEquals(c.getEmail(), "juan@example.com");
		assertEquals(c.getDireccion(), "C/ Lo que sea");
		assertEquals(c.getNacionalidad(), "Rumania");
		assertEquals(c.getDNI(), "90500084Y");
		assertEquals(c.getUsuario(), "adada");
		assertEquals(c.getPassword(), "12345");
		//Comprobar que el metodo equals de la clase funciona correctamente
		assertEquals(c,new Citizen("Juan",  "Torres Pardo", "juan@example.com", new Date(),"C/ Lo que sea",
				"Rumania", "90500084Y","adada", "12345"));
		//Cambiar todos los datos excepto el DNI para comprobar que sigue siendo el mismo usuario a pesar del cambio de los datos
		c.setNombre("Luis");
		c.setApellidos("Ramon Gonzalez");
		c.setDireccion("C/ sea");
		c.setEmail("luis@example.com");
		c.setNacionalidad("España");
		c.setPassword("qwery");
		c.setUsuario("lalala");
		assertEquals(c.getNombre(), "Luis");
		assertEquals(c.getApellidos(), "Ramon Gonzalez");
		assertEquals(c.getEmail(), "luis@example.com");
		assertEquals(c.getDireccion(), "C/ sea");
		assertEquals(c.getNacionalidad(), "España");
		assertEquals(c.getUsuario(), "lalala");
		assertEquals(c.getPassword(), "qwery");
		assertTrue(c.equals(new Citizen("Juan",  "Torres Pardo", "juan@example.com", new Date(),"C/ Lo que sea",
				"Rumania", "90500084Y","adada", "12345")));
	}
}
