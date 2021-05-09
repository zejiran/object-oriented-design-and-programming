package uniandes.dpoo.proyecto1.users;

import uniandes.dpoo.proyecto1.subjectPlanner.Semester;

public abstract class User {

	protected String login;
	
	public User() {
	}
	
	protected abstract void saveUser();
	protected abstract String generateGradeReport();
	protected abstract boolean ableToGraudate();
	protected Semester smt;
	
	protected void setLogin(String theLogin) {
		this.login = theLogin;
	}
	
	protected String getLogin(String theLogin) {
		return this.login;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		
		if (obj.getClass() != this.getClass()) {
			return false;
		}
		
		this.smt = (Semester) obj;
		
		return this.login.equals(obj);
	}
}
