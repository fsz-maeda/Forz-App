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
		
		int expensesId = Integer.parseInt(request.getParameter("expensesId"));
		
		ExpensesDAO dao = new ExpensesDAO();
		Expenses expenses = dao.findByExpensesId(expensesId);
		
		request.setAttribute("expenses", expenses);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/updateExpenses.jsp");
		dispatcher.forward(request, response);
	}

}
