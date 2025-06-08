package main.java.servicio;

import main.java.dto.GrillaDTO;
import main.java.modelo.ResultadoBusqueda;

public class ServicioGrilla {

	public GrillaDTO cargarGrillaDesdeArchivo() {
		// Lógica real para cargar desde archivo (acá un ejemplo simulado)
		// En tu TP deberías usar JFileChooser o leer un archivo JSON
		int[][] matriz = { { 1, -1, 1 }, { -1, 1, -1 }, { 1, -1, 1 } };
		return new GrillaDTO(matriz);
	}

	public ResultadoBusqueda ejecutarAlgoritmo(GrillaDTO grilla) {
		// Lógica real del algoritmo va acá (fuerza bruta, poda, etc.)
		// Este ejemplo simula un resultado
		String resumen = "Camino balanceado encontrado. Costo: 6. Caminos explorados: 15.";
		GrillaDTO grillaModificada = grilla; // Podrías devolver una nueva si la modifica
		return new ResultadoBusqueda(resumen, grillaModificada);
	}
}
