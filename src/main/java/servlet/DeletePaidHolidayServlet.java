package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.PaidHolidayDAO;

@WebServlet("/deletePaidHoliday")
public class DeletePaidHolidayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int paidHolidayId = Integer.parseInt(request.getParameter("paidHolidayId"));
		
		PaidHolidayDAO dao = new PaidHolidayDAO();
		boolean result = dao.deletePaidHoliday(paidHolidayId);
		
		if(result) {
			request.getSession().setAttribute("deletePaidHolidayMsg", "削除成功");
		}else {
			request.getSession().setAttribute("deletePaidHolidayMsg", "削除失敗");
		}
		
		response.sendRedirect("insertPaidHoliday");
	}

}
