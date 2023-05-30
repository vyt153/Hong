<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="admMgr" class="pack.admin.AdminMgr" />    
<script>
<% 
int num = Integer.parseInt(request.getParameter("num"));
if(admMgr.delAdm(num)){%>
	alert("삭제가 완료되었습니다.");
	location.href = "/admin/admMgr.jsp";
<%} else{%>
	alert("삭제에 실패하였습니다.");
	history.back();
<%}%>

</script>    