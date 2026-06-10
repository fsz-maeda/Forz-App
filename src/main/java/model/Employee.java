package model;

import java.io.Serializable;
import java.sql.Date;

public class Employee implements Serializable{
	private int employeeId;
	private String name;
	private String pass;
	private String mail;
	private int department;
	private int position;
	private String photoPath;
	private Date enter;
	private String intro;
	private boolean management;
	
	public Employee(int id, String name, String pass, String mail, int department, int position,
			String photoPath, Date enter, String intro, boolean management) {
		this.employeeId = id;
		this.name = name;
		this.pass = pass;
		this.mail = mail;
		this.department = department;
		this.position = position;
		this.photoPath = photoPath;
		this.enter = enter;
		this.intro = intro;
		this.management = management;
	}

	public String getPass() {
		return pass;
	}

	public String getMail() {
		return mail;
	}

	public Date getEnter() {
		return enter;
	}

	public String getIntro() {
		return intro;
	}

	public boolean getManagement() {
		return management;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public void setIntro(String intro) {
	    this.intro = intro;
	}
	public void setMail(String mail) {
	    this.mail = mail;
	}

	public void setPass(String pass) {
	    this.pass = pass;
	}

	public void setEnter(Date enter) {
	    this.enter = enter;
	}

	public void setManagement(boolean management) {
	    this.management = management;
	}

}
