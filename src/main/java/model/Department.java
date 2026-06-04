package model;

import java.io.Serializable;

public class Department implements Serializable{
	private int departmentId;
	private String departmentName;
	
	public Department(int departmentId, String departmentName) {
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}
}
