<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>
	<h1>Hello SpringMVC</h1>
	<!-- sessionScope는 생략이 가능하다 -->
	${sessionScope.memberId } 님이 로그인 하셨네요! ${sessionScope.memberName }님 환영합니다!
	<form action="/member/login" method="POST">
		ID:<input type="text" name="memberId"><br>
		PW:<input type="password" name="memberPw">
		<input type="submit" value="로그인">
	</form>
</body>
</html>