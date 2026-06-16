<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>自己紹介編集</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/editIntro.css">

</head>

<body>

<header>
	<div class="header-top">
		<h1><a href="Main">ForzApp</a></h1>
		<div class="header-link">
			<a href="MyProfileServlet">← プロフィールへ戻る</a>
		</div>
	</div>
</header>

<div class="page-title">
	<h2>📝 自己紹介編集</h2>
</div>

<div class="form-wrapper">

	<div class="form-card">

		<form action="${pageContext.request.contextPath}/EditIntroServlet" method="post">

			<label>About yourself</label>

			<textarea name="intro" rows="8" required>${employee.intro}</textarea>

			<button type="submit">保存する</button>

		</form>

	</div>

</div>

</body>
</html>