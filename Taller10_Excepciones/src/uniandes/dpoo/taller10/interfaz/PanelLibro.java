package uniandes.dpoo.taller1.interfaz;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import uniandes.dpoo.taller1.modelo.Imagen;
import uniandes.dpoo.taller1.modelo.Libro;

/**
 * Panel donde se muestran la información de un libro
 */
@SuppressWarnings("serial")
public class PanelLibro extends JPanel
{
	// ************************************************************************
	// Atributos
	// ************************************************************************

	/**
	 * El libro que se muestra en un momento dado en el panel
	 */
	private Libro libro;

	// ************************************************************************
	// Componentes gráficos
	// ************************************************************************

	/**
	 * El campo de texto donde se muestra el título de un libro
	 */
	private JTextField txtTitulo;

	/**
	 * El campo de texto donde se muestra el autor de un libro
	 */
	private JTextField txtAutor;

	/**
	 * El campo de texto donde se muestra la categoría de un libro
	 */
	private JTextField txtCategoria;

	/**
	 * El campo de texto donde se muestra la calificación de un libro
	 */
	private JTextField txtCalificacion;

	/**
	 * Una etiqueta que se utiliza para mostrar la portada del libro
	 */
	private JLabel labPortada;

	/**
	 * Una etiqueta que se utiliza para mostrar la información de la imagen con la
	 * portada del libro
	 */
	private JLabel labDetallesImagen;

	// ************************************************************************
	// Constructores
	// ************************************************************************

	/**
	 * Construye un nuevo panel para mostrar la información de un libro
	 */
	public PanelLibro()
	{
		setBorder(new TitledBorder("Libro"));

		txtTitulo = new JTextField(" ");
		txtTitulo.setEditable(false);
		txtAutor = new JTextField(" ");
		txtAutor.setEditable(false);
		txtCategoria = new JTextField(" ");
		txtCategoria.setEditable(false);
		txtCalificacion = new JTextField(" ");
		txtCalificacion.setEditable(false);
		labPortada = new JLabel("", SwingConstants.CENTER);

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0);

		add(new JLabel("Título:"), gc);
		gc.gridy++;
		add(new JLabel("Autor:"), gc);
		gc.gridy++;
		add(new JLabel("Categoría:"), gc);
		gc.gridy++;
		add(new JLabel("Calificación:"), gc);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.weightx = 10;
		add(txtTitulo, gc);

		gc.gridy++;
		add(txtAutor, gc);

		gc.gridy++;
		add(txtCategoria, gc);

		gc.gridy++;
		add(txtCalificacion, gc);

		gc.gridx = 0;
		gc.gridy++;
		gc.gridwidth = 2;
		gc.gridheight = 1;
		gc.weighty = 10;
		gc.fill = GridBagConstraints.BOTH;
		add(labPortada, gc);

		labDetallesImagen = new JLabel("", SwingConstants.LEFT);
		Font font = labDetallesImagen.getFont();
		font = font.deriveFont((float) (font.getSize() * 0.8));
		labDetallesImagen.setFont(font);

		gc.gridx = 0;
		gc.gridy++;
		gc.gridwidth = 2;
		gc.gridheight = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.BOTH;
		add(labDetallesImagen, gc);
	}

	// ************************************************************************
	// Métodos
	// ************************************************************************

	/**
	 * Cambia el libro cuya información se muestra en el panel
	 * 
	 * @param nuevolibro El nuevo libro para el cual se debe mostrar la información
	 */
	public void actualizarLibro(Libro nuevolibro)
	{
		libro = nuevolibro;
		if (libro != null)
		{
			txtTitulo.setText(nuevolibro.darTitulo());
			txtAutor.setText(nuevolibro.darAutor());
			txtCategoria.setText(nuevolibro.darCategoria().darNombre());
			txtCalificacion.setText("" + nuevolibro.darCalificacion());
			Imagen portada = nuevolibro.darPortada();
			labPortada.setIcon(new ImageIcon("./data/" + portada.darRutaArchivo()));
			labDetallesImagen.setText(portada.darRutaArchivo() + ": " + portada.darAncho() + "x" + portada.darAlto());
		}
		else
		{
			txtTitulo.setText("");
			txtAutor.setText("");
			txtCategoria.setText("");
			txtCalificacion.setText("");
			labPortada.setIcon(null);
			labDetallesImagen.setText("");
		}
	}
}
