<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="login.com.LoginUserBean" %>
<% LoginUserBean bean = (LoginUserBean)request.getAttribute("newUserInfo"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/style.css">
	<title>新規ユーザ登録面(確認)</title>
</head>
<body>
		<header><nav></nav></header>
		<section>
			<h1>新規ユーザー登録確認</h1>
			<p>お名前、ログインID、パスワードを確認し、「新規ユーザ登録実行」ボタンを押してください。</p>
			<form action="login" method="post">
				<table>
					<tr>
						<th>お名前</th>
						<td><%=bean.getName()%></td>
					</tr>
					<tr>
						<th>ログインID</th>
						<td><%=bean.getUserId()%></td>
					</tr>
					<tr>
						<th>パスワード</th>
						<td><%=bean.getPassword()%></td>
					</tr>
				</table>
				<input type="hidden" name="name" value="<%=bean.getName()%>">
				<input type="hidden" name="userId" value="<%=bean.getUserId()%>">
				<input type="hidden" name="password" value="<%=bean.getPassword()%>">

				<div class="aline-center">
					<input type="button" onclick="location.href='login?useradd=newadd'"  value="戻る">
					<input type="submit" name="submit" value="新規ユーザ登録実行">
				</div>

			</form>
		</section>
		<footer></footer>
</body>
</html>