package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.SalaryDAO;
import model.Employee;
import model.Salary;

@WebServlet("/updateSalary")
public class UpdateSalaryServlet extends HttpServlet {
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
		int salaryId = Integer.parseInt(request.getParameter("salaryId"));
		
		//指定したデータで給料を取得
		SalaryDAO dao = new SalaryDAO();
		Salary salary = dao.findBySalaryId(salaryId);
		
		//リクエストスコープに保存
		request.setAttribute("salary", salary);
		
		//updateSalary.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/updateSalary.jsp");
		dispatcher.forward(request, response);
	}

}
