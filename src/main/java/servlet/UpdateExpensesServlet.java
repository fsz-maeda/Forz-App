package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.ExpensesDAO;
import model.Expenses;

@WebServlet("/updateExpenses")
public class UpdateExpensesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
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
