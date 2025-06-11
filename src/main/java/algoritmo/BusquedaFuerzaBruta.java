package main.java.algoritmo;

import main.java.interfaz.IBusquedaCamino;
import main.java.modelo.Camino;
import main.java.modelo.Celda;
import main.java.modelo.Grilla;
import main.java.modelo.ResultadoBusqueda;

public class BusquedaFuerzaBruta implements IBusquedaCamino {

    private int caminosExplorados;
    private Camino caminoEncontrado;

    @Override
    public ResultadoBusqueda buscar(Grilla grilla) {
        if (grilla == null) {
            throw new IllegalArgumentException("La grilla no puede ser null.");
        }

        caminosExplorados = 0;
        caminoEncontrado = null;

        Celda inicio = grilla.obtenerCelda(0, 0);
        Celda destino = grilla.obtenerCelda(grilla.obtenerFilas() - 1, grilla.obtenerColumnas() - 1);
        Camino camino = new Camino();
        camino.agregarPaso(inicio);

        long inicioTiempo = System.nanoTime();
        boolean existe = buscar(grilla, inicio, destino, camino);
        long finTiempo = System.nanoTime();

        double tiempoEjecucion = (finTiempo - inicioTiempo) / 1_000_000.0;

        return new ResultadoBusqueda(existe, caminosExplorados, tiempoEjecucion, caminoEncontrado);
    }

    private boolean buscar(Grilla grilla, Celda actual, Celda destino, Camino camino) {
        caminosExplorados++;

        if (actual.equals(destino)) {
            if (camino.estaBalanceado() &&
                camino.obtenerLongitud() == grilla.obtenerFilas() + grilla.obtenerColumnas() - 1) {
                caminoEncontrado = new Camino(camino);
                return true;
            }
            return false;
        }

        int fila = actual.obtenerFila();
        int columna = actual.obtenerColumna();

        // Mover hacia abajo
        if (fila + 1 < grilla.obtenerFilas()) {
            Celda abajo = grilla.obtenerCelda(fila + 1, columna);
            camino.agregarPaso(abajo);
            if (buscar(grilla, abajo, destino, camino)) return true;
            camino.removerUltimoPaso();
        }

        // Mover hacia la derecha
        if (columna + 1 < grilla.obtenerColumnas()) {
            Celda derecha = grilla.obtenerCelda(fila, columna + 1);
            camino.agregarPaso(derecha);
            if (buscar(grilla, derecha, destino, camino)) return true;
            camino.removerUltimoPaso();
        }

        return false;
    }
}
