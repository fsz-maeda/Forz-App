package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.EmployeeDAO;
import dao.SalaryDAO;
import model.Employee;

@WebServlet("/insertSalaryCheck")
public class InsertSalaryCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		// パラメータ取得
		int employeeId;
		int amount;
		int month;

		try {
			employeeId = Integer.parseInt(request.getParameter("employeeId"));
			amount = Integer.parseInt(request.getParameter("amount"));
			month = Integer.parseInt(request.getParameter("month"));
		} catch (NumberFormatException e) {
			session.setAttribute("insertSalaryMsg", "入力値が不正です");
			response.sendRedirect("manageSalary");
			return;
		}

		// 給料チェック
		if (amount <= 0) {
			session.setAttribute("insertSalaryMsg", "給料額は1以上を入力してください");
			response.sendRedirect("manageSalary");
			return;
		}

		// 月チェック
		if (month < 1 || month > 12) {
			session.setAttribute("insertSalaryMsg", "支給月は1～12で入力してください");
			response.sendRedirect("manageSalary");
			return;
		}

		// 社員存在チェック
		EmployeeDAO employeeDAO = new EmployeeDAO();
		Employee target = employeeDAO.findByUserId(employeeId);

		if (target == null) {
			session.setAttribute("insertSalaryMsg", "対象社員が存在しません");
			response.sendRedirect("manageSalary");
			return;
		}

		// 給与登録
		SalaryDAO salaryDAO = new SalaryDAO();
		boolean result = salaryDAO.insertSalary(employeeId, amount, month);

		// 実行結果
		if (result) {
			session.setAttribute("insertSalaryMsg", "入力完了");
		} else {
			session.setAttribute("insertSalaryMsg", "入力失敗");
		}

		// 一覧へ戻る
		response.sendRedirect("manageSalary");
	}
}