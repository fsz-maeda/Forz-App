<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>部署内メディア一覧</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/media.css">

</head>
<body>

<div class="container">
    <h1>部署内メディア一覧</h1>

    <!-- 絞り込みフォーム -->
    <form action="SearchCategoryServlet" method="get" class="filter-form">
        <label for="searchCategory">カテゴリーで絞り込み</label>

        <select name="searchCategory" id="searchCategory">

            <option value="all" ${selectedType == 'all' ? 'selected' : ''}>すべて表示</option>
            <option value="業務ナレッジ" ${selectedType == '業務ナレッジ' ? 'selected' : ''}>業務ナレッジ</option>
            <option value="部署内連絡・進歩共有" ${selectedType == '部署内連絡・進歩共有' ? 'selected' : ''}>部署内連絡・進歩共有</option>
            <option value="メンバーシップ・相互理解" ${selectedType == 'メンバーシップ・相互理解' ? 'selected' : ''}>メンバーシップ・相互理解</option>
            <option value="その他" ${selectedType == 'その他' ? 'selected' : ''}>その他</option>

        </select>

        <button type="submit" class="btn btn-primary">絞り込む</button>
    </form>

    <!-- テーブル -->
    <div class="table-container">
        <table>
            <thead>
                <tr>
                    <th style="width: 20%;">カテゴリ</th>
                    <th style="width: 20%;">投稿日時</th>
                    <th style="width: 30%;">タイトル</th>
                    <th style="width: 15%;">投稿者</th>
                </tr>
            </thead>

            <tbody>
                <c:choose>
                    <c:when test="${not empty medialist}">
                        <c:forEach var="media" items="${medialist}" varStatus="status">
                            <tr>
                                <td>
                                    <span class="badge">${media.mediaType}</span>
                                </td>

                                <td style="color: var(--text-muted); font-size: 14px;">
                                    ${media.mediaDate}
                                </td>

                                <td>
                                    <a href="ArticleContentServlet?id=${media.id}" class="article-link">
                                        ${media.title}
                                    </a>
                                </td>

                                <td>
                                    <span class="contributor-name" title="${nameList[status.index]}">
<!--                                    メディアの投稿者IDと従業員のIDが同じなら名前を表示する-->
                                    	<c:forEach var="employee" items="${employeeList}">
                                    		<c:if test="${media.employeeId == employee.employeeId}">
                                    			${employee.name}
                                    		</c:if>
                                    	</c:forEach>
                                    </span>
                                </td>
                            </tr>

                        </c:forEach>
                    </c:when>

                    <c:otherwise>
                        <tr>
                            <td colspan="4" class="no-data">
                                表示する記事がありません。
                            </td>
                        </tr>
                    </c:otherwise>

                </c:choose>

            </tbody>
        </table>
    </div>

    <!-- ナビ -->
    <div class="nav-links">
        <a href="Main" class="btn btn-secondary">← メイン画面へ</a>
        <a href="MediaPostServlet" class="btn btn-primary">新規投稿</a>
    </div>

</div>

</body>
</html>