<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>メモアプリへようこそ</h1>
	<h2>！アカウント削除！</h2>
	<p>※アカウントを削除します。<br>
	 　メモデータも削除されますが、よろしいですか？</p>
 	<form action="MainServlet" method="post">
 		<input type="submit" name="AccountD" value="アカウント削除">
	</form>
	<a href="MainServlet">メイン画面へ</a>
</body>
</html>