package service;

import java.util.List;

import model.Expenses;

public class ExpensesListResult {

	private final List<Expenses> approvedList;
	private final List<Expenses> unapprovedList;

	public ExpensesListResult(List<Expenses> approvedList, List<Expenses> unapprovedList) {
		this.approvedList = approvedList;
		this.unapprovedList = unapprovedList;
	}

	public List<Expenses> getApprovedList() {
		return approvedList;
	}

	public List<Expenses> getUnapprovedList() {
		return unapprovedList;
	}
}