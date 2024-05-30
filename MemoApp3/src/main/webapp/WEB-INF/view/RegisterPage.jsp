<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Model.Memo"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/CommonCSS.css" rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>新規登録画面</title>
</head>
<body>
	<header> 新規登録画面 </header>
		<span id="real-time"></span>
	<main>
		<!--新規登録後、リストページに遷移-->
		<form action="RegisterServlet" method="post">
			<div class="title_div">
				<label>タイトル</label>
				<!--タイトルの入力欄-->
				<input type="text" name="memo_titl" maxlength="50">
			</div>
			<div class="content_div">

				<label>内容</label>
				<!--内容の入力欄-->
				<textarea type="text" name="memo_cont" maxlength="1000"></textarea>
			</div>
	
	</main>
	<footer>
			<button type='button' id="openModal">登録する</button>
					<section id="modalArea" class="modalArea">
						<div id="modalBg" class="modalBg"></div>
						<div class="modalWrapper">
							<div>
								<h1>本当に登録しますか。</h1>
								<form action="./TranRegiPageServlet" method="post">
									<button type="submit">はい</button>
								</form>
								<button class="closeModal">いいえ</button>
							</div>
						</div>
					</section>
					<!--リストページに遷移-->
					<form method="post" action="./DispListServlet">
						<button>やめる</button>
					</form>
	
	</footer>
	<span id="real-time"></span>
	<script src="main.js"></script>
</body>
</html>