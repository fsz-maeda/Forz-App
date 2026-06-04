package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.SalaryDAO;

@WebServlet("/deleteSalary")
public class DeleteSalaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int salaryId = Integer.parseInt(request.getParameter("salaryId"));
		
		SalaryDAO dao = new SalaryDAO();
		boolean result = dao.deleteSalary(salaryId);
		
		if(result) {
			request.getSession().setAttribute("deleteSalaryMsg", "削除成功");
		}else {
			request.getSession().setAttribute("deleteSalaryMsg", "削除失敗");
		}
		
		response.sendRedirect("manageSalary");
	}

}
