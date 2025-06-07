package main.java.algoritmo;

import main.java.dto.ResultadoCamino;
import main.java.interfaz.IBusquedaCamino;
import main.java.modelo.Camino;
import main.java.modelo.Grilla;
import main.java.modelo.Posicion;

public class BusquedaConPodaInteligente implements IBusquedaCamino {

	private int caminosExplorados;
	private Camino caminoEncontrado;

	@Override
	public ResultadoCamino buscar(Grilla grilla) {
		caminosExplorados = 0;
		caminoEncontrado = null;

		Posicion inicio = new Posicion(0, 0);
		Posicion destino = new Posicion(grilla.getFilas() - 1, grilla.getColumna() - 1);
		Camino camino = new Camino();
		camino.agregarPaso(inicio, grilla.getCargaElectricaEn(0, 0));

		long inicioTiempo = System.nanoTime();
		boolean existe = buscarConPoda(grilla, inicio, destino, camino);
		long finTiempo = System.nanoTime();

		double duracionEnMs = (finTiempo - inicioTiempo) / 1_000_000.0;

		return new ResultadoCamino(existe, caminosExplorados, duracionEnMs, caminoEncontrado);
	}

	private boolean buscarConPoda(Grilla grilla, Posicion actual, Posicion destino, Camino camino) {
		caminosExplorados++;

		int pasosRestantes = grilla.getFilas() + grilla.getColumna() - 1 - camino.obtenerLongitud();
		if (Math.abs(camino.obtenerSuma()) > pasosRestantes)
			return false;

		if (actual.equals(destino)) {
			if (camino.estaBalanceado() && camino.obtenerLongitud() == grilla.getFilas() + grilla.getColumna() - 1) {
				caminoEncontrado = camino; // TODO: Copia profunda
				return true;
			}
			return false;
		}

		int fila = actual.getFila(), columna = actual.getColumna();

		// Abajo
		if (fila + 1 < grilla.getFilas()) {
			Posicion abajo = new Posicion(fila + 1, columna);
			int carga = grilla.getCargaElectricaEn(fila + 1, columna);
			camino.agregarPaso(abajo, carga);
			if (buscarConPoda(grilla, abajo, destino, camino))
				return true;
			camino.removerUltimoPaso(carga);
		}

		// Derecha
		if (columna + 1 < grilla.getColumna()) {
			Posicion derecha = new Posicion(fila, columna + 1);
			int carga = grilla.getCargaElectricaEn(fila, columna + 1);
			camino.agregarPaso(derecha, carga);
			if (buscarConPoda(grilla, derecha, destino, camino))
				return true;
			camino.removerUltimoPaso(carga);
		}

		return false;
	}
}
