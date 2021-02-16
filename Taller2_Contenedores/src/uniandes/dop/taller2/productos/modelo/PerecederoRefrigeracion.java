package uniandes.dop.taller2.productos.modelo;

public class PerecederoRefrigeracion extends Producto {
	private double maxTemp;
	
	public PerecederoRefrigeracion(String nombre, double peso, double volumen, double maxTemp) {
		super(nombre, peso, volumen);
		this.maxTemp = maxTemp;
	}

//	@Override
//	public boolean equals(Object obj) {
//		Producto Prod = (Producto) obj;
//		if (obj.toString().contains("Perecedero de Refrigeración")) {
//			if (this.maxTemp == Prod.maxTemp && this.nombre.equals(Prod.nombre) && this.peso == obj.peso && this.volumen == obj.volumen) {
//				return true;
//			}
//		}
//	}
	
	@Override
	public String toString() {
		return "Perecedero de Refrigeración " + this.nombre;
	}
}
