package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.PaidHolidayDAO;

@WebServlet("/approvePaidHolidayCheck")
public class ApprovePaidHolidayCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int paidHolidayId = Integer.parseInt(request.getParameter("paidHolidayId"));
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		String status = request.getParameter("status");
		
		PaidHolidayDAO dao = new PaidHolidayDAO();
		boolean result = dao.approvePaidHoliday(paidHolidayId, status);
		
		if(result) {
			request.getSession().setAttribute("approvePaidHolidayMsg", "登録しました");
		}else {
			request.getSession().setAttribute("approvePaidHolidayMsg", "登録失敗しました");
		}
		
		response.sendRedirect("managePaidHoliday");
	}

}
