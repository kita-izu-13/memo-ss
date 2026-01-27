<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="model.Form" %>
<% 
	//セッションスコープからメモリストを取得
	Form form=(Form)session.getAttribute("form");
	String userId=(String)session.getAttribute("userId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メモアプリ</title>
</head>
<body>
	<p>ようこそ！　<c:out value="${userId}" />　さん</p>
	<a href="MainServlet">メインへ</a>
</body>
</html>