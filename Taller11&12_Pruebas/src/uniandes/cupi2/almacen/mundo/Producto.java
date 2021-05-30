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
import java.util.List;

/**
 * Clase que representa un producto del almacén.
 */
public class Producto
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Código único del producto.
     */
    private String codigo;

    /**
     * Nombre del producto.
     */
    private String nombre;

    /**
     * Descripción del producto.
     */
    private String descripcion;

    /**
     * Precio del producto.
     */
    private double precio;

    /**
     * Cantidad de unidades que se han vendido del producto.
     */
    private int cantidadUnidadesVendidas;

    /**
     * Referencia al producto hijo izquierda.
     */
    private Producto hijoIzquierda;

    /**
     * Referencia al producto hijo derecha.
     */
    private Producto hijoDerecha;

    // -------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------

    /**
     * Construye un nuevo producto.<br>
     * <b>post:</b> Los atributos código, nombre, descripción y precio se inicializaron con los valores dados por parámetro. El hijo izquierda y el hijo derecha se
     * inicializaron en null. La cantidad de unidades vendidas se inicializaron en 0.
     * @param pCodigo Código del producto. pCodigo != null && pCodigo != "".
     * @param pNombre Nombre del producto. pNombre != null && pNombre != "".
     * @param pDescripcion Descripción del producto. pDescripcion != null && pDescripcion != "".
     * @param pPrecio Precio del producto. pPrecio > 0.
     */
    public Producto( String pCodigo, String pNombre, String pDescripcion, double pPrecio )
    {
        codigo = pCodigo;
        nombre = pNombre;
        descripcion = pDescripcion;
        precio = pPrecio;
        cantidadUnidadesVendidas = 0;
    }

    /**
     * Construye un nuevo producto a partir de la información del lector.<br>
     * <b>post:</b> Los atributos código, nombre, descripción, precio y unidades vendidas se inicializaron con la información del archivo. El hijo izquierda y el hijo derecha
     * se inicializaron en null.
     * @param pLector Lector del archivo. pLector != null.
     * @throws Exception Si ocurren errores al leer los datos.
     */
    public Producto( BufferedReader pLector ) throws Exception
    {
        String datos[] = pLector.readLine( ).split( ";;;" );
        codigo = datos[ 0 ];
        nombre = datos[ 1 ];
        descripcion = datos[ 2 ];
        precio = Double.parseDouble( datos[ 3 ] );
        cantidadUnidadesVendidas = Integer.parseInt( datos[ 4 ] );
    }

    // -------------------------------------------------------------
    // Métodos
    // -------------------------------------------------------------

    /**
     * Retorna el código del producto.
     * @return Código del producto.
     */
    public String darCodigo( )
    {
        return codigo;
    }

    /**
     * Retorna el nombre del producto.
     * @return Nombre del producto.
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna la descripción del producto.
     * @return Descripción del producto.
     */
    public String darDescripcion( )
    {
        return descripcion;
    }

    /**
     * Retorna el precio del producto.
     * @return Precio del producto.
     */
    public double darPrecio( )
    {
        return precio;
    }

    /**
     * Retorna la cantidad de unidades vendidas del producto.
     * @return Cantidad de unidades vendidas.
     */
    public int darCantidadUnidadesVendidas( )
    {
        return cantidadUnidadesVendidas;
    }

    /**
     * Retorna el hijo izquierda.
     * @return hijo izquierda.
     */
    public Producto darHijoIzquierda( )
    {
        return hijoIzquierda;
    }

    /**
     * Retorna el hijo derecha.
     * @return hijo derecha.
     */
    public Producto darHijoDerecha( )
    {
        return hijoDerecha;
    }

    /**
     * Cambia el hijo izquierda por el valor dado por parámetro.<br>
     * <b>post:</b> Se asignó el valor del parámetro a el hijo izquierda.
     * @param pProducto Producto que será asignado.
     */
    public void cambiarHijoIzquierda( Producto pProducto )
    {
        hijoIzquierda = pProducto;
    }

    /**
     * Cambia el hijo derecha por el valor dado por parámetro.<br>
     * <b>post:</b> Se asignó el valor del parámetro a el hijo derecha.
     * @param pProducto Producto que será asignado.
     */
    public void cambiarHijoDerecha( Producto pProducto )
    {
        hijoDerecha = pProducto;
    }

    /**
     * Vende una cantidad de unidades dadas del producto.<br>
     * <b>post: </b>La cantidad de unidades vendidas aumentó en la cantidad dada por parámetro.
     * @param pCantidad Cantidad de unidades que se vendieron. pCantidad >=0.
     */
    public void vender( int pCantidad )
    {
        cantidadUnidadesVendidas += pCantidad;
    }

    /**
     * Retorna el valor de las ventas totales del producto.
     * @return Valor de las ventas del producto.
     */
    public double darValorVentas( )
    {
        return precio * cantidadUnidadesVendidas;
    }

    /**
     * Compara el código del producto con el valor dado por parámetro.
     * @param pCodigo Código con el cual se realizará la comparación. pCodigo != null && pCodigo != "".
     * @return Retorna 0 si los 2 códigos son iguales, 1 si el código del producto es mayor y -1 si es menor.
     */
    public int comparar( String pCodigo )
    {
        int comp = codigo.compareTo( pCodigo );
        int respuesta = 0;
        if( comp > 0 )
        {
            respuesta = 1;
        }
        if( comp < 0 )
        {
            respuesta = -1;
        }
        return respuesta;
    }

    /**
     * Retorna el peso del árbol que tiene como raíz este producto.
     * @return Peso del árbol generado a partir de este producto.
     */
    public int darPeso( )
    {
        int total = 1;
        if( hijoIzquierda != null )
        {
            total += hijoIzquierda.darPeso( );
        }
        if( hijoDerecha != null )
        {
            total += hijoDerecha.darPeso( );
        }
        return total;
    }

    /**
     * Agrega un producto al subárbol que contiene como raíz este producto.<br>
     * <b>post:</b> Si no existía un producto con el mismo código, se agregó el producto de tal manera que para cada nodo de este árbol el hijo izquierda tiene un código menor
     * y el hijo derecha tiene un código mayor.
     * @param pProducto Producto que se va a agregar. pProducto != null.
     * @throws AlmacenException Si ya existía en el subárbol un producto con el código dado.
     */
    public void agregarProducto( Producto pProducto ) throws AlmacenException
    {

        int comp = comparar( pProducto.darCodigo( ) );
        boolean agrego = false;
        if( comp > 0 )
        {
            if( hijoIzquierda == null )
            {
                hijoIzquierda = pProducto;
                agrego = true;
            }
            else
            {
                hijoIzquierda.agregarProducto( pProducto );
                agrego = true;
            }
        }
        else if( comp < 0 )
        {
            if( hijoDerecha == null )
            {
                hijoDerecha = pProducto;
                agrego = true;
            }
            else
            {
                hijoDerecha.agregarProducto( pProducto );
                agrego = true;
            }
        }
        
        if( !agrego )
        {
            throw new AlmacenException( "Ya existe un producto con el código dado." );
        }
    }

    /**
     * Busca un producto por código en el subárbol generado a partir de este producto.
     * @param pCodigo Código del producto que se esta buscando. pCodigo != null && pCodigo != "".
     * @return Producto con el código dado por parámetro o null si no existe en el subárbol un producto con ese código.
     */
    public Producto buscarProducto( String pCodigo )
    {
        int comp = comparar( pCodigo );
        Producto respuesta = this;

        if( comp > 0 )
        {
            respuesta = hijoIzquierda == null ? null : hijoIzquierda.buscarProducto( pCodigo );
        }
        else if( comp < 0 )
        {
            respuesta = hijoDerecha == null ? null : hijoDerecha.buscarProducto( pCodigo );
        }

        return respuesta;
    }

    /**
     * Retorna el producto con el código menor.
     * @return Producto con el código menor.
     */
    private Producto darProductoMenorCodigo( )
    {
        Producto respuesta = this;
        if( hijoIzquierda != null )
        {
            respuesta = hijoIzquierda.darProductoMenorCodigo( );
        }
        return respuesta;
    }

    /**
     * Elimina el producto con código dado del subárbol cuya raíz es el nodo actual.
     * @param pCodigo Código del producto. pCodigo != null && pCodigo != "".
     * @param pPadre Padre del producto actual.
     * @return True si eliminó el producto, false en caso contrario.
     */
    public boolean eliminarProducto( String pCodigo, Producto pPadre )
    {
        int comp = comparar( pCodigo );
        boolean respuesta = false;
        if( comp > 0 )
        {
            if( hijoIzquierda != null )
            {
                respuesta = hijoIzquierda.eliminarProducto( pCodigo, this );
            }
        }
        else if( comp < 0 )
        {
            if( hijoDerecha != null )
            {
                respuesta = hijoDerecha.eliminarProducto( pCodigo, this );
            }
        }
        else
        {
            if( hijoIzquierda != null && hijoDerecha != null )
            {
                Producto menor = hijoDerecha.darProductoMenorCodigo( );
                codigo = menor.codigo;
                nombre = menor.nombre;
                descripcion = menor.descripcion;
                precio = menor.precio;
                cantidadUnidadesVendidas = menor.cantidadUnidadesVendidas;

                hijoDerecha.eliminarProducto( codigo, this );
            }
            else if( pPadre.hijoIzquierda != null && pPadre.hijoIzquierda.codigo == codigo )
            {
                pPadre.hijoIzquierda = ( hijoIzquierda != null ) ? hijoIzquierda : hijoDerecha;
            }
            else if( pPadre.hijoDerecha != null && pPadre.hijoDerecha.codigo == codigo )
            {
                pPadre.hijoDerecha = ( hijoIzquierda != null ) ? hijoIzquierda : hijoDerecha;
            }
            respuesta = true;
        }

        return respuesta;
    }

    /**
     * Agrega a la lista acumulada este producto y los productos de su subárbol.
     * @param pAcumulado Lista acumulada de todos los productos.
     */
    public void darInorden( List<Producto> pAcumulado )
    {
        if( hijoIzquierda != null )
        {
            hijoIzquierda.darInorden( pAcumulado );
        }
        pAcumulado.add( this );

        if( hijoDerecha != null )
        {
            hijoDerecha.darInorden( pAcumulado );
        }
    }

    /**
     * Retorna el código y el nombre del producto separados por guión.
     */
    @Override
    public String toString( )
    {
        return codigo + " - " + nombre;
    }
}
