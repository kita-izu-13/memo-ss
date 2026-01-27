<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Memo,java.util.List,java.sql.Date,java.util.List,dao.MemosDAO" %>
<%
	//セッションスコープからメモリストを取得
	List<Memo> memoList=(List<Memo>)session.getAttribute("memoList");
	String radio=(String)session.getAttribute("radio");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メモアプリへようこそ</title>
<script src="<%= request.getContextPath() %>/action.js" defer></script>
</head>
<body>
	<h1>メモアプリへようこそ</h1>
	<form action="MainServlet" method="post">
		<input type="submit" name="select" value="追加">
		<input type="submit" name="select" value="編集">
		<input type="submit" name="select" value="削除">
		<input type="submit" name="select" value="検索">
		　　　
		<input type="submit" name="select" value="アカウント削除">
		　　　
		<a href="Logout" onclick="return logoutCheck();">ログアウト</a>
		<br>
		<br>
			
		<h2>☆　メモ一覧　☆</h2>
		<p>　上↑のボタンで、メモの追加・編集・削除・編集ができるよ！</p>
		<p>　</p>	
		
		<% if(radio!=null){ %>
		
			<% if(radio.equals("ID")){ %>
				<input type="radio" name="radio" value="ID" checked>作成順
				<input type="radio" name="radio" value="DATE" > 日付順
				<input type="radio" name="radio" value="TITLE" >タイトル順
				<input type="radio" name="radio" value="MEMO" >メモ順
				 
			<% } else if(radio.equals("DATE")){ %>
				<input type="radio" name="radio" value="ID">作成順
				<input type="radio" name="radio" value="DATE" checked>日付順
				<input type="radio" name="radio" value="TITLE" >タイトル順
				<input type="radio" name="radio" value="MEMO" >メモ順
				 
			<% } else if(radio.equals("TITLE")){ %>
				<input type="radio" name="radio" value="ID">作成順
				<input type="radio" name="radio" value="DATE">日付順
				<input type="radio" name="radio" value="TITLE" checked>タイトル順
				<input type="radio" name="radio" value="MEMO" >メモ順	
					
			<% }  else if(radio.equals("MEMO")){ %>
				<input type="radio" name="radio" value="ID">作成順
				<input type="radio" name="radio" value="DATE">日付順
				<input type="radio" name="radio" value="TITLE">タイトル順
				<input type="radio" name="radio" value="MEMO" checked>メモ順	
			<% } %>
			 
		<% } else { %>
			<input type="radio" name="radio" value="ID" checked>作成順
			<input type="radio" name="radio" value="DATE" > 日付順
			<input type="radio" name="radio" value="TITLE" >タイトル順
			<input type="radio" name="radio" value="MEMO" >メモ順
		<% } %>
		　　　
		<input type="submit" name="order" value="昇順">
		<input type="submit" name="order" value="降順">
	</form>
	
	<br> 
	 <p>日　付　：　タイトル　：　メ　モ</p>
	<% for (Memo memo : memoList) { %>
				<p><%= memo.getId() %><%= memo.getDate() %>：<%= memo.getTitle() %>：<%= memo.getMemo() %></p>
	<% } %>
</body>
</html>
