package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.ExpensesDAO;

@WebServlet("/UpdateExpensesCheckServlet")
public class UpdateExpensesCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int expensesId = Integer.parseInt(request.getParameter("expensesId"));
		String approval = request.getParameter("approval");
		
		ExpensesDAO dao = new ExpensesDAO();
		boolean result = dao.updateExpenses(expensesId, approval);
		
		if(result) {
			request.getSession().setAttribute("updateExpensesMsg", "承認しました");
		}else {
			request.getSession().setAttribute("updateExpensesMsg", "認証に失敗しました");
		}
		
		response.sendRedirect("manageExpenses");
	}

}
