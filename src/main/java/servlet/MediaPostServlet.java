package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.DepartmentDAO;
import dao.MediaDAO;
import model.Department;
import model.Employee;
import model.Media;

@WebServlet("/MediaPostServlet")
public class MediaPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Employee employee = (Employee)session.getAttribute("loginUser");
		
		if(employee == null) {
			response.sendRedirect("Home");
			return;
		}
		
		DepartmentDAO departmentDao = new DepartmentDAO();
		List<Department> departmentList = departmentDao.findAll();

		request.setAttribute("departmentList", departmentList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mediapost.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		int departmentId = Integer.parseInt(request.getParameter("departmentId"));
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		String errorMsg = "";
		String errorMsg2 = "";
		String errorMsg3 = "";

		if (category == null || category.isEmpty()) {
			errorMsg = "カテゴリーが入力されていません";
		}

		if (title == null || title.isEmpty()) {
			errorMsg2 = "タイトルが入力されていません";
		}

		if (content == null || content.isEmpty()) {
			errorMsg3 = "本文が入力されていません";
		}

		// エラーあり
		if (!errorMsg.isEmpty() || !errorMsg2.isEmpty() || !errorMsg3.isEmpty()) {

			request.getSession().setAttribute("errorMsg", errorMsg);
			request.getSession().setAttribute("errorMsg2", errorMsg2);
			request.getSession().setAttribute("errorMsg3", errorMsg3);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mediapost.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// 日本語変換
		if (category.equals("businessknowledge"))
			category = "業務ナレッジ";
		if (category.equals("contact"))
			category = "部署内連絡・進歩共有";
		if (category.equals("membership"))
			category = "メンバーシップ・相互理解";
		if (category.equals("others"))
			category = "その他";

		HttpSession session = request.getSession();
		Employee employee = (Employee) session.getAttribute("loginUser");

		Media media = new Media(category, title, content);

		MediaDAO dao = new MediaDAO();
		boolean result = dao.insert(media, departmentId, employee.getEmployeeId());

		if (result) {
			session.setAttribute("successMsg", "投稿しました");
			response.sendRedirect("media");
			return;
		}

		session.setAttribute("errorMsg", "投稿に失敗しました");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mediapost.jsp");
		dispatcher.forward(request, response);
	}
}
