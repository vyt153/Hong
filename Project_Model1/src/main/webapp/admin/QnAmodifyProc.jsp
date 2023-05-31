<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="cBean" class="pack.csc.CscBean" />
<jsp:useBean id="cMgr" class="pack.csc.CscMgr"/>
<jsp:setProperty name="cBean" property="*" />
<script>
<%
//관리자 로그인 세션
String admSession = (String)session.getAttribute("admSession");
String contentType = request.getParameter("contentType");
if(admSession!=null){
	if(cMgr.updateBoard(cBean, contentType)==1){%>
	alert("Q&A 글 수정이 완료되었습니다.");
	location.href="/admin/QnAList.jsp?gnbParam=csc";
<%	} else{%>
	alert("Q&A 글 수정이 실패하였습니다.");
	history.back();
<%}
} else{%>
	alert("세션이 만료되었습니다. 다시 로그인해주세요.");
	location.href = "../admin/admLogin.jsp";
<% } %>
</script>