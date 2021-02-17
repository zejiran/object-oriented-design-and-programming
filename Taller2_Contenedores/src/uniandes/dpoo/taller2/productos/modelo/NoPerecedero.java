package uniandes.dpoo.taller2.productos.modelo;

public class NoPerecedero extends Producto {
	private int toxicidad;
	private int MAX_TEMP = 50;
	
	public NoPerecedero(String nombre, double peso, double volumen, int toxicidad) {
		super(nombre, peso, volumen);
		setToxicidad(toxicidad);	
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
	
	public void setToxicidad(int toxicidad) {
	    if(0 <= toxicidad && toxicidad <= 5) {
	        this.toxicidad = toxicidad;
	    } else {
	        throw new IllegalArgumentException("Toxicidad is not valid.");
	    }
	}
	

}
