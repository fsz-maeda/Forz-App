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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("loginUser");

        if (employee == null) {
            response.sendRedirect("Home");
            return;
        }

        int eventId = Integer.parseInt(request.getParameter("eventId"));

        EventDAO eventdao = new EventDAO();
        Event event = eventdao.findById(eventId);

        if (event == null) {
            response.sendRedirect("event");
            return;
        }

        if (event.getEmployeeId() == employee.getEmployeeId()) {
            eventdao.delete(eventId);
        }

        response.sendRedirect("event");
    }
}