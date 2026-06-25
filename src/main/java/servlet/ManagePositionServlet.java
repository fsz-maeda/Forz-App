package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.PositionDAO;
import model.Position;

@WebServlet("/managePosition")
public class ManagePositionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		//役職テーブルをすべて取得
		PositionDAO dao = new PositionDAO();
		List<Position> positionList = dao.findAll();

		//リクエストスコープに保存
		request.setAttribute("positionList", positionList);
		
		//managePosition.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/managePosition.jsp");
		dispatcher.forward(request, response);
	}

}
