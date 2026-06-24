package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.PositionDAO;

@WebServlet("/insertPositionCheck")
public class InsertPositionCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		int positionId;
		String positionName;

		// 入力値取得
		try {
			positionId = Integer.parseInt(request.getParameter("positionId"));
			positionName = request.getParameter("positionName");

			if (positionName == null || positionName.trim().isEmpty()) {
				request.getSession().setAttribute("insertPositionMsg", "役職名を入力してください");
				response.sendRedirect("managePosition");
				return;
			}

		} catch (NumberFormatException e) {
			request.getSession().setAttribute("insertPositionMsg", "役職IDが不正です");
			response.sendRedirect("managePosition");
			return;
		}

		PositionDAO dao = new PositionDAO();

		// 同じIDの役職が存在するか確認
		if (dao.findByPositionId(positionId) != null) {
			request.getSession().setAttribute("insertPositionMsg", "その役職IDは既に登録されています");
			response.sendRedirect("managePosition");
			return;
		}

		// 登録
		boolean result = dao.insertPosition(positionId, positionName);

		if (result) {
			request.getSession().setAttribute("insertPositionMsg", "入力完了");
		} else {
			request.getSession().setAttribute("insertPositionMsg", "入力失敗");
		}

		response.sendRedirect("managePosition");
	}
}