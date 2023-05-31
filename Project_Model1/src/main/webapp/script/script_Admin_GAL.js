/**
 * 
 */

$(function(){
	
	// 게시판 검색 기능
	
	$("button#searchBtn").click(function(){
		let keyWord = $("#keyWord").val().trim();
		if(keyWord==""){
			alert("검색어를 입력해주세요");
			$("#keyWord").focus();
		} else{
			$("form").submit();
		}
	});
	
	// 리스트 수정 페이지 이동

	$("#modBtn").click(function() {
		$("#modFrm").attr("action", "/admin/galleryListMod.jsp");
		$("#modFrm").submit();
	});
	
	// 글쓰기 페이지 게시글 등록
	
	$("#regBtn").click(function(){
		let subject = $("#subject").val().trim();
		
		if(subject==""){
			alert("제목은 필수입력입니다.");
			$("#subject").focus();
		} else{
			$("#writeFrm").attr("action","/admin/galleryListModProc.jsp");
			$("#writeFrm").submit();
		}
	});
	
	// 뒤로가기
	
	$("#listBtn").click(function(){
		history.back();
	});
})

// 리스트 상세보기 페이지 이동

function read(p1, p2){
	let p3 = $("#pKeyField").val().trim();
	let p4 = $("#pKeyWord").val().trim();
	let param = "/admin/galleryListRead.jsp?num="+p1;
		param += "&nowPage="+p2;
		param += "&keyField="+p3;
		param += "&keyWord="+p4 ;
	location.href= param;
}

// 리스트 페이지 이동

function movePage(pageStart) {
	let p3 = $("#pKeyField").val().trim();  // p3 : keyField
	let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord
	let url = "/admin/galleryList.jsp?gnbParam=list&nowPage=" + pageStart;
	url += "&keyField=" + p3;
	url += "&keyWord=" + p4;
	location.href = url;
}

function moveBlock(nowBlock, pagePerBlock) {
	let nowPage = (nowBlock - 1) * pagePerBlock + 1;
	let p3 = $("#pKeyField").val().trim();  // p3 : keyField
	let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord
	let url = "/admin/galleryList.jsp?gnbParam=list&nowPage=" + nowPage;
	url += "&keyField=" + p3;
	url += "&keyWord=" + p4;
	location.href = url;
}


	