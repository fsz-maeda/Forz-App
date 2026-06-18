<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>新規投稿</title>


<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mediapost.css">
</head>

<body>

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

<div class="post-container">

    <h1 class="page-title">新規投稿</h1>

    <form action="MediaPostServlet" method="post" class="post-form">

        <!-- 部署 -->
        <div class="form-group">
            <label>部署</label>
            <select name="departmentId" required>
                <option value="">選んでください</option>
                <c:forEach var="department" items="${departmentList}">
                    <option value="${department.departmentId}">
                        ${department.departmentName}
                    </option>
                </c:forEach>
            </select>
        </div>

        <!-- カテゴリー -->
        <div class="form-group">
            <label>カテゴリー</label>
            <select name="category" required>

                <option value="businessknowledge">業務ナレッジ</option>
                <option value="contact">部署内連絡・進歩共有</option>
                <option value="membership">メンバーシップ・相互理解</option>
                <option value="others">その他</option>

            </select>
        </div>

        <!-- タイトル -->
        <div class="form-group">
            <label>タイトル</label>
            <input type="text" name="title" value="${param.title}" required />
            <c:if test="${not empty errorMsg2}">
                <div class="error">${errorMsg2}</div>
                <c:remove var="errorMsg2" scope="session"/>
            </c:if>
        </div>

        <!-- 本文 -->
        <div class="form-group">
            <label>本文</label>
            <textarea name="content" required>${param.content}</textarea>

            <c:if test="${not empty errorMsg3}">
                <div class="error">${errorMsg3}</div>
                <c:remove var="errorMsg3" scope="session"/>
            </c:if>
        </div>

        <c:if test="${not empty successMsg}">
		    <div class="success">${successMsg}</div>
		    <c:remove var="successMsg" scope="session"/>
		</c:if>

        <div class="btn-area">
        	<a href="media" class="back-btn">戻る</a>
            <button type="submit">投稿する</button>
        </div>

    </form>
</div>

</body>
</html>