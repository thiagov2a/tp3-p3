package main.java.modelo;

public class Grilla {

	private final Celda[][] celdas;
	private final int filas;
	private final int columnas;

	public Grilla(int[][] matriz) {
		validarMatriz(matriz);

		this.filas = matriz.length;
		this.columnas = matriz[0].length;
		this.celdas = new Celda[filas][columnas];

		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				celdas[i][j] = new Celda(i, j, matriz[i][j]);
			}
		}
	}

	private void validarMatriz(int[][] matriz) {
		if (matriz == null)
			throw new IllegalArgumentException("La matriz no puede ser null.");
		if (matriz.length == 0)
			throw new IllegalArgumentException("La matriz debe tener al menos una fila.");
		if (matriz[0] == null || matriz[0].length == 0)
			throw new IllegalArgumentException("La matriz debe tener al menos una columna.");

		int columnas = matriz[0].length;
		for (int i = 1; i < matriz.length; i++) {
			if (matriz[i] == null || matriz[i].length != columnas)
				throw new IllegalArgumentException("Todas las filas deben tener la misma longitud y no ser null.");
		}
	}

	public Celda obtenerCelda(int fila, int columna) {
		if (fila < 0 || fila >= filas)
			throw new IndexOutOfBoundsException("Fila fuera de rango: " + fila);
		if (columna < 0 || columna >= columnas)
			throw new IndexOutOfBoundsException("Columna fuera de rango: " + columna);

		return celdas[fila][columna];
	}

	public Celda[][] obtenerCeldas() {
		Celda[][] copia = new Celda[filas][columnas];
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				copia[i][j] = celdas[i][j];
			}
		}
		return copia;
	}

	public int obtenerFilas() {
		return filas;
	}

	public int obtenerColumnas() {
		return columnas;
	}
}
