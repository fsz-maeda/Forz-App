package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.MediaDAO;
import model.Employee;
import model.Media;

@WebServlet("/MediaDeleteServlet")
public class MediaDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 削除する記事のIDを取得
        int id = Integer.parseInt(request.getParameter("id"));

        HttpSession session = request.getSession();
        Employee loginUser = (Employee) session.getAttribute("loginUser");
        
        MediaDAO dao = new MediaDAO();
        Media media = dao.articleFind(id);

        // セキュリティチェック：記事が存在し、ログインユーザーが投稿者本人である場合のみ削除を許可
        if (media != null && loginUser != null && media.getUserId() == loginUser.getEmployeeId()) {
            
            // 👈 データベースから削除を実行
            boolean isSuccess = dao.mediaDelete(id); 

            if (isSuccess) {
                // 削除が成功したら、セッションに残っている記事の詳細情報（古いデータ）をクリアする
                session.removeAttribute("media");
                session.removeAttribute("commentlist");
            }
        }
        
        // 削除完了後（または失敗時）は記事一覧画面へリダイレクト
        response.sendRedirect("media");
    }
}
