package uniandes.dop.taller2.productos.modelo;

public class NoPerecedero extends Producto {
	private int toxicidad;
	private int MAX_TEMP = 50;
	
	public NoPerecedero(String nombre, double peso, double volumen, int toxicidad) {
		super(nombre, peso, volumen);
		this.toxicidad = toxicidad;	
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj.toString().equals(this.toString());
	}

	@Override
	public String toString() {
		return "No Perecedero: [nombre: " + this.nombre + ", peso: " + this.peso + ", volumen: "
				+ this.volumen + ", toxicidad: " + this.toxicidad + "]";
	}
	

}
