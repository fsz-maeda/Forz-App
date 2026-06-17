package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.EmployeeDAO;
import model.Employee;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
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
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		
		//指定した従業員IDをもつデータを削除
		EmployeeDAO dao = new EmployeeDAO();
		boolean result = dao.deleteEmployee(employeeId);
		
		//実行結果
		if(result) {
			request.getSession().setAttribute("deleteUserMsg", "削除成功");
		}else {
			request.getSession().setAttribute("deleteUserMsg", "削除失敗");
		}
		
		//manageUserにリダイレクト
		response.sendRedirect("manageUser");
	}

}
