<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>部署管理</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/manageDepartment.css">

</head>

<body>

<header>
	<div class="header-top">
		<h1><a href="admin">ForzApp</a></h1>
		<div class="header-link">
			<p><a href="admin">管理者ページへ</a></p>
		</div>
	</div>
</header>

<div class="word">
	<h2>🏢 部署管理</h2>
	<p>部署の追加・編集・削除</p>
</div>

<hr>

<div class="main">

	<!-- 左：追加 -->
	<div class="card">

		<h3>➕ 新規部署追加</h3>

		<form action="insertDepartment" method="post">
			<button type="submit" class="btn-primary">＋ 追加</button>
		</form>

	</div>

	<!-- 右：一覧 -->
	<div class="table-card">

		<h3>📋 既存部署一覧</h3>

		<table>

			<thead>
				<tr>
					<th>ID</th>
					<th>部署名</th>
					<th>修正</th>
					<th>削除</th>
				</tr>
			</thead>

			<tbody>
				<c:choose>
				    <c:when test="${empty departmentList}">
				        <tr>
				            <td colspan="4">
				                部署が登録されていません
				            </td>
				        </tr>
				    </c:when>
				
				    <c:otherwise>
				        <c:forEach var="department"
				                   items="${departmentList}">
				            <tr>
				                <td>${department.departmentId}</td>
				                <td>${department.departmentName}</td>
				
				                <td>
				                    <form action="updateDepartment" method="post">
				                        <input type="hidden" name="departmentId" value="${department.departmentId}">
				                        <button type="submit" class="btn-edit">
				                            修正
				                        </button>
				                    </form>
				                </td>
				
				                <td>
				                    <form action="deleteDepartment" method="post" onsubmit="return confirm('削除しますか？')">
				                        <input type="hidden" name="departmentId" value="${department.departmentId}">
				                        <button type="submit" class="btn-delete">
				                            削除
				                        </button>
				                    </form>
				                </td>
				            </tr>
				        </c:forEach>
				    </c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</div>

<!-- メッセージ -->
<c:if test="${insertDepartmentMsg != null}">
	<div class="message success">
		${insertDepartmentMsg}
	</div>
	<c:remove var="insertDepartmentMsg" scope="session"/>
</c:if>

<c:if test="${updateDepartmentMsg != null}">
	<div class="message info">
		${updateDepartmentMsg}
	</div>
	<c:remove var="updateDepartmentMsg" scope="session"/>
</c:if>

<c:if test="${deleteDepartmentMsg != null}">
	<div class="message danger">
		${deleteDepartmentMsg}
	</div>
	<c:remove var="deleteDepartmentMsg" scope="session"/>
</c:if>

<br>

</body>
</html>