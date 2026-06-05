package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.EmployeeDAO;
import model.Employee;

@WebServlet("/MyProfileServlet")
public class MyProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session =
				request.getSession(false);
		if (session == null ||
			session.getAttribute("employeeId") == null) {

			response.sendRedirect("login.jsp");
			return;
		}

		int employeeId =(Integer) session.getAttribute("employeeId");

		EmployeeDAO dao = new EmployeeDAO();

		Employee employee = dao.findById(employeeId);

		request.setAttribute("employee",employee);

		RequestDispatcher dispatcher = request.getRequestDispatcher(
				"//WEB-INF/jsp/profile.jsp");

		dispatcher.forward(request,response);
	}
}