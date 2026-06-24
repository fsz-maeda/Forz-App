package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.PositionDAO;

@WebServlet("/updatePositionCheck")
public class UpdatePositionCheckServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        
        try {
            // フォームデータ取得
            int positionId = Integer.parseInt(request.getParameter("positionId"));
            String positionName = request.getParameter("positionName");

            // 入力チェック
            if (positionName == null || positionName.trim().isEmpty()) {
                session.setAttribute("updatePositionMsg", "役職名を入力してください");
                response.sendRedirect("managePosition");
                return;
            }

            // 更新処理
            PositionDAO dao = new PositionDAO();
            boolean result = dao.updatePosition(positionId, positionName);

            // 実行結果
            if (result) {
                session.setAttribute("updatePositionMsg", "更新成功");
            } else {
                session.setAttribute("updatePositionMsg", "更新失敗");
            }

        } catch (NumberFormatException e) {
            session.setAttribute("updatePositionMsg", "不正なデータです");
        }

        // 一覧画面へ戻る
        response.sendRedirect("managePosition");
    }
}