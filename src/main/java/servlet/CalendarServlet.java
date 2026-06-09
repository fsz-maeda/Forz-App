package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.EventDAO;
import model.Event;

@WebServlet("/calendar")
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(
			HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		LocalDate today = LocalDate.now();

		int year = today.getYear();

		int month = today.getMonthValue();

		YearMonth ym = YearMonth.of(year, month);

		int lastDay = ym.lengthOfMonth();

		int firstDayOfWeek = ym.atDay(1)
				.getDayOfWeek()
				.getValue();
		request.setAttribute(
				"year",
				year);

		request.setAttribute(
				"month",
				month);

		request.setAttribute(
				"lastDay",
				lastDay);

		request.setAttribute(
				"firstDayOfWeek",
				firstDayOfWeek);
		int startColumn = firstDayOfWeek % 7;

		request.setAttribute(
				"startColumn",
				startColumn);
		EventDAO dao = new EventDAO();

		List<Event> eventList =
				dao.findAll();

		request.setAttribute(
				"eventList",
				eventList);
		RequestDispatcher dispatcher = request.getRequestDispatcher(
				"/WEB-INF/jsp/Calendar.jsp");

		dispatcher.forward(
				request,
				response);
	}
}