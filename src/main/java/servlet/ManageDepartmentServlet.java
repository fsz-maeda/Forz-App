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

import dao.DepartmentDAO;
import model.Department;
import model.Employee;

@WebServlet("/manageDepartment")
public class ManageDepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//ログインチェック
		HttpSession session = request.getSession();
		Employee employee = (Employee)session.getAttribute("loginUser");
		
		if(employee == null) {
			response.sendRedirect("home");
			return;
		}
		
		//部署テーブルをすべて取得
		DepartmentDAO dao = new DepartmentDAO();
		List<Department> departmentList = dao.findAll();

		//リクエストスコープに保存
		request.setAttribute("departmentList", departmentList);
		
		//manageDepartment.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/manageDepartment.jsp");
		dispatcher.forward(request, response);
	}

}
