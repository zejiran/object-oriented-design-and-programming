package test;

import java.io.BufferedReader;
import java.util.List;

/**
 * Clase que representa un producto del almac�n.
 */
public class TestProducto {

	public TestProducto(String pCodigo, String pNombre, String pDescripcion, double pPrecio) {
		codigo = pCodigo;
		nombre = pNombre;
		descripcion = pDescripcion;
		precio = pPrecio;
		cantidadUnidadesVendidas = 0;
	}

	/**
	 * Construye un nuevo producto a partir de la informaci�n del lector.<br>
	 * <b>post:</b> Los atributos c�digo, nombre, descripci�n, precio y unidades
	 * vendidas se inicializaron con la informaci�n del archivo. El hijo izquierda y
	 * el hijo derecha se inicializaron en null.
	 * 
	 * @param pLector Lector del archivo. pLector != null.
	 * @throws Exception Si ocurren errores al leer los datos.
	 */
	public TestProducto(BufferedReader pLector) throws Exception {
		String datos[] = pLector.readLine().split(";;;");
		codigo = datos[0];
		nombre = datos[1];
		descripcion = datos[2];
		precio = Double.parseDouble(datos[3]);
		cantidadUnidadesVendidas = Integer.parseInt(datos[4]);
	}

	// -------------------------------------------------------------
	// M�todos
	// -------------------------------------------------------------

	/**
	 * Retorna el c�digo del producto.
	 * 
	 * @return C�digo del producto.
	 */
	public String darCodigo() {
		return codigo;
	}

	/**
	 * Retorna el nombre del producto.
	 * 
	 * @return Nombre del producto.
	 */
	public String darNombre() {
		return nombre;
	}

	/**
	 * Retorna la descripci�n del producto.
	 * 
	 * @return Descripci�n del producto.
	 */
	public String darDescripcion() {
		return descripcion;
	}

	/**
	 * Retorna el precio del producto.
	 * 
	 * @return Precio del producto.
	 */
	public double darPrecio() {
		return precio;
	}

	/**
	 * Retorna la cantidad de unidades vendidas del producto.
	 * 
	 * @return Cantidad de unidades vendidas.
	 */
	public int darCantidadUnidadesVendidas() {
		return cantidadUnidadesVendidas;
	}

	/**
	 * Retorna el hijo izquierda.
	 * 
	 * @return hijo izquierda.
	 */
	public TestProducto darHijoIzquierda() {
		return hijoIzquierda;
	}

	/**
	 * Retorna el hijo derecha.
	 * 
	 * @return hijo derecha.
	 */
	public TestProducto darHijoDerecha() {
		return hijoDerecha;
	}

	/**
	 * Cambia el hijo izquierda por el valor dado por par�metro.<br>
	 * <b>post:</b> Se asign� el valor del par�metro a el hijo izquierda.
	 * 
	 * @param pProducto Producto que ser� asignado.
	 */
	public void cambiarHijoIzquierda(TestProducto pProducto) {
		hijoIzquierda = pProducto;
	}

	/**
	 * Cambia el hijo derecha por el valor dado por par�metro.<br>
	 * <b>post:</b> Se asign� el valor del par�metro a el hijo derecha.
	 * 
	 * @param pProducto Producto que ser� asignado.
	 */
	public void cambiarHijoDerecha(TestProducto pProducto) {
		hijoDerecha = pProducto;
	}

	/**
	 * Vende una cantidad de unidades dadas del producto.<br>
	 * <b>post: </b>La cantidad de unidades vendidas aument� en la cantidad dada por
	 * par�metro.
	 * 
	 * @param pCantidad Cantidad de unidades que se vendieron. pCantidad >=0.
	 */
	public void vender(int pCantidad) {
		cantidadUnidadesVendidas += pCantidad;
	}

	/**
	 * Retorna el valor de las ventas totales del producto.
	 * 
	 * @return Valor de las ventas del producto.
	 */
	public double darValorVentas() {
		return precio * cantidadUnidadesVendidas;
	}

	/**
	 * Compara el c�digo del producto con el valor dado por par�metro.
	 * 
	 * @param pCodigo C�digo con el cual se realizar� la comparaci�n. pCodigo !=
	 *                null && pCodigo != "".
	 * @return Retorna 0 si los 2 c�digos son iguales, 1 si el c�digo del producto
	 *         es mayor y -1 si es menor.
	 */
	public int comparar(String pCodigo) {
		int comp = codigo.compareTo(pCodigo);
		int respuesta = 0;
		if (comp > 0) {
			respuesta = 1;
		}
		if (comp < 0) {
			respuesta = -1;
		}
		return respuesta;
	}

	/**
	 * Retorna el peso del �rbol que tiene como ra�z este producto.
	 * 
	 * @return Peso del �rbol generado a partir de este producto.
	 */
	public int darPeso() {
		int total = 1;
		if (hijoIzquierda != null) {
			total += hijoIzquierda.darPeso();
		}
		if (hijoDerecha != null) {
			total += hijoDerecha.darPeso();
		}
		return total;
	}

	/**
	 * Agrega un producto al sub�rbol que contiene como ra�z este producto.<br>
	 * <b>post:</b> Si no exist�a un producto con el mismo c�digo, se agreg� el
	 * producto de tal manera que para cada nodo de este �rbol el hijo izquierda
	 * tiene un c�digo menor y el hijo derecha tiene un c�digo mayor.
	 * 
	 * @param pProducto Producto que se va a agregar. pProducto != null.
	 * @throws TestAlmacenException Si ya exist�a en el sub�rbol un producto con el
	 *                              c�digo dado.
	 */
	public void agregarProducto(TestProducto pProducto) throws TestAlmacenException {

		int comp = comparar(pProducto.darCodigo());
		boolean agrego = false;
		if (comp > 0) {
			if (hijoIzquierda == null) {
				hijoIzquierda = pProducto;
				agrego = true;
			} else {
				hijoIzquierda.agregarProducto(pProducto);
				agrego = true;
			}
		} else if (comp < 0) {
			if (hijoDerecha == null) {
				hijoDerecha = pProducto;
				agrego = true;
			} else {
				hijoDerecha.agregarProducto(pProducto);
				agrego = true;
			}
		}

		if (!agrego) {
			throw new TestAlmacenException("Ya existe un producto con el c�digo dado.");
		}
	}

	/**
	 * Busca un producto por c�digo en el sub�rbol generado a partir de este
	 * producto.
	 * 
	 * @param pCodigo C�digo del producto que se esta buscando. pCodigo != null &&
	 *                pCodigo != "".
	 * @return Producto con el c�digo dado por par�metro o null si no existe en el
	 *         sub�rbol un producto con ese c�digo.
	 */
	public TestProducto buscarProducto(String pCodigo) {
		int comp = comparar(pCodigo);
		TestProducto respuesta = this;

		if (comp > 0) {
			respuesta = hijoIzquierda == null ? null : hijoIzquierda.buscarProducto(pCodigo);
		} else if (comp < 0) {
			respuesta = hijoDerecha == null ? null : hijoDerecha.buscarProducto(pCodigo);
		}

		return respuesta;
	}

	/**
	 * Retorna el producto con el c�digo menor.
	 * 
	 * @return Producto con el c�digo menor.
	 */
	private TestProducto darProductoMenorCodigo() {
		TestProducto respuesta = this;
		if (hijoIzquierda != null) {
			respuesta = hijoIzquierda.darProductoMenorCodigo();
		}
		return respuesta;
	}

	/**
	 * Elimina el producto con c�digo dado del sub�rbol cuya ra�z es el nodo actual.
	 * 
	 * @param pCodigo C�digo del producto. pCodigo != null && pCodigo != "".
	 * @param pPadre  Padre del producto actual.
	 * @return True si elimin� el producto, false en caso contrario.
	 */
	public boolean eliminarProducto(String pCodigo, TestProducto pPadre) {
		int comp = comparar(pCodigo);
		boolean respuesta = false;
		if (comp > 0) {
			if (hijoIzquierda != null) {
				respuesta = hijoIzquierda.eliminarProducto(pCodigo, this);
			}
		} else if (comp < 0) {
			if (hijoDerecha != null) {
				respuesta = hijoDerecha.eliminarProducto(pCodigo, this);
			}
		} else {
			if (hijoIzquierda != null && hijoDerecha != null) {
				TestProducto menor = hijoDerecha.darProductoMenorCodigo();
				codigo = menor.codigo;
				nombre = menor.nombre;
				descripcion = menor.descripcion;
				precio = menor.precio;
				cantidadUnidadesVendidas = menor.cantidadUnidadesVendidas;

				hijoDerecha.eliminarProducto(codigo, this);
			} else if (pPadre.hijoIzquierda != null && pPadre.hijoIzquierda.codigo == codigo) {
				pPadre.hijoIzquierda = (hijoIzquierda != null) ? hijoIzquierda : hijoDerecha;
			} else if (pPadre.hijoDerecha != null && pPadre.hijoDerecha.codigo == codigo) {
				pPadre.hijoDerecha = (hijoIzquierda != null) ? hijoIzquierda : hijoDerecha;
			}
			respuesta = true;
		}

		return respuesta;
	}

	/**
	 * Agrega a la lista acumulada este producto y los productos de su sub�rbol.
	 * 
	 * @param pAcumulado Lista acumulada de todos los productos.
	 */
	public void darInorden(List<TestProducto> pAcumulado) {
		if (hijoIzquierda != null) {
			hijoIzquierda.darInorden(pAcumulado);
		}
		pAcumulado.add(this);

		if (hijoDerecha != null) {
			hijoDerecha.darInorden(pAcumulado);
		}
	}

	/**
	 * Retorna el c�digo y el nombre del producto separados por gui�n.
	 */
	@Override
	public String toString() {
		return codigo + " - " + nombre;
	}
}
