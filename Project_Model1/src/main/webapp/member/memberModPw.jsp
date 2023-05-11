<%@page import="pack.member.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="mMgr" class="pack.member.MemberMgr"/>
<%
	String uidSession = (String)session.getAttribute("uidSession");
	if(uidSession==null) response.sendRedirect("/index.jsp");
	MemberBean mBean = mMgr.modifyMember(uidSession);
%>
<%@ include file="/ind/topTmp.jsp" %>
<script>
$(function(){
	$("title").text("비밀번호 변경");
	$("button").click(function () {
		if($("input").val()==""){
			alert("기존 비밀번호를 입력해주세요");
		} else if($("#newPw").val()==""){
			alert("변경하실 비밀번호를 입력해주세요");
		}else if($("#newPw").val().trim()!=$("#newPwChk").val().trim()){
			alert("새 비밀번호가 일치하지 않습니다.");
		} else{
			$("form").attr("action","memberModPwProc.jsp");
			$("form").submit();
		}
	})
});
</script>
<style>
table{border-collapse: collapse; margin: 10px auto;}
td{padding: 10px; border: 1px solid #aaa;}
input{padding: 10px;}
button{padding: 10px; color: #fff;background-color: #50b639; width: 200px; border: none; cursor: pointer;}
#contents{text-align: center;}
</style>
<!--  헤더템플릿 시작, iframe으로 변경 -->
<%@ include file="/ind/headerTmp.jsp" %>
  	<!--  헤더템플릿 끝 -->    
  <main id="main" class="dFlex">
    		<div id="innerWrap" class="dFlex">
    		<div id="lnb">
	    		<!--  메인 LNB 템플릿 시작 -->
				<%@ include file="/ind/mainLnbTmp.jsp" %>
	    		<!--  메인 LNB 템플릿 끝 -->    	
    		</div>
    		
	    	<!-- 실제 작업 영역 시작 -->
    		<div id="contents">
			<form>
				<ul>
					<li><%=uidSession %> 님 비밀번호 수정</li>
				</ul>
				<table>
					<tr>
						<td>현재 비밀번호</td>
						<td>
						<input type="text" name="pw"/>
						</td>
					</tr>
					<tr>
						<td>새 비밀번호</td>
						<td>
						<input type="text" id="newPw" name="newPw"/>		
						</td>
					</tr>
					<tr>
						<td>새 비밀번호 확인</td>
						<td>
						<input type="text" id="newPwChk" name="newPwChk"/>
						</td>
					</tr>
				</table>
				</form>
			<div>
				<button type="button">변경</button>
			</div>
			</div>
 		</div>	
    	</main>
    	<!--  main#main  -->
    
<!--  푸터템플릿 시작 -->
<%@ include file="/ind/footerTmp.jsp" %>
<!--  푸터템플릿 끝 -->  