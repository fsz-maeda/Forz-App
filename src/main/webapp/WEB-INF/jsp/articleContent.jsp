<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Media"%>
<%@ page import="model.MediaComment"%>
<%@ page import="model.Employee"%>
<%@ page import="java.util.List" %>
<%
Media media = (Media)session.getAttribute("media");
List<MediaComment> commentlist = (List<MediaComment>)session.getAttribute("commentlist");
Employee loginUser = (Employee)session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><%= media.getTitle() %></title>
<style>
    :root {
        --primary-color: #2563eb;
        --primary-hover: #1d4ed8;
        --danger-color: #ef4444;
        --danger-hover: #dc2626;
        --bg-color: #f8fafc;
        --card-bg: #ffffff;
        --text-main: #1e293b;
        --text-muted: #64748b;
        --border-color: #e2e8f0;
    }
    
    body {
        font-family: "Helvetica Neue", Arial, "Hiragino Kaku Gothic ProN", "Hiragino Sans", Meiryo, sans-serif;
        background-color: var(--bg-color);
        color: var(--text-main);
        margin: 0;
        padding: 40px 20px;
        line-height: 1.7;
    }

    .container {
        max-width: 800px;
        margin: 0 auto;
        background: var(--card-bg);
        padding: 40px;
        border-radius: 8px;
        box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06);
    }

    /* 記事ヘッダー */
    .article-header {
        margin-bottom: 24px;
        padding-bottom: 20px;
        border-bottom: 2px solid var(--border-color);
    }

    h1 {
        font-size: 28px;
        margin: 0 0 16px 0;
        color: var(--text-main);
        line-height: 1.3;
    }

    /* アクションエリア（編集・削除） */
    .action-group {
        display: flex;
        gap: 10px;
        flex-wrap: wrap;
    }

    /* 記事本文 */
    
.article-content {
    font-size: 16px;
    color: var(--text-main);
    margin-bottom: 40px;
    white-space: pre-wrap;       
    overflow-wrap: break-word;   
    word-break: break-all;       
}
    

    /* コメントセクション */
    .comment-section {
        margin-top: 40px;
        padding-top: 30px;
        border-top: 1px dashed var(--border-color);
    }

    h2 {
        font-size: 20px;
        margin-top: 0;
        margin-bottom: 20px;
        color: var(--text-main);
    }

    /* コメントテーブル */
    .comment-table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 12px;
        font-size: 14px;
    }

    .comment-table th {
        background-color: #f1f5f9;
        color: var(--text-main);
        font-weight: 600;
        padding: 10px 14px;
        border-bottom: 2px solid var(--border-color);
        text-align: left;
    }

    .comment-table td {
        padding: 12px 14px;
        border-bottom: 1px solid var(--border-color);
        color: var(--text-main);
        vertical-align: top;
    }

    .comment-table tr:hover td {
        background-color: #f8fafc;
    }

    .comment-name {
        font-weight: bold;
        color: #475569;
        width: 25%;
    }

    /* ボタン・リンク共通 */
    .btn {
        display: inline-flex;
        align-items: center;
        justify-content: center;
        padding: 8px 16px;
        font-size: 14px;
        font-weight: 500;
        border-radius: 4px;
        border: none;
        cursor: pointer;
        transition: background-color 0.2s, color 0.2s;
        text-decoration: none;
    }

    .btn-sm {
        padding: 6px 12px;
        font-size: 13px;
    }

    .btn-primary {
        background-color: var(--primary-color);
        color: white;
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

    .btn-danger {
        background-color: #ffeeec;
        color: var(--danger-color);
        border: 1px solid #fca5a5;
    }

    .btn-danger:hover {
        background-color: var(--danger-color);
        color: white;
    }

    /* ページフッター */
    .article-footer {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-top: 30px;
    }
</style>
</head>
<body>

<div class="container">
    <!-- 記事ヘッダー領域 -->
    <div class="article-header">
        <h1><%= media.getTitle() %></h1>
        
        <% if (loginUser != null && loginUser.getEmployeeId() == media.getUserId()) { %>
            <div class="action-group">
                <form action="MediaEditServlet" method="get" style="display:inline;">
                    <input type="hidden" name="id" value="<%= media.getId() %>">
                    <button type="submit" class="btn btn-sm btn-secondary">この記事を編集する</button>
                </form>
                <form action="MediaDeleteServlet" method="post" style="display:inline;" onsubmit="return confirm('本当にこの記事を削除しますか？');">
                    <input type="hidden" name="id" value="<%= media.getId() %>">
                    <button type="submit" class="btn btn-sm btn-danger">この記事を削除する</button>
                </form>
            </div>
        <% } %>
    </div>

    <!-- 記事本文領域 -->
    
<div class="article-content">
    <%= media.getContent() %>
</div>
    

    <!-- 記事フッターナビゲーション -->
    <div class="article-footer">
        <a href="media" class="btn btn-secondary">← メディア画面へ</a>
        <a href="MediaCommentServlet" class="btn btn-primary">コメントを書く</a>
    </div>

    <!-- コメントセクション -->
    <div class="comment-section">
        <h2>コメント一覧</h2>
        <table class="comment-table">
            <thead>
                <tr>
                    <th class="comment-name">名前</th>
                    <th>コメント</th>
                </tr>
            </thead>
            <tbody>
                <% for (MediaComment mc : commentlist) { %>
                <tr>
                    <td class="comment-name"><%= mc.getName() %></td>
                    <td style="white-space: pre-wrap;"><%= mc.getComment() %></td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
