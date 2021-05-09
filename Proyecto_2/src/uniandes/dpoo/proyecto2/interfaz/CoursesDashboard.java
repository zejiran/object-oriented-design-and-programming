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

public class CoursesDashboard {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Dashboard cursos");
		frame.setSize(900, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar mb = new JMenuBar();
		JMenu m1 = new JMenu("<- Regresar");
		JMenu m2 = new JMenu("Cargar archivo de información de materias");
		JMenu m3 = new JMenu("Seleccionar semestre");
		mb.add(m1);
		mb.add(m2);
		mb.add(m3);
		JMenuItem m31 = new JMenuItem("Primero");
		JMenuItem m32 = new JMenuItem("Segundo");
		JMenuItem m33 = new JMenuItem("Tercero");
		JMenuItem m34 = new JMenuItem("Cuarto");
		JMenuItem m35 = new JMenuItem("Quinto");
		JMenuItem m36 = new JMenuItem("Sexto");
		JMenuItem m37 = new JMenuItem("Séptimo");
		JMenuItem m38 = new JMenuItem("Octavo");
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

		String data[][] = { { "ISIS-1001 Introducción a Ingeniería de Sistemas", "ISIS-1010", "MATE-3100 o ISIS-3000",
				"3", "8 semanas, Tipo E", "Aprobado en 202101", "A" }, { "", "", "", "", "", "", "" } };
		String column[] = { "Curso", "Prerrequisitos", "Correquisistos", "Créditos", "Detalles", "Estado", "Nota" };
		JTable jt = new JTable(data, column);
		JScrollPane sp = new JScrollPane(jt);

		panel.add(sp);
	}

}
