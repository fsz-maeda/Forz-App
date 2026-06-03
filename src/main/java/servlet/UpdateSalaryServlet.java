package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.SalaryDAO;
import model.Salary;

@WebServlet("/updateSalary")
public class UpdateSalaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int salaryId = Integer.parseInt(request.getParameter("salaryId"));
		
		SalaryDAO dao = new SalaryDAO();
		Salary salary = dao.findBySalaryId(salaryId);
		
		request.setAttribute("salary", salary);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/updateSalary.jsp");
		dispatcher.forward(request, response);
	}

}
