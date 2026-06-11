<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h2>📸 Change Profile Photo</h2>

<form action="${pageContext.request.contextPath}/UploadPhotoServlet"
      method="post"
      enctype="multipart/form-data">

    <input type="file" name="photo">

    <br><br>

    <input type="submit" value="Upload">

</form>