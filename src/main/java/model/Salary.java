package model;

import java.io.Serializable;

public class Salary implements Serializable{
	private int salaryId;
	private int employeeId;
	private int amount;
	private int month;
	
	public Salary(int salaryId, int employeeId, int amount, int month) {
		this.salaryId = salaryId;
		this.employeeId = employeeId;
		this.amount = amount;
		this.month = month;
	}

	public int getSalaryId() {
		return salaryId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public int getAmount() {
		return amount;
	}

	public int getMonth() {
		return month;
	}
	
}
