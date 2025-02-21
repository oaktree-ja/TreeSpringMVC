<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지사항 전체조회</title>
		<style>
			table{
				border: 1px solid black;
				border-collapse: collapse;
			}
			th,td{
				border: 1px solid rgb(110,102,102);
			}
		</style>
	</head>
	<body>
		<h1>공지사항 게시판</h1>
		<table>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일자</th>
				<th>첨부파일</th>
			</tr>
			<c:forEach items="${nList }" var="notice" varStatus="i">
				<tr>
					<td>${i.count }</td>
					<td>${notice.noticeSubject}</td>
					<td>${notice.noticeWriter }</td>
					<td>${notice.noticeDate }</td>
					<td>${notice.noticeFilename}</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="5" align="center">
					<a href="/notice/list?page=">이전</a>
					<c:forEach begin="1" end="5" var="p">
						<a href="/notice/list?page=${p}">${p}</a>
					</c:forEach>	
					<a href="/notice/list?page=6">다음</a>
				</td>
			
			</tr>
		</table>
	</body>
</html>