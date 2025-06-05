package main.java.interfaz;

import main.java.dto.ResultadoCamino;
import main.java.modelo.Grilla;

public interface IBusquedaCamino {

	ResultadoCamino buscar(Grilla grilla);
}
