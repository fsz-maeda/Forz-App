package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.DepartmentDAO;
import model.Employee;

@WebServlet("/insertDepartmentCheck")
public class InsertDepartmentCheckServlet extends HttpServlet {
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
		String departmentName = request.getParameter("departmentName");
		
		//指定した名前を登録
		DepartmentDAO dao = new DepartmentDAO();
		boolean result = dao.insertDepartment(departmentName);
		
		//実行結果
		if(result) {
			request.getSession().setAttribute("insertDepartmentMsg", "入力完了");
		}else {
			request.getSession().setAttribute("insertDepartmentMsg", "入力失敗");
		}
		
		//manageDepartmentにリダイレクト
		response.sendRedirect("manageDepartment");
	}

}
