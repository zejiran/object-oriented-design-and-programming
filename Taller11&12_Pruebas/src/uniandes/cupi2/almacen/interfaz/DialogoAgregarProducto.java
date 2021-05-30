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
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import uniandes.cupi2.almacen.mundo.Marca;

import java.awt.GridBagLayout;

/**
 * Dialogo para agregar un producto.
 */
@SuppressWarnings("serial")
public class DialogoAgregarProducto extends JDialog implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para ejecutar la acción del botón btnAgregar.
     */
    private static final String AGREGAR = "Agregar producto";

    /**
     * Comando para ejecutar la acción del botón btnCancelar.
     */
    private static final String CANCELAR = "Cancelar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazAlmacen principal;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Combo para la marca del producto.
     */
    @SuppressWarnings("rawtypes")
    private JComboBox cbMarca;

    /**
     * Campo de texto para el código del producto.
     */
    private JTextField txtCodigo;

    /**
     * Campo de texto para el nombre del producto.
     */
    private JTextField txtNombre;

    /**
     * Campo de texto para el precio del producto.
     */
    private JTextField txtPrecio;

    /**
     * Campo de texto para la descripción del producto.
     */
    private JTextPane txtDescripcion;

    /**
     * Botón para agregar el producto.
     */
    private JButton btnAgregar;

    /**
     * Botón para cancelar la acción.
     */
    private JButton btnCancelar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el dialogo que modifica el jugador.<br>
     * <b> post: </b> Todos los botones, etiquetas y campos de texto fueron inicializados y ubicados. Se asigno el atributo principal con el valor dado por parámetro.
     * @param pInterfaz Interfaz principal de la aplicación. pInterfaz != null
     * @param pMarcas Lista con las marcas a las cuales se puede agregar el producto.
     */
    public DialogoAgregarProducto( InterfazAlmacen pInterfaz, List<Marca> pMarcas )
    {
        principal = pInterfaz;
        setTitle( "Agregar Producto" );
        getContentPane( ).setLayout( new BorderLayout( ) );
        setSize( 550, 500 );

        GridBagLayout gbl = new GridBagLayout( );
        gbl.columnWeights = new double[]{ 0, 1, 1 };
        gbl.rowWeights = new double[]{ 0.0, 0.0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 };
        JPanel panelAux = new JPanel( gbl );
        panelAux.setBorder( new EmptyBorder( 10, 10, 10, 10 ) );
        getContentPane( ).add( panelAux, BorderLayout.CENTER );

        // Etiqueta marca
        JLabel lblMarca = new JLabel( "Marca:" );
        lblMarca.setHorizontalAlignment( SwingConstants.RIGHT );
        GridBagConstraints gbc_lblMarca = new GridBagConstraints( );
        gbc_lblMarca.fill = GridBagConstraints.BOTH;
        gbc_lblMarca.insets = new Insets( 0, 0, 5, 5 );
        gbc_lblMarca.gridx = 0;
        gbc_lblMarca.gridy = 0;
        panelAux.add( lblMarca, gbc_lblMarca );

        // Combo para la marca
        cbMarca = new JComboBox<>( pMarcas.toArray( ) );
        GridBagConstraints gbc_cbMarca = new GridBagConstraints( );
        gbc_cbMarca.gridwidth = 2;
        gbc_cbMarca.fill = GridBagConstraints.BOTH;
        gbc_cbMarca.insets = new Insets( 0, 0, 5, 5 );
        gbc_cbMarca.gridx = 1;
        gbc_cbMarca.gridy = 0;
        panelAux.add( cbMarca, gbc_cbMarca );

        // Etiqueta codigo
        JLabel lblCodigo = new JLabel( "Codigo:" );
        lblCodigo.setHorizontalAlignment( SwingConstants.RIGHT );
        GridBagConstraints gbc_lblCodigo = new GridBagConstraints( );
        gbc_lblCodigo.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblCodigo.insets = new Insets( 0, 0, 5, 5 );
        gbc_lblCodigo.gridx = 0;
        gbc_lblCodigo.gridy = 2;
        panelAux.add( lblCodigo, gbc_lblCodigo );

        // Campo de texto para el codigo
        txtCodigo = new JTextField( );
        GridBagConstraints gbc_txtCodigo = new GridBagConstraints( );
        gbc_txtCodigo.gridwidth = 2;
        gbc_txtCodigo.insets = new Insets( 0, 0, 5, 0 );
        gbc_txtCodigo.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtCodigo.gridx = 1;
        gbc_txtCodigo.gridy = 2;
        panelAux.add( txtCodigo, gbc_txtCodigo );

        // Etiqueta nombre
        JLabel lblNombre = new JLabel( "Nombre:" );
        lblNombre.setHorizontalAlignment( SwingConstants.RIGHT );
        GridBagConstraints gbc_lblNombre = new GridBagConstraints( );
        gbc_lblNombre.anchor = GridBagConstraints.EAST;
        gbc_lblNombre.insets = new Insets( 0, 0, 5, 5 );
        gbc_lblNombre.gridx = 0;
        gbc_lblNombre.gridy = 4;
        panelAux.add( lblNombre, gbc_lblNombre );

        // Campo de texto para el nombre
        txtNombre = new JTextField( );
        GridBagConstraints gbc_textField = new GridBagConstraints( );
        gbc_textField.gridwidth = 2;
        gbc_textField.insets = new Insets( 0, 0, 5, 0 );
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 1;
        gbc_textField.gridy = 4;
        panelAux.add( txtNombre, gbc_textField );

        // Etiqueta descripción
        JLabel lblDescripcion = new JLabel( "Descripción:" );
        lblDescripcion.setVerticalAlignment( SwingConstants.TOP );
        lblDescripcion.setHorizontalAlignment( SwingConstants.RIGHT );
        GridBagConstraints gbc_lblDescripcion = new GridBagConstraints( );
        gbc_lblDescripcion.fill = GridBagConstraints.BOTH;
        gbc_lblDescripcion.insets = new Insets( 0, 0, 5, 5 );
        gbc_lblDescripcion.gridx = 0;
        gbc_lblDescripcion.gridy = 6;
        panelAux.add( lblDescripcion, gbc_lblDescripcion );

        // Campo de texto para la descripción
        txtDescripcion = new JTextPane( );
        GridBagConstraints gbc_txtDescripcion = new GridBagConstraints( );
        gbc_txtDescripcion.gridheight = 3;
        gbc_txtDescripcion.gridwidth = 2;
        gbc_txtDescripcion.insets = new Insets( 0, 0, 5, 0 );
        gbc_txtDescripcion.fill = GridBagConstraints.BOTH;
        gbc_txtDescripcion.gridx = 1;
        gbc_txtDescripcion.gridy = 6;
        JScrollPane spDescripcion = new JScrollPane( txtDescripcion );
        panelAux.add( spDescripcion, gbc_txtDescripcion );

        // Etiqueta precio
        JLabel lblPrecio = new JLabel( "Precio: " );
        lblPrecio.setHorizontalAlignment( SwingConstants.RIGHT );
        GridBagConstraints gbc_lblPrecio = new GridBagConstraints( );
        gbc_lblPrecio.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblPrecio.insets = new Insets( 0, 0, 5, 5 );
        gbc_lblPrecio.gridx = 0;
        gbc_lblPrecio.gridy = 10;
        panelAux.add( lblPrecio, gbc_lblPrecio );

        // Campo de texto para el precio
        txtPrecio = new JTextField( );
        GridBagConstraints gbc_txtPrecio = new GridBagConstraints( );
        gbc_txtPrecio.gridwidth = 2;
        gbc_txtPrecio.insets = new Insets( 0, 0, 5, 0 );
        gbc_txtPrecio.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtPrecio.gridx = 1;
        gbc_txtPrecio.gridy = 10;
        panelAux.add( txtPrecio, gbc_txtPrecio );

        // Botón agregar
        btnAgregar = new JButton( AGREGAR );
        btnAgregar.setActionCommand( AGREGAR );
        btnAgregar.addActionListener( this );

        GridBagConstraints gbc_btnAgregar = new GridBagConstraints( );
        gbc_btnAgregar.insets = new Insets( 0, 0, 0, 5 );
        gbc_btnAgregar.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnAgregar.gridx = 1;
        gbc_btnAgregar.gridy = 12;
        panelAux.add( btnAgregar, gbc_btnAgregar );

        // Botón vender
        btnCancelar = new JButton( CANCELAR );
        btnCancelar.setActionCommand( CANCELAR );
        btnCancelar.addActionListener( this );
        GridBagConstraints gbc_btnCancelar = new GridBagConstraints( );
        gbc_btnCancelar.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnCancelar.gridx = 2;
        gbc_btnCancelar.gridy = 12;
        panelAux.add( btnCancelar, gbc_btnCancelar );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

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

            case AGREGAR:
                try
                {

                    String idPadre = ( ( Marca )cbMarca.getSelectedItem( ) ).darIdentificador( );
                    String codigo = txtCodigo.getText( ).trim( );
                    String nombre = txtNombre.getText( ).trim( );
                    String strPrecio = txtPrecio.getText( ).trim( );
                    String descripcion = txtDescripcion.getText( );
                    if(idPadre.isEmpty( ) || codigo.isEmpty( ) || nombre.isEmpty( ) || strPrecio.isEmpty( ) || descripcion.isEmpty( ) ){
                        throw new Exception("Faltan campos por ingresar.");
                    }
                    
                    double precio = Double.parseDouble( strPrecio );
                    if(precio<=0){
                        throw new NumberFormatException( );
                    }

                    principal.agregarProducto( idPadre, codigo, nombre, descripcion, precio );
                    dispose( );

                }
                catch( NumberFormatException e )
                {
                    JOptionPane.showMessageDialog( this, "El precio debe ser un valor entero mayor a 0.", "Agregar producto", JOptionPane.ERROR_MESSAGE );
                }
                catch( Exception e )
                {
                    JOptionPane.showMessageDialog( this, e.getMessage( ), "Agregar producto", JOptionPane.ERROR_MESSAGE );
                }
                break;
            case CANCELAR:
                dispose( );
                break;

        }
    }
}
