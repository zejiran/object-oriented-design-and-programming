package uniandes.dpoo.taller1.modelo;

import java.util.ArrayList;

/**
 * Esta clase representa a una categoría de libros dentro de la librería. Cada
 * categoría tiene un nombre único y además sabe si es una categoría a la que
 * pertenecen libros de ficción o no.
 */
public class Categoria
{
	// ************************************************************************
	// Atributos
	// ************************************************************************

	/**
	 * Nombre de la categoría
	 */
	private String nombre;

	/**
	 * Indica si la categoría corresponde a libros de ficción o no
	 */
	private boolean ficcion;

	/**
	 * Lista de libros que hacen parte de la categoría
	 */
	/*
	 * TODO Parte 3 - agregar una asociación a la clase Libro llamada libros, que
	 * sea una lista de Libro
	 */

	// ************************************************************************
	// Constructores
	// ************************************************************************

	public Categoria(String nombre, boolean ficcion)
	{
		this.nombre = nombre;
		this.ficcion = ficcion;
		/*
		 * TODO Parte 3 - inicializa la lista de libros con una lista vacía. Si esto no
		 * se hace al construir la categoría, la lista de libros sería null y todas las
		 * instrucciones para agregar libros fallarían.
		 */
	}

	// ************************************************************************
	// Métodos para consultar los atributos
	// ************************************************************************

	/**
	 * Consulta el nombre de la categoría
	 * 
	 * @return Categoria
	 */
	public String darNombre()
	{
		return nombre;
	}

	/**
	 * Consulta si esta es una categoría de ficción o no, con base en el atributo
	 * ficcion.
	 * 
	 * @return ficcion
	 */
	public boolean esFiccion()
	{
		return ficcion;
	}

	/**
	 * Retorna la lista de libros que hacen parte de la categoría
	 * 
	 * @return libros
	 */
	public ArrayList<Libro> darLibros()
	{
		// TODO Parte 3 - completar el método de acuerdo a la documentación
		return null;
	}

	// ************************************************************************
	// Otros métodos
	// ************************************************************************

	/**
	 * Agrega un nuevo libro a la categoría
	 * 
	 * @param nuevoLibro El nuevo libro que se va a agregar.
	 */
	public void agregarLibro(Libro nuevoLibro)
	{
		// TODO Parte 3 - completar el método de acuerdo a la documentación
	}

	/**
	 * Cuenta la cantidad de libros en la categoría
	 * 
	 * @return Cantidad de libros
	 */
	public int contarLibrosEnCategoria()
	{
		// TODO Parte 3 - completar el método de acuerdo a la documentación
		// En este punto no debería tener que hacer ningún recorrido
		return 0;
	}

	/**
	 * Calcula la calificación promedio de los libros que pertenecen a la categoría
	 * 
	 * @return Calificación promedio
	 */
	public double calificacionPromedio()
	{
		// TODO Parte 3 - completar el método de acuerdo a la documentación
		// En este punto tendrá que recorrer la lista de libros
		return 0.0;
	}

	/**
	 * Consulta si en la categoría hay algún libro escrito por el autor indicado.
	 * 
	 * La búsqueda del autor se hace de forma exacta (tiene que ser idéntico al
	 * valor indicado en el parámetro nombreAutor).
	 * 
	 * @param nombreAutor El nombre del autor para el que se quiere hacer la
	 *                    búsqueda.
	 * @return Retorna true si hay al menos un libro cuyo autor es el autor buscado.
	 *         Retorna false de lo contrario.
	 */
	public boolean hayLibroDeAutor(String nombreAutor)
	{
		// TODO Parte 3 - completar el método de acuerdo a la documentación
		return false;
	}

	/**
	 * Busca en la categoría los libros escritos por el autor indicado.
	 * 
	 * El nombre del autor puede estar incompleto, y la búsqueda no debe tener en
	 * cuenta mayúsculas y minúsculas. Por ejemplo, si se buscara por "ulio v"
	 * deberían encontrarse los libros donde el autor sea "Julio Verne".
	 * 
	 * @param cadenaAutor La cadena que se usará para consultar el autor. No
	 *                    necesariamente corresponde al nombre completo de un autor.
	 * @return Una lista con todos los libros cuyo autor coincida con la cadena
	 *         indicada
	 */
	public ArrayList<Libro> buscarLibrosDeAutor(String nombreAutor)
	{
		// TODO Parte 3 - completar el método de acuerdo a la documentación
		// Recuerde retornar una lista nueva (no la lista del atributo libros)
		return null;
	}

	// ************************************************************************
	// Métodos sobrecargados
	// ************************************************************************

	/**
	 * Este método permite representar una categoría como una cadena de caracteres
	 */
	@Override
	public String toString()
	{
		return nombre;
	}

}
