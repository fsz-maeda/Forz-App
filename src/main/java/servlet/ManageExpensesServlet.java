package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.EmployeeDAO;
import model.Employee;
import model.Expenses;
import service.ExpensesService;

@WebServlet("/manageExpenses")
public class ManageExpensesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ExpensesService service = new ExpensesService();

		List<Expenses> approvedList = service.getApprovedExpenses();
		List<Expenses> unapprovedList = service.getUnapprovedExpenses();
		
		EmployeeDAO employeeDAO = new EmployeeDAO();
		List<Employee> employeeList = employeeDAO.findAll();

		request.setAttribute("approvedList", approvedList);
		request.setAttribute("unapprovedList", unapprovedList);
		request.setAttribute("employeeList", employeeList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/manageExpenses.jsp");

		dispatcher.forward(request, response);
	}
}