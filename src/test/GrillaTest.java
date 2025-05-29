import modelo.Grilla;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GrillaTest {

    @Test
    public void testGrillaNoEstaVacia() {
        int[][] matriz = {
            {1, -1},
            {-1, 1}
        };

        Grilla grilla = new Grilla(matriz);

        // Verificamos que las filas y columnas no sean cero
        assertTrue(grilla.getFilas() > 0, "La grilla debe tener al menos una fila");
        assertTrue(grilla.getColumna() > 0, "La grilla debe tener al menos una columna");
    }
}