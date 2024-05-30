<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="Model.Memo"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/CommonCSS.css" rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>詳細画面</title>
</head>
<body>
	<header> 詳細画面 </header>
		<span id="real-time"></span>
	<main>
		<!--DBからメモ情報取得 -->
		<%
		Memo memo = (Memo) request.getAttribute("memo");
		%>
		<div class="title_div">
			<label>タイトル</label> <br>
			<!--メモタイトルを表示する -->
			<div class="edit_text">
				<%=memo.getMemoTitl()%></div>
		</div>
		<div class="content_div">
			<label>内容</label> <br>
			<!--メモ内容を表示する -->
			<div class="edit_text"><%=memo.getMemoCont()%></div>
		</div>
	
	</main>
	<footer>
	
					<!--編集ページに遷移-->
					<form action="./TranEditPageServlet" method="post">
						<button type="submit" name="memo_id" value="<%=memo.getMemoId()%>">編集</button>
					</form>
				
					<!--削除した上で、リストページに遷移-->
					<button type='button' id="openModal">削除</button>
					<section id="modalArea" class="modalArea">
						<div id="modalBg" class="modalBg"></div>
						<div class="modalWrapper">
							<div>
								<h1>本当に削除しますか。</h1>
								<form action="./DeleServlet" method="post">
									<button type="submit" name="memo_id" value="<%=memo.getMemoId()%>">はい</button>
								</form>
								<button class="closeModal">いいえ</button>
							</div>
						</div>
					</section>
			
					<!--リストページに遷移-->
					<form action="./DispListServlet" method="post">
						<button type="submit">もどる</button>
					</form>
		
				</footer>
					<script src="main.js"></script>
</body>
</html>