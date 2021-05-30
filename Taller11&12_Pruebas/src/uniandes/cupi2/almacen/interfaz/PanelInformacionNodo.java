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
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uniandes.cupi2.almacen.mundo.NodoAlmacen;
import uniandes.cupi2.almacen.mundo.Producto;

/**
 * Panel que contiene toda la información de productos.
 */
@SuppressWarnings("serial")
public class PanelInformacionNodo extends JPanel implements ListSelectionListener, ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la acción de agregar.
     */
    private final static String AGREGAR = "Agregar";

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con la información del producto seleccionado.
     */
    private PanelProducto panelProducto;

    /**
     * Lista con los productos del nodo.
     */
    @SuppressWarnings("rawtypes")
    private JList listProductos;

    /**
     * Etiqueta para el identificador del nodo.
     */
    private JTextField txtIdentificador;
    
    /**
     * Etiqueta para el tipo del nodo.
     */
    private JTextField txtTipo;
    
    /**
     * Etiqueta para el nombre del nodo.
     */
    private JTextField txtNombre;
    
    /**
     * Etiqueta para el valor de las ventas del nodo.
     */
    private JTextField txtVentas;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Relación con la ventana principal de la aplicación.
     */
    private InterfazAlmacen principal;

    /**
     * Botón para agregar productos.
     */
    private JButton btnAgregar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo panel.
     * @param pInterfaz Ventana principal de la aplicación. pInterfaz != null.
     */
    public PanelInformacionNodo( InterfazAlmacen pInterfaz )
    {
        
        principal = pInterfaz;
        setLayout( new BorderLayout( 0, 0 ) );

        JPanel panelAux = new JPanel( new BorderLayout( 0, 15 ) );
        panelAux.setBorder( new TitledBorder( null, "Productos" ) );
        add( panelAux, BorderLayout.WEST );

        listProductos = new JList<>( );
        listProductos.addListSelectionListener( this );

        btnAgregar = new JButton( "Agregar" );
        btnAgregar.setActionCommand( AGREGAR );
        btnAgregar.addActionListener( this );

        panelAux.add( btnAgregar, BorderLayout.SOUTH );
        JScrollPane spProductos = new JScrollPane( listProductos );
        panelAux.add( spProductos );
        spProductos.setPreferredSize( new Dimension( 200, 300 ) );

        JPanel panelInfo= new JPanel( new GridLayout(4,2,0,5 ) );
        panelInfo.setBorder( new TitledBorder( "Información nodo" ) );
        panelInfo.add( new JLabel( "Tipo de nodo: " ) );
        txtTipo = new JTextField(  );
        txtTipo.setEditable( false );
        panelInfo.add( txtTipo );
        
        panelInfo.add( new JLabel( "Identificador: " ) );
        txtIdentificador = new JTextField(  );
        txtIdentificador.setEditable( false );
        panelInfo.add( txtIdentificador );
        
        panelInfo.add( new JLabel( "Nombre: " ) );
        txtNombre = new JTextField(  );
        txtNombre.setEditable( false );
        panelInfo.add( txtNombre );
        
        panelInfo.add( new JLabel( "Total de ventas: " ) );
        txtVentas = new JTextField(  );
        txtVentas.setEditable( false );
        panelInfo.add( txtVentas );
        
        
        add( panelInfo, BorderLayout.NORTH );

        panelProducto = new PanelProducto( principal );
        panelProducto.setBorder( new TitledBorder( null, "Información producto" ) );

        add( panelProducto, BorderLayout.CENTER );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Selecciona el producto con código dado.<br>
     * <b>pre: </b> La lista de productos está inicializada.<br>
     * <b>post: </b> Se seleccionó el producto con el código dado.
     * @param pCodigo Código del producto. pCodigo != null && pCodigo != "".
     */
    public void seleccionar( String pCodigo )
    {
        for( int i = 0; i < listProductos.getModel( ).getSize( ); i++ )
        {
            Producto p = ( Producto )listProductos.getModel( ).getElementAt( i );
            if( p.darCodigo( ).equals( pCodigo ) )
            {
                listProductos.setSelectedIndex( i );
                break;
            }
        }
    }

    /**
     * Actualiza el panel con la información del nodo.
     * @param pNodo Nodo con la nueva información. pNodo != null.
     */
    @SuppressWarnings("unchecked")
    public void actualizar( NodoAlmacen pNodo )
    {
        txtTipo.setText( pNodo.darTipo( ) );
        txtIdentificador.setText( pNodo.darIdentificador( ) );
        txtNombre.setText( pNodo.darNombre( ) );
        txtVentas.setText( String.format( "COP $%,.2f", pNodo.darValorVentas( ) ) );
        listProductos.setListData( pNodo.darProductos( ).toArray( ) );

        
    }

    /**
     * Manejo del evento del usuario al seleccionar un producto de la lista.
     * @param pEvento Evento generado. pEvento != null.
     */
    @Override
    public void valueChanged( ListSelectionEvent pEvento )
    {
        Producto seleccionado = ( Producto )listProductos.getSelectedValue( );
        panelProducto.actualizar( seleccionado );

    }

    /**
     * Manejo de eventos sobre botones del usuario.
     * @param pEvento Evento de usuario. pEvento != null.
     */
    @Override
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
        switch( comando )
        {
            case AGREGAR:
                principal.mostrarDialogoAgregarProducto( );
                break;
        }

    }

}
