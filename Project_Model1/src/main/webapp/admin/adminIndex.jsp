<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
<%
String admSession = (String)session.getAttribute("admSession");
String level = (String)session.getAttribute("level");
if(admSession==null||admSession.equals("")){
response.sendRedirect("/admin/admLogin.jsp");%>
	alert("세션이 만료되었거나, 유효하지 않은 접근입니다.");
	location.href = "/admin/admLogin.jsp";
<%} else{%>
	

</script>
<%@ include file="/ind/topTmp.jsp" %>
<style>
/* 관리자 메인에만 필요한 css 시작 */
#wrap {min-width: 1600px !important;}
#post > div + div {
    margin: 4px 0 !important;
}
div.bbsList tr.prnTr>td.subjectTd > img {width: 16px; height: 16px;}
/* 관리자 메인에만 필요한 css 끝 */
#iconSample {
    width: 13px;
    height: 13px;
    color: #fff;
    font-size: 11px;
    text-align: center;
    line-height: 13px;
    border-radius: 50%;
    background-color: #f44;
    display: inline-block;
    transform: translate(2px, -2px);
}
div.bbsList tr.adminTr>td {
	text-align: center;
	padding: 20px 2px 10px;
	border-bottom: 1px solid #d2d2d2;
	color: #aaa;
	font-weight: bold;
}

div.bbsList tr.adminTr>td.subjectTd {
	text-align: left;
}
div#inner{
	width: 1800px;
	margin: 0 auto;
}
#lnb{height: 340px;}

#main.adminMain {padding: 50px 50px 100px;}
.dFlex.adminMain {flex-wrap: wrap; flex: 1; align-content: stretch; justify-content: space-between;}
#main .contentsClass {width: 48% !important; margin: 10px !important;}

</style>
<script>
$(function () {
	$("title").text("관리자 메인페이지");
})
</script>
<link rel="stylesheet" href="/style/style_BBS.css">
<script src="/script/script_BBS.js"></script>
 	
    	<!--  헤더템플릿 시작, iframe으로 변경 -->
		<%@ include file="/admind/headerTmp.jsp" %>
    	<!--  헤더템플릿 끝 -->    	
    	
    	
    	<main id="main" class="adminMain dFlex">
    		<div id="lnb">
	    		<!--  메인 LNB 템플릿 시작 -->
				<%@ include file="/admind/mainLnbTmp.jsp" %>
	    		<!--  메인 LNB 템플릿 끝 -->    	
    		</div>
    		<div class="dFlex adminMain">
    			<%@include file="/admind/admNotice.jsp" %>
    			<%@include file="/admind/bbslist.jsp"%>
   				<%@include file="/admind/adminUserInfoTbl.jsp" %>
    			<%@include file="/admind/gallerylist.jsp" %>
    		</div>
    	</main>
    	<!--  main#main  -->
    
        	   	
    	<!--  푸터템플릿 시작 -->
		<%@ include file="/ind/footerTmp.jsp" %>
    	<!--  푸터템플릿 끝 -->
<%}%>