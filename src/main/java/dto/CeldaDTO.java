package main.java.dto;

public class CeldaDTO {

	private int fila;
	private int columna;
	private int carga;

	public CeldaDTO(int fila, int columna, int carga) {
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
