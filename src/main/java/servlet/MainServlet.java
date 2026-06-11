package servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.DailyReportDAO;
import dao.EventDAO;
import model.DailyReport;
import model.Event;

@WebServlet("/Main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String[] messages = {
				"焦らず丁寧に進めましょう！",
				"周りへの思いやりを大切にしましょう！",
				"失敗を恐れず挑戦しましょう！",
				"昨日より一歩成長しましょう！",
				"健康に気をつけて頑張りましょう！",
				"お互いに助け合いながら進みましょう！",
				"元気よく一日をスタートしましょう！",
				"今日の出会いを大切にしましょう！",
				"ポジティブな気持ちで取り組みましょう！",
				"未来の自分のために頑張りましょう！",
				"今日も一日頑張りましょう！",
				"安全第一で行きましょう！",
				"笑顔で頑張りましょう！",
				"挑戦を楽しみましょう！",
				"チームワークを大切にしましょう！"
				
			};
		EventDAO eventDao = new EventDAO();

		List<Event> eventList =
		        eventDao.findAll();

		request.setAttribute(
		        "eventList",
		        eventList);


		DailyReportDAO reportDao =
		        new DailyReportDAO();

		Set<Integer> likedSet =
		        new HashSet<>();

		List<DailyReport> reportList =
		        reportDao.findAllWithComments(
		                likedSet);

		request.setAttribute(
		        "reportList",
		        reportList);
			Random random = new Random();
			String message =messages[random.nextInt(messages.length)];
request.setAttribute("motivation", message);

		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
