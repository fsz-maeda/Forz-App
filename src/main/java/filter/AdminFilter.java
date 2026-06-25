package filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Employee;

@WebFilter({
	"/admin",
	"/manageUser",
	"/editUser",
	"/updateUser",
	"/deleteUser",
	"/manageSalary",
	"/insertSalary",
	"/insertSalaryCheck",
	"/updateSalary",
	"/updateSalaryCheck",
	"/deleteSalary",
	"/managePosition",
    "/insertPosition",
    "/insertPositionCheck",
    "/updatePosition",
    "/updatePositionCheck",
    "/deletePosition",
    "/manageDepartment",
    "/insertDepartment",
    "/insertDepartmentCheck",
    "/updateDepartment",
    "/updateDepartmentCheck",
    "/deleteDepartment",
    "/manageExpenses",
    "/updateExpenses",
    "/updateExpensesCheck",
    "/managePaidHoliday",
    "/approvePaidHoliday",
    "/approvePaidHolidayCheck"
})

public class AdminFilter extends HttpFilter {

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpSession session = request.getSession(false);
		Employee loginUser = null;

		if (session != null) {
			loginUser = (Employee) session.getAttribute("loginUser");
		}

		if (loginUser == null || (!loginUser.getManagement() && loginUser.getEmployeeId() != 1)) {
			response.sendRedirect("Home");
			return;
		}

		chain.doFilter(request, response);
	}
}