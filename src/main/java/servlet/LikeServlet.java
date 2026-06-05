package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.LikeDAO;
import model.User;

@WebServlet("/like")
public class LikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		int eventId = Integer.parseInt(
				request.getParameter("eventId"));

		HttpSession session = request.getSession();

		User loginUser =
				(User) session.getAttribute("loginUser");

		int userId = loginUser.getUserId();

		LikeDAO likeDAO = new LikeDAO();

		if (likeDAO.exists(userId, eventId)) {

			likeDAO.delete(userId, eventId);
	} else {
		likeDAO.insert(userId, eventId);
	}
		response.sendRedirect("event");
	}
}