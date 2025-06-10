package main.java.servicio;

import java.util.List;
import java.util.Random;

import main.java.dto.CaminoDTO;
import main.java.dto.CeldaDTO;
import main.java.dto.GrillaDTO;
import main.java.interfaz.IBusquedaCamino;
import main.java.interfaz.IGeneradorGrilla;
import main.java.modelo.Camino;
import main.java.modelo.Celda;
import main.java.modelo.Grilla;
import main.java.modelo.ResultadoBusqueda;
import main.java.servicio.generadores.GeneradorGrillaAleatoria;

public class ServicioGrilla {

	public Grilla cargarGrillaAleatoria() {
		Random random = new Random();
		IGeneradorGrilla generador = new GeneradorGrillaAleatoria(random);
		int filas = random.nextInt(1, 15);
		int columnas = filas + 1;
		return generador.generar(filas, columnas);
	}

	public Grilla cargarGrillaDesdeArchivo() {
		return ConsumoGrilla.cargarGrillaDesdeJson("src/main/recursos/grilla_ejemplo_1.json");
	}

	public ResultadoBusqueda ejecutarAlgoritmo(IBusquedaCamino algoritmo, Grilla grilla) {
		return algoritmo.buscar(grilla);
	}

	public GrillaDTO obtenerGrillaDTO(Grilla grilla) {
		Celda[][] celdas = grilla.obtenerCeldas();
		CeldaDTO[][] celdasDTO = new CeldaDTO[grilla.obtenerFilas()][grilla.obtenerColumnas()];
		for (int i = 0; i < grilla.obtenerFilas(); i++) {
			for (int j = 0; j < grilla.obtenerColumnas(); j++) {
				Celda c = celdas[i][j];
				celdasDTO[i][j] = new CeldaDTO(c.obtenerFila(), c.obtenerColumna(), c.obtenerCarga());
			}
		}
		return new GrillaDTO(celdasDTO);
	}

	public CaminoDTO obtenerCaminoDTO(Camino camino) {
		List<Celda> pasos = camino.obtenerPasos();
		
		List<CeldaDTO> pasosDTO = pasos.stream().map(p -> new CeldaDTO(
				p.obtenerFila(),
				p.obtenerColumna(),
				p.obtenerCarga()))
				.toList();
		
		int cargaTotal = pasos.stream()
				.mapToInt(p -> p.obtenerCarga())
				.sum();
		
		return new CaminoDTO(pasosDTO, cargaTotal);
	}
}
