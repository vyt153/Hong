<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="aMgr" class="pack.admin.AdminMgr" />
<script>
<%
String uidSession = (String) session.getAttribute("uidSession");
if(uidSession!=null){
	if(aMgr.updateBoard(request)==1){%>
	alert("게시글 수정이 완료되었습니다.");
	location.href = "/admin/bbsList.jsp?nowPage1&keyField=&keyWord=";
<%	} else{%>
	alert("게시글 수정이 실패하였습니다.");
	history.back();
<%}
} %>
</script>