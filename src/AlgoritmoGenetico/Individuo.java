package AlgoritmoGenetico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import modelo.Camino;
import modelo.Grilla;
import modelo.Posicion;

public class Individuo {
    private static final String DERECHA = "Derecha";
    private static final String ABAJO = "Abajo";

    private List<String> movimientos;
    private int fitness;
    private Camino camino;
    private Grilla grilla;

    public Individuo(List<String> movimientos, Grilla grilla) {
        validarParametros(movimientos, grilla);

        this.grilla = grilla;
        this.movimientos = new ArrayList<>(movimientos);
        this.camino = construirCamino();
        this.fitness = calcularFitness();
    }

    public static Individuo generarAleatorio(int pasosDerecha, int pasosAbajo, Grilla grilla, Random random) {
        List<String> movimientos = new ArrayList<>();
        for (int i = 0; i < pasosDerecha; i++) movimientos.add(DERECHA);
        for (int i = 0; i < pasosAbajo; i++) movimientos.add(ABAJO);
        return new Individuo(movimientos, grilla);
    }

    private void validarParametros(List<String> movimientos, Grilla grilla) {
        int filas = grilla.getFilas();
        int columnas = grilla.getColumna();

        if (filas <= 0 || columnas <= 0) {
            throw new IllegalArgumentException("Dimensiones de la grilla inválidas");
        }

        if ((filas + columnas) % 2 == 0) {
            throw new IllegalArgumentException("La suma de filas y columnas debe ser impar para lograr un camino balanceado");
        }

        long derecha = movimientos.stream().filter(DERECHA::equals).count();
        long abajo = movimientos.stream().filter(ABAJO::equals).count();

        if (derecha != columnas - 1 || abajo != filas - 1) {
            throw new IllegalArgumentException("Cantidad de movimientos inválida para alcanzar el destino");
        }
    }

    private Camino construirCamino() {
        Camino camino = new Camino();
        int fila = 0, columna = 0;
        camino.agregarPaso(new Posicion(fila, columna), grilla.getCargaElectricaEn(fila, columna));

        for (String mov : movimientos) {
            if (DERECHA.equals(mov)) columna++;
            else if (ABAJO.equals(mov)) fila++;
            camino.agregarPaso(new Posicion(fila, columna), grilla.getCargaElectricaEn(fila, columna));
        }

        return camino;
    }

    public Individuo mutar(Random random) {
        List<String> nuevosMovimientos = new ArrayList<>(movimientos);
        int i = random.nextInt(nuevosMovimientos.size());
        int j = random.nextInt(nuevosMovimientos.size());

        if (!nuevosMovimientos.get(i).equals(nuevosMovimientos.get(j))) {
            Collections.swap(nuevosMovimientos, i, j);
        }

        return new Individuo(nuevosMovimientos, grilla);
    }

    public static Individuo recombinar(Individuo padre1, Individuo padre2, Random random, Grilla grilla) {
        List<String> movs1 = padre1.getMovimientos();
        List<String> movs2 = padre2.getMovimientos();
        int size = movs1.size();
        int puntoCorte = random.nextInt(size);

        List<String> hijo = new ArrayList<>(size);
        int derecha = 0, abajo = 0;

        for (int i = 0; i < puntoCorte; i++) {
            String m = movs1.get(i);
            hijo.add(m);
            if (DERECHA.equals(m)) derecha++;
            else abajo++;
        }

        int derechaTotal = grilla.getColumna() - 1;
        int abajoTotal = grilla.getFilas() - 1;

        for (String m : movs2) {
            if (DERECHA.equals(m) && derecha < derechaTotal) {
                hijo.add(DERECHA);
                derecha++;
            } else if (ABAJO.equals(m) && abajo < abajoTotal) {
                hijo.add(ABAJO);
                abajo++;
            }
        }

        // Si faltan pasos, completar
        while (derecha < derechaTotal) { hijo.add(DERECHA); derecha++; }
        while (abajo < abajoTotal) { hijo.add(ABAJO); abajo++; }

        return new Individuo(hijo, grilla);
    }

    private int calcularFitness() {
        return Math.abs(camino.getSuma());
    }

    // Getters
    public List<String> getMovimientos() {
        return Collections.unmodifiableList(movimientos);
    }

    public int getFitness() {
        return fitness;
    }

    public Camino getCamino() {
        return camino;
    }

    public boolean estaBalanceado() {
        return camino.estaBalanceado();
    }
}
