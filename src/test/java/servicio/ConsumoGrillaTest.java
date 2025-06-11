package test.java.servicio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import main.java.modelo.Grilla;
import main.java.servicio.ConsumoGrilla;

public class ConsumoGrillaTest {

    private Grilla grilla;

    @Before
    public void setUp() {
        grilla = ConsumoGrilla.cargarGrillaDesdeJson("src/test/recursos/grilla_test.json");
    }

    @Test
    public void testCargaGrilla() {
        assertNotNull(grilla);
    }

    @Test
    public void testFilasYColumnasCorrectas() {
        assertEquals(3, grilla.obtenerFilas());
        assertEquals(4, grilla.obtenerColumnas());
    }

    @Test
    public void testCargasDeCeldasCorrectas() {
        assertEquals(1, grilla.obtenerCelda(0, 0).obtenerCarga());
        assertEquals(-1, grilla.obtenerCelda(0, 1).obtenerCarga());
        assertEquals(1, grilla.obtenerCelda(0, 2).obtenerCarga());
        assertEquals(1, grilla.obtenerCelda(0, 3).obtenerCarga());
        
        assertEquals(-1, grilla.obtenerCelda(1, 0).obtenerCarga());
        assertEquals(1, grilla.obtenerCelda(1, 1).obtenerCarga());
        assertEquals(-1, grilla.obtenerCelda(1, 2).obtenerCarga());
        assertEquals(1, grilla.obtenerCelda(1, 3).obtenerCarga());
        
        assertEquals(1, grilla.obtenerCelda(2, 0).obtenerCarga());
        assertEquals(1, grilla.obtenerCelda(2, 1).obtenerCarga());
        assertEquals(-1, grilla.obtenerCelda(2, 2).obtenerCarga());
        assertEquals(1, grilla.obtenerCelda(2, 3).obtenerCarga());
    }
}
