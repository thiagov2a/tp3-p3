package main.java.servicio;

import java.util.List;
import java.util.Random;

import main.java.dto.CaminoDTO;
import main.java.dto.CeldaDTO;
import main.java.dto.GrillaDTO;
import main.java.interfaz.IBusquedaCamino;
import main.java.modelo.Camino;
import main.java.modelo.Celda;
import main.java.modelo.Grilla;
import main.java.modelo.ResultadoBusqueda;

public class ServicioGrilla {

	private Random random;
	private GeneradorGrilla generador;

	public ServicioGrilla() {
		this.random = new Random();
		this.generador = new GeneradorGrilla(random);
	}

	public Grilla cargarGrillaAleatoria() {
		int filas = random.nextInt(2, 10);
		int columnas = filas + 1;
		return generador.generar(filas, columnas);
	}

	public Grilla cargarGrillaDesdeArchivo() {
		int numeroArchivo = random.nextInt(1, 10);
		String ubicacionArchivo = String.format("src/main/recursos/grilla_ejemplo_%d.json", numeroArchivo);
		return ConsumoGrilla.cargarGrillaDesdeJson(ubicacionArchivo);
	}

	public ResultadoBusqueda ejecutarAlgoritmo(IBusquedaCamino algoritmo, Grilla grilla) {
		return algoritmo.buscar(grilla);
	}

	public GrillaDTO obtenerGrillaDTO(Grilla grilla) {
		Celda[][] celdas = grilla.obtenerCeldas();
		CeldaDTO[][] celdasDTO = new CeldaDTO[grilla.obtenerFilas()][grilla.obtenerColumnas()];
		
		for (int i = 0; i < grilla.obtenerFilas(); i++) {
			for (int j = 0; j < grilla.obtenerColumnas(); j++) {
				celdasDTO[i][j] = new CeldaDTO(
						celdas[i][j].obtenerFila(),
						celdas[i][j].obtenerColumna(),
						celdas[i][j].obtenerCarga());
			}
		}
		return new GrillaDTO(celdasDTO);
	}

	public CaminoDTO obtenerCaminoDTO(Camino camino) {
		List<Celda> pasos = camino.obtenerPasos();
		List<CeldaDTO> pasosDTO = obtenesPasosDTO(pasos);
		int cargaTotal = obtenerCargaTotal(pasos);
		return new CaminoDTO(pasosDTO, cargaTotal);
	}
	
	private List<CeldaDTO> obtenesPasosDTO(List<Celda> pasos) {
		return pasos.stream()
				.map(p -> new CeldaDTO(
						p.obtenerFila(),
						p.obtenerColumna(),
						p.obtenerCarga()))
				.toList();
	}
	
	private int obtenerCargaTotal(List<Celda> pasos) {
		return pasos.stream()
				.mapToInt(p -> p.obtenerCarga())
				.sum();
	}
}
