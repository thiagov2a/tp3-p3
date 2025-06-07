package main.java.modelo;

public class Celda {

	private final int fila;
	private final int columna;
	private final int carga;

	public Celda(int fila, int columna, int carga) {
		this.fila = fila;
		this.columna = columna;
		this.carga = carga;
	}

	public int obtenerFila() {
		return fila;
	}

	public int obtenerColumna() {
		return columna;
	}

	public int obtenerCarga() {
		return carga;
	}
}
