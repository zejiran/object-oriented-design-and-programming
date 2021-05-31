package test;


import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import uniandes.cupi2.almacen.mundo.Producto;

/**
 * Clase que representa un producto del almac�n.
 */
public class TestProducto {
	
	Producto product = new Producto("mock code", "mock name", "mock description", 1000);

	@Test
	@DisplayName("Obtain brand attributes")
	public void getNodosHijos() {
		assertNotNull(product.darCantidadUnidadesVendidas());
		assertNotNull(product.darCodigo());
		assertNotNull(product.darDescripcion());
		assertNotNull(product.darNombre());
		assertNotNull(product.darPeso());
		assertNotNull(product.darPrecio());
		assertEquals("mock code - mock name", product.toString());
		
	}
}
