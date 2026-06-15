package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.DepartmentDAO;
import dao.EmployeeDAO;
import dao.PositionDAO;
import model.Department;
import model.Employee;
import model.Position;



@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int employeeId =Integer.parseInt(request.getParameter("employeeId"));
		
		EmployeeDAO dao = new EmployeeDAO();
		Employee employee = dao.findById(employeeId);
		
		PositionDAO positionDAO = new PositionDAO();
        List<Position> positionList = positionDAO.findAll();
        
        DepartmentDAO departmentDAO = new DepartmentDAO();
        List<Department> departmentList = departmentDAO.findAll();
        
		request.setAttribute("employee", employee);
		request.setAttribute("positionList", positionList);
        request.setAttribute("departmentList", departmentList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp");
		dispatcher.forward(request, response);
	}

}
