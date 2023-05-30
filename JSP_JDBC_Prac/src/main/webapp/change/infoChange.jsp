<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<title>Document</title>
		<link rel="stylesheet" href="/style/style.css">
		<style>
		div#wrap{padding: 20px; width: 640px;}
		h1{text-align: center;}
		div#wrap>div{border: 3px solid #aaa; padding: 10px; text-align: center;}
		button{padding: 20px; width: 95%; font-size: 24px; margin: 10px; cursor: pointer;}
		</style>
	</head>
	<body>
		<div id="wrap">
			<h1>회원정보변경</h1>
			<div>
				<a href="changeId.html"><button id="idUpdate">아이디 변경</button></a> <br /><br />
				<a href="changePw.html"><button id="pwUpdate">비밀번호 변경</button></a>
				<a href="changeEmail.html"><button id="emailUpdate">이메일 변경</button></a>
			</div>
			<form></form>
		</div>
		<!-- div#wrap -->
	</body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script>
	</script>
</html>