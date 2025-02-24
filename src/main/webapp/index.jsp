<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>
	<h1>Hello SpringMVC</h1>
	<!-- 로그인이 되면 로그인한 사람이 사용할수 있는 메뉴  -->
	<c:if test="${memberId ne null && memberId ne '' }">
	<!-- sessionScope는 생략이 가능하다 -->
	${sessionScope.memberId } 님이 로그인 하셨네요! ${sessionScope.memberName }님 환영합니다!<br>
		<a href="/member/detail">마이페이지</a><br>
		<a href="/member/logout">로그아웃</a>
	</c:if>
	
	<!-- 로그인이 되지 않았으면 로그인 폼을 출력 -->
	<c:if test="${memberId eq null || memberId eq '' }">
	<form action="/member/login" method="POST">
		ID:<input type="text" name="memberId"><br>
		PW:<input type="password" name="memberPw"><br><br>
		<input type="submit" value="로그인">
	</form>
		<form action ="/member/insert" method="get">
		<button type ="submit" > 회원가입</button>
	</form>
	</c:if>
	
</body>
</html>