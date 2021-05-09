package uniandes.dpoo.proyecto2.interfaz;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class PensumLoader {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Carga de pensúm");
		frame.setSize(350, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("<- Regresar");
        mb.add(m1);

		JPanel panel = new JPanel();
		JPanel buttonPanel = new JPanel();
		placeComponents(panel, buttonPanel);
		frame.add(mb, BorderLayout.NORTH);
		frame.add(panel, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);

		frame.setVisible(true);
	}

	private static void placeComponents(JPanel panel, JPanel buttonPanel) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		buttonPanel.setLayout(new GridLayout(3, 2));

		// Mensaje
		JLabel bText = new JLabel("Carga de pensúm");
		bText.setAlignmentX(Component.CENTER_ALIGNMENT);
		bText.setFont(bText.getFont().deriveFont(Font.BOLD, 14f));
		panel.add(bText);

		JLabel sText = new JLabel("Solo se admiten archivos de tipo .PDF");
		sText.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(sText);

		// Botones
		JButton aButton = new JButton("Seleccionar archivo");
		buttonPanel.add(aButton);
	}

}
