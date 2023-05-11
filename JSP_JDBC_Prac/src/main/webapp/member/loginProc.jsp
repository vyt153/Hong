<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="dbLogin"  class="pack.jdbc.DBLogin"/>
<jsp:useBean id="memberBean"  class="pack.member.MemberBean"/>
<jsp:setProperty name="memberBean" property="*" />
<% 
if(dbLogin.memSelect(memberBean)) {
	session.setAttribute("id", memberBean.getUid());
	session.setMaxInactiveInterval(60);
	response.sendRedirect("../index.jsp");
} else{%>
<script>
alert("일치하는 회원정보가 없습니다");
history.back();
</script>
<%} %>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<title>Document</title>
		<link rel="stylesheet" href="/style/style.css">
		<style>
		div#wrap{
			width:600px; padding:10px; border:3px solid #aaa; border-radius:6px; margin:30px auto;}
		hr{margin:20px 0;}
		</style>
	</head>
	<body>
		<div id="wrap">
			<h1></h1>
			<hr>
		</div>
		<!-- div#wrap -->
	</body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script>
	$(function(){

	});
	</script>
</html>