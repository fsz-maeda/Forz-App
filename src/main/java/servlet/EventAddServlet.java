package servlet;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.EventDAO;
import model.Event;

@WebServlet("/eventAdd")
public class EventAddServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/EventAdd.jsp");
		dispatcher.forward(request, response);

	}
	
	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String area = request.getParameter("area");
		String eventDate = request.getParameter("eventDate");

		Event event = new Event();

		event.setUserId(1); // 仮
		event.setTitle(title);
		event.setContent(content);
		event.setArea(area);
		event.setEventDate(Date.valueOf(eventDate));

		EventDAO dao = new EventDAO();

		dao.insert(event);

		response.sendRedirect("event");

	}	
}