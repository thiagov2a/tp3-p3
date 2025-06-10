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

		panelGrilla = new JPanel();
		frame.add(panelGrilla, BorderLayout.CENTER);

		JPanel panelBotones = new JPanel(new FlowLayout());
		btnCargarJson = new JButton("Cargar Json");
		btnCargarGrilla = new JButton("Cargar Grilla");
		btnEjecutar = new JButton("Ejecutar Algoritmo");

		panelBotones.add(btnCargarJson);
		panelBotones.add(btnCargarGrilla);
		panelBotones.add(btnEjecutar);
		frame.add(panelBotones, BorderLayout.SOUTH);

		btnCargarJson.addActionListener(e -> controlador.cargarGrillaDesdeArchivo());
		btnCargarGrilla.addActionListener(e -> controlador.cargarGrillaAleatoria());
		btnEjecutar.addActionListener(e -> controlador.ejecutarAlgoritmo());
	}

	public void mostrar() {
		frame.setVisible(true);
	}

	public void actualizarGrilla(GrillaDTO grillaDTO) {
		panelGrilla.removeAll();

		int filas = grillaDTO.obtenerFilas();
		int columnas = grillaDTO.obtenerColumnas();

		panelGrilla.setLayout(new GridLayout(filas, columnas));

		CeldaDTO[][] celdas = grillaDTO.obtenerCeldas();

		for (int i = 0; i < celdas.length; i++) {
			for (int j = 0; j < celdas[i].length; j++) {
				CeldaDTO celda = celdas[i][j];
				JPanel celdaPanel = new JPanel(new BorderLayout());
				Integer celdaCarga = celda.obtenerCarga();
				JLabel celdaLabel = new JLabel(celdaCarga.toString());

				celdaLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
				celdaLabel.setHorizontalAlignment(SwingConstants.CENTER);
				celdaLabel.setVerticalAlignment(SwingConstants.CENTER);

				celdaPanel.add(celdaLabel, BorderLayout.CENTER);
				celdaPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				celdaPanel.setBackground(colorPorCarga(celda.obtenerCarga()));

				panelGrilla.add(celdaPanel);
			}
		}

		panelGrilla.revalidate();
		panelGrilla.repaint();
	}
	
	public void actualizarGrilla(GrillaDTO grillaDTO, CaminoDTO caminoDTO) {
		List<CeldaDTO> pasos = caminoDTO.obtenerPasos();
		int numeroDeColumnas = grillaDTO.obtenerColumnas();
		for (CeldaDTO celdaDTO : pasos) {
			int fila = celdaDTO.obtenerFila();
			int columna = celdaDTO.obtenerColumna();
			int indice = (fila * numeroDeColumnas) + columna;
			JPanel celdaPanel = (JPanel) panelGrilla.getComponent(indice);
			celdaPanel.setBackground(colorPorCarga(0));
			panelGrilla.add(celdaPanel, indice);
		}
	}

	private Color colorPorCarga(int carga) {
		return switch (carga) {
		case 1 -> Color.GREEN;
		case -1 -> Color.RED;
		default -> Color.LIGHT_GRAY;
		};
	}

	// "tipo" puede tener los valores: 0 (error), 1 (information), 2 (warning)
	public void mostrarMensaje(int tipo, String titulo, String mensaje) {
		JOptionPane.showMessageDialog(frame, mensaje, titulo, tipo, null);
	}

	public void colocarControlador(IVistaControlador controlador) {
		this.controlador = controlador;
	}
}
