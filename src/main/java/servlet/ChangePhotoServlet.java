package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ChangePhotoServlet
 */
@WebServlet("/ChangePhotoServlet")
public class ChangePhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		 HttpSession session = request.getSession(false);

	        if(session == null || session.getAttribute("loginUser") == null){
	            response.sendRedirect("login");
	            return;
	        }

	        request.getRequestDispatcher("/WEB-INF/jsp/changePhoto.jsp")
	               .forward(request, response);
	    }
	
		
	}


