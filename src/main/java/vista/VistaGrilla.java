package main.java.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.java.dto.CeldaDTO;
import main.java.dto.GrillaDTO;
import main.java.interfaz.IVistaControlador;

public class VistaGrilla {

	private JFrame frame;
	private JPanel panelGrilla;
	private JButton btnCargar;
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
		btnCargar = new JButton("Cargar Grilla");
		btnEjecutar = new JButton("Ejecutar Algoritmo");

		panelBotones.add(btnCargar);
		panelBotones.add(btnEjecutar);
		frame.add(panelBotones, BorderLayout.SOUTH);

		// Delegar eventos al controlador (las interfaces pueden definirse en el
		// controlador)
		btnCargar.addActionListener(e -> controlador.cargarGrilla());
		btnEjecutar.addActionListener(e -> controlador.ejecutarAlgoritmo());
	}

	public void mostrar() {
		frame.setVisible(true);
	}

	public void actualizarGrilla(GrillaDTO grillaDTO) {
		// Refrescar el panel con la grilla nueva
		panelGrilla.removeAll();

		int filas = grillaDTO.obtenerFilas();
		int columnas = grillaDTO.obtenerColumnas();

		panelGrilla.setLayout(new GridLayout(filas, columnas));

		CeldaDTO[][] celdas = grillaDTO.obtenerCeldas();

		for (int fila = 0; fila < celdas.length; fila++) {
			for (int col = 0; col < celdas[fila].length; col++) {
				CeldaDTO celda = celdas[fila][col];
				JPanel celdaPanel = new JPanel();
				celdaPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				celdaPanel.setBackground(colorPorCarga(celda.obtenerCarga()));
				panelGrilla.add(celdaPanel);
			}
		}

		panelGrilla.revalidate();
		panelGrilla.repaint();
	}

	private Color colorPorCarga(int carga) {
		// Asumamos: +1 verde, -1 rojo, 0 gris
		return switch (carga) {
		case 1 -> Color.GREEN;
		case -1 -> Color.RED;
		default -> Color.LIGHT_GRAY;
		};
	}

	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(frame, mensaje);
	}

	public void colocarControlador(IVistaControlador controlador) {
		this.controlador = controlador;
	}
}
