$(function(){
	
	// 댓글 작성
	$("#comBtn").click(function(){
		let comment = $("#comment").val().trim();
		
		if (comment == "") {
			alert("댓글을 입력해주세요.");
			$("#comment").focus();
		} else {
			$("#comFrm").attr("action", "/comments/comProc.jsp");
			$("#comFrm").submit();
		}
	});
	
	
});

function delProcMtd(cnum){
	
	let chkToF = confirm("삭제하시겠습니까?");
	
	if (chkToF == true) {
		console.log(cnum);
		
		location.href = "/comments/delProc.jsp?cnum=" + cnum;
		
	}
	
}