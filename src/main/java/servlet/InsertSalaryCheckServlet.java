package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.SalaryDAO;
import model.Employee;

@WebServlet("/insertSalaryCheck")
public class InsertSalaryCheckServlet extends HttpServlet {
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
		int userId = Integer.parseInt(request.getParameter("userId"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		int month = Integer.parseInt(request.getParameter("month"));
		
		//指定したデータを登録
		SalaryDAO dao = new SalaryDAO();
		boolean result = dao.insertSalary(userId, amount, month);
		
		//実行結果
		if(result) {
			request.getSession().setAttribute("insertSalaryMsg", "入力完了");
		}else {
			request.getSession().setAttribute("insertSalaryMsg", "入力失敗");
		}
		
		//manageSalaryにリダイレクト
		response.sendRedirect("manageSalary");
	}

}
