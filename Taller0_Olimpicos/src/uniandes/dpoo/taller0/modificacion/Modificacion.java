package uniandes.dpoo.taller0.modificacion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import uniandes.dpoo.taller0.procesamiento.CalculadoraEstadisticas;
import uniandes.dpoo.taller0.procesamiento.LoaderOlimpicos;

public class Modificacion
{
	/**
	 * Este es el método principal de la aplicación, con el que inicia la ejecución
	 * de la aplicación
	 * 
	 * @param args Parámetros introducidos en la línea de comandos al invocar la
	 *             aplicación
	 */
	public static void main(String[] args)
	{
		System.out.println("Hola, mundo!\n");

		CalculadoraEstadisticas calc;
		try {
			calc = LoaderOlimpicos.cargarArchivo("./data/atletas.csv");
			System.out.println("\n" + "País con más medallistas" + "\n");
			System.out.println(calc.paisConMasMedallistas());

			Map<String, Integer> paises = calc.paisConMasMedallistas();
			if (paises.size() > 1)
			{
				System.out.println("Hay " + paises.size() + " países empatados en el primer lugar.");
			}
			for (String nombre: paises.keySet())
			{
				System.out.println(nombre + " ha tenido " + paises.get(nombre) + " medallistas");
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: El archivo no se ha encontrado en el directorio esperado.");
		} catch (IOException e) {
			System.out.println("ERROR: hubo un problema leyendo el archivo.");
			System.out.println(e.getMessage());
		}
	}
}
