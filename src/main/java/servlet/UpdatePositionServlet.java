package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.PositionDAO;
import model.Position;

@WebServlet("/updatePosition")
public class UpdatePositionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		int positionId;

		// パラメータチェック
		try {
			positionId = Integer.parseInt(request.getParameter("positionId"));
			
		} catch (NumberFormatException e) {
			request.getSession().setAttribute("updatePositionMsg", "不正な役職IDです");
			response.sendRedirect("managePosition");
			return;
		}

		// 役職取得
		PositionDAO dao = new PositionDAO();
		Position position = dao.findByPositionId(positionId);

		// 存在チェック
		if (position == null) {
			request.getSession().setAttribute("updatePositionMsg", "対象の役職が存在しません");
			response.sendRedirect("managePosition");
			return;
		}

		// JSPへ渡す
		request.setAttribute("position", position);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/updatePosition.jsp");
		dispatcher.forward(request, response);
	}
}