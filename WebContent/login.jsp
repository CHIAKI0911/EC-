<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="style.css">
	<title>ログイン画面</title>
	</head>
	<body>
		<header><nav></nav></header>
		<section>
			<h1>ショッピングサイトへようこそ</h1>
			<p>ログインIDとパスワードを入力してください</p>
			<form action="login" method="post">
				<p>ログインID<input type="text" name="id"></p>
				<p>パスワード<input type="password" name="pass"></p>
				<p><input type="submit" value="ログイン" name="submit"></p>
			</form>
			<p style="margin-left:80px"><a href="login?useradd=newadd">新規ユーザー登録はこちら</a></p>
		</section>
		<footer></footer>
	</body>
</html>