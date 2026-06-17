package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.PaidHolidayDAO;
import model.Employee;
import model.PaidHoliday;

@WebServlet("/updatePaidHoliday")
public class UpdatePaidHolidayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Employee employee = (Employee)session.getAttribute("loginUser");
		
		if(employee == null) {
			response.sendRedirect("Home");
			return;
		}
		
		int paidHolidayId = Integer.parseInt(request.getParameter("paidHolidayId"));
		
		PaidHolidayDAO dao = new PaidHolidayDAO();
		PaidHoliday paidHoliday = dao.findByPaidHolidayId(paidHolidayId);
		
		request.setAttribute("paidHoliday", paidHoliday);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/updatePaidHoliday.jsp");
		dispatcher.forward(request, response);
	}

}
