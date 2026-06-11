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
import model.Employee;
import service.DailyReportService;


@WebServlet("/dailyReportPage")
public class DailyReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
//		ログイン確認
	    HttpSession session = request.getSession();
	    Employee loginUser = (Employee) session.getAttribute("loginUser");

	    if (loginUser == null) {
	        response.sendRedirect("Home");
	        return;
	    }

	    
	    DailyReportLikeDAO likeDao = new DailyReportLikeDAO();
	    
//	    いいねした日報のID一覧取得
	    Set<Integer> likedSet = likeDao.findLikedReportIds(loginUser.getEmployeeId());

	    DailyReportDAO dao = new DailyReportDAO();
	    
//	    ページネーション１ページ１０件表示
	    int page = 1;
	    int limit = 10;
	    String pageStr = request.getParameter("page");
	    if (pageStr != null) {
	        page = Integer.parseInt(pageStr);
	    }
	    int offset = (page - 1) * limit;
	    
//	    全部の日報の総件数取得で１ページ１０件になるようにしている
	    int totalCount = dao.countAllReports();
	    int totalPage = (int) Math.ceil((double) totalCount / limit);
	    
//	    前後ボタンの制御
	    boolean hasNext = page < totalPage;
	    boolean hasPrev = page > 1;
	    
	    request.setAttribute("currentPage", page);
	    request.setAttribute("totalPage", totalPage);
	    request.setAttribute("hasNext", hasNext);
	    request.setAttribute("hasPrev", hasPrev);
	    
	    
	    DailyReportService service = new DailyReportService();

//	    日報一覧といいね済みフラグ取得
	    List<DailyReport> list = service.getPagedReports(offset, limit, likedSet);

	    request.setAttribute("reportList", list);

	    request.setAttribute("loginUser", loginUser);

	    RequestDispatcher dispatcher =
	        request.getRequestDispatcher("//WEB-INF/jsp/dailyReport.jsp");

	    dispatcher.forward(request, response);
	}

}
