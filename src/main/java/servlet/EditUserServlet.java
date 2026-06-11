package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.DepartmentDAO;
import dao.EmployeeDAO;
import dao.PositionDAO;
import model.Department;
import model.Employee;
import model.Position;

@WebServlet("/editUser")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//フォームのデータを取得
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		
		//指定した従業員IDをもつデータを取得
		EmployeeDAO edao = new EmployeeDAO();
		Employee employee = edao.findByUserId(employeeId);
		
		//役職テーブルをすべて取得
		PositionDAO pdao = new PositionDAO();
		List<Position> positionList = pdao.findAll();
		
		//部署テーブルをすべて取得
		DepartmentDAO ddao = new DepartmentDAO();
		List<Department> departmentList = ddao.findAll();
		
		//取得したデータをリクエストスコープに保存
		request.setAttribute("employee", employee);
		request.setAttribute("positionList", positionList);
		request.setAttribute("departmentList", departmentList);
		
		//editUser.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/editUser.jsp");
		dispatcher.forward(request, response);
	}

}
