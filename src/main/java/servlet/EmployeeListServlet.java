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

import dao.DepartmentDAO;
import dao.EmployeeDAO;
import dao.PositionDAO;
import model.Department;
import model.Employee;
import model.Position;

@WebServlet("/EmployeeListServlet")
public class EmployeeListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	HttpSession session = request.getSession();
		Employee employee = (Employee)session.getAttribute("loginUser");
		
		if(employee == null) {
			response.sendRedirect("Home");
			return;
		}
    	
        EmployeeDAO dao = new EmployeeDAO();

        String keyword = request.getParameter("keyword");

        List<Employee> employeeList;

        if(keyword == null || keyword.trim().equals("")) {
            employeeList = dao.findAll();
        } else {
            employeeList = dao.search(keyword);
        }
        
        PositionDAO positionDAO = new PositionDAO();
        List<Position> positionList = positionDAO.findAll();
        
        DepartmentDAO departmentDAO = new DepartmentDAO();
        List<Department> departmentList = departmentDAO.findAll();

        request.setAttribute("employeeList", employeeList);
        request.setAttribute("positionList", positionList);
        request.setAttribute("departmentList", departmentList);
        request.setAttribute("keyword", keyword);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/employeeList.jsp");
        dispatcher.forward(request, response);
    }
}