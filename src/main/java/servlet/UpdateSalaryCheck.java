package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.SalaryDAO;
import model.Salary;

@WebServlet("/updateSalaryCheck")
public class UpdateSalaryCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//フォームのデータを取得
		int salaryId = Integer.parseInt(request.getParameter("salaryId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		int month = Integer.parseInt(request.getParameter("month"));
		
		//給料インスタンスの作成
		Salary salary = new Salary(salaryId, userId, amount, month);
		
		//給料テーブルを更新
		SalaryDAO dao = new SalaryDAO();
		boolean result = dao.updateSalary(salary);
		
		//実行結果
		if(result) {
			request.getSession().setAttribute("updateSalaryMsg", "更新完了");
		}else {
			request.getSession().setAttribute("updateSalaryMsg", "更新失敗");
		}
		
		//manageSalaryにフォワード
		response.sendRedirect("manageSalary");
	}

}
