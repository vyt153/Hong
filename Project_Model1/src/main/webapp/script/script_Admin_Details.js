/**
 * 
 */
$(function(){
	
	//리스트페이지 검색
	$("button#searchBtn").click(function(){
		/*console.log("ssss");*/
		let keyWord = $("#keyWord").val().trim();
		if(keyWord==""){
			alert("검색어를 입력해주세요");
			$("#keyWord").focus();
		} else{
			let param = $("input#nowPage").val().trim();
			let p3 = $("#keyField").val().trim();
			let p4 = $("#keyWord").val().trim();
			let p5 = $("#uid").val().trim();

			let url = "/admin/readBBSDetails.jsp?uid=" + p5;
			url += "&nowPage=" + param;
			url += "&keyField=" + p3;
			url += "&keyWord=" + p4;
			location.href = url;
		}
	});
	
	// 검색 결과를 유지한 리스트페이지 이동
	$("#listBtn").click(function(){
		let param = $("input#nowPage").val().trim();
		let p3 = $("#pKeyField").val().trim();
		let p4 = $("#pKeyWord").val().trim();
		let p5 = $("#pUid").val().trim();
		
		let url ="/admin/readBBSDetails.jsp?uid="+p5;
			url += "&nowPage="+param;
			url += "&keyField=" + p3 ;
			url += "&keyWord=" + p4 ;
		location.href = url;
	});
	
});


// Mng 이동

function uMove(){
	let p3 = $("#pKeyField").val().trim();  // p3 : keyField
	let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord
	let p5 = $("#puid").val().trim();
	let url = "/admin/readUserDetails.jsp?uid=" +p5;
		url += "&nowPage="+1;
		url += "&keyField=" + p3 ;
		url += "&keyWord=" + p4 ;
	location.href = url;
}
function gMove(){
	let p3 = $("#pKeyField").val().trim();  // p3 : keyField
	let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord
	let p5 = $("#puid").val().trim();
	let url = "/admin/readBBSDetails.jsp?uid=" +p5;
		url += "&nowPage="+1;
		url += "&keyField=" + p3 ;
		url += "&keyWord=" + p4 ;
	location.href = url;
}

// BBS 페이지

function movePage(pageStart, uidParam) {
	let p3 = $("#pKeyField").val().trim();  // p3 : keyField
	let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord
	let url = "/admin/readBBSDetails.jsp?uid="+uidParam;
		url += "&nowPage="+pageStart;
		url += "&keyField=" + p3 ;
		url += "&keyWord=" + p4 ;
	location.href = url;
}

function moveBlock(nowBlock,pagePerBlock, uidParam) {
	let nowPage = (nowBlock -1) * pagePerBlock +1;
	let p3 = $("#pKeyField").val().trim();  // p3 : keyField
	let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord
	let url = "/admin/readBBSDetails.jsp?uid="+uidParam;
		url += "&nowPage="+pageStart;
		url += "&keyField=" + p3 ;
		url += "&keyWord=" + p4 ;
	location.href = url;
}

function read(p1,p2){
	let p3 = $("#pKeyField").val().trim();
	let p4 = $("#pKeyWord").val().trim();
	let param = "/admin/bbsListRead.jsp?num="+p1;
		param += "&nowPage="+p2;
		param += "&keyField="+p3;
		param += "&keyWord="+p4;
	location.href = param;
}