package main.java.servicio.generadores;

import main.java.interfaz.IGeneradorCamino;
import main.java.modelo.Camino;
import main.java.modelo.Grilla;

public class GeneradorCaminoFijo implements IGeneradorCamino {

	@Override
	public Camino generar(Grilla grilla) {
		int filas = grilla.obtenerFilas();
		int columnas = grilla.obtenerColumnas();

		Camino camino = new Camino();
		int fila = 0, columna = 0;
		camino.agregarPaso(grilla.obtenerCelda(fila, columna));

		while (columna < columnas - 1) {
			columna++;
			camino.agregarPaso(grilla.obtenerCelda(fila, columna));
		}
		while (fila < filas - 1) {
			fila++;
			camino.agregarPaso(grilla.obtenerCelda(fila, columna));
		}

		return camino;
	}
}
