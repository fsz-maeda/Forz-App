package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.SystemSettingDAO;
import model.Employee;

/**
 * Servlet implementation class AdminSettingServlet
 */
@WebServlet("/AdminSettingServlet")
public class AdminSettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		Employee employee = (Employee)session.getAttribute("loginUser");
		
		if(employee == null) {
			response.sendRedirect("Home");
			return;
		}

	    SystemSettingDAO dao = new SystemSettingDAO();
	    int closeDay = dao.getCloseDay();

	    request.setAttribute("closeDay", closeDay);

	    RequestDispatcher dispatcher =
	            request.getRequestDispatcher("/WEB-INF/jsp/AdminSetting.jsp");

	    dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        Employee emp = (Employee) session.getAttribute("loginUser");

        if (emp == null || !emp.getManagement()) {
            response.sendError(403);
            return;
        }

        int closeDay = Integer.parseInt(request.getParameter("closeDay"));

        SystemSettingDAO dao = new SystemSettingDAO();
        dao.updateCloseDay(closeDay);

        response.sendRedirect("AttendanceServlet");
    }
}
