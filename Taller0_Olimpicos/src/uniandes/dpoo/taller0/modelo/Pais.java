package uniandes.dpoo.taller0.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Esta clase tiene la información sobre un país y sus atletas.
 */
public class Pais
{
	// ************************************************************************
	// Atributos
	// ************************************************************************

	/**
	 * El nombre del país
	 */
	private String nombre;

	/**
	 * La lista de los atletas que han representado al país
	 */
	private List<Atleta> atletas;

	// ************************************************************************
	// Constructores
	// ************************************************************************

	/**
	 * Construye un nuevo país con el nombre indicado y con una lista vacía de
	 * atletas
	 * 
	 * @param nombre El nombre del país
	 */
	public Pais(String nombre)
	{
		this.nombre = nombre;
		atletas = new ArrayList<Atleta>();
	}

	// ************************************************************************
	// Métodos para consultar los atributos
	// ************************************************************************

	/**
	 * Consulta el nombre del país
	 * 
	 * @return nombre
	 */
	public String darNombre()
	{
		return nombre;
	}

	// ************************************************************************
	// Otros métodos
	// ************************************************************************

	/**
	 * Agrega un nuevo atleta al país
	 * 
	 * @param nuevoAtleta El nuevo atleta
	 */
	public void agregarAtleta(Atleta nuevoAtleta)
	{
		atletas.add(nuevoAtleta);
	}

	/**
	 * Compila la información de los atletas de un país.
	 * 
	 * @return Una lista de mapas con la información de todos los atletas del país.
	 *         Cada registro de un atleta queda en un mapa que tiene tres llaves:
	 *         "evento", que tiene asociado el nombre del evento en el que participó
	 *         el atleta; "anio", que tiene asociado el año en el que el atleta
	 *         participó en el evento; y "nombre" que tiene asociado el nombre del
	 *         atleta.
	 */
	public List<Map<String, Object>> consultarAtletas()
	{
		List<Map<String, Object>> resultado = new ArrayList<Map<String, Object>>();
		for (Atleta unAtleta : atletas)
		{
			List<Map<String, Object>> participacionesAtleta = unAtleta.consultarParticipacionesAtleta();
			resultado.addAll(participacionesAtleta);
		}
		return resultado;
	}

	/**
	 * Calcula la cantidad de medallas de cada tipo que han acumulado en un evento
	 * los atletas del país.
	 * 
	 * @param nombreEvento El nombre del evento de interés
	 * @return Un arreglo con tres enteros: la cantidad de medallas de oro, la
	 *         cantidad de medallas de plata y la cantidad de medallas de bronce
	 *         ganadas por los atletas del país.
	 */
	public int[] calcularMedallasEvento(String nombreEvento)
	{
		int[] medallasEvento = { 0, 0, 0 };

		for (Atleta atleta : atletas)
		{
			int[] medallasAtleta = atleta.darMedallasEnEvento(nombreEvento);
			medallasEvento[0] += medallasAtleta[0];
			medallasEvento[1] += medallasAtleta[1];
			medallasEvento[2] += medallasAtleta[2];
		}
		return medallasEvento;
	}

	/**
	 * Calcula la cantidad de atletas diferentes del país que han sido medallistas
	 * 
	 * @return La cantidad de medallistas
	 */
	public int contarMedallistas()
	{
		Set<Atleta> medallistas = new HashSet<>();
		for (Atleta unAtleta : atletas)
		{
			if (!medallistas.contains(unAtleta) && unAtleta.esMedallista())
				medallistas.add(unAtleta);
		}

		return medallistas.size();
	}

	/**
	 * Consulta cuáles han sido los medallistas del país de un determinado género.
	 * 
	 * @param generoAtleta El género de interés.
	 * @return Retorna un mapa donde las llaves son los nombres de los atletas del
	 *         país y del género que han sido medallistas y los valores son una
	 *         lista con información de sus medallas. La información de cada medalla
	 *         también es un mapa que tiene tres llaves: "evento", que tiene
	 *         asociado el nombre del evento; "anio", que tiene asociado el año en
	 *         el que el atleta ganó la medalla; y "medalla" que tiene asociado el
	 *         tipo de medalla.
	 */
	public Map<String, List<Map<String, Object>>> consultarMedallistasGenero(Genero generoAtleta)
	{
		Map<String, List<Map<String, Object>>> resultado = new HashMap<String, List<Map<String, Object>>>();

		for (Atleta atleta : atletas)
		{
			if (atleta.darGenero().equals(generoAtleta) && atleta.esMedallista())
			{
				List<Map<String, Object>> medallasAtleta = atleta.consultarMedallas();
				resultado.put(atleta.darNombre(), medallasAtleta);
			}
		}
		return resultado;
	}
}
