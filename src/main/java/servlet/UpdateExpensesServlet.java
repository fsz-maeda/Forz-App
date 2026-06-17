package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.ExpensesDAO;
import model.Employee;
import model.Expenses;

@WebServlet("/updateExpenses")
public class UpdateExpensesServlet extends HttpServlet {
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
		int expensesId = Integer.parseInt(request.getParameter("expensesId"));
		
		//指定したデータで給料を取得
		ExpensesDAO dao = new ExpensesDAO();
		Expenses expenses = dao.findByExpensesId(expensesId);
		
		//リクエストスコープに保存
		request.setAttribute("expenses", expenses);
		
		//updateExpenses.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/updateExpenses.jsp");
		dispatcher.forward(request, response);
	}

}
