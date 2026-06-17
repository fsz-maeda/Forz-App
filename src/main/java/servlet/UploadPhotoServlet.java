package servlet;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import dao.EmployeeDAO;
import model.Employee;

@WebServlet("/UploadPhotoServlet")
@MultipartConfig
public class UploadPhotoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginUser") == null) {
            response.sendRedirect("Home");
            return;
        }

        Employee user = (Employee) session.getAttribute("loginUser");

        Part filePart = request.getPart("photo");
        String fileName = filePart.getSubmittedFileName();
        String uploadDir = getServletContext().getRealPath("/images");

        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String uploadPath = uploadDir + File.separator + fileName;

        filePart.write(uploadPath);

        EmployeeDAO dao = new EmployeeDAO();
        dao.updatePhoto(user.getEmployeeId(), "images/" + fileName);

        user.setPhotoPath("images/" + fileName);
        session.setAttribute("loginUser", user);

        response.sendRedirect("MyProfileServlet");
    }
}