package uniandes.dpoo.proyecto3;

import java.util.LinkedList;
import java.io.*;

public class Semester {

	public Semester() {
	}

	private int numCredits;
	private int codigoActual = 202011282;
	private LinkedList<Subject> subjects;

	public void addSubject(Subject theSubject) {
		if (new Student(3.5, codigoActual).getPromedio() <= 4.0 && (numCredits + theSubject.getCredits()) >= 20) {
			System.err.println("Error el estudiante ha excedido el número de créditos permitidos");
			Logger.writeLog("Error el estudiante ha excedido el número de créditos permitidos");

			return;
		} else if (new Student(3.5, codigoActual).getPromedio() > 4.0 && (numCredits + theSubject.getCredits()) >= 25) {
			System.err
					.println("Error el estudiante ha excedido el número de créditos permitidos incluso extracreditado");
			Logger.writeLog("Error el estudiante ha excedido el número de créditos permitidos incluso extracreditado");

			return;
		}

		this.subjects.add(theSubject);
		this.numCredits += theSubject.getCredits();
	}

	public void removeSubject(Subject theSubject) {
		this.subjects.remove(theSubject);
	}

	public void setNumCredits(int numCredits) {
		this.numCredits = numCredits;
	}

	public int getNumCredits() {
		return numCredits;
	}

	public void saveSemester(String filename) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("./data/semester/" + filename + ".txt"));
			for (Subject sbj : this.subjects) {
				bw.write(sbj.getSubjectID() + ";");
			}
			bw.close();
		} catch (Exception exc) {
			System.err.println("Error creating the file");
			Logger.writeLog("Error creating the file");
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (obj.getClass() != this.getClass()) {
			return false;
		}

		final Semester smt = (Semester) obj;
		if (this.subjects.size() != smt.subjects.size()) {
			return false;
		}

		for (int i = 0; i < this.subjects.size(); i++) {
			if (!this.subjects.get(i).equals(smt.subjects.get(i))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		int sum = 0;
		int count = 0;
		for (Subject sbj : this.subjects) {
			sum += sbj.hashCode();
			count += 1;
		}
		if (count == 0) {
			return 0;
		}
		return sum / count;
	}

	@Override
	public String toString() {
		String retString = "Semester:[";
		for (Subject sbj : this.subjects) {
			retString += sbj.getSubjectID() + ", ";
		}
		retString += "]";
		return retString;
	}

}
