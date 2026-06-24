package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.EmployeeDAO;
import dao.SalaryDAO;
import model.Employee;
import model.Salary;

@WebServlet("/updateSalary")
public class UpdateSalaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//フォームのデータを取得
		int salaryId;

		try {
		    salaryId = Integer.parseInt(request.getParameter("salaryId"));
		} catch(NumberFormatException e){
		    response.sendRedirect("manageSalary");
		    return;
		}
		
		//指定したデータで給料を取得
		SalaryDAO dao = new SalaryDAO();
		Salary salary = dao.findBySalaryId(salaryId);
		
		if(salary == null) {
		    request.getSession().setAttribute("updateSalaryMsg", "対象データが存在しません");
		    response.sendRedirect("manageSalary");
		    return;
		}
		
		EmployeeDAO employeeDAO = new EmployeeDAO();
		List<Employee> employeeList = employeeDAO.findAll();
		
		//リクエストスコープに保存
		request.setAttribute("salary", salary);
		request.setAttribute("employeeList", employeeList);
		
		//updateSalary.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/updateSalary.jsp");
		dispatcher.forward(request, response);
	}

}
