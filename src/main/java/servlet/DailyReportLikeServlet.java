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
    	
//    	ログイン確認
        HttpSession session = request.getSession();
        Employee user = (Employee) session.getAttribute("loginUser");

        if (user == null) {
            response.sendRedirect("Home");
            return;
        }
        
        
        int dailyReportId = Integer.parseInt(request.getParameter("dailyReportId"));

        DailyReportLikeDAO dao = new DailyReportLikeDAO();
        
//     	isLikedはuserがいいねしてるかどうかの確認処理していなかったらinsertLikeUserでいいねを押した登録処理
        Set<Integer> likedSet =
        	    dao.findLikedReportIds(user.getEmployeeId());

        	if (likedSet.contains(dailyReportId)) {
        	    dao.deleteLikeEmployee(user.getEmployeeId(), dailyReportId);
        	} else {
        	    dao.insertLikeEmployee(user.getEmployeeId(), dailyReportId);
        	}

        response.sendRedirect("dailyReportPage");
    }
}