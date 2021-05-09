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
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CourseOffer {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Oferta de cursos");
		frame.setSize(900, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar mb = new JMenuBar();
		JMenu m1 = new JMenu("<- Regresar");
		JMenu m3 = new JMenu("Seleccionar semestre");
		mb.add(m1);
		mb.add(m3);
		JMenuItem m31 = new JMenuItem("202010");
		JMenuItem m32 = new JMenuItem("202020");
		JMenuItem m33 = new JMenuItem("202110");
		JMenuItem m34 = new JMenuItem("202120");
		JMenuItem m35 = new JMenuItem("202210");
		JMenuItem m36 = new JMenuItem("202220");
		JMenuItem m37 = new JMenuItem("202310");
		JMenuItem m38 = new JMenuItem("202320");
		m3.add(m31);
		m3.add(m32);
		m3.add(m33);
		m3.add(m34);
		m3.add(m35);
		m3.add(m36);
		m3.add(m37);
		m3.add(m38);

		JPanel panel = new JPanel();
		JPanel buttonPanel = new JPanel();
		placeComponents(panel, buttonPanel);
		frame.add(mb, BorderLayout.NORTH);
		frame.add(panel);

		frame.setVisible(true);
	}

	private static void placeComponents(JPanel panel, JPanel buttonPanel) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		buttonPanel.setLayout(new GridLayout(3, 2));

		// Mensaje
		JLabel bText = new JLabel("Carga de cursos ofrecidos por semestre");
		bText.setAlignmentX(Component.CENTER_ALIGNMENT);
		bText.setFont(bText.getFont().deriveFont(Font.BOLD, 14f));
		panel.add(bText);

		String data[][] = { { "ISIS-1001 Introducción a Ingeniería de Sistemas", "19", "3", "8 semanas",
				"ML, Profesor Doctorazo, Lunes y Martes a las 4AM" }, { "", "", "", "", "" } };
		String column[] = { "Curso", "Sección", "Créditos", "Duración", "Detalles" };
		JTable jt = new JTable(data, column);
		JScrollPane sp = new JScrollPane(jt);

		panel.add(sp);
	}

}
