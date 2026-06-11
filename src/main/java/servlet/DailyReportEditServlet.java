package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.DailyReportDAO;
import model.DailyReport;
import model.Employee;

/**
 * Servlet implementation class DailyReportEditServlet
 */
@WebServlet("/DailyReportEditServlet")
public class DailyReportEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Employee loginUser = (Employee) session.getAttribute("loginUser");

        if (loginUser == null) {
            response.sendRedirect("Home");
            return;
        }

        int reportId = Integer.parseInt(request.getParameter("reportId"));

        DailyReportDAO dao = new DailyReportDAO();

//      どのレポートなのか中身を取得
        DailyReport report = dao.findById(reportId);

        request.setAttribute("report", report);

        request.getRequestDispatcher("/WEB-INF/jsp/dailyReportEdit.jsp")
               .forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
        Employee loginUser = (Employee) session.getAttribute("loginUser");

        if (loginUser == null) {
            response.sendRedirect("Home");
            return;
        }

        int reportId = Integer.parseInt(request.getParameter("reportId"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String reportType = request.getParameter("reportType");

        DailyReportDAO dao = new DailyReportDAO();
        
        boolean result = dao.updateReport(reportId, loginUser.getEmployeeId(), title, content, reportType);
        
        if(result) {
        	response.sendRedirect("dailyReportPage");
        	return;
        }else {
        	session.setAttribute("dailyReportUpdateReportErrorMsg", "更新できません。");
        	response.sendRedirect("DailyReportEditServlet");
        	return;

        }

        
    }

}
