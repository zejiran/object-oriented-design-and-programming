package uniandes.dpoo.taller2.productos.modelo;

public class PerecederoNoRefrigeracion extends Producto{
	private boolean resisteCalor;
	
	public PerecederoNoRefrigeracion(String nombre, double peso, double volumen, boolean resisteCalor) {
		super(nombre, peso, volumen);
		this.resisteCalor = resisteCalor;
	}

	@Override
	public boolean equals(Object obj) {
		return obj.toString().equals(this.toString());
	}
	
	@Override
	public String toString() {
		return "Perecedero No Refrigeracion: [nombre: " + this.nombre + ", peso: " + this.peso + ", volumen: "
				+ this.volumen + ", resisteCalor: " + this.resisteCalor + "]";
	}

	public boolean isResisteCalor() {
		return resisteCalor;
	}

	public void setResisteCalor(boolean resisteCalor) {
		this.resisteCalor = resisteCalor;
	}
	
	
}
