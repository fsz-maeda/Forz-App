package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.PaidHolidayDAO;
import model.Employee;
import model.PaidHoliday;

@WebServlet("/managePaidHoliday")
public class ManagePaidHolidayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Employee employee = (Employee)session.getAttribute("loginUser");
		
		if(employee == null) {
			response.sendRedirect("Home");
			return;
		}
		
		//有給テーブルをすべて取得
		PaidHolidayDAO dao = new PaidHolidayDAO();
		List<PaidHoliday> paidHolidayList = dao.findAll();
		
		//リクエストスコープに保存
		request.setAttribute("paidHolidayList", paidHolidayList);

		//managePaidHoliday.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/managePaidHoliday.jsp");
		dispatcher.forward(request, response);
	}

}
