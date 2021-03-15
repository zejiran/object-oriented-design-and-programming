package uniandes.dpoo.proyecto1.subjectPlanner;

import java.util.HashMap;
import java.util.Map.Entry;

public class Pensum {

	private String major;
	private int totalCredits;
	private HashMap<Integer, Semester> pensum;
	
	public void addSemester(int semesterNumber, Semester theSemester) {
		this.pensum.put(semesterNumber, theSemester);
	}
	
	public void removeSemester(int semesterNumber) {
		this.pensum.remove(semesterNumber);
	}
	
	public void setMajor(String major) {
		this.major = major;
	}
	
	public void setTotalCredits(int totalCredits) {
		this.totalCredits = totalCredits;
	}
	
	public String getMajor() {
		return major;
	}
	
	public int getTotalCredits() {
		return totalCredits;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		
		if (obj.getClass() != this.getClass()) {
			return false;
		}
		
		final Pensum pns = (Pensum) obj;
		
		if (this.pensum.equals(pns.pensum)) {
			return true;
		}
		return false;
		
	}

	@Override
	public String toString() {
		String ret_string = "";
		for (Entry<Integer, Semester> entry : this.pensum.entrySet()) {
			ret_string += entry.getKey() + entry.getValue().toString() + "\n";
		}
		return ret_string;
	}
	
	

}
