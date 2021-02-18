package uniandes.dpoo.taller2.extension.modelo;

import java.util.ArrayList;

public class ContenedorLifo extends Contenedor {
	public ContenedorLifo(ArrayList<Cargamento> cargamentos, double capacidadVolumetrica, double capacidadPorPeso,
			boolean esExclusivo) {
		super(cargamentos, capacidadVolumetrica, capacidadPorPeso, esExclusivo);
	}

	@Override
	public void eliminarCargamento(String id) {
		System.out.println(
				"ERROR: este tipo de contenedor no permite eliminar cargamentos diferentes al último insertado");
	}

	public void eliminarUltimoCargamento() {
		if (this.cargamentos.size() > 0) {
			this.cargamentos.remove(this.cargamentos.size() - 1);
			System.out.println("El cargamento ha sido eliminado satisfactoriamente");
		} else {
			System.out.println("ERROR: no se ha encontrado un cargamento a eliminar");
		}
	}
}
