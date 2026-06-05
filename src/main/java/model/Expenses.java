package model;

import java.io.Serializable;

public class Expenses implements Serializable{
	private int expensesId;
	private int userId;
	private int amount;
	private String detail;
	private String approval;
	
	public Expenses(int expensesId, int userId, int amount, String detail, String approval) {
		this.expensesId = expensesId;
		this.userId = userId;
		this.amount = amount;
		this.detail = detail;
		this.approval = approval;
	}
	
	public Expenses(int amount, String detail) {
		this.amount = amount;
		this.detail = detail;
	}

	public int getExpensesId() {
		return expensesId;
	}

	public int getUserId() {
		return userId;
	}

	public int getAmount() {
		return amount;
	}

	public String getDetail() {
		return detail;
	}

	public String getApproval() {
		return approval;
	}
	
}
