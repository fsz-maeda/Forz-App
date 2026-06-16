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

import dao.MediaCommentDAO;
import model.Employee;
import model.Media;
import model.MediaComment;

/**
 * Servlet implementation class MediaCommentServlet
 */
@WebServlet("/MediaCommentServlet")
public class MediaCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Employee employee = (Employee)session.getAttribute("loginUser");
		
		if(employee == null) {
			response.sendRedirect("Home");
			return;
		}

		String esg1 = "";
		String esg2 = "";

		session.setAttribute("esg1", esg1);
		session.setAttribute("esg2", esg2);

		Media media = (Media) session.getAttribute("media");
		MediaCommentDAO dao = new MediaCommentDAO();
		List<MediaComment> commentlist = dao.findComment(media);
		session.setAttribute("commentlist", commentlist);

		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/mediaComment.jsp");

		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String comment = request.getParameter("comment");
		String a = request.getParameter("IID");
		int id = Integer.parseInt(a);
		String esg1 = "";
		String esg2 = "";

		if (comment.length() != 0) {
			HttpSession session = request.getSession();
			Employee loginUser = (Employee) session.getAttribute("loginUser");
			MediaComment mc = new MediaComment(id, comment);
			MediaCommentDAO dao = new MediaCommentDAO();

			dao.postComment(mc, loginUser);
			
			response.sendRedirect("ArticleContentServlet?id=" + id);
			return;
		}

		if (comment.length() == 0) {
			esg2 = "コメントが入力されていません";
		}
		HttpSession session = request.getSession();
		session.setAttribute("esg1", esg1);
		session.setAttribute("esg2", esg2);

		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/mediaComment.jsp");
		dispatcher.forward(request, response);
	}
}
