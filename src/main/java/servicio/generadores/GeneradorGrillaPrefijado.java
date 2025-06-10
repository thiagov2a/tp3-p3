package main.java.servicio.generadores;

import main.java.interfaz.IGeneradorGrilla;
import main.java.modelo.Grilla;

public class GeneradorGrillaPrefijado implements IGeneradorGrilla {

	// TODO: Borrar todo
	private final int[][] grillaFija;

	public GeneradorGrillaPrefijado(int[][] grillaFija) {
		this.grillaFija = grillaFija;
	}

	@Override
	public Grilla generar(int filas, int columnas) {
		// TODO: Agregar validación de n x m
		return new Grilla(grillaFija);
	}
}
