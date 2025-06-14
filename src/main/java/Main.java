package main.java;

import java.util.Random;

import main.java.algoritmo.BusquedaConPodaInteligente;
import main.java.algoritmo.BusquedaFuerzaBruta;
import main.java.controlador.ControladorGrilla;
import main.java.interfaz.IBusquedaCamino;
import main.java.interfaz.IVistaControlador;
import main.java.modelo.Grilla;
import main.java.modelo.ResultadoBusqueda;
import main.java.servicio.GeneradorGrilla;
import main.java.servicio.ServicioGrilla;
import main.java.vista.VistaGrilla;

public class Main {

	public static void main(String[] args) {

		ServicioGrilla servicio = new ServicioGrilla();
		VistaGrilla vista = new VistaGrilla();
		IVistaControlador controlador = new ControladorGrilla(vista, servicio);
		vista.colocarControlador(controlador);
		controlador.iniciar();

		for (int tamaño = 3; tamaño <= 12; tamaño++) {
			int filas = tamaño;
			int columnas = tamaño + 1; // Número par de pasos

			Random random = new Random();
			GeneradorGrilla generador = new GeneradorGrilla(random);
			Grilla grilla = generador.generar(filas, columnas);
			// Grilla grilla =
			// ConsumoGrilla.cargarGrillaDesdeJson("src/main/recursos/grilla_ejemplo_1.json");

			System.out.println("\n============================================");
			System.out.println("Tamaño de grilla: " + filas + "x" + columnas);

			// BÚSQUEDA SIN PODA
			IBusquedaCamino fuerzaBruta = new BusquedaFuerzaBruta();
			ResultadoBusqueda resultadoSinPoda = fuerzaBruta.buscar(grilla);

			System.out.println("\n[Sin poda]");
			System.out.println("¿Existe camino balanceado? " + (resultadoSinPoda.existe() ? "Sí" : "No"));
			System.out.println("Caminos explorados: " + resultadoSinPoda.obtenerCaminosExplorados());
			System.out.println("Tiempo de ejecución: " + resultadoSinPoda.obtenerTiempoEjecucion() + " ms");

			// BÚSQUEDA CON PODA
			IBusquedaCamino conPoda = new BusquedaConPodaInteligente();
			ResultadoBusqueda resultadoConPoda = conPoda.buscar(grilla);

			System.out.println("\n[Con poda]");
			System.out.println("¿Existe camino balanceado? " + (resultadoConPoda.existe() ? "Sí" : "No"));
			System.out.println("Caminos explorados: " + resultadoConPoda.obtenerCaminosExplorados());
			System.out.println("Tiempo de ejecución: " + resultadoConPoda.obtenerTiempoEjecucion() + " ms");

			System.out.println("============================================\n");
		}
	}
}
