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

import dao.UserDAO;
import model.UserPosition;

@WebServlet("/manageUser")
public class ManageUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		UserDAO dao = new UserDAO();
		List<UserPosition> userPositionList = dao.findPositionName();
		
		HttpSession session = request.getSession();
		session.setAttribute("userPositionList", userPositionList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/manageUser.jsp");
		dispatcher.forward(request, response);
	}

}
