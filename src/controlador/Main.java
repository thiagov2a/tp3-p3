package controlador;

import algoritmo.BuscadorDeCaminos;
import modelo.Camino;
import modelo.Grilla;

public class Main {
    public static void main(String[] args) {
    	int[][] matriz = {
    		    { 1, -1,  1, -1,  1, -1 , 1},
    		    { 1,  1, -1, -1,  1, -1 , 1},
    		    { -1, 1,  1, -1, -1, 1 , 1},
    		    { 1, -1, 1, -1,  1, -1 , 1},
    		    { -1, 1, -1, 1, -1, 1  , 1},
    		    { 1, -1, 1, -1, 1, -1 , 1}
    		};


        Grilla grilla = new Grilla(matriz);

        // 🔍 SIN PODA
        BuscadorDeCaminos buscadorSinPoda = new BuscadorDeCaminos();
        long inicioSinPoda = System.currentTimeMillis();
        boolean existeSinPoda = buscadorSinPoda.existeCaminoBalanceado(grilla);
        long finSinPoda = System.currentTimeMillis();
        long duracionSinPoda = finSinPoda - inicioSinPoda;

        System.out.println("¿Camino balanceado (sin poda)? " + (existeSinPoda ? "Sí" : "No"));
        System.out.println("Caminos explorados (sin poda): " + buscadorSinPoda.getCaminosExplorados());
        System.out.println("Tiempo de ejecución (sin poda): " + duracionSinPoda + " ms");

        System.out.println();

        // 🔍 CON PODA
        BuscadorDeCaminos buscadorConPoda = new BuscadorDeCaminos();
        long inicioConPoda = System.currentTimeMillis();
        boolean existeConPoda = buscadorConPoda.existeCaminoBalanceadoConPoda(grilla);
        long finConPoda = System.currentTimeMillis();
        long duracionConPoda = finConPoda - inicioConPoda;

        System.out.println("¿Camino balanceado (con poda)? " + (existeConPoda ? "Sí" : "No"));
        System.out.println("Caminos explorados (con poda): " + buscadorConPoda.getCaminosExplorados());
        System.out.println("Tiempo de ejecución (con poda): " + duracionConPoda + " ms");
    }
}


