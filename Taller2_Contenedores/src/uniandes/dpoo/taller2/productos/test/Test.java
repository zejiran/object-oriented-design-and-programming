package uniandes.dpoo.taller2.productos.test;

import uniandes.dpoo.taller2.productos.modelo.NoPerecedero;
import uniandes.dpoo.taller2.productos.modelo.PerecederoNoRefrigeracion;
import uniandes.dpoo.taller2.productos.modelo.PerecederoRefrigeracion;
import uniandes.dpoo.taller2.productos.modelo.Producto;

public class Test {
	public static void main(String[] args) {
		Producto producto1 = new PerecederoNoRefrigeracion("Zucaritas", 300, 150, true);
		Producto producto2 = new PerecederoNoRefrigeracion("Zucaritas", 300, 150, true);
		Producto producto3 = new NoPerecedero("Papas", 1500, 500, 0);
		Producto producto4 = new NoPerecedero("Papas Radioactivas", 1500, 500, 5);
		Producto producto5 = new PerecederoRefrigeracion("Zucaritas", 300, 150, 0);
		System.out.println(producto1);
		System.out.println(producto2);
		System.out.println(producto3);
		System.out.println(producto4);
		System.out.println(producto5);
		System.out.println(producto1.equals(producto2)); // true
		System.out.println(producto2.equals(producto3)); // false
		System.out.println(producto4.equals(producto3)); // false
		System.out.println(producto5.equals(producto1)); // false
	}
}
