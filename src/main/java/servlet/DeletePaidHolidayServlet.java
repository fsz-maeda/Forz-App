package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.PaidHolidayDAO;
import model.Employee;

@WebServlet("/deletePaidHoliday")
public class DeletePaidHolidayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Employee employee = (Employee)session.getAttribute("loginUser");
		
		if(employee == null) {
			response.sendRedirect("Home");
			return;
		}
		
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
