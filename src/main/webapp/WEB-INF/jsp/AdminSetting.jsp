<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


現在の締め日：${closeDay}

<form method="post" action="AdminSettingServlet">
    <input type="number" name="closeDay" value="${closeDay}">
    <button type="submit">更新</button>
</form>

</body>
</html>