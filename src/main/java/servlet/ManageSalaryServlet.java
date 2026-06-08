package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.SalaryDAO;
import model.Employee;
import model.Salary;

@WebServlet("/manageSalary")
public class ManageSalaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Employee employee = (Employee)session.getAttribute("loginUser");
		
		if(employee == null) {
			response.sendRedirect("home");
			return;
		}
		
		SalaryDAO dao = new SalaryDAO();
		List<Salary> salaryList = dao.findAll();
		
		request.setAttribute("salaryList", salaryList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/manageSalary.jsp");
		dispatcher.forward(request, response);
	}

}
