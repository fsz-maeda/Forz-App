<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Group</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/createGroup.css">

</head>

<body>

<header>
	<div class="header-top">
		<h1><a href="Main">ForzApp</a></h1>
		<div class="header-link">
			<a href="GroupChatServlet">← Back</a>
			<button class="hamburger" onclick="toggleMenu()">☰</button>
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
		</div>
	</div>
</header>

<div class="form-page">

	<div class="form-card">

		<h2>👥 グループ作成</h2>

		<form action="CreateGroupServlet" method="post">

			<label>グループ名</label>

			<input type="text"
			       name="groupName"
			       placeholder="例：開発チーム"
			       required>

			<button type="submit">作成</button>

		</form>

	</div>

</div>

</body>
</html>