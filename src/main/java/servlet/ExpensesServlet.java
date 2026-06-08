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

import dao.ExpensesDAO;
import model.Employee;
import model.Expenses;

@WebServlet("/expenses")
public class ExpensesServlet extends HttpServlet {
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
		
		ExpensesDAO dao = new ExpensesDAO();
		List<Expenses> expensesList = dao.expensesOK(employee.getEmployeeId(), "承認");
		
		request.setAttribute("expensesList", expensesList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/expenses.jsp");
		dispatcher.forward(request, response);
	}

}
