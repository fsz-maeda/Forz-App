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

		HttpSession session = request.getSession();
		Employee loginUser = (Employee) session.getAttribute("loginUser");

		if (loginUser == null) {
			response.sendRedirect("Home");
			return;
		}

		// =========================
		// 検索キーワード取得
		// =========================
		String keyword = request.getParameter("keyword");

		// =========================
		// ページング
		// =========================
		int page = 1;
		int limit = 10;

		String pageStr = request.getParameter("page");
		if (pageStr != null) {
			page = Integer.parseInt(pageStr);
		}

		int offset = (page - 1) * limit;

		DailyReportDAO dao = new DailyReportDAO();
		DailyReportLikeDAO likeDao = new DailyReportLikeDAO();

		// いいね情報
		Set<Integer> likedSet =
				likeDao.findLikedReportIds(loginUser.getEmployeeId());

		List<DailyReport> list;
		int totalCount;

		// =========================
		// 検索分岐
		// =========================
		if (keyword != null && !keyword.trim().isEmpty()) {

			list = dao.searchReports(keyword, offset, limit);

			// 本当はCOUNT用SQLが必要（簡易版）
			totalCount = list.size();

			request.setAttribute("keyword", keyword);

		} else {

			DailyReportService service = new DailyReportService();

			list = service.getPagedReports(offset, limit, likedSet);

			totalCount = dao.countAllReports();
		}

		// =========================
		// ページ数計算
		// =========================
		int totalPage = (int) Math.ceil((double) totalCount / limit);

		boolean hasNext = page < totalPage;
		boolean hasPrev = page > 1;

		request.setAttribute("currentPage", page);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("hasNext", hasNext);
		request.setAttribute("hasPrev", hasPrev);

		// =========================
		// 共通データ
		// =========================
		request.setAttribute("reportList", list);
		request.setAttribute("loginUser", loginUser);

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/dailyReport.jsp");

		dispatcher.forward(request, response);
	}
}