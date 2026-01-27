<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//セッションスコープからselectを取得
	String select=(String)session.getAttribute("select");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メモアプリ</title>
</head>
<body>
	<% if( select.equals("編集")){  %>
		<h1>メモアプリ登録</h1>
		<p>編集完了しました</p>
		<a href="MainServlet">メインへ</a>
	<% } else if( select.equals("削除")){ %>
		<h1>メモアプリ削除</h1>
		<p>削除完了しました</p>
		<a href="MainServlet">メインへ</a>
	<% } else if( select.equals("追加")){ %>
		<h1>メモアプリ追加</h1>
		<p>追加完了しました</p>
		<a href="MainServlet">メインへ</a>
	<% } else {  %>
		<h1>アカウント削除</h1>
		<p>アカウント削除、完了しました</p>
		<a href="index.jsp">トップへ</a>		
	<% } %>	
	
</body>
</html>