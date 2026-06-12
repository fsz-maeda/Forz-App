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
		String yearStr = request.getParameter("year");
		String monthStr = request.getParameter("month");

		LocalDate today = LocalDate.now();

		int year = today.getYear();
		int month = today.getMonthValue();

		if (yearStr != null && monthStr != null) {

			year = Integer.parseInt(yearStr);
			month = Integer.parseInt(monthStr);

		}
		int prevYear = year;
		int prevMonth = month - 1;

		if (prevMonth == 0) {
			prevMonth = 12;
			prevYear--;
		}

		int nextYear = year;
		int nextMonth = month + 1;

		if (nextMonth == 13) {
			nextMonth = 1;
			nextYear++;
		}

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
		request.setAttribute("prevYear", prevYear);
		request.setAttribute("prevMonth", prevMonth);
		request.setAttribute("nextYear", nextYear);
		request.setAttribute("nextMonth", nextMonth);
		EventDAO dao = new EventDAO();

		List<Event> eventList = dao.findAll();

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