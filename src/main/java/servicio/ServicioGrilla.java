package main.java.servicio;

import java.util.Random;

import main.java.algoritmo.BusquedaConPodaInteligente;
import main.java.dto.CeldaDTO;
import main.java.dto.GrillaDTO;
import main.java.interfaz.IBusquedaCamino;
import main.java.interfaz.IGeneradorGrilla;
import main.java.modelo.Celda;
import main.java.modelo.Grilla;
import main.java.modelo.ResultadoBusqueda;
import main.java.servicio.generadores.GeneradorGrillaAleatoria;

public class ServicioGrilla {

	public Grilla cargarGrillaAleatoria() {
		Random rand = new Random();
		IGeneradorGrilla generador = new GeneradorGrillaAleatoria(rand);
		Grilla grilla = generador.generar(rand.nextInt(1, 10), rand.nextInt(1, 10));
		return grilla;
	}

	public Grilla cargarGrillaDesdeArchivo() {
		// TODO: Cargar JSON, parsear a Grilla y luego convertir a DTO
		Grilla grilla = null;
		return grilla;
	}

	public ResultadoBusqueda ejecutarAlgoritmo(Grilla grilla) {
		IBusquedaCamino algoritmo = new BusquedaConPodaInteligente(); // o seleccionable
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
}
