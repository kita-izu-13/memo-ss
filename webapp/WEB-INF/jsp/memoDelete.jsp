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
	
	<h2>☆　削除モード　☆</h2>
	<p>削除したいメモを選んで、削除ボタンを押してね！</p>
	<p>複数選択可能だよ～</p>
	<br>
	<br>
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
	
	<form action="MainServlet" method="post">
		<input type="submit" value="削除">
		<input type="submit" name="select" value="キャンセル">			 
			  <%--　削除ボタンが押されたら、チェックボックス表示しつつメモ表示（id取得）。 
			  　　　チェックボックスで選択して削除ボタン押したら、削除	--%>
		<p>　日　付　：　タイトル　：　メ　モ</p>
		 	 <% for (Memo memo : memoList) { %>
				 <p><input type="checkbox" name="delete" value="<%= memo.getId() %>"><%= memo.getDate() %>：<%= memo.getTitle() %>：<%= memo.getMemo() %></p>
			<% } %>
			<input type="submit" value="削除">
	</form>

</body>
</html>
 