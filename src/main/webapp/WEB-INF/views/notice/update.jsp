<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지사항-수정</title>
	</head>
	<body>
		<h1> 공지사항 수정 </h1>
		<form action="/notice/update" method="post" enctype="multipart/form-data">
			작성자: <span>${notice.noticeWriter }</span><br>
			제목: <input type="text" name="noticeSubject" value="${notice.noticeSubject }" size="50"><br>
			내용: <textarea rows="6" cols="60" name="noticeContent">${notice.noticeContent }</textarea><br>
			첨부파일:<span><a href="..${notice.noticeFilepath }"download>${notice.noticeFilename }</a></span> 
			<input type="file"name="rerloadFile"><br>
			<button type ="submit" >등록</button>
		</form>
	</body>
</html>