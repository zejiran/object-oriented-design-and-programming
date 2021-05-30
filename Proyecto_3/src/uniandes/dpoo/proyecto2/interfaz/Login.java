package uniandes.dpoo.proyecto2.interfaz;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Login");
		frame.setSize(350, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.add(panel);

		placeComponents(panel);

		frame.setVisible(true);
	}

	private static void placeComponents(JPanel panel) {
		panel.setLayout(null);
		
		// Campo de usuario
		JLabel userLabel = new JLabel("Usuario Uniandes");
		userLabel.setBounds(10, 20, 130, 25);
		panel.add(userLabel);

		JTextField userText = new JTextField(20);
		userText.setBounds(150, 20, 165, 25);
		panel.add(userText);
		
		// Campo de contraseña
		JLabel passwordLabel = new JLabel("Contraseña");
		passwordLabel.setBounds(10, 50, 130, 25);
		panel.add(passwordLabel);
		
		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(150, 50, 165, 25);
		panel.add(passwordText);
		
		// Botón de login
		JButton loginButton = new JButton("Ingresar");
		loginButton.setBounds(10, 80, 130, 25);
		panel.add(loginButton);
	}

}