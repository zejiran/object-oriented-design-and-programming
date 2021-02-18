package uniandes.dpoo.taller2.extension.modelo;

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

	public Producto getProducto() {
		return producto;
	}

	public String getPropietario() {
		return propietario;
	}

	public String getId() {
		return id;
	}

	public int getUnidadesProducto() {
		return unidadesProducto;
	}
}
