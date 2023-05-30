<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String admSession_HTMP = (String)session.getAttribute("admSession");
	String level_HTMP = (String)session.getAttribute("level");
%>

<script>
<%if(admSession_HTMP==null||level_HTMP==null){
%>
	alert("세션이 만료되었거나, 유효하지 않은 접근입니다.");
	location.href = "/admin/admLogin.jsp";
<%}%>
</script>	
<header id="header" class="dFlex">
	<!-- 로고, GNB -->
	<div id="innerWrap" class="dFlex">
		<div id="headerLogo">
			<a href="/admin/adminIndex.jsp"> <img src="/images/logo.png" alt="헤더로고이미지">
			</a>
		</div>

		<nav id="gnb">

			<ul id="mainMenu" class="dFlex">

				<li class="mainLi"><a href="/admin/adminIndex.jsp">HOME</a></li>
				<%if(level_HTMP.equals("Super")) {%>				
				<li class="mainLi"><a href="/admin/admMgr.jsp">관리자 관리</a></li>
				<%} %>
				<li class="mainLi"><a href="/admin/admLogout.jsp">로그아웃</a></li>

			</ul>

		</nav>

		<%
		if (admSession_HTMP != null) {
			String loginIp = request.getRemoteAddr();
		%>
		<p id="userData">
			<span><b><%=level_HTMP%> </b>관리자님<span>로그인 중</span></span>
			<%-- (<span id="headerclock"></span> / 
	    			<span>접속 IP : <%=loginIp %></span>) --%>
		</p>
		<!-- <script src="/script/header.js"></script> -->
		<%
		}
		%>
	</div>
</header>
<!--  header#header  -->
