package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.QuestionnaireDAO;
import model.Employee;

@WebServlet("/InsertQuestionnaireCheckServlet")
public class InsertQuestionnaireCheckServlet extends HttpServlet {
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
		
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		String content = request.getParameter("content");
		
		QuestionnaireDAO dao = new QuestionnaireDAO();
		boolean result = dao.insertQuestionnaire(employeeId, content);
		
		if(result) {
			request.getSession().setAttribute("insertQuestionnaireMsg", "記入完了");
		}else {
			request.getSession().setAttribute("insertQuestionnaireMsg", "記入失敗");
		}
		
		response.sendRedirect("insertQuestionnaire");
	}

}
