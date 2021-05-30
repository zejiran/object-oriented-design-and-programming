/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad  de  los Andes   (Bogotá - Colombia)
 * Departamento de  Ingeniería  de  Sistemas    y   Computación
 * Licenciado   bajo    el  esquema Academic Free License versión 2.1
 *      
 * Proyecto Cupi2   (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_almacen
 * Autor: Equipo Cupi2 2018
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.almacen.mundo;

/**
 * Representa una excepción del almacén.
 */
@SuppressWarnings("serial")
public class AlmacenException extends Exception{

    /**
     * Construye una nueva excepción con el mensaje dado..
     * @param pMensaje Mensaje de la excepción. pMensaje != null && pMensaje != "".
     */
	public AlmacenException(String pMensaje) {
	    super(pMensaje );
	}
}
