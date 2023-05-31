<%@ page import = "pack.comment.ComBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id = "cMgr" class = "pack.comment.ComMgr"/>

<% request.setCharacterEncoding("UTF-8"); %>

<%
int cnum = Integer.parseInt(request.getParameter("cnum"));
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
out.print(url);
%>
<script>
<%  
cMgr.delCom(cnum);
%>

alert("댓글이 삭제되었습니다.");
location.href = url;

</script>