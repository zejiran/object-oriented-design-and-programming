package uniandes.dpoo.taller1.modelo;

/**
 * Esta clase agrupa la información sobre un libro disponible en la librería
 */
public class Libro {

	// ************************************************************************
	// Atributos
	// ************************************************************************

	/**
	 * Título del libro
	 */
	private String titulo;

	/**
	 * Autor o autores del libro
	 */
	private String autor;

	/**
	 * Calificación obtenida por el libro en el sitio bookdepository.com
	 */
	private double calificacion;

	/**
	 * Categoría a la que pertenece el libro
	 */
	private Categoria categoria;

	/**
	 * Imagen con la portada del libro
	 */
	private Imagen portada;

	// ************************************************************************
	// Constructores
	// ************************************************************************

	/**
	 * Construye un nuevo libro, sin portada, a partir de los parámetros.
	 * 
	 * La portada se inicializa en null.
	 * 
	 * Además de inicializar los atributos del libro, agrega el libro que se está
	 * creando a la categoría usando el método agregarLibro de la clase Categoría.
	 * 
	 * @param elTitulo       Título del libro
	 * @param elAutor        Autor o autores del libro
	 * @param laCalificacion Calificación obtenida por el libro en
	 *                       bookdepository.com
	 * @param laCategoria    Categoría a la que pertenece el libro
	 */
	public Libro(String elTitulo, String elAutor, double laCalificacion, Categoria laCategoria) {
		titulo = elTitulo;
		autor = elAutor;
		calificacion = laCalificacion;
		categoria = laCategoria;
		categoria.agregarLibro(this);
	}

	// ************************************************************************
	// Métodos para consultar los atributos
	// ************************************************************************

	/**
	 * Consulta el título del libro
	 * 
	 * @return titulo
	 */
	public String darTitulo() {
		return titulo;
	}

	/**
	 * Consulta el autor del libro
	 * 
	 * @return autor
	 */
	public String darAutor() {
		return autor;
	}

	/**
	 * Consulta la calificación del libro en bookdepository.com
	 * 
	 * @return calificacion
	 */
	public double darCalificacion() {
		return calificacion;
	}

	/**
	 * Consulta la categoría del libro
	 * 
	 * @return categoria
	 */
	public Categoria darCategoria() {
		return categoria;
	}

	/**
	 * Consulta la imagen con la portada del libro. Si el libro no tiene una portada
	 * debe retornar la imagen por defecto de 85x85 pixeles que se encuentra en el
	 * archivo "./data/imagenes/missing.png"
	 * 
	 * @return La portada del libro o un objeto de tipo Imagen con la imagen del
	 *         archivo "./data/imagenes/missing.png" -> Esta ruta es incorrecta.
	 */
	public Imagen darPortada() {
		Imagen portadaDefecto = new Imagen("./imagenes/missing.png", 85, 85);
		if (portada != null) {
			return this.portada;
		}

		return portadaDefecto;
	}

	// ************************************************************************
	// Otros métodos
	// ************************************************************************

	/**
	 * Modifica la portada del libro
	 * 
	 * @param nuevaPortada Nueva portada para el libro
	 */
	public void cambiarPortada(Imagen nuevaPortada) {
		this.portada = nuevaPortada;
	}

	/**
	 * Permite saber si este libro tiene portada.
	 * 
	 * @return Retorna true si el libro tiene una portada. Retorna false en caso
	 *         contrario.
	 */
	public boolean tienePortada() {
		if (portada == null) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return titulo + " (" + autor + ")";
	}
}
