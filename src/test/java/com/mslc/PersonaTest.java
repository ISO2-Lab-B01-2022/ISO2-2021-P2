package com.mslc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PersonaTest {
	
	private Persona p;
	
	@Before
	public void setUp() throws Exception {
		p = new Persona("Juan", 18, true, false, false, true, false);
	}
	
	@Test
	public void testGetNombre() throws Exception {
		assertEquals("Juan", p.getNombre());
	}
	@Test
	public void testGetEdad() throws Exception {
		assertEquals(18, p.getEdad());
	}
	@Test
	public void testIsSano() throws Exception {
		assertTrue(p.isSano());
	}
	@Test
	public void testIsContacto() throws Exception {
		assertFalse(p.isContacto());
	}
	@Test
	public void testIsSintomas() throws Exception {
		assertFalse(p.isSintomas());
	}
	@Test
	public void testIsPasaporte_COVID() throws Exception {
		assertTrue(p.isPasaporte_COVID());
	}
	@Test
	public void testIsProfesional() throws Exception {
		assertFalse(p.isProfesional());
	}
}
