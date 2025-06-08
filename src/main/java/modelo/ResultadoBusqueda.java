package main.java.modelo;

public class ResultadoBusqueda {

	private boolean existe;
	private int caminosExplorados;
	private double tiempoEjecucion; // en ms
	private Camino camino; // camino = null si no se encontró

	public ResultadoBusqueda(boolean existe, int caminosExplorados, double tiempoEjecucion, Camino camino) {
		this.existe = existe;
		this.caminosExplorados = caminosExplorados;
		this.tiempoEjecucion = tiempoEjecucion;
		this.camino = camino;
	}

	public boolean existe() {
		return existe;
	}

	public int obtenerCaminosExplorados() {
		return caminosExplorados;
	}

	public double obtenerTiempoEjecucion() {
		return tiempoEjecucion;
	}

	public Camino obtenerCamino() {
		return camino;
	}
}
