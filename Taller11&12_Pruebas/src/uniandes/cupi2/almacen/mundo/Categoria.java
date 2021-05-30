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

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una categoría del almacén.
 */
public class Categoria extends NodoAlmacen
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Representa el tipo del nodo categoría.
     */
    public final static String TIPO = "Categoria";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Lista con los nodos hijos de la categoría.
     */
    private List<NodoAlmacen> nodosHijos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva categoría sin nodosHijos.<br>
     * <b>post: </b> Se inicializaron los atributos de la clase padre con los valores dados por parámetro y el tipo respectivo. Se inicializó la lista de nodosHijos como una lista
     * vacía.
     * @param pIdentificador Identificador único de la marca. pIdentificador != null && pIdentificador != "".
     * @param pNombre Nombre de la categoría. pNombre != null && pNombre != "".
     */
    public Categoria( String pIdentificador, String pNombre )
    {
        super( TIPO, pIdentificador, pNombre );
        nodosHijos = new ArrayList<>( );
    }

    /**
     * Construye una nueva categoría a partir de la línea con la información general y el lector para la información adicional.<br>
     * <b>post:</b> Se inicializaron los atributos de la clase padre con el identificador que viene en la línea y el tipo respectivo. Se cargaron los nodosHijos de la categoría de
     * la información contenida en el lector.
     * @param pLinea Línea que contiene la información general de la marca. pLinea != null && pLinea != "" && pLinea.startsWith(TIPO).
     * @param pLector Lector para acceder a la información de los productos.
     * @throws AlmacenException Si ocurren errores al leer la información de los productos.
     */
    public Categoria( String pLinea, BufferedReader pLector ) throws AlmacenException
    {
        super( TIPO, pLinea.split( ";;;" )[ 1 ], pLinea.split( ";;;" )[ 2 ] );
        nodosHijos = new ArrayList<>( );
        try
        {
            String datos[] = pLinea.split( ";;;" );
            int numHijos = Integer.parseInt( datos[ 3 ] );
            while( numHijos-- > 0 )
            {
                agregarNodo( identificador, crearNodo( pLector ) );
            }
        }
        catch( Exception e )
        {
            throw new AlmacenException( "Error al leer la marca:\n" + e.getMessage( ) );
        }

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna la lista de los nodosHijos hijos.
     * @return Lista de nodosHijos.
     */
    public List<NodoAlmacen> darNodos( )
    {
        return nodosHijos;
    }

    /**
     * Indica si esta categoría tiene como hijo el nodo con el identificador dado.<br>
     * <b>pre: </b> La lista de nodosHijos está inicializada.<br>
     * @param pIdNodo Identificador del nodo. pIdNodo != null && pIdNodo != "".
     * @return True si esta categoría tiene un hijo con el identificador dado, False en caso contrario.
     */
    private boolean tieneHijo( String pIdNodo )
    {
        boolean respuesta = false;
        for( int i = 0; i < nodosHijos.size( ) && !respuesta; i++ )
        {
            NodoAlmacen nodo = nodosHijos.get( i );
            respuesta = nodo.darIdentificador( ).equals( pIdNodo );
        }
        return respuesta;
    }

    /**
     * Retorna la categoría padre del nodo con identificador dado.<br>
     * <b>pre: </b> La lista de nodosHijos está inicializada y existe un nodo con el identificador dado.<br>
     * @param pIdNodo Identificador del nodo. pIdNodo != null && pIdNodo != "".
     * @return Padre del nodo dado.
     */
    public Categoria buscarPadre( String pIdNodo )
    {
        Categoria respuesta = tieneHijo( pIdNodo ) ? this : null;
        for( int i = 0; i < nodosHijos.size( ) && respuesta == null; i++ )
        {
            NodoAlmacen actual = nodosHijos.get( i );
            if( actual.darTipo( ).equals( Categoria.TIPO ) )
            {
                respuesta = ( ( Categoria )actual ).buscarPadre( pIdNodo );
            }

        }
        return respuesta;
    }

    /**
     * Busca el nodo con el identificador dado.
     * @param pIdentificador Identificador del nodo. pIdentificador != null && pIdentificador != "".
     * @return NodoAlmacen con el identificador dado o null si no se encontró el nodo.
     */
    @Override
    public NodoAlmacen buscarNodo( String pIdentificador )
    {
        NodoAlmacen respuesta = null;
        if( pIdentificador.equals( identificador ) )
        {
            respuesta = this;
        }
        else
        {
            for( int i = 0; i < nodosHijos.size( ) && respuesta == null; i++ )
            {
                respuesta = nodosHijos.get( i ).buscarNodo( pIdentificador );
            }
        }
        return respuesta;
    }

    /**
     * Agrega un nuevo nodo del tipo dado a la lista.<br>
     * <b>pre: </b> La lista de nodosHijos está inicializada.<br>
     * <b>post: </b> Se agregó un nuevo nodo a la lista con los valores dados.
     * @param pIdPadre Identificador único del padre del nodo. pIdPadre != null && pIdPadre != "".
     * @param pTipo Tipo del nodo. pTipo != null && pTipo != "".
     * @param pIdentificador Identificador del nodo. pIdentificador != null && pIdentificador != "".
     * @param pNombre Nombre del nodo. pNombre != null && pNombre != "".
     * @throws AlmacenException Si ya existe un nodo en el subárbol con ese identificador.
     */
    public void agregarNodo( String pIdPadre, String pTipo, String pIdentificador, String pNombre ) throws AlmacenException
    {
        NodoAlmacen nuevo = pTipo.equals( Categoria.TIPO ) ? new Categoria( pIdentificador, pNombre ) : new Marca( pIdentificador, pNombre );
        agregarNodo( pIdPadre, nuevo );
    }

    /**
     * Agrega un nuevo nodo a la lista.<br>
     * <b>pre: </b> La lista de nodosHijos está inicializada.<br>
     * <b>post: </b> Se agregó el nuevo nodo a la lista.
     * @param pIdPadre Identificador único del padre del nodo. pIdPadre != null && pIdPadre != "".
     * @param pNodo NodoAlmacen que se va a agregar. pNodo != null.
     * @return True si agregó el nodo, False en caso contrario.
     * @throws AlmacenException Si ya existe un nodo en el subárbol con ese identificador.
     */
    public boolean agregarNodo( String pIdPadre, NodoAlmacen pNodo ) throws AlmacenException
    {
        if( buscarNodo( pNodo.identificador ) != null )
        {
            throw new AlmacenException( "Ya existe un nodo en el árbol con el identificador " + pNodo.identificador );
        }
        boolean respuesta = false;
        if( identificador.equals( pIdPadre ) )
        {
            respuesta = nodosHijos.add( pNodo );
        }
        else
        {
            for( int i = 0; i < nodosHijos.size( ) && !respuesta; i++ )
            {
                NodoAlmacen actual = nodosHijos.get( i );
                if( actual instanceof Categoria )
                {
                    respuesta = ( ( Categoria )actual ).agregarNodo( pIdPadre, pNodo );
                }
            }
        }
        return respuesta;
    }

    /**
     * Elimina el nodo con el identificador dado.<br>
     * <b>pre: </b>La lista de nodosHijos está inicializada. Existe un nodo con el identificador dado en el subárbol.<br>
     * <b>post: </b> Se eliminó el nodo con toda su información y su subárbol asociado.
     * @param pIdentificador Identificador del nodo. pIdentificador != null && pIdentificador != "".
     * @return NodoAlmacen eliminado.
     */
    public NodoAlmacen eliminarNodo( String pIdentificador )
    {
        NodoAlmacen respuesta = null;
        for( int i = 0; i < nodosHijos.size( ) && respuesta == null; i++ )
        {
            NodoAlmacen actual = nodosHijos.get( i );
            if( actual.identificador.equals( pIdentificador ) )
            {
                respuesta = nodosHijos.remove( i );
            }
            else if( actual instanceof Categoria )
            {
                respuesta = ( ( Categoria )actual ).eliminarNodo( pIdentificador );
            }
        }

        return respuesta;
    }

    /**
     * Busca el producto con el código dado.
     * @param pCodigo Código del producto. pCodigo != null && pCodigo != "".
     * @return El producto buscado o null si no existe.
     */
    public Producto buscarProducto( String pCodigo )
    {
        Producto respuesta = null;
        for( int i = 0; i < nodosHijos.size( ) && respuesta == null; i++ )
        {
            NodoAlmacen actual = nodosHijos.get( i );
            if( actual instanceof Marca )
            {
                respuesta = ( ( Marca )actual ).buscarProducto( pCodigo );
            }
            else
            {
                respuesta = ( ( Categoria )actual ).buscarProducto( pCodigo );
            }
        }

        return respuesta;

    }

    /**
     * Agrega a la lista acumulada todos los productos del nodo.<br>
     * <b>pre:</b> La lista de nodosHijos está inicializada.
     * @param pProductos Lista acumulada con los productos. pProductos != null.
     */
    @Override
    public void darProductos( List<Producto> pProductos )
    {
        for( NodoAlmacen nodoAlmacen : nodosHijos )
        {
            nodoAlmacen.darProductos( pProductos );
        }
    }

    /**
     * Retorna una lista con todas las marcas que tiene la categoría y su subárbol.<br>
     * <b>pre:</b> La lista de nodosHijos está inicializada.<br>
     * @return Lista con todas las marcas de la categoría y su subárbol.
     * 
     */
    public List<Marca> darMarcas( )
    {
        List<Marca> respuesta = new ArrayList<>( );
        for( NodoAlmacen nodo : nodosHijos )
        {
            if( nodo instanceof Categoria )
            {
                respuesta.addAll( ( ( Categoria )nodo ).darMarcas( ) );
            }
            else
            {
                respuesta.add( ( Marca )nodo );
            }
        }
        return respuesta;

    }

    /**
     * Retorna todos los nodosHijos del árbol que tiene como raíz este nodo. Los nodosHijos se agregan en preorden.<br>
     * <b>pre:</b> La lista de nodosHijos está inicializada.
     * @return Lista con todos los nodosHijos del árbol.
     */
    public List<NodoAlmacen> darPreorden( )
    {
        List<NodoAlmacen> respuesta = new ArrayList<>( );
        respuesta.add( this );
        for( NodoAlmacen nodo : nodosHijos )
        {
            if( nodo instanceof Categoria )
            {
                respuesta.addAll( ( ( Categoria )nodo ).darPreorden( ) );
            }
            else
            {
                respuesta.add( nodo );
            }
        }
        return respuesta;
    }

    /**
     * Retorna todos los nodosHijos del árbol que tiene como raíz este nodo. Los nodosHijos se agregan en posorden.<br>
     * <b>pre:</b> La lista de nodosHijos está inicializada.
     * @return Lista con todos los nodosHijos del árbol.
     */
    public List<NodoAlmacen> darPosorden( )
    {
        List<NodoAlmacen> respuesta = new ArrayList<>( );
        for( NodoAlmacen nodo : nodosHijos )
        {
            if( nodo instanceof Categoria )
            {
                respuesta.addAll( ( ( Categoria )nodo ).darPosorden( ) );
            }
            else
            {
                respuesta.add( nodo );
            }
        }
        respuesta.add( this );
        return respuesta;
    }

    /**
     * Retorna el valor total de las ventas de la categoría.
     * @return Valor de las ventas de la categoría.
     */
    public double darValorVentas( )
    {
        double valorVentas = 0;
        for( NodoAlmacen nodoAlmacen : nodosHijos )
        {
            valorVentas += nodoAlmacen.darValorVentas( );
        }
        return valorVentas;
    }

}
