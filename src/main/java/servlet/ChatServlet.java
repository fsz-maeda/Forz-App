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

import dao.ChatDAO;
import dao.EmployeeDAO;
import model.Chat;
import model.Employee;

@WebServlet("/ChatServlet")
public class ChatServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
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

		request.setAttribute(
				"loginUser",
				loginUser);

		String keyword = request.getParameter("keyword");

		String receiverIdStr = request.getParameter("receiverId");

		EmployeeDAO employeeDao = new EmployeeDAO();

		List<Employee> employeeList;

		if (keyword != null &&
				!keyword.trim().isEmpty()) {

			employeeList = employeeDao.search(keyword);

		} else {

			employeeList = employeeDao.findAll();
		}

		request.setAttribute(
				"employeeList",
				employeeList);

		if (receiverIdStr != null &&
				!receiverIdStr.isEmpty()) {

			int receiverId = Integer.parseInt(receiverIdStr);

			Employee receiver = employeeDao.findById(receiverId);

			request.setAttribute(
					"receiver",
					receiver);

			ChatDAO chatDao = new ChatDAO();

			List<Chat> chatList = chatDao.findChat(
					loginUser.getEmployeeId(),
					receiverId);

			request.setAttribute(
					"chatList",
					chatList);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(
				"/WEB-INF/jsp/chat.jsp");

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

		int senderId = loginUser.getEmployeeId();

		int receiverId = Integer.parseInt(
				request.getParameter(
						"receiverId"));

		String message = request.getParameter(
				"message");

		if (message != null &&
				!message.trim().isEmpty()) {

			Chat chat = new Chat();

			chat.setSenderId(senderId);
			chat.setReceiverId(receiverId);
			chat.setMessage(message);

			ChatDAO dao = new ChatDAO();

			dao.insert(chat);
		}

		response.sendRedirect(
				"ChatServlet?receiverId="
						+ receiverId);
	}
}