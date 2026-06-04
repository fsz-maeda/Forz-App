package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.DepartmentDAO;

@WebServlet("/insertDepartmentCheck")
public class InsertDepartmentCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		String departmentName = request.getParameter("departmentName");
		
		DepartmentDAO dao = new DepartmentDAO();
		boolean result = dao.insertDepartment(departmentName);
		
		if(result) {
			request.getSession().setAttribute("insertDepartmentMsg", "入力完了");
		}else {
			request.getSession().setAttribute("insertDepartmentMsg", "入力失敗");
		}
		
		response.sendRedirect("manageDepartment");
	}

}
