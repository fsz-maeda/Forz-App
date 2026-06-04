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

import model.Media;
import model.MediaLogic;

/**
 * Servlet implementation class MediaServlet
 */
@WebServlet("/media")
public class MediaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        
        MediaLogic ml = new MediaLogic();
        List<Media> mediaList = ml.execute();
        session.setAttribute("mediaList",mediaList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/media.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
