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

@WebServlet("/insertExpenses")
public class InsertExpensesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Employee employee = (Employee)session.getAttribute("loginUser");
		
		if(employee == null) {
			response.sendRedirect("Home");
			return;
		}
		
		ExpensesDAO dao = new ExpensesDAO();
		List<Expenses> expensesList = dao.findByEmployeeId(employee.getEmployeeId());
		
		request.setAttribute("expensesList", expensesList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/insertExpenses.jsp");
		dispatcher.forward(request, response);
	}

}
