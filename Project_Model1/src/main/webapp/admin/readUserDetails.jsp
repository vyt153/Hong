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
String level = (String)session.getAttribute("level");
%>

<div id="bbsPagingTxt">

<%
// 페이지 관련 속성 값 시작 //

int totalRecord = 0; // 전체 데이터 수
int numPerPage = 9; // 페이지당 출력하는 데이터 수
int pagePerBlock = 5; // 페이지당 출력되는 블럭 수
int totalPage = 0; // 전체 페이지 수 
int totalBlock = 0; // 전체 블럭 수 

int nowPage = 1; // 사용자가 보고있는 페이지 번호
int nowBlock = 1; // 사용자가 보고있는 블럭

int start = 0; // DB에서 데이터를 불러올 때 시작하는 인덱스 번호
int end = 9; // 시작하는 인덱스 번호부터 반환하는 데이터 개수

int listSize = 0; // 1페이지에서 보여주는 데이터 수

// 게시판 검색 소스
String keyField =""; // DB 컬럼명
String keyWord =""; // DB 검색어

if(request.getParameter("keyWord")!=null){
	keyField = request.getParameter("keyField");
	keyWord= request.getParameter("keyWord");
}

if(request.getParameter("nowPage")!=null){
	nowPage = Integer.parseInt(request.getParameter("nowPage"));
	start = (nowPage * numPerPage) - numPerPage;
	end = numPerPage;
}

totalRecord = aMgr.getTotalCount2(uidParam,keyField, keyWord);

totalPage = (int)Math.ceil((double)totalRecord/numPerPage);
nowBlock = (int)Math.ceil((double)nowPage/pagePerBlock);
totalBlock = (int)Math.ceil((double)totalPage/pagePerBlock);

// 페이징 관련 속성 끝//
Vector<BoardBean> vList = null;
%>

</div>
<!-- div#bbsPagingTxt -->

<div id="galPaginTxt">

<%

	Vector<GalleryBean> list = aMgr.getGalleryList(uidParam, keyField, keyWord,start,end);
	
	totalRecord = aMgr.getTotalGallery(uidParam, keyField, keyWord);

%>

</div>
<!-- div#galPaginTxt -->

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

<script src="/script/script_Admin_Details.js"></script>
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
		
		<div id="memMng" class="contents">
			<form name="modFrm" id="modFrm">

				<table id="modFrmTbl">
					<caption>회원 정보 수정</caption>
					<tbody>
						<tr class="req">
							<td>아이디</td>
							<td><%=mBean.getUid() %> <input type="hidden" name="uid"
								value="<%=mBean.getUid() %>"></td>
							<td>&nbsp;</td>
						</tr>
						<tr class="req">
							<td>이름</td>
							<td><input type="text" name="uname" id="uname"
								maxlength="20" value="<%=mBean.getUname()%>" /></td>
							<td>&nbsp;</td>
						</tr>
						<tr class="req">
							<td>Email</td>
							<td>
								<% 
											String uemail = mBean.getUemail();
											String[] email = uemail.split("@");
										%> <input type="text" id="uemail_01" maxlength="20" size="7"
								value="<%=email[0]%>" /> <span>@</span> <input type="text"
								id="uemail_02" maxlength="20" size="10" value="<%=email[1]%>" />

								<select id="emailDomain" class="frmDropMenu">
									<option value="">직접입력</option>
									<option>naver.com</option>
									<option>daum.net</option>
							</select>

								<button type="button" id="emailAuthBtn" class="frmBtn">인증코드받기</button>

								<!-- 이메일 인증영역 시작 : Authentication Code 인증코드 -->

								<div id="emailAuthArea">
									<span>인증코드 입력</span> <input type="text" id="emailAuth"
										size="25" />
									<button type="button" class="frmBtn">인증하기</button>
								</div> <!-- div#emailAuthArea --> <input type="hidden" name="uemail"
								id="uemail" />
							</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>성별</td>
							<td>
								<%
										String gender = mBean.getGender();
										String chkMale = "";
										String chkFemale = "";
										if(gender!=null){
											if(gender.equals("1")){
												chkMale = "checked";
											}else if(gender.equals("1")){
												chkFemale = "checked";
											}
										}
									%> <label> 남<input type="radio" name="gender" value="1"
									<%=chkMale %> /></label> <label> 여<input type="radio"
									name="gender" value="2" <%=chkFemale %> /></label>
							</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>생년월일</td>
							<td>
								<%if(mBean.getUbirthday()!=null){%> <input type="text"
								name="ubirthday" id="ubirthday" maxlength="6" size="8"
								value="<%=mBean.getUbirthday()%>" /> <%} else{ %> <input
								type="text" name="ubirthday" id="ubirthday" maxlength="6"
								size="8" value="" /> <%} %>
							</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>우편번호</td>
							<td>
								<%if(mBean.getUzipcode()!=null){%> <input type="text"
								name="uzipcode" id="uzipcode" maxlength="7" size="7"
								value="<%=mBean.getUzipcode() %>" readonly /> <%} else{ %> <input
								type="text" name="uzipcode" id="uzipcode" maxlength="7" size="7"
								value="" readonly /> <%} %>
								<button type="button" id="findZipBtn" class="frmBtn">우편번호찾기</button>
							</td>
							<td><span>우편번호 찾기 버튼을 클릭하세요.</span></td>
						</tr>
						<tr>
							<td>주소</td>
							<td>
								<%if(mBean.getUaddr()!=null){%> <input type="text" name="uaddr"
								id="uaddr" maxlength="100" size="50"
								value="<%=mBean.getUaddr() %>" /> <%} else{ %> <input type="text"
								name="uaddr" id="uaddr" maxlength="100" size="50" value="" /> <%} %>
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

							<td><label><input type="checkbox" name="uhobby"
									value="인터넷" />인터넷</label> <label><input type="checkbox"
									name="uhobby" value="여행" />여행</label> <label><input
									type="checkbox" name="uhobby" value="게임" />게임</label> <label><input
									type="checkbox" name="uhobby" value="영화" />영화</label> <label><input
									type="checkbox" name="uhobby" value="운동" />운동</label></td>
							<td></td>
						</tr>
						<tr>
							<td>직업</td>
							<%
										String ujob = mBean.getUjob();
									%>
							<td><select name="ujob" id="ujob" class="frmDropMenu">
									<%if(ujob!=null){ %>
									<option <% if(ujob.equals("")) out.print("selected"); %>
										value="">- 선택 -</option>
									<option <% if(ujob.equals("교수")) out.print("selected"); %>>교수</option>
									<option <% if(ujob.equals("학생")) out.print("selected"); %>>학생</option>
									<option <% if(ujob.equals("회사원")) out.print("selected"); %>>회사원</option>
									<option <% if(ujob.equals("공무원")) out.print("selected"); %>>공무원</option>
									<option <% if(ujob.equals("자영업")) out.print("selected"); %>>자영업</option>
									<option <% if(ujob.equals("전문직")) out.print("selected"); %>>전문직</option>
									<option <% if(ujob.equals("주부")) out.print("selected"); %>>주부</option>
									<option <% if(ujob.equals("무직")) out.print("selected"); %>>무직</option>
									<%} else{%>
									<option>- 선택 -</option>
									<option>교수</option>
									<option>학생</option>
									<option>회사원</option>
									<option>공무원</option>
									<option>자영업</option>
									<option>전문직</option>
									<option>주부</option>
									<option>무직</option>
									<%} %>
							</select></td>
							<td></td>
						</tr>
						<tr>
						<%if(level.equals("Super")||level.equals("Manager")||level.equals("Member")){ %>
							<td colspan="3">
								<button type="button" id="modSbmBtn" class="frmBtn">수정하기</button>
								<button type="reset" class="frmBtn">다시쓰기</button>
							</td>
							<%} %>
						</tr>
					</tbody>
				</table>
			</form>
			<!-- form[name=regFrm -->
			
		<!-- 검색결과 유지용 매개변수 데이터 -->
	    <input type="hidden" id="pKeyField" value="<%=keyField %>" />
	    <input type="hidden" id="pKeyWord" value="<%=keyWord %>"/>
		</div>
		<!-- div#memMng -->

		</div>
		<!-- div#userModArea -->
	</div>
	<!-- inner -->
</main>
<!-- 실제 작업영역 끝 -->
			
<!--  푸터템플릿 시작 -->
<%@ include file="/ind/footerTmp.jsp" %>
<!--  푸터템플릿 끝 --> 	