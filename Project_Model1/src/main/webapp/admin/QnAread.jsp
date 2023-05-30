<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String uidSession = (String)session.getAttribute("uidSession"); %>
<%@ page import="pack.csc.CscBean" %>
<jsp:useBean id="cMgr" class="pack.csc.CscMgr"/>
<%
	request.setCharacterEncoding("UTF-8");
	int numParam = Integer.parseInt(request.getParameter("num"));
	
	//관리자 로그인 세션
	String admSession = (String)session.getAttribute("admSession"); 
	
	// 검색어 수신 시작
	String keyField = request.getParameter("keyField");
	String keyWord = request.getParameter("keyWord");
	
	// 현재 페이지 돌아가기
	String nowPage = request.getParameter("nowPage");
	
	cMgr.upCount(numParam); //조회수 증가
	CscBean bean = cMgr.getBoard(numParam);
	
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
	
	String ip = bean.getIp();
	
	session.setAttribute("bean", bean);
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
		<script src="/resource/jquery-3.6.0.min.js"></script>
		<script src="/script/script_Csc.js"></script>
	</head>
	<body>
		<!--  헤더템플릿 시작 -->
		<%@ include file="/admind/headerTmp.jsp" %>
    	<!--  헤더템플릿 끝 -->    	
    	
    	
    	<main id="main" class="dFlex">
    		<div id="innerWrap" class="dFlex">
    		<div id="lnb">
	    		<!--  메인 LNB 템플릿 시작 -->
				<%@ include file="/admind/mainLnbTmp.jsp" %>
	    		<!--  메인 LNB 템플릿 끝 -->    	
    		</div>
    		
    		
	    	<!-- 실제 작업 영역 시작 -->
    		<div id="contents" class="bbsRead">

				<!--  게시글 상세보기 페이지 내용 출력 시작 -->
				<h2><%=subject %></h2>
				
				<table id="readTbl">
					<tbody id="readTblBody">
						<tr>
							<td>작성자</td>  <!-- td.req 필수입력 -->
							<td><%=uname %></td>
							<td>등록일</td>  <!-- td.req 필수입력 -->
							<td><%=regtm %></td>
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
						<tr>
							<%
							String listBtnLabel = "";
							if(keyWord.equals("null") || keyWord.equals("")) {
								listBtnLabel = "돌아가기";
							} else {
								listBtnLabel = "검색목록";
							}
							%>
						
							<td colspan="4" id="btnAreaTd" class="read">
								<button type="button" id="listBtn"><%=listBtnLabel %></button>
								
								<% if (admSession!=null) { %>
									<% if(depth<1){ %>
										<button type="button" id="replyBtn">답 변</button>
									<% } %>
									<button type="button" id="amdModBtn">수 정</button>
									<button type="button" id="delBtn">삭 제</button>
								<%} %>
							</td>
						</tr>
					</tfoot>
					 
				</table>
				<input type="hidden" name="nowPage" value="<%=nowPage%>" id="nowPage">
				<input type="hidden" name="num" value="<%=num%>" id="num">
				
				<!-- 검색어전송 시작 -->
				<input type="hidden" id="pKeyField" value="<%=keyField%>">
				<input type="hidden" id="pKeyWord" value="<%=keyWord%>">
				<!-- 검색어전송 끝 -->
			  
				<!--  게시글 상세보기 페이지 내용 출력 끝 -->

    		</div>
    		<!-- 실제 작업 영역 끝 -->
    		</div>
    	</main>
    	<!--  main#main  -->
    
        	   	
    	<!--  푸터템플릿 시작 -->
		<%@ include file="/ind/footerTmp.jsp" %>
    	<!--  푸터템플릿 끝 -->  
        
