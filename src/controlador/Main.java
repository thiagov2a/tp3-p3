package controlador;

import algoritmo.BuscadorDeCaminos;
import modelo.Grilla;
import modelo.GeneradorGrillaBalanceada;

public class Main {
    public static void main(String[] args) {
    	for (int tamaño = 3; tamaño <= 12; tamaño++) {
            int filas = tamaño;
            int columnas = tamaño + 1; // Garantizamos que haya pasos pares

            Grilla grilla = GeneradorGrillaBalanceada.generar(filas, columnas);
    	
        System.out.println("Grilla generada aleatoriamente con tamaño " + filas + "x" + columnas);

        // 🔍 SIN PODA
        BuscadorDeCaminos buscadorSinPoda = new BuscadorDeCaminos();
        long inicioSinPoda = System.currentTimeMillis();
        boolean existeSinPoda = buscadorSinPoda.existeCaminoBalanceado(grilla);
        long finSinPoda = System.currentTimeMillis();
        long duracionSinPoda = finSinPoda - inicioSinPoda;

        System.out.println("\n¿Camino balanceado (sin poda)? " + (existeSinPoda ? "Sí" : "No"));
        System.out.println("Caminos explorados (sin poda): " + buscadorSinPoda.getCaminosExplorados());
        System.out.println("Tiempo de ejecución (sin poda): " + duracionSinPoda + " ms");

        // 🔍 CON PODA
        BuscadorDeCaminos buscadorConPoda = new BuscadorDeCaminos();
        long inicioConPoda = System.currentTimeMillis();
        boolean existeConPoda = buscadorConPoda.existeCaminoBalanceadoConPoda(grilla);
        long finConPoda = System.currentTimeMillis();
        long duracionConPoda = finConPoda - inicioConPoda;

        System.out.println("\n¿Camino balanceado (con poda)? " + (existeConPoda ? "Sí" : "No"));
        System.out.println("Caminos explorados (con poda): " + buscadorConPoda.getCaminosExplorados());
        System.out.println("Tiempo de ejecución (con poda): " + duracionConPoda + " ms");
    }
}
}