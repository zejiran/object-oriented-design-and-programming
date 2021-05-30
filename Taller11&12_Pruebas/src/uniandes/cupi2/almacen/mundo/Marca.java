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
 * Clase que representa una Marca.
 */
public class Marca extends NodoAlmacen
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que representa el tipo del nodo marca.
     */
    public final static String TIPO = "Marca";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Referencia al producto raíz del árbol binario que contiene los productos de la marca.
     */
    private Producto productoRaiz;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva marca.<br>
     * <b>post: </b> Se inicializaron los atributos de la clase padre con los valores dados por parámetro y el tipo respectivo. El producto raíz se inicializó en null.
     * @param pIdentificador Identificador único de la marca. pIdentificador != null && pIdentificador != "".
     * @param pNombre Nombre de la marca. pNombre != null && pNombre != "".
     */
    public Marca( String pIdentificador, String pNombre )
    {
        super( TIPO, pIdentificador, pNombre );
        productoRaiz = null;
    }

    /**
     * Construye una nueva marca a partir de la línea con la información general y el lector para la información adicional.<br>
     * <b>post:</b> Se inicializaron los atributos de la clase padre con el nombre que viene en la línea y el tipo respectivo. Se cargaron los productos de la marca de la
     * información contenida en el lector.
     * @param pLinea Línea que contiene la información general de la marca. pLinea != null && pLinea != "" && pLinea.startsWith(TIPO).
     * @param pLector Lector para acceder a la información de los productos.
     * @throws AlmacenException Si ocurren errores al leer la información de los productos.
     */
    public Marca( String pLinea, BufferedReader pLector ) throws AlmacenException
    {
        super( TIPO, pLinea.split( ";;;" )[ 1 ], pLinea.split( ";;;" )[ 2 ] );
        try
        {
            String datos[] = pLinea.split( ";;;" );
            int numHijos = Integer.parseInt( datos[ 3 ] );
            while( numHijos-- > 0 )
            {
                Producto producto = new Producto( pLector );
                agregarProducto( producto );
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
     * Retorna la cantidad de productos pertenecientes a la marca.
     * @return Cantidad de productos de la marca.
     */
    public int darCantidadProductos( )
    {
        return productoRaiz == null ? 0 : productoRaiz.darPeso( );
    }

    /**
     * Agrega un nuevo producto con la información dada al árbol de productos de la marca.<br>
     * <b>post: </b>Se agregó el producto al árbol.
     * @param pCodigo Código del producto. pCodigo != null && pCodigo != "".
     * @param pNombre Nombre del producto. pNombre != null && pNombre != "".
     * @param pDescripcion Descripción del producto. pDescripcion != null && pDescripcion != "".
     * @param pPrecio Precio del producto. pPrecio > 0.
     * @throws AlmacenException Si ya existía un producto con el código dado.
     */
    public void agregarProducto( String pCodigo, String pNombre, String pDescripcion, double pPrecio ) throws AlmacenException
    {
        Producto nuevo = new Producto( pCodigo, pNombre, pDescripcion, pPrecio );
        agregarProducto( nuevo );

    }

    /**
     * Agrega un producto al árbol de productos de la marca.<br>
     * <b>post: </b>Se agregó el producto al árbol.
     * @param pProducto Producto nuevo. pProducto != null.
     * @throws AlmacenException Si ya existía un producto con el código dado.
     */
    private void agregarProducto( Producto pProducto ) throws AlmacenException
    {
        if( productoRaiz == null )
        {
            productoRaiz = pProducto;
        }
        else
        {
            productoRaiz.agregarProducto( pProducto );
        }
    }
    /**
     * Busca un producto por código en el árbol de productos.
     * @param pCodigo Código del producto que se esta buscando. pCodigo != null && pCodigo != "".
     * @return Producto con el código dado por parámetro o null si no existe.
     */
    public Producto buscarProducto( String pCodigo )
    {
        return productoRaiz == null ? null : productoRaiz.buscarProducto( pCodigo );
    }

    /**
     * Vende una cantidad dada del producto con el código recibido por parámetro.<br>
     * <b>pre: </b>Existe el producto con el código dado. <br>
     * <b>post: </b> Se vendieron las cantidades dadas del producto con código dado.
     * @param pCodigo Código del producto. pCodigo != null && pCodigo != "".
     * @param pCantidad Cantidad de unidades vendidas. pCantidad > 0.
     */
    public void venderProducto( String pCodigo, int pCantidad )
    {
        buscarProducto( pCodigo ).vender( pCantidad );
    }

    /**
     * Retorna la venta total de los productos de la marca.
     * @return Venta total de los productos de la marca.
     */
    public double darValorVentas( )
    {

        double valorVentas = 0;
        ArrayList<Producto> productos = new ArrayList<>( );
        if( productoRaiz != null )
        {
            productoRaiz.darInorden( productos );
        }
        for( Producto producto : productos )
        {
            valorVentas += producto.darValorVentas( );
        }
        return valorVentas;
    }

    /**
     * Agrega a la lista acumulada todos los productos del nodo.<br>
     * <b>pre:</b> La lista de nodos está inicializada.
     * @param pProductos Lista acumulada con los productos. pProductos != null.
     */
    @Override
    public void darProductos( List<Producto> pProductos )
    {
        List<Producto> inorden = new ArrayList<>( );
        if( productoRaiz != null )
        {
            productoRaiz.darInorden( inorden );
            pProductos.addAll( inorden );
        }
    }

    /**
     * Busca el nodo con el identificador dado.
     * @param pIdentificador Identificador del nodo. pIdentificador != null && pIdentificador != "".
     * @return NodoAlmacen con el identificador dado o null si no se encontró el nodo.
     */
    @Override
    public NodoAlmacen buscarNodo( String pIdentificador )
    {
        return identificador.equals( pIdentificador ) ? this : null;
    }

    /**
     * Elimina el producto con el código dado.<br>
     * <b>post:</b> Se eliminó el producto.
     * @param pCodigo Código del producto. pCodigo != null && pCodigo != "".
     * @return True si eliminó el producto, false en caso contrario.
     */
    public boolean eliminarProducto( String pCodigo )
    {
        boolean respuesta = false;
        if( productoRaiz != null )
        {
            if( productoRaiz.darCodigo( ).equals( pCodigo ) )
            {
                Producto raizAux = new Producto( "", "", "", -1 );
                raizAux.cambiarHijoIzquierda( productoRaiz );
                respuesta = productoRaiz.eliminarProducto( pCodigo, raizAux );
                productoRaiz = raizAux.darHijoIzquierda( );
            }
            else
            {
                respuesta = productoRaiz.eliminarProducto( pCodigo, null );
            }
        }
        return respuesta;
    }

}
