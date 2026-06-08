package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.EmployeeDAO;
import model.Employee;

@WebServlet("/EmployeeListServlet")
public class EmployeeListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        EmployeeDAO dao = new EmployeeDAO();

        String keyword = request.getParameter("keyword");

        List<Employee> employeeList;

        if(keyword == null || keyword.trim().equals("")) {

            employeeList = dao.findAll();

        } else {

            employeeList = dao.search(keyword);
        }

        request.setAttribute("employeeList", employeeList);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(
                "/WEB-INF/jsp/employeeList.jsp");

        dispatcher.forward(request, response);
    }
}