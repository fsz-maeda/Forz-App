package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.EmployeeDAO;
import model.Employee;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
	        HttpServletResponse response)
	        throws ServletException, IOException {

	    request.setCharacterEncoding("UTF-8");

	    String name = request.getParameter("name");
	    String pass = request.getParameter("pass");

	    EmployeeDAO dao = new EmployeeDAO();
	    Employee employee = dao.findByNameAndPass(name, pass);
	    Employee user = dao.login(name, pass);

	    if(employee != null) {

	        HttpSession session = request.getSession();

	        session.setAttribute("loginUser", employee);

	        response.sendRedirect("MyProfileServlet");

	    } else {

	        request.setAttribute("errorMsg",
	                "ユーザー名またはパスワードが違います");

	        RequestDispatcher dispatcher =
	                request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");

	        dispatcher.forward(request, response);
	    }
	
	}
}
