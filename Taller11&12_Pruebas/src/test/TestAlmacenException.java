package test;

import uniandes.cupi2.almacen.mundo.AlmacenException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.Assert.*;

public class TestAlmacenException {
	
	AlmacenException exception;

	@Test
	@DisplayName("Exception creation")
	public void exceptionCreation() {
		assertNull(exception);

		exception = new AlmacenException("excepción de prueba");

		assertNotNull(exception);
	}
}
