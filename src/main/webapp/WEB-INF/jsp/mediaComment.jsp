<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.MediaComment"%>
<%@ page import="model.Media"%>

<%
String s = (String) session.getAttribute("esg1");
String s2 = (String) session.getAttribute("esg2");
Media media = (Media) session.getAttribute("media");
%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>コメント投稿</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mediaComment.css">

</head>
<body>


<!-- 共通ヘッダー -->
<header>
    <div class="header-top">
        <h1><a href="Main">ForzApp</a></h1>

        <div class="header-link">
            <a href="media">メディア画面へ</a>
            <button class="hamburger" onclick="toggleMenu()">☰</button>
            <jsp:include page="/WEB-INF/jsp/header.jsp" />
        </div>
    </div>
</header>

<div class="container">

    <h1 class="page-title">コメント投稿</h1>

    <div class="form-card">

<form action="MediaCommentServlet" method="post">

    <div class="form-group">
        <label>コメント</label>

        <textarea name="comment" rows="8"
            placeholder="コメントを入力してください（改行OK）"><%=request.getParameter("comment") != null ? request.getParameter("comment") : ""%></textarea>

        <% if (s2 != null && !s2.trim().isEmpty()) { %>
            <div class="error-message"><%=s2%></div>
        <% } %>
    </div>

    <% if (media != null) { %>
        <input type="hidden" name="IID" value="<%=media.getId()%>">
    <% } %>

    <!-- ★ここがポイント -->
    <div class="btn-row">
            <div class="back-area">
		         <a href="ArticleContentServlet?id=<%=media.getId()%>" class="back-btn">
		            戻る
		        </a>		
		    </div>

        <button type="submit">投稿する</button>
    </div>

</form>



    </div>

</div>


</body>
</html>