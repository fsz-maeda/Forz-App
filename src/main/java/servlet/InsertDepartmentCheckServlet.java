package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import service.DepartmentService;

@WebServlet("/insertDepartmentCheck")
public class InsertDepartmentCheckServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final DepartmentService departmentService = new DepartmentService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String departmentName = request.getParameter("departmentName");

        HttpSession session = request.getSession();

        try {
            departmentService.insertDepartment(departmentName);
            session.setAttribute("insertDepartmentMsg", "登録完了");

        } catch (IllegalArgumentException e) {
            session.setAttribute("insertDepartmentMsg", e.getMessage());

        } catch (Exception e) {
            session.setAttribute("insertDepartmentMsg", "システムエラーが発生しました");
            e.printStackTrace();
        }

        response.sendRedirect("manageDepartment");
    }
}