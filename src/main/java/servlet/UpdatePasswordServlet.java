package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.EmployeeDAO;
import model.Employee;

@WebServlet("/UpdatePasswordServlet")
public class UpdatePasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginUser") == null) {
            response.sendRedirect("login");
            return;
        }

        Employee user = (Employee) session.getAttribute("loginUser");

        String oldPass = request.getParameter("oldPass");
        String newPass = request.getParameter("newPass");
        String confirmPass = request.getParameter("confirmPass");

        EmployeeDAO dao = new EmployeeDAO();

        // 現在のパスワード確認
        Employee checkUser =
                dao.findByNameAndPass(user.getName(), oldPass);

        if (checkUser == null) {

            request.setAttribute("errorMsg",
                    "現在のパスワードが違います");

            request.getRequestDispatcher("/WEB-INF/jsp/ChangePassword.jsp")
                   .forward(request, response);

            return;
        }

        // 新しいパスワード確認
        if (!newPass.equals(confirmPass)) {

            request.setAttribute("errorMsg",
                    "新しいパスワードが一致しません");

            request.getRequestDispatcher("/WEB-INF/jsp/ChangePassword.jsp")
                   .forward(request, response);

            return;
        }

        // パスワード更新
        boolean result =
                dao.updatePassword(user.getEmployeeId(), newPass);

        if (result) {

            request.setAttribute("successMsg",
                    "パスワードを変更しました");

            request.getRequestDispatcher("/WEB-INF/jsp/ChangePassword.jsp")
                   .forward(request, response);

        } else {

            request.setAttribute("errorMsg",
                    "パスワード変更に失敗しました");

            request.getRequestDispatcher("/WEB-INF/jsp/ChangePassword.jsp")
                   .forward(request, response);
        }
    }
}