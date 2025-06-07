package AlgoritmoGenetico;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import modelo.Grilla;

public class IndividuoTest {

    private Grilla grillaMock;

    @Before
    public void setUp() {
        configurarGrillaMock(3, 4);
    }

    private void configurarGrillaMock(int filas, int columnas) {
        grillaMock = mock(Grilla.class);
        when(grillaMock.getFilas()).thenReturn(filas);
        when(grillaMock.getColumna()).thenReturn(columnas);

        when(grillaMock.getCargaElectricaEn(anyInt(), anyInt()))
            .thenAnswer(invocation -> {
                int fila = invocation.getArgument(0);
                int col = invocation.getArgument(1);
                return ((fila + col) % 2 == 0) ? 1 : -1;
            });
    }

    @Test
    public void testConstructorYGetters() {
        List<String> movimientos = Arrays.asList(
            "Derecha", "Derecha", "Derecha",
            "Abajo", "Abajo"
        );
        Individuo individuo = new Individuo(movimientos, grillaMock);

        assertEquals(movimientos, individuo.getMovimientos());
        assertNotNull(individuo.getCamino());
        assertEquals(Math.abs(individuo.getCamino().getSuma()), individuo.getFitness());
    }

    @Test
    public void testGenerarAleatorio() {
        Random random = new Random(42);

        int pasosDerecha = 3;
        int pasosAbajo = 2;
        Individuo aleatorio = Individuo.generarAleatorio(pasosDerecha, pasosAbajo, grillaMock, random);

        assertNotNull(aleatorio);
        assertEquals(pasosDerecha + pasosAbajo, aleatorio.getMovimientos().size());
        assertEquals(Math.abs(aleatorio.getCamino().getSuma()), aleatorio.getFitness());
    }

    @Test
    public void testMutarConIntercambio() {
        Random random = new Random(1);

        List<String> movimientos = Arrays.asList(
            "Derecha", "Derecha", "Derecha",
            "Abajo", "Abajo"
        );
        Individuo original = new Individuo(movimientos, grillaMock);
        Individuo mutado = original.mutar(random);

        assertNotNull(mutado);
        assertNotEquals(original.getMovimientos(), mutado.getMovimientos());
        assertEquals(original.getMovimientos().size(), mutado.getMovimientos().size());
    }

    @Test
    public void testRecombinar() {
        Random random = new Random(2);

        List<String> mov1 = Arrays.asList("Derecha", "Derecha", "Derecha", "Abajo", "Abajo");
        List<String> mov2 = Arrays.asList("Abajo", "Abajo", "Derecha", "Derecha", "Derecha");

        Individuo padre1 = new Individuo(mov1, grillaMock);
        Individuo padre2 = new Individuo(mov2, grillaMock);

        Individuo hijo = Individuo.recombinar(padre1, padre2, random, grillaMock);

        assertNotNull(hijo);
        assertEquals(padre1.getMovimientos().size(), hijo.getMovimientos().size());

        int derecha = (int) hijo.getMovimientos().stream().filter(m -> m.equals("Derecha")).count();
        int abajo = (int) hijo.getMovimientos().stream().filter(m -> m.equals("Abajo")).count();

        assertEquals(3, derecha);
        assertEquals(2, abajo);
    }

    @Test
    public void testEstaBalanceado() {
        List<String> movimientos = Arrays.asList("Derecha", "Derecha", "Derecha", "Abajo", "Abajo");
        Individuo individuo = new Individuo(movimientos, grillaMock);

        assertTrue(individuo.estaBalanceado());
    }
    
    @Test
    public void testConGrillaMasGrande() {
        configurarGrillaMock(5, 6);

        List<String> movimientos = new ArrayList<>();
        for (int i = 0; i < 5; i++) movimientos.add("Derecha");
        for (int i = 0; i < 4; i++) movimientos.add("Abajo");

        Individuo individuo = new Individuo(movimientos, grillaMock);

        assertEquals(9, individuo.getMovimientos().size());
        assertTrue(individuo.estaBalanceado());
    }
    
}