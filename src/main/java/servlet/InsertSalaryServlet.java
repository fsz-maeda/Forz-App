package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.EmployeeDAO;
import model.EmployeePosition;

@WebServlet("/insertSalary")
public class InsertSalaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// 役職名・部署名付き社員一覧取得
		EmployeeDAO dao = new EmployeeDAO();
		List<EmployeePosition> employeePositionList = dao.findPositionName();

		// リクエストスコープに保存
		request.setAttribute("employeePositionList", employeePositionList);

		// JSPへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/insertSalary.jsp");
		dispatcher.forward(request, response);
	}
}