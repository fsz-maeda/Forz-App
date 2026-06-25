package servlet;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import service.PaidHolidayService;

@WebServlet("/updatePaidHolidayCheck")
public class UpdatePaidHolidayCheckServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loginUser") == null) {
			response.sendRedirect("Home");
			return;
		}

		int paidHolidayId = Integer.parseInt(request.getParameter("paidHolidayId"));
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		Date startDate = Date.valueOf(request.getParameter("startDate"));
		Date finishDate = Date.valueOf(request.getParameter("finishDate"));
		String holidayType = request.getParameter("holidayType");

		PaidHolidayService service = new PaidHolidayService();

		String msg = service.updatePaidHoliday(
				paidHolidayId,
				employeeId,
				startDate,
				finishDate,
				holidayType
		);

		session.setAttribute("updatePaidHolidayMsg", msg);

		response.sendRedirect("insertPaidHoliday");
	}
}