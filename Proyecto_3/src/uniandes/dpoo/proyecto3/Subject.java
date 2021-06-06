package uniandes.dpoo.proyecto3;

import java.util.LinkedList;
import java.io.*;

public class Subject {

	private String name;
	private float credits;
	private int period;
	private String description;
	private String subjectID;
	private LinkedList<String> majorRestrictions;
	private LinkedList<Subject> preRequirements;
	private LinkedList<Subject> coRequirements;

	public Subject(String name, float credits, int period, String description, String subjectID) {
		this.name = name;
		this.credits = credits;
		this.period = period;
		this.description = description;
		this.subjectID = subjectID;
	}

	public void addPreRequirement(Subject theSubject) {
		this.preRequirements.add(theSubject);
	}

	public void addCoRequirement(Subject theSubject) {
		this.coRequirements.add(theSubject);
	}

	public void addMajorRestriction(String theMajor) {
		this.majorRestrictions.add(theMajor);
	}

	public boolean removePreRequirement(Subject theSubject) {
		return this.preRequirements.remove(theSubject);
	}

	public boolean removeCoRequirement(Subject theSubject) {
		return this.coRequirements.remove(theSubject);
	}

	public void setName(String theName) {
		this.name = theName;
	}

	public void setCredits(float theCredits) {
		this.credits = theCredits;
	}

	public void setPeriod(int thePeriod) {
		this.period = thePeriod;
	}

	public void setSubjectID(String theSubjectID) {
		this.subjectID = theSubjectID;
	}

	public void setDescription(String theDescription) {
		this.description = theDescription;
	}

	public String getName() {
		return this.name;
	}

	public float getCredits() {
		return this.credits;
	}

	public int getPeriod() {
		return this.period;
	}

	public String getDescription() {
		return this.description;
	}

	public String getSubjectID() {
		return this.subjectID;
	}

	public LinkedList<Subject> getPreRequirements() {
		return this.preRequirements;
	}

	public boolean getPreRequirement(Subject theRequirement) {
		for (Subject sbj : this.preRequirements) {
			if (sbj.equals(theRequirement)) {
				return true;
			}
		}
		return false;
	}

	public LinkedList<Subject> getCoRequirements() {
		return this.coRequirements;
	}

	public boolean getCoRequirement(Subject theRequirement) {
		for (Subject sbj : this.coRequirements) {
			if (sbj.equals(theRequirement)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasRestriction(String majorName) {
		for (String restr : this.majorRestrictions) {
			if (restr.equals(majorName)) {
				return true;
			}
		}
		return false;
	}

	public void saveSubject(String filename) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("./data/subjects/" + filename + ".txt"));
			bw.write(this.subjectID);
			bw.close();
		} catch (Exception exc) {
			System.err.println("Error creating the file");
			Logger.writeLog("Error creating the file");
		}
	}

	@Override
	public int hashCode() {
		return this.subjectID.hashCode();
	}

	@Override
	public String toString() {
		return this.subjectID + ": " + this.name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (obj.getClass() != this.getClass()) {
			return false;
		}

		final Subject sbj = (Subject) obj;
		return this.subjectID.equals(sbj.subjectID);
	}

}
