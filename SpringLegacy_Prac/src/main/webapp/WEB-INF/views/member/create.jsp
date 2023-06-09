<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
table#memberTbl td input {
font-size: 17px;
padding: 4px 10px;
}
button {
font-size: 18px;
padding: 4px 10px;
cursor: pointer;
}
</style>
</head>
<body>
	<div id="wrap">	
		<h1>입력 양식 화면</h1>
		<hr />
		<%@ include file="../inc/headerGNB.jsp" %>
		<form method="post">
			<table id="memberTbl">
				<tbody>
					<tr>
						<td>아이디</td>			
						<td><input type="text" name="uid"/></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="upw"/></td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" name="uName"/></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><input type="text" name="uEmail"/></td>
					</tr>
				</tbody>
			</table>
			<button type="submit">전송</button>
			<button type="button">목록</button>
		</form>
	</div>
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script src="/resources/script/script.js"></script>
</body>
</html>