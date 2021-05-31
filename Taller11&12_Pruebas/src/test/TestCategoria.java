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

package test;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una categor�a del almac�n.
 */
public class TestCategoria extends TestNodoAlmacen
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Representa el tipo del nodo categor�a.
     */
    public final static String TIPO = "Categoria";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Lista con los nodos hijos de la categor�a.
     */
    private List<TestNodoAlmacen> nodosHijos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva categor�a sin nodosHijos.<br>
     * <b>post: </b> Se inicializaron los atributos de la clase padre con los valores dados por par�metro y el tipo respectivo. Se inicializ� la lista de nodosHijos como una lista
     * vac�a.
     * @param pIdentificador Identificador �nico de la marca. pIdentificador != null && pIdentificador != "".
     * @param pNombre Nombre de la categor�a. pNombre != null && pNombre != "".
     */
    public TestCategoria( String pIdentificador, String pNombre )
    {
        super( TIPO, pIdentificador, pNombre );
        nodosHijos = new ArrayList<>( );
    }

    /**
     * Construye una nueva categor�a a partir de la l�nea con la informaci�n general y el lector para la informaci�n adicional.<br>
     * <b>post:</b> Se inicializaron los atributos de la clase padre con el identificador que viene en la l�nea y el tipo respectivo. Se cargaron los nodosHijos de la categor�a de
     * la informaci�n contenida en el lector.
     * @param pLinea L�nea que contiene la informaci�n general de la marca. pLinea != null && pLinea != "" && pLinea.startsWith(TIPO).
     * @param pLector Lector para acceder a la informaci�n de los productos.
     * @throws TestAlmacenException Si ocurren errores al leer la informaci�n de los productos.
     */
    public TestCategoria( String pLinea, BufferedReader pLector ) throws TestAlmacenException
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
            throw new TestAlmacenException( "Error al leer la marca:\n" + e.getMessage( ) );
        }

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna la lista de los nodosHijos hijos.
     * @return Lista de nodosHijos.
     */
    public List<TestNodoAlmacen> darNodos( )
    {
        return nodosHijos;
    }

    /**
     * Indica si esta categor�a tiene como hijo el nodo con el identificador dado.<br>
     * <b>pre: </b> La lista de nodosHijos est� inicializada.<br>
     * @param pIdNodo Identificador del nodo. pIdNodo != null && pIdNodo != "".
     * @return True si esta categor�a tiene un hijo con el identificador dado, False en caso contrario.
     */
    private boolean tieneHijo( String pIdNodo )
    {
        boolean respuesta = false;
        for( int i = 0; i < nodosHijos.size( ) && !respuesta; i++ )
        {
            TestNodoAlmacen nodo = nodosHijos.get( i );
            respuesta = nodo.darIdentificador( ).equals( pIdNodo );
        }
        return respuesta;
    }

    /**
     * Retorna la categor�a padre del nodo con identificador dado.<br>
     * <b>pre: </b> La lista de nodosHijos est� inicializada y existe un nodo con el identificador dado.<br>
     * @param pIdNodo Identificador del nodo. pIdNodo != null && pIdNodo != "".
     * @return Padre del nodo dado.
     */
    public TestCategoria buscarPadre( String pIdNodo )
    {
        TestCategoria respuesta = tieneHijo( pIdNodo ) ? this : null;
        for( int i = 0; i < nodosHijos.size( ) && respuesta == null; i++ )
        {
            TestNodoAlmacen actual = nodosHijos.get( i );
            if( actual.darTipo( ).equals( TestCategoria.TIPO ) )
            {
                respuesta = ( ( TestCategoria )actual ).buscarPadre( pIdNodo );
            }

        }
        return respuesta;
    }

    /**
     * Busca el nodo con el identificador dado.
     * @param pIdentificador Identificador del nodo. pIdentificador != null && pIdentificador != "".
     * @return NodoAlmacen con el identificador dado o null si no se encontr� el nodo.
     */
    @Override
    public TestNodoAlmacen buscarNodo( String pIdentificador )
    {
        TestNodoAlmacen respuesta = null;
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
     * <b>pre: </b> La lista de nodosHijos est� inicializada.<br>
     * <b>post: </b> Se agreg� un nuevo nodo a la lista con los valores dados.
     * @param pIdPadre Identificador �nico del padre del nodo. pIdPadre != null && pIdPadre != "".
     * @param pTipo Tipo del nodo. pTipo != null && pTipo != "".
     * @param pIdentificador Identificador del nodo. pIdentificador != null && pIdentificador != "".
     * @param pNombre Nombre del nodo. pNombre != null && pNombre != "".
     * @throws TestAlmacenException Si ya existe un nodo en el sub�rbol con ese identificador.
     */
    public void agregarNodo( String pIdPadre, String pTipo, String pIdentificador, String pNombre ) throws TestAlmacenException
    {
        TestNodoAlmacen nuevo = pTipo.equals( TestCategoria.TIPO ) ? new TestCategoria( pIdentificador, pNombre ) : new TestMarca( pIdentificador, pNombre );
        agregarNodo( pIdPadre, nuevo );
    }

    /**
     * Agrega un nuevo nodo a la lista.<br>
     * <b>pre: </b> La lista de nodosHijos est� inicializada.<br>
     * <b>post: </b> Se agreg� el nuevo nodo a la lista.
     * @param pIdPadre Identificador �nico del padre del nodo. pIdPadre != null && pIdPadre != "".
     * @param pNodo NodoAlmacen que se va a agregar. pNodo != null.
     * @return True si agreg� el nodo, False en caso contrario.
     * @throws TestAlmacenException Si ya existe un nodo en el sub�rbol con ese identificador.
     */
    public boolean agregarNodo( String pIdPadre, TestNodoAlmacen pNodo ) throws TestAlmacenException
    {
        if( buscarNodo( pNodo.identificador ) != null )
        {
            throw new TestAlmacenException( "Ya existe un nodo en el �rbol con el identificador " + pNodo.identificador );
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
                TestNodoAlmacen actual = nodosHijos.get( i );
                if( actual instanceof TestCategoria )
                {
                    respuesta = ( ( TestCategoria )actual ).agregarNodo( pIdPadre, pNodo );
                }
            }
        }
        return respuesta;
    }

    /**
     * Elimina el nodo con el identificador dado.<br>
     * <b>pre: </b>La lista de nodosHijos est� inicializada. Existe un nodo con el identificador dado en el sub�rbol.<br>
     * <b>post: </b> Se elimin� el nodo con toda su informaci�n y su sub�rbol asociado.
     * @param pIdentificador Identificador del nodo. pIdentificador != null && pIdentificador != "".
     * @return NodoAlmacen eliminado.
     */
    public TestNodoAlmacen eliminarNodo( String pIdentificador )
    {
        TestNodoAlmacen respuesta = null;
        for( int i = 0; i < nodosHijos.size( ) && respuesta == null; i++ )
        {
            TestNodoAlmacen actual = nodosHijos.get( i );
            if( actual.identificador.equals( pIdentificador ) )
            {
                respuesta = nodosHijos.remove( i );
            }
            else if( actual instanceof TestCategoria )
            {
                respuesta = ( ( TestCategoria )actual ).eliminarNodo( pIdentificador );
            }
        }

        return respuesta;
    }

    /**
     * Busca el producto con el c�digo dado.
     * @param pCodigo C�digo del producto. pCodigo != null && pCodigo != "".
     * @return El producto buscado o null si no existe.
     */
    public TestProducto buscarProducto( String pCodigo )
    {
        TestProducto respuesta = null;
        for( int i = 0; i < nodosHijos.size( ) && respuesta == null; i++ )
        {
            TestNodoAlmacen actual = nodosHijos.get( i );
            if( actual instanceof TestMarca )
            {
                respuesta = ( ( TestMarca )actual ).buscarProducto( pCodigo );
            }
            else
            {
                respuesta = ( ( TestCategoria )actual ).buscarProducto( pCodigo );
            }
        }

        return respuesta;

    }

    /**
     * Agrega a la lista acumulada todos los productos del nodo.<br>
     * <b>pre:</b> La lista de nodosHijos est� inicializada.
     * @param pProductos Lista acumulada con los productos. pProductos != null.
     */
    @Override
    public void darProductos( List<TestProducto> pProductos )
    {
        for( TestNodoAlmacen nodoAlmacen : nodosHijos )
        {
            nodoAlmacen.darProductos( pProductos );
        }
    }

    /**
     * Retorna una lista con todas las marcas que tiene la categor�a y su sub�rbol.<br>
     * <b>pre:</b> La lista de nodosHijos est� inicializada.<br>
     * @return Lista con todas las marcas de la categor�a y su sub�rbol.
     * 
     */
    public List<TestMarca> darMarcas( )
    {
        List<TestMarca> respuesta = new ArrayList<>( );
        for( TestNodoAlmacen nodo : nodosHijos )
        {
            if( nodo instanceof TestCategoria )
            {
                respuesta.addAll( ( ( TestCategoria )nodo ).darMarcas( ) );
            }
            else
            {
                respuesta.add( ( TestMarca )nodo );
            }
        }
        return respuesta;

    }

    /**
     * Retorna todos los nodosHijos del �rbol que tiene como ra�z este nodo. Los nodosHijos se agregan en preorden.<br>
     * <b>pre:</b> La lista de nodosHijos est� inicializada.
     * @return Lista con todos los nodosHijos del �rbol.
     */
    public List<TestNodoAlmacen> darPreorden( )
    {
        List<TestNodoAlmacen> respuesta = new ArrayList<>( );
        respuesta.add( this );
        for( TestNodoAlmacen nodo : nodosHijos )
        {
            if( nodo instanceof TestCategoria )
            {
                respuesta.addAll( ( ( TestCategoria )nodo ).darPreorden( ) );
            }
            else
            {
                respuesta.add( nodo );
            }
        }
        return respuesta;
    }

    /**
     * Retorna todos los nodosHijos del �rbol que tiene como ra�z este nodo. Los nodosHijos se agregan en posorden.<br>
     * <b>pre:</b> La lista de nodosHijos est� inicializada.
     * @return Lista con todos los nodosHijos del �rbol.
     */
    public List<TestNodoAlmacen> darPosorden( )
    {
        List<TestNodoAlmacen> respuesta = new ArrayList<>( );
        for( TestNodoAlmacen nodo : nodosHijos )
        {
            if( nodo instanceof TestCategoria )
            {
                respuesta.addAll( ( ( TestCategoria )nodo ).darPosorden( ) );
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
     * Retorna el valor total de las ventas de la categor�a.
     * @return Valor de las ventas de la categor�a.
     */
    public double darValorVentas( )
    {
        double valorVentas = 0;
        for( TestNodoAlmacen nodoAlmacen : nodosHijos )
        {
            valorVentas += nodoAlmacen.darValorVentas( );
        }
        return valorVentas;
    }

}
