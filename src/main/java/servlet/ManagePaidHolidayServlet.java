package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.PaidHolidayDAO;
import model.PaidHoliday;

@WebServlet("/managePaidHoliday")
public class ManagePaidHolidayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		PaidHolidayDAO dao = new PaidHolidayDAO();
		List<PaidHoliday> paidHolidayList = dao.findAll();
		
		request.setAttribute("paidHolidayList", paidHolidayList);
		
		System.out.println(paidHolidayList.size());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/managePaidHoliday.jsp");
		dispatcher.forward(request, response);
	}

}
