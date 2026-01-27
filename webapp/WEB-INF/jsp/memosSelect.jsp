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
	
	<h2>☆　検索モード　☆</h2>
	<p>検索したい項目を選択・キーワードを入力して、検索ボタンを押してね！</p>
	　　　　　<a href="Logout" onclick="return logoutCheck();">ログアウト</a>
	<p></p>
	<br>
	<form action="MainServlet" method="post">
		<input type="radio" name="selectr" value="TITLE">タイトル
		<input type="radio" name="selectr" value="MEMO" checked>メモ
		<br>
		<textarea name="selecttext"></textarea>
		<input type="submit" value="検索">
		<input type="submit" name="select" value="キャンセル">			 
		<p>日　付　：　タイトル　：　メ　モ</p>
		 	 <% for (Memo memo : memoList) { %>
				 <p><%= memo.getDate() %>：<%= memo.getTitle() %>：<%= memo.getMemo() %></p>
			<% } %>
			<input type="submit" value="検索">
	</form>
	<a href="MainServlet">メイン画面へ</a>
</body>
</html>