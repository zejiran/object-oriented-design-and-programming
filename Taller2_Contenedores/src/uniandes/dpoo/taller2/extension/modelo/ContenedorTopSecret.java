package uniandes.dpoo.taller2.extension.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ContenedorTopSecret extends Contenedor {
	public ContenedorTopSecret(ArrayList<Cargamento> cargamentos, double capacidadVolumetrica, double capacidadPorPeso,
			boolean esExclusivo) {
		super(cargamentos, capacidadVolumetrica, capacidadPorPeso, esExclusivo);
	}

	@Override
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
			manifiesto += "\n- Cargamento " + cargamentoActual.getId() + "\n";
			manifiesto += "  Propiedad anónima\n";
			manifiesto += "  Contiene producto secreto\n";
			manifiesto += "  Unidades: " + cargamentoActual.getUnidadesProducto() + "\n";

			if (productoActual instanceof NoPerecedero) {
				if (toxicidad < ((NoPerecedero) productoActual).getToxicidad()) {
					toxicidad = ((NoPerecedero) productoActual).getToxicidad();
				}
			} else if (productoActual instanceof PerecederoRefrigeracion) {
				refrigeracion = true;
				if (maxTemp > ((PerecederoRefrigeracion) productoActual).getMaxTemp()) {
					maxTemp = ((PerecederoRefrigeracion) productoActual).getMaxTemp();
				}
				manifiesto += "  Fecha de vencimiento: "
						+ ((PerecederoRefrigeracion) productoActual).getFechaVencimiento() + "\n";
			} else if (productoActual instanceof PerecederoNoRefrigeracion) {
				if (!((PerecederoNoRefrigeracion) productoActual).getResisteCalor()) {
					refrigeracion = true;
				}
				manifiesto += "  Fecha de vencimiento: "
						+ ((PerecederoNoRefrigeracion) productoActual).getFechaVencimiento() + "\n";
			}
		}

		manifiesto += "\n\n* Resumen final *\n";
		manifiesto += "  Se tienen " + this.cargamentos.size() + " cargamentos en el contenedor actual\n";
		double[] capacidadUsada = obtenerCapacidadUsada();
		double volumenActual = capacidadUsada[0];
		double pesoActual = capacidadUsada[1];
		manifiesto += "  Se han usado " + volumenActual + " de " + this.capacidadVolumetrica + " metros cúbicos\n";
		manifiesto += "  Se han usado " + pesoActual + " de " + this.capacidadPorPeso + " toneladas\n";
		manifiesto += "  Refrigeración requerida: " + (refrigeracion ? "sí" : "no") + "\n";
		manifiesto += "  Temperatura máxima: " + maxTemp + "\n";
		manifiesto += "  Nivel de toxicidad: " + toxicidad + "\n";

		return manifiesto;
	}
}
