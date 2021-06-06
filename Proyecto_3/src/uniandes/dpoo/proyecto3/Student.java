package uniandes.dpoo.proyecto3;

public class Student {
	private double promedio;
	private int codigo;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public double getPromedio() {
		return promedio;
	}

	public void setPromedio(double promedio) {
		this.promedio = promedio;
	}

	public Student(double promedio, int codigo) {
		this.promedio = promedio;
		this.codigo = codigo;
	}
}
