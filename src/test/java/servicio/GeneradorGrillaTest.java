package test.java.servicio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import main.java.algoritmo.BusquedaConPodaInteligente;
import main.java.interfaz.IBusquedaCamino;
import main.java.modelo.Grilla;
import main.java.modelo.ResultadoBusqueda;
import main.java.servicio.GeneradorGrilla;

public class GeneradorGrillaTest {

	private GeneradorGrilla generador;
	private Random random;

	@Before
	public void setUp() {
		random = new Random();
		generador = new GeneradorGrilla(random);
	}

	@Test
	public void testGenerarGrillaDimensionesCorrectas() {
		int filas = 5;
		int columnas = 6;
		Grilla grilla = generador.generar(filas, columnas);
		assertEquals(filas, grilla.obtenerFilas());
		assertEquals(columnas, grilla.obtenerColumnas());
	}

	@Test
	public void testGenerarGrillaTieneCaminoBalanceado() {
		int filas = random.nextInt(2, 10);
		int columnas = filas + 1;
		Grilla grilla = generador.generar(filas, columnas);

		IBusquedaCamino buscador = new BusquedaConPodaInteligente();
		ResultadoBusqueda resultado = buscador.buscar(grilla);

		assertTrue(resultado.existe());
	}
}
