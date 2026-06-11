<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.MediaComment"%>
<%@ page import="model.Media" %>
 <%
 String s = (String)session.getAttribute("esg1");
 String s2 = (String)session.getAttribute("esg2");
 Media media = (Media)session.getAttribute("media");
 %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>コメント投稿</title>
<style>
    :root {
        --primary-color: #2563eb;
        --primary-hover: #1d4ed8;
        --bg-color: #f8fafc;
        --card-bg: #ffffff;
        --text-main: #1e293b;
        --text-muted: #64748b;
        --border-color: #e2e8f0;
        --error-color: #ef4444;
    }
    
    body {
        font-family: "Helvetica Neue", Arial, "Hiragino Kaku Gothic ProN", "Hiragino Sans", Meiryo, sans-serif;
        background-color: var(--bg-color);
        color: var(--text-main);
        margin: 0;
        padding: 40px 20px;
        line-height: 1.6;
    }

    .container {
        max-width: 600px;
        margin: 0 auto;
        background: var(--card-bg);
        padding: 35px;
        border-radius: 8px;
        box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06);
    }

    h1 {
        font-size: 22px;
        margin-top: 0;
        margin-bottom: 24px;
        padding-bottom: 12px;
        border-bottom: 2px solid var(--border-color);
        color: var(--text-main);
    }

    .form-group {
        margin-bottom: 20px;
    }

    label {
        display: block;
        font-size: 14px;
        font-weight: 600;
        margin-bottom: 8px;
        color: var(--text-main);
    }

    /* テキストエリアの装飾 */
    textarea {
        width: 100%;
        box-sizing: border-box; /* 幅にパディングを含める */
        padding: 12px;
        border: 1px solid var(--border-color);
        border-radius: 6px;
        font-family: inherit;
        font-size: 15px;
        line-height: 1.5;
        resize: vertical; /* 縦方向のみサイズ変更可能に */
        outline: none;
        transition: border-color 0.2s, box-shadow 0.2s;
    }

    textarea:focus {
        border-color: var(--primary-color);
        box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.1);
    }

    /* エラーメッセージ */
    .error-message {
        color: var(--error-color);
        font-size: 14px;
        margin-top: 8px;
        font-weight: 500;
    }

    /* ボタンエリア（横並び） */
    .btn-group {
        display: flex;
        gap: 12px;
        margin-top: 24px;
        border-top: 1px solid var(--border-color);
        padding-top: 20px;
    }

    /* ボタン共通 */
    .btn {
        display: inline-flex;
        align-items: center;
        justify-content: center;
        padding: 10px 20px;
        font-size: 14px;
        font-weight: 500;
        border-radius: 4px;
        border: none;
        cursor: pointer;
        transition: background-color 0.2s;
        text-decoration: none;
    }

    .btn-primary {
        background-color: var(--primary-color);
        color: white;
        flex: 1; /* 投稿ボタンを少し広めにする */
    }

    .btn-primary:hover {
        background-color: var(--primary-hover);
    }

    .btn-secondary {
        background-color: #edf2f7;
        color: #4a5568;
    }

    .btn-secondary:hover {
        background-color: #e2e8f0;
    }
</style>
</head>
<body>

<div class="container">
    <h1>コメント投稿</h1>
    
    <form action="MediaCommentServlet" method="post">
        <div class="form-group">
            <label for="comment">コメント</label>
            <textarea name="comment" id="comment" rows="8" placeholder="コメントを入力してください（※改行も反映されます）"><%= request.getParameter("comment") != null ? request.getParameter("comment") : "" %></textarea>
            
            <%-- エラーメッセージが存在する場合のみ綺麗に表示 --%>
            <% if (s2 != null && !s2.trim().isEmpty()) { %>
                <div class="error-message"><%= s2 %></div>
            <% } %>
        </div>
        
        <input type="hidden" name="IID" value="<%= media.getId() %>">
        
        <div class="btn-group">
            <%-- 戻るボタンをフォームの横に並べるため、中の構造を調整 --%>
            <button type="submit" class="btn btn-primary">投稿する</button>
        </div>
    </form><br><br><br>
    
    <%-- 戻るボタンのフォームを下の位置でボタンデザインに統一 --%>
    <div style="margin-top: -54px; width: fit-content;">
        <form action="ArticleContentServlet" method="post">
            <button type="submit" class="btn btn-secondary">戻る</button>
        </form>
    </div>
</div>

</body>
</html>
