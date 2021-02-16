package uniandes.dop.taller2.productos.modelo;

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

}
