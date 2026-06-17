package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.PositionDAO;
import model.Employee;

@WebServlet("/updatePositionCheck")
public class UpdatePositionCheckServlet extends HttpServlet {
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
		int newPositionId = Integer.parseInt(request.getParameter("newPositionId"));
		String positionName = request.getParameter("positionName");

		//指定したデータで更新
		PositionDAO dao = new PositionDAO();
		boolean result = dao.updatePosition(newPositionId, positionName, positionId);
		
		//実行結果
		if(result) {
			request.getSession().setAttribute("updatePositionMsg", "更新成功");
		}else {
			request.getSession().setAttribute("updatePositionMsg", "更新失敗");
		}
		
		//managePositionにリダイレクト
		response.sendRedirect("managePosition");
	}

}
