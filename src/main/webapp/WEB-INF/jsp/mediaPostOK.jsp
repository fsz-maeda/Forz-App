<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>投稿完了</title>
<style>
    :root {
        --primary-color: #2563eb;
        --primary-hover: #1d4ed8;
        --success-color: #10b981;
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
        display: flex;
        align-items: center;
        justify-content: center;
        min-height: 80vh;
    }

    .container {
        max-width: 450px;
        width: 100%;
        background: var(--card-bg);
        padding: 40px 30px;
        border-radius: 8px;
        box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06);
        text-align: center;
    }

    /* 完了アイコン */
    .success-icon {
        width: 64px;
        height: 64px;
        background-color: #ecfdf5;
        color: var(--success-color);
        border-radius: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 32px;
        font-weight: bold;
        margin: 0 auto 20px auto;
    }

    h1 {
        font-size: 20px;
        margin-top: 0;
        margin-bottom: 12px;
        color: var(--text-main);
    }

    p {
        color: var(--text-muted);
        font-size: 14px;
        margin-bottom: 30px;
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
        cursor: pointer;
        transition: background-color 0.2s;
        text-decoration: none;
        box-sizing: border-box;
        width: 100%;
    }

    .btn-primary {
        background-color: var(--primary-color);
        color: white;
        border: none;
    }

    .btn-primary:hover {
        background-color: var(--primary-hover);
    }
</style>
</head>
<body>

<div class="container">
    <!-- 完了を示すチェックマークアイコン -->
    <div class="success-icon">✓</div>
    
    <h1>投稿が完了しました</h1>
    <p>新しい記事が正常に公開されました。</p>
    
    <!-- 元のリンクをボタンUIにして維持 -->
    <a href="media" class="btn btn-primary">メディア画面へ戻る</a>
</div>

</body>
</html>
