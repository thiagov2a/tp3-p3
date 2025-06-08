package main.java.servicio;

import main.java.algoritmo.BusquedaConPodaInteligente;
import main.java.dto.GrillaDTO;
import main.java.interfaz.IBusquedaCamino;
import main.java.modelo.Grilla;
import main.java.modelo.ResultadoBusqueda;

public class ServicioGrilla {

	public GrillaDTO cargarGrillaDesdeArchivo() {
		// TODO: Cargar JSON, parsear a Grilla y luego convertir a DTO
		GrillaDTO grillaDTO = null;
		return grillaDTO;
	}

	public ResultadoBusqueda ejecutarAlgoritmo(Grilla grilla) {
		IBusquedaCamino algoritmo = new BusquedaConPodaInteligente(); // o seleccionable
		return algoritmo.buscar(grilla);
	}
}
