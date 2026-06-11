package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.EmployeeDAO;
import model.Employee;
import model.EmployeePosition;

@WebServlet("/manageUser")
public class ManageUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//ログインチェック
		HttpSession session = request.getSession();
		Employee employee = (Employee)session.getAttribute("loginUser");
		
		if(!((employee != null && employee.getManagement() == true) || employee.getEmployeeId() == 1)){
			response.sendRedirect("Home");
			return;
		}
		
		//従業員テーブルをすべて取得
		EmployeeDAO dao = new EmployeeDAO();
		List<EmployeePosition> employeePositionList = dao.findPositionName();
		
		//セッションスコープに保存
		session.setAttribute("employeePositionList", employeePositionList);
		
		//manageUser.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/manageUser.jsp");
		dispatcher.forward(request, response);
	}

}
