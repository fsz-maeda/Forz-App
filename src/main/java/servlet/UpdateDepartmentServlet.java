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
public class UpdateDepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// パラメータ取得
		String departmentIdStr = request.getParameter("departmentId");

		if (departmentIdStr == null || departmentIdStr.isBlank()) {
		    response.sendRedirect("manageDepartment");
		    return;
		}

		int departmentId;

		try {
		    departmentId = Integer.parseInt(departmentIdStr);
		} catch (NumberFormatException e) {
		    response.sendRedirect("manageDepartment");
		    return;
		}

		// 部署取得
		DepartmentDAO dao = new DepartmentDAO();
		Department department = dao.findByDepartmentId(departmentId);

		// 存在しない部署対策
		if (department == null) {
			response.sendRedirect("manageDepartment");
			return;
		}

		// リクエストスコープへ保存
		request.setAttribute("department", department);

		// 修正画面へ遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/updateDepartment.jsp");
		dispatcher.forward(request, response);
	}
}