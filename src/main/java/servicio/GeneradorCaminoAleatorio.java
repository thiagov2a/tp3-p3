package main.java.servicio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import main.java.interfaz.IGeneradorCamino;
import main.java.modelo.Camino;
import main.java.modelo.Grilla;
import main.java.modelo.Posicion;

public class GeneradorCaminoAleatorio implements IGeneradorCamino {

	private Random random;

	public GeneradorCaminoAleatorio(Random random) {
		this.random = random;
	}

	@Override
	public Camino generar(Grilla grilla) {
		int filas = grilla.getFilas();
		int columnas = grilla.getColumna();

		// Generar una secuencia de movimientos: (filas - 1) hacia abajo y (columnas - 1) hacia la derecha
		List<Boolean> movimientos = new ArrayList<>();
		for (int i = 0; i < filas - 1; i++)
			movimientos.add(true); // true = abajo
		for (int j = 0; j < columnas - 1; j++)
			movimientos.add(false); // false = derecha
		Collections.shuffle(movimientos, random);

		// Construir el camino
		int fila = 0, columna = 0;
		Camino camino = new Camino();
		camino.agregarPaso(new Posicion(fila, columna), grilla.getCargaElectricaEn(fila, columna));

		for (boolean mov : movimientos) {
			if (mov)
				fila++;
			else
				columna++;
			camino.agregarPaso(new Posicion(fila, columna), grilla.getCargaElectricaEn(fila, columna));
		}

		return camino;
	}
}
