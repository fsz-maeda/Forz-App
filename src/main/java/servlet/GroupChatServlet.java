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

import dao.GroupDAO;
import dao.GroupMessageDAO;
import model.Employee;
import model.Group;
import model.GroupMessage;

@WebServlet("/GroupChatServlet")
public class GroupChatServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Employee loginUser = (Employee) session.getAttribute("loginUser");

        if (loginUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        GroupDAO groupDao = new GroupDAO();
        List<Group> groupList = groupDao.getAllGroups();

        String groupIdStr = request.getParameter("groupId");

        Group group = null;
        List<GroupMessage> msgList = null;

        if (groupIdStr != null && !groupIdStr.trim().isEmpty()) {
            try {
                int groupId = Integer.parseInt(groupIdStr);
                group = groupDao.getGroupById(groupId);

                if (group != null) {
                    GroupMessageDAO msgDao = new GroupMessageDAO();
                    msgList = msgDao.getMessages(groupId);
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("GroupChatServlet");
                return;
            }
        }

        request.setAttribute("groupList", groupList);
        request.setAttribute("group", group);
        request.setAttribute("msgList", msgList);
        request.setAttribute("loginUser", loginUser);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/groupChat.jsp");
        dispatcher.forward(request, response);
    }
}