package uniandes.dpoo.taller2.contenedores.test;

import java.util.ArrayList;
import uniandes.dpoo.taller2.contenedores.modelo.Cargamento;
import uniandes.dpoo.taller2.contenedores.modelo.Contenedor;
import uniandes.dpoo.taller2.productos.modelo.NoPerecedero;
import uniandes.dpoo.taller2.productos.modelo.PerecederoNoRefrigeracion;
import uniandes.dpoo.taller2.productos.modelo.PerecederoRefrigeracion;
import uniandes.dpoo.taller2.productos.modelo.Producto;

public class Test {
	public static void main(String[] args) {
		Producto producto1 = new PerecederoNoRefrigeracion("Zucaritas", 300, 150, true);
		Producto producto2 = new NoPerecedero("Papas", 1500, 500, 0);
		Producto producto3 = new NoPerecedero("Papas Radioactivas", 1500, 500, 5);
		Producto producto4 = new PerecederoRefrigeracion("Helado", 3000, 1500, 0);
		Cargamento cargamento1 = new Cargamento(producto1, "Éxito", "A0001FS", 12);
		Cargamento cargamento1b = new Cargamento(producto1, "Éxito", "B0000FS", 12);
		Cargamento cargamento2 = new Cargamento(producto2, "Carulla", "GKSL1FS", 20);
		Cargamento cargamento3 = new Cargamento(producto3, "Ferney", "FLEP505", 50);
		Cargamento cargamento4 = new Cargamento(producto4, "Uniandes", "KALD4P", 400000);
		Cargamento cargamento4a = new Cargamento(producto4, "Uniandes", "KALD4P", 40);
		ArrayList<Cargamento> cargamentos =  new ArrayList<Cargamento>();
		
		Contenedor contenedorExclusivo = new Contenedor(cargamentos, 20.0, 30.0, true);
		contenedorExclusivo.agregarCargamento(cargamento1);
		contenedorExclusivo.agregarCargamento(cargamento3); // Error por exclusividad
		contenedorExclusivo.agregarCargamento(cargamento1b); // Se agrega correctamente el mismo tipo
		System.out.println(contenedorExclusivo.generarManifiesto());
		
		Contenedor contenedor = new Contenedor(cargamentos, 10.0, 10.0, false);
		contenedor.agregarCargamento(cargamento3); // No se permite agregar tóxicos con perecederos
		contenedorExclusivo.eliminarCargamento("B0000FS"); // Se elimina correctamente por ID
		contenedorExclusivo.eliminarCargamento("A0001FS"); // Se elimina correctamente por ID
		contenedor.agregarCargamento(cargamento3); // Se agrega un tóxico a un contenedor vacío
		System.out.println(contenedor.generarManifiesto());
		contenedorExclusivo.eliminarCargamento("FLEP505"); // Se elimina correctamente por ID
		contenedor.agregarCargamento(cargamento4); // Se agrega un cargamento demasiado pesado y grande
		contenedor.agregarCargamento(cargamento4a); // Se agrega un producto exitosamente
		contenedor.agregarCargamento(cargamento2); // Se agrega un producto exitosamente
		System.out.println(contenedor.generarManifiesto());
	}
}
