<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="pack.member.MemberBean" %>
<jsp:useBean id="dbInsert" class="pack.jdbc.DBInsert" />
<jsp:useBean id="memberBean" class="pack.member.MemberBean" />
<jsp:setProperty name="memberBean" property="*"/>
<%
String res = "";
if(dbInsert.memInsert(memberBean)){
	res = "회원가입 완료!";
} else{%>
	<script>
	alert("값이 중복되었습니다.");
	history.back();
	</script>
<% }
%>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<title>Document</title>
		<link rel="stylesheet" href="/style/style.css">
		<style>
		div#wrap{
			width:300px; padding:10px; border-radius:6px; margin:30px auto;text-align: center;}
		h1{margin:20px 0;}
		button{padding: 15px; font-size: 20px; color:#fff; background-color: rgba(51, 170, 255, 0.7);cursor: pointer;}
		</style>
	</head>
	<body>
		<div id="wrap">
			<h1><%=res %></h1>
			<button onclick="location.href='../index.jsp'">메인페이지로</button>
		</div>
		<!-- div#wrap -->
	</body>
</html>