<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	div#wrap {
	width: 600px;
	padding: 20px;
	border: 3px solid #aaa;
	border-radius: 6px;
	margin: 10px auto;
	}
	hr {
	margin: 10px;
	}
	table {
	width: 580px;
	border: 1px solid #aaa;
	margin: 10px;
	border-collapse: collapse;
	}
	table#memberTbl td {
	padding: 2px 10px;
	border: 1px solid #aaa;
	}
	table#memberTbl td:first-child {
	width: 30px;
	font-size: 17px;
	text-align: right:
	}
	a {
	font-size: 18px;
	padding: 4px 10px;
	cursor: pointer;
	}
</style>
</head>
<body>
	<div id="wrap">
		<h1>상세 내용 보기 화면</h1>
		<hr>
		<%@ include file="../inc/headerGNB.jsp" %>
		<table id="memberTbl">
			<thead>
				<tr>
					<th>번호</th>
					<th>ID</th>
					<th>이름</th>
					<th>Email</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="row" items="${list}">
				<tr>
					<td> ${row.num}</td>
					<td>
					<a href="/detail?num=${row.num}">
					${row.uid}
					</a>
					</td>
					<td>${row.uname}</td>
					<td>${row.uemail}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div>
		</div>
	</div>
</body>
</html>