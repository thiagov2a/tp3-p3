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
}


