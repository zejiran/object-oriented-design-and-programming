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

package uniandes.cupi2.almacen.interfaz;

import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import uniandes.cupi2.almacen.mundo.Almacen;
import uniandes.cupi2.almacen.mundo.AlmacenException;
import uniandes.cupi2.almacen.mundo.Categoria;
import uniandes.cupi2.almacen.mundo.Marca;
import uniandes.cupi2.almacen.mundo.NodoAlmacen;

/**
 * Interfaz principal de la aplicación.
 */
@SuppressWarnings("serial")
public class InterfazAlmacen extends JFrame
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Relación con el mundo.
     */
    private Almacen mundo;

    /**
     * Panel que muestra los nodos del árbol.
     */
    private PanelNodos panelNodos;

    /**
     * Panel que muestra la información del nodo seleccionado.
     */
    private PanelInformacionNodo panelInformacionNodo;

    /**
     * Panel de opciones generales.
     */
    private PanelOpciones panelOpciones;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva ventana de la aplicación.<br>
     * <b>post:</b> Se inicializaron y se ubicaron todos los componentes gráficos de la ventana.
     * @throws AlmacenException Si ocurren errores al cargar el archivo.
     */
    public InterfazAlmacen( ) throws AlmacenException
    {
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setTitle( "El Almacén" );
        setSize( 950, 700 );

        setLayout( new BorderLayout( ) );
        setLocationRelativeTo( null );
        mundo = new Almacen( new File( "./data/datos.txt" ) );

        panelInformacionNodo = new PanelInformacionNodo( this );
        panelNodos = new PanelNodos( this, mundo.darCategoriaRaiz( ) );
        panelOpciones = new PanelOpciones( this );

        JLabel lblBanner = new JLabel( new ImageIcon( "./data/imagenes/almacen.jpg" ));
        
        add(lblBanner,BorderLayout.NORTH);
        add( panelNodos, BorderLayout.WEST );
        add( panelInformacionNodo, BorderLayout.CENTER );
        add( panelOpciones, BorderLayout.SOUTH );

        panelNodos.seleccionarNodo( mundo.darCategoriaRaiz( ).darIdentificador( ) );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Muestra el dialogo para agregar un nuevo nodo.<br>
     * <b>post:</b>Si se acepto el dialogo, se agregó un nuevo nodo con la información ingresada. Si se generan errores se muestra un mensaje indicando la razón del error.
     */
    public void agregarNodo( )
    {
        JComboBox<String> cbTipo = new JComboBox<>( new String[]{ Categoria.TIPO, Marca.TIPO } );
        String idNodoSeleccionado = panelNodos.darIdSeleccionado( );
        JTextField txtIdPadre = new JTextField( idNodoSeleccionado );
        txtIdPadre.setEditable( false );
        JTextField txtIdentificador = new JTextField( );
        JTextField txtNombre = new JTextField( );
        Object[] message = new Object[]{ "Nodo:", txtIdPadre, "Tipo:", cbTipo, "Identificador:", txtIdentificador,"Nombre:",txtNombre };
        int opc = JOptionPane.showConfirmDialog( this, message, "Agregar nodo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE );
        if( opc == JOptionPane.OK_OPTION )
        {
            try
            {
                String tipo = ( String )cbTipo.getSelectedItem( );
                String identificador = txtIdentificador.getText( ).trim( );
                String nombre = txtNombre.getText( ).trim( );

                if( identificador == null || identificador.isEmpty( ) || nombre == null || nombre.isEmpty( ) )
                {
                    throw new Exception( "El identificador no puede ser vacio." );
                }
                if( identificador.contains( "/" ) || identificador.contains( ";;;" ) )
                {
                    throw new Exception( "Identificador inválido. El identificador no puede contener ';'" );
                }
                mundo.agregarNodo( idNodoSeleccionado, tipo, identificador, nombre );
                actualizar( );
                panelNodos.seleccionarNodo( identificador );
            }
            catch( Exception e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Agregar nodo", JOptionPane.ERROR_MESSAGE );
            }

        }
    }

    /**
     * Elimina el nodo con el id seleccionado.<br>
     * <b>pre: </b>Todos los paneles y el mundo está inicializado.<br>
     * <b>post: </b> Se eliminó el nodo y se actualizo la interfaz.
     * @param pIdNodo Identificador único del nodo. pIdNodo != null && pIdNodo != "".
     */
    public void eliminarNodo( String pIdNodo )
    {
        try
        {
            Categoria nuevaSel = mundo.eliminarNodo( pIdNodo );
            panelNodos.seleccionarNodo( nuevaSel.darIdentificador( ) );
            actualizar( );
        }
        catch( AlmacenException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Eliminar nodo", JOptionPane.ERROR_MESSAGE );
        }

    }

    /**
     * Abre el dialogo de entrada para ingresar la cantidad de unidades y vende dicha cantidad del producto con código dado.<br>
     * <b>pre: </b> Todos los paneles y el mundo están inicializados. Existe el producto con el código dado.<br>
     * <b>post: </b> Se vendió el producto y se actualizo el panel panelInformaciónNodo.
     * @param pCodigo Código del producto. pCodigo != null && pCodigo != "".
     */
    public void venderProducto( String pCodigo )
    {
        try
        {
            String input = JOptionPane.showInputDialog( this, "Ingresa la cantidad de unidades:" );
            if( input != null )
            {
                int cantidad = Integer.parseInt( input );
                if( cantidad <= 0 )
                    throw new Exception( );
                mundo.venderProducto( pCodigo, cantidad );
                actualizar( );
                panelInformacionNodo.seleccionar( pCodigo );
            }
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, "La cantidad debe ser un valor entero mayor a 0.", "Vender producto", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Crea y muestra un dialogo para agregar un producto.
     */
    public void mostrarDialogoAgregarProducto( )
    {
        NodoAlmacen seleccionado = mundo.buscarNodo( panelNodos.darIdSeleccionado( ) );
        List<Marca> marcas;
        if( seleccionado instanceof Marca )
        {
            marcas = new ArrayList<>( );
            marcas.add( ( Marca )seleccionado );
        }
        else
        {
            marcas = ( ( Categoria )seleccionado ).darMarcas( );
        }
        if( marcas.isEmpty( ) )
        {
            JOptionPane.showMessageDialog( this, "No hay marcas en este nodo para agregar productos.", "Agregar producto", JOptionPane.WARNING_MESSAGE );
        }
        else
        {
            DialogoAgregarProducto d = new DialogoAgregarProducto( this, marcas );
            d.setLocationRelativeTo( this );
            d.setVisible( true );
        }
    }

    /**
     * Agrega un producto con la información dada.<br>
     * <b>pre: </b> Los paneles y el mundo están inicializados.<br>
     * <b>post:</b> Se agregó el producto al mundo y se actualizo la interfaz.
     * @param pIdMarca Identificador de la marca del producto. pIdMarca != null && pIdMarca != "".
     * @param pCodigo Código del producto. pCodigo != null && pCodigo != "".
     * @param pNombre Nombre del producto. pNombre != null && pNombre != "".
     * @param pDescripcion Descripción del producto. pDescripcion != null && pDescripcion != "".
     * @param pPrecio Precio del producto. pPrecio > 0;
     * @throws AlmacenException Si ocurren errores al agregar el producto.
     */
    public void agregarProducto( String pIdMarca, String pCodigo, String pNombre, String pDescripcion, double pPrecio ) throws AlmacenException
    {

        mundo.agregarProducto( pIdMarca, pCodigo, pNombre, pDescripcion, pPrecio );
        actualizar( );
        panelNodos.seleccionarNodo( pIdMarca );
        panelInformacionNodo.seleccionar( pCodigo );
    }

    /**
     * Elimina el producto con el código dado.<br>
     * <b>pre: </b>Los paneles y el mundo están inicializados.<br>
     * <b>post:</b> Se eliminó el producto y se actualizó la interfaz.
     * @param pCodigo Código del producto.
     */
    public void eliminarProducto( String pCodigo )
    {
        mundo.eliminarProducto( pCodigo );
        actualizar( );
        
    }

    /**
     * Actualiza el panel con la información del nodo con identificador dado por parámetro.
     * @param pIdNodo Identificador del nodo. pIdentificador != null && pIdentificador != "".
     */
    public void actualizarInformacionNodo( String pIdNodo )
    {
        panelInformacionNodo.actualizar( mundo.buscarNodo( pIdNodo ) );
    }

    /**
     * Actualiza la interfaz con los datos del mundo.<br>
     * <b>pre: </b> Todos los paneles y el mundo están inicializados.<br>
     * <b>post: </b> Se actualizaron todos los paneles con la información del nodo seleccionado.
     */
    private void actualizar( )
    {
        panelNodos.actualizar( mundo.darCategoriaRaiz( ) );

        panelInformacionNodo.actualizar( mundo.buscarNodo( panelNodos.darIdSeleccionado( ) ) );
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = mundo.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = mundo.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args Arreglo opcional de argumentos que se recibe por línea de comandos
     */
    public static void main( String[] args )
    {
        try
        {
            // Unifica la interfaz para Mac y para Windows.
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );

            InterfazAlmacen interfaz = new InterfazAlmacen( );
            interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
            JOptionPane.showMessageDialog( null, "No ha sido posible cargar la interfaz. Revisa la consola para ver el error." );
        }
    }

}
