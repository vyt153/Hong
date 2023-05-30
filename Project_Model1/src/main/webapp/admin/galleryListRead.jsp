<%@page import="pack.gallery.GalleryBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="gMgr" class="pack.gallery.GalleryMgr" />
<%
	request.setCharacterEncoding("UTF-8");
	String admSession = (String)session.getAttribute("admSession");
	String level = (String)session.getAttribute("level");
	int num = Integer.parseInt(request.getParameter("num"));

	String nowPage = request.getParameter("nowPage");
	String keyField = request.getParameter("keyField");
	String keyWord = request.getParameter("keyWord");
	
	gMgr.upCount(num);
	GalleryBean bean = gMgr.getGallery(num);
	String uid = bean.getUid();
%>
<%@ include file="/ind/topTmp.jsp" %>
<script src = "/script/script_Admin_GAL.js"></script>
<script>
$(function () {
	$("title").text("갤러리 게시판 보기");
	$("#galleryListBtn").click(function(){
		let param = $("input#nowPage").val().trim();
		let p3 = $("#pKeyField").val().trim();
		let p4 = $("#pKeyWord").val().trim();
		
		let url ="/admin/galleryList.jsp?nowPage="+param;
			url += "&keyField="+p3;
			url += "&keyWord="+p4;
		location.href = url;
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
	<%@ include file="/admind/headerTmp.jsp" %>
	<!-- 헤더템플릿 끝 -->
	
	<main id="main">
	<div id="innerWrap" class="dFlex">
		<div id="lnb">
    		<!--  메인 LNB 템플릿 시작 -->
    		
			<%@ include file="/admind/mainLnbTmp.jsp" %>
			
    		<!--  메인 LNB 템플릿 끝 -->    	
   		</div>
   		<form id="modFrm">
			<div id="contents">
				<header>
					<div><a href="#">갤러리게시판</a></div>
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
						<img src="../galleryupload/<%=bean.getFilename() %>" alt="#" />
					</div>
					<div id="textBox"><%=bean.getContent() %>
					</div>
				</div>
					<%
						String listBtnLabel = "";
						if(keyWord==null || keyWord.equals("")) {
							listBtnLabel = "목록으로";
						} else {
							listBtnLabel = "검색목록";
						}
						%>
					<button type="button" id="galleryListBtn"><%=listBtnLabel %></button>
					<%if(level.equals("Super")||level.equals("Manager")||level.equals("Board")){ %>
					<button type="button" id="modBtn">수 정</button>
					<button type="button" id="delBtn">삭 제</button>
					<%} %>
					<button type="button" onclick="history.back()">뒤로가기</button>
			</div>
				<input type="hidden" name="num" value="<%=num %>"/>
		</form>
			<form action="deleteProc.jsp" id="delFrm">
				<input type="hidden" name="num" value="<%=num %>"/>
			</form>
		<!-- 검색어전송 시작 -->
		<input type="hidden" id="pKeyField" value="<%=keyField%>">
		<input type="hidden" id="pKeyWord" value="<%=keyWord%>">
		<!-- 검색어전송 끝 -->
		<input type="hidden" name="nowPage" value="<%=nowPage%>" id="nowPage">
	</div>
	<!-- div#innerWrap -->
	</main>
<!-- 푸터템플릿 시작 -->
<%@ include file="/ind/footerTmp.jsp" %>
<!-- 푸터템플릿 끝 -->