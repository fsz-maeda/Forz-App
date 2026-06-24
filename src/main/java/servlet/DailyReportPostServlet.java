package servlet;

import java.io.IOException;
import java.util.Set;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.DailyReportDAO;
import model.Employee;

@WebServlet("/DailyReportPostServlet")
public class DailyReportPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
//		ログイン確認
		HttpSession session = request.getSession();

		Employee loginUser = (Employee) session.getAttribute("loginUser");

		if (loginUser == null) {
		    response.sendRedirect("Home");
		    return;
		}
		
		
	    RequestDispatcher dispatcher =request.getRequestDispatcher("//WEB-INF/jsp/dailyReportPost.jsp");
	    	dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		ログイン確認
		HttpSession session = request.getSession();

		Employee loginUser = (Employee) session.getAttribute("loginUser");

		if (loginUser == null) {
		    response.sendRedirect("Home");
		    return;
		}
		
		String csrf = request.getParameter("csrf");
		String sessionCsrf = (String) session.getAttribute("csrfToken");
		
		if(sessionCsrf == null || !sessionCsrf.equals(csrf)) {
			response.sendError(403);
			return;
		}
		
//		新規投稿からの取得
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String action = request.getParameter("action");
		
		Set<String> allowed = Set.of("post", "dailyReportDelete");
		if(action == null || !allowed.contains(action)) {
			response.sendError(400);
			return;
		}
		
		
		DailyReportDAO dao = new DailyReportDAO();
		
//		新規投稿と削除の分岐　投稿ボタン後の処理
		if("post".equals(action)) {
			if(title == null || title.trim().isEmpty() || content == null ||content.trim().isEmpty()) {
				session.setAttribute("dailyReportErrorMsg", "タイトルと内容は必須です");
				
				response.sendRedirect("DailyReportPostServlet");
				return ;
				
			}
			int employeeId = loginUser.getEmployeeId();
			
	//		日報の追加処理
			boolean result = dao.insertDailyReport(
			employeeId,
			request.getParameter("dailyType"),
			title,
			content
			);
		
//		成功したら一覧ページへ失敗したらエラー文とともに投稿ページへ戻す
		if(result) {
			response.sendRedirect("dailyReportPage");
		}else {
			session.setAttribute("dailyReportErrorMsg", "投稿失敗");
			response.sendRedirect("DailyReportPostServlet");
			return;
		}
		
//		削除ボタン後の処理
		}else if ("dailyReportDelete".equals(action)) {
			int reportId;

			try {
			    reportId = Integer.parseInt(request.getParameter("reportId"));
			    if (reportId <= 0) throw new NumberFormatException();
			} catch (Exception e) {
			    response.sendError(400);
			    return;
			}

		    int loginUserId = loginUser.getEmployeeId();

		    boolean result = dao.dailyReportDelete(loginUserId, reportId);

		    if (result) {
		        session.setAttribute("deleteMsg", "削除しました");
		    } else {
		        session.setAttribute("deleteErrorMsg", "これはあなたの投稿ではありません");
		    }

		    response.sendRedirect("dailyReportPage");
		    return;
		}
	}
}

