<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Update page</title>
	</head>
	<body>
		<form action ="/member/update" method="POST">
			<input type ="hidden" name="memberId" value="${member.memberId}"><br>
			PW :<input type ="password" name="memberPw"><br>
			EMAIL :<input type ="text" name="memberEmail"value="${member.memberEmail}"><br>
			PHONE :<input type ="text" name="memberPhone" value="${member.memberPhone}"><br>
			ADDRESS :<input type ="text" name="memberAddress" value="${member.memberAddress}"><br>
			<input type ="submit" value="수정">
		</form>
	</body>
</html>