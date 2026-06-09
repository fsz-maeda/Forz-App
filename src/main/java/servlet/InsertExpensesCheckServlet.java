package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.ExpensesDAO;
import model.Employee;

@WebServlet("/insertExpensesCheck")
public class InsertExpensesCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int amount = Integer.parseInt(request.getParameter("amount"));
		String detail = request.getParameter("detail");
		
		HttpSession session = request.getSession();
		Employee employee = (Employee)session.getAttribute("loginUser");
		
		ExpensesDAO dao = new ExpensesDAO();
		boolean result = dao.insertExpenses(employee.getEmployeeId(), amount, detail);
		
		if(result) {
			request.getSession().setAttribute("insertExpensesMsg", "申請完了");
		}else {
			request.getSession().setAttribute("insertExpensesMsg", "申請失敗");
		}
		
		response.sendRedirect("insertExpenses");
	}

}
