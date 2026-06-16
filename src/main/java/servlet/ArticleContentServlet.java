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
import dao.MediaLikesDAO;
import model.Employee;
import model.Media;
import model.MediaComment;
import model.MediaLikeStatus;

@WebServlet("/ArticleContentServlet")
public class ArticleContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Employee employee = (Employee)session.getAttribute("loginUser");
		
		if(employee == null) {
			response.sendRedirect("Home");
			return;
		}
		
		int id = Integer.parseInt(request.getParameter("id"));

		MediaDAO dao = new MediaDAO();
		Media media = dao.articleFind(id);

		if(media == null) {
		    response.sendRedirect("media");
		    return;
		}

		MediaCommentDAO commentDao = new MediaCommentDAO();
		List<MediaComment> commentlist = commentDao.findComment(media);
		
		MediaLikesDAO likeDao = new MediaLikesDAO();
		MediaLikeStatus status = likeDao.getStatus(employee.getEmployeeId(), media.getId());

		request.setAttribute("status", status);

		request.setAttribute("media", media);
		request.setAttribute("commentlist", commentlist);
		 
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/articleContent.jsp");
		dispatcher.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/articleContent.jsp");
		dispatcher.forward(request, response);
	
	}

}
