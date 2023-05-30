<%@page import="pack.gallery.GalleryBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="mMgr" class="pack.member.MemberMgr" />
<jsp:useBean id="gMgr" class="pack.gallery.GalleryMgr" />
<%
	String uid = (String) session.getAttribute("uidSession");
	String uname = mMgr.getMemberName(uid);
	int num = Integer.parseInt(request.getParameter("num"));
	GalleryBean bean = gMgr.getGallery(num);
	
	String oriFilename	= bean.getOriFilename();
%>
<%@ include file="/ind/topTmp.jsp" %>
<script>
$(function () {
	$("title").text("갤러리게시판 글 수정하기");
})
</script>
<script src = "/script/script_Admin_GAL.js"></script>
		<link rel="stylesheet" href="/style/style_BBS.css">
			<!--  헤더템플릿 시작 -->
			<%@ include file="/admind/headerTmp.jsp" %>
	    	<!--  헤더템플릿 끝 -->
	    	
	    	<main id="main">
    		<div id="innerWrap" class="dFlex">
	    		<div id="lnb">
		    		<!--  메인 LNB 템플릿 시작 -->
					<%@ include file="/admind/mainLnbTmp.jsp" %>
		    		<!--  메인 LNB 템플릿 끝 -->    	
	    		</div>
    		
	    		<div id="contents" class="bbsWrite">
		    	<h2>글 수정하기</h2>
			    	<form name="writeFrm" enctype="multipart/form-data" method="post" id="writeFrm">
<!-- input type="file"이 있다면 무조건 enctype 지정 method="post" 필수(데이터 전송 용량 한계를 없애기 위해) -->
			    		<table>
			    			<tbody>
			    				<tr>
			    					<td class="req">성명</td>
			    					<td>
			    						<%=bean.getUname() %>
			    					</td>
			    				</tr>
			    				<tr>
			    					<td class="req">제목</td>
			    					<td>
			    						<input type="text" name="subject" maxlength="50" id="subject" value="<%=bean.getSubject()%>"/>
			    					</td>
			    				</tr>
			    				<tr>
			    					<td class="contentTD">내용</td>
			    					<td>
			    						<textarea name="content" id="content" wrap="hard" cols="60"><%=bean.getContent()%></textarea>
			    					</td>
			    				</tr>
			    				<tr>
			    					<td>파일수정</td>
			    					<td>
			    						<span class="spanFile"> 
											<%=oriFilename %>
			    							<label class="fileUplodeHid">
				    							<input type="file" name="filename"/>
				    							<button type="button" id="fileModBtn">파일 변경</button>
			    							</label>
										</span>
										<small>* 파일은 하나만 업로드 가능합니다.</small>
			    					</td>
			    				</tr>
			    				<tr>
			    					<td>내용타입</td>
			    					<td>
			    						<label>
			    							<input type="radio" name="contentType" value="HTML"/>
			    							<span>HTML</span></label>
			    						<label>
			    							<input type="radio" name="contentType" value="TEXT" checked/>
			    							<span>TEXT</span></label>
			    					</td>
			    				</tr>
			    			</tbody>
			    			<tfoot>
			    				<tr>
			    					<td colspan="2"><hr /></td>
			    				</tr>
			    				<tr>
			    					<td colspan="2">
			    						<button type="button" id="regBtn">수정</button>
			    						<button type="reset">다시쓰기</button>
			    						<button type="button" id="listBtn">리스트</button>
			    					</td>
			    				</tr>
			    			</tfoot>
			    		</table>
			    		<input type="hidden" name="num" value="<%=num%>"/>
			    		<input type="hidden" name="ip" value="<%=request.getRemoteAddr() %>" />
			    	</form>
		    	</div>
		    </div>
	    	</main>
<!--  푸터템플릿 시작 -->
<%@ include file="/ind/footerTmp.jsp" %>
  	<!--  푸터템플릿 끝 --> 