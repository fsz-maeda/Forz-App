<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Media,java.util.List"%>
<%
List<Media> mediaList = (List<Media>)session.getAttribute("mediaList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>media</title>
</head>
<body>
<h1>media</h1><br>
<form action="SearchCategoryServlet" method="get">
    <label for="searchType">記事のカテゴリーで絞り込み：</label>
    <select name="searchCategory" id="searchCategory">
        <option value="all" <%= "all".equals(request.getAttribute("selectedType")) ? "selected" : "" %>>すべて表示</option>
        <option value="業務ナレッジ" <%= "業務ナレッジ".equals(request.getAttribute("selectedType")) ? "selected" : "" %>>業務ナレッジ</option>
        <option value="部署内連絡・進歩共有" <%= "部署内連絡・進歩共有".equals(request.getAttribute("selectedType")) ? "selected" : "" %>>部署内連絡・進歩共有</option>
        <option value="メンバーシップ・相互理解" <%= "メンバーシップ・相互理解".equals(request.getAttribute("selectedType")) ? "selected" : "" %>>メンバーシップ・相互理解</option>
        <option value="その他" <%= "その他".equals(request.getAttribute("selectedType")) ? "selected" : "" %>>その他</option>
    </select>
    <button type="submit">絞り込む</button>
</form>
<table border="1"style="border-collapse: collapse">
<tr>
<th>カテゴリ</th>
<th>投稿日時</th>
<th>タイトル</th>
</tr>
<%for (Media media : mediaList){%>
<td><%= media.getMediaType()%></td><td><%= media.getMediaDate()%></td><td><a href="ArticleContentServlet?id=<%= media.getId()%>"><%= media.getTitle()%></a></td><tr>
<%} %>
</table><br>
<a href="MediaPostServlet">新規投稿</a><br>
<a href="Main">メイン画面へ</a>
</body>
</html>