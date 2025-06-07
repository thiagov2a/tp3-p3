package main.java;

import java.util.Random;

import main.java.algoritmo.BusquedaConPodaInteligente;
import main.java.algoritmo.BusquedaFuerzaBruta;
import main.java.dto.ResultadoCamino;
import main.java.interfaz.IBusquedaCamino;
import main.java.interfaz.IGeneradorGrilla;
import main.java.modelo.Grilla;
import main.java.servicio.GeneradorGrillaAleatoria;

public class Main {

	public static void main(String[] args) {

		for (int tamaño = 3; tamaño <= 12; tamaño++) {
			int filas = tamaño;
			int columnas = tamaño + 1; // Número par de pasos

			Random random = new Random();
			IGeneradorGrilla generador = new GeneradorGrillaAleatoria(random);
			Grilla grilla = generador.generar(filas, columnas);

			System.out.println("\n============================================");
			System.out.println("Tamaño de grilla: " + filas + "x" + columnas);

			// BÚSQUEDA SIN PODA
			IBusquedaCamino fuerzaBruta = new BusquedaFuerzaBruta();
			ResultadoCamino resultadoSinPoda = fuerzaBruta.buscar(grilla);

			System.out.println("\n[Sin poda]");
			System.out.println("¿Existe camino balanceado? " + (resultadoSinPoda.existe() ? "Sí" : "No"));
			System.out.println("Caminos explorados: " + resultadoSinPoda.getCaminosExplorados());
			System.out.println("Tiempo de ejecución: " + resultadoSinPoda.getTiempoEjecucion() + " ms");

			// BÚSQUEDA CON PODA
			IBusquedaCamino conPoda = new BusquedaConPodaInteligente();
			ResultadoCamino resultadoConPoda = conPoda.buscar(grilla);

			System.out.println("\n[Con poda]");
			System.out.println("¿Existe camino balanceado? " + (resultadoConPoda.existe() ? "Sí" : "No"));
			System.out.println("Caminos explorados: " + resultadoConPoda.getCaminosExplorados());
			System.out.println("Tiempo de ejecución: " + resultadoConPoda.getTiempoEjecucion() + " ms");

			System.out.println("============================================\n");
		}
	}
}
