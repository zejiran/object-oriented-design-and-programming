package uniandes.dpoo.taller2.productos.modelo;

public abstract class Producto {
	protected String nombre;
	protected double peso;
	protected double volumen;

	protected Producto(String nombre, double peso, double volumen) {
		this.nombre = nombre;
		this.peso = peso;
		this.volumen = volumen;
	}

	public abstract String toString();

	public String getNombre() {
		return nombre;
	}

	public double getPeso() {
		return peso;
	}

	public double getVolumen() {
		return volumen;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}
}
