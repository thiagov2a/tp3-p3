package modelo;

public class Grilla {

	private int celdas [][];
	
	public Grilla (int [][] celdas) {
		
		this.celdas = celdas;
	}
	
	public int getFilas() {
		return celdas.length;
	}
	
	public int getColumna() {
		return celdas[0].length;
	}
	
	public int getCargaElectricaEn(int fila, int columna) {
	    return celdas[fila][columna];
	}

	
	  public int[][] getCeldas() {
	        return celdas;
	    }
}
