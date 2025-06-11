package main.java.dto;

import java.util.List;

public class CaminoDTO {

	private List<CeldaDTO> pasos;
	private int cargaTotal;

	public CaminoDTO(List<CeldaDTO> pasos, int cargaTotal) {
		this.pasos = pasos;
		this.cargaTotal = cargaTotal;
	}

	public List<CeldaDTO> obtenerPasos() {
		return pasos;
	}

	public int obtenerCargaTotal() {
		return cargaTotal;
	}
}
