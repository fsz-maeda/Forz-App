package servlet;

import java.io.IOException;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.DailyReportLikeDAO;
import model.Employee;

@WebServlet("/DailyReportLikeServlet")
public class DailyReportLikeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
//    	ログイン確認
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("loginUser");

        if (employee == null) {
            response.sendRedirect("Home");
            return;
        }
        
        
        int dailyReportId = Integer.parseInt(request.getParameter("dailyReportId"));

        DailyReportLikeDAO dao = new DailyReportLikeDAO();
        
//      いいねしているのかの確認
        Set<Integer> likedSet = dao.findLikedReportIds(employee.getEmployeeId());
        
//        	いいねしているかしていないかでの分岐containsでsetの中身で同じもの探している
        	if (likedSet.contains(dailyReportId)) {
        	    dao.deleteLikeEmployee(employee.getEmployeeId(), dailyReportId);
        	} else {
        	    dao.insertLikeEmployee(employee.getEmployeeId(), dailyReportId);
        	}

        response.sendRedirect("dailyReportPage");
    }
}