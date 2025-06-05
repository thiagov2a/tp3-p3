package main.java.dto;

import java.util.List;
import main.java.modelo.Posicion;

public class ResultadoCamino {

    private boolean existe;
    private int caminosExplorados;
    private long tiempoEjecucion; // en ms
    private List<Posicion> camino; // null si no se encontró

    public ResultadoCamino(boolean existe, int caminosExplorados, long tiempoEjecucion, List<Posicion> camino) {
        this.existe = existe;
        this.caminosExplorados = caminosExplorados;
        this.tiempoEjecucion = tiempoEjecucion;
        this.camino = camino;
    }

    public boolean existe() {
        return existe;
    }

    public int getCaminosExplorados() {
        return caminosExplorados;
    }

    public long getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    public List<Posicion> getCamino() {
        return camino;
    }
}
