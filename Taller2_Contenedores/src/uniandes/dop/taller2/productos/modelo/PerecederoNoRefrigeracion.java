package uniandes.dop.taller2.productos.modelo;

public class PerecederoNoRefrigeracion extends Producto{
	private boolean resisteCalor;
	
	public PerecederoNoRefrigeracion(String nombre, double peso, double volumen, boolean resisteCalor) {
		super(nombre, peso, volumen);
		this.resisteCalor = resisteCalor;
	}

	@Override
	public String toString() {
		return "Perecedero de No Refrigeración " + this.nombre;
	}
	
	
}
