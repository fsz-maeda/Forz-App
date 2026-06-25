package servlet;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Employee;
import service.PaidHolidayService;

@WebServlet("/insertPaidHolidayCheck")
public class InsertPaidHolidayCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// =========================
		// ① ログインチェック
		// =========================
		HttpSession session = request.getSession();
		Employee loginUser = (Employee) session.getAttribute("loginUser");

		if (loginUser == null) {
			response.sendRedirect("Home");
			return;
		}

		// =========================
		// ② パラメータ取得
		// =========================
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		Date startDate = Date.valueOf(request.getParameter("startDate"));
		Date finishDate = Date.valueOf(request.getParameter("finishDate"));
		String holidayType = request.getParameter("holidayType");

		// =========================
		// ③ Serviceに全部委譲
		// =========================
		PaidHolidayService service = new PaidHolidayService();

		String resultMessage = service.applyPaidHoliday(
				employeeId,
				startDate,
				finishDate,
				holidayType
		);

		// =========================
		// ④ メッセージ格納
		// =========================
		session.setAttribute("insertPaidHolidayMsg", resultMessage);

		// =========================
		// ⑤ 画面遷移
		// =========================
		response.sendRedirect("insertPaidHoliday");
	}
}