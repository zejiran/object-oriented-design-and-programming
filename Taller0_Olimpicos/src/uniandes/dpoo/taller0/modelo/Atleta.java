package uniandes.dpoo.taller0.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Esta clase encapsula la información sobre los atletas y calcula algunas cosas
 * sobre sus resultados.
 */
public class Atleta
{
	// ************************************************************************
	// Atributos
	// ************************************************************************

	/**
	 * El nombre del atleta. Se asume que los nombres de los atletas no están
	 * repetidos, pero eso no es un problema para los atletas.
	 */
	private String nombre;

	/**
	 * El género del atleta, basado en la enumeración Genero
	 */
	private Genero genero;

	/**
	 * Una lista con las participaciones registradas para el atleta.
	 */
	private List<Participacion> participaciones;

	/**
	 * El país al que representa el atleta
	 */
	private Pais pais;

	// ************************************************************************
	// Constructores
	// ************************************************************************

	/**
	 * Construye un nuevo atleta e inicializa sus atributos con la información de
	 * los parámetros. La lista de participaciones se inicializa como una lista
	 * vacía.
	 * 
	 * @param elNombre El nombre del atleta.
	 * @param elGenero El género del atleta.
	 * @param elPais   El país al que representa el atleta. No es responsabilidad
	 *                 del atleta agregarse al país.
	 */
	public Atleta(String elNombre, Genero elGenero, Pais elPais)
	{
		this.nombre = elNombre;
		this.genero = elGenero;
		this.participaciones = new ArrayList<>();
		this.pais = elPais;
	}

	// ************************************************************************
	// Métodos para consultar los atributos
	// ************************************************************************

	/**
	 * Consulta el nombre del atleta
	 * 
	 * @return nombre
	 */
	public String darNombre()
	{
		return nombre;
	}

	/**
	 * Consulta el género del atleta
	 * 
	 * @return genero
	 */
	public Genero darGenero()
	{
		return genero;
	}

	/**
	 * Consulta el país del atleta
	 * 
	 * @return pais
	 */
	public Pais darPais()
	{
		return pais;
	}

	// ************************************************************************
	// Otros métodos
	// ************************************************************************

	/**
	 * Registra en el atleta una participación en un evento.
	 * 
	 * @param nuevaParticipacion La participación que se va a registrar.
	 */
	public void agregarParticipacion(Participacion nuevaParticipacion)
	{
		participaciones.add(nuevaParticipacion);
	}

	/**
	 * Compila la información de los eventos en los que ha participado un atleta.
	 * 
	 * @return Una lista de mapas con la información de las participaciones del
	 *         atleta. Cada registro queda en un mapa con tres llaves: "evento", que
	 *         tiene asociado el nombre del evento en el que participó el atleta y
	 *         "anio", que tiene asociado el año en el que el atleta participó en el
	 *         evento; y "nombre" que tiene asociado el nombre del atleta.
	 */
	public List<Map<String, Object>> consultarParticipacionesAtleta()
	{
		List<Map<String, Object>> participacionesAtleta = new ArrayList<Map<String, Object>>();
		for (Participacion participacionAtleta : participaciones)
		{
			int anio = participacionAtleta.darEvento().darAnio();
			String evento = participacionAtleta.darEvento().darDeporte();
			Map<String, Object> registro = new HashMap<String, Object>();
			registro.put("evento", evento);
			registro.put("anio", anio);
			registro.put("nombre", nombre);
			participacionesAtleta.add(registro);
		}
		return participacionesAtleta;
	}

	/**
	 * Calcula la cantidad de medallas que el atleta ha ganado en el evento indicado
	 * 
	 * @param nombreEvento El nombre del evento de interés
	 * @return Un arreglo con tres enteros: la cantidad de medallas de oro, la
	 *         cantidad de medallas de plata y la cantidad de medallas de bronce
	 *         ganadas por el atleta.
	 */
	public int[] darMedallasEnEvento(String nombreEvento)
	{
		int[] medallasEvento = { 0, 0, 0 };
		for (Participacion participacion : participaciones)
		{
			if (participacion.darEvento().darDeporte().equals(nombreEvento))
			{
				if (participacion.darResultado().equals(Medalla.GOLD))
					medallasEvento[0]++;
				else if (participacion.darResultado().equals(Medalla.SILVER))
					medallasEvento[1]++;
				else if (participacion.darResultado().equals(Medalla.BRONZE))
					medallasEvento[2]++;
			}
		}

		return medallasEvento;
	}

	/**
	 * Calcula cuáles fueron las medallas que ganó el atleta en un rango de años
	 * 
	 * @param anioInicial El año inicial para el rango
	 * @param anioFinal   El año final para el rango
	 * @return Una lista con la información de las medallas que ganó el atleta. Cada
	 *         registro es un mapa con tres llaves: "evento", que tiene asociado el
	 *         nombre del evento en el que el atleta ganó una medalla; "anio", que
	 *         tiene asociado el año en el que se llevó a cabo el evento; y
	 *         "medalla", que tiene asociado el tipo de medalla que ganó el atleta
	 *         ("gold", "silver" o "bronze").
	 */
	public List<Map<String, Object>> contarMedallasEnRango(int anioInicial, int anioFinal)
	{
		List<Map<String, Object>> resultado = new ArrayList<Map<String, Object>>();
		for (Participacion participacionAtleta : participaciones)
		{
			Evento evento = participacionAtleta.darEvento();
			int anio = evento.darAnio();
			if (participacionAtleta.ganoMedalla() && anioInicial <= anio && anio <= anioFinal)
			{
				Map<String, Object> registro = new HashMap<String, Object>();
				registro.put("evento", evento.darDeporte());
				registro.put("anio", anio);
				Medalla medalla = participacionAtleta.darResultado();
				registro.put("medalla", medalla.darTipoMedalla());
				resultado.add(registro);
			}
		}

		return resultado;
	}

	/**
	 * Consulta si el atleta ha sido medallista olímpico
	 * 
	 * @return Retorna true si el atleta ha sido medallista al menos en una de sus
	 *         participaciones. Retorna false de lo contrario.
	 */
	public boolean esMedallista()
	{
		boolean esMedallista = false;
		for (int i = 0; i < participaciones.size() && !esMedallista; i++)
		{
			Participacion participacion = participaciones.get(i);
			if (participacion.ganoMedalla())
			{
				esMedallista = true;
			}
		}
		return esMedallista;
	}

	/**
	 * Cuenta la cantidad de medallas que ha ganado el atleta
	 * 
	 * @return Cantidad de medallas ganadas
	 */
	public int contarMedallas()
	{
		int cantidadMedallas = 0;

		for (Participacion participacion : participaciones)
		{
			if (participacion.ganoMedalla())
			{
				cantidadMedallas++;
			}
		}

		return cantidadMedallas;
	}

	/**
	 * Consulta en cuáles eventos el atleta ha ganado una medalla
	 * 
	 * @return Retorna una lista de mapas donde cada mapa tiene la información de
	 *         sus medallas. La información de cada medalla tiene tres llaves:
	 *         "evento", que tiene asociado el nombre del evento; "anio", que tiene
	 *         asociado el año en el que el atleta ganó la medalla; y "medalla" que
	 *         tiene asociado el tipo de medalla.
	 */
	public List<Map<String, Object>> consultarMedallas()
	{
		List<Map<String, Object>> medallas = new ArrayList<Map<String, Object>>();

		for (Participacion participacion : participaciones)
		{
			if (participacion.ganoMedalla())
			{
				Map<String, Object> registroMedalla = new HashMap<String, Object>();
				registroMedalla.put("evento", participacion.darEvento().darDeporte());
				registroMedalla.put("anio", participacion.darEvento().darAnio());
				registroMedalla.put("medalla", participacion.darResultado().darTipoMedalla());
				medallas.add(registroMedalla);
			}
		}

		return medallas;
	}

	/**
	 * Cuenta la cantidad de deportes diferentes en las que ha participado el atleta
	 * 
	 * @return Cantidad de deportes
	 */
	public int contarDeportes()
	{
		Set<String> deportes = new HashSet<String>();
		for (Participacion participacion : participaciones)
		{
			deportes.add(participacion.darEvento().darDeporte());
		}
		return deportes.size();
	}

	// ************************************************************************
	// Métodos sobrecargados de una superclase
	// ************************************************************************

	@Override
	public boolean equals(Object obj)
	{
		if (obj.getClass() != this.getClass())
			return false;
		else
		{
			Atleta otro = (Atleta) obj;
			return this.nombre.equals(otro.nombre);
		}
	}

	@Override
	public int hashCode()
	{
		return nombre.hashCode();
	}

	@Override
	public String toString()
	{
		return nombre;
	}

}
