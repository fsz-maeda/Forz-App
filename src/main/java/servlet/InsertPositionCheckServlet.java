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
		
		int positionId = Integer.parseInt(request.getParameter("positionId"));
		String positionName = request.getParameter("positionName");

		PositionDAO dao = new PositionDAO();
		boolean result = dao.insertPosition(positionId, positionName);
		
		if(result) {
			request.getSession().setAttribute("insertPositionMsg", "入力完了");
		}else {
			request.getSession().setAttribute("insertPositionMsg", "入力失敗");
		}
		
		response.sendRedirect("managePosition");
	}

}
