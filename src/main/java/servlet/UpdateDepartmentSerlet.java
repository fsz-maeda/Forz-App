package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.DepartmentDAO;
import model.Department;

@WebServlet("/updateDepartment")
public class UpdateDepartmentSerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int departmentId = Integer.parseInt(request.getParameter("departmentId"));
		
		DepartmentDAO dao = new DepartmentDAO();
		Department department = dao.findByDepartmentId(departmentId);
		
		request.setAttribute("department", department);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/updateDepartment.jsp");
		dispatcher.forward(request, response);
	}

}
