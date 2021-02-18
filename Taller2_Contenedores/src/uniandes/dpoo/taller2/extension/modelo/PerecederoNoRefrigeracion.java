package uniandes.dpoo.taller2.extension.modelo;

public class PerecederoNoRefrigeracion extends Producto {
	private boolean resisteCalor;
	private String tipo;
	private String fechaVencimiento;

	public PerecederoNoRefrigeracion(String nombre, double peso, double volumen, boolean inflamable,
			boolean resisteCalor, String tipo, String fechaVencimiento) {
		super(nombre, peso, volumen, inflamable);
		this.resisteCalor = resisteCalor;
		this.tipo = tipo;
		this.setFechaVencimiento(fechaVencimiento);
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

	public boolean getResisteCalor() {
		return resisteCalor;
	}

	public void setResisteCalor(boolean resisteCalor) {
		this.resisteCalor = resisteCalor;
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
