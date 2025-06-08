package main.java.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.java.interfaz.IGeneradorCamino;

public class Camino {

	private final List<Celda> pasos;
	private int cargaTotal;

	public Camino() {
		this.pasos = new ArrayList<>();
		this.cargaTotal = 0;
	}

	public static Camino aleatorio(Grilla grilla, IGeneradorCamino generador) {
		return generador.generar(grilla);
	}

	public void agregarPaso(Celda celda) {
		pasos.add(celda);
		cargaTotal += celda.obtenerCarga();
	}

	public void removerUltimoPaso() {
		if (!pasos.isEmpty()) {
			Celda ultimo = pasos.remove(pasos.size() - 1);
			cargaTotal -= ultimo.obtenerCarga();
		}
	}

	public boolean estaBalanceado() {
		return cargaTotal == 0;
	}

	public int obtenerLongitud() {
		return pasos.size();
	}

	public int obtenerCargaTotal() {
		return cargaTotal;
	}

	public List<Celda> obtenerPasos() {
		return Collections.unmodifiableList(pasos);
	}
}
