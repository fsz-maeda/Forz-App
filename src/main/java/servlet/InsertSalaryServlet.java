package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.EmployeeDAO;
import model.UserPosition;

@WebServlet("/insertSalary")
public class InsertSalaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		EmployeeDAO dao = new EmployeeDAO();
		List<UserPosition> userPositionList = dao.findPositionName();
		
		HttpSession session = request.getSession();
		session.setAttribute("userPositionList", userPositionList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/insertSalary.jsp");
		dispatcher.forward(request, response);
	}

}
