package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.SalaryDAO;

@WebServlet("/insertSalaryCheck")
public class InsertSalaryCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		int month = Integer.parseInt(request.getParameter("month"));
		
		SalaryDAO dao = new SalaryDAO();
		boolean result = dao.insertSalary(userId, amount, month);
		
		if(result) {
			request.getSession().setAttribute("insertSalaryMsg", "入力完了");
		}else {
			request.getSession().setAttribute("insertSalaryMsg", "入力失敗");
		}
		
		response.sendRedirect("manageSalary");
	}

}
