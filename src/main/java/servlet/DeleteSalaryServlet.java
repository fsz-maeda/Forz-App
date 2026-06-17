package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.SalaryDAO;
import model.Employee;

@WebServlet("/deleteSalary")
public class DeleteSalaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Employee employee = (Employee)session.getAttribute("loginUser");
		
		if(employee == null) {
			response.sendRedirect("Home");
			return;
		}
		
		//フォームのデータを取得
		int salaryId = Integer.parseInt(request.getParameter("salaryId"));
		
		//指定した給料IDをもつデータを削除
		SalaryDAO dao = new SalaryDAO();
		boolean result = dao.deleteSalary(salaryId);
		
		//実行結果
		if(result) {
			request.getSession().setAttribute("deleteSalaryMsg", "削除成功");
		}else {
			request.getSession().setAttribute("deleteSalaryMsg", "削除失敗");
		}
		
		//manageSalaryにリダイレクト
		response.sendRedirect("manageSalary");
	}

}
