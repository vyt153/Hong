<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 내용 보기</title>
<style>
	div#wrap {
	width: 600px;
	padding: 20px;
	border: 3px solid #aaa;
	border-radius: 6px;
	margin: 10px auto;
	}
	hr {
	margin: 10px;
	}
	table#memberTbl td {
	padding: 10px;
	}
	table#memberTbl td:first-child {
	width: 120px;
	font-size: 17px;
	text-align: right:
	}
	table#memberTbl td:last-child {
	width: 400px;
	}
	a {
	font-size: 18px;
	padding: 4px 10px;
	cursor: pointer;
	}
</style>
</head>
<body>
	<div id="wrap">
		<h1>상세 내용 보기 화면</h1>
		<hr>
		<table id="memberTbl">
			<tbody>
				<tr>
					<td>아이디</td>
					<td>${data.uid}</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td>${data.upw}</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>${data.uname}</td>
				</tr>
				<tr>
					<td>Email</td>
					<td>${data.uemail}</td>
				</tr>
			</tbody>
		</table>
		<a href="/list">목록으로</a>
	</div>
	 <!-- div#wrap -->
</body>
</html>