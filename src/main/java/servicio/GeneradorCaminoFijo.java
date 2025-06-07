package main.java.servicio;

import main.java.interfaz.IGeneradorCamino;
import main.java.modelo.Camino;
import main.java.modelo.Grilla;
import main.java.modelo.Posicion;

public class GeneradorCaminoFijo implements IGeneradorCamino {

	@Override
	public Camino generar(Grilla grilla) {
		int filas = grilla.getFilas();
		int columnas = grilla.getColumna();

		Camino camino = new Camino();
		int fila = 0, columna = 0;
		camino.agregarPaso(new Posicion(fila, columna), grilla.getCargaElectricaEn(fila, columna));

		while (columna < columnas - 1) {
			columna++;
			camino.agregarPaso(new Posicion(fila, columna), grilla.getCargaElectricaEn(fila, columna));
		}
		while (fila < filas - 1) {
			fila++;
			camino.agregarPaso(new Posicion(fila, columna), grilla.getCargaElectricaEn(fila, columna));
		}

		return camino;
	}
}
