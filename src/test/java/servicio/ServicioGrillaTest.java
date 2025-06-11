package test.java.servicio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import main.java.dto.CaminoDTO;
import main.java.dto.CeldaDTO;
import main.java.dto.GrillaDTO;
import main.java.modelo.Camino;
import main.java.modelo.Celda;
import main.java.modelo.Grilla;
import main.java.servicio.ServicioGrilla;

public class ServicioGrillaTest {

	private ServicioGrilla servicio;

	@Before
	public void setUp() {
		servicio = new ServicioGrilla();
	}

	@Test
	public void testCargarGrillaAleatoria() {
		Grilla grilla = servicio.cargarGrillaAleatoria();
		assertNotNull(grilla);
		int filas = grilla.obtenerFilas();
		int columnas = grilla.obtenerColumnas();
		// columnas debe ser filas + 1
		assertEquals(filas + 1, columnas);
	}

	@Test
	public void testCargarGrillaDesdeArchivo() {
		Grilla grilla = servicio.cargarGrillaDesdeArchivo();
		assertNotNull(grilla);
		assert (grilla.obtenerFilas() > 0);
		assert (grilla.obtenerColumnas() > 0);
	}

	@Test
	public void testObtenerGrillaDTO() {
		Grilla grilla = servicio.cargarGrillaAleatoria();
		GrillaDTO grillaDTO = servicio.obtenerGrillaDTO(grilla);
		assertNotNull(grillaDTO);
		CeldaDTO[][] celdasDTO = grillaDTO.obtenerCeldas();
		assertEquals(grilla.obtenerFilas(), celdasDTO.length);
		assertEquals(grilla.obtenerColumnas(), celdasDTO[0].length);
	}

	@Test
	public void testObtenerCaminoDTO() {
		Celda celda1 = new Celda(0, 0, 1);
		Celda celda2 = new Celda(0, 1, -1);
		Celda celda3 = new Celda(0, 2, 1);

		Camino camino = new Camino();
		camino.agregarPaso(celda1);
		camino.agregarPaso(celda2);
		camino.agregarPaso(celda3);

		CaminoDTO caminoDTO = servicio.obtenerCaminoDTO(camino);

		assertNotNull(caminoDTO);
		List<CeldaDTO> pasosDTO = caminoDTO.obtenerPasos();
		assertEquals(3, pasosDTO.size());

		assertEquals(0, pasosDTO.get(0).obtenerFila());
		assertEquals(0, pasosDTO.get(0).obtenerColumna());
		assertEquals(1, pasosDTO.get(0).obtenerCarga());

		assertEquals(0, pasosDTO.get(1).obtenerFila());
		assertEquals(1, pasosDTO.get(1).obtenerColumna());
		assertEquals(-1, pasosDTO.get(1).obtenerCarga());

		assertEquals(0, pasosDTO.get(2).obtenerFila());
		assertEquals(2, pasosDTO.get(2).obtenerColumna());
		assertEquals(1, pasosDTO.get(2).obtenerCarga());

		int cargaTotalEsperada = 1 + (-1) + 1;
		assertEquals(cargaTotalEsperada, caminoDTO.obtenerCargaTotal());
	}
}
