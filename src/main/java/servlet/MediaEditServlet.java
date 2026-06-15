package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.MediaDAO;
import model.Employee;
import model.Media;

@WebServlet("/MediaEditServlet")
public class MediaEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        MediaDAO dao = new MediaDAO();
        Media media = dao.articleFind(id); 

        HttpSession session = request.getSession();
        Employee loginUser = (Employee) session.getAttribute("loginUser");

        
        if (media != null && loginUser != null && media.getEmployeeId() == loginUser.getEmployeeId()) {
            request.setAttribute("editMedia", media);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mediaEdit.jsp");
            dispatcher.forward(request, response);
        } else {
            
            response.sendRedirect("media");
        }
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        HttpSession session = request.getSession();
        Employee loginUser = (Employee) session.getAttribute("loginUser");
        
        MediaDAO dao = new MediaDAO();
        Media media = dao.articleFind(id);

       
        if (media != null && loginUser != null && media.getEmployeeId() == loginUser.getEmployeeId()) {
            
            Media updateMedia = new Media(id, content, title, loginUser.getEmployeeId());
            boolean isSuccess = dao.mediaUpdate(updateMedia); 

            if (isSuccess) {
                
                session.setAttribute("media", updateMedia);
                response.sendRedirect("media"); 
                return;
            }
        }
        
        response.sendRedirect("media");
    }
}
