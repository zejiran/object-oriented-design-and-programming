package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import uniandes.cupi2.almacen.mundo.Marca;
import uniandes.cupi2.almacen.mundo.NodoAlmacen;

/**
 * Clase abstracta que representa un nodo del �rbol del mercado.
 */
public abstract class TestNodoAlmacen {

	NodoAlmacen node = new Marca("mock id", "mock name");

	@Test
	@DisplayName("Obtain brand attributes")
	public void getNodosHijos() {
		assertTrue(node.equals(node));
		assertNotNull(node.darIdentificador());
		assertNotNull(node.darNombre());
		assertNotNull(node.darProductos());
		assertNotNull(node.darTipo());
		assertNotNull(node.darValorVentas());
	}

}
