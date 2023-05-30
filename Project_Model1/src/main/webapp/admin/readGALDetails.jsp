<%@page import="java.util.Arrays"%>
<%@page import="pack.gallery.GalleryBean"%>
<%@page import="java.util.Vector"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="pack.member.MemberBean"%>
<%@page import="pack.bbs.BoardBean"%>
<%@page import="pack.gallery.GalleryBean"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="mMgr" class="pack.member.MemberMgr"/>
<jsp:useBean id="bMgr" class="pack.bbs.BoardMgr"/> 
<jsp:useBean id="aMgr" class="pack.admin.AdminMgr"/> 

<% 
request.setCharacterEncoding("UTF-8");
String uidParam = (String)request.getParameter("uid");
MemberBean mBean = mMgr.modifyMember(uidParam);
%>

<%
String keyField =""; // DB 컬럼명
String keyWord =""; // DB 검색어

if(request.getParameter("keyWord")!=null){
	keyField = request.getParameter("keyField");
	keyWord= request.getParameter("keyWord");
}

// 페이지 관련 속성 값 시작 //
// 페이징 = 페이지 나누기를 의미함

int totalRecord = 0; // 전체 데이터 수
int numPerPage = 3; // 페이지당 출력하는 데이터 수
int pagePerBlock = 3; // 블럭당 표시되는 페이지 수의 개수
int totalPage = 0; // 전체 페이지 수 
int totalBlock = 0; // 전체 블럭 수 

int nowPage = 1; // 사용자가 보고있는 페이지 번호
int nowBlock = 1; // 사용자가 보고있는 블럭

int start = 0; // DB에서 데이터를 불러올 때 시작하는 인덱스 번호
int end = 3; // 시작하는 인덱스 번호부터 반환하는 데이터 개수

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

Vector<GalleryBean> list = aMgr.getGalleryList(uidParam, keyField, keyWord,start,end);

totalRecord = aMgr.getTotalGallery(uidParam, keyField, keyWord);


totalPage = (int)Math.ceil((double)totalRecord/numPerPage);
nowBlock = (int)Math.ceil((double)nowPage/pagePerBlock);
totalBlock = (int)Math.ceil((double)totalPage/pagePerBlock);
%>


<%@ include file="/ind/topTmp.jsp" %>
<style>
#userModArea {
width: 1000px; border: 2px solid #aaa; padding: 10px;
}
#userModTmp {
margin-left: 15px;
}
.contents {
margin: 5px auto; border: 2px solid #aaa;
}
li.umTmpLi {
font-weight: bold; font-size: 18px; border: 2px solid #aaa; padding: 10px;
text-align: center;
}
li.umTmpLi:hover {
background-color: #aaa; cursor: pointer;
}
#userModTmp>#ul#umTmp {
height: 25px; align-items : center; justify-content : center;
}
#galMng div#post img {
width: 150px;
}
</style>
<script>
$(function () {
	$("title").text("수정 페이지");
})
</script>

<script src="/script/script_Admin_GAL.js"></script>
<script src="/script/script_MemMod.js"></script>
<!--  헤더템플릿 시작, iframe으로 변경 -->
<%@ include file="/admind/headerTmp.jsp" %>
<!--  헤더템플릿 끝 -->

<!-- 실제 작업영역 시작-->
<main id="main">
				
	<div id="innerWrap" class="dFlex">
				
		<div id="lnb">
			<!--  메인 LNB 템플릿 시작 -->
			<%@ include file="/admind/mainLnbTmp.jsp" %>
			<!--  메인 LNB 템플릿 끝 -->    	
		</div>

		<div id="userModArea">
<%
String memURL = "/admin/readUserDetails.jsp?uid="+uidParam+"&nowPage=1&keyField=&keyWord=";
String bbsURL = "/admin/readBBSDetails.jsp?uid="+uidParam+"&nowPage=1&keyField=&keyWord=";
String galURL = "/admin/readGALDetails.jsp?uid="+uidParam+"&nowPage=1&keyField=&keyWord=";
%>
		<div id="userModTmp">
			<ul id="umTmp"  class = "dFlex">
				<li id = "memMngGo" class ="umTmpLi">
					<a href="<%=memURL%>">회원정보</a>
				</li>
				<li id = "bbsMngGo" class ="umTmpLi">
					<a href="<%=bbsURL%>">자유게시판</a>
				</li>
				<li id = "galMngGo" class ="umTmpLi">
					<a href="<%=galURL%>">갤러리게시판</a>
				</li>
			</ul>
		</div>
		<!-- div#userModTmp -->

		<div id="galMng" class="contents">
		
		<% if(keyWord==null||keyWord.equals("")){ %>
    			<h3><a href="list.jsp?gnbParam=gallery">갤러리게시판</a></h3>
    		<%} else{%>
    			<h3>검색결과</h3>
    			<%} %>
    			<div id="container">
				<% for(int i=0;i<list.size();i++){
					int num = list.get(i).getNum();
					String regtm = list.get(i).getRegtm();
					Date now = new Date();
			        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
					long time = Long.parseLong(formatter.format(now));
					long writeTm = Long.parseLong(regtm.replaceAll("[^0-9]", ""));
				%>
	    			<div id="post" onclick="read('<%=num %>', '<%=nowPage %>')">
		    			<div id="imgBox"><img src="../galleryupload/<%=list.get(i).getFilename() %>" alt="#" /></div>
		    			<div id="titleBox">
		    			제목 : <%=list.get(i).getSubject() %>
		    			<%if(time-writeTm<1000000){
							out.print("<span id='iconSample'>N</span>");
						}%>
		    			</div>
		    			<div id="uName">작성자 : <%=list.get(i).getUname() %> </div>
		    			<div id="date">작성일 : <%=list.get(i).getRegtm() %></div>
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
		<!-- div#galMng -->
		
		
		</div>
		<!-- div#userModArea -->
	</div>
	<!-- inner -->
</main>
<!-- 실제 작업영역 끝 -->





<!--  푸터템플릿 시작 -->
<%@ include file="/ind/footerTmp.jsp" %>
<!--  푸터템플릿 끝 --> 	






