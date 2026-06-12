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

import dao.MediaDAO;
import model.Employee;
import model.Media;

/**
 * Servlet implementation class SearchCategoryServlet
 */
@WebServlet("/SearchCategoryServlet")
public class SearchCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Employee loginUser = (Employee) session.getAttribute("loginUser");
		
		String selectedType = request.getParameter("searchCategory");
		MediaDAO dao = new MediaDAO();
		List<Media> mediaList;

		
		if (selectedType.equals("all")) {
			mediaList = dao.findAll(loginUser.getEmployeeId());
	    } else {
	    	mediaList = dao.findByCategory(loginUser, selectedType);
	    }
		
		session.setAttribute("mediaList", mediaList);
		request.setAttribute("selectedType", selectedType);

	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/media.jsp");
	    dispatcher.forward(request, response);
	

	    



	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
