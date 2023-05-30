<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String uidSession = (String)session.getAttribute("uidSession"); %>
<%@ page import="pack.bbs.BoardBean" %>
<%@ page import="pack.comment.ComBean" %>
<jsp:useBean id="bMgr" class="pack.bbs.BoardMgr" />
<jsp:useBean id="cMgr" class="pack.comment.ComMgr" />
<%
	request.setCharacterEncoding("UTF-8");
	int numParam = Integer.parseInt(request.getParameter("num"));
	
	// 검색어 수신 시작
	String keyField = request.getParameter("keyField");
	String keyWord = request.getParameter("keyWord");
	
	// 현재 페이지 돌아가기
	String nowPage = request.getParameter("nowPage");
	
	bMgr.upCount(numParam); //조회수 증가
	BoardBean bean = bMgr.getBoard(numParam);
	
	int num = bean.getNum();
	String uid = bean.getUid();
	String uname = bean.getUname();
	String subject = bean.getSubject();
	String content = bean.getContent();
	
	int pos = bean.getPos();
	int ref = bean.getRef();
	int depth = bean.getDepth();
	String regtm = bean.getRegtm();
	int readcnt = bean.getReadcnt();
	String filename = bean.getFilename();
	double filesize = bean.getFilesize();
	String oriFilename = bean.getOriFilename();
	String fUnit="Bytes";
	if(filesize>1024){
		filesize /= 1024;
		fUnit = "KBytes";
	}
	
	String ip = bean.getIp();
	
	session.setAttribute("bean", bean);
	
	Vector<ComBean> cList = null;
%>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>글내용 보기</title>
		<link rel="stylesheet" href="/style/style_Common.css">
		<link rel="stylesheet" href="/style/style_Template.css">
		<link rel="stylesheet" href="/style/style_BBS.css">
		<link rel="stylesheet" href="/style/style_Comments.css">
		<script src="/resource/jquery-3.6.0.min.js"></script>
		<script src="/script/script_BBS.js"></script>
		<script src="/script/script_Com.js"></script>
	</head>
	<body>
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
    		
    		
	    	<!-- 실제 작업 영역 시작 -->
    		<div id="contents" class="bbsRead">

				<!--  게시글 상세보기 페이지 내용 출력 시작 -->
				<h2><%=subject %></h2>
				<form id="comFrm">
				<table id="readTbl">
					<tbody id="readTblBody">
						<tr>
							<td>작성자</td>  <!-- td.req 필수입력 -->
							<td><%=uname %></td>
							<td>등록일</td>  <!-- td.req 필수입력 -->
							<td><%=regtm %></td>
						</tr>
						<tr>
							<td>첨부파일</td> <!-- td.req 필수입력 -->
							<td colspan="3">
								<input type="hidden" name="filename" value="<%=filename%>" 
											id="hiddenFname" form="downloadForm">
							<% if (filename != null && !filename.equals("")) { %>						
								<span id="downloadFile"><%=oriFilename%></span>							
								(<span><%=(int)filesize + " " + fUnit%></span>)
							<% } else { %>
								등록된 파일이 없습니다.
							<% } %>
							</td>
						</tr>
						<tr>
							<td colspan="4" id="readContentTd"><pre><%=content %></pre></td>
						</tr>					
					</tbody>
					 
					<tfoot id="readTblFoot">	
						<tr>
							<td colspan="4" id="footTopSpace"></td>							
						</tr>			     
						<tr>
							<td colspan="4" id="articleInfoTd">
								<span><%="조회수 : " + readcnt %></span>
								<span>(<%="IP : " + ip %>)</span>							
							</td>							
						</tr>
						<tr>
							<td colspan="4" id="hrTd"><hr></td>							
						</tr>
						<!-- 댓글 영역 시작 -->
						<tr>
							<td colspan="4"><b>댓글</b></td>
						</tr>
						<%
						cList = cMgr.comList(num);
						int listSize = cList.size();
						
						if(cList.isEmpty()){
						%>
						<tr>
							<td colspan = "4">댓글이 없습니다.</td>
						</tr>
						
						<% } else {%>
						<%
							for(int i=0;i<listSize;i++) {
								
								ComBean listBean = cList.get(i);
								
								int cNum = listBean.getCnum();
								int cListnum = listBean.getListnum();
								String cUid = listBean.getUid();
								String cUname = listBean.getUname();
								String cComment = listBean.getComment();
								String cRegtm = listBean.getRegtm();
								int cPos = listBean.getPos();
								String cIp = listBean.getIp();
								
							
						%>
						
						<tr id="Com">
							<td colspan = "2">
								<%=cUname %> (<%=cIp %>)
								<input type="hidden" id = "cNum" name="cnum" form = "delBBSCom" value = "<%=cNum%>"/>
								<input type="hidden" id = "ckeyField" name = "ckeyField" form = "delBBSCom" value="<%=keyField%>">
								<input type="hidden" id = "ckeyWord" name = "ckeyWord" form = "delBBSCom" value="<%=keyWord%>">
								<input type="hidden" id = "cnowPage" name = "cnowPage" form = "delBBSCom" value="<%=nowPage%>">
							</td>
							<td>
								<button type="button" id="cModBtn<%=cNum%>">(수정하기)</button>
							</td>
							<td>
								<button type="button" id="cDelBtn<%=cNum%>" onclick = "delProcMtd(<%=cNum%>)">(삭제하기)</button>
							</td>
						</tr>
						<tr>
							<td colspan = "3" id = "comTxt">
								<%= cComment %>
							</td>
							<td>
								답글버튼
							</td>
						</tr>
						
						<% } } %>
						
						<% if (uidSession != null) { %>
						<tr>
							<td colspan="3">
							<textarea name="comment" id="comment" placeholder = "댓글을 입력해주세요."></textarea>
							</td>
							<td>
							<button type="button" id="comBtn">댓글쓰기</button>
							</td>
						</tr>
						<% } %>
						<!-- 댓글 영역 종료 -->
						<tr>
							<td colspan="4" id="hrTd"><hr></td>							
						</tr>
						
						
						
						<tr>
							<%
							String listBtnLabel = "";
							if(keyWord.equals("null") || keyWord.equals("")) {
								listBtnLabel = "목록으로";
							} else {
								listBtnLabel = "검색목록";
							}
							%>
						
							<td colspan="4" id="btnAreaTd" class="read">
								<button type="button" id="listBtn"><%=listBtnLabel %></button>
								
								<% if (uidSession==null) { %>
								<button type="button" onclick="alert('로그인이 필요합니다.');">답 변</button>
								<% } else if(depth<2){ %>
								<button type="button" id="replyBtn">답 변</button>
								<% } else{%>
								<%} %>
								<% 
									// out.print("uidSession : "+ uidSession + "<br>" + "uid : "+ uid);
									if (uidSession!=null && uidSession.equals(uid))  { 
								%>
								<button type="button" id="modBtn">수 정</button>
								<button type="button" id="delBtn">삭 제</button>
								<% } %>
							</td>
						</tr>
					</tfoot>
					 
				</table>
				
				<input type="hidden" name="listnum" value="<%=num%>" id="listnum">
				<input type="hidden" name="uid" value="<%=uidSession%>" id="uid">
				<input type="hidden" name="uname" value="<%=uname%>" id="uname">
				<input type="hidden" name="ip" value="<%=request.getRemoteAddr() %>" />
				<input type="hidden" name="nowPage" value="<%=nowPage%>" id="nowPage">
				<input type="hidden" name="num" value="<%=num%>" id="num">
				
				<!-- 검색어전송 시작 -->
				<input type="hidden" id="pKeyField" value="<%=keyField%>">
				<input type="hidden" id="pKeyWord" value="<%=keyWord%>">
				<!-- 검색어전송 끝 -->
				</form>
			  
				<!--  게시글 상세보기 페이지 내용 출력 끝 -->
				<form action="/bbs/download.jsp" id="downloadForm"></form>
		    	<form id="delBBSCom"></form>

    		</div>
    		<!-- 실제 작업 영역 끝 -->
    		</div>
    	</main>
    	<!--  main#main  -->
        	   	
    	<!--  푸터템플릿 시작 -->
		<%@ include file="/ind/footerTmp.jsp" %>
    	<!--  푸터템플릿 끝 -->  
        
