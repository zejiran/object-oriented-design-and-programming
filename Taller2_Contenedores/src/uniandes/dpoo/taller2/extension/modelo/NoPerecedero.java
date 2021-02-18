package uniandes.dpoo.taller2.extension.modelo;

public class NoPerecedero extends Producto {
	private int toxicidad;
	private double MAX_TEMP = 50;

	public NoPerecedero(String nombre, double peso, double volumen, int toxicidad, boolean inflamable) {
		super(nombre, peso, volumen, inflamable);
		setToxicidad(toxicidad);
	}

	@Override
	public boolean equals(Object obj) {
		return obj.toString().equals(this.toString());
	}

	@Override
	public String toString() {
		return "No Perecedero: [nombre: " + this.nombre + ", peso: " + this.peso + ", volumen: " + this.volumen
				+ ", toxicidad: " + this.toxicidad + "]";
	}

	public void setToxicidad(int toxicidad) {
		if (0 <= toxicidad && toxicidad <= 5) {
			this.toxicidad = toxicidad;
		} else {
			System.out.println("Toxicidad is not valid.");
		}
	}

	public double getMAX_TEMP() {
		return MAX_TEMP;
	}

	public int getToxicidad() {
		return toxicidad;
	}
}
