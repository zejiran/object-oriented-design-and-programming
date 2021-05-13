package uniandes.dpoo.taller1.interfaz;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uniandes.dpoo.taller1.modelo.Libro;

/**
 * Panel donde se muestran una lista de libros: pueden ser los libros de una
 * categoría pero también pueden ser los libros resultado de una búsqueda.
 */
@SuppressWarnings("serial")
public class PanelLibros extends JPanel implements ListSelectionListener
{
	// ************************************************************************
	// Atributos
	// ************************************************************************

	/**
	 * Ventana que contiene al panel
	 */
	private InterfazLibreria ventana;

	// ************************************************************************
	// Componentes gráficos
	// ************************************************************************

	/**
	 * El componente que le muestra la lista de libros al usuario
	 */
	private JList<Libro> listaLibros;

	/**
	 * El modelo con la información que se muestra en el componente listaLibros
	 */
	private DefaultListModel<Libro> modeloLibros;

	// ************************************************************************
	// Constructores
	// ************************************************************************

	/**
	 * Construye un nuevo panel para mostrar la lista de libros en una categoría
	 * 
	 * @param interfazLibreria La ventana dentro de la que se encuentra el panel
	 */
	public PanelLibros(InterfazLibreria interfazLibreria)
	{

		ventana = interfazLibreria;
		setBorder(new TitledBorder("Libros"));
		setLayout(new BorderLayout());
		modeloLibros = new DefaultListModel<Libro>();
		listaLibros = new JList<>(modeloLibros);
		listaLibros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaLibros.addListSelectionListener(this);

		JScrollPane panel = new JScrollPane(listaLibros);
		add(panel);
	}

	// ************************************************************************
	// Métodos
	// ************************************************************************

	/**
	 * Actualiza la lista de libros que se muestran al usuario
	 * 
	 * @param libros La nueva lista de libros que se debe mostrar.
	 */
	public void actualizarLibros(ArrayList<Libro> libros)
	{
		modeloLibros.clear();
		for (Libro libro : libros)
		{
			modeloLibros.addElement(libro);
		}
	}

	// ************************************************************************
	// Métodos implementados de la interfaz ListSelectionListener
	// ************************************************************************

	/**
	 * Este método es el que se invoca cuando se selecciona un nuevo libro en la
	 * lista.
	 *
	 * Al hacer una selección, se invoca al método mostrarLibro de InterfazLibreria
	 */
	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		if (!e.getValueIsAdjusting())
		{
			Libro libro = listaLibros.getSelectedValue();
			if (libro != null)
				ventana.mostrarLibro(libro);
		}
	}
}
