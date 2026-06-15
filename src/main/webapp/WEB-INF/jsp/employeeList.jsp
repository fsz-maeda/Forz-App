<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>   
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員一覧</title>
</head>
<body>
<h2>社員一覧</h2>
	<form action="EmployeeListServlet" method="get">
		<input type="text"name="keyword"placeholder="社員名検索">
		<input type="submit"value="Search">
	</form>
	
	<c:choose>
	    <c:when test="${keyword == null}">
	    	<a href="Main">メインへ</a>
	    </c:when>
	    <c:otherwise>
	        <a href="EmployeeListServlet">戻る</a>
	    </c:otherwise>
	</c:choose>
	
	<hr>
	
	<c:forEach var="emp"  items="${employeeList}">
		<hr>
		社員ID : ${emp.employeeId}<br>
		名前 : ${emp.name}<br>
		
		<c:forEach var="department" items="${departmentDAO}">
			<c:if test="${department.departmentId == emp.department}">
				部署 :${department.departmentName}<br>
			</c:if>
		</c:forEach>
		
		<c:forEach var="position" items="${positionList}">
			<c:if test="${positionId == emp.position}">
				役職 :${position.positionName}<br>
			</c:if>
		</c:forEach>
			
		<a href="ProfileServlet?employeeId=${emp.employeeId}">Profile</a><br>
		<a href="ChatServlet?receiverId=${emp.employeeId}">Chat</a><br>
	</c:forEach>
</body>
</html>