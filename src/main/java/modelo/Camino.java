package main.java.modelo;

import java.util.ArrayList;
import java.util.List;

import main.java.interfaz.IGeneradorCamino;

public class Camino {

	private final List<Posicion> pasos;
	private int suma;

	public Camino() {
		this.pasos = new ArrayList<>();
		this.suma = 0;
	}

	public static Camino aleatorio(Grilla grilla, IGeneradorCamino generador) {
		return generador.generar(grilla);
	}

	public void agregarPaso(Posicion pos, int carga) {
		pasos.add(pos);
		suma += carga;
	}

	public void removerUltimoPaso(int carga) {
		if (!pasos.isEmpty()) {
			pasos.remove(pasos.size() - 1);
			suma -= carga;
		}
	}

	public boolean estaBalanceado() {
		return suma == 0;
	}

	public int obtenerLongitud() {
		return pasos.size();
	}

	public List<Posicion> obtenerPasos() {
		List<Posicion> copia = new ArrayList<>();
		for (Posicion p : pasos) {
			copia.add(p);
		}
		return copia;
	}

	public int obtenerSuma() {
		return suma;
	}
}
