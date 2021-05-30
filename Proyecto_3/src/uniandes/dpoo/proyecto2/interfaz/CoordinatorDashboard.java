package uniandes.dpoo.proyecto2.interfaz;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CoordinatorDashboard {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Dashboard de coordinador");
		frame.setSize(350, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		JPanel buttonPanel = new JPanel();
		placeComponents(panel, buttonPanel);
		frame.add(panel, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.CENTER);

		frame.setVisible(true);
	}

	private static void placeComponents(JPanel panel, JPanel buttonPanel) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		buttonPanel.setLayout(new GridLayout(3, 2));

		// Mensaje
		JLabel bText = new JLabel("¿Qué deseas realizar?");
		bText.setAlignmentX(Component.CENTER_ALIGNMENT);
		bText.setFont(bText.getFont().deriveFont(Font.BOLD, 14f));
		panel.add(bText);

		JTextField dText = new JTextField("Digite código de estudiante a seleccionar");
		dText.setPreferredSize(new Dimension(200, 24));
		dText.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(dText);

		// Botones
		JButton aButton = new JButton("Cargar pensúm");
		buttonPanel.add(aButton);

		JButton bButton = new JButton("Cargar cursos");
		buttonPanel.add(bButton);

		JButton cButton = new JButton("Ver cursos");
		buttonPanel.add(cButton);

		JButton dButton = new JButton("Crear planes");
		buttonPanel.add(dButton);

		JButton eButton = new JButton("Ver perfil");
		buttonPanel.add(eButton);

		JButton fButton = new JButton("Generar reporte");
		buttonPanel.add(fButton);
	}

}
