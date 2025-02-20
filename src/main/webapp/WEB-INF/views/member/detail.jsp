<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Spring-mypage</title>
	</head>
	<body>
		<h1>mypage</h1>
		ID: <span>${member.memberId }</span><br>
		NAME: <span>${member.memberName }</span><br>
		AGE: <span>${member.memberAge }</span><br>
		GENDER: <span>${member.memberAge }</span><br>
		EMAIL: <span>${member.memberEmail }</span><br>
		PHONE: <span>${member.memberPhone }</span><br>
		ADDRESS: <span>${member.memberAddress }</span><br>
		DATE: <span>${member.memberDate }</span><br>
		<!-- (a tag)링크를 클릭했을때 아무 동작도 하지 않도록 하는 방법, 페이지 이동이나 새로 고침이 되지 않음
			기본 링크 동작을 막을때 사용함
		-->
		<a href="/member/update">수정페이지로 이동</a><br> <a href="javascript:void(0);" onclick="deleteConfirm();">회원탈퇴</a><br>
		<a href="/">메인으로 이동</a>
		<script >
			function deleteConfirm(){
				var result = confirm("정말 탈퇴 하시겠습니까");
				if(result){
					//멤버 정보 삭제
					
					location.replace("/member/delete");
					//alert("탈퇴 완료 되었습니다");
				}
			}
		</script>
	</body>
</html>