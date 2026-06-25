package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.PaidHoliday;
import service.PaidHolidayService;

@WebServlet("/approvePaidHoliday")
public class ApprovePaidHolidayServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idParam = request.getParameter("paidHolidayId");

		if (idParam == null || idParam.isEmpty()) {
			response.sendRedirect("managePaidHoliday");
			return;
		}

		int paidHolidayId;

		try {
			paidHolidayId = Integer.parseInt(idParam);
		} catch (NumberFormatException e) {
			response.sendRedirect("managePaidHoliday");
			return;
		}

		PaidHolidayService service = new PaidHolidayService();
		PaidHoliday holiday = service.getById(paidHolidayId);

		if (holiday == null) {
			response.sendRedirect("managePaidHoliday");
			return;
		}

		request.setAttribute("holiday", holiday);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/approvePaidHoliday.jsp");

		dispatcher.forward(request, response);
	}
}