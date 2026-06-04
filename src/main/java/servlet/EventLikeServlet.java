//package servlet;
//
//import java.io.IOException;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import dao.EventDAO;
//
//@WebServlet("/eventLike")
//public class EventLikeServlet extends HttpServlet {
//
//	protected void doPost(
//			HttpServletRequest request,
//			HttpServletResponse response)
//			throws ServletException, IOException {
//
//		int eventId = Integer.parseInt(
//				request.getParameter("eventId"));
//
//		EventDAO dao = new EventDAO();
//
//		dao.addLike(eventId);
//
//		response.sendRedirect("event");
//	}
//}