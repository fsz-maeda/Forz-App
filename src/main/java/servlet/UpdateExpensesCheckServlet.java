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

@WebServlet("/updateExpensesCheck")
public class UpdateExpensesCheckServlet extends HttpServlet {
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
		String approval = request.getParameter("approval");
		
		//指定したデータで更新
		ExpensesDAO dao = new ExpensesDAO();
		boolean result = dao.updateExpenses(expensesId, approval);
		
		//実行結果
		if(result) {
			request.getSession().setAttribute("updateExpensesMsg", "承認しました");
		}else {
			request.getSession().setAttribute("updateExpensesMsg", "認証に失敗しました");
		}
		
		//manageExpensesにリダイレクト
		response.sendRedirect("manageExpenses");
	}

}
