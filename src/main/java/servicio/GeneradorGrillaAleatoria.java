package main.java.servicio;

import java.util.Random;

import main.java.algoritmo.BusquedaConPodaInteligente;
import main.java.dto.ResultadoCamino;
import main.java.interfaz.IBusquedaCamino;
import main.java.interfaz.IGeneradorGrilla;
import main.java.modelo.Grilla;

public class GeneradorGrillaAleatoria implements IGeneradorGrilla {

	private Random random;

	public GeneradorGrillaAleatoria(Random random) {
		this.random = random;
	}

	@Override
	public Grilla generar(int filas, int columnas) {
		Grilla grilla;
		ResultadoCamino camino;
		do {
			int[][] matriz = new int[filas][columnas];

			// Cargar con valores aleatorios de +1 y -1
			for (int i = 0; i < filas; i++) {
				for (int j = 0; j < columnas; j++) {
					matriz[i][j] = random.nextBoolean() ? 1 : -1;
				}
			}
			grilla = new Grilla(matriz);

			// Sale del bucle cuando encuentra un camino balanceado
			IBusquedaCamino buscador = new BusquedaConPodaInteligente();
			camino = buscador.buscar(grilla);
		} while (!camino.existe());

		return grilla;
	}
}
