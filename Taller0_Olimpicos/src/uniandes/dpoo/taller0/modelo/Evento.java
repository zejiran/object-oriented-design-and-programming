package uniandes.dpoo.taller0.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase sirve para encapsular la información sobre los eventos: el
 * deporte, el año de la competencia y los participantes. Un evento corresponde
 * a un deporte para el cual hubo competencia en unos juegos olímpicos. Es decir
 * que el mismo deporte aparece en varios eventos pero en diferentes años.
 */
public class Evento
{
	// ************************************************************************
	// Atributos
	// ************************************************************************

	/**
	 * El nombre del deporte
	 */
	private String deporte;

	/**
	 * El año del evento
	 */
	private int anio;

	/**
	 * La lista con las participaciones en el evento
	 */
	private List<Participacion> participaciones;

	// ************************************************************************
	// Constructores
	// ************************************************************************

	/**
	 * Construye un nuevo evento, inicializando los atributos con los valores de los
	 * parámetros y creando una lista vacía de participaciones.
	 * 
	 * @param deporte El deporte al que corresponde el evento
	 * @param anio    El año de la competencia
	 */
	public Evento(String deporte, int anio)
	{
		this.deporte = deporte;
		this.anio = anio;
		this.participaciones = new ArrayList<Participacion>();
	}

	// ************************************************************************
	// Métodos para consultar los atributos
	// ************************************************************************

	/**
	 * Retorna el nombre del deporte al que corresponde el evento.
	 * 
	 * @return deporte
	 */
	public String darDeporte()
	{
		return deporte;
	}

	/**
	 * Retorna el año en el que se realizó el evento
	 * 
	 * @return anio
	 */
	public int darAnio()
	{
		return anio;
	}

	// ************************************************************************
	// Otros métodos
	// ************************************************************************

	/**
	 * Agrega una nueva participación al evento
	 * 
	 * @param nuevaParticipacion La nueva participación agregada
	 */
	public void agregarParticipacion(Participacion nuevaParticipacion)
	{
		participaciones.add(nuevaParticipacion);
	}

	/**
	 * Retorna una lista con los atletas que participaron en el evento
	 * 
	 * @return Una lista de atletas
	 */
	public List<Atleta> darAtletasEnEvento()
	{
		List<Atleta> atletas = new ArrayList<Atleta>();

		for (Participacion participante : participaciones)
		{
			atletas.add(participante.darAtleta());
		}

		return atletas;
	}

	/**
	 * Construye una lista con los atletas que han ganado al menos una medalla en el
	 * evento.
	 * 
	 * @return Una lista con los medallistas, donde ningún atleta aparece más de una
	 *         vez.
	 */
	public List<Atleta> darMedallistas()
	{
		List<Atleta> medallistas = new ArrayList<>();
		for (Participacion participacion : participaciones)
		{
			if (participacion.ganoMedalla())
			{
				Atleta elAtleta = participacion.darAtleta();
				if (!medallistas.contains(elAtleta))
				{
					medallistas.add(elAtleta);
				}
			}
		}
		return medallistas;
	}

	@Override
	public String toString()
	{
		return deporte + " (" + anio + ")";
	}

}
