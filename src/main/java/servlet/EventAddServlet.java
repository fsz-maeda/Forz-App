package servlet;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.EventDAO;
import model.Employee;
import model.Event;

@WebServlet("/eventAdd")
public class EventAddServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Employee employee = (Employee)session.getAttribute("loginUser");
		
		if(employee == null) {
			response.sendRedirect("Home");
			return;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/EventAdd.jsp");
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

		HttpSession session = request.getSession();
		Employee loginUser = (Employee) session.getAttribute("loginUser");
		Event event = new Event();

		event.setUserId(loginUser.getEmployeeId());

		event.setTitle(title);
		event.setContent(content);
		event.setArea(area);
		event.setEventDate(Date.valueOf(eventDate));

		EventDAO dao = new EventDAO();

		dao.insert(event);

		response.sendRedirect("event");

	}	
}