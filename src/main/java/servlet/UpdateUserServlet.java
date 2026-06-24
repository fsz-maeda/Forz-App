package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.EmployeeDAO;
import model.Employee;

@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//フォームのデータを取得
		int employeeId;
		int positionId;
		int departmentId;
		int remainPaidHoliday;

		try {
			employeeId =Integer.parseInt(request.getParameter("employeeId"));
			positionId =Integer.parseInt(request.getParameter("positionId"));
			departmentId =Integer.parseInt(request.getParameter("departmentId"));
			remainPaidHoliday =Integer.parseInt(request.getParameter("remainPaidHoliday"));
			
		} catch(NumberFormatException e) {
			response.sendRedirect("manageUser");
			return;
		}
		
		String enter = request.getParameter("enter");
		boolean management = Boolean.parseBoolean(request.getParameter("management"));
		
		HttpSession session = request.getSession();
		
		if(remainPaidHoliday < 0) {
			session.setAttribute("updateUserMsg", "有給日数は0以上を入力してください");
			response.sendRedirect("manageUser");
			return;
		}
		
		EmployeeDAO dao = new EmployeeDAO();
		Employee target = dao.findByUserId(employeeId);

		if(target == null) {
			session.setAttribute("updateUserMsg", "対象社員が存在しません");
			response.sendRedirect("manageUser");
			return;
		}
		
		//指定したデータで従業員を更新
		boolean result = dao.updateEmployee(employeeId, positionId, departmentId, enter, remainPaidHoliday, management);
		
		//実行結果
		if(result) {
		    request.getSession().setAttribute("updateUserMsg", "更新成功");
		} else {
		    request.getSession().setAttribute("updateUserMsg", "更新失敗");
		}

		//manageUserにリダイレクト
		response.sendRedirect("manageUser");
	}

}
