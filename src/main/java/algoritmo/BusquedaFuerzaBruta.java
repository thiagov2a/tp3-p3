package main.java.algoritmo;

import main.java.dto.ResultadoCamino;
import main.java.interfaz.IBusquedaCamino;
import main.java.modelo.Camino;
import main.java.modelo.Celda;
import main.java.modelo.Grilla;

public class BusquedaFuerzaBruta implements IBusquedaCamino {

	private int caminosExplorados;
	private Camino caminoEncontrado;

	@Override
	public ResultadoCamino buscar(Grilla grilla) {
		caminosExplorados = 0;
		caminoEncontrado = null;

		Celda inicio = grilla.obtenerCelda(0, 0);
		Celda destino = grilla.obtenerCelda(grilla.obtenerFilas() - 1, grilla.obtenerColumnas() - 1);
		Camino camino = new Camino();
		camino.agregarPaso(inicio);

		long inicioTiempo = System.nanoTime();
		boolean existe = buscar(grilla, inicio, destino, camino);
		long finTiempo = System.nanoTime();

		double duracionEnMs = (finTiempo - inicioTiempo) / 1_000_000.0;

		return new ResultadoCamino(existe, caminosExplorados, duracionEnMs, caminoEncontrado);
	}

	private boolean buscar(Grilla grilla, Celda actual, Celda destino, Camino camino) {
		caminosExplorados++;

		if (actual.equals(destino)) {
			if (camino.estaBalanceado()
					&& camino.obtenerLongitud() == grilla.obtenerFilas() + grilla.obtenerColumnas() - 1) {
				caminoEncontrado = camino; // TODO: Copia profunda
				return true;
			}
			return false;
		}

		int fila = actual.obtenerFila(), columna = actual.obtenerColumna();

		// Abajo
		if (fila + 1 < grilla.obtenerFilas()) {
			Celda abajo = grilla.obtenerCelda(fila + 1, columna);
			camino.agregarPaso(abajo);
			if (buscar(grilla, abajo, destino, camino))
				return true;
			camino.removerUltimoPaso(abajo);
		}

		// Derecha
		if (columna + 1 < grilla.obtenerColumnas()) {
			Celda derecha = grilla.obtenerCelda(fila, columna + 1);
			camino.agregarPaso(derecha);
			if (buscar(grilla, derecha, destino, camino))
				return true;
			camino.removerUltimoPaso(derecha);
		}

		return false;
	}
}
