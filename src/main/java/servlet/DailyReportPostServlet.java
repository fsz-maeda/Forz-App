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
		
//		ログイン確認
		HttpSession session = request.getSession();

		User loginUser = (User) session.getAttribute("loginUser");

		if (loginUser == null) {
		    response.sendRedirect("Home");
		    return;
		}
		
		
	    RequestDispatcher dispatcher =
		        request.getRequestDispatcher("//WEB-INF/jsp/dailyReportPost.jsp");

		    dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		ログイン確認
		HttpSession session = request.getSession();

		User loginUser = (User) session.getAttribute("loginUser");

		if (loginUser == null) {
		    response.sendRedirect("Home");
		    return;
		}
		
//		新規投稿
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String action = request.getParameter("action");
		
		DailyReportDAO dao = new DailyReportDAO();
		
//		新規投稿と削除の分岐　投稿ボタン後の処理
		if("post".equals(action)) {
			if(title == null || title.trim().isEmpty() || content == null ||content.trim().isEmpty()) {
				session.setAttribute("dailyReportErrorMsg", "タイトルと内容は必須です");
				
				response.sendRedirect("DailyReportPostServlet");
				return ;
				
			}
		int userId = loginUser.getUserId();
		
//		日報のインサート処理
		boolean result = dao.insertDaylyReport(
		userId,
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

		    int reportId = Integer.parseInt(request.getParameter("reportId"));
		    int loginUserId = loginUser.getUserId();

		    boolean result = dao.dailyReportDelete(loginUserId, reportId);

		    if (!result) {
		        session.setAttribute("deleteErrorMsg", "これはあなたの投稿ではありません");
		    }

		    response.sendRedirect("dailyReportPage");
		    return;
		}
	}
}

