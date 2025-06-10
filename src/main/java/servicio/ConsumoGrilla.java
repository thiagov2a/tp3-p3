package main.java.servicio;



import java.io.FileReader;
import com.google.gson.*;
import main.java.modelo.*;

public class ConsumoGrilla {

    public static Grilla cargarGrillaDesdeJson(String rutaArchivo) {
        try (FileReader reader = new FileReader(rutaArchivo)) {
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray filas = json.getAsJsonArray("celdas");

            int numFilas = filas.size();
            int numColumnas = filas.get(0).getAsJsonArray().size();

            int[][] matriz = new int[numFilas][numColumnas];

            for (int i = 0; i < numFilas; i++) {
                JsonArray fila = filas.get(i).getAsJsonArray();
                for (int j = 0; j < numColumnas; j++) {
                    matriz[i][j] = fila.get(j).getAsInt();
                }
            }

            return new Grilla(matriz);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al cargar la grilla: " + e.getMessage());
        }
    }
}

