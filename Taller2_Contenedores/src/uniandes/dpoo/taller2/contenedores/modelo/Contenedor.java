package uniandes.dpoo.taller2.contenedores.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import uniandes.dpoo.taller2.productos.modelo.NoPerecedero;
import uniandes.dpoo.taller2.productos.modelo.PerecederoNoRefrigeracion;
import uniandes.dpoo.taller2.productos.modelo.PerecederoRefrigeracion;
import uniandes.dpoo.taller2.productos.modelo.Producto;

public class Contenedor {
	private ArrayList<Cargamento> cargamentos;
	private double capacidadVolumetrica;
	private double capacidadPorPeso;
	private boolean esExclusivo;

	public Contenedor(ArrayList<Cargamento> cargamentos, int capacidadVolumetrica, int capacidadPorPeso,
			boolean esExclusivo) {
		this.cargamentos = cargamentos;
		this.capacidadVolumetrica = capacidadVolumetrica;
		this.capacidadPorPeso = capacidadPorPeso;
		this.esExclusivo = esExclusivo;
	}

	private double[] obtenerCapacidadUsada() {
		double[] capacidad = { 0.0, 0.0 }; // {volumen, peso}
		Collection<Cargamento> cargamentos = this.cargamentos;

		// Iterate cargamentos of Contenedor to get the currently occupied capacity
		for (Iterator<Cargamento> iterator = cargamentos.iterator(); iterator.hasNext();) {
			Cargamento cargamentoActual = iterator.next();
			Producto producto = cargamentoActual.getProducto();

			// Get peso-volumen and convert to tons-cubic meters
			double volumenProducto = producto.getVolumen() / Math.pow(10, 6);
			double pesoProducto = producto.getPeso() / Math.pow(10, 6);

			capacidad[0] += cargamentoActual.getUnidadesProducto() * volumenProducto;
			capacidad[1] += cargamentoActual.getUnidadesProducto() * pesoProducto;
		}

		return capacidad;
	}

	private boolean verificarCapacidad(double pesoAgregado, double volumenAgregado) {
		double[] capacidadUsada = obtenerCapacidadUsada();
		double volumenActual = capacidadUsada[0];
		double pesoActual = capacidadUsada[1];

		if (pesoAgregado + pesoActual > this.capacidadPorPeso) {
			System.out.println("ERROR: se ha excedido la capacidad por peso del contenedor");
			return false;
		}

		if (volumenAgregado + volumenActual > this.capacidadPorPeso) {
			System.out.println("ERROR: se ha excedido la capacidad volumétrica del contenedor");
			return false;
		}

		return true;
	}

	public void agregarCargamento(Cargamento cargamento) {
		// Verify capacidad of Contenedor
		double pesoAgregado = cargamento.getProducto().getPeso() / Math.pow(10, 6);
		double volumenAgregado = cargamento.getProducto().getVolumen() / Math.pow(10, 6);
		boolean hayCapacidad = this.verificarCapacidad(pesoAgregado, volumenAgregado);
		if (!hayCapacidad) {
			return;
		}

		if (this.cargamentos.size() > 0) {
			if (this.esExclusivo) {
				if (!cargamento.getProducto().equals(this.cargamentos.get(0).getProducto())) {
					System.out.println("ERROR: no se permite añadir productos distintos a un contenedor exclusivo");
					return;
				}
			}

			Collection<Cargamento> cargamentos = this.cargamentos;
			boolean hayPerecedero = false;
			boolean hayToxico = false;
			for (Iterator<Cargamento> iterator = cargamentos.iterator(); iterator.hasNext();) {
				Cargamento cargamentoActual = iterator.next();
				Producto productoActual = cargamentoActual.getProducto();

				if (productoActual instanceof NoPerecedero) {
					if (((NoPerecedero) productoActual).getToxicidad() > 0) {
						hayToxico = true;
					}
				} else if (productoActual instanceof PerecederoRefrigeracion
						|| productoActual instanceof PerecederoNoRefrigeracion) {
					hayPerecedero = true;
				}
			}

			if ((cargamento.getProducto() instanceof PerecederoRefrigeracion
					|| cargamento.getProducto() instanceof PerecederoNoRefrigeracion) && hayToxico) {
				System.out.println("ERROR: no se permite añadir productos perecederos con tóxicos");
				return;
			} else if (cargamento.getProducto() instanceof NoPerecedero && hayPerecedero) {
				if (((NoPerecedero) cargamento.getProducto()).getToxicidad() > 0) {
					System.out.println("ERROR: no se permite añadir productos tóxicos con perecederos");
					return;
				}
			}
		}

		this.cargamentos.add(cargamento);
	}

	public void eliminarCargamento(String id) {
		Collection<Cargamento> cargamentos = this.cargamentos;
		boolean deleted = false;

		Iterator<Cargamento> iterator = cargamentos.iterator();
		while (iterator.hasNext() && !deleted) {
			Cargamento cargamentoActual = iterator.next();
			if (cargamentoActual.getId().equals(id)) {
				this.cargamentos.remove(cargamentoActual);
				System.out.println("El cargamento" + id + "ha sido eliminado satisfactoriamente");
				return;
			}
		}

		System.out.println("ERROR: no se ha encontrado el cargamento a eliminar");
	}

	public String generarManifiesto() {
		String manifiesto = "\n\n** Manifiesto **\n\n";
		boolean refrigeracion = false;
		double maxTemp = 50.0;
		int toxicidad = 0;

		Collection<Cargamento> cargamentos = this.cargamentos;

		manifiesto += "* Cargamentos\n";
		for (Iterator<Cargamento> iterator = cargamentos.iterator(); iterator.hasNext();) {
			Cargamento cargamentoActual = iterator.next();
			Producto productoActual = cargamentoActual.getProducto();
			manifiesto += "- Cargamento" + cargamentoActual.getId() + "\n";
			manifiesto += "  Propiedad de '" + cargamentoActual.getPropietario() + "' \n";
			manifiesto += "  Contiene '" + cargamentoActual.getProducto().getNombre() + "' \n";
			manifiesto += "  Unidades: " + cargamentoActual.getUnidadesProducto() + "\n\n";

			if (productoActual instanceof NoPerecedero) {
				if (toxicidad < ((NoPerecedero) productoActual).getToxicidad()) {
					toxicidad = ((NoPerecedero) productoActual).getToxicidad();
				}
			} else if (productoActual instanceof PerecederoRefrigeracion) {
				refrigeracion = true;
				if (maxTemp > ((PerecederoRefrigeracion) productoActual).getMaxTemp()) {
					maxTemp = ((PerecederoRefrigeracion) productoActual).getMaxTemp();
				}
			} else if (productoActual instanceof PerecederoNoRefrigeracion) {
				if (!((PerecederoNoRefrigeracion) productoActual).getResisteCalor()) {
					refrigeracion = true;
				}
			}
		}

		manifiesto += "\n* Resumen final *\n";
		manifiesto += "Se tienen " + this.cargamentos.size() + " cargamentos en el contenedor actual\n";
		double[] capacidadUsada = obtenerCapacidadUsada();
		double volumenActual = capacidadUsada[0];
		double pesoActual = capacidadUsada[1];
		manifiesto += "Se han usado " + volumenActual + " de " + this.capacidadVolumetrica + " metros cúbicos\n";
		manifiesto += "Se han usado " + pesoActual + " de " + this.capacidadPorPeso + " toneladas\n";
		manifiesto += "Refrigeración requerida:" + refrigeracion + "\n";
		manifiesto += "Temperatura máxima: " + maxTemp + "\n";
		manifiesto += "Nivel de toxicidad: " + toxicidad + "\n";

		return manifiesto;
	}

	@Override
	public String toString() {
		return "Contenedor [cargamentos=" + cargamentos + ", capacidadVolumetrica=" + capacidadVolumetrica
				+ ", capacidadPorPeso=" + capacidadPorPeso + ", esExclusivo=" + esExclusivo + "]";
	}
}
