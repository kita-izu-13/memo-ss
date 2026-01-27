<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メモアプリ</title>
</head>
<body>
	<h1>メモアプリへようこそ</h1>
	<h2>☆ ユーザー登録</h2>
	<form action="FormServlet" method="post">
		ユーザーID：<input type="text" name="userId"><br>
		パスワード：<input type="password" name="pass"><br>
		<input type="submit" value="登録">
	</form>
	<a href="index.jsp">トップへ</a>
</body>
</html>