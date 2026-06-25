package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import service.PaidHolidayService;

@WebServlet("/approvePaidHolidayCheck")
public class ApprovePaidHolidayCheckServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		String idParam = request.getParameter("paidHolidayId");
		String status = request.getParameter("status");

		if (idParam == null || status == null) {
			session.setAttribute("approvePaidHolidayMsg", "入力エラー");
			response.sendRedirect("managePaidHoliday");
			return;
		}

		int paidHolidayId;

		try {
			paidHolidayId = Integer.parseInt(idParam);
		} catch (NumberFormatException e) {
			session.setAttribute("approvePaidHolidayMsg", "ID不正");
			response.sendRedirect("managePaidHoliday");
			return;
		}

		PaidHolidayService service = new PaidHolidayService();
		boolean result = service.approvePaidHoliday(paidHolidayId, status);

		session.setAttribute("approvePaidHolidayMsg", result ? "登録しました" : "登録失敗しました");

		response.sendRedirect("managePaidHoliday");
	}
}