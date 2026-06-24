package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.SalaryDAO;
import model.Salary;

@WebServlet("/deleteSalary")
public class DeleteSalaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		// パラメータ取得
		int salaryId;

		try {
			salaryId = Integer.parseInt(request.getParameter("salaryId"));
			
		} catch (NumberFormatException e) {
			session.setAttribute("deleteSalaryMsg", "不正なデータです");
			response.sendRedirect("manageSalary");
			return;
		}

		SalaryDAO dao = new SalaryDAO();
		Salary salary = dao.findBySalaryId(salaryId);

		// 対象データ存在確認
		if (salary == null) {
			session.setAttribute("deleteSalaryMsg", "対象データが存在しません");
			response.sendRedirect("manageSalary");
			return;
		}

		// 削除実行
		boolean result = dao.deleteSalary(salaryId);

		// 結果メッセージ
		if (result) {
			session.setAttribute("deleteSalaryMsg", "削除成功");
		} else {
			session.setAttribute("deleteSalaryMsg", "削除失敗");
		}

		response.sendRedirect("manageSalary");
	}
}