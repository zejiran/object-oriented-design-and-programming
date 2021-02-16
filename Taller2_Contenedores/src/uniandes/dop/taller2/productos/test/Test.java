package uniandes.dop.taller2.productos.test;

import uniandes.dop.taller2.productos.modelo.Producto;
import uniandes.dop.taller2.productos.modelo.NoPerecedero;
import uniandes.dop.taller2.productos.modelo.PerecederoRefrigeracion;
import uniandes.dop.taller2.productos.modelo.PerecederoNoRefrigeracion;


public class Test {
	
	public static void main(String[] args) {
		Producto producto1 = new PerecederoRefrigeracion("Aceitunas", 300, 150, 15);
		Producto producto2 = new PerecederoRefrigeracion("Zucaritas", 300, 150, 15);
		System.out.println(producto1.equals(producto2));
	}

}
