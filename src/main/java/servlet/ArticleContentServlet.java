package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.MediaDAO;
import model.Media;

/**
 * Servlet implementation class MediaArticleServlet
 */
@WebServlet("/ArticleContentServlet")
public class ArticleContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ID = request.getParameter("id");
		int id =Integer.parseInt(ID);
		
		MediaDAO dao = new MediaDAO();
		Media media = dao.articleFind(id);
		
		 HttpSession session = request.getSession();
		 session.setAttribute("media",media);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/articleContent.jsp");
		dispatcher.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
