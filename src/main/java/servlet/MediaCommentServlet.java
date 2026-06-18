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
import dao.MediaDAO;
import model.Employee;
import model.Media;
import model.MediaComment;

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

		int mediaId = Integer.parseInt(request.getParameter("mediaId"));
		
		MediaDAO mediaDao = new MediaDAO();
		Media media = mediaDao.articleFind(mediaId);
		
		MediaCommentDAO dao = new MediaCommentDAO();
		List<MediaComment> commentlist = dao.findComment(mediaId);
		
		session.setAttribute("media", media);
		session.setAttribute("commentlist", commentlist);

		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/mediaComment.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String comment = request.getParameter("comment");
		int id = Integer.parseInt(request.getParameter("mediaId"));
		String esg1 = "";
		String esg2 = "";

		if (comment.length() != 0) {
			HttpSession session = request.getSession();
			Employee loginUser = (Employee) session.getAttribute("loginUser");
			MediaComment mc = new MediaComment(id, comment);
			MediaCommentDAO dao = new MediaCommentDAO();

			dao.postComment(mc, loginUser);
			
			response.sendRedirect("ArticleContentServlet?mediaId=" + id);
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
