<%@page import="pack.admin.AdminBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/ind/topTmp.jsp" %>
<%@ include file="/admind/headerTmp.jsp" %>
<jsp:useBean id="amdMgr" class="pack.admin.AdminMgr" />
<%
	String admSession = (String)session.getAttribute("admSession");
%>
<link rel="stylesheet" href="/style/style_BBS.css">
<style>
#addAdm{display: none;}
#modAdm{display: none;}
td{text-align: center;}
</style>
<script>
$(function(){
	$("#admAddBtn").click(function () {
		let name = $("#admName").val();
		let id = $("#admId").val();
		let pw = $("#admPw").val();
		let level = $("#level").val();
		if(name!=""&&id!=""&&pw!=""&&level!=""){
			$("#admFrm").attr("action","admAddProc.jsp");
			$("#admFrm").submit();
		} else{
			alert("정보를 모두 입력해주세요");
		}
	})
	$("#admModBtn").click(function () {
		let name = $("#modName").val();
		let id = $("#modId").val();
		let pw = $("#modPw").val();
		let level = $("#modLevel").val();
		if(name!=""&&id!=""&&pw!=""&&level!=""){
			$("#modFrm").submit();
		} else{
			alert("정보를 모두 입력해주세요");
		}
	})
	$("#addBtn").click(function () {
		$("#addAdm").toggle();
	})
	$("#delBtn").click(function () {
		if(!$(".delChk").is(":checked")){
			$(".delChk").val("");
		}
	})
	$("#modBtn").click(function () {
		let className = $("input[name=editChk]:checked").val();
		if(className==null) alert("편집할 관리자계정을 선택해주세요");
		else $('.'+className).toggle();
	})
	
	$("#delBtn").click(function () {
		let num = $("input[name=editChk]:checked").val();
		if(num==null) alert("편집할 관리자계정을 선택해주세요");
		else{
			if(confirm("삭제하시겠습니까?")){
				$("input#delFrm").val(num);
				$("#delFrm").attr("action","admDelProc.jsp");
				$("#delFrm").submit();
			} else return false;
		}
	})
})
</script>
	<main id="main">
    		<div id="innerWrap" class="dFlex">
    		<div id="lnb">
    			<!--  메인 LNB 템플릿 시작 -->
				<%@ include file="/admind/mainLnbTmp.jsp" %>
	    		<!--  메인 LNB 템플릿 끝 -->    	
    		</div>
    		<div id="contents" class="bbsList">
    		<h3><a href="/admin/admMgr.jsp">관리자관리</a></h3>
  				<form id="admFrm">
		    		<table id="boardList">
		   				<thead>
		   					<tr>
		   						<th>이름</th>
		   						<th>아이디</th>
		   						<th>비밀번호</th>
		   						<th>등급</th>
		   						<th>편집</th>
		   					</tr>
		   					<tr>
		   						<td colspan="5" class="spaceTd"></td>
		   					</tr>
		   				</thead>
		   				<tbody>
		   				<%
		   				List<AdminBean> list = amdMgr.getAdmList();
		   				for(int i=0;i<list.size();i++){
		   					AdminBean bean = list.get(i);
		   					
		   					if(bean.getLevel().equals("Super")){%>
			   					<tr>
			   						<td><%=bean.getAdmName() %></td>
			   						<td><%=bean.getAdmId() %></td>
			   						<td><%=bean.getAdmPw() %></td>
			   						<td><%=bean.getLevel() %></td>
			   						<td></td>
			   					</tr>
		   					<%} else{
		   					%>
		   					<tr>
		   						<td><%=bean.getAdmName() %></td>
		   						<td><%=bean.getAdmId() %></td>
		   						<td><%=bean.getAdmPw() %></td>
		   						<td><%=bean.getLevel() %></td>
		   						<td>
			   						<input type="radio" name="editChk" value="<%=bean.getNum() %>"/>
		   						</td>
		   					</tr>
		   					<tr id="modAdm" class="<%=bean.getNum() %>">
		   						<td><input type="text" name="admName" id="modName" form="modFrm" value="<%=bean.getAdmName() %>" size="10"/></td>
		   						<td><input type="text" name="admId" id="modId" form="modFrm" value="<%=bean.getAdmId() %>"/></td>
		   						<td><input type="text" name="admPw" id="modPw" form="modFrm" value="<%=bean.getAdmPw() %>"/></td>
		   						<td>
		   							<select name="level" id="modLevel" form="modFrm">
		   								<option <%if(bean.getLevel().equals("Manager")) out.print("selected");%>>Manager</option>
		   								<option <%if(bean.getLevel().equals("Board")) out.print("selected");%>>Board</option>
		   								<option <%if(bean.getLevel().equals("Member")) out.print("selected");%>>Member</option>
		   								<option <%if(bean.getLevel().equals("Notice")) out.print("selected");%>>Notice</option>
		   							</select>
		   						</td>
		   						<td>
									<input type="hidden" name="num" form="modFrm" value="<%=bean.getNum() %>" />
		   							<button type="button" id="admModBtn">수정하기</button>
		   						</td>
		   						
		   					</tr>
		   				<%} 
		   				}%>
		   					<tr id="addAdm">
		   						<td><input type="text" name="admName" id="admName" size="10"/></td>
		   						<td><input type="text" name="admId" id="admId"/></td>
		   						<td><input type="text" name="admPw" id="admPw"/></td>
		   						<td>
		   							<select name="level" id="level">
		   								<option>Manager</option>
		   								<option>Board</option>
		   								<option>Member</option>
		   								<option>Notice</option>
		   							</select>
		   						</td>
		   						<td><button type="button" id="admAddBtn">추가하기</button></td>
		   					</tr>
		   				</tbody>
					</table>
   				</form>
   				<form action="/admin/admModProc.jsp" id="modFrm"></form>
   				<form action="/admin/admDelProc.jsp" id="delFrm"></form>
   				<input type="hidden" name="num" id="delFrm" form="delFrm" />
				<button type="button" id="addBtn">추가</button>
				<button type="button" id="modBtn">수정</button>
				<button type="button" id="delBtn">삭제</button>
			</div>
	<!-- div#wrap -->
	</div>
    	</main>
	    	
    	<!--  푸터템플릿 시작 -->
		<%@ include file="/ind/footerTmp.jsp" %>
    	<!--  푸터템플릿 끝 --> 