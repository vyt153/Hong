/**
 * 
 */
 
 // 상세보기 페이지 이동 >> bbsListRead.jsp
 
 function read(p1,p2){
	let p3 = $("#pKeyField").val().trim();
	let p4 = $("#pKeyWord").val().trim();
	let param = "/admin/bbsListRead.jsp?num="+p1;
		param += "&nowPage="+p2;
		param += "&keyField="+p3;
		param += "&keyWord="+p4;
	location.href = param;
}

$(function(){
	
	// 파일 다운로드 시작
	$("span#downloadFile").click(function(){
		$("#downloadForm").submit();
	});
	
	// 게시글 수정 페이지 이동 >> bbsListMod.jsp
	$("button#modBtn").click(function(){
		let num = $("input#num").val().trim();
		let nowPage = $("input#nowPage").val().trim();
		let p3 = $("#pKeyField").val().trim();
		let p4 = $("#pKeyWord").val().trim();
		
		let url ="/admin/bbsListMod.jsp?";
		url += "num="+num+"&nowPage="+nowPage;
		url += "&keyField="+p3;
		url += "&keyWord="+p4;
		location.href = url;
	});
	
	// 글수정 페이지 게시글 수정 시작
	$("#modProcBtn").click(function(){
		let subject = $("#subject").val().trim();
		if(subject==""){
			alert("제목은 필수입력입니다.");
			$("#subject").focus();
		} else{
			$("#modFrm").attr("action","/admin/bbsListModProc.jsp");
			$("#modFrm").submit();
		}
	});
	
	//리스트페이지 검색
	$("button#searchBtn").click(function(){
		let keyWord = $("#keyWord").val().trim();
		if(keyWord==""){
			alert("검색어를 입력해주세요");
			$("#keyWord").focus();
		} else{
			$("#searchFrm").submit();
		}
	});
	
	// 검색 결과를 유지한 리스트페이지 이동
	$("#listBtn").click(function(){
		let param = $("input#nowPage").val().trim();
		let p3 = $("#pKeyField").val().trim();
		let p4 = $("#pKeyWord").val().trim();
		
		let url ="/admin/bbsList.jsp?nowPage"+param;
			url += "&keyField="+p3;
			url += "&keyWord="+p4;
		location.href = url;
	});
	
	// 뒤로가기 버튼
	$("#backBtn").click(function(){
		history.back();
	})
});