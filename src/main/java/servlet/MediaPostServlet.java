package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Employee;
import model.Media;
import model.MediaRegistLogic;

/**
 * Servlet implementation class MediaPost
 */
@WebServlet("/MediaPostServlet")
public class MediaPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String errorMsg="";
		String errorMsg2 ="";
		String errorMsg3 ="";
		
		 HttpSession session = request.getSession();
		 session.setAttribute("errorMsg",errorMsg);
		 session.setAttribute("errorMsg2",errorMsg2);
		 session.setAttribute("errorMsg3", errorMsg3);
		 
		 
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mediapost.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String a = request.getParameter("departmentId");
		int departmentId =Integer.parseInt(a);
		
		if(category.equals("businessknowledge")) {category="業務ナレッジ";}
		if(category.equals("contact")) {category="部署内連絡・進歩共有";}
		if(category.equals("membership")) {category="メンバーシップ・相互理解";}
		if(category.equals("others")) {category="その他";}
		
		String errorMsg="";
		String errorMsg2 ="";
		String errorMsg3 ="";
		
		if(category.length()==0) {
			errorMsg +="カテゴリーが入力されていません";
		}
		if(title.length()==0) {
			errorMsg2 += "タイトルが入力されていません";
		}
		if(content.length()==0) {
			errorMsg3 += "内容が入力されていません";
		}
		if(title.length()!=0&&content.length()!=0&&category.length()!=0) {
			HttpSession session = request.getSession();
			Employee em = (Employee)session.getAttribute("loginUser");
			Media media = new Media(category,title,content);
			MediaRegistLogic mrl = new MediaRegistLogic();
			boolean result = mrl.execute(media,departmentId,em);
			if(result) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mediaPostOK.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		 HttpSession session = request.getSession();
		 session.setAttribute("errorMsg",errorMsg);
		 session.setAttribute("errorMsg2", errorMsg2);
		 session.setAttribute("errorMsg3", errorMsg3);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mediapost.jsp");
		dispatcher.forward(request, response);

	}

}
