package uniandes.dpoo.taller0.modelo;

/**
 * Esta enumeración sirve para indicar los posibles tipos de medalla (oro, plata
 * o bronce), así como el resultado en que un atleta no haya ganado medalla.
 * 
 * Los valores de la enumeración son: GOLD, SILVER, BRONZE, NA
 */
public enum Medalla
{
	GOLD, SILVER, BRONZE, NA;

	/**
	 * Retorna una cadena de caracteres con el tipo de la medalla, en minúsculas.
	 * 
	 * @return Una cadena con letras minúsculas indicando el tipo de medalla.
	 */
	public String darTipoMedalla()
	{
		return toString().toLowerCase();
	}
}
