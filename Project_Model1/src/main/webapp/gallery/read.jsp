<%@page import="pack.gallery.GalleryBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="gMgr" class="pack.gallery.GalleryMgr" />
<%
	String uidSession = (String)session.getAttribute("uidSession");
	int num = Integer.parseInt(request.getParameter("num"));
	
	gMgr.upCount(num);
	GalleryBean bean = gMgr.getGallery(num);
	String uid = bean.getUid();
%>
<%@ include file="/ind/topTmp.jsp" %>
<script>
$(function () {
	$("title").text("갤러리게시판 글 보기");
	$("#modBtn").click(function () {
		let num = $("#num").val().trim();
		let url = "modify.jsp?gnbParam=gallery";
		url += "&num="+num;
		location.href = url;
	});
	$("#delBtn").click(function () {
		if(confirm("삭제하시겠습니까?")){
			$("#delFrm").submit();		
		}
	});
})
</script>
<style>
#contents div{margin: 5px;}
#container{display: flex;}
#imgBox{width: 300px; height: 100%; overflow: hidden;}
img{width: 100%;height: 100%;object-fot:cover;}
#textBox{flex: 1}
</style>
	<!-- 헤더템플릿 시작 -->
	<%@ include file="/ind/headerTmp.jsp" %>
	<!-- 헤더템플릿 끝 -->
	
	<main id="main" class="dFlex">
		<div id="innerWrap" class="dFlex">
		<div id="lnb">
    		<!--  메인 LNB 템플릿 시작 -->
    		
			<%@ include file="/ind/mainLnbTmp.jsp" %>
			
    		<!--  메인 LNB 템플릿 끝 -->    	
   		</div>
			<div id="contents">
				<header>
					<div>
						<h3><%=bean.getSubject() %></h3>
					</div>
					<div>
						<div><%=bean.getUname() %></div>
						<div>
							<span><%=bean.getRegtm() %> </span><span> 조회수 : <%=bean.getReadcnt() %></span>
						</div>
					</div>
				</header>
				<div id="container">
					<div id="imgBox">
						<img src="/galleryupload/<%=bean.getFilename() %>" alt="#" />
					</div>
					<div id="textBox"><%=bean.getContent() %>
					</div>
				</div>
					<button type="button" onclick="location.href='list.jsp?gnbParam=gallery'">목록으로</button>
					<%if (uidSession!=null && uidSession.equals(uid))  { %>
						<button type="button" id="modBtn">수 정</button>
						<button type="button" id="delBtn">삭 제</button>
					<% } %>
			</div>
				<input type="hidden" name="num" id="num" value="<%=num %>"/>
			<form action="deleteProc.jsp" id="delFrm">
				<input type="hidden" name="num" value="<%=num %>"/>
			</form>
		</div>
	</main>
<!-- 푸터템플릿 시작 -->
<%@ include file="/ind/footerTmp.jsp" %>
<!-- 푸터템플릿 끝 -->