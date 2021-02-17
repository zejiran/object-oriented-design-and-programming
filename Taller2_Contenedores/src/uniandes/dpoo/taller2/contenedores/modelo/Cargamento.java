package uniandes.dpoo.taller2.contenedores.modelo;
package uniandes.dpoo.taller2.productos.modelo;

public class Cargamento {
	private Producto producto;
	private String propietario;
	private String id;
	private int unidadesProducto;

	public Cargamento(Producto producto, String propietario, String id, int unidadesProducto) {
		this.producto = producto;
		this.propietario = propietario;
		this.id = id;
		this.unidadesProducto = unidadesProducto;
	}
}
