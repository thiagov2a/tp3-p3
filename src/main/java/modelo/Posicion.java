package main.java.modelo;

public class Posicion {
	
    private int fila;
    private int columna;

    public Posicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Posicion)) return false;
        Posicion otra = (Posicion) o;
        return this.fila == otra.fila && this.columna == otra.columna;
    }

    @Override
    public int hashCode() {
        return fila * 31 + columna;
    }

    @Override
    public String toString() {
        return "(" + fila + ", " + columna + ")";
    }
}
