package main.java.servicio;

import java.io.FileReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import main.java.modelo.Grilla;

public class ConsumoGrilla {

	public static Grilla cargarGrillaDesdeJson(String rutaArchivo) {
		try (FileReader reader = new FileReader(rutaArchivo)) {
			JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
			JsonArray celdas = json.getAsJsonArray("celdas");

			int filas = celdas.size();
			int columnas = celdas.get(0).getAsJsonArray().size();

			int[][] matriz = new int[filas][columnas];
			for (int i = 0; i < filas; i++) {
				JsonArray fila = celdas.get(i).getAsJsonArray();
				for (int j = 0; j < columnas; j++) {
					matriz[i][j] = fila.get(j).getAsInt();
				}
			}
			return new Grilla(matriz);
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
}
