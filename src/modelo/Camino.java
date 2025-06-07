package modelo;

import java.util.ArrayList;
import java.util.List;

public class Camino {
    private List<Posicion> pasos;
    private int suma;

    public Camino() {
        this.pasos = new ArrayList<>();
        this.suma = 0;
    }

    public void agregarPaso(Posicion pos, int carga) {
    	pasos.add(pos);
	    suma += carga;
    }

    public void removerUltimoPaso(int carga) {
        if (!pasos.isEmpty()) {
            pasos.remove(pasos.size() - 1);
            suma -= carga;
        }
    }

    public List<Posicion> getPasos() {
        return pasos;
    }

    public int getSuma() {
        return suma;
    }

    public boolean estaBalanceado() {
        return suma == 0;
    }

    public int longitud() {
        return pasos.size();
    }
    //  ----- solo parna comprobar que funcioaba algoritmo genetico, eliminar a posteriori
    public String getRecorridoComoMovimientos() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < pasos.size(); i++) {
            Posicion anterior = pasos.get(i - 1);
            Posicion actual = pasos.get(i);
    
            if (actual.getFila() > anterior.getFila()) {
                sb.append("Abajo");
            } else if (actual.getColumna() > anterior.getColumna()) {
                sb.append("Derecha");
            }
    
            if (i < pasos.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
    public String resumenCamino() {
        StringBuilder sb = new StringBuilder();
        sb.append("Recorrido: ").append(getRecorridoComoMovimientos()).append("\n");
        sb.append("Suma de cargas: ").append(getSuma()).append("\n");
        sb.append("Fitness: ").append(calcularFitness()).append("\n");
        sb.append("Longitud: ").append(longitud()).append(" pasos");
        return sb.toString();
    }
    
    private int calcularFitness() {
        return Math.abs(suma);
    }
}


