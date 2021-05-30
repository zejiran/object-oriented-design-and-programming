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

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import uniandes.cupi2.almacen.mundo.Marca;
import uniandes.cupi2.almacen.mundo.NodoAlmacen;

/**
 * Clase para manejar el icono del árbol del almacén.
 */
@SuppressWarnings("serial")
public class AlmacenCellRenderer extends DefaultTreeCellRenderer
{

    /**
     * @see javax.swing.tree.DefaultTreeCellRenderer#getTreeCellRendererComponent(javax.swing.JTree, java.lang.Object, boolean, boolean, boolean, int, boolean)
     */
    @Override
    public Component getTreeCellRendererComponent( JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus )
    {
        JLabel label = ( JLabel )super.getTreeCellRendererComponent( tree, value, selected, expanded, leaf, row, hasFocus );
        Object o = ( ( DefaultMutableTreeNode )value ).getUserObject( );
        label.setIcon( new ImageIcon( o instanceof Marca ? "./data/imagenes/marca.png" : "./data/imagenes/categoria.png" ) );
        label.setText( ( ( NodoAlmacen )o ).darNombre( ) );

        return label;
    }
}
