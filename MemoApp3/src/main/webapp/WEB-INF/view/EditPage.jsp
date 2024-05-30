<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Model.Memo"%>

<!DOCTYPE html>
<html>
<head>
<link href="css/CommonCSS.css" rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>編集画面</title>
</head>
<body>
	<header> 編集画面 </header>
	<span id="real-time"></span>
	<main>
		<!--DBからメモ情報取得 -->
		<%
		Memo memo = (Memo) request.getAttribute("memo");
		%>
		<!--編集登録後、リストページに遷移-->
		<form action="EditServlet" method="post">
			<div class="title_div">
				<label>タイトル</label> <br>
				<!--メモタイトルを表示、タイトルの入力欄-->
				<input type="text" name="memo_titl" maxlength="50"
					value="<%=memo.getMemoTitl()%>">
			</div>
			<div class="content_div">
				<label>内容</label> <br>
				<!--メモ内容を表示し、内容の入力欄-->
				<textarea type="text" name="memo_cont" maxlength="1000"><%=memo.getMemoCont()%></textarea>
			</div>
			<!--メモIDを保持する-->
			<input type="hidden" name="memo_id" value="<%=memo.getMemoId()%>">
	</main>
	<footer>
			<!--登録ボタン-->
		<button type='button' id="openModal">登録する</button>
					<!--登録時の確認モーダルセッション-->
		<section id="modalArea" class="modalArea">
			<div id="modalBg" class="modalBg"></div>
			<div class="modalWrapper">
				<div>
					<h1>本当に登録しますか。</h1>
					<!--編集サーブレッドに遷移-->
					<form action="./TranEditPageServlet" method="post">
						<button type="submit" name="memo_id" value="<%=memo.getMemoId()%>">はい</button>
					</form>
					<!--モーダル閉じ-->
					<button class="closeModal">いいえ</button>
				</div>
			</div>
		</section>
		<!--リストページに遷移-->
		<form method="post" action="./DispListServlet">
			<button>やめる</button>
		</form>
	</footer>
	<script src="main.js"></script>
</body>
</html>