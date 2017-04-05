package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.model.Administrador;
import es.uniovi.asw.model.Categoria;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.Comentario;
import es.uniovi.asw.model.Sugerencia;
import es.uniovi.asw.model.types.VotosComentariosKey;
import es.uniovi.asw.model.types.VotosSugerenciasKey;

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
	
	@Test
	public void categoria(){
		Date d = new Date();
		Date d2 = new Date();
		List<String> pal = new ArrayList<String>();
		pal.add("holi");
		Categoria cat = new Categoria("catprueba", d, d2, 3, pal);
		

		assertEquals(cat.getNombre(),"catprueba");
		assertEquals(cat.getFechaInicio(),d);
		assertEquals(cat.getFechaFin(),d2);
		assertEquals(cat.getMinimoVotos(),3);
		assertEquals(cat.getPalabrasNoPermitidas().size(),pal.size());

		Categoria cat2 = new Categoria("qweweq", d, d2, 3, pal);
		assertEquals(cat,cat2); //Son iguales porque tienen el mismo ID y es la bd la que lo genera
			
		
	}
	
	@Test
	public void votes(){
		Date d = new Date();
		Date d2 = new Date();
		List<String> pal = new ArrayList<String>();
		pal.add("holi");
		Categoria cat = new Categoria("catprueba", d, d2, 3, pal);
		Sugerencia s = new Sugerencia(c, "test", "testx2", cat);
		
		VotosSugerenciasKey vs = new VotosSugerenciasKey(c.getId(),s.getId());
		VotosSugerenciasKey vs2 = new VotosSugerenciasKey(c.getId(),s.getId());
		assertEquals(vs,vs2);
		assertEquals(vs.hashCode(),vs2.hashCode());
		
		Comentario com = new Comentario(c,s,"hola");
		
		VotosComentariosKey vc = new VotosComentariosKey(c.getId(),com.getId());
		VotosComentariosKey vc2 = new VotosComentariosKey(c.getId(),com.getId());
		assertEquals(vc,vc2);
		assertEquals(vc.hashCode(),vc2.hashCode());
	}
	
	@Test
	public void admin(){
		Administrador admin = new Administrador("test","test");
		Administrador admin2 = new Administrador("twewst","tqwewqest");
		
		assertEquals(admin,admin2); //Son iguales porque tienen el mismo ID y es la bd la que lo genera
		
		admin.setUsuario("123");
		assertEquals("123",admin.getUsuario());
		
		admin.setPassword("123");
		assertEquals("123",admin.getUsuario());
		
		assertEquals(admin.hashCode(),admin2.hashCode());
	}
}
