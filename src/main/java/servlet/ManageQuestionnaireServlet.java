package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.QuestionnaireDAO;
import model.Questionnaire;

@WebServlet("/ManageQuestionnaireServlet")
public class ManageQuestionnaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//アンケートテーブルをすべて取得
		QuestionnaireDAO dao = new QuestionnaireDAO();
		List<Questionnaire> questionnaireList = dao.findAll();
		
		//アンケートリストをリクエストスコープに保存
		request.setAttribute("questionnaireList", questionnaireList);
		
		//manageQuestionnaire.jspに保存
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/manageQuestionnaire.jsp");
		dispatcher.forward(request, response);
	}

}
