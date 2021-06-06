package uniandes.dpoo.proyecto3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
	public static void writeLog(String info) {
	    String filename = "errors.log";
	    String FILENAME = "" + filename;
	    BufferedWriter bw = null;
	    FileWriter fw = null;
	    try {
	        fw = new FileWriter(FILENAME, true);
	        bw = new BufferedWriter(fw);
	        bw.write(info);
	        bw.write("\n");
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (bw != null)
	                bw.close();
	            if (fw != null)
	                fw.close();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }
	}
}
