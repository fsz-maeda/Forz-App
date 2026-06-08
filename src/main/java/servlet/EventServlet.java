package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.CommentDAO;
import dao.EventDAO;
import dao.LikeDAO;
import model.Employee;
import model.Event;

@WebServlet("/event")
public class EventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		EventDAO dao = new EventDAO();
		String pageStr = request.getParameter("page");

		int page = 1;

		if (pageStr != null) {
			page = Integer.parseInt(pageStr);
		}
		int pageSize = 5;
		int offset = (page - 1) * pageSize;
		List<Event> eventList = dao.findPage(offset, pageSize);
		int totalCount = dao.countAll();
		int totalPages = (int) Math.ceil(
				(double) totalCount
						/ pageSize);
		HttpSession session = request.getSession();

		Employee loginUser = (Employee) session.getAttribute("loginUser");

		CommentDAO commentDAO = new CommentDAO();
		LikeDAO likeDAO = new LikeDAO();

		for (Event event : eventList) {

			event.setCommentList(
					commentDAO.findByEventId(event.getEventId()));

			event.setLikes(
					likeDAO.countByEventId(event.getEventId()));
			if (loginUser != null) {

				event.setLiked(
						likeDAO.exists(
							loginUser.getEmployeeId(),
								event.getEventId()));
			}
		}
		request.setAttribute("eventList", eventList);
		request.setAttribute(
				"currentPage",
				page);

		request.setAttribute(
				"totalPages",
				totalPages);
		RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF/jsp/Event.jsp");

		dispatcher.forward(request, response);
	}
}
