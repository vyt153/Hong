<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="cMgr" class = "pack.comment.ComMgr"/>
<jsp:useBean id = "cBean" class = "pack.comment.ComBean"/>
<jsp:setProperty name = "cBean" property="*"/>

<%
String uidSession = (String) session.getAttribute("uidSession");
int num = Integer.parseInt(request.getParameter("num"));

String keyField = request.getParameter("keyField");
String keyWord = request.getParameter("keyWord");
String nowPage = request.getParameter("nowPage");

String url = "/bbs/read.jsp?nowPage="+nowPage;
url += "&keyField="+keyField;
url += "&keyWord="+keyWord;
url += "&gnbParam=bbs";
url += "&num="+num;
%>
<script>
<%if(uidSession!=null){
	if(cMgr.insertComment(cBean)){%>
	location.href = "<%=url%>";
<%}
} else{ %>
	alert("세션이 만료되었습니다. 다시 로그인해주세요.");
	location.href = "/member/login.jsp";
	<%}%>
</script>