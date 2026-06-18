package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.EmployeeDAO;
import dao.PaidHolidayDAO;
import model.Employee;
import model.PaidHoliday;

@WebServlet("/approvePaidHoliday")
public class ApprovePaidHolidayServlet extends HttpServlet {
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
		
		//フォームのデータを取得
		int paidHolidayId = Integer.parseInt(request.getParameter("paidHolidayId"));
		String status = request.getParameter("status");
		
		//指定した有給IDで有給を取得
		PaidHolidayDAO dao = new PaidHolidayDAO();
		PaidHoliday holiday = dao.findByPaidHolidayId(paidHolidayId);
		
		// 状態更新（これが必須）
		dao.approvePaidHoliday(paidHolidayId, status);

		// 承認された場合のみ減算
		if ("承認".equals(status)) {
		    EmployeeDAO edao = new EmployeeDAO();
		    edao.decreaseRemainPaidHoliday(
		        holiday.getEmployeeId(),
		        holiday.getUsedDays()
		    );
		}
		
		//リクエストスコープに保存
		request.setAttribute("holiday", holiday);
		
		//approvalPaidHoliday.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/approvePaidHoliday.jsp");
		dispatcher.forward(request, response);
	}

}
