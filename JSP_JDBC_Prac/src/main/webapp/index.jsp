<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Document</title>
<link rel="stylesheet" href="/style/style.css">

</head>
<body>
	<div id="wrap">
		<div id="header">
			<a href="#"><img src="images/logo_header.png" alt="header" /></a>
			<div>
				<div>
					<ul class="nav">
						소장처별
						<li>한국</li>
						<li>프랑스</li>
						<li>이탈리아</li>
						<li>독일</li>
					</ul>
				</div>
				<div>
					<ul  class="nav">
						장르별
						<li>회화</li>
						<li>판화</li>
						<li>공예/고대유물</li>
						<li>그외</li>
					</ul>
				</div>
				<div>
					<ul  class="nav">
						테마별
						<li>초상화/인물</li>
						<li>종교</li>
						<li>신화/문학</li>
						<li>일상/스포츠</li>
					</ul >
				</div>
				<div>
					<ul class="nav">
						시대별
						<li>1900년 이전</li>
						<li>1900년대</li>
						<li>현대</li>
						<li>그외</li>
					</ul>
				</div>
			</div>
			<ul class="login">
				<li><a href="/member/login.html">로그인</a></li>
				<li><a href="/member/join.html">회원가입</a></li>
			</ul>
			<ul class="loginUl name">
				<li><span><%= session.getAttribute("id") %></span>님 접속중</li>
				<li id="logout"><a href="/member/logoutProc.jsp">로그아웃</a></li>
				<li><a href="/change/infoChange.jsp">정보수정</a></li>
				<li><a href="/change/quit.html">회원탈퇴</a></li>
			</ul>
		</div>
		<div id="main">
			<div id="slideFrame">
				<a href="#"><img src="images/slideShow_01.jpg" alt="#" /><span>세계의
						거장과 함께한 전시회</span></a> <a href="#"><img src="images/slideShow_02.jpg"
					alt="#" /><span>야외 조형물 전시장도 오픈합니다.</span></a> <a href="#"><img
					src="images/slideShow_03.jpg" alt="#" /><span>명화와 함께 떠나는 휴가</span></a>
			</div>
			<div id="section">
				<div>
					<button id="btn1">공지사항</button>
					<table id="info">
						<tbody>
							<tr>
								<td id="layerTd">2019 시민큐레이터 프로젝트</td>
								<td>2019/05/07</td>
							</tr>
							<tr>
								<td>SeMA신소장품 멀티-엑세스 4</td>
								<td>2019/05/03</td>
							</tr>
							<tr>
								<td>월 북서울미술관 문화행사 일정</td>
								<td>2019/02/22</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div id="gallery">
					<button id="btn2">갤러리</button>
					<div>
						<img src="images/gallery_01.jpg" alt="" /> <img
							src="images/gallery_02.jpg" alt="" /> <img
							src="images/gallery_03.jpg" alt="" />
					</div>
				</div>
				<div id="banner">
					<img src="images/banner.png" alt="" />
				</div>
			</div>
			<div id="footer">
				<a href="#"><img src="images/logo_Footer.png" alt="#" /></a>
				<div id="address">
					<i>04515 서울특별시 중구 덕수궁길 61 (서소문동) (02) 2124-8800</i> <br /> ©2019
						Milwaukee Art Museum 700 N. Art Museum Drive Milwaukee, WI 53202
					
				</div>
				<div id="icon">
					<a href="#"><img src="images/ico_fb.png" alt="" /></a> 
					<a href="#"><img src="images/ico_in.png" alt="" /></a> 
					<a href="#"><img src="images/ico_blog.png" alt="" /></a>
				</div>
			</div>
		</div>
	</div>
	<div id="layer">
		<h2>
			["여름의 문턱", 5월 29일
			&lt;콘서트 +뮤지엄나이트&gt; 개최]
		</h2>
		<p>
			- 서울시립미술관은 5월 29일 수요일 19:00∽20:00 서소문 본관에서 옥상 세마휴에서
			&lt;콘서트 +뮤지엄나이트&gt; 개최합니다.
		</p>
		<p>- '여름의 문턱'이라는 주제로 진행되는 이번 콘서트는 뮤지션 오지은의 공연으로 이루어지며, &lt;그렇게 정해진
			길 위에서 &gt;, &lt;NONE&gt; 등 10여 곡을 선보일 예정입니다.</p>
		<p>- 오지은은 2018 SeMA 신소장품&lt;멀티-액세스 4913&gt;전과 연계한 뮤지엄나이트 프로그램의 뮤직
			디렉터를 맡아 전시 작품과 어울리는 8개의 곡을 선곡한 바 있습니다.</p>
		<button id="btnLayer">닫기</button>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script src="script/script.js"></script>
	<script>
	$(function () {
		<%
		if(session.getAttribute("id")!=null){%>
			$(".name").removeClass("name");
			$(".login").css({"display":"none"});
		<% }%>
		$("#logout").click(function () {
			alert("로그아웃 되었습니다.")
		})
	})
	
	</script>
	<!-- div#wrap -->
</body>

</html>