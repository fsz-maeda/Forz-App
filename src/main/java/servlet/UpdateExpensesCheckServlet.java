package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import service.ExpensesService;

@WebServlet("/updateExpensesCheck")
public class UpdateExpensesCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idParam = request.getParameter("expensesId");
		String approval = request.getParameter("approval");

		if (idParam == null || idParam.isEmpty() || approval == null) {
			response.sendRedirect("manageExpenses");
			return;
		}

		int expensesId;

		try {
			expensesId = Integer.parseInt(idParam);
		} catch (NumberFormatException e) {
			response.sendRedirect("manageExpenses");
			return;
		}

		ExpensesService service = new ExpensesService();

		boolean result = service.updateApproval(expensesId, approval);

		if (result) {
			request.getSession().setAttribute("updateExpensesMsg", "審査結果を更新しました");
		} else {
			request.getSession().setAttribute("updateExpensesMsg", "更新に失敗しました");
		}

		response.sendRedirect("manageExpenses");
	}
}