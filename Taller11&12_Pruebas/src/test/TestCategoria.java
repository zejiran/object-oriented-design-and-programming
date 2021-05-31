package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import uniandes.cupi2.almacen.mundo.Categoria;

/**
 * Representa una categor�a del almac�n.
 */
public class TestCategoria {
	
	Categoria category = new Categoria("mock id", "mock name");

	@Test
	@DisplayName("Obtain Constant TIPO")
	public void getTIPO() {
		assertEquals("Categoria", Categoria.TIPO);
	}

	@Test
	@DisplayName("Obtain category attributes")
	public void getNodosHijos() {
		assertNotNull(category.darNodos());
		assertNotNull(category.darIdentificador());
		assertNotNull(category.darMarcas());
		assertNotNull(category.darPosorden());
		assertNotNull(category.darNombre());
		assertNotNull(category.darPreorden());
		assertNotNull(category.darProductos());
		assertNotNull(category.darValorVentas());
		assertNotNull(category.darTipo());
	}
}
