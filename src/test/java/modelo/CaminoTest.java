package test.java.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import main.java.modelo.Camino;
import main.java.modelo.Celda;

public class CaminoTest {

	private Celda celdaPositiva;
	private Celda celdaNegativa;

	@Before
	public void setUp() {
		celdaPositiva = new Celda(0, 0, 1);
		celdaNegativa = new Celda(0, 1, -1);
	}

	@Test
	public void testAgregarPasoActualizaCargaYLongitud() {
		Camino camino = new Camino();
		assertEquals(0, camino.obtenerLongitud());
		assertEquals(0, camino.obtenerCargaTotal());

		camino.agregarPaso(celdaPositiva);
		assertEquals(1, camino.obtenerLongitud());
		assertEquals(1, camino.obtenerCargaTotal());

		camino.agregarPaso(celdaNegativa);
		assertEquals(2, camino.obtenerLongitud());
		assertEquals(0, camino.obtenerCargaTotal());
	}

	@Test
	public void testRemoverUltimoPasoActualizaCargaYLongitud() {
		Camino camino = new Camino();
		camino.agregarPaso(celdaPositiva);
		camino.agregarPaso(celdaNegativa);

		camino.removerUltimoPaso();
		assertEquals(1, camino.obtenerLongitud());
		assertEquals(1, camino.obtenerCargaTotal());

		camino.removerUltimoPaso();
		assertEquals(0, camino.obtenerLongitud());
		assertEquals(0, camino.obtenerCargaTotal());

		// Sacar el último paso cuando no hay ninguno no debe cambiar nada
		camino.removerUltimoPaso();
		assertEquals(0, camino.obtenerLongitud());
		assertEquals(0, camino.obtenerCargaTotal());
	}

	@Test
	public void testEstaBalanceado() {
		Camino camino = new Camino();
		assertTrue(camino.estaBalanceado());

		camino.agregarPaso(celdaPositiva);
		assertFalse(camino.estaBalanceado());

		camino.agregarPaso(celdaNegativa);
		assertTrue(camino.estaBalanceado());
	}

	// Verifica que la lista que devuelve obtenerPasos() sea inmutable y lance excepción al intentar modificarla
	@Test(expected = UnsupportedOperationException.class)
	public void testObtenerPasosEsInmutable() {
		Camino camino = new Camino();
		camino.agregarPaso(celdaPositiva);
		camino.obtenerPasos().add(celdaNegativa);
	}

	@Test
	public void testConstructorCopiaRealizaCopiaIndependiente() {
		Camino original = new Camino();
		original.agregarPaso(celdaPositiva);

		Camino copia = new Camino(original);

		// Modificar el camino original no tiene que afectar a la copia
		original.agregarPaso(celdaNegativa);

		assertEquals(1, copia.obtenerLongitud());
		assertEquals(1, copia.obtenerCargaTotal());

		assertEquals(2, original.obtenerLongitud());
		assertEquals(0, original.obtenerCargaTotal());
	}
}
