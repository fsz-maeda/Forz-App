package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.CommentDAO;
import model.Employee;

@WebServlet("/CommentDeleteServlet")
public class CommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Employee employee = (Employee)session.getAttribute("loginUser");
		
		if(employee == null) {
			response.sendRedirect("Home");
			return;
		}

		int commentId = Integer.parseInt(request.getParameter("commentId"));
		CommentDAO dao = new CommentDAO();
		dao.delete(commentId);
		response.sendRedirect("event");

	}
}
