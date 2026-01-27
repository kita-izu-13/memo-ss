<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メモアプリ</title>
</head>
<body>
	<h1>メモアプリ登録</h1>
	<p>ユーザー登録が完了しました</p>
	<p>登録ありがとう！　<c:out value="${userId}" /> さん</p>
	<a href="index.jsp">トップへ</a>
</body>
</html>