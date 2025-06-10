package main.java.controlador;

import main.java.algoritmo.BusquedaConPodaInteligente;
import main.java.algoritmo.BusquedaFuerzaBruta;
import main.java.dto.CaminoDTO;
import main.java.dto.GrillaDTO;
import main.java.interfaz.IBusquedaCamino;
import main.java.interfaz.IVistaControlador;
import main.java.modelo.Camino;
import main.java.modelo.Grilla;
import main.java.modelo.ResultadoBusqueda;
import main.java.servicio.ServicioGrilla;
import main.java.vista.VistaGrilla;

public class ControladorGrilla implements IVistaControlador {

	private VistaGrilla vista;
	private ServicioGrilla servicio;
	private Grilla grilla;

	public ControladorGrilla(VistaGrilla vista, ServicioGrilla servicio) {
		this.vista = vista;
		this.servicio = servicio;
	}

	@Override
	public void iniciar() {
		vista.mostrar();
	}

	@Override
	public void cargarGrillaDesdeArchivo() {
		try {
			grilla = servicio.cargarGrillaDesdeArchivo();
			if (grilla == null) {
				vista.mostrarMensaje(2, "Warning", "No se pudo cargar la grilla.");
				return;
			}

			GrillaDTO grillaDTO = servicio.obtenerGrillaDTO(grilla);
			vista.actualizarGrilla(grillaDTO);
			vista.mostrarMensaje(1, "Carga completa", "Grilla cargada correctamente.");
		} catch (Exception e) {
			vista.mostrarMensaje(0, "Error", "Error al cargar la grilla: " + e.getMessage());
		}
	}

	@Override
	public void cargarGrillaAleatoria() {
		try {
			grilla = servicio.cargarGrillaAleatoria();
			if (grilla == null) {
				vista.mostrarMensaje(2, "Warning", "No se pudo cargar la grilla.");
				return;
			}

			GrillaDTO grillaDTO = servicio.obtenerGrillaDTO(grilla);
			vista.actualizarGrilla(grillaDTO);
			vista.mostrarMensaje(1, "Carga completa", "Grilla cargada correctamente.");
		} catch (Exception e) {
			vista.mostrarMensaje(0, "Error", "Error al cargar la grilla: " + e.getMessage());
		}
	}

	@Override
	public void ejecutarAlgoritmo() {
		if (grilla == null) {
			vista.mostrarMensaje(2, "Warning", "Primero debe cargar una grilla.");
			return;
		}

		try {
			IBusquedaCamino algoritmo = new BusquedaFuerzaBruta();
			ResultadoBusqueda resultado = servicio.ejecutarAlgoritmo(algoritmo, grilla);

			IBusquedaCamino algoritmo2 = new BusquedaConPodaInteligente();
			ResultadoBusqueda resultado2 = servicio.ejecutarAlgoritmo(algoritmo2, grilla);

			GrillaDTO grillaDTO = servicio.obtenerGrillaDTO(grilla);

			Camino camino = resultado2.obtenerCamino();
			CaminoDTO caminoDTO = servicio.obtenerCaminoDTO(camino);
			
			vista.actualizarGrilla(grillaDTO, caminoDTO);

			// TODO: Cargar Camino del ResultadoBusqueda a DTO para mostrarlo en pantalla

			vista.mostrarMensaje(1, "Resultados",
					"Fuerza Bruta: " + resultado.toString() + "\n\nPoda Inteligente: " + resultado2.toString());
		} catch (Exception e) {
			vista.mostrarMensaje(0, "Error", "Error durante ejecución: " + e.getMessage());
		}
	}
}
