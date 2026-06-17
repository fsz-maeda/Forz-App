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
import service.DeleteDepartmentService;

@WebServlet("/deleteDepartment")
public class DeleteDepartmentServlet extends HttpServlet {
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
		int departmentId = Integer.parseInt(request.getParameter("departmentId"));
		
		//指定した部署IDをもつデータが存在するか確認
		DeleteDepartmentService check = new DeleteDepartmentService();
		boolean checkResult = check.deleteDepartment(departmentId);
		
		//確認結果
		if(!checkResult) {
			request.getSession().setAttribute("deleteDepartmentMsg", "対象の部署に所属しているユーザーがいるため"
					+ "削除できません");
		}else {
			//指定した部署IDをもつデータを削除
			DepartmentDAO dao = new DepartmentDAO();
			boolean result = dao.deleteDepartmnt(departmentId);
			
			//実行結果
			if(result) {
				request.getSession().setAttribute("deleteDepartmentMsg", "削除成功");
			}else {
				request.getSession().setAttribute("deleteDepartmentMsg", "削除失敗");
			}
		}
		
		//manageDpartmentにリダイレクト
		response.sendRedirect("manageDepartment");
	}

}
