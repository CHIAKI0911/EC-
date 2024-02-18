<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="style.css">
	<title>ログインエラー画面</title>
</head>
	<body>
		<header><nav></nav></header>
		<section>
			<h1>ログインエラー</h1>
			<p><%=(String)request.getAttribute("error_msg") %></p>
			<form action="login" method="get">
				<p><input type="submit" style="margin-left: 0px;" value="戻る" name="submit"></p>
			</form>
		</section>
		<footer></footer>
	</body>
</html>