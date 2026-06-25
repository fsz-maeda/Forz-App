package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import service.DepartmentService;

@WebServlet("/updateDepartmentCheck")
public class UpdateDepartmentCheckServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private DepartmentService departmentService = new DepartmentService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String departmentId = request.getParameter("departmentId");
        String departmentName = request.getParameter("departmentName");

        try {
            departmentService.updateDepartment(departmentId, departmentName);
            request.getSession().setAttribute("updateDepartmentMsg", "更新完了");

        } catch (IllegalArgumentException e) {
            request.getSession().setAttribute("updateDepartmentMsg", e.getMessage());

        }

        response.sendRedirect("manageDepartment");
    }
}