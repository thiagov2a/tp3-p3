package main.java.dto;

import main.java.modelo.Camino;

public class ResultadoCamino {

	private boolean existe;
	private int caminosExplorados;
	private double tiempoEjecucion; // en ms
	private Camino camino; // null si no se encontró

	public ResultadoCamino(boolean existe, int caminosExplorados, double tiempoEjecucion, Camino camino) {
		this.existe = existe;
		this.caminosExplorados = caminosExplorados;
		this.tiempoEjecucion = tiempoEjecucion;
		this.camino = camino;
	}

	public boolean existe() {
		return existe;
	}

	public int getCaminosExplorados() {
		return caminosExplorados;
	}

	public double getTiempoEjecucion() {
		return tiempoEjecucion;
	}

	public Camino getCamino() {
		return camino;
	}
}
