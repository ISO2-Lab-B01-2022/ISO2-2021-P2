package com.mslc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PrincipalTest {

	@Test
	public void testMain() throws Exception {
		assertEquals(0, Principal.main(null));
	}

	// nivel_pandemia = 0
	@Test
	public void testMain_N0_NV() throws Exception {
		boolean ex = false;
		try {
			Persona p = new Persona("Juan", 18, false, false, false, true, false);
			Principal.principal(50, 100, 10, 20, p);
		} catch (NoPosibilidadDeViajarException npve) {
			ex = true;
		}
		assertTrue(ex);
	}

	@Test
	public void testMain_N0_NA() throws Exception {
		boolean ex = false;
		try {
			Persona p = new Persona("Juan", 18, true, false, false, true, false);
			Principal.principal(50, 100, 50, 50, p);
		} catch (NoHayPlazasDisponiblesException npde) {
			ex = true;
		}
		assertTrue(ex);
	}

	@Test
	public void testMain_N0_J() throws Exception {
		Persona p = new Persona("Juan", 18, true, false, false, true, false);
		assertEquals(20 * (1 - 0.6), Principal.principal(50, 100, 10, 20, p), 0.001);
	}

	@Test
	public void testMain_N0_M() throws Exception {
		Persona p = new Persona("Juan", 70, true, false, false, true, false);
		assertEquals(20 * (1 - 0.8), Principal.principal(50, 100, 10, 20, p), 0.001);
	}

	// nivel_pandemia = 1

	@Test
	public void testMain_N1_J() throws Exception {
		Persona p = new Persona("Juan", 18, true, false, false, true, false);
		assertEquals(20 * (1 - 0.3), Principal.principal(150, 100, 10, 20, p), 0.001);
	}

	@Test
	public void testMain_N1_M() throws Exception {
		Persona p = new Persona("Juan", 70, true, false, false, true, false);
		assertEquals(20 * (1 - 0.5), Principal.principal(150, 100, 10, 20, p), 0.001);
	}

	// nivel_pandemia = 2

	@Test
	public void testMain_N2_M() throws Exception {
		Persona p = new Persona("Juan", 70, true, false, false, true, false);
		assertEquals(20 * 1.2, Principal.principal(250, 100, 10, 20, p), 0.001);
	}

	// nivel_pandemia = 3
	@Test
	public void testMain_N3_NAP() throws Exception {
		boolean ex = false;
		try {
			Persona p = new Persona("Juan", 18, true, false, false, true, true);
			Principal.principal(350, 100, 32, 6, p);
		} catch (NoHayPlazasDisponiblesException npde) {
			ex = true;
		}
		assertTrue(ex);
	}

	@Test
	public void testMain_N3_NANP() throws Exception {
		boolean ex = false;
		try {
			Persona p = new Persona("Juan", 18, true, false, false, true, false);
			Principal.principal(350, 100, 30, 8, p);
		} catch (NoHayPlazasDisponiblesException npde) {
			ex = true;
		}
		assertTrue(ex);
	}

	@Test
	public void testMain_N3_J() throws Exception {
		Persona p = new Persona("Juan", 18, true, false, false, true, false);
		assertEquals(20 * 1.2, Principal.principal(350, 100, 20, 6, p), 0.001);
	}

	@Test
	public void testMain_N3_M() throws Exception {
		Persona p = new Persona("Juan", 70, true, false, false, true, false);
		assertEquals(20 * 1.5, Principal.principal(350, 100, 20, 6, p), 0.001);
	}

	// nivel_pandemia = 4
	@Test
	public void testMain_N4_JP() throws Exception {
		Persona p = new Persona("Juan", 18, true, false, false, true, true);
		assertEquals(20 * 1.5, Principal.principal(550, 100, 20, 2, p), 0.001);
	}

	@Test
	public void testMain_N4_J() throws Exception {
		Persona p = new Persona("Juan", 18, true, false, false, true, false);
		assertEquals(20 * 1.5, Principal.principal(550, 100, 20, 2, p), 0.001);
	}

	@Test
	public void testMain_N4_M() throws Exception {
		boolean ex = false;
		try {
			Persona p = new Persona("Juan", 70, true, false, false, true, false);
			Principal.principal(550, 100, 20, 2, p);
		} catch (NoPosibilidadDeViajarException npve) {
			ex = true;
		}
		assertTrue(ex);
	}
}
