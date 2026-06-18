package servlet;

import java.io.IOException;

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

@WebServlet("/approvePaidHolidayCheck")
public class ApprovePaidHolidayCheckServlet extends HttpServlet {
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
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		String status = request.getParameter("status");
		
		//有給を承認
		PaidHolidayDAO dao = new PaidHolidayDAO();
		boolean result = dao.approvePaidHoliday(paidHolidayId, status);

		if (result && "承認".equals(status)) {

		    PaidHoliday holiday = dao.findByPaidHolidayId(paidHolidayId);

		    // すでに承認済みならスキップ
		    if (!"承認済み".equals(holiday.getStatus())) {

		        EmployeeDAO edao = new EmployeeDAO();

		        edao.decreaseRemainPaidHoliday(
		            holiday.getEmployeeId(),
		            holiday.getUsedDays()
		        );
		    }
		}
		
		//実行結果
		if(result) {
			request.getSession().setAttribute("approvePaidHolidayMsg", "登録しました");
		}else {
			request.getSession().setAttribute("approvePaidHolidayMsg", "登録失敗しました");
		}
		
		//managePaidHolidayにリダイレクト
		response.sendRedirect("managePaidHoliday");
	}

}
