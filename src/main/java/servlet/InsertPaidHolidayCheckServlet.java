package servlet;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.PaidHolidayDAO;
import service.PaidHolidayService;

@WebServlet("/insertPaidHolidayCheck")
public class InsertPaidHolidayCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        Date startDate = Date.valueOf(request.getParameter("startDate"));
        Date finishDate = Date.valueOf(request.getParameter("finishDate"));
        
        PaidHolidayService phs = new PaidHolidayService();
        double usedDays = phs.calcUsedDays(startDate, finishDate);
        
        if (usedDays == -1) {

            request.getSession().setAttribute("insertPaidHolidayMsg", "終了日は開始日以降を入力してください");

            response.sendRedirect("insertPaidHoliday");
            return;
        }
        
        PaidHolidayDAO dao = new PaidHolidayDAO();
        boolean result = dao.insertPaidHoliday(employeeId, usedDays, startDate, finishDate);
        
        if(result) {
        	request.getSession().setAttribute("insertPaidHolidayMsg", "申請完了");
        }else {
        	request.getSession().setAttribute("insertPaidHolidayMsg", "申請失敗");
        }
        
        response.sendRedirect("insertPaidHoliday");
    }
}