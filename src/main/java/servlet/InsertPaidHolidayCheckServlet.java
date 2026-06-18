package servlet;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.EmployeeDAO;
import dao.PaidHolidayDAO;
import model.Employee;
import service.PaidHolidayService;

@WebServlet("/insertPaidHolidayCheck")
public class InsertPaidHolidayCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
		Employee loginUser = (Employee)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			response.sendRedirect("Home");
			return;
		}

        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        Date startDate = Date.valueOf(request.getParameter("startDate"));
        Date finishDate = Date.valueOf(request.getParameter("finishDate"));
        String holidayType = request.getParameter("holidayType");
        
        PaidHolidayService phs = new PaidHolidayService();
        double usedDays = phs.calcUsedDays(startDate, finishDate, holidayType);
        
        EmployeeDAO edao = new EmployeeDAO();
        Employee employee = edao.findByUserId(employeeId);
        boolean check = phs.checkUsedDays(employeeId, usedDays, employee);
        
        if (usedDays == -1) {

            request.getSession().setAttribute("insertPaidHolidayMsg", "入力内容に誤りがあります");

            response.sendRedirect("insertPaidHoliday");
            return;
        }
        
        if(check) {
	        PaidHolidayDAO dao = new PaidHolidayDAO();
	        boolean result = dao.insertPaidHoliday(employeeId, usedDays, startDate, finishDate, holidayType);
	        
	        if(result) {
	        	request.getSession().setAttribute("insertPaidHolidayMsg", "申請完了");
	        }else {
	        	request.getSession().setAttribute("insertPaidHolidayMsg", "申請失敗");
	        }
        }else {
        	request.getSession().setAttribute("insertPaidHolidayMsg", "有給日数が超過しています");
        }
        
        response.sendRedirect("insertPaidHoliday");
    }
}