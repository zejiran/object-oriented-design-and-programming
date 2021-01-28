package uniandes.dpoo.taller0.modelo;

/**
 * Esta clase relaciona un atleta con un evento para indicar que el atleta
 * participó en ese evento y obtuvo un cierto resultado. Esta clase tiene
 * también la información del resultado que obtuvo el atleta y de la edad que
 * tenía cuando se realizó la competición.
 */
public class Participacion
{
	// ************************************************************************
	// Atributos
	// ************************************************************************

	/**
	 * El resultado obtenido por el atleta en el evento
	 */
	private Medalla resultado;

	/**
	 * La edad que tenía el atleta cuando participó en el evento
	 */
	private int edad;

	/**
	 * El atleta que participó en el evento
	 */
	private Atleta atleta;

	/**
	 * El evento en el que participó el atleta
	 */
	private Evento evento;

	// ************************************************************************
	// Constructores
	// ************************************************************************

	/**
	 * Construye una nueva participación e incializa los atributos a partir de los
	 * parámetros.
	 * 
	 * @param elAtleta    El atleta
	 * @param edad        La edad del atleta cuando participó en el evento
	 * @param elEvento    El evento
	 * @param elResultado El resultado obtenido por el atleta en el evento
	 */
	public Participacion(Atleta elAtleta, int edad, Evento elEvento, Medalla elResultado)
	{
		this.atleta = elAtleta;
		this.edad = edad;
		this.evento = elEvento;
		this.resultado = elResultado;
	}

	// ************************************************************************
	// Métodos para consultar los atributos
	// ************************************************************************

	/**
	 * Retorna el atleta asociado a la participación
	 * 
	 * @return atleta
	 */
	public Atleta darAtleta()
	{
		return atleta;
	}

	/**
	 * Retorna la edad del atleta cuando participó en el evento
	 * 
	 * @return edad
	 */
	public int darEdad()
	{
		return edad;
	}

	/**
	 * Retorna el evento asociado a la participación
	 * 
	 * @return evento
	 */
	public Evento darEvento()
	{
		return evento;
	}

	/**
	 * Retorna el resultado obtenido por el atleta durante su participación en el
	 * evento
	 * 
	 * @return resultadoParticipacion
	 */
	public Medalla darResultado()
	{
		return resultado;
	}

	// ************************************************************************
	// Otros métodos
	// ************************************************************************

	/**
	 * Revisa si al participar en el evento el atleta obtuvo alguna medalla
	 * 
	 * @return Retorna true si el resultado registrado corresponde a una medalla
	 */
	public boolean ganoMedalla()
	{
		return !resultado.equals(Medalla.NA);
	}

}
