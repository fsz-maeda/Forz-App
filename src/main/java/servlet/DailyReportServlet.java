package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.DailyReportDAO;
import dao.DailyReportLikeDAO;
import model.DailyReport;
import model.User;


@WebServlet("/dailyReportPage")
public class DailyReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
//		ログイン確認
	    HttpSession session = request.getSession();
	    User loginUser = (User) session.getAttribute("loginUser");

	    if (loginUser == null) {
	        response.sendRedirect("Home");
	        return;
	    }
	    
	    
	    DailyReportLikeDAO likeDao = new DailyReportLikeDAO();
//	    いいね数の取得
	    Set<Integer> likedSet = likeDao.findLikedReportIds(loginUser.getUserId());

	    DailyReportDAO dao = new DailyReportDAO();
	    
	    List<DailyReport> list = dao.findAllWithComments(likedSet);

	    request.setAttribute("reportList", list);
	    request.setAttribute("loginUser", loginUser);

	    RequestDispatcher dispatcher =
	        request.getRequestDispatcher("/WEB-INF/jsp/dailyReport.jsp");

	    dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
