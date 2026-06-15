<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="sideMenu" class="main-menu">
    <h2>📋 メニュー</h2>
    <ul>
        <li><a href="event">🎉 社内イベント</a></li>
        <li><a href="dailyReportPage">📝 日報</a></li>
        <li><a href="media">🏢 部署コミュニティ</a></li>
        <li><a href="EmployeeListServlet">👥 社員一覧</a></li>
        <li><a href="ChatServlet">💬 Chat</a></li>
        <li><a href="MyProfileServlet">👤 プロフィール</a></li>
        <li><a href="application">📃 各種申請</a></li>
        <li><a href="salary">💰 給料ページ</a></li>

        <c:if test="${loginUser.employeeId == 1 || loginUser.management == true}">
            <li><a href="admin">⚙ 管理者ページ</a></li>
        </c:if>
    </ul>
</div>

<script>
function toggleMenu() {
    document.getElementById("sideMenu").classList.toggle("open");
}
</script>

<style>
.main-menu {
    position: fixed;
    top: 0;
    left: -260px; /* 初期は隠す */
    width: 260px;
    height: 100%;
    background: #fff;
    transition: 0.3s;
    box-shadow: 2px 0 5px rgba(0,0,0,0.2);
    z-index: 1000;
}

.main-menu.open {
    left: 0;
}

.hamburger {
    position: fixed;
    top: 10px;
    left: 10px;
    font-size: 24px;
    z-index: 1100;
}
</style>