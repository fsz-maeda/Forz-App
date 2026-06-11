package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.EmployeeDAO;

@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		int positionId = Integer.parseInt(request.getParameter("positionId"));
		int departmentId = Integer.parseInt(request.getParameter("departmentId"));
		String enter = request.getParameter("enter");
		int remainPaidHoliday = Integer.parseInt(request.getParameter("remainPaidHoliday"));
		boolean management = Boolean.parseBoolean(request.getParameter("management"));
		
		EmployeeDAO dao = new EmployeeDAO();
		boolean result = dao.updateEmployee(employeeId, positionId, departmentId, enter, remainPaidHoliday, management);
		
		if(result) {
		    request.getSession().setAttribute("updateUserMsg", "更新成功");
		} else {
		    request.getSession().setAttribute("updateUserMsg", "更新失敗");
		}

		response.sendRedirect("manageUser");
	}

}
