<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="login.com.LoginUserBean" %>
<%
    LoginUserBean bean = (LoginUserBean)session.getAttribute("user_db");
    if (bean != null && "login".equals(session.getAttribute("login_db"))) {
%>
    <header>
        <nav>
            <ul>
                <li>ようこそ「<%= bean.getName()%>」さん</li>
                <li><a href="/ECサイト作成/history">購入履歴</a></li>
                <form action="shopping/buy" method="get">
                <li><a href="/ECサイト作成/shopping?cart=select">カート一覧</a></li>
                </form>
                <li><a href="/ECサイト作成/login">ログアウト</a></li>
            </ul>
        </nav>
    </header>
<%
    }
%>
