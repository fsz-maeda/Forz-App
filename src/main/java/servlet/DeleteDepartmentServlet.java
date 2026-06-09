package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.DepartmentDAO;
import service.DeleteDepartmentService;

@WebServlet("/deleteDepartment")
public class DeleteDepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int departmentId = Integer.parseInt(request.getParameter("departmentId"));
		
		DeleteDepartmentService check = new DeleteDepartmentService();
		boolean checkResult = check.deleteDepartment(departmentId);
		
		if(!checkResult) {
			request.getSession().setAttribute("deleteDepartmentMsg", "対象の部署に所属しているユーザーがいるため"
					+ "削除できません");
		}else {
			DepartmentDAO dao = new DepartmentDAO();
			boolean result = dao.deleteDepartmnt(departmentId);
			
			if(result) {
				request.getSession().setAttribute("deleteDepartmentMsg", "削除成功");
			}else {
				request.getSession().setAttribute("deleteDepartmentMsg", "削除失敗");
			}
		}
		
		response.sendRedirect("manageDepartment");
	}

}
