package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.PositionDAO;
import model.Employee;
import model.Position;

@WebServlet("/updatePosition")
public class UpdatePositionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Employee employee = (Employee)session.getAttribute("loginUser");
		
		if(employee == null) {
			response.sendRedirect("Home");
			return;
		}
		
		//フォームのデータを取得
		int positionId = Integer.parseInt(request.getParameter("positionId"));

		//指定したデータで役職を取得
		PositionDAO dao = new PositionDAO();
		Position position = dao.findByPositionId(positionId);
		
		//リクエストスコープに保存
		request.setAttribute("position", position);
		
		//updatePosition.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/updatePosition.jsp");
		dispatcher.forward(request, response);
	}

}
