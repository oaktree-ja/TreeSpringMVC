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
					<td><a href ="/notice/detail?noticeNo=${notice.noticeNo }">${notice.noticeSubject}</a></td>
					<td>${notice.noticeWriter }</td>
					<td>${notice.noticeDate }</td>
					<c:if test="${not empty notice.noticeFilename}">
						<td>o</td> <!-- 첨부파일이 있을때 표시  -->
					</c:if>
					<c:if test="${empty notice.noticeFilename}">
						<td>x</td> <!-- 첨부파일이 없을때 표시  -->
					</c:if>
					
				</tr>
			</c:forEach>
			<tr>
				<td colspan="5" align="center">
					<c:if test="${startNavi ne 1}">
					<a href="/notice/list?page=">이전</a>
					</c:if>
					<c:forEach begin="${startNavi}" end="${endNavi}" var="p">
						<a href="/notice/list?page=${p}">${p}</a>
					</c:forEach>	
					<c:if test="${endNavi ne maxPage}">
					<a href="/notice/list?page=${endNavi+1}">다음</a>
					</c:if>
				</td>
			
			</tr>
		</table>
	</body>
</html>