package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.UserDAO;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		UserDAO dao = new UserDAO();
		boolean result = dao.deleteUser(userId);
		
		if(result) {
			request.getSession().setAttribute("deleteUserMsg", "削除成功");
		}else {
			request.getSession().setAttribute("deleteUserMsg", "削除失敗");
		}
		
		response.sendRedirect("manageUser");
	}

}
