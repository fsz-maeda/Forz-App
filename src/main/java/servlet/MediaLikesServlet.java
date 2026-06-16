package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.MediaLikesDAO;
import model.Employee;

@WebServlet("/mediaLikes")
public class MediaLikesServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("loginUser");

        int mediaId = Integer.parseInt(request.getParameter("mediaId"));

        MediaLikesDAO dao = new MediaLikesDAO();

        if (dao.isLiked(employee.getEmployeeId(), mediaId)) {
            dao.removeLike(employee.getEmployeeId(), mediaId);
        } else {
            dao.addLike(employee.getEmployeeId(), mediaId);
        }

        response.sendRedirect("ArticleContentServlet?id=" + mediaId);
    }
}
