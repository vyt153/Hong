<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="changeID"  class="pack.change.ChangeID"/>
<jsp:useBean id="changePw"  class="pack.change.ChangePw"/>
<jsp:useBean id="changeEmail"  class="pack.change.ChangeEmail"/>
<jsp:useBean id="quit"  class="pack.change.Quit"/>
<jsp:useBean id="memberBean"  class="pack.member.MemberBean"/>
<jsp:setProperty name="memberBean" property="*" />
<script>
<%
int id = Integer.parseInt(request.getParameter("id"));
String uid = (String)session.getAttribute("id");
if(uid!=null){
	if(id==1){
		if(changeID.memUpdate(uid, memberBean)){%>
			alert("아이디가 변경되었습니다.");
			location.href="../member/logoutProc.jsp";
		<%}else{%>
			alert("일치하는 회원정보가 없습니다.");
			history.back();
		<%}
	} else if(id==2){
		if(changePw.memUpdate(uid, memberBean)){%>
		alert("비밀번호가 변경되었습니다.");
		location.href="../member/logoutProc.jsp";
	<%} else{%>
			alert("일치하는 회원정보가 없습니다.");
			history.back();
		<%}
	} else if(id==3){
		if(changeEmail.memUpdate(uid, memberBean)){%>
			alert("이메일이 변경되었습니다.");
			location.href="../member/logoutProc.jsp";
	<%} else{%>
			alert("일치하는 회원정보가 없습니다.");
			history.back();
		<%}
	} else if(id==4){
		if(quit.memUpdate(uid, memberBean)){%>
			alert("회원 탈퇴되었습니다.");
			location.href="../member/logoutProc.jsp";
		<%} else{%>
			alert("일치하는 회원정보가 없습니다.");
			history.back();
		<%}
	}
} else{%>
	alert("세션이 만료되었습니다.");
	location.href="../index.jsp";
<%}%>

</script>