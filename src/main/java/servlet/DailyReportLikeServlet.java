package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.DailyReportLikeDAO;
import model.User;

@WebServlet("/DailyReportLikeServlet")
public class DailyReportLikeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
//    	ログイン確認
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");

        if (user == null) {
            response.sendRedirect("Home");
            return;
        }
        
        
        int dailyReportId = Integer.parseInt(request.getParameter("dailyReportId"));

        DailyReportLikeDAO dao = new DailyReportLikeDAO();
        
//     	isLikedはuserがいいねしてるかどうかの確認処理していなかったらinsertLikeUserでいいねを押した登録処理
        if (!dao.isLiked(user.getUserId(), dailyReportId)) {
            dao.insertLikeUser(user.getUserId(), dailyReportId);
        }

        response.sendRedirect("dailyReportPage");
    }
}