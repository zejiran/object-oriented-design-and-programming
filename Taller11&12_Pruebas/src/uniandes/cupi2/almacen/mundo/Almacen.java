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
import java.io.File;
import java.io.FileReader;

/**
 * Representa el almac�n.
 */
public class Almacen
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La categor�a ra�z del almac�n.
     */
    private Categoria categoriaRaiz;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo almac�n a partir de los datos del archivo.<br>
     * <b>post:</b> Se cargo la informaci�n del almac�n contenida en el archivo.
     * @param pArchivo Archivo con la informaci�n del almac�n. pArchivo != null.
     * @throws AlmacenException Si ocurre alg�n error al cargar la informaci�n.
     */
    public Almacen( File pArchivo ) throws AlmacenException
    {
        cargar( pArchivo );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna la categor�a ra�z del almac�n.
     * @return Categor�a ra�z del almac�n.
     */
    public Categoria darCategoriaRaiz( )
    {
        return categoriaRaiz;
    }

    /**
     * Carga el almac�n a partir de la informaci�n del archivo.<br>
     * <b>post: </b>Se cargaron los datos del archivo al �rbol del almac�n.
     * @param pArchivo Archivo que contiene los datos. pArchivo != null
     * @throws AlmacenException Si el archivo no cumple el formato especificado o si ocurre alg�n error leyendo el archivo.
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
     * Agrega un nuevo nodo a la categor�a con la ruta dada..<br>
     * <b>pre:</b> La ra�z del �rbol existe y est� inicializada. Existe una categor�a para la ruta.<br>
     * <b>post: </b> Se agreg� el nodo a la categor�a dada.
     * @param pIdPadre Identificador de la categor�a padre. pIdPadre != null && pIdPadre != "".
     * @param pTipo Tipo del nodo. pTipo != null && pTipo = {Categoria.TIPO, Marca.TIPO}
     * @param pIdentificador Identificador �nico del nodo. pIdentificador != null && pIdentificador != "".
     * @param pNombre Nombre del nodo. pNombre != null && pNombre != "".
     * @throws AlmacenException Si ya existe en el �rbol un nodo con el identificador dado.
     */
    public void agregarNodo( String pIdPadre, String pTipo, String pIdentificador, String pNombre ) throws AlmacenException
    {
        categoriaRaiz.agregarNodo( pIdPadre, pTipo, pIdentificador, pNombre );
    }

    /**
     * Elimina el nodo que est� en la ruta indicada.<br>
     * <b>pre:</b> La categor�a ra�z est� inicializada. Existe un nodo con el id dado.<br>
     * <b>post:</b>Se elimin� el nodo y todo su sub�rbol.<br>
     * @param pIdNodo Identificador �nico del nodo. pIdNodo != null && pIdNodo != "".
     * @return La categor�a padre del nodo eliminado.
     * @throws AlmacenException Si el nodo a eliminar es la ra�z.
     */
    public Categoria eliminarNodo( String pIdNodo ) throws AlmacenException
    {
        if( categoriaRaiz.identificador.equals( pIdNodo ) )
            throw new AlmacenException( "No se puede eliminar la ra�z" );
        Categoria respuesta = categoriaRaiz.buscarPadre( pIdNodo );
        categoriaRaiz.eliminarNodo( pIdNodo );
        return respuesta;
    }

    /**
     * Vende el producto con el c�digo dado en la cantidad especificada por par�metro.<br>
     * <b>pre:</b> La categor�a ra�z est� inicializada. Existe el producto con el c�digo dado.<br>
     * <b>post: </b> Se vendi� el producto especificado en la cantidad dada.
     * @param pCodigo C�digo del producto. pCodigo != null && pCodigo != "".
     * @param pCantidad Cantidad de unidades que se vender�n. pCantidad > 0.
     */
    public void venderProducto( String pCodigo, int pCantidad )
    {
        categoriaRaiz.buscarProducto( pCodigo ).vender( pCantidad );
    }

    /**
     * Retorna el nodo con el identificador dado.<br>
     * <b>pre: </b> La categor�a ra�z est� inicializada.
     * @param pIdNodo Identificador �nico del nodo. pIdNodo != null.
     * @return El nodo encontrado o null si no existe.
     */
    public NodoAlmacen buscarNodo( String pIdNodo )
    {
        return categoriaRaiz.buscarNodo( pIdNodo );
    }

    /**
     * Agrega un nuevo producto de la marca dada con la informaci�n dada por par�metro.<br>
     * <b>pre:</b> La categor�a ra�z est� inicializada. Existe la marca con el identificador dado.<br>
     * <b>post:</b> Se agreg� un nuevo producto a la marca.<br>
     * @param pIdMarca Identificador de la marca del producto. pIdMarca != null && pIdMarca != "".
     * @param pCodigo C�digo del producto. pCodigo != null && pCodigo != "".
     * @param pNombre Nombre del producto. pNombre != null && pNombre != "".
     * @param pDescripcion Descripci�n del producto. pDescripcion != null && pDescripcion != "".
     * @param pPrecio Precio del producto. pPrecio > 0.
     * @throws AlmacenException Si ya existe un producto con el c�digo dado.
     */
    public void agregarProducto( String pIdMarca, String pCodigo, String pNombre, String pDescripcion, double pPrecio ) throws AlmacenException
    {
        if(categoriaRaiz.buscarProducto( pCodigo ) != null ){
            throw new AlmacenException( "Ya existe un producto con codigo "+ pCodigo);
        }
        ((Marca)buscarNodo( pIdMarca )).agregarProducto( pCodigo, pNombre, pDescripcion, pPrecio );
    }
    
    /**
     * Elimina el producto con el c�digo dado por par�metro.<br>
     * <b>pre: </b> La categor�a ra�z est� inicializada y existe el producto con el c�digo dado.
     * <b>post: </b>Se elimin� el producto.
     * @param pCodigo C�digo del producto. pCodigo != null && pCodigo != "".
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
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * M�todo para la extensi�n2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }   

}
