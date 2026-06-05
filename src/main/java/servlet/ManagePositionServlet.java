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

import dao.PositionDAO;
import model.Position;
import model.User;

@WebServlet("/managePosition")
public class ManagePositionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		
		if(user == null) {
			response.sendRedirect("home");
			return;
		}

		PositionDAO dao = new PositionDAO();
		List<Position> positionList = dao.findAll();

		request.setAttribute("positionList", positionList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/managePosition.jsp");
		dispatcher.forward(request, response);
	}

}
