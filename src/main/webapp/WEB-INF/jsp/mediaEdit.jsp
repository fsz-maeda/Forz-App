<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Media" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%
Media editMedia = (Media) request.getAttribute("editMedia");
%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>記事の編集</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mediaEdit.css">

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

    <h1 class="page-title">記事の編集</h1>

    <form action="MediaEditServlet" method="post" class="edit-form">

        <input type="hidden" name="id" value="<%= editMedia.getID() %>">

        <div class="form-group">
            <label for="title">タイトル</label>
            <input type="text" id="title" name="title"
                   value="<%= editMedia.getTitle() %>" required>
        </div>

        <div class="form-group">
            <label for="content">本文</label>
            <textarea id="content" name="content" rows="12" required><%= editMedia.getContent() %></textarea>
        </div>

        <div class="btn-area">
            <button type="submit">更新する</button>
        </div>

    </form>
</div>


</body>
</html>