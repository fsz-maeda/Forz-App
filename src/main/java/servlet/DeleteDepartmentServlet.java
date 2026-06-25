package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import service.DepartmentService;

@WebServlet("/deleteDepartment")
public class DeleteDepartmentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final DepartmentService departmentService = new DepartmentService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String departmentId = request.getParameter("departmentId");

        try {
            departmentService.deleteDepartment(departmentId);
            request.getSession().setAttribute("deleteDepartmentMsg", "削除成功");

        } catch (IllegalArgumentException e) {
            request.getSession().setAttribute("deleteDepartmentMsg", e.getMessage());

        } catch (Exception e) {
            request.getSession().setAttribute("deleteDepartmentMsg", "システムエラーが発生しました");
            e.printStackTrace();
        }

        response.sendRedirect("manageDepartment");
    }
}