package main.java.servicio;

import main.java.interfaz.IGeneradorGrilla;
import main.java.modelo.Grilla;

public class GeneradorGrillaFija implements IGeneradorGrilla {

	private final int[][] grillaFija;

	public GeneradorGrillaFija(int[][] grillaFija) {
		this.grillaFija = grillaFija;
	}

	@Override
	public Grilla generar(int filas, int columnas) {
		// TODO: Agregar validación de n x m
		return new Grilla(grillaFija);
	}
}
