package model;

import java.io.Serializable;

public class Salary implements Serializable{
	private int salaryId;
	private int userId;
	private int amount;
	private int month;
	
	public Salary(int salaryId, int userId, int amount, int month) {
		this.salaryId = salaryId;
		this.userId = userId;
		this.amount = amount;
		this.month = month;
	}

	public int getSalaryId() {
		return salaryId;
	}

	public int getUserId() {
		return userId;
	}

	public int getAmount() {
		return amount;
	}

	public int getMonth() {
		return month;
	}
	
}
