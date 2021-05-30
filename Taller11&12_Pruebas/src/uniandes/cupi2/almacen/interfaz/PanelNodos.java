/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad  de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado   bajo  el  esquema Academic Free License versión 2.1
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
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import uniandes.cupi2.almacen.mundo.Categoria;
import uniandes.cupi2.almacen.mundo.Marca;
import uniandes.cupi2.almacen.mundo.NodoAlmacen;

/**
 * Panel que muestra el árbol con los nodos.
 */
@SuppressWarnings("serial")
public class PanelNodos extends JPanel implements TreeSelectionListener, ActionListener, ListSelectionListener
{

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Constante para la acción agregar.
	 */
	private final static String AGREGAR = "Agregar";

	/**
	 * Constante para la acción eliminar.
	 */
	private final static String ELIMINAR = "Eliminar";

	/**
	 * Constante para la acción del combo ver como.
	 */
	private final static String VISTA = "Vista";

	/**
	 * Constante para la vista árbol.
	 */
	private final static String VER_ARBOL = "Ver como árbol";

	/**
	 * Constante para la vista lista preorden.
	 */
	private final static String VER_PREORDEN = "Ver lista en preorden";

	/**
	 * Constante para la vista lista preorden.
	 */
	private final static String VER_POSORDEN = "Ver lista en posorden";

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Ventana principal de la aplicación.
	 */
	private InterfazAlmacen principal;

	/**
	 * Categoría raíz del árbol.
	 */
	private Categoria categoriaRaiz;

	// -----------------------------------------------------------------
	// Atributos de la interfaz
	// -----------------------------------------------------------------
	/**
	 * Árbol que contiene los nodos.
	 */
	private JTree tree;

	/**
	 * La raíz del JTree.
	 */
	private DefaultMutableTreeNode raizJtree;

	/**
	 * JList para mostrar los nodos.
	 */
	@SuppressWarnings("rawtypes")
	private JList listNodos;

	/**
	 * Scroll pane que contiene el árbol.
	 */
	private JScrollPane spNodos;

	/**
	 * Botón para agregar un nodo.
	 */
	private JButton btnAgregar;

	/**
	 * Botón para eliminar un nodo.
	 */
	private JButton btnEliminar;

	/**
	 * Combo para el tipo de vista.
	 */
	private JComboBox<String> cbVerComo;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Construye un nuevo panel nodos.
	 * 
	 * @param pInterfaz Ventana principal de la aplicación. pInterfaz != null.
	 * @param pRaiz     Categoría raíz. pRaiz != null.
	 */
	public PanelNodos(InterfazAlmacen pInterfaz, Categoria pRaiz)
	{
		setLayout(new BorderLayout(0, 15));
		setBorder(new TitledBorder("Categorías y marcas"));
		principal = pInterfaz;
		categoriaRaiz = pRaiz;

		btnEliminar = new JButton(ELIMINAR);
		btnEliminar.addActionListener(this);
		btnEliminar.setActionCommand(ELIMINAR);

		btnAgregar = new JButton(AGREGAR);
		btnAgregar.addActionListener(this);
		btnAgregar.setActionCommand(AGREGAR);

		cbVerComo = new JComboBox<>(new String[] { VER_ARBOL, VER_PREORDEN, VER_POSORDEN });
		cbVerComo.addActionListener(this);
		cbVerComo.addActionListener(this);
		cbVerComo.setActionCommand(VISTA);
		add(cbVerComo, BorderLayout.NORTH);

		listNodos = new JList<>(pRaiz.darPreorden().toArray());
		listNodos.addListSelectionListener(this);

		spNodos = new JScrollPane();
		spNodos.setPreferredSize(new Dimension(250, 550));
		add(spNodos, BorderLayout.WEST);

		actualizar(pRaiz);
		seleccionarNodo(pRaiz.darIdentificador());

		JPanel panelBotones = new JPanel(new GridLayout(1, 2));
		panelBotones.add(btnEliminar);
		panelBotones.add(btnAgregar);
		add(panelBotones, BorderLayout.SOUTH);

	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Actualiza el panel con la raíz dada.<br>
	 * <b>post: </b> Vuelve a crear el árbol con la nueva información y actualiza el
	 * viewport del scroll pane.
	 * 
	 * @param pRaiz Categoría raíz del almacén. pRaiz != null.
	 */
	public void actualizar(Categoria pRaiz)
	{
		String idSeleccionado = darIdSeleccionado();
		List<String> abiertos = new ArrayList<>();
		if (tree != null)
		{
			for (int i = 0; i < tree.getRowCount() - 1; i++)
			{
				TreePath pathActual = tree.getPathForRow(i);
				TreePath pathSiguiente = tree.getPathForRow(i + 1);
				if (pathActual.isDescendant(pathSiguiente))
				{
					DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) pathActual.getLastPathComponent();
					abiertos.add(((NodoAlmacen) treeNode.getUserObject()).darIdentificador());
				}
			}
		}

		categoriaRaiz = pRaiz;
		String vista = (String) cbVerComo.getSelectedItem();
		listNodos = new JList<>((vista.equals(VER_PREORDEN) ? pRaiz.darPreorden() : pRaiz.darPosorden()).toArray());
		listNodos.addListSelectionListener(this);

		raizJtree = new DefaultMutableTreeNode(pRaiz);
		agregarHijos(raizJtree, pRaiz);

		tree = new JTree(raizJtree);
		if (vista.equals(VER_ARBOL))
		{
			spNodos.setViewportView(tree);
		}
		else
		{
			spNodos.setViewportView(listNodos);
		}

		ImageIcon imageIcon = new ImageIcon("./data/imagenes/marca.png");
		DefaultTreeCellRenderer renderer = new AlmacenCellRenderer();
		renderer.setLeafIcon(imageIcon);

		tree.setCellRenderer(renderer);
		tree.addTreeSelectionListener(this);

		for (String id : abiertos)
		{
			DefaultMutableTreeNode treeNode = buscar(id);
			if (treeNode != null)
			{
				tree.expandPath(new TreePath(treeNode.getPath()));
			}
		}

		if (idSeleccionado != null)
		{
			seleccionarNodo(idSeleccionado);
		}
	}

	/**
	 * Agrega los hijos de la categoría al nodo del JTree.<br>
	 * <b>post:</b> Los nodos hijos de la categoría se agregaron al JTree junto con
	 * su subárbol.
	 * 
	 * @param pNodo      NodoAlmacen del JTree al cual se agregaran los nodos. pNodo
	 *                   != null.
	 * @param pCategoria Categoría del almacén que corresponde al nodo del JTree.
	 */
	public void agregarHijos(DefaultMutableTreeNode pNodo, Categoria pCategoria)
	{
		for (NodoAlmacen nodoAlmacen : pCategoria.darNodos())
		{
			DefaultMutableTreeNode nodoArbol = new DefaultMutableTreeNode(nodoAlmacen);
			if (nodoAlmacen instanceof Categoria)
			{
				agregarHijos(nodoArbol, (Categoria) nodoAlmacen);
			}
			pNodo.add(nodoArbol);
		}
	}

	/**
	 * Retorna el identificador del nodo seleccionado.
	 * 
	 * @return Identificador del nodo seleccionado.
	 */
	public String darIdSeleccionado()
	{
		if (tree == null || raizJtree == null || tree.getSelectionPath() == null)
		{
			return null;
		}

		String vista = (String) cbVerComo.getSelectedItem();
		NodoAlmacen seleccionado = (NodoAlmacen) listNodos.getSelectedValue();
		if (vista.equals(VER_ARBOL))
		{
			DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
			return ((NodoAlmacen) treeNode.getUserObject()).darIdentificador();
		}

		return seleccionado.darIdentificador();
	}

	/**
	 * Selecciona el nodo almacén con el identificador dado en el JTree.<br>
	 * <b>post: </b>Se seleccionó el nodo en el JTree.
	 * 
	 * @param pIdNodo Identificador del nodo almacén. pIdentificador != null.
	 */
	private void seleccionarNodoArbol(String pIdNodo)
	{
		DefaultMutableTreeNode treeNode = buscar(pIdNodo);
		if (treeNode != null)
		{
			tree.setSelectionPath(new TreePath(treeNode.getPath()));
			tree.scrollPathToVisible(new TreePath(treeNode.getPath()));
			tree.expandPath(new TreePath(treeNode.getPath()));
		}
	}

	/**
	 * Selecciona el nodo almacén con el identificador dado en la lista de
	 * nodos.<br>
	 * <b>post: </b>Se seleccionó el nodo en la lista de nodos.
	 * 
	 * @param pIdNodo Identificador del nodo almacén. pIdentificador != null.
	 */
	private void seleccionarNodoLista(String pIdNodo)
	{
		if (listNodos != null)
		{
			for (int i = 0; i < listNodos.getModel().getSize(); i++)
			{
				NodoAlmacen p = (NodoAlmacen) listNodos.getModel().getElementAt(i);
				if (p.darIdentificador().equals(pIdNodo))
				{
					listNodos.setSelectedIndex(i);
					break;
				}
			}
		}
	}

	/**
	 * Selecciona el nodo con el identificador dado.<br>
	 * <b>pre: </b> El árbol está inicializado.
	 * 
	 * @param pIdNodo Identificador del nodo. pIdNodo != null && pIdNodo != "".
	 */
	public void seleccionarNodo(String pIdNodo)
	{
		seleccionarNodoArbol(pIdNodo);

		seleccionarNodoLista(pIdNodo);
	}

	/**
	 * Retorna el nodo del jtree que contiene el nodo almacén con el identificador
	 * dado.
	 * 
	 * @param pIdNodo Identificador del nodo. pIdNodo != null && pIdNodo != "".
	 * @return Nodo del jtree que contiene el nodo almacén con el identificador dado
	 *         o null si no existe.
	 */
	private DefaultMutableTreeNode buscar(String pIdNodo)
	{
		Enumeration<TreeNode> e = raizJtree.depthFirstEnumeration();
		while (e.hasMoreElements())
		{
			DefaultMutableTreeNode nodoJtree = (DefaultMutableTreeNode) e.nextElement();
			NodoAlmacen nodoAlmacen = (NodoAlmacen) nodoJtree.getUserObject();
			if (nodoAlmacen.darIdentificador().equalsIgnoreCase(pIdNodo))
			{
				return nodoJtree;
			}
		}
		return null;
	}

	/**
	 * Manejo de eventos del usuario.
	 * 
	 * @param pEvento Evento de usuario. pEvento != null.
	 */
	@Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String comando = pEvento.getActionCommand();
		switch (comando)
		{
		case AGREGAR:
			principal.agregarNodo();
			break;
		case ELIMINAR:
			principal.eliminarNodo(darIdSeleccionado());
			break;
		case VISTA:
			String idSeleccionado = darIdSeleccionado();
			String vista = (String) cbVerComo.getSelectedItem();
			if (vista.equals(VER_ARBOL))
			{
				spNodos.setViewportView(tree);
			}
			else
			{
				listNodos = new JList<>(
						(vista.equals(VER_PREORDEN) ? categoriaRaiz.darPreorden() : categoriaRaiz.darPosorden())
								.toArray());
				listNodos.addListSelectionListener(this);
				spNodos.setViewportView(listNodos);
			}
			seleccionarNodo(idSeleccionado);
			break;
		}

	}

	/**
	 * Maneja la acción cuando se selecciona un nodo.
	 * 
	 * @param pNodo Nodo seleccionado. pNodo != null.
	 */
	private void nodoSeleccionadoAccion(NodoAlmacen pNodo)
	{
		principal.actualizarInformacionNodo(pNodo.darIdentificador());
		if (pNodo instanceof Marca)
		{
			btnAgregar.setEnabled(false);
		}
		else
		{
			btnAgregar.setEnabled(true);
		}
	}

	/**
	 * Manejo de eventos del JTree.
	 * 
	 * @param pEvento Evento de usuario. pEvento != null.
	 */
	@Override
	public void valueChanged(TreeSelectionEvent pEvento)
	{
		DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) pEvento.getPath().getLastPathComponent();
		NodoAlmacen nodoAlmacen = (NodoAlmacen) treeNode.getUserObject();
		seleccionarNodoLista(nodoAlmacen.darIdentificador());
		nodoSeleccionadoAccion(nodoAlmacen);
	}

	/**
	 * Manejo del evento del usuario al seleccionar un producto de la lista.
	 * 
	 * @param pEvento Evento generado. pEvento != null.
	 */
	@Override
	public void valueChanged(ListSelectionEvent pEvento)
	{
		NodoAlmacen nodoAlmacen = (NodoAlmacen) listNodos.getSelectedValue();
		seleccionarNodoArbol(nodoAlmacen.darIdentificador());
		nodoSeleccionadoAccion(nodoAlmacen);
	}

}
