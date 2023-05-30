<%@page import="pack.admin.AdmBoardBean"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="pack.gallery.GalleryBean"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="admMgr" class="pack.admin.AdminMgr" />
<jsp:useBean id="gMgr" class="pack.gallery.GalleryMgr" />
<%
	request.setCharacterEncoding("UTF-8");
	String uidSession = (String)session.getAttribute("uidSession");
	String keyField =""; // DB 컬럼명
	String keyWord =""; // DB 검색어
	
	if(request.getParameter("keyWord")!=null){
		keyField = request.getParameter("keyField");
		keyWord= request.getParameter("keyWord");
	}
	
	// 페이지 관련 속성 값 시작 //
	// 페이징 = 페이지 나누기를 의미함

	int totalRecord = 0; // 전체 데이터 수
	int numPerPage = 6; // 페이지당 출력하는 데이터 수
	int pagePerBlock = 5; // 블럭당 표시되는 페이지 수의 개수
	int totalPage = 0; // 전체 페이지 수 
	int totalBlock = 0; // 전체 블럭 수 

	int nowPage = 1; // 사용자가 보고있는 페이지 번호
	int nowBlock = 1; // 사용자가 보고있는 블럭

	int start = 0; // DB에서 데이터를 불러올 때 시작하는 인덱스 번호
	int end = 6; // 시작하는 인덱스 번호부터 반환하는 데이터 개수

	int listSize = 0; // 1페이지에서 보여주는 데이터 수

	// 게시판 검색 소스

	if(request.getParameter("keyWord")!=null){
		keyField = request.getParameter("keyField");
		keyWord= request.getParameter("keyWord");
	}

	if(request.getParameter("nowPage")!=null){
		nowPage = Integer.parseInt(request.getParameter("nowPage"));
		start = (nowPage * numPerPage) - numPerPage;
		end = numPerPage;
	}

	Vector<GalleryBean> list = gMgr.getGalleryList(keyField, keyWord,start,end);
	
	totalRecord = gMgr.getTotalGallery(keyField, keyWord);

	totalPage = (int)Math.ceil((double)totalRecord/numPerPage);
	nowBlock = (int)Math.ceil((double)nowPage/pagePerBlock);
	totalBlock = (int)Math.ceil((double)totalPage/pagePerBlock);
%>
<%@ include file="/ind/topTmp.jsp" %>
<script>
$(function () {
	$("title").text("갤러리게시판");
	$("#galleryBtn").click(function () {
		<%if(uidSession!=null){%>
		$("form").attr("action","/gallery/write.jsp");
		$("form").submit();
		<%} else{%>
		alert("로그인 후 글쓰기가 가능합니다.");
		<%}%>
	});
})
function Read(p1,p2) {
	let p3 = $("#pKeyField").val().trim();
	let p4 = $("#pKeyWord").val().trim();
	let param = "/gallery/read.jsp?num="+p1;
		param += "&nowPage="+p2;
		param += "&keyField="+p3;
		param += "&keyWord="+p4 ;
	location.href= param;
}
</script>
<link rel="stylesheet" href="/style/style_Gallery.css">
<script src="/script/script_Gallery.js"></script>
<style>
#pageInfo{justify-content: space-between;}
#noticeList{border-collapse: collapse; width: 80%; text-align: center;}
.prnTr>td:nth-child(2){text-align: left;}
.prnTr:hover{background-color: rgba(90, 185, 94, 0.3);
	cursor: pointer;}
.prnTr>td>img{width: 16px; height: 10px;}
</style>
			<!--  헤더템플릿 시작, iframe으로 변경 -->
			<%@ include file="/ind/headerTmp.jsp" %>
	    	<!--  헤더템플릿 끝 -->    	
    	
    	<main id="main" >
    		<div id="innerWrap" class="dFlex">
    		<div id="lnb">
	    		<!--  메인 LNB 템플릿 시작 -->
	    		
				<%@ include file="/ind/mainLnbTmp.jsp" %>
				
	    		<!--  메인 LNB 템플릿 끝 -->    	
    		</div>
    		
	    	<!-- 실제 작업 영역 시작 -->
    		<div id="contents">
    		
    			<h3><a href="/gallery/list.jsp?gnbParam=gallery">갤러리게시판</a></h3>
    		<%
	    			String prnType = "";
	    			if(keyWord.equals("null")||keyWord.equals("")){
	    				prnType="전체 게시글";
	    			}else{
	    				prnType = "검색 결과";
	    			}
	    		%>
	    			<div id="pageInfo" class="dFlex">
	    			<span><%=prnType %> : <%=totalRecord %> 개</span>
	    			<span>페이지 : <%=nowPage + " / " + totalPage %></span>
	    			</div>
	    			
    			<table id="noticeList">
    				<thead>
    					<tr>
    						<th>유형</th>
    						<th>제목</th>
    						<th>이름</th>
    						<th>날짜</th>
    						<th>조회수</th>
    					</tr>
    					<tr>
    						<td colspan="5" class="spaceTd"></td>
    					</tr>
    				</thead>
    			<%
	    		Date now = new Date();
		        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		        long time = Long.parseLong(formatter.format(now));
	    		List<AdmBoardBean> noticeList = admMgr.getNoticeList(); 
	    		for(int i=0;i<noticeList.size();i++){
	    			AdmBoardBean bean = noticeList.get(i);
	    			String postTM = bean.getPostTM();
	    			String filename = bean.getFilename();
	    			String indFile = "";
	    			if(filename!=null){
	    				indFile = "<img src='/images/clip_16x10.png' alt=''>";
					}
					long post = Long.parseLong(bean.getPostTM().replaceAll("[^0-9]", ""));
					String noticeChk = "";
					if(time-post<1000000){
						noticeChk = "<span id='iconSample'>N</span>";
						postTM = postTM.substring(11, 16);
					} else{
						postTM = postTM.substring(0, 10);
					}
	    			String type = "";
	    			if(bean.getType()==1) type="필독";
	    			else type = "공지";%>
	    				<tr class="fixedNotice prnTr" onclick="Read(<%=bean.getNum()%>,<%=nowPage%>)">
	    					<td><%=type %></td>
	    					<td><%=bean.getSubject()+noticeChk+indFile %></td>
	    					<td><%=bean.getadmName() %></td>
	    					<td><%=postTM %></td>
	    					<td><%=bean.getReadcnt() %></td>
	    				</tr>
   				<%} %>
    			</table>
    			<div id="container">
				<% for(int i=0;i<list.size();i++){
					int num = list.get(i).getNum();
					String regtm = list.get(i).getRegtm();
					long writeTm = Long.parseLong(regtm.replaceAll("[^0-9]", ""));
				%>
	    			<div id="post" onclick="Read('<%=num %>')">
		    			<div id="imgBox"><img src="../galleryupload/<%=list.get(i).getFilename() %>" alt="#" /></div>
		    			<div id="titleBox">
		    			제목 : <%=list.get(i).getSubject() %>
		    			<%if(time-writeTm<1000000){
							out.print("<span id='iconSample'>N</span>");
							regtm = regtm.substring(11, 16);
						} else regtm = regtm.substring(0, 10);%>
		    			</div>
		    			<div id="uName">작성자 : <%=list.get(i).getUname() %> </div>
		    			<div id="date">작성일 : <%=regtm %></div>
		    			<div id="viewCnt">조회수 : <%=list.get(i).getReadcnt() %></div>
	    			</div>
				<%} %>
				</div>
	    		<div id="listBtnArea">
	    			<button type="button" id="galleryBtn">글쓰기</button>
	    			<form>
						<div>
							<select name="keyField" id="keyField">
								<option value="subject"
									<%if(keyField.equals("subject")) out.print("selected"); %>>제  목
								</option>
								<option value="uName"
									<%if(keyField.equals("uName")) out.print("selected"); %>>이  름
								</option>
								<option value="content"
									<%if(keyField.equals("content")) out.print("selected"); %>>내  용
								</option>
							</select>	
							<input type="text" name="keyWord" id="keyWord" 
							size="20" maxlength="30" value="<%=keyWord%>"/>
							<button type="button" id="searchBtn">검색</button>
						</div>	
						<input type="hidden" id="pKeyField" value="<%=keyField %>" />
						<input type="hidden" id="pKeyWord" value="<%=keyWord %>"/>
   					</form>
	    		</div>
	    		
	    		<div id="listPagingArea">
    			
    			<div id="pagingTd">
    				<%
    					int pageStart = (nowBlock -1) * pagePerBlock +1;
    					int pageEnd = (nowBlock < totalBlock) ? pageStart + pagePerBlock - 1 : totalPage;
    					if (totalPage != 0) {
    				%>
    				<% if (nowBlock>1) { %>
    				<span class="moveBlockArea" onclick="moveBlock('<%=nowBlock-1%>', '<%=pagePerBlock%>')">
						&lt; 
					</span>
    				<% } else { %>
    				<span class="moveBlockArea" ></span>
    				<% } %>
    				<%
    				for (; pageStart<=pageEnd; pageStart++) { %>
    				<% if (pageStart == nowPage) { %>
    				<span class="nowPageNum"><%=pageStart %></span>
    				<% } else { %>	
    					<span class="pageNum" onclick="movePage('<%=pageStart%>')">
    						<%=pageStart %>
    					</span>
    				<% } %>
    				
    				<% } %>
    						
    				<% if (totalBlock>nowBlock) { %>
    					<span  class="moveBlockArea" 
    					onclick="moveBlock('<%=nowBlock+1%>', '<%=pagePerBlock%>')">
							&gt;
						</span>
    				<% } else { %>
    					<span class="moveBlockArea"></span>
    				<% } %>
    				<%
					} else {
					out.print("<b>[ Paging Area ]</b>"); // End if
					}
					%>
    			</div>
    			
    		</div>
    		</div>
    		
    		
    		<!-- 실제 작업 영역 끝 -->
    		</div>
    	</main>
    	<!--  main#main  -->
    
    	<!--  푸터템플릿 시작 -->
		<%@ include file="/ind/footerTmp.jsp" %>
    	<!--  푸터템플릿 끝 -->  
