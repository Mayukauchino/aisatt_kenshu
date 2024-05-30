<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="Model.Memo"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/CommonCSS.css" rel="stylesheet" type="text/css">
<meta charset="utf-8">
<script type="text/javascript">
<!--現在の日付を文字列で取得-->
	function Time() {
		var realTime = new Date();
		var hour = realTime.getHours();
		var minutes = realTime.getMinutes();
		var seconds = realTime.getSeconds();
		var text = hour + ":" + minutes + ":" + seconds;
		document.getElementById("real-time").innerHTML = text;
	}
	setInterval('Time()', 1000);
</script>
<title>一覧画面</title>
</head>
<body>
	<header> 一覧画面 </header>
	<!--現在時間を表示 -->
	<span id="real-time"></span>
	<main>
		<!--DBからメモリスト情報取得 -->
		 <% 
			List<Memo> memoList = (List<Memo>) request.getAttribute("memoList");
		 %>
		<!--メモの数分表示する -->
		<%
			for (Memo memo : memoList) {
				int tmp = memo.getDeleFlag();
				if (tmp == 0) {
			%>
		<div class="list_div">
			タイトル：<%=memo.getMemoTitl()%>
			<!--詳細ページに遷移-->
			<form action="TranDetaPageServlet" method="post">
				<button class="detail_button" type="submit" name="memo_id"
					value="<%=memo.getMemoId()%>">詳細</button>
			</form>
		</div>
		<%
			}
			}
			%>
	</main>
	<footer>
		<!--登録ページに遷移-->
		<form action="TranRegiPageServlet" method="post">
			<button type="submit">新規登録</button>
		</form
	</footer>
	<script src="main.js"></script>
</body>
</html>
