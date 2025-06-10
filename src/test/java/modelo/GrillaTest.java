package test.java.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import main.java.modelo.Celda;
import main.java.modelo.Grilla;

public class GrillaTest {

	private Grilla grilla;
	private int[][] matrizBase;

	@Before
	public void setUp() {
		matrizBase = new int[][] {
			{1, -1, -1, -1},
			{-1, 1, 1, -1},
			{1, 1, 1, -1}
			};
		grilla = new Grilla(matrizBase);
	}

	@Test
	public void testObtenerFilas() {
		assertEquals(3, grilla.obtenerFilas());
	}

	@Test
	public void testObtenerColumnas() {
		assertEquals(4, grilla.obtenerColumnas());
	}

	@Test
	public void testObtenerCeldaValida() {
		Celda celda = grilla.obtenerCelda(0, 1);
		assertNotNull(celda);
		assertEquals(0, celda.obtenerFila());
		assertEquals(1, celda.obtenerColumna());
		assertEquals(-1, celda.obtenerCarga());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testObtenerCeldaFueraDeRangoFila() {
		grilla.obtenerCelda(5, 1); // Fila fuera de rango
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testObtenerCeldaFueraDeRangoColumna() {
		grilla.obtenerCelda(1, 5); // Columna fuera de rango
	}

	@Test
	public void testObtenerCeldasDevuelveCopia() {
		Celda[][] copia = grilla.obtenerCeldas();
		assertEquals(3, copia.length);
		assertEquals(4, copia[0].length);
		assertEquals(1, copia[0][0].obtenerCarga());
		assertEquals(-1, copia[0][1].obtenerCarga());

		// Verificamos que es una copia del arreglo, no el mismo objeto
		Celda[][] otraCopia = grilla.obtenerCeldas();
		assertFalse(copia == otraCopia);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMatrizNullLanzaExcepcion() {
		new Grilla(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMatrizVaciaLanzaExcepcion() {
		new Grilla(new int[0][0]);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMatrizFilaVaciaLanzaExcepcion() {
		new Grilla(new int[0][1]);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMatrizColumnaVaciaExcepcion() {
		new Grilla(new int[1][0]);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMatrizConFilasDeDistintoTamanoLanzaExcepcion() {
		int[][] matrizIrregular = { { 1, -1 }, { 1 } };
		new Grilla(matrizIrregular);
	}
}
