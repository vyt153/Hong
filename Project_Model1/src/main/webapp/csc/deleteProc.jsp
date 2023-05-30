<%@page import="pack.csc.CscBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="cMgr" class="pack.csc.CscMgr"/>
<% request.setCharacterEncoding("UTF-8"); %>
<%
	String uidSession = (String)session.getAttribute("uidSession");
	String nowPage = request.getParameter("nowPage");
	String reqNum = request.getParameter("num");
	int numParam = Integer.parseInt(reqNum);
	
	String keyField = request.getParameter("keyField");
	String keyWord = request.getParameter("keyWord");
	
	CscBean bean =(CscBean)session.getAttribute("bean");
	
	String url = "/csc/QnA.jsp?gnbParam=csc&nowPage="+nowPage;
			url += "&keyField="+keyField;
			url += "&keyWord="+keyWord;
			url += "&menu=QnA";
%>
<script>
	<%if(uidSession!=null){
		int extCnt = cMgr.deleteBoard(numParam);%>
		alert("삭제되었습니다.");
		location.href = "<%=url%>";
<%	} else{%>
		alert("세션이 만료되었습니다. 다시 로그인해주세요.");
		location.href = "/member/login.jsp";
	<%}%>
</script>