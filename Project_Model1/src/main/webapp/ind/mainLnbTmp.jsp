<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String uidSession_MLTmp = (String)session.getAttribute("uidSession");
	request.setCharacterEncoding("UTF-8");
	
	String gnbParam = "";
	String menu = "";
	if(request.getParameter("gnbParam")!=null){
		gnbParam = request.getParameter("gnbParam");
	}
	if(request.getParameter("menu")!=null){
		menu = request.getParameter("menu");
	}
	
	String lnbMainTit = "커뮤니티";
	if (gnbParam.equals("myPage")){
		lnbMainTit = "마이페이지";
	} else if (gnbParam.equals("csc")){
		lnbMainTit = "고객센터";
	}
	
%>

	<nav id="mainLNB">
		<ul id="lnbMainMenu">
			<li class="lnbMainH1">
				<p class="tit"><%=lnbMainTit %></p>
			</li>
			<%if (gnbParam.equals("myPage")){ %>
			<li class="lnbMainLi <%if(menu.equals("modInfo")) out.print("on");%>"><a href="memberMod.jsp?gnbParam=myPage&menu=modInfo">회원정보수정</a></li>
			<li class="lnbMainLi <%if(menu.equals("modPw")) out.print("on");%>"><a href="memberModPw.jsp?gnbParam=myPage&menu=modPw">비밀번호변경</a></li>
			<li class="lnbMainLi <%if(menu.equals("loginInfo")) out.print("on");%>"><a href="mypageAccessList.jsp?gnbParam=myPage&menu=loginInfo">접속내역</a></li>
			<li class="lnbMainLi <%if(menu.equals("Quit")) out.print("on");%>"><a href="memberQuit.jsp?gnbParam=myPage&menu=Quit">회원탈퇴</a></li>
			<%} else if (gnbParam.equals("csc")){ %> 
			<!-- customer service center : 고객센터 -->
				<%if(menu.equals("QnA")){ %>
			<li class="lnbMainLi <%out.print("on");%>"><a href="/csc/QnA.jsp?gnbParam=csc&menu=QnA">Q&A</a></li>
			<li class="lnbMainLi"><a href="#">FAQ</a></li>
				<%} else{ %>
			<li class="lnbMainLi"><a href="/csc/QnA.jsp?gnbParam=csc&menu=QnA">Q&A</a></li>
			<li class="lnbMainLi"><a href="#">FAQ</a></li>
				<%} %>
			<%} else{%>
				<%if(gnbParam.equals("bbs")){%>
				<li class="lnbMainLi <%out.print("on");%>"><a href="/bbs/list.jsp?gnbParam=bbs">자유게시판</a></li>
				<li class="lnbMainLi"><a href="/gallery/list.jsp?gnbParam=gallery">갤러리게시판</a></li>
				<li class="lnbMainLi"><a href="/notice/list.jsp?gnbParam=notice">공지사항</a></li>
				<%} else if(gnbParam.equals("gallery")) {%>
				<li class="lnbMainLi"><a href="/bbs/list.jsp?gnbParam=bbs">자유게시판</a></li>
				<li class="lnbMainLi <%out.print("on");%>"><a href="/gallery/list.jsp?gnbParam=gallery">갤러리게시판</a></li>
				<li class="lnbMainLi"><a href="/notice/list.jsp?gnbParam=notice">공지사항</a></li>
				<%} else if(gnbParam.equals("notice")){%>
				<li class="lnbMainLi"><a href="/bbs/list.jsp?gnbParam=bbs">자유게시판</a></li>
				<li class="lnbMainLi"><a href="/gallery/list.jsp?gnbParam=gallery">갤러리게시판</a></li>
				<li class="lnbMainLi <%out.print("on");%>"><a href="/notice/list.jsp?gnbParam=notice">공지사항</a></li>
				<%} else{%>
				<li class="lnbMainLi"><a href="/bbs/list.jsp?gnbParam=bbs">자유게시판</a></li>
				<li class="lnbMainLi"><a href="/gallery/list.jsp?gnbParam=gallery">갤러리게시판</a></li>
				<li class="lnbMainLi"><a href="/notice/list.jsp?gnbParam=notice">공지사항</a></li>
				<%} 
			}%>
		</ul>
	</nav>