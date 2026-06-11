package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.PositionDAO;
import service.DeletePositionService;

@WebServlet("/deletePosition")
public class DeletePositionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//フォームのデータを取得
		int positionId = Integer.parseInt(request.getParameter("positionId"));
		
		//指定した役職IDをもつデータがあるか確認
		DeletePositionService check = new DeletePositionService();
		boolean checkResult = check.deletePositionCheck(positionId);
		
		//確認結果
		if(!checkResult) {
			request.getSession().setAttribute("deletePositionMsg", "この役職IDに属しているユーザーがいるため、"
					+ "削除できません");
			
			response.sendRedirect("managePosition");
			return;
		}
		
		//指定した役職IDをもつデータを削除
		PositionDAO dao = new PositionDAO();
		boolean result = dao.deletePosition(positionId);
		
		//実行結果
		if(result) {
			request.getSession().setAttribute("deletePositionMsg", "削除成功");
		}else {
			request.getSession().setAttribute("deletePositionMsg", "削除失敗");
		}
		
		//managePositionにリダイレクト
		response.sendRedirect("managePosition");
	}

}
