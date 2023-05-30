<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="gMgr" class="pack.gallery.GalleryMgr" />

<script>
	
	setTimeout(function () {
	<%if(gMgr.updateGallery(request)==1){%>
		alert("수정이 완료되었습니다.");
		location.href= "/admin/galleryList.jsp?gnbParam=gallery";
	}, 2500);
	<%} else{%>
		alert("수정에 실패하였습니다.");
		history.back();
	<%}%>

</script>