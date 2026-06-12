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
import jakarta.servlet.http.HttpSession;

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
		Employee loginUser = (Employee) session.getAttribute("loginUser");

		MediaDAO dao = new MediaDAO();
		List<Media> medialist = dao.findAll(loginUser.getDepartment()); //部署ごとのfindAll
		List<Media> mediaList = dao.findAll(loginUser.getEmployeeId()); //従業員ごとのfindAll

		System.out.println(medialist.size());
		System.out.println(mediaList.size());
		
		List<Integer> userIdList = new ArrayList<Integer>();
		
		if (mediaList != null) {
			for (Media media : mediaList) {
				userIdList.add(media.getUserId());
			}
		}

		List<String> nameList = new ArrayList<>();
		
		if (userIdList != null) {
			for (int u : userIdList) {
				String name = dao.findName(u);
				nameList.add(name);
			}
		}

		request.setAttribute("medialist", medialist);
		request.setAttribute("nameList", nameList);
		request.setAttribute("mediaList", mediaList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/media.jsp");
		dispatcher.forward(request, response);
	}
}
