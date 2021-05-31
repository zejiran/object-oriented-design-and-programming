package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import uniandes.cupi2.almacen.mundo.Marca;

/**
 * Clase que representa una Marca.
 */
public class TestMarca {
	
	Marca brand = new Marca("mock id", "mock name");

	@Test
	@DisplayName("Obtain Constant TIPO")
	public void getTIPO() {
		assertEquals("Marca", Marca.TIPO);
	}

	@Test
	@DisplayName("Obtain brand attributes")
	public void getNodosHijos() {
		assertNotNull(brand.darCantidadProductos());
		assertNotNull(brand.darIdentificador());
		assertNotNull(brand.darNombre());
		assertNotNull(brand.darProductos());
		assertNotNull(brand.darTipo());
		assertNotNull(brand.darValorVentas());
	}
}
