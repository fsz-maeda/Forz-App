<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>プロフィール写真変更</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/changePhoto.css">

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
	<h2>📸 プロフィール写真変更</h2>
</div>

<div class="form-wrapper">

	<div class="form-card">

		<form action="${pageContext.request.contextPath}/UploadPhotoServlet"
		      method="post"
		      enctype="multipart/form-data">

			<label>写真を選択してください</label>

			<input type="file" name="photo" accept="image/*">

			<button type="submit">アップロード</button>

		</form>

	</div>

</div>

</body>
</html>