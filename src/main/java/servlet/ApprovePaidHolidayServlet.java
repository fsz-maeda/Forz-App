package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.PaidHolidayDAO;
import model.PaidHoliday;

@WebServlet("/approvePaidHoliday")
public class ApprovePaidHolidayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//フォームのデータを取得
		int paidHolidayId = Integer.parseInt(request.getParameter("paidHolidayId"));
		
		//指定した有給IDで有給を取得
		PaidHolidayDAO dao = new PaidHolidayDAO();
		PaidHoliday holiday = dao.findByPaidHolidayId(paidHolidayId);
		
		//リクエストスコープに保存
		request.setAttribute("holiday", holiday);
		
		//approvalPaidHoliday.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/approvePaidHoliday.jsp");
		dispatcher.forward(request, response);
	}

}
