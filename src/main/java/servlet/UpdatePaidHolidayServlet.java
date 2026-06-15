package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.PaidHolidayDAO;
import model.PaidHoliday;

@WebServlet("/updatePaidHoliday")
public class UpdatePaidHolidayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int paidHolidayId = Integer.parseInt(request.getParameter("paidHolidayId"));
		
		PaidHolidayDAO dao = new PaidHolidayDAO();
		PaidHoliday paidHoliday = dao.findByPaidHolidayId(paidHolidayId);
		
		request.setAttribute("paidHoliday", paidHoliday);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/updatePaidHoliday.jsp");
		dispatcher.forward(request, response);
	}

}
