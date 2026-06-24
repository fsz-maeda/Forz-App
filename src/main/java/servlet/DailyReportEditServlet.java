package servlet;

import java.io.IOException;
import java.util.Set;

import jakarta.servlet.RequestDispatcher;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
        HttpSession session = request.getSession();
        Employee loginUser = (Employee) session.getAttribute("loginUser");

        if (loginUser == null) {
            response.sendRedirect("Home");
            return;
        }

        int reportId;
        
        try {
        	reportId = Integer.parseInt(request.getParameter("reportId"));
        	if(reportId <= 0) throw new NumberFormatException();
        }catch (Exception e) {
        	response.sendError(400);
        	return;
        }
        
        DailyReportDAO dao = new DailyReportDAO();
        
//      どのレポートなのか中身を取得
        DailyReport report = dao.findById(reportId);
        if(report == null || report.getEmployeeId() != loginUser.getEmployeeId()) {
        	response.sendError(403);
        	return;
        }

        request.setAttribute("report", report);

		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/dailyReportEdit.jsp");
		dispatcher.forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
        Employee loginUser = (Employee) session.getAttribute("loginUser");

        if (loginUser == null) {
            response.sendRedirect("Home");
            return;
        }
        
        String csrf = request.getParameter("csrf");
        String sessionCsrf = (String) session.getAttribute("csrfToken");
        
        if(sessionCsrf == null || !sessionCsrf.equals(csrf)) {
        	response.sendError(403);
        	return;
        }
        
        int reportId;
        
        try {
        	reportId = Integer.parseInt(request.getParameter("reportId"));
        	if(reportId <= 0) throw new NumberFormatException();
        }catch(Exception e) {
        	response.sendError(400);
        	return;
        }
        
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String reportType = request.getParameter("reportType");
        
        if(title == null || title.trim().isEmpty() || title.length() > 100){
        	response.sendError(400);
        	return;
        }
        
        if(content == null || content.trim().isEmpty() || content.length() > 5000) {
        	response.sendError(400);
        	return;
        }
        
        Set<String> allowedTypes = Set.of("日報","週間レポート");
        
        if(reportType == null || !allowedTypes.contains(reportType)) {
        	response.sendError(400);
        	return;
        }

        DailyReportDAO dao = new DailyReportDAO();
        
        DailyReport report = dao.findById(reportId);
        if(report == null || report.getEmployeeId() != loginUser.getEmployeeId()) {
        	response.sendError(403);
        	return;
        }
        
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
