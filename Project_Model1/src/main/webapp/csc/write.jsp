<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="cMgr" class="pack.csc.CscMgr"/>
<%
	String uid = (String) session.getAttribute("uidSession");
	String uname = cMgr.getMemberName(uid);
%>
<%@ include file="/ind/topTmp.jsp" %>
<script>
$(function () {
	$("title").text("Q&A 글쓰기");
})
</script>
		<link rel="stylesheet" href="/style/style_BBS.css">
		<script src="/script/script_Csc.js"></script>
			<!--  헤더템플릿 시작 -->
			<%@ include file="/ind/headerTmp.jsp" %>
	    	<!--  헤더템플릿 끝 -->
	    	
	    	<main id="main" class="dFlex">
    			<div id="innerWrap" class="dFlex">
	    		<div id="lnb">
		    		<!--  메인 LNB 템플릿 시작 -->
					<%@ include file="/ind/mainLnbTmp.jsp" %>
		    		<!--  메인 LNB 템플릿 끝 -->    	
	    		</div>
    		
	    		<div id="contents" class="bbsWrite">
		    	<h2>글쓰기</h2>
			    	<form name="writeFrm" method="post" id="writeFrm">
			    		<table>
			    			<tbody>
			    				<tr>
			    					<td class="req">성명</td>
			    					<td>
			    						<%=uname %>
			    						<input type="hidden" name="uname" value="<%=uname%>"/>
			    						<input type="hidden" name="uid" value="<%=uid%>"/>
			    					</td>
			    				</tr>
			    				<tr>
			    					<td class="req">제목</td>
			    					<td>
			    						<input type="text" name="subject" maxlength="50" id="subject" />
			    					</td>
			    				</tr>
			    				<tr>
			    					<td class="contentTD">내용</td>
			    					<td>
			    						<textarea name="content" id="content" wrap="hard" cols="60"></textarea>
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
			    						<button type="button" id="regBtn">등록</button>
			    						<button type="reset" id="resetBtn">다시쓰기</button>
			    						<button type="button" id="listBtn">리스트</button>
			    					</td>
			    				</tr>
			    			</tfoot>
			    		</table>
			    		<input type="hidden" name="ip" value="<%=request.getRemoteAddr() %>" />
			    	</form>
		    	</div>
		    	</div>
	    	</main>
<!--  푸터템플릿 시작 -->
<%@ include file="/ind/footerTmp.jsp" %>
  	<!--  푸터템플릿 끝 --> 