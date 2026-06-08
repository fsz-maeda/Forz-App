package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.CommentDAO;
import model.Comment;
import model.Employee;

@WebServlet("/commentAdd")
public class CommentAddServlet extends HttpServlet {

	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// JSPから受け取る
		int eventId =
				Integer.parseInt(
						request.getParameter("eventId"));

		String text =
				request.getParameter("comment");

		// ログインユーザー取得
		HttpSession session =
				request.getSession();

		Employee loginUser =
				(Employee) session.getAttribute("loginUser");

		// Commentオブジェクト作成
		Comment comment = new Comment();

		comment.setEventId(eventId);
		comment.setUserId(loginUser.getEmployeeId());
		comment.setComment(text);

		// DB登録
		CommentDAO dao = new CommentDAO();

		dao.insert(comment);

		// 一覧へ戻る
		response.sendRedirect("event");
	}
}