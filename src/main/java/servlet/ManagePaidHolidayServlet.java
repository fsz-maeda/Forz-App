package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

		request.setAttribute("paidHolidayList", paidHolidayList);

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/managePaidHoliday.jsp");

		dispatcher.forward(request, response);
	}
}