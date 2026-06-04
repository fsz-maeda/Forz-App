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
import model.Event;
import model.User;

@WebServlet("/event")
public class EventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		
		EventDAO dao = new EventDAO();

		List<Event> eventList = dao.findAll();
		HttpSession session = request.getSession();

		User loginUser =
		        (User) session.getAttribute("loginUser");

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
								loginUser.getUserId(),
								event.getEventId()));
			}
		}
		request.setAttribute("eventList", eventList);

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/Event.jsp");

		dispatcher.forward(request, response); 
	}
}
