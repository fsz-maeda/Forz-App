package service;

import java.util.ArrayList;
import java.util.List;

import dao.ExpensesDAO;
import model.Expenses;

public class ExpensesService {

	private final ExpensesDAO dao = new ExpensesDAO();

	// =========================
	// 一覧（承認済）
	// =========================
	public List<Expenses> getApprovedExpenses() {

		List<Expenses> approved = new ArrayList<>();
		List<Expenses> all = dao.findAll();

		if (all == null) return approved;

		for (Expenses e : all) {
			if (e.getApproval() != null) {
				approved.add(e);
			}
		}

		return approved;
	}

	// =========================
	// 一覧（未承認）
	// =========================
	public List<Expenses> getUnapprovedExpenses() {

		List<Expenses> unapproved = new ArrayList<>();
		List<Expenses> all = dao.findAll();

		if (all == null) return unapproved;

		for (Expenses e : all) {
			if (e.getApproval() == null) {
				unapproved.add(e);
			}
		}

		return unapproved;
	}

	// =========================
	// 1件取得
	// =========================
	public Expenses getExpensesById(int expensesId) {
		return dao.findByExpensesId(expensesId);
	}

	// =========================
	// 承認/否決更新
	// =========================
	public boolean updateApproval(int expensesId, String approval) {

		if (approval == null) return false;

		if (!approval.equals("承認") && !approval.equals("否決")) {
			return false;
		}

		return dao.updateExpenses(expensesId, approval);
	}
}