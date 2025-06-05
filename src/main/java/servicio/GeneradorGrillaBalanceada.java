package main.java.servicio;

import java.util.Random;

import main.java.algoritmo.BuscadorDeCaminos;
import main.java.modelo.Grilla;

public class GeneradorGrillaBalanceada {

	public static Grilla generar(int filas, int columnas) {
		Random rand = new Random();
		Grilla grilla;

		do {
			int[][] matriz = new int[filas][columnas];

			// Cargar con valores aleatorios de +1 y -1
			for (int i = 0; i < filas; i++) {
				for (int j = 0; j < columnas; j++) {
					matriz[i][j] = rand.nextBoolean() ? 1 : -1;
				}
			}

			grilla = new Grilla(matriz);

		} while (!new BuscadorDeCaminos().existeCaminoBalanceadoConPoda(grilla)); // solo acepta si tiene camino
																					// balanceado

		return grilla;
	}
}