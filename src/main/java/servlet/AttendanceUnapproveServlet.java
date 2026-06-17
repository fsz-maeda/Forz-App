package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.AttendanceDAO;
import model.Employee;

/**
 * Servlet implementation class AttendanceUnapproveServlet
 */
@WebServlet("/AttendanceUnapproveServlet")
public class AttendanceUnapproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Employee emp = (Employee) session.getAttribute("loginUser");

		//	        管理者チェック
		if (emp == null || !emp.getManagement()) {
			response.sendError(403);
			return;
		}

		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));

		AttendanceDAO dao = new AttendanceDAO();
		dao.unapproveMonth(emp.getEmployeeId(), year, month);

		response.sendRedirect("AttendanceServlet?year=" + year + "&month=" + month);
	}
}
