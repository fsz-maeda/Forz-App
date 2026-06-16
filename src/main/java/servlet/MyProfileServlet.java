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
import model.EmployeePosition;

@WebServlet("/MyProfileServlet")
public class MyProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if(session == null ||
           session.getAttribute("loginUser") == null) {

            response.sendRedirect("login");
            return;
        }
        
       Employee loginUser = (Employee) session.getAttribute("loginUser");
       request.setAttribute("employee",loginUser);
       
       EmployeeDAO dao = new EmployeeDAO();
       EmployeePosition employeePosition = dao.showProfile(loginUser.getEmployeeId());
       
       request.setAttribute("employeePosition", employeePosition);

		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/MyProfile.jsp");
		dispatcher.forward(request,response);
	}
}