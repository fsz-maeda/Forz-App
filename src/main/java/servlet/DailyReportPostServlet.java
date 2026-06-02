package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.DailyReportDAO;
import model.User;

@WebServlet("/DailyReportPostServlet")
public class DailyReportPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    RequestDispatcher dispatcher =
		        request.getRequestDispatcher("/WEB-INF/jsp/dailyReportPost.jsp");

		    dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DailyReportDAO dao = new DailyReportDAO();
		HttpSession session = request.getSession();

		User loginUser = (User) session.getAttribute("loginUser");

		if (loginUser == null) {
		    response.sendRedirect("Home");
		    return;
		}
		
		int userId = loginUser.getUserId();
		
		boolean result = dao.insertDaylyReport(
		userId,
		request.getParameter("title"),
		request.getParameter("dailyType"),
		request.getParameter("content")
		);
		
		if(result) {
			response.sendRedirect("dailyReportPage");
		}else {
			session.setAttribute("dailyReportErrorMsg", "投稿失敗");
			response.sendRedirect("DailyReportPostServlet");
		}
		
		
	}

}
