package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.DepartmentDAO;

@WebServlet("/updateDepartmentCheck")
public class UpdateDepartmentCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//フォームのデータを取得
		int departnmentId = Integer.parseInt(request.getParameter("departmentId"));
		String departmentName = request.getParameter("departmentName");
		
		//指定したデータでテーブルを更新
		DepartmentDAO dao = new DepartmentDAO();
		boolean result = dao.updateDepartment(departnmentId, departmentName);
		
		//実行結果
		if(result) {
			request.getSession().setAttribute("updateDepartmentMsg", "更新完了");
		}else {
			request.getSession().setAttribute("updateDepartmentMsg", "更新失敗");
		}
		
		//manageDepartmentにリダイレクト
		response.sendRedirect("manageDepartment");
	}

}
