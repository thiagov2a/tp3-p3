package test.java.algoritmo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import main.java.algoritmo.BusquedaConPodaInteligente;
import main.java.interfaz.IBusquedaCamino;
import main.java.modelo.Grilla;
import main.java.modelo.ResultadoBusqueda;

public class BusquedaConPodaInteligenteTest {

	private IBusquedaCamino buscador;

	@Before
	public void setUp() {
		buscador = new BusquedaConPodaInteligente();
	}

	@Test
	public void testCaminoBalanceadoExiste() {
		int[][] matriz = {
			{ 1, -1, 1 },
			{ -1, 1, -1 }
		};
		Grilla grilla = new Grilla(matriz);
		ResultadoBusqueda resultado = buscador.buscar(grilla);

		assertTrue(resultado.existe());
		assertNotNull(resultado.obtenerCamino());
		assertTrue(resultado.obtenerCamino().estaBalanceado());
		assertTrue(resultado.obtenerTiempoEjecucion() >= 0);
		assertTrue(resultado.obtenerCaminosExplorados() > 0);
	}

	@Test
	public void testCaminoBalanceadoNoExiste() {
		int[][] matriz = {
			{ 1, 1 },
			{ 1, 1 },
			{ 1, 1 }
		};
		Grilla grilla = new Grilla(matriz);
		ResultadoBusqueda resultado = buscador.buscar(grilla);

		assertFalse(resultado.existe());
		assertNull(resultado.obtenerCamino());
		assertTrue(resultado.obtenerTiempoEjecucion() >= 0);
		assertTrue(resultado.obtenerCaminosExplorados() > 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGrillaNulaLanzaExcepcion() {
		buscador.buscar(null);
	}
}
