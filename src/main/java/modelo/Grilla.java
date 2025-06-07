package main.java.modelo;

public class Grilla {

	private final Celda[][] celdas;

	public Grilla(int[][] matriz) {
		int filas = matriz.length;
		int columnas = matriz[0].length;
		celdas = new Celda[filas][columnas];

		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				celdas[i][j] = new Celda(i, j, matriz[i][j]);
			}
		}
	}

	public int obtenerFilas() {
		return celdas.length;
	}

	public int obtenerColumnas() {
		return celdas[0].length;
	}

	public Celda obtenerCelda(int fila, int columna) {
		return celdas[fila][columna];
	}

	public Celda[][] obtenerCeldas() {
		Celda[][] copia = new Celda[celdas.length][];
		for (int i = 0; i < celdas.length; i++) {
			copia[i] = celdas[i].clone();
		}
		return copia;
	}
}
