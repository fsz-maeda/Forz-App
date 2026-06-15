package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.ExpensesDAO;
import model.Employee;
import model.Expenses;

@WebServlet("/manageExpenses")
public class ManageExpensesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		//ログインチェック
		HttpSession session = request.getSession();
		Employee employee = (Employee) session.getAttribute("loginUser");

		if (!((employee != null && employee.getManagement() == true) || employee.getEmployeeId() == 1)) {
			response.sendRedirect("Home");
			return;
		}

		//経費テーブルをすべて取得
		ExpensesDAO dao = new ExpensesDAO();
		List<Expenses> expensesList = dao.findAll();

		List<Expenses> approvaledList = new ArrayList<>();
		List<Expenses> unapprovaledList = new ArrayList<>();

		if (expensesList != null) {
			for (Expenses expenses : expensesList) {
				if (expenses.getApproval() != null) {
					//承認済みの経費リスト
					approvaledList.add(expenses);
				} else {
					//未承認の経費リスト
					unapprovaledList.add(expenses);
				}
			}
		}

		//各リストをリクエストスコープに保存
		request.setAttribute("approvaledList", approvaledList);
		request.setAttribute("unapprovaledList", unapprovaledList);

		//manageExpenses.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/manageExpenses.jsp");
		dispatcher.forward(request, response);
	}

}
