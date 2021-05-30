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

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import uniandes.cupi2.almacen.mundo.Producto;

/**
 * Panel que contiene la información de un producto.
 */
@SuppressWarnings("serial")
public class PanelProducto extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la acción vender.
     */
    private final static String VENDER = "Vender";

    /**
     * Constante para la acción eliminar.
     */
    private final static String ELIMINAR = "Eliminar";

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Campo de texto para el precio del producto.
     */
    private JTextField txtPrecio;

    /**
     * Campo de texto para las unidades vendidas del producto.
     */
    private JTextField txtUnidadesVendidas;

    /**
     * Campo de texto para el valor de las ventas del producto.
     */
    private JTextField txtValorVentas;

    /**
     * Etiqueta para el nombre.
     */
    private JLabel lblNombre;

    /**
     * Etiqueta para la imagen del producto.
     */
    private JLabel lblImagen;

    /**
     * Campo de texto para la descripción.
     */
    private JTextPane txtDescripcion;

    /**
     * Botón para vender el producto.
     */
    private JButton btnVender;

    /**
     * Botón para eliminar el producto.
     */
    private JButton btnEliminar;

    /**
     * Campo de texto para el código.
     */
    private JTextField txtCodigo;
    
    /**
     * Scroll Pane para la descripción.
     */
    private JScrollPane spDescripcion;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Relación con la ventana principal de la aplicación.
     */
    private InterfazAlmacen principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo panel producto.
     * @param pInterfaz Ventana principal de la aplicación. pInterfaz != null.
     */
    public PanelProducto( InterfazAlmacen pInterfaz )
    {
        principal = pInterfaz;
        setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
        setLayout( new BorderLayout( 0, 0 ) );

        lblNombre = new JLabel( "" );
        lblNombre.setHorizontalAlignment( SwingConstants.CENTER );
        lblNombre.setFont( new Font( "Tahoma", Font.BOLD, 20 ) );
        add( lblNombre, BorderLayout.NORTH );

        lblImagen = new JLabel( );
        add( lblImagen, BorderLayout.WEST );

        // Panel auxiliar
        JPanel panelAux = new JPanel( );
        panelAux.setBorder( new EmptyBorder( 10, 5, 10, 0 ) );
        add( panelAux, BorderLayout.CENTER );
        GridBagLayout gbl_panelAux = new GridBagLayout( );
        gbl_panelAux.columnWeights = new double[]{ 0, 1, 1 };
        gbl_panelAux.rowWeights = new double[]{ 0, 0, 1, 0, 0, 0, 0, 0, 0 };
        panelAux.setLayout( gbl_panelAux );

        // Etiqueta codigo
        JLabel lblCodigo = new JLabel( "Codigo:" );
        lblCodigo.setHorizontalAlignment( SwingConstants.RIGHT );
        GridBagConstraints gbc_lblCodigo = new GridBagConstraints( );
        gbc_lblCodigo.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblCodigo.insets = new Insets( 0, 0, 5, 5 );
        gbc_lblCodigo.gridx = 0;
        gbc_lblCodigo.gridy = 0;
        panelAux.add( lblCodigo, gbc_lblCodigo );

        // Campo de texto para el codigo
        txtCodigo = new JTextField( );
        txtCodigo.setEditable( false );
        GridBagConstraints gbc_txtCodigo = new GridBagConstraints( );
        gbc_txtCodigo.gridwidth = 2;
        gbc_txtCodigo.insets = new Insets( 0, 0, 5, 5 );
        gbc_txtCodigo.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtCodigo.gridx = 1;
        gbc_txtCodigo.gridy = 0;
        panelAux.add( txtCodigo, gbc_txtCodigo );

        // Etiqueta descripción
        JLabel lblDescripcion = new JLabel( "Descripción:" );
        lblDescripcion.setVerticalAlignment( SwingConstants.TOP );
        lblDescripcion.setHorizontalAlignment( SwingConstants.RIGHT );
        GridBagConstraints gbc_lblDescripcion = new GridBagConstraints( );
        gbc_lblDescripcion.fill = GridBagConstraints.BOTH;
        gbc_lblDescripcion.insets = new Insets( 0, 0, 5, 5 );
        gbc_lblDescripcion.gridx = 0;
        gbc_lblDescripcion.gridy = 2;
        panelAux.add( lblDescripcion, gbc_lblDescripcion );

        // Campo de texto para la descripción
        txtDescripcion = new JTextPane( );
        txtDescripcion.setContentType("text/html");
        txtDescripcion.setEditable( false );
        GridBagConstraints gbc_txtDescripcion = new GridBagConstraints( );
        gbc_txtDescripcion.gridheight = 3;
        gbc_txtDescripcion.gridwidth = 2;
        gbc_txtDescripcion.insets = new Insets( 0, 0, 5, 0 );
        gbc_txtDescripcion.fill = GridBagConstraints.BOTH;
        gbc_txtDescripcion.gridx = 1;
        gbc_txtDescripcion.gridy = 2;
        spDescripcion = new JScrollPane( txtDescripcion );
        panelAux.add( spDescripcion, gbc_txtDescripcion );

        // Etiqueta precio
        JLabel lblPrecio = new JLabel( "Precio: " );
        lblPrecio.setHorizontalAlignment( SwingConstants.RIGHT );
        GridBagConstraints gbc_lblPrecio = new GridBagConstraints( );
        gbc_lblPrecio.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblPrecio.insets = new Insets( 0, 0, 5, 5 );
        gbc_lblPrecio.gridx = 0;
        gbc_lblPrecio.gridy = 6;
        panelAux.add( lblPrecio, gbc_lblPrecio );

        // Campo de texto para el precio
        txtPrecio = new JTextField( );
        txtPrecio.setEditable( false );
        GridBagConstraints gbc_txtPrecio = new GridBagConstraints( );
        gbc_txtPrecio.gridwidth = 2;
        gbc_txtPrecio.insets = new Insets( 0, 0, 5, 0 );
        gbc_txtPrecio.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtPrecio.gridx = 1;
        gbc_txtPrecio.gridy = 6;
        panelAux.add( txtPrecio, gbc_txtPrecio );
        txtPrecio.setColumns( 10 );

        // Etiqueta unidades vendidas
        JLabel lblUnidadesVendidas = new JLabel( "Unidades vendidas" );
        lblUnidadesVendidas.setHorizontalAlignment( SwingConstants.RIGHT );
        GridBagConstraints gbc_lblUnidadesVendidas = new GridBagConstraints( );
        gbc_lblUnidadesVendidas.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblUnidadesVendidas.insets = new Insets( 0, 0, 5, 5 );
        gbc_lblUnidadesVendidas.gridx = 0;
        gbc_lblUnidadesVendidas.gridy = 8;
        panelAux.add( lblUnidadesVendidas, gbc_lblUnidadesVendidas );

        // Campo de texto para las unidades vendidas
        txtUnidadesVendidas = new JTextField( );
        txtUnidadesVendidas.setEditable( false );
        GridBagConstraints gbc_txtUnidadesVendidas = new GridBagConstraints( );
        gbc_txtUnidadesVendidas.gridwidth = 2;
        gbc_txtUnidadesVendidas.insets = new Insets( 0, 0, 5, 0 );
        gbc_txtUnidadesVendidas.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtUnidadesVendidas.gridx = 1;
        gbc_txtUnidadesVendidas.gridy = 8;
        panelAux.add( txtUnidadesVendidas, gbc_txtUnidadesVendidas );

        // Etiqueta valor ventas
        JLabel lblValorVentas = new JLabel( "Valor ventas:" );
        lblValorVentas.setHorizontalAlignment( SwingConstants.RIGHT );
        GridBagConstraints gbc_lblValorVentas = new GridBagConstraints( );
        gbc_lblValorVentas.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblValorVentas.insets = new Insets( 0, 0, 5, 5 );
        gbc_lblValorVentas.gridx = 0;
        gbc_lblValorVentas.gridy = 10;
        panelAux.add( lblValorVentas, gbc_lblValorVentas );

        // Campo de texto para el valor de las ventas
        txtValorVentas = new JTextField( );
        txtValorVentas.setEditable( false );
        GridBagConstraints gbc_txtValorVentas = new GridBagConstraints( );
        gbc_txtValorVentas.insets = new Insets( 0, 0, 5, 0 );
        gbc_txtValorVentas.gridwidth = 2;
        gbc_txtValorVentas.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtValorVentas.gridx = 1;
        gbc_txtValorVentas.gridy = 10;
        panelAux.add( txtValorVentas, gbc_txtValorVentas );

        // Botón eliminar
        btnEliminar = new JButton( ELIMINAR );
        btnEliminar.setActionCommand( ELIMINAR );
        btnEliminar.addActionListener( this );
        btnEliminar.setEnabled( false );
        GridBagConstraints gbc_btnEliminar = new GridBagConstraints( );
        gbc_btnEliminar.insets = new Insets( 0, 0, 0, 5 );
        gbc_btnEliminar.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnEliminar.gridx = 1;
        gbc_btnEliminar.gridy = 12;
        panelAux.add( btnEliminar, gbc_btnEliminar );

        // Botón vender
        btnVender = new JButton( VENDER );
        btnVender.setActionCommand( VENDER );
        btnVender.addActionListener( this );
        btnVender.setEnabled( false );
        GridBagConstraints gbc_btnVender = new GridBagConstraints( );
        gbc_btnVender.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnVender.gridx = 2;
        gbc_btnVender.gridy = 12;
        panelAux.add( btnVender, gbc_btnVender );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza el panel con la información del producto dado.<br>
     * @param pProducto Producto con la nueva información.
     */
    public void actualizar( Producto pProducto )
    {
        lblNombre.setText( "" );
        txtCodigo.setText( "" );
        txtDescripcion.setText( "" );
        txtPrecio.setText( "" );
        txtUnidadesVendidas.setText( "" );
        txtValorVentas.setText( "" );
        btnVender.setEnabled( false );
        btnEliminar.setEnabled( false );

        if( pProducto != null )
        {
            lblNombre.setText( pProducto.darNombre( ) );
            txtCodigo.setText( pProducto.darCodigo( ) );
            txtDescripcion.setText( pProducto.darDescripcion( ) );
            txtPrecio.setText( String.format( "COP $%,.2f", pProducto.darPrecio( ) ) );
            txtUnidadesVendidas.setText( "" + pProducto.darCantidadUnidadesVendidas( ) );
            txtValorVentas.setText( String.format( "COP $%,.2f", pProducto.darValorVentas( ) ) );
            btnVender.setEnabled( true );
            btnEliminar.setEnabled( true );
            spDescripcion.getVerticalScrollBar( ).setValue( 0 );
            txtDescripcion.setCaretPosition( 0 );
            
        }

    }

    /**
     * Manejo de eventos del usuario.
     * @param pEvento Evento de usuario. pEvento != null.
     */
    @Override
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
        switch( comando )
        {
            case VENDER:
                principal.venderProducto( txtCodigo.getText( ) );
                break;
            case ELIMINAR:
                principal.eliminarProducto( txtCodigo.getText( ) );
                break;
        }

    }

}
