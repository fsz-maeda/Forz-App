package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.PositionDAO;
import model.DeletePositionCheck;

@WebServlet("/deletePosition")
public class DeletePositionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int positionId = Integer.parseInt(request.getParameter("positionId"));
		
		DeletePositionCheck check = new DeletePositionCheck();
		boolean checkResult = check.deletePositionCheck(positionId);
		
		if(!checkResult) {
			request.getSession().setAttribute("deletePositionMsg", "この役職IDに属しているユーザーがいるため、"
					+ "削除できません");
			
			response.sendRedirect("managePosition");
			return;
		}
		
		PositionDAO dao = new PositionDAO();
		boolean result = dao.deletePosition(positionId);
		
		if(result) {
			request.getSession().setAttribute("deletePositionMsg", "削除成功");
		}else {
			request.getSession().setAttribute("deletePositionMsg", "削除失敗");
		}
		
		response.sendRedirect("managePosition");
	}

}
