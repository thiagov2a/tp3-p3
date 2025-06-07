package AlgoritmoGenetico;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import modelo.Camino;
import modelo.Grilla;

public class AlgoritmoGenetico {
    private int poblacionSize;
    private int maxGeneraciones;
    private double tasaMutacion;
    private Random random = new Random();

    private Grilla grilla;

    public AlgoritmoGenetico(Grilla grilla, int poblacionSize, int maxGeneraciones, double tasaMutacion) {
        this.grilla = grilla;
        this.poblacionSize = poblacionSize;
        this.maxGeneraciones = maxGeneraciones;
        this.tasaMutacion = tasaMutacion;
    }

    public Camino ejecutar() {
        List<Individuo> poblacion = generarPoblacionInicial();
        Individuo mejorIndividuo = null;

        for (int gen = 0; gen < maxGeneraciones; gen++) {
            poblacion.sort(Comparator.comparingInt(Individuo::getFitness));
            mejorIndividuo = poblacion.get(0);
            
            if (mejorIndividuo.estaBalanceado()) {
                return mejorIndividuo.getCamino();
            }
            
            int MejoresIndividuos = Math.max(1, poblacionSize / 10);
            List<Individuo> nuevaPoblacion = new ArrayList<>();
            nuevaPoblacion.addAll(poblacion.subList(0, MejoresIndividuos));
            
            while (nuevaPoblacion.size() < poblacionSize) {
                Individuo padre1 = seleccionar(poblacion);
                Individuo padre2 = seleccionar(poblacion);
                Individuo hijo = Individuo.recombinar(padre1, padre2, random, grilla);
                if (random.nextDouble() < tasaMutacion) {
                    hijo = hijo.mutar(random);
                }
                nuevaPoblacion.add(hijo);
            }
            poblacion = nuevaPoblacion;
        }
    
        System.out.println("No se encontró un camino balanceado. Devolviendo el mejor encontrado.");
        return mejorIndividuo.getCamino();
    }

    private List<Individuo> generarPoblacionInicial() {
        List<Individuo> poblacion = new ArrayList<>();
        int pasosDerecha = grilla.getColumna() - 1;
        int pasosAbajo = grilla.getFilas() - 1;

        for (int i = 0; i < poblacionSize; i++) {
            Individuo ind = Individuo.generarAleatorio(pasosDerecha, pasosAbajo, grilla, random);
            poblacion.add(ind);
        }

        return poblacion;
    }

    private Individuo seleccionar(List<Individuo> poblacion) {
        int ElejirMejorTamaño = 4;
        Individuo mejorIndividuo = poblacion.get(random.nextInt(poblacion.size()));
        for (int i = 1; i < ElejirMejorTamaño; i++) {
            Individuo Candidato = poblacion.get(random.nextInt(poblacion.size()));
            if (Candidato.getFitness() < mejorIndividuo.getFitness()) {
                mejorIndividuo = Candidato;
            }
        }
        return mejorIndividuo;
    }
}
