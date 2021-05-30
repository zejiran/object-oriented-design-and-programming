package uniandes.dpoo.proyecto2.interfaz;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class StudentProgress {
	
	public static void main(String[] args) {
		JMenuBar mb = new JMenuBar();
		JMenu m1 = new JMenu("<- Regresar");
		mb.add(m1);
		
		JFrame frame = new JFrame("Avance de estudiante");
		frame.setSize(750, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		JPanel listPanel = new JPanel();
		placeComponents(panel, listPanel);
		frame.add(mb, BorderLayout.NORTH);
		frame.add(panel, BorderLayout.CENTER);
		frame.add(listPanel, BorderLayout.SOUTH);

		frame.setVisible(true);
	}

	private static void placeComponents(JPanel panel, JPanel listPanel) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		listPanel.setLayout(new GridLayout(2, 2));

		// Mensaje
		JLabel aText = new JLabel("Juan Alegría");
		aText.setFont(aText.getFont().deriveFont(Font.BOLD, 14f));
		aText.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(aText);
		
		JLabel bText = new JLabel("202011282");
		bText.setFont(bText.getFont().deriveFont(Font.BOLD, 14f));
		bText.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(bText);
		
		JLabel cText = new JLabel("Promedio actual: 2.76");
		cText.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(cText);
		
		JLabel dText = new JLabel("Estado: Primíparo en prueba académica a punto de retirar carrera.");
		dText.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(dText);
		
		JLabel eText = new JLabel("Candidato a grado: No");
		eText.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(cText);

		// Listado
		JLabel headerA = new JLabel("Aprobadas");
		listPanel.add(headerA);

		JLabel headerB = new JLabel("Requisitos por aprobar");
		listPanel.add(headerB);
		
		JLabel elementA = new JLabel("ISIS-1001 Introducción a Ingeniería de Sistemas");
		listPanel.add(elementA);

		JLabel elementB = new JLabel("ISIS-1226 Diseño y programación orientada a objetos");
		listPanel.add(elementB);
	}
	
}
