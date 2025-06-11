package main.java.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.java.dto.CaminoDTO;
import main.java.dto.CeldaDTO;
import main.java.dto.GrillaDTO;
import main.java.interfaz.IVistaControlador;

public class VistaGrilla {

	private JFrame frame;
	private JPanel panelGrilla;
	private JPanel panelBotones;
	private JButton btnCargarJson;
	private JButton btnCargarGrilla;
	private JButton btnEjecutar;
	private IVistaControlador controlador;

	public VistaGrilla() {
		inicializar();
	}

	private void inicializar() {
		frame = new JFrame("Visualización de Grilla");
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());

		construirPanelGrilla();
		construirPanelBotones();

		frame.add(panelGrilla, BorderLayout.CENTER);
		frame.add(panelBotones, BorderLayout.SOUTH);
	}

	private void construirPanelGrilla() {
		panelGrilla = new JPanel();
	}

	private void construirPanelBotones() {
		panelBotones = new JPanel(new FlowLayout());

		btnCargarJson = crearBoton("Cargar Json", () -> controlador.cargarGrillaDesdeArchivo());
		btnCargarGrilla = crearBoton("Cargar Grilla", () -> controlador.cargarGrillaAleatoria());
		btnEjecutar = crearBoton("Ejecutar Algoritmo", () -> controlador.ejecutarAlgoritmo());

		panelBotones.add(btnCargarJson);
		panelBotones.add(btnCargarGrilla);
		panelBotones.add(btnEjecutar);
	}

	private JButton crearBoton(String texto, Runnable accion) {
		JButton boton = new JButton(texto);
		boton.addActionListener(e -> accion.run());
		return boton;
	}

	public void mostrar() {
		frame.setVisible(true);
	}

	public void actualizarGrilla(GrillaDTO grillaDTO) {
		panelGrilla.removeAll();

		int filas = grillaDTO.obtenerFilas();
		int columnas = grillaDTO.obtenerColumnas();
		CeldaDTO[][] celdas = grillaDTO.obtenerCeldas();

		panelGrilla.setLayout(new GridLayout(filas, columnas));

		for (int fila = 0; fila < filas; fila++) {
			for (int columna = 0; columna < columnas; columna++) {
				CeldaDTO celda = celdas[fila][columna];
				panelGrilla.add(crearPanelCelda(celda));
			}
		}

		panelGrilla.revalidate();
		panelGrilla.repaint();
	}

	public void actualizarGrilla(GrillaDTO grillaDTO, CaminoDTO caminoDTO) {
		List<CeldaDTO> pasos = caminoDTO.obtenerPasos();
		int columnas = grillaDTO.obtenerColumnas();

		for (CeldaDTO celda : pasos) {
			int fila = celda.obtenerFila();
			int columna = celda.obtenerColumna();
			int indice = (fila * columnas) + columna;

			JPanel celdaPanel = (JPanel) panelGrilla.getComponent(indice);
			celdaPanel.setBackground(colorPorCarga(0));
		}

		panelGrilla.revalidate();
		panelGrilla.repaint();
	}

	private JPanel crearPanelCelda(CeldaDTO celda) {
		JPanel panel = new JPanel(new BorderLayout());
		
		Integer carga = celda.obtenerCarga();
		JLabel label = new JLabel(carga.toString());

		label.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);

		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.setBackground(colorPorCarga(celda.obtenerCarga()));
		panel.add(label, BorderLayout.CENTER);

		return panel;
	}

	private Color colorPorCarga(int carga) {
		return switch (carga) {
			case 1 -> Color.GREEN;
			case -1 -> Color.RED;
			default -> Color.LIGHT_GRAY;
		};
	}

	public void mostrarMensaje(int tipo, String titulo, String mensaje) {
		JOptionPane.showMessageDialog(frame, mensaje, titulo, tipo);
	}

	public void colocarControlador(IVistaControlador controlador) {
		this.controlador = controlador;
	}
}
