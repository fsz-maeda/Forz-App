package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.SalaryDAO;
import model.Salary;

@WebServlet("/deleteSalary")
public class DeleteSalary extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		SalaryDAO dao = new SalaryDAO();
		List<Salary> salaryList = dao.findAll();
		
		request.setAttribute("salaryList", salaryList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/deleteSalary.jsp");
		dispatcher.forward(request, response);
	}
}
