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


@WebServlet("/AttendanceApproveServlet")
public class AttendanceApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
        Employee emp = (Employee) session.getAttribute("loginUser");

        if (emp == null) {
            response.sendRedirect("Home");
            return;
        }

        if (!emp.getManagement()) {
            response.sendError(403);
            return;
        }

        int employeeId = emp.getEmployeeId();

        int year = Integer.parseInt(request.getParameter("year"));
        int month = Integer.parseInt(request.getParameter("month"));

        AttendanceDAO dao = new AttendanceDAO();
        dao.approveMonth(employeeId, year, month);

        response.sendRedirect("AttendanceServlet?year=" + year + "&month=" + month);
    }
}