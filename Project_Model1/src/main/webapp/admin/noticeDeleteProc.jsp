<%@page import="pack.bbs.BoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="admMgr" class="pack.admin.AdminMgr" />
<% request.setCharacterEncoding("UTF-8"); %>
<%
	String admSession = (String)session.getAttribute("admSession");
	String nowPage = request.getParameter("nowPage");
	String reqNum = request.getParameter("num");
	int numParam = Integer.parseInt(reqNum);
	
	String keyField = request.getParameter("keyField");
	String keyWord = request.getParameter("keyWord");
	
	String url = "/admin/admNotice.jsp?nowPage="+nowPage;
			url += "&keyField="+keyField;
			url += "&keyWord="+keyWord;
%>
<script>
	<%if(admSession!=null){
		if(admMgr.deleteNotice(numParam)){%>
		alert("삭제되었습니다.");
		location.href = "<%=url%>";
		<%}
	} else{%>
		alert("세션이 만료되었습니다. 다시 로그인해주세요.");
		location.href = "/member/login.jsp";
	<%}%>
</script>