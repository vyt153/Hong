<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="admMgr" class="pack.admin.AdminMgr" />
<jsp:useBean id="encBean" class="pack.security.Encryption" />
<%
String chkAuth = (String)session.getAttribute("chkAuth");
chkAuth = encBean.decrypt(chkAuth);
String encData = request.getParameter("chkAuth");
String decData = encBean.decrypt(encData);
String id = request.getParameter("admId");
String pw = request.getParameter("admPw");
String level = admMgr.getAdmLevel(id);

if(admMgr.login(id, pw)&&chkAuth.equals(decData)){
	session.setAttribute("admSession", id);
	session.setAttribute("level", level);
	response.sendRedirect("adminIndex.jsp");
} else{%>
<script>
	alert("입력하신 정보와 일치하는 관리자 계정이 없거나, 정상적인 접속이 아닙니다.");
	history.back();
</script>
<%}%>