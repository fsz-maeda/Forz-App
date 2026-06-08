package servlet;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.EventDAO;
import model.Employee;
import model.Event;

@WebServlet("/eventDelete")
public class EventDeleteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession();

        Employee loginUser =
                (Employee) session.getAttribute("loginUser");

        int eventId =
                Integer.parseInt(
                        request.getParameter("eventId"));

        EventDAO dao = new EventDAO();

        Event event = dao.findById(eventId);

        if (event != null &&
            event.getEmployeeId() ==
            loginUser.getEmployeeId()) {

            dao.delete(eventId);
        }

        response.sendRedirect("Event");
    }
}