package servlet;

import java.io.IOException;

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

@WebServlet("/eventEdit")
public class EventEditServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// 編集画面表示
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Employee employee = (Employee)session.getAttribute("loginUser");
		
		if(employee == null) {
			response.sendRedirect("Home");
			return;
		}
		
		int eventId = Integer.parseInt(request.getParameter("eventId"));

		EventDAO dao = new EventDAO();
		Event event = dao.findById(eventId);

		request.setAttribute("event", event);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/EventEdit.jsp");
		dispatcher.forward(request, response);
	}

	// 編集内容保存
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		int eventId = Integer.parseInt(request.getParameter("eventId"));

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String area = request.getParameter("area");
		java.sql.Date eventDate = java.sql.Date.valueOf(request.getParameter("eventDate"));

		Event event = new Event();
		event.setEventId(eventId);
		event.setTitle(title);
		event.setContent(content);
		event.setArea(area);
		event.setEventDate(eventDate);

		EventDAO dao = new EventDAO();
		dao.update(event);

		response.sendRedirect("event");
	}
}