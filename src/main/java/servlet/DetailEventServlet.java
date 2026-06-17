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

import dao.CommentDAO;
import dao.EmployeeDAO;
import dao.EventDAO;
import dao.LikeDAO;
import model.Employee;
import model.Event;

@WebServlet("/detailEvent")
public class DetailEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Employee loginUser = (Employee) session.getAttribute("loginUser");

		if (loginUser == null) {
			response.sendRedirect("Home");
			return;
		}
		
		int eventId = Integer.parseInt(request.getParameter("eventId"));
		
		EventDAO eventDAO = new EventDAO();
		Event event = eventDAO.findById(eventId);
		
		EmployeeDAO employeeDAO = new EmployeeDAO();
		List<Employee> employeeList = employeeDAO.findAll();
		
		CommentDAO commentDAO = new CommentDAO();
		LikeDAO likeDAO = new LikeDAO();

		event.setCommentList(commentDAO.findByEventId(eventId));
		event.setLikes(likeDAO.countByEventId(eventId));
			
		if (loginUser != null) {
			event.setLiked(likeDAO.exists(loginUser.getEmployeeId(), event.getEventId()));
		}
		
		request.setAttribute("event", event);
		request.setAttribute("employeeList", employeeList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/detailEvent.jsp");
		dispatcher.forward(request, response);
	}

}
