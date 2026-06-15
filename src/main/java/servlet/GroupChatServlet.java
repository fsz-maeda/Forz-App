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

	@Override
	protected void doGet(
			HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null ||
				session.getAttribute("loginUser") == null) {

			response.sendRedirect("Home");
			return;
		}

		Employee loginUser = (Employee) session.getAttribute("loginUser");

		request.setAttribute(
				"loginUser",
				loginUser);

		GroupDAO groupDao = new GroupDAO();

		List<Group> groupList = groupDao.getAllGroups();

		request.setAttribute(
				"groupList",
				groupList);

		String groupIdStr = request.getParameter("groupId");

		Group group = null;
		List<GroupMessage> msgList = null;

		if (groupIdStr != null &&
				!groupIdStr.trim().isEmpty()) {

			try {

				int groupId = Integer.parseInt(groupIdStr);

				group = groupDao.getGroupById(groupId);

				if (group != null) {

					GroupMessageDAO msgDao = new GroupMessageDAO();

					msgList = msgDao.getMessages(groupId);
				}

			} catch (NumberFormatException e) {

				response.sendRedirect(
						"GroupChatServlet");
				return;
			}
		}

		request.setAttribute(
				"group",
				group);

		request.setAttribute(
				"msgList",
				msgList);

		RequestDispatcher dispatcher = request.getRequestDispatcher(
				"/WEB-INF/jsp/groupChat.jsp");

		dispatcher.forward(
				request,
				response);
	}

	@Override
	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);

		if (session == null ||
				session.getAttribute("loginUser") == null) {

			response.sendRedirect("Home");
			return;
		}

		Employee loginUser = (Employee) session.getAttribute("loginUser");

		String groupIdStr = request.getParameter("groupId");

		String message = request.getParameter("message");

		if (groupIdStr == null ||
				groupIdStr.isEmpty()) {

			response.sendRedirect(
					"GroupChatServlet");
			return;
		}

		int groupId = Integer.parseInt(groupIdStr);

		if (message != null &&
				!message.trim().isEmpty()) {

			GroupMessage msg = new GroupMessage();

			msg.setGroupId(groupId);

			msg.setSenderId(
					loginUser.getEmployeeId());

			msg.setMessage(message);

			GroupMessageDAO dao = new GroupMessageDAO();

			dao.sendMessage(msg);
		}

		response.sendRedirect(
				"GroupChatServlet?groupId="
						+ groupId);
	}
}