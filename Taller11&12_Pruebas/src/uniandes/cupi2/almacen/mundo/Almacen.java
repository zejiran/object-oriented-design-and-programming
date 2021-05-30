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
import java.io.File;
import java.io.FileReader;

/**
 * Representa el almacén.
 */
public class Almacen
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La categoría raíz del almacén.
     */
    private Categoria categoriaRaiz;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo almacén a partir de los datos del archivo.<br>
     * <b>post:</b> Se cargo la información del almacén contenida en el archivo.
     * @param pArchivo Archivo con la información del almacén. pArchivo != null.
     * @throws AlmacenException Si ocurre algún error al cargar la información.
     */
    public Almacen( File pArchivo ) throws AlmacenException
    {
        cargar( pArchivo );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna la categoría raíz del almacén.
     * @return Categoría raíz del almacén.
     */
    public Categoria darCategoriaRaiz( )
    {
        return categoriaRaiz;
    }

    /**
     * Carga el almacén a partir de la información del archivo.<br>
     * <b>post: </b>Se cargaron los datos del archivo al árbol del almacén.
     * @param pArchivo Archivo que contiene los datos. pArchivo != null
     * @throws AlmacenException Si el archivo no cumple el formato especificado o si ocurre algún error leyendo el archivo.
     */
    private void cargar( File pArchivo ) throws AlmacenException
    {
        try
        {
            BufferedReader in = new BufferedReader( new FileReader( pArchivo ) );
            categoriaRaiz = new Categoria( in.readLine( ), in );
        }
        catch( Exception e )
        {
            e.printStackTrace(  );
            throw new AlmacenException( "Error al leer el archivo.\n" + e.getMessage( ) );

        }
    }

    /**
     * Agrega un nuevo nodo a la categoría con la ruta dada..<br>
     * <b>pre:</b> La raíz del árbol existe y está inicializada. Existe una categoría para la ruta.<br>
     * <b>post: </b> Se agregó el nodo a la categoría dada.
     * @param pIdPadre Identificador de la categoría padre. pIdPadre != null && pIdPadre != "".
     * @param pTipo Tipo del nodo. pTipo != null && pTipo = {Categoria.TIPO, Marca.TIPO}
     * @param pIdentificador Identificador único del nodo. pIdentificador != null && pIdentificador != "".
     * @param pNombre Nombre del nodo. pNombre != null && pNombre != "".
     * @throws AlmacenException Si ya existe en el árbol un nodo con el identificador dado.
     */
    public void agregarNodo( String pIdPadre, String pTipo, String pIdentificador, String pNombre ) throws AlmacenException
    {
        categoriaRaiz.agregarNodo( pIdPadre, pTipo, pIdentificador, pNombre );
    }

    /**
     * Elimina el nodo que está en la ruta indicada.<br>
     * <b>pre:</b> La categoría raíz está inicializada. Existe un nodo con el id dado.<br>
     * <b>post:</b>Se eliminó el nodo y todo su subárbol.<br>
     * @param pIdNodo Identificador único del nodo. pIdNodo != null && pIdNodo != "".
     * @return La categoría padre del nodo eliminado.
     * @throws AlmacenException Si el nodo a eliminar es la raíz.
     */
    public Categoria eliminarNodo( String pIdNodo ) throws AlmacenException
    {
        if( categoriaRaiz.identificador.equals( pIdNodo ) )
            throw new AlmacenException( "No se puede eliminar la raíz" );
        Categoria respuesta = categoriaRaiz.buscarPadre( pIdNodo );
        categoriaRaiz.eliminarNodo( pIdNodo );
        return respuesta;
    }

    /**
     * Vende el producto con el código dado en la cantidad especificada por parámetro.<br>
     * <b>pre:</b> La categoría raíz está inicializada. Existe el producto con el código dado.<br>
     * <b>post: </b> Se vendió el producto especificado en la cantidad dada.
     * @param pCodigo Código del producto. pCodigo != null && pCodigo != "".
     * @param pCantidad Cantidad de unidades que se venderán. pCantidad > 0.
     */
    public void venderProducto( String pCodigo, int pCantidad )
    {
        categoriaRaiz.buscarProducto( pCodigo ).vender( pCantidad );
    }

    /**
     * Retorna el nodo con el identificador dado.<br>
     * <b>pre: </b> La categoría raíz está inicializada.
     * @param pIdNodo Identificador único del nodo. pIdNodo != null.
     * @return El nodo encontrado o null si no existe.
     */
    public NodoAlmacen buscarNodo( String pIdNodo )
    {
        return categoriaRaiz.buscarNodo( pIdNodo );
    }

    /**
     * Agrega un nuevo producto de la marca dada con la información dada por parámetro.<br>
     * <b>pre:</b> La categoría raíz está inicializada. Existe la marca con el identificador dado.<br>
     * <b>post:</b> Se agregó un nuevo producto a la marca.<br>
     * @param pIdMarca Identificador de la marca del producto. pIdMarca != null && pIdMarca != "".
     * @param pCodigo Código del producto. pCodigo != null && pCodigo != "".
     * @param pNombre Nombre del producto. pNombre != null && pNombre != "".
     * @param pDescripcion Descripción del producto. pDescripcion != null && pDescripcion != "".
     * @param pPrecio Precio del producto. pPrecio > 0.
     * @throws AlmacenException Si ya existe un producto con el código dado.
     */
    public void agregarProducto( String pIdMarca, String pCodigo, String pNombre, String pDescripcion, double pPrecio ) throws AlmacenException
    {
        if(categoriaRaiz.buscarProducto( pCodigo ) != null ){
            throw new AlmacenException( "Ya existe un producto con codigo "+ pCodigo);
        }
        ((Marca)buscarNodo( pIdMarca )).agregarProducto( pCodigo, pNombre, pDescripcion, pPrecio );
    }
    
    /**
     * Elimina el producto con el código dado por parámetro.<br>
     * <b>pre: </b> La categoría raíz está inicializada y existe el producto con el código dado.
     * <b>post: </b>Se eliminó el producto.
     * @param pCodigo Código del producto. pCodigo != null && pCodigo != "".
     */
    public void eliminarProducto(String pCodigo){
        for(Marca marca: categoriaRaiz.darMarcas( ) ){
            if(marca.buscarProducto( pCodigo )!=null){
                marca.eliminarProducto( pCodigo );
                break;
            }
        }
    }
    
    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Método para la extensión2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }   

}
