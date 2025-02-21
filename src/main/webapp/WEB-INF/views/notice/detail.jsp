<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지사항-상세</title>
		</head>
		<style>
			table{
				border: 1px solid black;
				border-collapse: collapse;
			}
			th,td{
				border: 1px solid rgb(110,102,102);
			}
		</style>
	<body>
		<h1>공지사항 - 상세</h1>
		
		<table>
			<tr>
				<th>제목</th>
				<td>${notice.noticeSubject }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${notice.noticeWriter }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${notice.noticeContent }</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td><a href="..${notice.noticeFilepath }"download>${notice.noticeFilename }</a></td>
			</tr>
		</table>
		<div>
			<button type= "button" onclick="showModifyForm(${notice.noticeNo })";>수정하기</button>
			<button type= "button">삭제하기</button>
			<button type = "button" id="listBtn">목록으로</button>
			<button type= "button" onclick="goBack();">뒤로가기</button>
		</div>
		<script>
			function showModifyForm(noticeNo){
				location.href ="/notice/update?noticeNo="+ noticeNo;
			}
			document.querySelector("#listBtn").addEventListener("click",function(){
				location.href ="/notice/list";
			});
			
			function goBack(){
				history.go(-1);
			}
		</script>
		
	</body>
</html>