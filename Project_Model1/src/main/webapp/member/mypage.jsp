<%@page import="java.util.Arrays"%>
<%@page import="pack.member.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="mMgr" class="pack.member.MemberMgr"/>
<%
	String uidSession = (String)session.getAttribute("uidSession");
	if(uidSession==null) response.sendRedirect("/index.jsp");
	MemberBean mBean = mMgr.modifyMember(uidSession);
%>
<%@ include file="/ind/topTmp.jsp" %>
<script>
$(function () {
	$("title").text("마이페이지");
})
</script>
<link rel="stylesheet" href="/style/style_MemMod.css">
<style>
caption#cap{background-color: #50b639;}
</style>
			<!--  헤더템플릿 시작, iframe으로 변경 -->
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
    		<div id="contents">
	    		<form name="modFrm" id="modFrm">
					
						<table id="modFrmTbl">
							<caption id="cap">회원 정보</caption>
							<tbody>
								<tr>
									<td class="req">아이디</td>
									<td>
										<%=mBean.getUid() %>
										<input type="hidden" name="uid" value="<%=mBean.getUid() %>" >
									</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td class="req">이름</td>
									<td>
										<%=mBean.getUname()%>
									</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td class="req">Email</td>
									<td>
										<% 
											String uemail = mBean.getUemail();
											String[] email = uemail.split("@");
										%>
										<%=uemail %>
									</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>성별</td>
									<td>
									<%
										String gender = mBean.getGender();
										String chkGender = "";
										if(gender!=null){
											if(gender.equals("1")){
												chkGender = "남자";
											}else if(gender.equals("2")){
												chkGender = "여자";
											}
										} else{
											chkGender = "선택안함";
										}
									%>
									<%=chkGender %>
									</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>생년월일</td>
									<td>
									<%if(mBean.getUbirthday()!=null){%>
										<%=mBean.getUbirthday()%>
									<%} else{ %>
										입력안함
									 <%} %>
									 </td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>우편번호</td>
									<td>
									<%if(mBean.getUzipcode()!=null){%>
										<%=mBean.getUzipcode() %>
									<%} else{ %>
										입력안함
									 <%} %>
									</td>
								</tr>
								<tr>
									<td>주소</td>
									<td>
									<%if(mBean.getUaddr()!=null){%>
										<%=mBean.getUaddr() %>
									<%} else{ %>
										입력안함
										<%} %>
									</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>취미</td>
									<%
										String[] uhobby = mBean.getUhobby();
									%>
									<script>
										$(function () {
											let chkBoxAry = <%=Arrays.toString(uhobby)%>;
											let len = chkBoxAry.length;
											
											let chkToF;
											for (var i = 0; i < len; i++) {
												if(chkBoxAry[i]==1) chkToF = true;
												$("input[name=uhobby]").eq(i).prop("checked", chkToF);
												chkToF=false;
											}
										})
									</script>
									<td>
										<label><input type="checkbox" name="uhobby" value="인터넷"/>인터넷</label>
										<label><input type="checkbox" name="uhobby" value="여행"/>여행</label>
										<label><input type="checkbox" name="uhobby" value="게임"/>게임</label>
										<label><input type="checkbox" name="uhobby" value="영화"/>영화</label>
										<label><input type="checkbox" name="uhobby" value="운동"/>운동</label>
									</td>
									<td></td>
								</tr>
								<tr>
									<td>직업</td>
									<%
										String ujob = mBean.getUjob();
									%>
									<td>
									<%if(ujob!=null){ %>
										<%=ujob %>
										<%}else{ %>
										입력안함
										<%} %>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
    		</div>
    		<!-- 실제 작업 영역 끝 -->
    		</div>	
    	</main>
    	<!--  main#main  -->
    
    	<!--  푸터템플릿 시작 -->
		<%@ include file="/ind/footerTmp.jsp" %>
    	<!--  푸터템플릿 끝 -->  
    