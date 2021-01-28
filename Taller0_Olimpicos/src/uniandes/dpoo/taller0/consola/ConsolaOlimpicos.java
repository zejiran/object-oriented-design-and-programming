package uniandes.dpoo.taller0.consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import uniandes.dpoo.taller0.modelo.Atleta;
import uniandes.dpoo.taller0.modelo.Genero;
import uniandes.dpoo.taller0.procesamiento.CalculadoraEstadisticas;
import uniandes.dpoo.taller0.procesamiento.LoaderOlimpicos;

public class ConsolaOlimpicos
{
	/**
	 * Esta es la calculadora de estadísticas que se usará para hacer todas las
	 * operaciones de la aplicación. Esta calculadora también contiene toda la
	 * información sobre los atletas después de que se cargue desde un archivo.
	 */
	private CalculadoraEstadisticas calculadora;

	/**
	 * Ejecuta la aplicación: le muestra el menú al usuario y la pide que ingrese
	 * una opción, y ejecuta la opción seleccionada por el usuario. Este proceso se
	 * repite hasta que el usuario seleccione la opción de abandonar la aplicación.
	 */
	public void ejecutarAplicacion()
	{
		System.out.println("Estadísticas sobre los Juegos Olímpicos\n");

		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1)
					ejecutarCargarAtletas();
				else if (opcion_seleccionada == 2 && calculadora != null)
					ejecutarAtletasPorAnio();
				else if (opcion_seleccionada == 3 && calculadora != null)
					ejecutarMedallasEnRango();
				else if (opcion_seleccionada == 4 && calculadora != null)
					ejecutarAtletasPorPais();
				else if (opcion_seleccionada == 5 && calculadora != null)
					ejecutarPaisConMasMedallistas();
				else if (opcion_seleccionada == 6 && calculadora != null)
					ejecutarMedallistasPorEvento();
				else if (opcion_seleccionada == 7 && calculadora != null)
					ejecutarAtletasConMasMedallasQue();
				else if (opcion_seleccionada == 8 && calculadora != null)
					ejecutarAtletaEstrella();
				else if (opcion_seleccionada == 9 && calculadora != null)
					ejecutarMejorPaisEnUnEvento();
				else if (opcion_seleccionada == 10 && calculadora != null)
					ejecutarTodoterreno();
				else if (opcion_seleccionada == 11 && calculadora != null)
					ejecutarMedallistasPorNacionYGenero();
				else if (opcion_seleccionada == 12 && calculadora != null)
					ejecutarPorcentajeMedallistas();
				else if (opcion_seleccionada == 13)
				{
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
				else if (calculadora == null)
				{
					System.out.println("Para poder ejecutar esta opción primero debe cargar un archivo de atletas.");
				}
				else
				{
					System.out.println("Por favor seleccione una opción válida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
	}

	/**
	 * Muestra al usuario el menú con las opciones para que escoja la siguiente
	 * acción que quiere ejecutar.
	 */
	public void mostrarMenu()
	{
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Cargar un archivo de atletas");
		System.out.println("2. Consultar los atletas de un año dado");
		System.out.println("3. Consultar las medallas de un atleta en un periodo");
		System.out.println("4. Consultar los atletas de un país dado");
		System.out.println("5. Consultar el país con más medallistas");
		System.out.println("6. Consultar todos los medallistas de un evento dado");
		System.out.println("7. Consultar los atletas con un mínimo de medallas");
		System.out.println("8. Consultar el atleta estrella de todos los tiempos");
		System.out.println("9. Consultar mejor país en un evento");
		System.out.println("10. Consultar el atleta todoterreno");
		System.out.println("11. Consultar los medallistas por país y género");
		System.out.println("12. Consultar el porcentaje de atletas que son medallistas");
		System.out.println("13. Salir de la aplicación\n");
	}

	/**
	 * Le muestra el usuario el porcentaje de atletas que son medallistas
	 */
	private void ejecutarPorcentajeMedallistas()
	{
		System.out.println("\n" + "Porcentaje de atletas que son medallistas" + "\n");

		double porcentaje = calculadora.porcentajeMedallistas();
		double redondeado = (double) ((int) (porcentaje * 100.0)) / 100.0;
		System.out.println("El porcentaje de atletas que son medallistas es el " + redondeado + "%");
	}

	/**
	 * Le pide el usuario el nombre de un país y un género, y luego le muestra la
	 * información de los medallistas de ese género que han representado a ese país.
	 */
	private void ejecutarMedallistasPorNacionYGenero()
	{
		System.out.println("\n" + "Medallistas por país y género" + "\n");

		String pais = input("Por favor ingrese el nombre de un pais");
		String genero = input("Por favor ingrese M para consultar hombres y F para consultar mujeres").toLowerCase();

		if (!"m".equals(genero) && !"f".equals(genero))
		{
			System.out.println("Sólo puede seleccionar M o F");
		}
		else
		{
			Genero elGenero = genero.equals("m") ? Genero.MASCULINO : Genero.FEMENINO;
			Map<String, List<Map<String, Object>>> medallistas = calculadora.medallistasPorNacionGenero(pais, elGenero);

			if (medallistas != null)
			{
				for (Map.Entry<String, List<Map<String, Object>>> entry : medallistas.entrySet())
				{
					String nombre_atleta = entry.getKey();
					List<Map<String, Object>> medallas = entry.getValue();

					System.out.println("El atleta " + nombre_atleta + " ha ganado " + medallas.size() + " medallas.");
					for (Map<String, Object> medalla : medallas)
					{
						System.out.println("Evento: " + medalla.get("evento") + " (" + medalla.get("anio") + ")");
						System.out.println("    Medalla: " + medalla.get("medalla"));
					}
				}
			}
			else
			{
				System.out.println("No se encontraron medallistas del país ingresado.");
			}
		}
	}

	/**
	 * Le informa al usuario si hay un atleta todoterreno y la cantidad de deportes
	 * diferentes en los que ha participado
	 */
	private void ejecutarTodoterreno()
	{
		System.out.println("\n" + "Atleta todoterreno" + "\n");

		Atleta todoterreno = calculadora.buscarAtletaTodoterreno();
		System.out.println("El atleta todoterreno es: " + todoterreno.darNombre());
		System.out.println("Ha participado en " + todoterreno.contarDeportes() + " deportes diferentes");
	}

	/**
	 * Le pide al usuario el nombre de un evento y luego le informa cuál es el mejor
	 * país en ese evento.
	 */
	private void ejecutarMejorPaisEnUnEvento()
	{
		System.out.println("\n" + "Mejor país en un evento" + "\n");

		String evento = input("Por favor ingrese el nombre de un evento");
		Map<String, int[]> mejores = calculadora.mejorPaisEvento(evento);
		if (mejores.size() == 0)
		{
			System.out.println("No se encontró información sobre el evento: " + evento);
		}
		else if (mejores.size() == 1)
		{
			String pais = mejores.keySet().iterator().next();
			System.out.println("El mejor país en " + evento + " es " + pais + ":");
			int[] medallas = mejores.get(pais);
			System.out.println(
					"Ha gando: " + medallas[0] + " oros, " + medallas[1] + " platas, " + medallas[2] + " bronces.");
		}
		else
		{
			System.out.println("Hay un empate en " + evento + ":");
			for (Map.Entry<String, int[]> entry : mejores.entrySet())
			{
				int[] medallas = entry.getValue();
				System.out.println(entry.getKey() + "ha gando: " + medallas[0] + " oros, " + medallas[1] + " platas, "
						+ medallas[2] + " bronces.");
			}
		}
	}

	/**
	 * Muestra cuáles han sido los atletas (o el atleta) que más medallas ha ganado.
	 */
	private void ejecutarAtletaEstrella()
	{
		System.out.println("\n" + "Atleta estrella de todos los tiempos" + "\n");

		Map<String, Integer> estrellas = calculadora.atletaEstrella();
		for (Map.Entry<String, Integer> entry : estrellas.entrySet())
		{
			String nombre = entry.getKey();
			int medallas = entry.getValue();
			System.out.println(nombre + " ganó " + medallas + " medallas");
		}

	}

	/**
	 * Le pide al usuario una cantidad mínima de medallas y luego le muestra la
	 * información de los atletas que han ganado más de esa cantidad de medallas.
	 */
	private void ejecutarAtletasConMasMedallasQue()
	{
		System.out.println("\n" + "Atletas con mínimo de medallas" + "\n");

		try
		{
			int cantidadMinima = Integer.parseInt(input("Ingrese la cantidad mínima de medallas"));
			Map<String, Integer> atletas = calculadora.atletasConMasMedallas(cantidadMinima);
			System.out.println(
					"Hay " + atletas.size() + " atletas que han ganado más de " + cantidadMinima + " medallas.");
			for (Map.Entry<String, Integer> entry : atletas.entrySet())
			{
				String nombre = entry.getKey();
				int medallas = entry.getValue();
				System.out.println(nombre + " ganó " + medallas + " medallas");
			}
		}
		catch (NumberFormatException nfe)
		{
			System.out.println("El número ingresado no es válido. Por favor escriba un número entero.");
		}
	}

	/**
	 * Le pide al usuario el nombre de un evento y muestra los atletas que han sido
	 * medallistas en ese envento.
	 */
	private void ejecutarMedallistasPorEvento()
	{
		System.out.println("\n" + "Medallistas de un evento" + "\n");

		String evento = input("Por favor ingrese el nombre de un evento");
		List<Atleta> medallistas = calculadora.medallistasPorEvento(evento);
		System.out.println("Los medallistas de " + evento + "son:");
		int num = 1;
		for (Atleta atleta : medallistas)
		{
			System.out.println("" + num + ". - " + atleta.darNombre());
			num++;
		}
	}

	/**
	 * Consulta el país (o los países) con más medallistas
	 */
	private void ejecutarPaisConMasMedallistas()
	{
		System.out.println("\n" + "País con más medallistas" + "\n");

		Map<String, Integer> paises = calculadora.paisConMasMedallistas();
		if (paises.size() > 1)
		{
			System.out.println("Hay " + paises.size() + " países empatados en el primer lugar.");
		}
		for (String nombre : paises.keySet())
		{
			System.out.println(nombre + " ha tenido " + paises.get(nombre) + " medallistas");
		}
	}

	/**
	 * Le pide al usuario el nombre de un país y luego le muestra la información de
	 * todos los atletas de ese país.
	 */
	private void ejecutarAtletasPorPais()
	{
		System.out.println("\n" + "Atletas de un país" + "\n");

		String pais = input("Por favor ingrese el nombre de un pais");
		List<Map<String, Object>> atletas = calculadora.atletasPorPais(pais);
		if (atletas == null)
		{
			System.out.println("No existe un país con ese nombre");
		}
		else
		{
			for (Map<String, Object> datos : atletas)
			{
				String nombre = (String) datos.get("nombre");
				String evento = (String) datos.get("evento");
				int anio = (int) datos.get("anio");
				System.out.println(" - " + evento + " en " + anio + " --> " + nombre);
			}
		}
	}

	/**
	 * Le pide al usuario un rango de años y el nombre de un atleta. A continuación
	 * le muestra al usuario todas las medallas ganadas por el atleta en ese rango
	 * de años.
	 */
	private void ejecutarMedallasEnRango()
	{
		System.out.println("\n" + "Medallas de un atleta en un periodo" + "\n");
		try
		{
			int anio_inicial = Integer.parseInt(input("Ingrese el año inicial para el rango"));
			int anio_final = Integer.parseInt(input("Ingrese el año final para el rango"));
			String nombre_atleta = input("Ingrese el nombre del atleta que le interesa");
			List<Map<String, Object>> medallas = calculadora.medallasEnRango(anio_inicial, anio_final, nombre_atleta);
			if (medallas == null)
			{
				System.out.println("No se encontró un atleta llamado " + nombre_atleta);
			}
			else
			{
				System.out.println("El atleta " + nombre_atleta + " ha ganado " + medallas.size() + " medallas.");
				for (Map<String, Object> medalla : medallas)
				{
					System.out.println("Evento: " + medalla.get("evento") + " (" + medalla.get("anio") + ")");
					System.out.println("    Medalla: " + medalla.get("medalla"));
				}
			}
		}
		catch (NumberFormatException nfe)
		{
			System.out.println("El número ingresado no es válido. Por favor escriba un número entero.");
		}
	}

	/**
	 * Ejecuta la opción para consultar los atletas de un año.
	 */
	private void ejecutarAtletasPorAnio()
	{
		System.out.println("\n" + "Medallas de un atleta en un periodo" + "\n");

		int anio = Integer.parseInt(input("Ingrese el año de su interés"));
		Map<String, List<Atleta>> atletas = calculadora.atletasPorAnio(anio);
		System.out.println("Se encontraron " + atletas.size() + " atletas");
		for (String deporte : atletas.keySet())
		{
			System.out.println(deporte + ": " + atletas.get(deporte).size() + " atletas");
		}
	}

	/**
	 * Este método le pide al usuario el nombre de un archivo con información de los
	 * atletas, lo carga usando la clase LoaderOlimpicos y crea un objeto de tipo
	 * CalculadoraEstadisticas para que sea usado por las otras opciones de la
	 * consola.
	 */
	private void ejecutarCargarAtletas()
	{
		System.out.println("\n" + "Cargar un archivo de atletas" + "\n");

		String archivo = input("Por favor ingrese el nombre del archivo CSV con los atletas");
		try
		{
			calculadora = LoaderOlimpicos.cargarArchivo(archivo);
			System.out.println("Se cargó el archivo " + archivo + " con información de los Juegos Olímpicos.");
			Collection<String> eventos = calculadora.darNombresDeportes();
			System.out.println("Los deportes para los que se tiene información son:");
			for (String dep : eventos)
			{
				System.out.println(" - " + dep);
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: el archivo indicado no se encontró.");
		}
		catch (IOException e)
		{
			System.out.println("ERROR: hubo un problema leyendo el archivo.");
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Este método sirve para imprimir un mensaje en la consola pidiéndole
	 * información al usuario y luego leer lo que escriba el usuario.
	 * 
	 * @param mensaje El mensaje que se le mostrará al usuario
	 * @return La cadena de caracteres que el usuario escriba como respuesta.
	 */
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Este es el método principal de la aplicación, con el que inicia la ejecución
	 * de la aplicación
	 * 
	 * @param args Parámetros introducidos en la línea de comandos al invocar la
	 *             aplicación
	 */
	public static void main(String[] args)
	{
		ConsolaOlimpicos consola = new ConsolaOlimpicos();
		consola.ejecutarAplicacion();
	}

}
