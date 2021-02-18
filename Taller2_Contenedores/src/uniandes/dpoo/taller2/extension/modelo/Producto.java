package uniandes.dpoo.taller2.extension.modelo;

public abstract class Producto {
	protected String nombre;
	protected double peso;
	protected double volumen;
	protected boolean inflamable;

	protected Producto(String nombre, double peso, double volumen, boolean inflamable) {
		this.nombre = nombre;
		this.peso = peso;
		this.volumen = volumen;
		this.inflamable = inflamable;
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

	public boolean getInflamabilidad() {
		return inflamable;
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

	public void setInflamabilidad(boolean inflamable) {
		this.inflamable = inflamable;
	}
}
