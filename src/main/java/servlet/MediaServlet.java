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
import model.MediaLogic;

/**
 * Servlet implementation class MediaServlet
 */
@WebServlet("/media")
public class MediaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Employee loginUser = (Employee)session.getAttribute("loginUser");
        MediaLogic ml = new MediaLogic();
        List<Media> mediaList = ml.execute(loginUser);
        session.setAttribute("mediaList",mediaList);
        List<Media> mediaList2 = (List<Media>)session.getAttribute("mediaList");
        List<Integer>userIdList = new ArrayList<Integer>();
        for (Media media : mediaList2) {
        	userIdList.add(media.getUserId());
        }
        MediaDAO dao = new MediaDAO(); 
        List<String>nameList = new ArrayList<>(); 
        for (int u : userIdList) {
        	String name = dao.findName(u);
        	nameList.add(name);
        }
        session.setAttribute("nameList",nameList);
        
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/media.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
