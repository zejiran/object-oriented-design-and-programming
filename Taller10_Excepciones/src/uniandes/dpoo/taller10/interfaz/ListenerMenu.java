package uniandes.dpoo.taller1.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 * Esta clase implementa un listener (ActionListener) para los eventos
 * relacionados con abrir los archivos de una librería.
 */
public class ListenerMenu implements ActionListener
{

	// ************************************************************************
	// Constantes
	// ************************************************************************

	public static final String ABRIR_LIBROS = "ABRIR_LIBROS";

	// ************************************************************************
	// Atributos
	// ************************************************************************

	private InterfazLibreria ventana;

	// ************************************************************************
	// Constructores
	// ************************************************************************

	public ListenerMenu(InterfazLibreria interfazLibreria)
	{
		ventana = interfazLibreria;
	}

	// ************************************************************************
	// Métodos implementados de la interfaz
	// ************************************************************************

	/**
	 * Este método le pide al usuario el archivo con la información de las
	 * categorías y el archivo con la información de los libros de la librería. Si
	 * todo sale bien con la selección de los archivos, se invoca al método
	 * cargarArchivos de la ventana principal de la aplicación.
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String comando = e.getActionCommand();
		if (ABRIR_LIBROS.equals(comando))
		{
			File archivo_categorias = null;
			JFileChooser fc = new JFileChooser("./data");
			fc.setDialogTitle("Seleccione el archivo con las categorías");
			fc.setFileFilter(new FiltroCSV());
			int resultado = fc.showOpenDialog(ventana);
			if (resultado == JFileChooser.APPROVE_OPTION)
			{
				archivo_categorias = fc.getSelectedFile();

				File archivo_libros = null;
				fc = new JFileChooser("./data");
				fc.setDialogTitle("Seleccione el archivo con los libros");
				fc.setFileFilter(new FiltroCSV());
				resultado = fc.showOpenDialog(ventana);
				if (resultado == JFileChooser.APPROVE_OPTION)
				{
					archivo_libros = fc.getSelectedFile();

					ventana.cargarArchivos(archivo_categorias, archivo_libros);
				}
			}
		}

	}

	// ************************************************************************
	// Clases anidadas
	// ************************************************************************

	private final class FiltroCSV extends FileFilter
	{
		@Override
		public String getDescription()
		{
			return "Archivo CSV";
		}

		@Override
		public boolean accept(File f)
		{
			return f.isDirectory() || f.getName().toLowerCase().endsWith(".csv");
		}
	}

}
