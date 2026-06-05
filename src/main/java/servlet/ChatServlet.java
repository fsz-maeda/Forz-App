package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.ChatDAO;
import dao.EmployeeDAO;
import model.Chat;
import model.Employee;

/**
 * Servlet implementation class ChatServlet
 */
@WebServlet("/ChatServlet")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
	        throws ServletException, IOException {

	    String receiverId =request.getParameter("receiverId");
	    
	    String keyword = request.getParameter("keyword");
	    
	    EmployeeDAO dao = new EmployeeDAO();

	    List<Employee> employeeList = new ArrayList<>();

	    try {
	        if(keyword != null && !keyword.trim().isEmpty()) {
	            employeeList = dao.search(keyword);
	        } else {
	            employeeList = dao.findAll();
	        }
	    } catch(Exception e) {
	        e.printStackTrace();
	    }

	    request.setAttribute("employeeList", employeeList);
	    request.setAttribute("receiverId", receiverId);
	    RequestDispatcher dispatcher =request.getRequestDispatcher("WEB-INF/jsp/chat.jsp");

	    dispatcher.forward(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int senderId = 1;

		int receiverId =Integer.parseInt(
			request.getParameter("receiverId"));

		String message =request.getParameter("message");

		Chat chat = new Chat();

		chat.setSenderId(senderId);
		chat.setReceiverId(receiverId);
		chat.setMessage(message);

		ChatDAO dao = new ChatDAO();

		dao.insert(chat);

		response.sendRedirect("ChatServlet?receiverId=" + receiverId);
	}


	}


