package servlet;

import java.io.IOException;
import java.util.ArrayList;
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
import model.Media;
import model.MediaComment;

/**
 * Servlet implementation class MediaArticleServlet
 */
@WebServlet("/ArticleContentServlet")
public class ArticleContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ID = request.getParameter("id");
		int id =Integer.parseInt(ID);
		
		HttpSession session = request.getSession();
		
		
		MediaDAO dao = new MediaDAO();
		Media media = dao.articleFind(id);
		 session.setAttribute("media",media);
		

		 Media m = (Media)session.getAttribute("media");
		 MediaCommentDAO daoo = new MediaCommentDAO();
		 List<MediaComment>commentlist = new ArrayList();
		 commentlist = daoo.findComment(m);
		 session.setAttribute("commentlist",commentlist);
		 
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/articleContent.jsp");

		

		dispatcher.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/articleContent.jsp");
		dispatcher.forward(request, response);
	
	}

}
