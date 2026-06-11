<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<h3>自己紹介編集</h3>

<hr>

<form action="${pageContext.request.contextPath}/EditIntroServlet" method="post">

    <p>About yourself:</p>

    <textarea name="intro" rows="8" cols="60" style="resize:none;">
${employee.intro}
    </textarea>

    <br><br>

    <input type="submit" value=" Save Changes">

</form>

<hr>