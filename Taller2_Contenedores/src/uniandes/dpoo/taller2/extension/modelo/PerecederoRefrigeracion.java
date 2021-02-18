package uniandes.dpoo.taller2.extension.modelo;

public class PerecederoRefrigeracion extends Producto {
	private double maxTemp;
	private String tipo;
	private String fechaVencimiento;

	public PerecederoRefrigeracion(String nombre, double peso, double volumen, double maxTemp, boolean inflamable,
			String tipo, String fechaVencimiento) {
		super(nombre, peso, volumen, inflamable);
		this.maxTemp = maxTemp;
		this.tipo = tipo;
		this.fechaVencimiento = fechaVencimiento;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
}
