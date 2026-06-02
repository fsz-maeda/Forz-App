<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>レポート</title>
</head>
<body>
	<h1>レポート</h1>
	
	<c:forEach var="report" items="${reportList}">
	    <p>${report.date} : ${report.content}</p>
	</c:forEach>
	
	<a href="Main">メイン画面へ</a>
	<a href="Home">ホーム画面へ</a>
</body>
</html>