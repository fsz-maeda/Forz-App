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
import model.Department;

@WebServlet("/manageDepartment")
public class ManageDepartmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        // 部署一覧取得
        DepartmentDAO dao = new DepartmentDAO();
        List<Department> departmentList = dao.findAll();

        // DAO異常対策
        if (departmentList == null) {
            request.setAttribute("errorMessage", "部署一覧の取得に失敗しました。");
        } else {
            request.setAttribute("departmentList", departmentList);
        }

        // JSPへフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/manageDepartment.jsp");
        dispatcher.forward(request, response);
    }
}