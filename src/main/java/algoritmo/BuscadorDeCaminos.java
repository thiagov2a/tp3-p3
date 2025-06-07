package main.java.algoritmo;

import main.java.modelo.Camino;
import main.java.modelo.Grilla;
import main.java.modelo.Posicion;

public class BuscadorDeCaminos {

	private int caminosExplorados;

	public BuscadorDeCaminos() {
		this.caminosExplorados = 0;
	}

	public int getCaminosExplorados() {
		return caminosExplorados;
	}

	public boolean existeCaminoBalanceado(Grilla grilla) {
		Posicion inicio = new Posicion(0, 0);
		Posicion destino = new Posicion(grilla.getFilas() - 1, grilla.getColumna() - 1);

		Camino camino = new Camino();
		int cargaInicial = grilla.getCargaElectricaEn(0, 0);
		camino.agregarPaso(inicio, cargaInicial);

		return buscar(grilla, inicio, destino, camino);
	}

	private boolean buscar(Grilla grilla, Posicion inicio, Posicion destino, Camino camino) {
		caminosExplorados++;

		if (inicio.equals(destino)) {
			return camino.estaBalanceado() && camino.obtenerLongitud() == (grilla.getFilas() + grilla.getColumna() - 1);
		}

		int columna = inicio.getColumna();
		int fila = inicio.getFila();

		// Abajo
		if (fila + 1 < grilla.getFilas()) {
			Posicion abajo = new Posicion(fila + 1, columna);
			int carga = grilla.getCargaElectricaEn(fila + 1, columna);
			camino.agregarPaso(abajo, carga);

			if (buscar(grilla, abajo, destino, camino))
				return true;

			camino.removerUltimoPaso(carga); // Volver atrás
		}

		// Derecha
		if (columna + 1 < grilla.getColumna()) {
			Posicion derecha = new Posicion(fila, columna + 1);
			int carga = grilla.getCargaElectricaEn(fila, columna + 1);
			camino.agregarPaso(derecha, carga);

			if (buscar(grilla, derecha, destino, camino))
				return true;

			camino.removerUltimoPaso(carga);

		}

		return false;
	}

	public boolean existeCaminoBalanceadoConPoda(Grilla grilla) {
		Posicion inicio = new Posicion(0, 0);
		Posicion destino = new Posicion(grilla.getFilas() - 1, grilla.getColumna() - 1);

		Camino camino = new Camino();
		int cargaInicial = grilla.getCargaElectricaEn(0, 0);
		camino.agregarPaso(inicio, cargaInicial);

		return buscarConPoda(grilla, inicio, destino, camino);
	}

	private boolean buscarConPoda(Grilla grilla, Posicion inicio, Posicion destino, Camino camino) {
		caminosExplorados++;

		// Nueva poda
		int pasosRestantes = (grilla.getFilas() + grilla.getColumna() - 1) - camino.obtenerLongitud();
		int sumaParcial = camino.obtenerSuma();
		if (Math.abs(sumaParcial) > pasosRestantes) {
			return false;
		}

		if (inicio.equals(destino)) {
			return camino.estaBalanceado() && camino.obtenerLongitud() == (grilla.getFilas() + grilla.getColumna() - 1);
		}

		int columna = inicio.getColumna();
		int fila = inicio.getFila();

		// Abajo
		if (fila + 1 < grilla.getFilas()) {
			Posicion abajo = new Posicion(fila + 1, columna);
			int carga = grilla.getCargaElectricaEn(fila + 1, columna);
			camino.agregarPaso(abajo, carga);

			if (buscarConPoda(grilla, abajo, destino, camino))
				return true;

			camino.removerUltimoPaso(carga); // Volver atrás
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
