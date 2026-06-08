package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.DailyReportCommentDAO;
import model.Employee;

/**
 * Servlet implementation class DailyReportCommentServlet
 */
@WebServlet("/DailyReportCommentServlet")
public class DailyReportCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

//		ログイン確認
		HttpSession session = request.getSession();
		Employee loginUser = (Employee)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			response.sendRedirect("Home");
			return;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/dailyReport.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//		ログイン確認
		HttpSession session = request.getSession();
		Employee loginUser = (Employee) session.getAttribute("loginUser");

		if (loginUser == null) {
			response.sendRedirect("Home");
			return;
		}

		String action = request.getParameter("action");
		DailyReportCommentDAO dao = new DailyReportCommentDAO();

		int reportId = Integer.parseInt(request.getParameter("reportId"));

		if ("insert".equals(action)) {

			String comment = request.getParameter("comment");

			if (comment == null || comment.trim().isEmpty()) {
				session.setAttribute("dailyReportCommentErrorMsg", "コメントを入力してください。");
				response.sendRedirect("dailyReportPage");
				return;
			}

			dao.insertComment(loginUser.getEmployeeId(), reportId, comment);

		} else if ("delete".equals(action)) {

			int commentId = Integer.parseInt(request.getParameter("commentId"));

			dao.deleteComment(commentId, loginUser.getEmployeeId());
		}

		response.sendRedirect("dailyReportPage");
	}

}
