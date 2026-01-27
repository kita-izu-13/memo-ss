<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Memo,java.util.List,java.sql.Date,java.util.List,dao.MemosDAO" %>
<%
	//リクエストスコープからメモリストを取得
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
	
	<h2>☆　編集モード　☆</h2>
	<p>編集したいメモを選んで、編集ボタンを押してね！</p>
	<p></p>
	<form action="MainServlet" method="post">
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
		<a href="Logout" onclick="return logoutCheck();">ログアウト</a>
	<br>
	<br>
		<form action="MainServlet" method="post">
			<input type="submit" value="編集">
			<input type="submit" name="select" value="キャンセル">
			 		
		 	 <%--　更新ボタンが押されたら、ラジオボタン表示しつつメモ表示（id取得）。 
		 	 　　　ラジオボタンで選択して更新ボタン押したら、追加画面--%>
		 	<p>　日　付　：　タイトル　：　メ　モ</p>
			  <% for (Memo memo : memoList) { %>
				 <p><input type="radio" name="update" value="<%= memo.getId() %>"><%= memo.getDate() %>：<%= memo.getTitle() %>：<%= memo.getMemo() %></p>
			 <% } %>
			<input type="submit" value="編集">
		</form>	
</body>
</html>