package uniandes.dpoo.proyecto2.interfaz;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Inicio {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Inicio");
		frame.setSize(350, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		placeComponents(panel);
		frame.add(panel, BorderLayout.CENTER);

		frame.setVisible(true);
	}

	private static void placeComponents(JPanel panel) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		// Mensaje
		JLabel bText = new JLabel("Bienvenido");
		bText.setAlignmentX(Component.CENTER_ALIGNMENT);
		bText.setFont(bText.getFont().deriveFont(Font.BOLD, 14f));
		panel.add(bText);

		JLabel sText = new JLabel("Selecciona el tipo de usuario");
		sText.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(sText);

		// Botón estudiante
		JButton studentButton = new JButton("Estudiante");
		studentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(studentButton);

		// Botón coordinador
		JButton coordinatorButton = new JButton("Coordinador");
		coordinatorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(coordinatorButton);
	}

}
