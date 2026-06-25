package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Expenses;
import service.ExpensesService;

@WebServlet("/updateExpenses")
public class UpdateExpensesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idParam = request.getParameter("expensesId");

		if (idParam == null || idParam.isEmpty()) {
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
		Expenses expenses = service.getExpensesById(expensesId);

		if (expenses == null) {
			response.sendRedirect("manageExpenses");
			return;
		}

		request.setAttribute("expenses", expenses);

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/updateExpenses.jsp");

		dispatcher.forward(request, response);
	}
}