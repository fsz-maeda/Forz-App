package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.QuestionnaireDAO;
import model.Employee;
import model.Questionnaire;

@WebServlet("/insertQuestionnaire")
public class InsertQuestionnaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Employee employee = (Employee)session.getAttribute("loginUser");
		
		QuestionnaireDAO dao = new QuestionnaireDAO();
		List<Questionnaire> questionnaireList = dao.findByEmployeeId(employee.getEmployeeId());
		
		request.setAttribute("questionnaireList", questionnaireList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/insertQuestionnaire.jsp");
		dispatcher.forward(request, response);
	}

}
