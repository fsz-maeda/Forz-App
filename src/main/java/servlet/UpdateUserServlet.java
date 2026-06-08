package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.EmployeeDAO;

@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		String positionName = request.getParameter("positionName");
		
		EmployeeDAO dao = new EmployeeDAO();
		boolean result = dao.updateUserPosition(positionName, userId);
		
		if(result) {
		    request.getSession().setAttribute("updateUserMsg", "更新成功");
		} else {
		    request.getSession().setAttribute("updateUserMsg", "更新失敗");
		}

		response.sendRedirect("manageUser");
	}

}
