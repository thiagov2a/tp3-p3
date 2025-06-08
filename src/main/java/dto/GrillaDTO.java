package main.java.dto;

public class GrillaDTO {

	private CeldaDTO[][] celdas;
	private int filas;
	private int columnas;

	public GrillaDTO(CeldaDTO[][] celdas) {
		this.celdas = celdas;
		this.filas = celdas.length;
		this.columnas = celdas[0].length;
	}

	public CeldaDTO[][] obtenerCeldas() {
		return celdas;
	}

	public int obtenerFilas() {
		return filas;
	}

	public int obtenerColumnas() {
		return columnas;
	}
}
