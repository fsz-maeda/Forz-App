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
		
		//フォームのデータを取得
		int employeeId;
		
		try {
		    employeeId = Integer.parseInt(request.getParameter("employeeId"));
		} catch(NumberFormatException e) {
		    response.sendRedirect("manageUser");
		    return;
		}
		
		EmployeeDAO dao = new EmployeeDAO();
		Employee target = dao.findByUserId(employeeId);
		
		HttpSession session = request.getSession();
		Employee employee = (Employee)session.getAttribute("loginUser");

		if(target == null) {
			session.setAttribute("deleteUserMsg", "対象社員が存在しません");
			response.sendRedirect("manageUser");
			return;
		}
		
		if(employee.getEmployeeId() == employeeId) {
			session.setAttribute("deleteUserMsg", "自分自身は削除できません");
			response.sendRedirect("manageUser");
			return;
		}
		
		//指定した従業員IDをもつデータを削除
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
