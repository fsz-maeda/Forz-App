package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.GroupDAO;
import model.Employee;
import model.Group;

@WebServlet("/CreateGroupServlet")
public class CreateGroupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // GET request
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

    	request.getRequestDispatcher("WEB-INF/jsp/createGroup.jsp")
        .forward(request, response);
    }

    // POST request
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        Employee loginUser =
                (Employee) session.getAttribute("loginUser");

        if (loginUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String groupName =
                request.getParameter("groupName");

        Group group = new Group();
        group.setGroupName(groupName);
        group.setCreatedBy(
                loginUser.getEmployeeId());

        GroupDAO dao = new GroupDAO();
        dao.createGroup(group);

        response.sendRedirect("GroupChatServlet");
    }
}