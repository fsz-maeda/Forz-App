<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Media" %>
<%
Media editMedia = (Media) request.getAttribute("editMedia");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>記事の編集</title>
<style>
    :root {
        --primary-color: #2563eb;
        --primary-hover: #1d4ed8;
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
        line-height: 1.6;
    }

    .container {
        max-width: 800px;
        margin: 0 auto;
        background: var(--card-bg);
        padding: 40px;
        border-radius: 8px;
        box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06);
    }

    h1 {
        font-size: 24px;
        margin-top: 0;
        margin-bottom: 24px;
        padding-bottom: 12px;
        border-bottom: 2px solid var(--border-color);
        color: var(--text-main);
    }

    .form-group {
        margin-bottom: 24px;
    }

    label {
        display: block;
        font-size: 14px;
        font-weight: 600;
        margin-bottom: 8px;
        color: var(--text-main);
    }

    /* 入力フォーム共通 */
    input[type="text"],
    textarea {
        width: 100%;
        box-sizing: border-box;
        padding: 12px;
        border: 1px solid var(--border-color);
        border-radius: 6px;
        font-family: inherit;
        font-size: 15px;
        outline: none;
        transition: border-color 0.2s, box-shadow 0.2s;
    }

    input[type="text"]:focus,
    textarea:focus {
        border-color: var(--primary-color);
        box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.1);
    }

    textarea {
        resize: vertical; /* 縦方向のみサイズ変更可能 */
        line-height: 1.6;
    }

    /* ボタン・リンクエリア */
    .btn-group {
        display: flex;
        align-items: center;
        gap: 16px;
        margin-top: 32px;
        border-top: 1px solid var(--border-color);
        padding-top: 24px;
    }

    /* ボタン共通 */
    .btn {
        display: inline-flex;
        align-items: center;
        justify-content: center;
        padding: 10px 24px;
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
    <h1>記事の編集</h1>

    <form action="MediaEditServlet" method="post">
        
        <input type="hidden" name="id" value="<%= editMedia.getId() %>">

        <!-- タイトル入力 -->
        <div class="form-group">
            <label for="title">タイトル</label>
            <input type="text" id="title" name="title" value="<%= editMedia.getTitle() %>" placeholder="タイトルを入力してください">
        </div>

        <!-- 本文入力 -->
        <div class="form-group">
            <label for="content">本文</label>
            <textarea id="content" name="content" rows="12" placeholder="本文を入力してください"><%= editMedia.getContent() %></textarea>
        </div>

        <!-- アクションボタン -->
        <div class="btn-group">
            <button type="submit" class="btn btn-primary">更新する</button>
            <a href="media" class="btn btn-secondary">キャンセルして一覧へ</a>
        </div>
    </form>
</div>

</body>
</html>
