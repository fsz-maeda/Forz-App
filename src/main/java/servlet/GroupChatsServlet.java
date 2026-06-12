package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.GroupMessageDAO;
import model.Employee;
import model.GroupMessage;

@WebServlet("/GroupChatsServlet")
public class GroupChatsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
        Employee loginUser = (Employee) session.getAttribute("loginUser");

        if (loginUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String groupIdStr = request.getParameter("groupId");
        String message = request.getParameter("message");

        if (groupIdStr == null || groupIdStr.trim().isEmpty()
                || message == null || message.trim().isEmpty()) {
            response.sendRedirect("GroupChatServlet");
            return;
        }

        int groupId;

        try {
            groupId = Integer.parseInt(groupIdStr);
        } catch (Exception e) {
            response.sendRedirect("GroupChatServlet");
            return;
        }

        GroupMessage gm = new GroupMessage();
        gm.setGroupId(groupId);
        gm.setSenderId(loginUser.getEmployeeId());
        gm.setMessage(message);

        GroupMessageDAO dao = new GroupMessageDAO();
        dao.sendMessage(gm);

        response.sendRedirect("GroupChatServlet?groupId=" + groupId);
	}

}
