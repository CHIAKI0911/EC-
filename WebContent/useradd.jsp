<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/style.css">
	<title>新規ユーザー登録画面</title>
	</head>
	<body>
		<header><nav></nav></header>
		<section>
			<h1>新規ユーザー登録</h1>
			<p>お名前、ログインID、パスワードを入力し、「新規ユーザー登録確認」ボタンを押してください。</p>
			<form action="login" method="post">
				<table>
					<tr>
						<th>お名前</th>
						<td><input type="text" name="userName"></td>
					</tr>
					<tr>
						<th>ログインID</th>
						<td><input type="text" name="userId"></td>
					</tr>
					<tr>
						<th>パスワード</th>
						<td><input type="text" name="password"></td>
					</tr>
				</table>
				<div class="aline-center">
					<input type="button" onclick="location.href='login'"  value="戻る">
					<input type="submit" name="submit" value="新規ユーザー登録確認">
				</div>
			</form>
		</section>
		<footer></footer>
	</body>
</html>