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
import model.PaidHoliday;
import service.PaidHolidayService;

@WebServlet("/managePaidHoliday")
public class ManagePaidHolidayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PaidHolidayService service = new PaidHolidayService();

		List<PaidHoliday> paidHolidayList = service.getAllPaidHoliday();
		
		EmployeeDAO employeeDAO = new EmployeeDAO();
		List<Employee> employeeList = employeeDAO.findAll();

		request.setAttribute("paidHolidayList", paidHolidayList);
		request.setAttribute("employeeList", employeeList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/managePaidHoliday.jsp");

		dispatcher.forward(request, response);
	}
}