package AlgoritmoGenetico;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import modelo.Camino;
import modelo.Grilla;
import modelo.Posicion;

public class AlgoritmoGeneticoTest {

    private Grilla grilla;
    private int[][] celdasValidas;

    @Before
    public void setUp() {
        celdasValidas = new int[][]{
            {1, -1, 1, -1},
            {-1, 1, -1, 1},
            {1, -1, 1, -1}
        };
        grilla = new Grilla(celdasValidas);
    }

    @Test
    public void testEjecutarDevuelveCaminoBalanceadoSiSeEncuentra() {
        AlgoritmoGenetico ag = new AlgoritmoGenetico(grilla, 50, 100, 0.2);
        Camino camino = ag.ejecutar();

        assertNotNull("El algoritmo debe retornar un camino", camino);
        assertEquals("El camino debe llegar a la última celda",
                    new Posicion(grilla.getFilas() - 1, grilla.getColumna() - 1),
                    camino.getPasos().get(camino.getPasos().size() - 1));
        assertEquals("El camino debe comenzar en (0, 0)",
                    new Posicion(0, 0),
                    camino.getPasos().get(0));
    }

    @Test
    public void testCaminoTieneCantidadDePasosCorrecta() {
        AlgoritmoGenetico ag = new AlgoritmoGenetico(grilla, 30, 50, 0.1);
        Camino camino = ag.ejecutar();

        int pasosEsperados = grilla.getFilas() - 1 + grilla.getColumna() - 1;
        assertEquals("El camino debe tener la cantidad esperada de pasos", pasosEsperados + 1, camino.longitud());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLanzaExcepcionSiGrillaEsImposibleDeBalancear() {
        int[][] grillaImposible = {
            {5, 5},
            {5, 5}
        };
        Grilla grilla = new Grilla(grillaImposible);
    
        AlgoritmoGenetico ag = new AlgoritmoGenetico(grilla, 20, 10, 0.2);
        ag.ejecutar();
    }
    

    @Test
    public void testNoLanzaExcepcionConParametrosMinimos() {
        AlgoritmoGenetico ag = new AlgoritmoGenetico(grilla, 1, 1, 0.0);
        Camino camino = ag.ejecutar();
        assertNotNull("Debe ejecutarse incluso con mínima configuración", camino);
    }

    @Test
    public void testGeneraPoblacionInicialCorrectamente() {
        Grilla grilla = mock(Grilla.class);
        when(grilla.getFilas()).thenReturn(3);
        when(grilla.getColumna()).thenReturn(4);
        when(grilla.getCargaElectricaEn(anyInt(), anyInt())).thenReturn(1);

        AlgoritmoGenetico ag = new AlgoritmoGenetico(grilla, 10, 10, 0.2);
        Camino camino = ag.ejecutar();

        assertNotNull(camino);
        assertTrue("Debe generar una población inicial y retornar algún camino", camino.longitud() > 0);
    }
}
