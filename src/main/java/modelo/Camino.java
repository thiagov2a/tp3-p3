package main.java.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.java.interfaz.IGeneradorCamino;

public class Camino {

	private final List<Celda> pasos;
	private int suma;

	public Camino() {
		this.pasos = new ArrayList<>();
		this.suma = 0;
	}

	public static Camino aleatorio(Grilla grilla, IGeneradorCamino generador) {
		return generador.generar(grilla);
	}

	public void agregarPaso(Celda celda) {
		pasos.add(celda);
		suma += celda.obtenerCarga();
	}

	public void removerUltimoPaso(Celda celda) {
		if (!pasos.isEmpty()) {
			pasos.remove(pasos.size() - 1);
			suma -= celda.obtenerCarga();
		}
	}

	public boolean estaBalanceado() {
		return suma == 0;
	}

	public int obtenerLongitud() {
		return pasos.size();
	}

	public int obtenerSuma() {
		return suma;
	}

	public List<Celda> obtenerPasos() {
		return Collections.unmodifiableList(pasos);
	}
}
