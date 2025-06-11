package main.java.algoritmo;

import main.java.interfaz.IBusquedaCamino;
import main.java.modelo.Camino;
import main.java.modelo.Celda;
import main.java.modelo.Grilla;
import main.java.modelo.ResultadoBusqueda;

public class BusquedaConPodaInteligente implements IBusquedaCamino {

	private int caminosExplorados;
	private Camino caminoEncontrado;

	@Override
	public ResultadoBusqueda buscar(Grilla grilla) {
		caminosExplorados = 0;
		caminoEncontrado = null;

		Celda inicio = grilla.obtenerCelda(0, 0);
		Celda destino = grilla.obtenerCelda(grilla.obtenerFilas() - 1, grilla.obtenerColumnas() - 1);
		Camino camino = new Camino();
		camino.agregarPaso(inicio);

		long inicioTiempo = System.nanoTime();
		boolean existe = buscarConPoda(grilla, inicio, destino, camino);
		long finTiempo = System.nanoTime();

		double duracionEnMs = (finTiempo - inicioTiempo) / 1_000_000.0;

		return new ResultadoBusqueda(existe, caminosExplorados, duracionEnMs, caminoEncontrado);
	}

	private boolean buscarConPoda(Grilla grilla, Celda actual, Celda destino, Camino camino) {
		caminosExplorados++;

		int pasosRestantes = grilla.obtenerFilas() + grilla.obtenerColumnas() - 1 - camino.obtenerLongitud();
		if (Math.abs(camino.obtenerCargaTotal()) > pasosRestantes)
			return false;

		if (actual.equals(destino)) {
			if (camino.estaBalanceado()
					&& camino.obtenerLongitud() == (grilla.obtenerFilas() + grilla.obtenerColumnas() - 1)) {
				caminoEncontrado = new Camino(camino);
				return true;
			}
			return false;
		}

		int fila = actual.obtenerFila(), columna = actual.obtenerColumna();

		// Abajo
		if (fila + 1 < grilla.obtenerFilas()) {
			Celda abajo = grilla.obtenerCelda(fila + 1, columna);
			camino.agregarPaso(abajo);
			if (buscarConPoda(grilla, abajo, destino, camino))
				return true;
			camino.removerUltimoPaso();
		}

		// Derecha
		if (columna + 1 < grilla.obtenerColumnas()) {
			Celda derecha = grilla.obtenerCelda(fila, columna + 1);
			camino.agregarPaso(derecha);
			if (buscarConPoda(grilla, derecha, destino, camino))
				return true;
			camino.removerUltimoPaso();
		}

		return false;
	}
}
