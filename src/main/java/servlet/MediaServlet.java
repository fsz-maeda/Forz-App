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

import dao.EmployeeDAO;
import dao.MediaDAO;
import model.Employee;
import model.Media;

@WebServlet("/media")
public class MediaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		Employee loginUser = (Employee)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			response.sendRedirect("Home");
			return;
		}

		MediaDAO dao = new MediaDAO();
		List<Media> medialist = dao.findAll(loginUser.getDepartment()); //部署ごとのfindAll
		
		System.out.println(loginUser.getDepartment());
		
		EmployeeDAO edao = new EmployeeDAO();
		List<Employee> employeeList = edao.findAll(); //従業員のデータを取得

		request.setAttribute("medialist", medialist);
		request.setAttribute("employeeList", employeeList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/media.jsp");
		dispatcher.forward(request, response);
	}
}
