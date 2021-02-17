package uniandes.dpoo.taller2.productos.modelo;

public class PerecederoRefrigeracion extends Producto {
	private double maxTemp;
	
	public PerecederoRefrigeracion(String nombre, double peso, double volumen, double maxTemp) {
		super(nombre, peso, volumen);
		this.maxTemp = maxTemp;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj.toString().equals(this.toString());
	}
	
	
	@Override
	public String toString() {
		return "Perecedero Refrigeracion: [nombre: " + this.nombre + ", peso: " + this.peso + ", volumen: "
				+ this.volumen + ", máxima temperatura: " + this.maxTemp + "]";
	}

	public double getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(double maxTemp) {
		this.maxTemp = maxTemp;
	}
	
	
}
