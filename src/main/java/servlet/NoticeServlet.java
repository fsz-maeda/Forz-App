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

import dao.NoticeDAO;
import model.Employee;
import model.Notice;

@WebServlet("/NoticeServlet")
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Employee employee = (Employee)session.getAttribute("loginUser");
		
		if(employee == null) {
			response.sendRedirect("Home");
			return;
		}
		
		NoticeDAO dao = new NoticeDAO();
		List<Notice> noticeList = dao.findAll();
		
		request.setAttribute("noticeList", noticeList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/notice.jsp");
		dispatcher.forward(request, response);
	}

}
