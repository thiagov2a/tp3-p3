package main.java.controlador;

import main.java.dto.GrillaDTO;
import main.java.interfaz.IVistaControlador;
import main.java.servicio.ServicioGrilla;
import main.java.vista.VistaGrilla;

public class ControladorGrilla implements IVistaControlador {

	private VistaGrilla vista;
	private ServicioGrilla servicio; // Lógica/modelo que carga y ejecuta algoritmo
	private GrillaDTO grillaActual;

	public ControladorGrilla(VistaGrilla vista, ServicioGrilla servicio) {
		this.vista = vista;
		this.servicio = servicio;
	}

	@Override
	public void iniciar() {
		vista.mostrar();
	}

	@Override
	public void cargarGrilla() {
		try {
			grillaActual = servicio.cargarGrillaDesdeArchivo();
			if (grillaActual == null) {
				vista.mostrarMensaje("No se pudo cargar la grilla.");
				return;
			}
			vista.actualizarGrilla(grillaActual);
			vista.mostrarMensaje("Grilla cargada correctamente.");
		} catch (Exception e) {
			vista.mostrarMensaje("Error al cargar la grilla: " + e.getMessage());
		}
	}

	@Override
	public void ejecutarAlgoritmo() {
		if (grillaActual == null) {
			vista.mostrarMensaje("Primero debe cargar una grilla.");
			return;
		}

		try {
			var resultado = servicio.ejecutarAlgoritmo(grillaActual);
			vista.mostrarMensaje("Resultado: " + resultado.obtenerResumen());
			// Si el algoritmo modifica la grilla, actualizar vista
			vista.actualizarGrilla(resultado.obtenerGrillaResultante());
		} catch (Exception e) {
			vista.mostrarMensaje("Error durante ejecución: " + e.getMessage());
		}
	}
}
