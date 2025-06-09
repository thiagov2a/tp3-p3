package main.java.controlador;

import main.java.dto.GrillaDTO;
import main.java.interfaz.IVistaControlador;
import main.java.modelo.Grilla;
import main.java.modelo.ResultadoBusqueda;
import main.java.servicio.ServicioGrilla;
import main.java.vista.VistaGrilla;

public class ControladorGrilla implements IVistaControlador {

	private VistaGrilla vista;
	private ServicioGrilla servicio; // Lógica/modelo que carga y ejecuta algoritmo
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
	public void cargarGrillaAleatoria() {
		try {
			grilla = servicio.cargarGrillaAleatoria();
			if (grilla == null) {
				vista.mostrarMensaje("No se pudo cargar la grilla.");
				return;
			}
			GrillaDTO grillaDTO = servicio.obtenerGrillaDTO(grilla);
			vista.actualizarGrilla(grillaDTO);
			vista.mostrarMensaje("Grilla cargada correctamente.");
		} catch (Exception e) {
			vista.mostrarMensaje("Error al cargar la grilla: " + e.getMessage());
		}
	}

	@Override
	public void cargarGrillaDesdeArchivo() {
		try {
			// grilla = servicio.cargarGrillaDesdeArchivo();
			if (grilla == null) {
				vista.mostrarMensaje("No se pudo cargar la grilla.");
				return;
			}
			GrillaDTO grillaDTO = servicio.obtenerGrillaDTO(grilla);
			vista.actualizarGrilla(grillaDTO);
			vista.mostrarMensaje("Grilla cargada correctamente.");
		} catch (Exception e) {
			vista.mostrarMensaje("Error al cargar la grilla: " + e.getMessage());
		}
	}

	@Override
	public void ejecutarAlgoritmo() {
		if (grilla == null) {
			vista.mostrarMensaje("Primero debe cargar una grilla.");
			return;
		}

		try {
			ResultadoBusqueda resultado = servicio.ejecutarAlgoritmo(grilla);
			vista.mostrarMensaje("Resultado: " + resultado.toString()); // TODO: toString()
		} catch (Exception e) {
			vista.mostrarMensaje("Error durante ejecución: " + e.getMessage());
		}
	}
}
