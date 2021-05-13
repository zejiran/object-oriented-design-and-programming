package uniandes.dpoo.taller1.modelo;

/**
 * Esta clase agrupa la información de una imagen
 */
public class Imagen
{
	// ************************************************************************
	// Atributos
	// ************************************************************************

	/**
	 * Ruta en la que se encuentra el archivo con la imagen
	 */
	private String rutaArchivo;

	/**
	 * Ancho en pixeles de la imagen
	 */
	private int ancho;

	/**
	 * Alto en pixeles de la imagen
	 */
	private int alto;

	// ************************************************************************
	// Constructores
	// ************************************************************************

	/**
	 * Construye una nueva imagen a partir de la información de los parámetros
	 * 
	 * @param rutaArchivo Ruta en la que se encuentra el archivo con la imagen
	 * @param ancho       Ancho en pixeles de la imagen
	 * @param alto        Alto en pixeles de la imagen
	 */
	public Imagen(String rutaArchivo, int ancho, int alto)
	{
		this.rutaArchivo = rutaArchivo;
		this.ancho = ancho;
		this.alto = alto;
	}

	// ************************************************************************
	// Métodos para consultar los atributos
	// ************************************************************************

	/**
	 * Consulta la ruta de la imagen
	 * 
	 * @return rutaArchivo
	 */
	public String darRutaArchivo()
	{
		return rutaArchivo;
	}

	/**
	 * Consulta el ancho de la imagen
	 * 
	 * @return ancho
	 */
	public int darAncho()
	{
		return ancho;
	}

	/**
	 * Consulta el alto de la imagen
	 * 
	 * @return alto
	 */
	public int darAlto()
	{
		return alto;
	}

}
