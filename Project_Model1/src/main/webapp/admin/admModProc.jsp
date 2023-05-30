<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="admMgr" class="pack.admin.AdminMgr" />    
<jsp:useBean id="admBean" class="pack.admin.AdminBean" />
<jsp:setProperty name="admBean" property="*" />
<script>
<% 
if(admMgr.modAdm(admBean)){%>
	alert("수정이 완료되었습니다.");
	location.href = "/admin/admMgr.jsp";
<%} else{%>
	alert("관리자 정보를 올바르게 입력해주세요.");
	history.back();
<%}%>

</script>    