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

@WebServlet("/LoginCheck")
public class LoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		
		EmployeeDAO dao = new EmployeeDAO();
		boolean result = dao.login(name, pass);
		
		if(result) {
			Employee loginUser = dao.findByNameAndPass(name, pass);
			
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			
			String csrfToken = java.util.UUID.randomUUID().toString();
			session.setAttribute("csrfToken", csrfToken);
			
			response.sendRedirect("Main");
		}else {
			request.getSession().setAttribute("loginMsg", "ログイン失敗");
			response.sendRedirect("Home");
		}
	}

}
