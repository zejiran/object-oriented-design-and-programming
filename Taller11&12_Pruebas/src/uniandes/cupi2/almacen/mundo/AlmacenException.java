/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad  de  los Andes   (Bogot� - Colombia)
 * Departamento de  Ingenier�a  de  Sistemas    y   Computaci�n
 * Licenciado   bajo    el  esquema Academic Free License versi�n 2.1
 *      
 * Proyecto Cupi2   (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_almacen
 * Autor: Equipo Cupi2 2018
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.almacen.mundo;

/**
 * Representa una excepci�n del almac�n.
 */
@SuppressWarnings("serial")
public class AlmacenException extends Exception{

    /**
     * Construye una nueva excepci�n con el mensaje dado..
     * @param pMensaje Mensaje de la excepci�n. pMensaje != null && pMensaje != "".
     */
	public AlmacenException(String pMensaje) {
	    super(pMensaje );
	}
}
