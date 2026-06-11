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

@WebServlet("/EditIntroServlet")
public class EditIntroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        Employee loginUser =(Employee)session.getAttribute("loginUser");

        if(loginUser == null) {
            response.sendRedirect("login");
            return;
        }

        String intro = request.getParameter("intro");

        EmployeeDAO dao = new EmployeeDAO();
        dao.updateIntro(loginUser.getEmployeeId(), intro);

        loginUser.setIntro(intro);

        session.setAttribute("loginUser", loginUser);

        response.sendRedirect("MyProfileServlet");
    }
    
}