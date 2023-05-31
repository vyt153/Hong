<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="encBean" class="pack.security.Encryption" />
<%
	String rndValue = String.valueOf(Math.random());
	String encData = encBean.encrypt(rndValue, session);
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>로그인</title>
		<link rel="stylesheet" href="/style/style_Common.css">
		<link rel="stylesheet" href="/style/style_Template.css">
		<link rel="stylesheet" href="/style/style_Member.css">
		<script src="/resource/jquery-3.6.0.min.js"></script>
	</head>
	<body>
		<div id="wrap">
			<main id="main" class="loginPage dFlex autoH">
				<div class="logoArea">
					<a href="/index.jsp">
	    				<img src="/images/logo.png" alt="헤더로고이미지">
	    			</a>
				</div>
				<!-- 실제 작업 영역 시작 -->
				<div class="loginDiv">
					<form action="" id="loginFrm" name="loginFrm">
						<div id="loginArea">
							<div id="loginInput">
								<input type="text" name="admId" placeholder="관리자 아이디 입력" id="uid"/>
								<input type="text" name="admPw" placeholder="관리자 비밀번호 입력" id="upw"/>
							</div>
							<button type="button" id="loginBtn">관리자 로그인</button>
						</div>
						<!-- div#loginArea -->
						<input type="hidden" name="chkAuth" value="<%=encData %>" />
					</form>
				</div>
				<!-- 실제 작업 영역 끝 -->
			</main>
		</div>
		<!-- div#wrap -->
		<script>
		$(function(){
			// 로그인 버튼 전송
			$("#loginBtn").click(function(){
				fnLoginSbm();
			});
			
			// 엔터키 이벤트
			$(window).keydown(function(){
				let code = event.keyCode;
				if(code==13) return false;
			});
			$(window).keyup(function(){
				let code = event.keyCode;
				if(code==13) fnLoginSbm();
			});
			
			function fnLoginSbm(){
				let uid = $("#uid").val().trim();
				$("#uid").val(uid);
				let upw = $("#upw").val().trim();
				$("#upw").val(upw);
				
				if (uid == "") {
					alert("아이디를 입력해주세요.");
					$("#uid").focus();
					return;
				} else if (upw == "") {
					alert("비밀번호를 입력해주세요.");
					$("#upw").focus();
					return;
				} else {			
					$("#loginFrm").attr("action", "admLoginProc.jsp");
					$("#loginFrm").submit();
				}
			}
			
		})
		</script>
	</body>
</html>