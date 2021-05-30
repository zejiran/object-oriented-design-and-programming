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

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase abstracta que representa un nodo del �rbol del mercado.
 */
public abstract class NodoAlmacen
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Tipo del nodo.
     */
    protected String tipo;

    /**
     * Identificador del nodo.
     */
    protected String identificador;
    
    /**
     * El nombre del nodo.
     */
    protected String nombre;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo nodo.<br>
     * <b>post: </b> Los atributos identificador y tipo se inicializaron con los valores dados por par�metro.
     * @param pTipo Tipo del nodo. pTipo != null && pTipo != "".
     * @param pIdentificador Identificador del nodo. pIdentificador != null && pIdentificador != "".
     * @param pNombre Nombre del nodo. pNombre != null && pNombre != "".
     */
    public NodoAlmacen( String pTipo, String pIdentificador, String pNombre )
    {
        tipo = pTipo;
        identificador = pIdentificador;
        nombre = pNombre;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el identificador del nodo.
     * @return Identificador del nodo.
     */
    public String darIdentificador( )
    {
        return identificador;
    }

    /**
     * Retorna el tipo del nodo.
     * @return Tipo del nodo.
     */
    public String darTipo( )
    {
        return tipo;
    }

    /**
     * Retorna el nombre del nodo.
     * @return Nombre del nodo.
     */
    public String darNombre(){
        return nombre;
    }
    
    /**
     * Busca el nodo con el identificador dado.
     * @param pIdentificador Identificador del nodo. pIdentificador != null && pIdentificador != "".
     * @return NodoAlmacen con el identificador dado o null si no se encontr� el nodo.
     */
    public abstract NodoAlmacen buscarNodo( String pIdentificador );

    /**
     * Retorna el valor total de las ventas del nodo.
     * @return Valor ventas del nodo.
     */
    public abstract double darValorVentas( );

    /**
     * Crea un nodo a partir de la informaci�n del lector.
     * @param pLector Lector que contiene la informaci�n. pLector != null.
     * @return NodoAlmacen creado.
     * @throws AlmacenException Si ocurre alg�n error al leer la informaci�n.
     */
    public NodoAlmacen crearNodo( BufferedReader pLector ) throws AlmacenException
    {
        try
        {
            String linea = pLector.readLine( );
            String datos[] = linea.split( ";;;" );
            NodoAlmacen respuesta = null;
            if( datos[ 0 ].equals( Categoria.TIPO ) )
            {
                respuesta = new Categoria( linea, pLector );
            }
            else if( datos[ 0 ].equals( Marca.TIPO ) )
            {
                respuesta = new Marca( linea, pLector );
            }
            else
            {
                throw new AlmacenException( datos[ 0 ] + " no es un tipo de nodo valido." );
            }
            return respuesta;

        }
        catch( IOException e )
        {
            throw new AlmacenException( "Error al leer el archivo.\n" + e.getMessage( ) );
        }
    }

    /**
     * Agrega a la lista acumulada todos los productos del nodo.<br>
     * <b>pre:</b> La lista de nodos est� inicializada.
     * @param pProductos Lista acumulada con los productos. pProductos != null.
     */
    public abstract void darProductos( List<Producto> pProductos );

    /**
     * Retorna una lista con todos los productos de la categor�a.<br>
     * <b>pre:</b> La lista de nodos est� inicializada.
     * @return Lista con los productos del nodo.
     */
    public List<Producto> darProductos( )
    {
        List<Producto> productos = new ArrayList<>( );
        darProductos( productos );
        return productos;
    }
    
    /**
     * Retorna la representaci�n en string de este objeto.<br>
     * @return Nombre del nodo.
     */
    @Override
    public String toString( )
    {
        return nombre;
    }

}
